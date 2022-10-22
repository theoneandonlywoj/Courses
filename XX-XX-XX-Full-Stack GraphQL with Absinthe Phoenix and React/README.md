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

## Migrate
```sh
mix ecto.migrate
```

## Generate the Place schema (without a migration as we have already done it)
```sh
mix phx.gen.schema Vacation.Place places --no-migration
```

## Content of the schema ()
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