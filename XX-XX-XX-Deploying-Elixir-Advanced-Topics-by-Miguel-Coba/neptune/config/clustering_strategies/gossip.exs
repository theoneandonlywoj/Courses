import Config

magic_cookie =
  System.get_env("LIBCLUSTER_COOKIE") ||
    raise """
    environment variable LIBCLUSTER_COOKIE is missing.
    You can generate one by calling: mix phx.gen.secret
    """

config :libcluster,
  topologies: [
    gossip_example: [
      strategy: Elixir.Cluster.Strategy.Gossip,
      config: [
        secret: magic_cookie
      ]
    ]
  ]
