import Config

config :libcluster,
  topologies: [
    epmd_example: [
      strategy: Elixir.Cluster.Strategy.Epmd,
      config: [
        hosts: [:"node4000@MBP-Wojciech", :"node4001@MBP-Wojciech"]
      ]
    ]
  ]
