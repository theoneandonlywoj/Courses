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
