# Full-Stack GraphQL with Absinthe Phoenix and React
https://pragmaticstudio.com/courses/unpacked-full-stack-graphql-with-absinthe-phoenix-react

# Steps
## Create a new project
```sh
mix phx.new getaways --module Getaways
```

## Add the following libraries (mix.exs)
```elixir
...
{:absinthe, "~> 1.6.1"},
{:absinthe_plug, "~> 1.5.8"},
{:absinthe_phoenix, "~> 2.0.1"},
{:pbkdf2_elixir, "~> 1.0"},
{:cors_plug, "~> 2.0"},
{:dataloader, "~> 1.0.6"}
...
```

## Change the settings for the dev password if needed and create the database.
```sh
mix ecto.create
```

## Create the first migration
```sh
mix ecto.gen.migration create_places_table
```

## Content of the migration (priv/repo/migrations/20221022161418_create_places_table.exs)
```elixir
defmodule Getaways.Repo.Migrations.CreatePlacesTable do
  use Ecto.Migration

  def change do
    create table(:places) do
      add :name, :string, null: false
      add :slug, :string, null: false
      add :description, :string, null: false
      add :location, :string, null: false
      add :price_per_night, :decimal, null: false
      add :image, :string, null: false
      add :image_thumbnail, :string, null: false
      add :max_guests, :integer, null: false
      add :pet_friendly, :boolean, default: false, null: false
      add :pool, :boolean, default: false, null: false
      add :wifi, :boolean, default: false, null: false

      timestamps()
    end

    create unique_index(:places, [:name])
    create unique_index(:places, [:slug])
  end
end
```

## Create the Users migration
```sh
mix ecto.gen.migration create_users_table
```

## Content of the migration (priv/repo/migrations/20221022164606_create_users_table.exs)
```elixir
defmodule Getaways.Repo.Migrations.CreateUsersTable do
  use Ecto.Migration

  def change do
    create table(:users) do
      add :username, :string, null: false
      add :email, :string, null: false
      add :password_hash, :string, null: false

      timestamps()
    end

    create unique_index(:users, [:username, :email])
  end
end
```

## Create the Bookings migration
```sh
mix ecto.gen.migration create_bookings_table
```

## Content of the migration (priv/repo/migrations/20221022170710_create_bookings_table.exs)
```elixir
defmodule Getaways.Repo.Migrations.CreateBookingsTable do
  use Ecto.Migration

  def change do
    create table(:bookings) do
      add :start_date, :date, null: false
      add :end_date, :date, null: false
      add :state, :string, null: false
      add :total_price, :decimal
      add :place_id, references(:places), null: false
      add :user_id, references(:users), null: false

      timestamps()
    end

    create index(:bookings, [:place_id, :user_id])
  end
end
```

# Create the Reviews migration
```sh
mix ecto.gen.migration create_reviews_table
```

## Content of the migration (priv/repo/migrations/20221022171414_create_reviews_table.exs)
```elixir
defmodule Getaways.Repo.Migrations.CreateReviewsTable do
  use Ecto.Migration

  def change do
    create table(:reviews) do
      add :rating, :integer, null: false
      add :comment, :string, null: false
      add :place_id, references(:places), null: false
      add :user_id, references(:users), null: false

      timestamps()
    end

    create index(:reviews, [:place_id])
    create index(:reviews, [:user_id])
  end
end

```

## Migrate
```sh
mix ecto.migrate
```

## Generate the Place schema (without a migration as we have already done it)
```sh
mix phx.gen.schema Vacation.Place places --no-migration
```

## Content of the schema (lib/getaways/vacation/place.ex)
```elixir
defmodule Getaways.Vacation.Place do
  use Ecto.Schema
  import Ecto.Changeset

  schema "places" do
    field :name, :string
    field :slug, :string
    field :description, :string
    field :location, :string
    field :price_per_night, :decimal
    field :image, :string
    field :image_thumbnail, :string
    field :max_guests, :integer, default: 2
    field :pet_friendly, :boolean, default: false
    field :pool, :boolean, default: false
    field :wifi, :boolean, default: false

    has_many :bookings, Getaways.Vacation.Booking
    has_many :reviews, Getaways.Vacation.Review

    timestamps()
  end

  def changeset(place, attrs) do
    required_fields = [
      :name,
      :slug,
      :description,
      :location,
      :price_per_night,
      :image,
      :image_thumbnail
    ]

    optional_fields = [:max_guests, :pet_friendly, :pool, :wifi]

    place
    |> cast(attrs, required_fields ++ optional_fields)
    |> validate_required(required_fields)
    |> unique_constraint(:name)
    |> unique_constraint(:slug)
  end
end

```

