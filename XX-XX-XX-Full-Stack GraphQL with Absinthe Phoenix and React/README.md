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

## Generate the Review schema (without a migration as we have already done it)
```sh
mix phx.gen.schema Vacation.Review reviews --no-migration
```

## Content of the schema (lib/getaways/vacation/review.ex)
```elixir
defmodule Getaways.Vacation.Review do
  use Ecto.Schema
  import Ecto.Changeset

  schema "reviews" do
    field :rating, :integer
    field :comment, :string

    belongs_to :place, Getaways.Vacation.Place
    belongs_to :user, Getaways.Accounts.User

    timestamps(type: :utc_datetime)
  end

  def changeset(review, attrs) do
    required_fields = [:rating, :comment, :place_id]

    review
    |> cast(attrs, required_fields)
    |> validate_required(required_fields)
    |> assoc_constraint(:place)
    |> assoc_constraint(:user)
  end
end
```

## Provide seed data (priv/repo/seeds.ex).
```elixir
# Script for populating the database. You can run it as:
#
#     mix run priv/repo/seeds.exs
#

alias Getaways.Repo
alias Getaways.Vacation.{Place, Booking, Review}
alias Getaways.Accounts.User

#
# USERS
#

mike =
  %User{}
  |> User.changeset(%{
    username: "mike",
    email: "mike@example.com",
    password: "secret"
  })
  |> Repo.insert!()

nicole =
  %User{}
  |> User.changeset(%{
    username: "nicole",
    email: "nicole@example.com",
    password: "secret"
  })
  |> Repo.insert!()

beachbum =
  %User{}
  |> User.changeset(%{
    username: "beachbum",
    email: "beachbum@example.com",
    password: "secret"
  })
  |> Repo.insert!()

#
# PLACES
#

images_url = "#{GetawaysWeb.Endpoint.url()}/images"

%Place{
  name: "Sand Castle",
  slug: "sand-castle",
  description: "Build endless sand castles in your front yard",
  location: "Portugal",
  max_guests: 2,
  pet_friendly: false,
  pool: false,
  wifi: false,
  price_per_night: Decimal.from_float(195.00),
  image: "#{images_url}/sand-castle.jpg",
  image_thumbnail: "#{images_url}/sand-castle-thumb.jpg",
  bookings: [
    %Booking{
      start_date: ~D[2019-10-18],
      end_date: ~D[2019-10-21],
      total_price: Decimal.from_float(585.00),
      user: mike
    }
  ]
}
|> Repo.insert!()

%Place{
  name: "Blue Igloo",
  slug: "blue-igloo",
  description: "Chill out!",
  location: "Canada",
  max_guests: 3,
  pet_friendly: false,
  pool: false,
  wifi: false,
  price_per_night: Decimal.from_float(100.00),
  image: "#{images_url}/blue-igloo.jpg",
  image_thumbnail: "#{images_url}/blue-igloo-thumb.jpg",
  bookings: [
    %Booking{
      start_date: ~D[2019-07-21],
      end_date: ~D[2019-07-31],
      total_price: Decimal.from_float(1000.00),
      user: mike
    }
  ],
  reviews: [
    %Review{
      comment: "It's a chillaxing experience! ❄️",
      rating: 5,
      user: nicole,
      inserted_at: DateTime.from_naive!(~N[2019-03-26 22:00:00], "Etc/UTC")
    }
  ]
}
|> Repo.insert!()

%Place{
  name: "Dock House",
  slug: "dock-house",
  description: "Escape to simplicity...",
  location: "Secret Lake",
  max_guests: 2,
  pet_friendly: true,
  pool: false,
  wifi: false,
  price_per_night: Decimal.from_float(100.00),
  image: "#{images_url}/dock-house.jpg",
  image_thumbnail: "#{images_url}/dock-house-thumb.jpg",
  bookings: [
    %Booking{
      start_date: ~D[2019-06-10],
      end_date: ~D[2019-06-17],
      total_price: Decimal.from_float(700.00),
      user: nicole
    },
    %Booking{
      start_date: ~D[2019-08-07],
      end_date: ~D[2019-08-12],
      total_price: Decimal.from_float(3500.00),
      user: mike
    }
  ],
  reviews: [
    %Review{
      comment: "A little splash of heaven! 💦",
      rating: 5,
      user: mike,
      inserted_at: DateTime.from_naive!(~N[2019-03-15 22:00:00], "Etc/UTC")
    },
    %Review{
      comment: "Enjoyed some absinthe on the dock. 🍹",
      rating: 4,
      user: beachbum,
      inserted_at: DateTime.from_naive!(~N[2019-03-21 09:00:00], "Etc/UTC")
    },
    %Review{
      comment: "Great fishing! 🐟",
      rating: 5,
      user: nicole
    }
  ]
}
|> Repo.insert!()

%Place{
  name: "Ski Cabin",
  slug: "ski-cabin",
  description: "Ski in and ski out!",
  location: "Switzerland",
  max_guests: 6,
  pet_friendly: true,
  pool: false,
  wifi: false,
  price_per_night: Decimal.from_float(225.00),
  image: "#{images_url}/ski-cabin.jpg",
  image_thumbnail: "#{images_url}/ski-cabin-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Hobbit House",
  slug: "hobbit-house",
  description: "Short cuts make delays, but inns make longer ones.",
  location: "New Zealand",
  max_guests: 4,
  pet_friendly: true,
  pool: false,
  wifi: true,
  price_per_night: Decimal.from_float(150.00),
  image: "#{images_url}/hobbit-house.jpg",
  image_thumbnail: "#{images_url}/hobbit-house-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Greek Villa",
  slug: "greek-villa",
  description: "Incredible ocean views",
  location: "Greece",
  max_guests: 6,
  pet_friendly: true,
  pool: false,
  wifi: true,
  price_per_night: Decimal.from_float(225.00),
  image: "#{images_url}/greek-villa.jpg",
  image_thumbnail: "#{images_url}/greek-villa-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Captain's Quarters",
  slug: "captains-quarters",
  description: "Slumber at sea",
  location: "Great Lakes, USA",
  max_guests: 2,
  pet_friendly: false,
  pool: false,
  wifi: false,
  price_per_night: Decimal.from_float(150.00),
  image: "#{images_url}/captains-quarters.jpg",
  image_thumbnail: "#{images_url}/captains-quarters-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Tranquil Tower",
  slug: "tranquil-tower",
  description: "Lift your spirit on stilts",
  location: "North Sea, Netherlands",
  max_guests: 4,
  pet_friendly: true,
  pool: false,
  wifi: false,
  price_per_night: Decimal.from_float(125.00),
  image: "#{images_url}/tranquil-tower.jpg",
  image_thumbnail: "#{images_url}/tranquil-tower-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Courtyard Oasis",
  slug: "courtyard-oasis",
  description: "Float in your own lazy river",
  location: "Morocco",
  max_guests: 6,
  pet_friendly: true,
  pool: true,
  wifi: true,
  price_per_night: Decimal.from_float(200.00),
  image: "#{images_url}/courtyard-oasis.jpg",
  image_thumbnail: "#{images_url}/courtyard-oasis-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Beachfront Hut",
  slug: "beachfront-hut",
  description: "Swim, snorkel, and sunburn!",
  location: "Maldives",
  max_guests: 4,
  pet_friendly: false,
  pool: false,
  wifi: false,
  price_per_night: Decimal.from_float(145.00),
  image: "#{images_url}/beachfront-hut.jpg",
  image_thumbnail: "#{images_url}/beachfront-hut-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Mountain Lake Cabin",
  slug: "mountain-lake-cabin",
  description: "Emerald waters await",
  location: "Italy",
  max_guests: 8,
  pet_friendly: true,
  pool: false,
  wifi: false,
  price_per_night: Decimal.from_float(275.00),
  image: "#{images_url}/mountain-lake-cabin.jpg",
  image_thumbnail: "#{images_url}/mountain-lake-cabin-thumb.jpg",
  bookings: [
    %Booking{
      start_date: ~D[2019-06-01],
      end_date: ~D[2019-06-30],
      total_price: Decimal.from_float(8250.00),
      user: mike
    }
  ]
}
|> Repo.insert!()

%Place{
  name: "Lighthouse Retreat",
  slug: "lighthouse-retreat",
  description: "A brilliant location!",
  location: "Lake Michigan",
  max_guests: 10,
  pet_friendly: true,
  pool: false,
  wifi: true,
  price_per_night: Decimal.from_float(275.00),
  image: "#{images_url}/lighthouse-retreat.jpg",
  image_thumbnail: "#{images_url}/lighthouse-retreat-thumb.jpg",
  bookings: [
    %Booking{
      start_date: ~D[2019-09-01],
      end_date: ~D[2019-09-30],
      total_price: Decimal.from_float(8250.00),
      user: mike
    }
  ]
}
|> Repo.insert!()

%Place{
  name: "Medieval Treehouse",
  slug: "medieval-treehouse",
  description: "Enchantment beckons",
  location: "Sherwood Forest",
  max_guests: 2,
  pet_friendly: true,
  pool: false,
  wifi: false,
  price_per_night: Decimal.from_float(175.00),
  image: "#{images_url}/medieval-treehouse.jpg",
  image_thumbnail: "#{images_url}/medieval-treehouse-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Infinity Pool",
  slug: "infinity-pool",
  description: "The views go on and on...",
  location: "Mexico",
  max_guests: 7,
  pet_friendly: true,
  pool: true,
  wifi: true,
  price_per_night: Decimal.from_float(275.00),
  image: "#{images_url}/infinity-pool.jpg",
  image_thumbnail: "#{images_url}/infinity-pool-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Mountain Chalet",
  slug: "mountain-chalet",
  description: "Stay warm and cozy by the fire",
  location: "Emerald Lake, Canada",
  max_guests: 6,
  pet_friendly: true,
  pool: false,
  wifi: true,
  price_per_night: Decimal.from_float(195.00),
  image: "#{images_url}/mountain-chalet.jpg",
  image_thumbnail: "#{images_url}/mountain-chalet-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Oceanside",
  slug: "oceanside",
  description: "Go exploring on the beach",
  location: "Florida",
  max_guests: 8,
  pet_friendly: true,
  pool: false,
  wifi: true,
  price_per_night: Decimal.from_float(175.00),
  image: "#{images_url}/oceanside.jpg",
  image_thumbnail: "#{images_url}/oceanside-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Spanish Villa",
  slug: "spanish-villa",
  description: "Party all night",
  location: "Spain",
  max_guests: 10,
  pet_friendly: true,
  pool: true,
  wifi: true,
  price_per_night: Decimal.from_float(225.00),
  image: "#{images_url}/spanish-villa.jpg",
  image_thumbnail: "#{images_url}/spanish-villa-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Hammock House",
  slug: "hammock-house",
  description: "Rest easy in the backcountry",
  location: "National Park",
  max_guests: 2,
  pet_friendly: true,
  pool: false,
  wifi: false,
  price_per_night: Decimal.from_float(110.00),
  image: "#{images_url}/hammock-house.jpg",
  image_thumbnail: "#{images_url}/hammock-house-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Starry Yurt",
  slug: "starry-yurt",
  description: "A studio under the stars",
  location: "Colorado",
  max_guests: 2,
  pet_friendly: true,
  pool: false,
  wifi: false,
  price_per_night: Decimal.from_float(110.00),
  image: "#{images_url}/starry-yurt.jpg",
  image_thumbnail: "#{images_url}/starry-yurt-thumb.jpg",
  bookings: [
    %Booking{
      start_date: ~D[2019-06-10],
      end_date: ~D[2019-06-18],
      total_price: Decimal.from_float(880.00),
      user: nicole
    },
    %Booking{
      start_date: ~D[2019-12-10],
      end_date: ~D[2019-12-17],
      total_price: Decimal.from_float(770.00),
      user: nicole,
      state: "canceled"
    },
    %Booking{
      start_date: ~D[2019-08-07],
      end_date: ~D[2019-08-12],
      total_price: Decimal.from_float(1430.00),
      user: beachbum,
      state: "reserved"
    },
    %Booking{
      start_date: ~D[2019-09-01],
      end_date: ~D[2019-09-07],
      total_price: Decimal.from_float(660.00),
      user: beachbum,
      state: "canceled"
    },
    %Booking{
      start_date: ~D[2019-10-07],
      end_date: ~D[2019-10-12],
      total_price: Decimal.from_float(550.00),
      user: beachbum,
      state: "canceled"
    },
    %Booking{
      start_date: ~D[2019-11-10],
      end_date: ~D[2019-11-20],
      total_price: Decimal.from_float(1100.00),
      user: nicole,
      state: "reserved"
    }
  ]
}
|> Repo.insert!()

%Place{
  name: "Waterfront Study",
  slug: "waterfront-study",
  description: "Deep work happens here",
  location: "Flathead Lake",
  max_guests: 2,
  pet_friendly: false,
  pool: false,
  wifi: true,
  price_per_night: Decimal.from_float(150.00),
  image: "#{images_url}/waterfront-study.jpg",
  image_thumbnail: "#{images_url}/waterfront-study-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Lakeside Retreat",
  slug: "lakeside-retreat",
  description: "Relax under the big sky",
  location: "Austria",
  max_guests: 4,
  pet_friendly: true,
  pool: false,
  wifi: true,
  price_per_night: Decimal.from_float(210.00),
  image: "#{images_url}/lakeside-retreat.jpg",
  image_thumbnail: "#{images_url}/lakeside-retreat-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Fairytale Castle",
  slug: "fairytale-castle",
  description: "Live like a king and queen!",
  location: "Germany",
  max_guests: 6,
  pet_friendly: true,
  pool: false,
  wifi: true,
  price_per_night: Decimal.from_float(295.00),
  image: "#{images_url}/fairytale-castle.jpg",
  image_thumbnail: "#{images_url}/fairytale-castle-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Italian Chalet",
  slug: "italian-chalet",
  description: "Do some self-reflection",
  location: "Italy",
  max_guests: 10,
  pet_friendly: true,
  pool: true,
  wifi: true,
  price_per_night: Decimal.from_float(285.00),
  image: "#{images_url}/italian-chalet.jpg",
  image_thumbnail: "#{images_url}/italian-chalet-thumb.jpg"
}
|> Repo.insert!()

%Place{
  name: "Seaside Lodge",
  slug: "seaside-lodge",
  description: "Enjoy the view from the nook",
  location: "California",
  max_guests: 4,
  pet_friendly: true,
  pool: false,
  wifi: true,
  price_per_night: Decimal.from_float(165.00),
  image: "#{images_url}/seaside-lodge.jpg",
  image_thumbnail: "#{images_url}/seaside-lodge-thumb.jpg"
}
|> Repo.insert!()
```

