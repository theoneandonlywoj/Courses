defmodule NeptuneWeb.PageController do
  use NeptuneWeb, :controller

  def home(conn, _params) do
    this_node = node()
    other_nodes = Node.list()
    render(conn, :home, %{this_node: this_node, other_nodes: other_nodes})
  end
end
