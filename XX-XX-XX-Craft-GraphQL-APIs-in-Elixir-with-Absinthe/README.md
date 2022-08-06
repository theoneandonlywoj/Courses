# Craft GraphQL APIs in Elixir with Absinthe
Craft GraphQL APIs in Elixir with Absinthe
Flexible, Robust Services for Queries, Mutations, and Subscriptions
by Bruce Williams and Ben Wilson

## Project Setup
##### Check Elixir version
```sh
elixir --version
```
##### Create a new project
```sh
mix phx.new plateslate --module PlateSlate --no-assets --no-html
```

##### Setup a database
```sh
mix ecto.setup
```

##### Adding dependencies to the mix.exs file
```elixir
{:absinthe, "~> 1.6.0"},
{:absinthe_plug, "~> 1.5"},
{:absinthe_phoenix, "~> 2.0.0"},
{:absinthe_relay, "~> 1.5.0"}
```