## Create a file for the Vacation context.
```sh
touch lib/getaways/vacation.ex
```

## Content of the Vacation context (lib/getaways/vacation.ex)
```elixir
defmodule Getaways.Vacation do
  @moduledoc """
  The Vacation context: public interface for finding, booking,
  and reviewing vacation places.
  """

  import Ecto.Query, warn: false
  alias Getaways.Repo

  alias Getaways.Vacation.{Place, Booking, Review}
  alias Getaways.Accounts.User

  @doc """
  Returns the place with the given `slug`.

  Raises `Ecto.NoResultsError` if no place was found.
  """
  def get_place_by_slug!(slug) do
    Repo.get_by!(Place, slug: slug)
  end

  @doc """
  Returns a list of all places.
  """
  def list_places do
    Repo.all(Place)
  end

  @doc """
  Returns a list of places matching the given `criteria`.

  Example Criteria:

  [{:limit, 15}, {:order, :asc}, {:filter, [{:matching, "lake"}, {:wifi, true}, {:guest_count, 3}]}]
  """

  def list_places(criteria) do
    query = from(p in Place)

    Enum.reduce(criteria, query, fn
      {:limit, limit}, query ->
        from p in query, limit: ^limit

      {:filter, filters}, query ->
        filter_with(filters, query)

      {:order, order}, query ->
        from p in query, order_by: [{^order, :id}]
    end)
    |> Repo.all()
  end

  defp filter_with(filters, query) do
    Enum.reduce(filters, query, fn
      {:matching, term}, query ->
        pattern = "%#{term}%"

        from q in query,
          where:
            ilike(q.name, ^pattern) or
              ilike(q.description, ^pattern) or
              ilike(q.location, ^pattern)

      {:pet_friendly, value}, query ->
        from q in query, where: q.pet_friendly == ^value

      {:pool, value}, query ->
        from q in query, where: q.pool == ^value

      {:wifi, value}, query ->
        from q in query, where: q.wifi == ^value

      {:guest_count, count}, query ->
        from q in query, where: q.max_guests >= ^count

      {:available_between, %{start_date: start_date, end_date: end_date}}, query ->
        available_between(query, start_date, end_date)
    end)
  end

  # Returns a query for places available between the given
  # start_date and end_date using the Postgres-specific
  # OVERLAPS function.
  defp available_between(query, start_date, end_date) do
    from place in query,
      left_join: booking in Booking,
      on:
        booking.place_id == place.id and
          fragment(
            "(?, ?) OVERLAPS (?, ? + INTERVAL '1' DAY)",
            booking.start_date,
            booking.end_date,
            type(^start_date, :date),
            type(^end_date, :date)
          ),
      where: is_nil(booking.place_id)
  end

  @doc """
  Returns the booking with the given `id`.

  Raises `Ecto.NoResultsError` if no booking was found.
  """
  def get_booking!(id) do
    Repo.get!(Booking, id)
  end

  @doc """
  Creates a booking for the given user.
  """
  def create_booking(%User{} = user, attrs) do
    %Booking{}
    |> Booking.changeset(attrs)
    |> Ecto.Changeset.put_assoc(:user, user)
    |> Repo.insert()
  end

  @doc """
  Cancels the given booking.
  """
  def cancel_booking(%Booking{} = booking) do
    booking
    |> Booking.cancel_changeset(%{state: "canceled"})
    |> Repo.update()
  end

  @doc """
  Creates a review for the given user.
  """
  def create_review(%User{} = user, attrs) do
    %Review{}
    |> Review.changeset(attrs)
    |> Ecto.Changeset.put_assoc(:user, user)
    |> Repo.insert()
  end

  # Dataloader

  def datasource() do
    Dataloader.Ecto.new(Repo, query: &query/2)
  end

  def query(Booking, %{scope: :place, limit: limit}) do
    Booking
    |> where(state: "reserved")
    |> order_by(desc: :start_date)
    |> limit(^limit)
  end

  def query(Booking, %{scope: :user}) do
    Booking
    |> order_by(asc: :start_date)
  end

  def query(queryable, _) do
    queryable
  end
end
```

