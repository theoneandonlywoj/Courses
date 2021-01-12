defmodule TeamLicense.Application do
  # See https://hexdocs.pm/elixir/Application.html
  # for more information on OTP Applications
  @moduledoc false

  use Application

  def start(_type, _args) do
    children = [
      # Start the Telemetry supervisor
      TeamLicenseWeb.Telemetry,
      # Start the PubSub system
      {Phoenix.PubSub, name: TeamLicense.PubSub},
      # Start the Endpoint (http/https)
      TeamLicenseWeb.Endpoint
      # Start a worker by calling: TeamLicense.Worker.start_link(arg)
      # {TeamLicense.Worker, arg}
    ]

    # See https://hexdocs.pm/elixir/Supervisor.html
    # for other strategies and supported options
    opts = [strategy: :one_for_one, name: TeamLicense.Supervisor]
    Supervisor.start_link(children, opts)
  end

  # Tell Phoenix to update the endpoint configuration
  # whenever the application is updated.
  def config_change(changed, _new, removed) do
    TeamLicenseWeb.Endpoint.config_change(changed, removed)
    :ok
  end
end
