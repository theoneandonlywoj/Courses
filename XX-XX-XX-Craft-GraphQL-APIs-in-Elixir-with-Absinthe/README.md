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

##### Create a folder to organise the code
```sh
mkdir lib/plateslate/menu
```

##### Create a file for the Category
```sh
touch lib/plateslate/menu/category.ex
```

##### Category (lib/plateslate/menu/category.ex)
```elixir
defmodule PlateSlate.Menu.Category do
  use Ecto.Schema
  import Ecto.Changeset
  alias PlateSlate.Menu.Category

  schema "categories" do
    field :description, :string
    field :name, :string

    timestamps()
  end

  @doc false
  def changeset(%Category{} = category, attrs) do
    category
    |> cast(attrs, [:description, :name])
    |> validate_required([:name])
  end
end
```

##### Add a Category in the seeds for testing (lib/priv/repo.seeds.ex)
```elixir
# Script for populating the database. You can run it as:
#
#     mix run priv/repo/seeds.exs
#
# Inside the script, you can read and write to any of your
# repositories directly:
#
#     PlateSlate.Repo.insert!(%PlateSlate.SomeSchema{})
#
# We recommend using the bang functions (`insert!`, `update!`
# and so on) as they will fail if something goes wrong.

alias PlateSlate.Repo
alias PlateSlate.Menu.Category

%Category{name: "Category1", description: "Description1"} |> Repo.insert!()
```

##### Create a file for the Absinthe Schema
```sh
touch lib/plateslate_web/schema.ex
```

##### Absinthe Schema file (lib/plateslate_web/schema.ex)
```elixir
defmodule PlateSlateWeb.Schema do
  use Absinthe.Schema

  alias PlateSlate.{Menu, Repo}

  object :category do
    field :id, :id
    field :name, :string
    field :description, :string
  end

  query do
    field :categories, list_of(:category) do
      resolve(fn _, _, _ ->
        {:ok, Repo.all(Menu.Category)}
      end)
    end
  end
end

```

##### Adapt the router
Put the following:
```elixir
  scope "/" do
    pipe_through :api

    forward "/graphiql", Absinthe.Plug.GraphiQL,
      schema: PlateSlateWeb.Schema,
      interface: :simple,
      context: %{pubsub: PlateSlateWeb.Endpoint}
  end
```
after:
```elixir
  pipeline :api do
    plug :accepts, ["json"]
  end
```