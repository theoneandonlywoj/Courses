defmodule Discuss.CommentsChannel do
  use Discuss.Web, :channel
  alias Discuss.{Topic, Comment}

  def join(name, _params, socket) do
    # Name arguments is given as "comments:topic_id"
    # Pattern matching
    "comments: " <> topic_id = name
    # 'topic id' must be an integer
    topic_id = String.to_integer(topic_id)
    # Fetching Topic with given id
    # Adding preloading comments for the topics
    topic = Topic
      |> Repo.get(topic_id)
      |> Repo.preload(:comments)

    IO.inspect(topic.comments)
    # Assigning value to the socket (similar like with the conn object)
    # {:ok, %{}, socket}
    {:ok, %{comments: topic.comments}, assign(socket, :topic, topic)}
  end

  def handle_in(name, message, socket) do
    # Pattern matching to get the content message
    %{"messageContent" => content} = message
    # Obtaining the topic from the socket object
    topic = socket.assigns.topic
    # Building the association
    changeset = topic
    # with the comments table
    |> build_assoc(:comments)
    # Content property comes from the Comment model
    |> Comment.changeset(%{content: content})

    case Repo.insert(changeset) do
      {:ok, comment} ->
        # Notifying everyone who subscribes to the channel
        # broadcast!(socket, event_name, data_to_be_sent)
        broadcast!(socket, "comments:#{socket.assigns.topic.id}:new",
            %{comment: comment}
          )
        # Reply will come from the broadcast method.
        # {:reply, :ok, socket}
      {:error, _reason} ->
        {:reply, { :error, %{ errors: changeset }}, socket}
    end
  end
end
