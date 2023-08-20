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

- Run the project with IEX:
```bash
iex -S mix phx.server
```

- Create a valid user (password must be longer than 12 characters):
```iex
alias Pento.Accounts
params = %{email: "theoneandonlywoj@gmail.com", password: "P455word1234"}
{:ok, user} = Accounts.register_user(params)
```

- Fetch the current user from the connection and protect the /guess path (lib/pento_web/router.ex):
```elixir
...
  pipeline :browser do
    plug :accepts, ["html"]
    plug :fetch_session
    plug :fetch_live_flash
    plug :put_root_layout, html: {PentoWeb.Layouts, :root}
    plug :protect_from_forgery
    plug :put_secure_browser_headers
    plug :fetch_current_user
  end
...
  scope "/", PentoWeb do
    pipe_through [:browser, :require_authenticated_user]

    live_session :require_authenticated_user,
      on_mount: [{PentoWeb.UserAuth, :ensure_authenticated}] do
      live "/users/settings", UserSettingsLive, :edit
      live "/users/settings/confirm_email/:token", UserSettingsLive, :confirm_email
      live "/guess", WrongLive
    end
  end
...
```

- Test it with localhost:4000/guess and log in.

- The root layout has a lot of useful information (f.e. branding etc.).
- Use the root layout (the root layout would be used either way if not specified, this is just an example) (lib/pento_web/router.ex):
```elixir
...
  scope "/", PentoWeb do
    pipe_through [:browser, :require_authenticated_user]

    live_session :require_authenticated_user,
      root_layout: {PentoWeb.Layouts, :root},
      on_mount: [{PentoWeb.UserAuth, :ensure_authenticated}] do
      live "/users/settings", UserSettingsLive, :edit
      live "/users/settings/confirm_email/:token", UserSettingsLive, :confirm_email
      live "/guess", WrongLive
    end
  end
...
```

- Obtain user from the session(lib/pento_web/live/wrong_live.ex):
```elixir
defmodule PentoWeb.WrongLive do
  use PentoWeb, :live_view
  alias Pento.Accounts

  def mount(_params, session, socket) do
    user = Accounts.get_user_by_session_token(session["user_token"])

    {
      :ok,
      assign(
        socket,
        score: 0,
        message: "Guess a number.",
        session_id: session["live_socket_id"],
        current_user: user
      )
    }
  end
...
end
```

- Render the session id and user's email ():
```elixir
...
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
      <pre>
        <%= @current_user.email %>
        <%= @session_id %>
      </pre>
    </h2>
    """
  end
...
```

- Generate Products LiveView:
```sh
mix phx.gen.live Catalog Product products \
  name:string \
  description:string \
  unit_price:float \
  sku:integer:unique
```

- Migrate:
```sh
mix ecto.migrate
```

- Add the routes (lib/pento_web/router.ex):
```elixir
...
  scope "/", PentoWeb do
    pipe_through [:browser, :require_authenticated_user]

    live_session :require_authenticated_user,
      root_layout: {PentoWeb.Layouts, :root},
      on_mount: [{PentoWeb.UserAuth, :ensure_authenticated}] do
      live "/users/settings", UserSettingsLive, :edit
      live "/users/settings/confirm_email/:token", UserSettingsLive, :confirm_email
      live "/guess", WrongLive

      live "/products", ProductLive.Index, :index
      live "/products/new", ProductLive.Index, :new
      live "/products/:id/edit", ProductLive.Index, :edit

      live "/products/:id", ProductLive.Show, :show
      live "/products/:id/show/edit", ProductLive.Show, :edit
    end
  end
...
```

- Use ConnCase's function to log in the user (test/pento_web/live/product_live_test.exs):
```elixir
...
  describe "Index" do
    setup [:create_product, :register_and_log_in_user]
...
  describe "Show" do
    setup [:create_product, :register_and_log_in_user]
...
```

- Run the tests:
```sh
mix test
```

- Add price constraint to the product changeset (lib/pento/catalog/product.ex):
```elixir
...
  def changeset(product, attrs) do
    ...
    |> validate_number(:unit_price, greater_than: 0.0)
  end
...
```

- Add seed products (priv/repo/seeds.exs):
```elixir
# Script for populating the database. You can run it as:
#
#     mix run priv/repo/seeds.exs
#

alias Pento.Catalog

products = [
  %{
    name: "Chess",
    description: "The classic strategy game",
    sku: 5_678_910,
    unit_price: 10.00
  },
  %{
    name: "Tic-Tac-Toe",
    description: "The game of Xs and Os",
    sku: 11_121_314,
    unit_price: 3.00
  },
  %{
    name: "Table Tennis",
    description: "Bat the ball back and forth. Don't miss!",
    sku: 15_222_324,
    unit_price: 12.00
  }
]

Enum.each(products, fn product ->
  Catalog.create_product(product)
end)
```

- Populate the database with the seed values:
```sh
mix run priv/repo/seeds.exs
```