## Create a file for the Accounts context.
```sh
touch lib/getaways/accounts.ex
```

## Content of the Accounts context (lib/getaways/accounts.ex)
```elixir
defmodule Getaways.Accounts do
  @moduledoc """
  The Accounts context: public interface for account functionality.
  """

  import Ecto.Query, warn: false
  alias Getaways.Repo

  alias Getaways.Accounts.User

  @doc """
  Returns the user with the given `id`.

  Returns `nil` if the user does not exist.
  """
  def get_user(id) do
    Repo.get(User, id)
  end

  @doc """
  Creates a user.
  """
  def create_user(attrs) do
    %User{}
    |> User.changeset(attrs)
    |> Repo.insert()
  end

  @doc """
  Authenticates a user.

  Returns `{:ok, user}` if a user exists with the given username
  and the password is valid. Otherwise, `:error` is returned.
  """
  def authenticate(username, password) do
    user = Repo.get_by(User, username: username)

    with %{password_hash: password_hash} <- user,
         true <- Pbkdf2.verify_pass(password, password_hash) do
      {:ok, user}
    else
      _ -> :error
    end
  end

  # Dataloader

  def datasource() do
    Dataloader.Ecto.new(Repo, query: &query/2)
  end

  def query(queryable, _) do
    queryable
  end
end
```

