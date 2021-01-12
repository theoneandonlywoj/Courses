defmodule TeamLicenseWeb.TeamLicenseLive do
  use TeamLicenseWeb, :live_view

  alias TeamLicense.Licenses

  def mount(_params, _session, socket) do
    {:ok, assign(socket, seats: 2, amount: Licenses.calculate(2))}
  end

  def render(assigns) do
    ~L"""
    <h1>Team License</h1>
    <div>
      <div class="seats" style="display: inline-flex; align-items: center;">
        <img src="images/license.svg" height="40px">
        <span>
          Your license is currently for
          <strong><%= @seats %></strong> seats.
        </span>
      </div>
      <form phx-change="update">
        <input type="range" min="1" max="10"
              name="seats" value="<%= @seats %>" />
      </form>
      <div class="amount">
        <%= @amount %>
      </div>
    </div>
    """
  end

  def handle_event("update", %{"seats" => seats}, socket) do
    seats = String.to_integer(seats)

    socket =
      assign(socket,
        seats: seats,
        amount: Licenses.calculate(seats)
      )

    {:noreply, socket}
  end
end
