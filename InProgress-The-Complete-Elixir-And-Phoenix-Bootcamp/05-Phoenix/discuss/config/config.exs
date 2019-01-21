# This file is responsible for configuring your application
# and its dependencies with the aid of the Mix.Config module.
#
# This configuration file is loaded before any dependency and
# is restricted to this project.
use Mix.Config

# General application configuration
config :discuss,
  ecto_repos: [Discuss.Repo]

# Configures the endpoint
config :discuss, Discuss.Endpoint,
  url: [host: "localhost"],
  secret_key_base: "A8CccofufxORAJ6gCIzzMGAPmR2T3r+ogdN80Ckkb4EghpFwvg8+WDlU8yW1gGg4",
  render_errors: [view: Discuss.ErrorView, accepts: ~w(html json)],
  pubsub: [name: Discuss.PubSub,
           adapter: Phoenix.PubSub.PG2]

# Configures Elixir's Logger
config :logger, :console,
  format: "$time $metadata[$level] $message\n",
  metadata: [:request_id]

# Import environment specific config. This must remain at the bottom
# of this file so it overrides the configuration defined above.
import_config "#{Mix.env}.exs"

# UeberAuth Github Setup
config :ueberauth, Ueberauth,
  providers:[
    github: { Ueberauth.Strategy.Github, []}
  ]

  config :ueberauth, Ueberauth.Strategy.Github.Oauth,
    client_id: "cbc4356f47d5e5ba15c3",
    client_secret: "15ab91c4e5e7aee00d892913323a58a269f2621d"
