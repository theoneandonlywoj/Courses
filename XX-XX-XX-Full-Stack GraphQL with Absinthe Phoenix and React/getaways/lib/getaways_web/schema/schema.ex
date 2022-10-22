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
