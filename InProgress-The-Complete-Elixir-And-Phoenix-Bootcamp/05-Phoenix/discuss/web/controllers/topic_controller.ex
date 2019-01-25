defmodule Discuss.TopicController do
  # Go and grab all behaviours from web.ex
  # It is similar to class inheritance in OOP
  use Discuss.Web, :controller

  # Discuss.Topic => Topic
  alias Discuss.Topic

  # Not using the layout
  #plug :put_layout, false

  # Adding the plug only for given actions
  # Only for :new, :create, :edit, :update, :delete
  plug Discuss.Plugs.RequireAuth when action in [:new, :create, :edit, :update, :delete]

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
    # Creating changeset with user id association
    # First, we need to obtain the user_id from the session
    changeset = conn.assigns.user
    # Building an association with the topic
    # The atom :topics comes from field in the DB in the user.ex model
    |> build_assoc(:topics)
    # Building the changeset from the struct
    |> Topic.changeset(topic)
    # Old version: changeset = Topic.changeset(%Topic{}, topic)
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
    IO.inspect(conn.assigns)
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

  def delete(conn, %{"id" => topic_id}) do
    # get! function re-directs to an error page if it fails
    Repo.get!(Topic, topic_id)
    |> Repo.delete!()

    # There is no need for case statement,
    # because the code below will be accessed only if the deletion is successful
    conn
    |> put_flash(:info, "Topic Deleted!")
    |> redirect(to: topic_path(conn, :index))
  end
end
