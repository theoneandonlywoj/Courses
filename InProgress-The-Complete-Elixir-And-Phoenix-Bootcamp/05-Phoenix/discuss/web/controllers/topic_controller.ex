defmodule Discuss.TopicController do
  # Go and grab all behaviours from web.ex
  # It is similar to class inheritance in OOP
  use Discuss.Web, :controller

  # Discuss.Topic => Topic
  alias Discuss.Topic

  # Not using the layout
  #plug :put_layout, false

  def new(conn, _params) do
    struct = %Topic{}
    params = %{}
    # An empty changeset
    changeset = Topic.changeset(struct, params)
    # Rendering template new.html that must be in folder "topic"
    render conn, "new.html", changeset_variable: changeset
  end

  # Accessing the parameters with pattern matching
  def create(conn, %{"topic" => topic}) do
    # Creating changeset
    changeset = Topic.changeset(%Topic{}, topic)
    # They changeset does not need to be check valid?
    # Repo module takes care of it and it will not try to insert it,
    # if the changeset is invalid
    case Repo.insert(changeset) do
      {:ok, _topic} ->
        conn
        # Show an info
        |> put_flash(:info, "Topic Created")
        # Redirect to function index
        |> redirect(to: topic_path(conn, :index))
      {:error, changeset} ->
        conn
        |> put_flash(:error, "An error occured!")
        |> render("new.html", changeset_variable: changeset)
    end
  end

  def index(conn, _params) do
    topics = Repo.all(Topic)
    render conn, "index.html", topics_list: topics
  end

  def edit(conn, %{"id" => topic_id}) do
    # Selecting with an id
    topic = Repo.get(Topic, topic_id)
    # No changes for now, thus we do not pass the second parameter.
    topic_changeset = Topic.changeset(topic)

    render conn, "edit.html", changeset_variable: topic_changeset, topic_variable: topic
  end

  def update(conn, %{"id" => topic_id, "topic" => topic_from_form}) do
    old_topic = Repo.get(Topic, topic_id)
    topic_changeset = Topic.changeset(old_topic, topic_from_form)

    # Updating
     case Repo.update(topic_changeset) do
      {:ok, _} ->
        conn
        # Show an info
        |> put_flash(:info, "Topic Updated")
        # Redirect to function index
        |> redirect(to: topic_path(conn, :index))
      {:error, topic_changeset} ->
        # Redirect back to the edit page
        render conn, "edit.html", changeset_variable: topic_changeset, topic_variable: old_topic
    end
  end
end
