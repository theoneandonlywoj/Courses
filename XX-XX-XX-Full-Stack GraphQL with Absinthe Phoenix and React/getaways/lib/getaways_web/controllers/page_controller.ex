defmodule GetawaysWeb.PageController do
  use GetawaysWeb, :controller

  def index(conn, _params) do
    render(conn, "index.html")
  end
end