## Create a file for the Absinthe Schema.
```sh
mkdir lib/getaways_web/schema
touch lib/getaways_web/schema/schema.ex
```

## Initial content of the schema. (lib/getaways_web/schema/schema.ex)
```elixir
defmodule GetawaysWeb.Schema.Schema do
  use Absinthe.Schema
  alias Getaways.{Accounts, Vacation}

  query do

  end
end
```

## Add the first query and type (lib/getaways_web/schema/schema.ex).
```elixir
defmodule GetawaysWeb.Schema.Schema do
  use Absinthe.Schema
  alias Getaways.{Accounts, Vacation}
  alias GetawaysWeb.Resolvers

  # Importing Custom types, f.e. decimal
  import_types(Absinthe.Type.Custom)

  query do
    @desc "Get a place by its slug"
    field :place, :place do
      arg(:slug, non_null(:string))
      resolve(&Resolvers.Vacation.place/3)
    end
  end

  object :place do
    field :id, non_null(:id)
    field :name, non_null(:string)
    field :location, non_null(:string)
    field :slug, non_null(:string)
    field :description, non_null(:string)
    field :max_guests, non_null(:integer)
    field :pet_friendly, non_null(:boolean)
    field :pool, non_null(:boolean)
    field :wifi, non_null(:boolean)
    field :price_per_night, non_null(:decimal)
    field :image, non_null(:string)
    field :image_thumbnail, non_null(:string)
  end
end
```

