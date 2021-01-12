# This file is responsible for configuring your application
# and its dependencies with the aid of the Mix.Config module.
#
# This configuration file is loaded before any dependency and
# is restricted to this project.

# General application configuration
use Mix.Config

# Configures the endpoint
config :team_license, TeamLicenseWeb.Endpoint,
  url: [host: "localhost"],
  secret_key_base: "LYq6nCXwtUnOLXvtttiDNRKIMV6zPIKBGkXfwL2Q/MhUxEMU2F4wVPhFCZ3JNO8b",
  render_errors: [view: TeamLicenseWeb.ErrorView, accepts: ~w(html json), layout: false],
  pubsub_server: TeamLicense.PubSub,
  live_view: [signing_salt: "0XNtPzwX"]

# Configures Elixir's Logger
config :logger, :console,
  format: "$time $metadata[$level] $message\n",
  metadata: [:request_id]

# Use Jason for JSON parsing in Phoenix
config :phoenix, :json_library, Jason

# Import environment specific config. This must remain at the bottom
# of this file so it overrides the configuration defined above.
import_config "#{Mix.env()}.exs"
