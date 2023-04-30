import Config

config :libcluster,
  topologies: [
    erlang_hosts_example: [
      strategy: Elixir.Cluster.Strategy.ErlangHosts
    ]
  ]
