import Config

config :libcluster,
  topologies: [
    k8s_example: [
      strategy: Elixir.Cluster.Strategy.Kubernetes.DNS,
      config: [
        service: "neptune-headless",
        application_name: "neptune"
      ]
    ]
  ]
