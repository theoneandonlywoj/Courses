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
