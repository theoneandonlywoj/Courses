# Deploying Elixir - Advanved Topics by Miguel Coba
https://blog.miguelcoba.com/deploying-elixir-advanced-topics-ebook

## Notes

#### Create a sample application
```sh
mix phx.new neptune --install
cd neptune

mix ecto.create
mix phx.server
```

#### Production environment
Create prod database
```sh
psql -U postgres
```

```sql
CREATE DATABASE neptune_prod;
```

Show all databases
```sql
SELECT datname FROM pg_database;
```

Exit the CLI
```sql
\q
```

#### Run the application using Elixir Release
Release Docker configuration
```sh
mix phx.gen.release --docker --ecto
```
Create a release
```sh
MIX_ENV=prod mix release
```

Environment variables
```sh
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4000
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
```

Start the server using the release
```sh
_build/prod/rel/neptune/bin/neptune start
```

#### Run the applications in Docker
Build
```sh
docker build -t neptune .
```

Run
```sh
docker run --rm -d -p $PORT:$PORT -e PORT -e SECRET_KEY_BASE -e DATABASE_URL --name neptune neptune
```

Stop
```sh
docker stop neptune
```

#### Clustering in Elixir
First Shell Session
```sh
iex --sname node1
```

Second Shell Session
```sh
iex --sname node2
```

Connect the first node to the second
```elixir
iex(node1@MBP-Wojciech)1> Node.connect(:"node2@MBP-Wojciech")
true
```

Connect the first node to the second
```elixir
iex(node1@MBP-Wojciech)1> Node.connect(:"node2@MBP-Wojciech")
```

List the connected nodes (from node1):
```elixir
iex(node1@MBP-Wojciech)1> Node.list()
```

List the connected nodes (from node2):
```elixir
iex(node2@MBP-Wojciech)1> Node.list()
```

#### Showing node information
lib/neptune_web/controllers/page_controller.ex
```elixir
defmodule NeptuneWeb.PageController do
  use NeptuneWeb, :controller

  def home(conn, _params) do
    this_node = node()
    other_nodes = Node.list()
    render(conn, :home, %{this_node: this_node, other_nodes: other_nodes})
  end
end

```

