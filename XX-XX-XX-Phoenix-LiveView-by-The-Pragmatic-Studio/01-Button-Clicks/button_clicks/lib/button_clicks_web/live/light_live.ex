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
    """
  end
end