## Create a file for the Vacations Resolver.
```sh
mkdir lib/getaways_web/resolvers
touch lib/getaways_web/resolvers/vacation.ex
```

## Content (touch lib/getaways_web/resolvers/vacation.ex)
```elixir
defmodule GetawaysWeb.Resolvers.Vacation do
  alias Getaways.Vacation

  def place(_, %{slug: slug}, _) do
    {:ok, Vacation.get_place_by_slug!(slug)}
  end
end
```

## You can test with IEX.
```sh
iex -S mix
```
```iex
doc = """
    query {
        place(slug: "ski-cabin") {
            id
            name
            location
        }
    }
"""

Absinthe.run(doc, GetawaysWeb.Schema.Schema)
```

## Update the router (lib/getaways_web/router.ex)
```elixir
defmodule GetawaysWeb.Router do
  use GetawaysWeb, :router

  pipeline :api do
    plug :accepts, ["json"]
  end

  scope "/" do
    pipe_through :api

    forward "/api", Absinthe.Plug, schema: GetawaysWeb.Schema.Schema

    forward "/graphiql", Absinthe.Plug.GraphiQL,
      schema: GetawaysWeb.Schema.Schema,
      interface: :simple
  end
end
```

## Test the GraphiQL GUI
### Start the server
```sh
mix phx.server
```

### In your browser, go to localhost:4000/graphiql
### In the left panel, paste the query and press the PLAY button.
```graphql
query {
  place(slug: "ski-cabin") {
    id
    name
    location
  }
}
```

## Add query for querying all places (lib/getaways_web/schema/schema.ex)
```elixir
...
  query do
    @desc "Get a place by its slug"
    field :place, :place do
      arg(:slug, non_null(:string))
      resolve(&Resolvers.Vacation.place/3)
    end

    @desc "Get a list of places"
    field :places, list_of(:place) do
      arg(:limit, :integer)
      resolve(&Resolvers.Vacation.places/3)
    end
  end
...
```

## Implementation for Resolvers.Vacation.places/3 (lib/getaways/resolvers/vacation.ex)
```elixir
...
  def places(_, args, _) do
    {:ok, Vacation.list_places(args)}
  end
...
```

## Test it in the browser with the following query
```graphql
{
  places(limit: 10) {
    id
    name
    location
  }
}
```

## Adding sorting (lib/getaways_web/schema/schema.ex)
```elixir
...
query do
    @desc "Get a place by its slug"
    field :place, :place do
      arg(:slug, non_null(:string))
      resolve(&Resolvers.Vacation.place/3)
    end

    @desc "Get a list of places"
    field :places, list_of(:place) do
      arg(:limit, :integer)
      arg(:order, type: :sort_order, default_value: :asc)
      resolve(&Resolvers.Vacation.places/3)
    end
  end

  enum :sort_order do
    value :asc
    value :desc
  end
...
```

## Test it in the browser with two queries (ascending and descending order).
```graphql
{
  places(limit: 10, order: ASC) {
    id
    name
    location
  }
}

```

```graphql
{
  places(limit: 10, order: ASC) {
    id
    name
    location
  }
}
```

## Adding filters (lib/getaways_web/schema/schema.ex)
```elixir
...
  query do
    @desc "Get a place by its slug"
    field :place, :place do
      arg(:slug, non_null(:string))
      resolve(&Resolvers.Vacation.place/3)
    end

    @desc "Get a list of places"
    field :places, list_of(:place) do
      arg(:limit, :integer)
      arg(:order, type: :sort_order, default_value: :asc)
      arg(:filter, :place_filter)
      resolve(&Resolvers.Vacation.places/3)
    end
  end

  @desc "Filters for the list of places"
  input_object :place_filter do
    @desc "Matching a name, location, or description"
    field :matching, :string

    @desc "Has wifi"
    field :wifi, :boolean

    @desc "Allows pets"
    field :pet_friendly, :boolean

    @desc "Has a pool"
    field :pool, :boolean

    @desc "Number of guests"
    field :guest_count, :integer

    @desc "Available for booking between a start and end date"
    field :available_between, :date_range
  end

  @desc "Start and end dates"
  input_object :date_range do
    field :start_date, non_null(:date)
    field :end_date, non_null(:date)
  end

  enum :sort_order do
    value(:asc)
    value(:desc)
  end
...
```

## Test it!
```graphql
{
  places(limit: 10, order: DESC, filter: {matching: "lake", wifi: true}) {
    id
    name
    location
  }
}
```

# Create a file for the ChangesetErrors module
```sh
touch lib/getaways_web/schema/changeset_errors.ex
```

## ChangesetErrors module content (lib/getaways_web/schema/changeset_errors.ex)
```elixir
defmodule GetawaysWeb.Schema.ChangesetErrors do
  @doc """
  Traverses the changeset errors and returns a map of 
  error messages. For example:

  %{start_date: ["can't be blank"], end_date: ["can't be blank"]}
  """
  def error_details(changeset) do
    Ecto.Changeset.traverse_errors(changeset, fn {msg, opts} ->
      Enum.reduce(opts, msg, fn {key, value}, acc ->
        String.replace(acc, "%{#{key}}", to_string(value))
      end)
    end)
  end
end
```

## Update the Vacation Resolver with the ChangesetErrors alias
```elixir
defmodule GetawaysWeb.Resolvers.Vacation do
  alias Getaways.Vacation
  alias GetawaysWeb.Schema.ChangesetErrors

  ...

end
```

