defmodule Discuss.UserSocket do
  use Phoenix.Socket
  # This file can be compared to Route.ex file
  # Adding route for the comments channel
  channel "comments:*", Discuss.CommentsChannel

  transport :websocket, Phoenix.Transports.WebSocket

  def connect(_params, socket) do
    {:ok, socket}
  end

  def id(_socket), do: nil
end