lib/neptune_web/controllers/page_html/home.html.heex
```elixir
<.flash_group flash={@flash} />
<div class="px-4 py-10 sm:px-6 sm:py-28 lg:px-8 xl:px-28 xl:py-32">
  <div class="mx-auto max-w-xl lg:mx-0">
    <svg viewBox="0 0 71 48" class="h-12" aria-hidden="true">
      <path
        d="m26.371 33.477-.552-.1c-3.92-.729-6.397-3.1-7.57-6.829-.733-2.324.597-4.035 3.035-4.148 1.995-.092 3.362 1.055 4.57 2.39 1.557 1.72 2.984 3.558 4.514 5.305 2.202 2.515 4.797 4.134 8.347 3.634 3.183-.448 5.958-1.725 8.371-3.828.363-.316.761-.592 1.144-.886l-.241-.284c-2.027.63-4.093.841-6.205.735-3.195-.16-6.24-.828-8.964-2.582-2.486-1.601-4.319-3.746-5.19-6.611-.704-2.315.736-3.934 3.135-3.6.948.133 1.746.56 2.463 1.165.583.493 1.143 1.015 1.738 1.493 2.8 2.25 6.712 2.375 10.265-.068-5.842-.026-9.817-3.24-13.308-7.313-1.366-1.594-2.7-3.216-4.095-4.785-2.698-3.036-5.692-5.71-9.79-6.623C12.8-.623 7.745.14 2.893 2.361 1.926 2.804.997 3.319 0 4.149c.494 0 .763.006 1.032 0 2.446-.064 4.28 1.023 5.602 3.024.962 1.457 1.415 3.104 1.761 4.798.513 2.515.247 5.078.544 7.605.761 6.494 4.08 11.026 10.26 13.346 2.267.852 4.591 1.135 7.172.555ZM10.751 3.852c-.976.246-1.756-.148-2.56-.962 1.377-.343 2.592-.476 3.897-.528-.107.848-.607 1.306-1.336 1.49Zm32.002 37.924c-.085-.626-.62-.901-1.04-1.228-1.857-1.446-4.03-1.958-6.333-2-1.375-.026-2.735-.128-4.031-.61-.595-.22-1.26-.505-1.244-1.272.015-.78.693-1 1.31-1.184.505-.15 1.026-.247 1.6-.382-1.46-.936-2.886-1.065-4.787-.3-2.993 1.202-5.943 1.06-8.926-.017-1.684-.608-3.179-1.563-4.735-2.408l-.043.03a2.96 2.96 0 0 0 .04-.029c-.038-.117-.107-.12-.197-.054l.122.107c1.29 2.115 3.034 3.817 5.004 5.271 3.793 2.8 7.936 4.471 12.784 3.73A66.714 66.714 0 0 1 37 40.877c1.98-.16 3.866.398 5.753.899Zm-9.14-30.345c-.105-.076-.206-.266-.42-.069 1.745 2.36 3.985 4.098 6.683 5.193 4.354 1.767 8.773 2.07 13.293.51 3.51-1.21 6.033-.028 7.343 3.38.19-3.955-2.137-6.837-5.843-7.401-2.084-.318-4.01.373-5.962.94-5.434 1.575-10.485.798-15.094-2.553Zm27.085 15.425c.708.059 1.416.123 2.124.185-1.6-1.405-3.55-1.517-5.523-1.404-3.003.17-5.167 1.903-7.14 3.972-1.739 1.824-3.31 3.87-5.903 4.604.043.078.054.117.066.117.35.005.699.021 1.047.005 3.768-.17 7.317-.965 10.14-3.7.89-.86 1.685-1.817 2.544-2.71.716-.746 1.584-1.159 2.645-1.07Zm-8.753-4.67c-2.812.246-5.254 1.409-7.548 2.943-1.766 1.18-3.654 1.738-5.776 1.37-.374-.066-.75-.114-1.124-.17l-.013.156c.135.07.265.151.405.207.354.14.702.308 1.07.395 4.083.971 7.992.474 11.516-1.803 2.221-1.435 4.521-1.707 7.013-1.336.252.038.503.083.756.107.234.022.479.255.795.003-2.179-1.574-4.526-2.096-7.094-1.872Zm-10.049-9.544c1.475.051 2.943-.142 4.486-1.059-.452.04-.643.04-.827.076-2.126.424-4.033-.04-5.733-1.383-.623-.493-1.257-.974-1.889-1.457-2.503-1.914-5.374-2.555-8.514-2.5.05.154.054.26.108.315 3.417 3.455 7.371 5.836 12.369 6.008Zm24.727 17.731c-2.114-2.097-4.952-2.367-7.578-.537 1.738.078 3.043.632 4.101 1.728.374.388.763.768 1.182 1.106 1.6 1.29 4.311 1.352 5.896.155-1.861-.726-1.861-.726-3.601-2.452Zm-21.058 16.06c-1.858-3.46-4.981-4.24-8.59-4.008a9.667 9.667 0 0 1 2.977 1.39c.84.586 1.547 1.311 2.243 2.055 1.38 1.473 3.534 2.376 4.962 2.07-.656-.412-1.238-.848-1.592-1.507Zm17.29-19.32c0-.023.001-.045.003-.068l-.006.006.006-.006-.036-.004.021.018.012.053Zm-20 14.744a7.61 7.61 0 0 0-.072-.041.127.127 0 0 0 .015.043c.005.008.038 0 .058-.002Zm-.072-.041-.008-.034-.008.01.008-.01-.022-.006.005.026.024.014Z"
        fill="#FD4F00"
      />
    </svg>
    <h1 class="text-brand mt-10 flex items-center text-sm font-semibold leading-6">
      Phoenix Framework
      <small class="bg-brand/5 text-[0.8125rem] ml-3 rounded-full px-2 font-medium leading-6">
        v<%= Application.spec(:phoenix, :vsn) %>
      </small>
    </h1>
    <div class="flex">
      <div class="w-full">
        <div class="mt-10 grid grid-cols-2 gap-y-4 text-sm leading-6 text-zinc-700 sm:grid-cols-2">
          <div>
            This node
          </div>
          <div>
            Other nodes
          </div>
          <div>
            <ul>
              <li>
                <%= @this_node %>
              </li>
            </ul>
          </div>
          <div>
            <ul>
              <%= for node <- @other_nodes do %>
        <li>
          <%= node %>
        </li>
      <% end %>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
```

#### Clustering with Libcluster
Add libcluster dependency (mix.exs):
```elixir
defp deps do
    [
      ...
      {:libcluster, "~> 3.3"}
      ...
    ]
  end
```

Get updated dependencies:
```bash
mix deps.get
```