## Querying Bookings - Places relationship (the query won't work yet)
```graphql
{
  place(slug: "dock-house") {
    id
    name
    bookings {
      state
      totalPrice
    }
  }
}
```

## Add the Booking object (lib/getaways_web/schema/schema.ex)
```elixir
...
  object :booking do
    field :id, non_null(:id)
    field :start_date, non_null(:date)
    field :end_date, non_null(:date)
    field :state, non_null(:string)
    field :total_price, non_null(:decimal)
  end
...
```

## Update the Places object(lib/getaways_web/schema/schema.ex)
```elixir
...
  object :place do
    field :id, non_null(:id)
    field :name, non_null(:string)
    field :location, non_null(:string)
    field :slug, non_null(:string)
    field :description, non_null(:string)
    field :max_guests, non_null(:integer)
    field :pet_friendly, non_null(:boolean)
    field :pool, non_null(:boolean)
    field :wifi, non_null(:boolean)
    field :price_per_night, non_null(:decimal)
    field :image, non_null(:string)
    field :image_thumbnail, non_null(:string)
    field :bookings, list_of(:booking) do
      resolve &Resolvers.Vacation.bookings_for_place/3
    end
  end
...
```

## Implement the resolver (lib/getaways_web/resolvers/vacation.ex)
```elixir
...
  def bookings_for_place(place, _, _) do
    {:ok, Vacation.bookings_for_place(place)}
  end
...
```

## Implement the function that will obtain the booking for a given place (lib/getaways/vacation.ex)
```elixir
...
 def bookings_for_place(%Place{} = place) do
    Booking
    |> where(place_id: ^place.id)
    |> where(state: "reserved")
    |> Repo.all()
  end
...
```

## Now, you can test with the following queries (the 1st should have 2 queries, the 2nd only 1 query)
```graphql
{
  place(slug: "dock-house") {
    id
    name
    bookings {
      state
      totalPrice
    }
  }
}

```

```graphql
{
  place(slug: "dock-house") {
    id
    name
  }
}
```

## Utilising the Dataloader (fixing N+1 problem).
### You can delete the bookings_for_place function as we won't need it anymore. (lib/getaways/vacation.ex)
### You can delete the bookings_for_place Resolver as we won't need it anymore. (lib/getaways_web/resolvers/vacation.ex)
### Define context function (lib/getaways_web/schema/schema.ex)
```
...
  def context(ctx) do
    source = Dataloader.Ecto.new(Getaways.Repo)

    loader =
      Dataloader.new()
      |> Dataloader.add_source(Vacation, source)

    Map.put(ctx, :loader, loader)
  end
...
```

### Update the Place object (lib/getaways_web/schema/schema.ex) and import Absinthe Dataloader helper for easier macro
```elixir
...
import Absinthe.Resolution.Helpers, only: [dataloader: 1, dataloader: 3]
...
object :place do
    field :id, non_null(:id)
    field :name, non_null(:string)
    field :location, non_null(:string)
    field :slug, non_null(:string)
    field :description, non_null(:string)
    field :max_guests, non_null(:integer)
    field :pet_friendly, non_null(:boolean)
    field :pool, non_null(:boolean)
    field :wifi, non_null(:boolean)
    field :price_per_night, non_null(:decimal)
    field :image, non_null(:string)
    field :image_thumbnail, non_null(:string)

    field :bookings, list_of(:booking), resolve: dataloader(Vacation)
  end
...
  def plugins do
    [Absinthe.Middleware.Dataloader] ++ Absinthe.Plugin.defaults()
  end
...
```

## Update / add other objects.
```elixir
    object :place do
    field :id, non_null(:id)
    field :name, non_null(:string)
    field :location, non_null(:string)
    field :slug, non_null(:string)
    field :description, non_null(:string)
    field :max_guests, non_null(:integer)
    field :pet_friendly, non_null(:boolean)
    field :pool, non_null(:boolean)
    field :wifi, non_null(:boolean)
    field :price_per_night, non_null(:decimal)
    field :image, non_null(:string)
    field :image_thumbnail, non_null(:string)
    field :bookings, list_of(:booking), resolve(dataloader(Vacation))
    field :reviews, list_of(:review), resolve: dataloader(Vacation)
  end

  object :booking do
    field :id, non_null(:id)
    field :start_date, non_null(:date)
    field :end_date, non_null(:date)
    field :state, non_null(:string)
    field :total_price, non_null(:decimal)
    field :user, non_null(:user), resolve: dataloader(Vacation)
    field :place, non_null(:place), resolve: dataloader(Vacation)
  end

  object :review do
    field :id, non_null(:id)
    field :rating, non_null(:integer)
    field :comment, non_null(:string)
    field :inserted_at, non_null(:naive_datetime)
    field :user, non_null(:user), resolve: dataloader(Vacation)
    field :place, non_null(:place), resolve: dataloader(Vacation)
  end

  object :user do
    field :username, non_null(:string)
    field :email, non_null(:string)

    field :bookings, list_of(:booking),
      resolve: dataloader(Vacation)

    field :reviews, list_of(:review), resolve: dataloader(Vacation)
  end
```

### Now, you can query more complicated scenarios.
```sh
{
  places{
    id
    name
    bookings {
      state
      totalPrice
      user {
        email
      }
    }
    reviews {
      rating
      comment
      user {
        username
        email
      }
    }
  }
}
```

