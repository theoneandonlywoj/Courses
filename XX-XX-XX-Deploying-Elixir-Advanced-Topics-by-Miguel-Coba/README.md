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
<section class="phx-hero">
  <h1>Deploying Elixir: Advanced Topics</h1>
  <p>This is a node in a cluster of Erlang Nodes</p>
</section>
<div class="grid grid-cols-3 gap-4">
  <div>
    <h2>This node</h2>
    <ul>
      <li>
        <%= @this_node %>
      </li>
    </ul>
  </div>
  <div>
    <h2>Other nodes</h2>
    <ul>
      <%= for node <- @other_nodes do %>
        <li>
          <%= node %>
        </li>
      <% end %>
    </ul>
  </div>
</div>
```