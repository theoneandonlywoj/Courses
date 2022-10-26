defmodule GetawaysWeb.Resolvers.Vacation do
  alias Getaways.Vacation
  alias GetawaysWeb.Schema.ChangesetErrors

  def place(_, %{slug: slug}, _) do
    {:ok, Vacation.get_place_by_slug!(slug)}
  end

  def places(_, args, _) do
    {:ok, Vacation.list_places(args)}
  end

  # (parent, args, context)
  def bookings_for_place(place, _, _) do
    {:ok, Vacation.bookings_for_place(place)}
  end

  def create_booking(_, args, %{context: %{current_user: user}}) do
    case Vacation.create_booking(user, args) do
      {:error, changeset} ->
        {:error,
         message: "Could not create booking", details: ChangesetErrors.error_details(changeset)}

      {:ok, booking} ->
        {:ok, booking}
    end
  end
end
