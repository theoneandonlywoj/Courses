defmodule Discuss.CommentsChannel do
  use Discuss.Web, :channel
  alias Discuss.Topic

  def join(name, _params, socket) do
    # Name arguments is given as "comments:topic_id"
    # Pattern matching
    "comments: " <> topic_id = name
    # 'topic id' must be an integer
    topic_id = String.to_integer(topic_id)
    # Fetching Topic with given id
    topic = Repo.get(Topic, topic_id)

    IO.inspect(topic)
    {:ok, %{}, socket}
  end

  def handle_in(name, message, socket) do
    # Pattern matching to get the content message
    %{"messageContent" => content} = message
    IO.inspect(content)
    {:reply, :ok, socket}
  end
end