Modyfy the application file (lib/neptune/application.ex)
```elixir
defmodule Neptune.Application do
  # See https://hexdocs.pm/elixir/Application.html
  # for more information on OTP Applications
  @moduledoc false

  use Application

  @impl true
  def start(_type, _args) do
    topologies = Application.get_env(:libcluster, :topologies) || []

    children = [
      ...
      {Cluster.Supervisor, [topologies, [name: Neptune.ClusterSupervisor]]}
      ...
    ]

    # See https://hexdocs.pm/elixir/Supervisor.html
    # for other strategies and supported options
    opts = [strategy: :one_for_one, name: Neptune.Supervisor]
    Supervisor.start_link(children, opts)
  end

  ...
end
```

#### Epmd Strategy
Epmd config (config/clusterting_strategies/epmd.exs):
```elixir
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
```

Import it in the main config file (config/config.exs)
```elixir
  ...
  # Clustering Strategy
  strategy = System.get_env("CLUSTERING_STRATEGY") || "epmd"
  import_config "clustering_strategies/#{strategy}.exs"
```

Start the first node:
```bash
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4000
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
export MIX_ENV=prod
export CLUSTERING_STRATEGY=epmd
iex --sname node4000 -S mix phx.server
```

Start the second node:
```bash
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4001
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
export MIX_ENV=prod
export CLUSTERING_STRATEGY=epmd
iex --sname node4001 -S mix phx.server
```

See results by accessing http://localhost:4000/ and http://localhost:4001/.

#### Local Empd Strategy
Config (config/clustering_strategies/local_epmd.exs)
```elixir
import Config

config :libcluster,
  topologies: [
    local_empd_example: [
      strategy: Elixir.Cluster.Strategy.LocalEpmd
    ]
  ]
```

Start the first node
```bash
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4000
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
export MIX_ENV=prod
export CLUSTERING_STRATEGY=local_epmd
iex --sname node4000 -S mix phx.server
```

Start the second node
```bash
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4001
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
export MIX_ENV=prod
export CLUSTERING_STRATEGY=local_epmd
iex --sname node4001 -S mix phx.server
```

Start the third node
```bash
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4002
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
export MIX_ENV=prod
export CLUSTERING_STRATEGY=local_epmd
iex --sname node4002 -S mix phx.server
```

See results by accessing http://localhost:4000/, http://localhost:4001/ and http://localhost:4002/.

#### Erlang Hosts Strategy
Config (config/clustering_strategies/erlang_hosts.exs)
```elixir
import Config

config :libcluster,
  topologies: [
    erlang_hosts_example: [
      strategy: Elixir.Cluster.Strategy.ErlangHosts
    ]
  ]
```

Create the Erlang Hosts file:
```sh
touch .hosts.erlang
```

Populate with your hostname (.hosts.erlang):
```erlang
'MBP-Wojciech'.
```

Start the first node
```bash
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4000
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
export MIX_ENV=prod
export CLUSTERING_STRATEGY=erlang_hosts
iex --sname node4000 -S mix phx.server
```

Start the second node
```bash
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4001
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
export MIX_ENV=prod
export CLUSTERING_STRATEGY=erlang_hosts
iex --sname node4001 -S mix phx.server
```

Start the third node
```bash
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4002
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
export MIX_ENV=prod
export CLUSTERING_STRATEGY=erlang_hosts
iex --sname node4002 -S mix phx.server
```

#### Gossip Strategy
Config (config/clustering_strategies/gossip.exs):
```elixir
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
```

Generate two secrets:
```bash
mix phx.gen.secret
```

Example cookie 1:
A3Zc+vC7bYULDkBKCOgv0xgB704guo/XoSxxVHeghPDD3MrE+ONrV0gaD6GN5RYu

Example cookie 2:
lzweV6bru1Z1hUKUpFv5Ry/+Chw9fGST0vv6aAQAIsiT1qhh025TmzpRqauLurlw


Start the first node
```bash
export LIBCLUSTER_COOKIE=A3Zc+vC7bYULDkBKCOgv0xgB704guo/XoSxxVHeghPDD3MrE+ONrV0gaD6GN5RYu
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4000
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
export MIX_ENV=prod
export CLUSTERING_STRATEGY=gossip
iex --sname node4000 -S mix phx.server
```

Start the second node
```bash
export LIBCLUSTER_COOKIE=A3Zc+vC7bYULDkBKCOgv0xgB704guo/XoSxxVHeghPDD3MrE+ONrV0gaD6GN5RYu
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4001
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
export MIX_ENV=prod
export CLUSTERING_STRATEGY=gossip
iex --sname node4001 -S mix phx.server
```

