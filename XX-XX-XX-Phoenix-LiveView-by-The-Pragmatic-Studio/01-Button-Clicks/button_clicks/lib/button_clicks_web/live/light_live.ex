defmodule ButtonClicksWeb.LightLive do
  use ButtonClicksWeb, :live_view

  def mount(_params, _session, socket) do
    {:ok, assign(socket, :brightness, 10)}
  end

  def render(assigns) do
    ~L"""
      <h1>Front Porch Light</h1>
      <div class="meter">
      <span style="width: <%= @brightness %>%">
        <%= @brightness %>%
      </span>
      </div>
      <button phx-click="off">
        Off
      </button>
      <button phx-click="on">
        On
      </button>
      <button phx-click="down">
        Down
      </button>
      <button phx-click="up">
        Up
      </button>
    """
  end

  def handle_event("on", _value, socket) do
    {:noreply, assign(socket, :brightness, 100)}
  end

  def handle_event("off", _value, socket) do
    {:noreply, assign(socket, :brightness, 0)}
  end

  def handle_event("down", _value, socket) do
    {:noreply, update(socket, :brightness, fn b -> max(b - 10, 0) end)}
  end

  def handle_event("up", _value, socket) do
    {:noreply, update(socket, :brightness, fn b -> min(b + 10, 100) end)}
  end
end