## Filtering
### Please review the query functions inside the Vacation module (lib/getaways/vacation.ex).
```elixir
  def query(Booking, %{scope: :place}) do
    Booking
    |> where(state: "reserved")
    |> order_by(desc: :start_date)
  end

  def query(Booking, %{scope: :user}) do
    Booking
    |> order_by(asc: :start_date)
  end
```
### We want to return only Bookings with status "reserved" when queried for a place.
### Update the :place and :user objects (lib/getaways_web/schema/schema.ex)
```elixir
...
  object :place do
    field :id, non_null(:id)
    field :name, non_null(:string)
    field :location, non_null(:string)
    field :slug, non_null(:string)
    field :description, non_null(:string)
    field :max_guests, non_null(:integer)
    field :pet_friendly, non_null(:boolean)
    field :pool, non_null(:boolean)
    field :wifi, non_null(:boolean)
    field :price_per_night, non_null(:decimal)
    field :image, non_null(:string)
    field :image_thumbnail, non_null(:string)

    field :bookings, list_of(:booking),
      resolve: dataloader(Vacation, :bookings, args: %{scope: :place})

    field :reviews, list_of(:review), resolve: dataloader(Vacation)
  end
...
```

### Test it!
```graphql
{
  places{
    id
    name
    bookings {
      state
      totalPrice
      user {
        email
        bookings {
          state
        }
      }
    }
  }
}
```

## Add more filtering, f.e. limit.
### Update the query function in the Vacation module (lib/getaways/vacation.ex).
```elixir
...
  def query(Booking, %{scope: :place, limit: limit}) do
    Booking
    |> where(state: "reserved")
    |> order_by([desc: :start_date])
    |> limit(^limit)
  end
...
```

### Update the :places object (lib/getaways_web/schema/schema.ex)
```elixir
...
  object :place do
    field :id, non_null(:id)
    field :name, non_null(:string)
    field :location, non_null(:string)
    field :slug, non_null(:string)
    field :description, non_null(:string)
    field :max_guests, non_null(:integer)
    field :pet_friendly, non_null(:boolean)
    field :pool, non_null(:boolean)
    field :wifi, non_null(:boolean)
    field :price_per_night, non_null(:decimal)
    field :image, non_null(:string)
    field :image_thumbnail, non_null(:string)

    field :bookings, list_of(:booking) do
      arg(:limit, type: :integer, default_value: 100)
      resolve(dataloader(Vacation, :bookings, args: %{scope: :place}))
    end

    field :reviews, list_of(:review), resolve: dataloader(Vacation)
  end
...
```

### Test it!
```graphql
{
  places{
    id
    name
    bookings(limit: 2) {
      state
      totalPrice
    }
  }
}
```

## Update the context (lib/getaways_web/schema/schema.ex) and the objects
```elixir
  object :place do
    field :id, non_null(:id)
    field :name, non_null(:string)
    field :location, non_null(:string)
    field :slug, non_null(:string)
    field :description, non_null(:string)
    field :max_guests, non_null(:integer)
    field :pet_friendly, non_null(:boolean)
    field :pool, non_null(:boolean)
    field :wifi, non_null(:boolean)
    field :price_per_night, non_null(:decimal)
    field :image, non_null(:string)
    field :image_thumbnail, non_null(:string)

    field :bookings, list_of(:booking) do
      arg(:limit, type: :integer, default_value: 100)
      resolve(dataloader(Vacation, :bookings, args: %{scope: :place}))
    end

    field :reviews, list_of(:review), resolve: dataloader(Vacation)
  end

  object :booking do
    field :id, non_null(:id)
    field :start_date, non_null(:date)
    field :end_date, non_null(:date)
    field :state, non_null(:string)
    field :total_price, non_null(:decimal)
    field :user, non_null(:user), resolve: dataloader(Accounts)
    field :place, non_null(:place), resolve: dataloader(Vacation)
  end

  object :review do
    field :id, non_null(:id)
    field :rating, non_null(:integer)
    field :comment, non_null(:string)
    field :inserted_at, non_null(:naive_datetime)
    field :user, non_null(:user), resolve: dataloader(Accounts)
    field :place, non_null(:place), resolve: dataloader(Vacation)
  end

  object :user do
    field :username, non_null(:string)
    field :email, non_null(:string)

    field :bookings, list_of(:booking),
      resolve: dataloader(Vacation, :bookings, args: %{scope: :user})

    field :reviews, list_of(:review), resolve: dataloader(Vacation)
  end

  def context(ctx) do
    loader =
      Dataloader.new()
      |> Dataloader.add_source(Vacation, Vacation.datasource())
      |> Dataloader.add_source(Accounts, Accounts.datasource())

    Map.put(ctx, :loader, loader)
  end
```

## Add the first mutation for creating a booking. (lib/getaways_web/schema/schema.ex)
```elixir
  query do
  ...
  end

  mutation do
    @desc "Create a booking for a place"
    field :create_booking, :booking do
      arg :place_id, non_null(:id)
      arg :start_date, non_null(:date)
      arg :end_date, non_null(:date)
      resolve &Resolvers.Vacation.create_booking/3
    end
  end
```

## Temporarily hardcode the user to be of id 1. (lib/getaways_web/schema/schema.ex)
```elixir
  def context(ctx) do
    ctx = Map.put(ctx, :current_user, Getaways.Accounts.get_user(1))

    loader =
      Dataloader.new()
      |> Dataloader.add_source(Vacation, Vacation.datasource())
      |> Dataloader.add_source(Accounts, Accounts.datasource())

    Map.put(ctx, :loader, loader)
  end
```

