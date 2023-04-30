import Config

config :libcluster,
  topologies: [
    local_empd_example: [
      strategy: Elixir.Cluster.Strategy.LocalEpmd
    ]
  ]
