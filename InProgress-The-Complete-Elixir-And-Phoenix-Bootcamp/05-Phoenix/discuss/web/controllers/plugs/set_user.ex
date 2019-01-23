defmodule Discuss.Plugs.SetUser do
  import Plug.Conn
  import Phoenix.Controller

  alias Discuss.Repo
  alias Discuss.User

  # Adding empty init, because we do not need any initial setup
  # when the app starts
  def init(_params) do
  end

  def call(conn, _params_from_init) do
    # Getting user id from the session
    # Function 'get_session' is defined in Phoenix.Controller
    user_id = get_session(conn, :user_id)
    # Condition statement
    # First condition that evaluates to true will be executed
    cond do
      # 1. We have a user_id
      user = user_id && Repo.get(User, user_id) ->
        # We put data to the 'assigns' part of the connection to user_property
        # Function 'assign' from Plug.Conn will return the connection
        assign(conn, :user_property, user)
      # 2. We do not have a user_id
      # We place true so it will be executed in the end
      true ->
        assign(conn, :user_property, nil)
    end
  end
end
