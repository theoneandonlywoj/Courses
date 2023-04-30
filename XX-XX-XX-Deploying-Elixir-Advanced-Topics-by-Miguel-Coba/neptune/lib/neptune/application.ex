defmodule Neptune.Application do
  # See https://hexdocs.pm/elixir/Application.html
  # for more information on OTP Applications
  @moduledoc false

  use Application

  @impl true
  def start(_type, _args) do
    topologies = Application.get_env(:libcluster, :topologies) || []

    children = [
      # Start the Telemetry supervisor
      NeptuneWeb.Telemetry,
      # Start the Ecto repository
      Neptune.Repo,
      # Start the PubSub system
      {Phoenix.PubSub, name: Neptune.PubSub},
      # Start Finch
      {Finch, name: Neptune.Finch},
      # Start the Endpoint (http/https)
      NeptuneWeb.Endpoint,
      # Start a worker by calling: Neptune.Worker.start_link(arg)
      # {Neptune.Worker, arg}
      # Clustering
      {Cluster.Supervisor, [topologies, [name: Neptune.ClusterSupervisor]]}
    ]

    # See https://hexdocs.pm/elixir/Supervisor.html
    # for other strategies and supported options
    opts = [strategy: :one_for_one, name: Neptune.Supervisor]
    Supervisor.start_link(children, opts)
  end

  # Tell Phoenix to update the endpoint configuration
  # whenever the application is updated.
  @impl true
  def config_change(changed, _new, removed) do
    NeptuneWeb.Endpoint.config_change(changed, removed)
    :ok
  end
end
