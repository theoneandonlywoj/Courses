defmodule Discuss.UserSocket do
  use Phoenix.Socket
  # This file can be compared to Route.ex file
  # Adding route for the comments channel
  channel "comments:*", Discuss.CommentsChannel

  transport :websocket, Phoenix.Transports.WebSocket

  # Obtaining token that comes from socket.js
  # It is passed as a parameter
  def connect(%{"token" => token}, socket) do
    IO.puts(token)
    case Phoenix.Token.verify(socket, "key", token) do
      # User_is comes from the token
      # That information was encrypted
      {:ok, user_id} ->
        {:ok, assign(socket, :user_id, user_id)}
      {:error, _error} ->
        :error
    end
  end

  def id(_socket), do: nil
end
