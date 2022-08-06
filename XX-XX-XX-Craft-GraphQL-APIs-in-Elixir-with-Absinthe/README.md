# Craft GraphQL APIs in Elixir with Absinthe
Craft GraphQL APIs in Elixir with Absinthe
Flexible, Robust Services for Queries, Mutations, and Subscriptions
by Bruce Williams and Ben Wilson

## Project Setup
##### Check Elixir version
```sh
elixir --version
```
##### Create a new project
```sh
mix phx.new plateslate --module PlateSlate --no-assets --no-html
```

##### Setup a database
```sh
mix ecto.setup
```

##### Adding dependencies to the mix.exs file
```elixir
{:absinthe, "~> 1.6.0"},
{:absinthe_plug, "~> 1.5"},
{:absinthe_phoenix, "~> 2.0.0"},
{:absinthe_relay, "~> 1.5.0"}
```

##### Create first migration
```sh
mix ecto.gen.migration create_categories_table
```

##### Migration: create_categories_table (priv/repo/migrations/20220806161658_create_categories_table.exs)
```elixir
defmodule PlateSlate.Repo.Migrations.CreateCategoriesTable do
  use Ecto.Migration

  def change do
    create table(:categories) do
      add :name, :string, null: false
      add :description, :string

      timestamps()
    end
  end
end
```

##### Migrate
```sh
mix ecto.migrate
```
