defmodule Discuss.Plugs.RequireAuth do
  import Plug.Conn
  import Phoenix.Controller

  alias Discuss.Router.Helpers
  def init(_params) do
  end

  def call(conn, _params_from_init) do
    # If the session has a user
    if conn.assigns[:user] do
      # Continue on by passing the connection
      conn
    else
      conn
      |> put_flash(:error, "You must be logged in.")
      # Helper.topic_path comes from Discuss.Router.Helpers
      |> redirect(to: Helpers.topic_path(conn, :index))
      # Halt to make sure the connection is not passed further in the pipeline
      # Thus the last action in the plug will be passed to the user
      # The method comes from Plug.conn
      |> halt()
    end

  end
end
