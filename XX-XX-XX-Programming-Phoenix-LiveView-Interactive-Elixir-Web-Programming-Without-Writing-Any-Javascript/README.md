### Programming Phoenix LiveView by Bruce A.Tate and Sophie DeBenedetto
#### Interactive Elixir Web Programming Without Writing Any JavaScript

##### Setup
```bash
mix phx.new pento
cd pento
mix ecto.create
```

##### Development
- Add /guess endpoint (lib/pento_web/router.ex)
```elixir
...
  scope "/", PentoWeb do
    pipe_through :browser

    get "/", PageController, :home
    live "/guess", WrongLive
  end
...
```

- Create file for the wrong_live.ex:
```bash
mkdir lib/pento_web/live
touch lib/pento_web/live/wrong_live.ex
```

- Content (lib/pento_web/live/wrong_live.ex):
```elixir
defmodule PentoWeb.WrongLive do
  use PentoWeb, :live_view

  def mount(_params, _session, socket) do
    {:ok, assign(socket, score: 0, message: "Make a guess:")}
  end

  def render(assigns) do
    ~H"""
    <h1>Your score: <%= @score %></h1>
    <h2>
      <%= @message %>
    </h2>
    <h2>
      <%= for n <- 1..10 do %>
        <.link href="#" phx-click="guess" phx-value-number={n}>
          <%= n %>
        </.link>
      <% end %>
    </h2>
    """
  end

  def handle_event("guess", %{"number" => guess}, socket) do
    message = "Your guess: #{guess}. Wrong! Guess again."
    score = socket.assigns.score - 1

    {:noreply, assign(socket, message: message, score: score)}
  end
end
```

- Test it in the browser (localhost:4000/guess) and see the score getting lower any time we click on a number.

- Generate the authentication layer (and create LiveView based authentication system by accepting prompted option with Y):
```sh
mix phx.gen.auth Accounts User users
```

- Re-fetch the dependencies and run the migrations:
```bash
mix deps.get
mix ecto.migrate
```