## Generate the User schema (without a migration as we have already done it)
```sh
mix phx.gen.schema Accounts.User users --no-migration
```

## Content of the schema (lib/getaways/accounts/user.ex)
```elixir
defmodule Getaways.Accounts.User do
  use Ecto.Schema
  import Ecto.Changeset

  schema "users" do
    field :username, :string
    field :email, :string
    field :password_hash, :string
    field :password, :string, virtual: true

    has_many :bookings, Getaways.Vacation.Booking
    has_many :reviews, Getaways.Vacation.Review

    timestamps()
  end

  def changeset(user, attrs) do
    required_fields = [:username, :email, :password]

    user
    |> cast(attrs, required_fields)
    |> validate_required(required_fields)
    |> validate_length(:username, min: 2)
    |> validate_length(:password, min: 6)
    |> unique_constraint(:username)
    |> unique_constraint(:email)
    |> hash_password()
  end

  defp hash_password(changeset) do
    case changeset do
      %Ecto.Changeset{valid?: true, changes: %{password: password}} ->
        put_change(changeset, :password_hash, Pbkdf2.hash_pwd_salt(password))

      _ ->
        changeset
    end
  end
end
```

## Generate the Booking schema (without a migration as we have already done it)
```sh
mix phx.gen.schema Vacation.Booking bookings --no-migration
```

## Content of the schema (lib/getaways/vacation/booking.ex)
```elixir
defmodule Getaways.Vacation.Booking do
  use Ecto.Schema
  import Ecto.Changeset
  import Ecto.Query

  schema "bookings" do
    field :start_date, :date
    field :end_date, :date
    field :state, :string, default: "reserved"
    field :total_price, :decimal

    belongs_to :place, Getaways.Vacation.Place
    belongs_to :user, Getaways.Accounts.User

    timestamps()
  end

  def changeset(booking, attrs) do
    required_fields = [:start_date, :end_date, :place_id]
    optional_fields = [:state]

    booking
    |> cast(attrs, required_fields ++ optional_fields)
    |> validate_required(required_fields)
    |> validate_start_date_before_end_date()
    |> validate_dates_available()
    |> assoc_constraint(:place)
    |> assoc_constraint(:user)
    |> calculate_total_price()
  end

  def cancel_changeset(booking, attrs) do
    booking
    |> cast(attrs, [:state])
    |> validate_required([:state])
  end

  defp validate_start_date_before_end_date(changeset) do
    case changeset.valid? do
      true ->
        start_date = get_field(changeset, :start_date)
        end_date = get_field(changeset, :end_date)

        case Date.compare(start_date, end_date) do
          :gt ->
            add_error(changeset, :start_date, "cannot be after :end_date")

          _ ->
            changeset
        end

      _ ->
        changeset
    end
  end

  defp validate_dates_available(changeset) do
    case changeset.valid? do
      true ->
        start_date = get_field(changeset, :start_date)
        end_date = get_field(changeset, :end_date)
        place_id = get_field(changeset, :place_id)

        case dates_available?(start_date, end_date, place_id) do
          true ->
            changeset

          false ->
            add_error(changeset, :start_date, "is not available")
        end

      _ ->
        changeset
    end
  end

  defp dates_available?(start_date, end_date, place_id) do
    query =
      from booking in Getaways.Vacation.Booking,
        where:
          booking.place_id == ^place_id and
            fragment(
              "(?, ?) OVERLAPS (?, ? + INTERVAL '1' DAY)",
              booking.start_date,
              booking.end_date,
              type(^start_date, :date),
              type(^end_date, :date)
            )

    case Getaways.Repo.all(query) do
      [] -> true
      _ -> false
    end
  end

  defp calculate_total_price(changeset) do
    case changeset.valid? do
      true ->
        place_id = get_field(changeset, :place_id)
        end_date = get_field(changeset, :end_date)
        start_date = get_field(changeset, :start_date)

        place = Getaways.Repo.get!(Getaways.Vacation.Place, place_id)

        total_nights = Date.diff(end_date, start_date)
        total_price = Decimal.mult(place.price_per_night, total_nights)

        put_change(changeset, :total_price, total_price)

      _ ->
        changeset
    end
  end
end
```
