defmodule PlateSlate.Application do
  # See https://hexdocs.pm/elixir/Application.html
  # for more information on OTP Applications
  @moduledoc false

  use Application

  @impl true
  def start(_type, _args) do
    children = [
      # Start the Ecto repository
      PlateSlate.Repo,
      # Start the Telemetry supervisor
      PlateSlateWeb.Telemetry,
      # Start the PubSub system
      {Phoenix.PubSub, name: PlateSlate.PubSub},
      # Start the Endpoint (http/https)
      PlateSlateWeb.Endpoint
      # Start a worker by calling: PlateSlate.Worker.start_link(arg)
      # {PlateSlate.Worker, arg}
    ]

    # See https://hexdocs.pm/elixir/Supervisor.html
    # for other strategies and supported options
    opts = [strategy: :one_for_one, name: PlateSlate.Supervisor]
    Supervisor.start_link(children, opts)
  end

  # Tell Phoenix to update the endpoint configuration
  # whenever the application is updated.
  @impl true
  def config_change(changed, _new, removed) do
    PlateSlateWeb.Endpoint.config_change(changed, removed)
    :ok
  end
end
