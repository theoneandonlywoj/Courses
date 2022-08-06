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

    field :category, :category do
      arg :id, non_null(:integer)
      resolve(fn _, args, _ ->
        {:ok, Repo.get!(Menu.Category, args.id)}
      end)
    end
  end
end
