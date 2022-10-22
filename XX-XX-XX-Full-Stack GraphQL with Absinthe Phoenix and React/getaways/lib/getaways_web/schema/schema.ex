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
