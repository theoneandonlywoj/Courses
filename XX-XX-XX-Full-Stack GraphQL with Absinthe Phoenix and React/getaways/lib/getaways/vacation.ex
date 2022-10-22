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