Start the third node
```bash
export LIBCLUSTER_COOKIE=lzweV6bru1Z1hUKUpFv5Ry/+Chw9fGST0vv6aAQAIsiT1qhh025TmzpRqauLurlw
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4002
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
export MIX_ENV=prod
export CLUSTERING_STRATEGY=gossip
iex --sname node4002 -S mix phx.server
```

Start the fourth node
```bash
export LIBCLUSTER_COOKIE=lzweV6bru1Z1hUKUpFv5Ry/+Chw9fGST0vv6aAQAIsiT1qhh025TmzpRqauLurlw
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4003
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
export MIX_ENV=prod
export CLUSTERING_STRATEGY=gossip
iex --sname node4003 -S mix phx.server
```

See results by accessing http://localhost:4000/, http://localhost:4001/, http://localhost:4002/ and http://localhost:4003/.

#### PostgreSQL inside Docker
Check if there is an already running instance:
```sh
brew services info postgresql
```

If it is indeed running, stop it:
```sh
brew services stop postgresql
```

Create a Docker volume to persist the data
```sh
docker volume create pgdata
```

Download and start PostgreSQL Docker Image (with data peristance with the pgdata volume):
```sh
docker run -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -v pgdata:/var/lib/postgresql/data postgres
```

In another Terminal, connect to the server:
```sh
psql -U postgres -h localhost
```

Create database:
```sql
CREATE DATABASE test_db;
```

Close the Docker image:
```sh
docker stop $(docker ps -q --filter ancestor=postgres )
```

#### Kubernates
Install Minikube (local Kubernates):
```sh
brew install minikube
```

Test the setup:
```sh
which minikube
```
```sh
minikube version
```

Create a cluster named neptune-mk with 2 nodes:
```sh
minikube start -p neptune-mk -n 2
```

Activate the neptune-mk profile so that minikube also uses this profile as default when we execute minikube commands:
```sh
minikube profile neptune-mk
```

Confirm there are 2 nodes:
```sh
kubectl get nodes
```
and 0 pods:
```sh
kubectl get pods
```

Create a folder for the k8 configs:
```sh
mkdir k8s
```

Create the first configuration:
```sh
touch k8s/postgresql-database.yml
```

Content of the config (k8s/postgresql-database.yml):
```yaml
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: postgres-database
spec:
  selector:
    matchLabels:
      app: postgres
  serviceName: postgres-service
  replicas: 1
  template:
    metadata:
      labels:
        app: postgres
    spec:
      containers:
        - name: postgres
          image: postgres
          env:
            - name: POSTGRES_PASSWORD
              value: mysecretpassword
            - name: POSTGRES_DB
              value: neptune_prod
            - name: PGDATA
              value: /var/lib/postgresql/data/pgdata
          volumeMounts:
            - name: postgres-volume
              mountPath: /var/lib/postgresql/data
  volumeClaimTemplates:
    - metadata:
        name: postgres-volume
      spec:
        accessModes: ["ReadWriteOnce"]
        resources:
          requests:
            storage: 1Gi
```

Apply the configuration to the cluster:
```sh
kubectl apply -f k8s/postgres-database.yml
```

Check the pods:
```sh
kubectl get pods
```

Create a Service to access the Pod (Kubernetes doesn't allow a direct access to pods):
```sh
touch k8s/postgres-service.yml
```

Content (k8s/postgres-service.yml)
```yaml
apiVersion: v1
kind: Service
metadata:
  name: postgres-service
spec:
  selector:
    app: postgres
  type: LoadBalancer
  ports:
    - port: 5432
      targetPort: 5432
```

Service with type LoadBalancer allows the service to be accessible outside the cluster utilising publicly accessible IP.

Start the service:
```sh
kubectl apply -f k8s/postgres-service.yml
```

Create a tunnel to access the service (keep this terminal alive while connecting to the cluster):
```sh
minikube tunnel
```

List the services (in another terminal):
```sh
kubectl get services
```

Connect to the database using the EXTERNAL-IP:
```sh
psql -U postgres -h <EXTERNAL-IP>
```

Exit the PostgreSQL terminal and list the pods:
```sh
kubectl get pods
```

Kill the pod to test the self-healing capabilities:
```sh
kubectl delete pod postgres-database-0
```

List the pods until the status is Running:
```sh
kubectl get pods
```

Connect again and confirm the neptune_prod database survived a Pod restart.