## Add the create_booking resolver (lib/getaways_web/resolvers/vacation.ex)
```elixir
  ...
  def create_booking(_, args, %{context: %{current_user: user}}) do
    case Vacation.create_booking(user, args) do
      {:error, changeset} ->
        {:error,
         message: "Could not create booking",
         details: ChangesetErrors.error_details(changeset)
        }

      {:ok, booking} ->
        {:ok, booking}
    end
  end
  ...
```

## Test it!
```graphql
mutation{
  createBooking(
    placeId: 1,
    startDate: "2022-10-26"
    endDate: "2022-10-30") {
      id
      startDate
      endDate
      state
      totalPrice
    }
}
```

## Test obtaining and error by swapping the startDate and endDate (endDate being before the startDate)
```graphql
mutation{
  createBooking(
    placeId: 1,
    endDate: "2022-10-26"
    startDate: "2022-10-30") {
      id
      startDate
      endDate
      state
      totalPrice
    }
}
```

## Add mutation for cancelling your booking. (lib/getaways_web/schema/schema.ex)
```elixir
  mutation do
    ...

    @desc "Cancel a booking"
    field :cancel_booking, :booking do
      arg :booking_id, non_null(:id)
      resolve &Resolvers.Vacation.cancel_booking/3
    end
  end
```

## Add the resolver resolver (lib/getaways_web/resolvers/vacation.ex)
```elixir
  ...
  def cancel_booking(_, args, %{context: %{current_user: user}}) do
    booking = Vacation.get_booking!(args[:booking_id])

    # Make sure the user owns the booking
    if booking.user_id == user.id do
      case Vacation.cancel_booking(booking) do
        {:error, changeset} ->
          {
            :error,
            message: "Could not cancel booking!",
            details: ChangesetErrors.error_details(changeset)
          }

        {:ok, booking} ->
          {:ok, booking}
      end
    else
      {
        :error,
        message: "Hey, that's not your booking!"
      }
    end
  end
  ...
```

## Test it! (create a review and pick up its id)
```graphql
mutation{
  cancelBooking(bookingId: 14){
    id
    state
  }
}
```

## Sign up mutation (lib/getaways_web/schema/schema.ex)
```elixir
  mutation do
    ...
    @desc "Create a user account"
    field :signup, :session do
      arg :username, non_null(:string)
      arg :email, non_null(:string)
      arg :password, non_null(:string)
      resolve &Resolvers.Accounts.signup/3
    end
  end
  ...
  object :session do
    field :user, non_null(:user)
    field :token, non_null(:string)
  end
```

## Add signup resolver. (lib/getaways_web/resolvers/accounts.ex)
```elixir
defmodule GetawaysWeb.Resolvers.Accounts do
  alias Getaways.Accounts
  alias GetawaysWeb.Schema.ChangesetErrors

  def signup(_, args, _) do
    case Accounts.create_user(args) do
      {:error, changeset} ->
        {
          :error,
          message: "Could not create account", details: ChangesetErrors.error_details(changeset)
        }

      {:ok, user} ->
        token = GetawaysWeb.AuthToken.sign(user)
        {:ok, %{user: user, token: token}}
    end
  end
end
```

## Create authentication token functionality. (lib/getaways/auth_token.ex)
```elixir
defmodule GetawaysWeb.AuthToken do
  @user_salt "user auth salt"

  @doc """
  Encodes the given `user` id and signs it, returning a token
  clients can use as identification when using the API.
  """
  def sign(user) do
    Phoenix.Token.sign(GetawaysWeb.Endpoint, @user_salt, %{id: user.id})
  end

  @doc """
  Decodes the original data from the given `token` and
  verifies its integrity.
  """
  def verify(token) do
    Phoenix.Token.verify(GetawaysWeb.Endpoint, @user_salt, token, max_age: 365 * 24 * 3600)
  end
end
```

## Test it!
```graphql
mutation {
  signup(username: "wojciech", email: "test@gmail.com", password: "password123") {
    user {
      username
      email
    }
    token
  }
}
```

## Sign in mutation (lib/getaways_web/schema/schema.ex)
```elixir
  mutation do
    ...
    @desc "Sign in a user"
    field :signin, :session do
      arg :username, non_null(:string)
      arg :password, non_null(:string)
      resolve &Resolvers.Accounts.signin/3
    end
  end
```

## Add signin resolver. (lib/getaways_web/resolvers/accounts.ex)
```elixir
defmodule GetawaysWeb.Resolvers.Accounts do
  alias Getaways.Accounts
  alias GetawaysWeb.Schema.ChangesetErrors

  ...
  def signin(_, %{username: username, password: password}, _) do
    case Accounts.authenticate(username, password) do
      :error ->
        {:error, "Whoops, invalid credentials!"}

      {:ok, user} ->
        token = GetawaysWeb.AuthToken.sign(user)
        {:ok, %{user: user, token: token}}
    end
  end
end
```

## Test it!
```graphql
mutation {
  signin(username: "wojciech", password: "password123") {
    user {
      username
      email
    }
    token
  }
}
```

## Test it failing with wrong credentials
```graphql
mutation {
  signin(username: "wojciech", password: "incorrect_password") {
    user {
      username
      email
    }
    token
  }
}
```