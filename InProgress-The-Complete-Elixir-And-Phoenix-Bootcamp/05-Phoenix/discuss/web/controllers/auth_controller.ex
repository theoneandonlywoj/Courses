defmodule Discuss.AuthController do
  use Discuss.Web, :controller
  plug Ueberauth

  # Aliasing the User model
  alias Discuss.User

  def callback(conn, params) do
    # Pattern matching to extract ueberauth_auth
    %{assigns: %{ ueberauth_auth: auth }} = conn
    # Extracting user data
    user_params = %{
      token: auth.credentials.token,
      email: auth.info.email,
      provider: "github"
    }
    # Creating a new User changeset
    changeset = User.changeset(%User{}, user_params)
    signin(conn, changeset)
  end

  defp signin(conn, changeset) do
    case insert_or_update_user(changeset) do
      # Successful login (either existing or just inserted)
      {:ok, user} ->
        conn
        |> put_flash(:info, "Welcome back!")
        # Putting encrypted user id into the session
        |> put_session(:user_id, user.id)
        |> redirect(to: topic_path(conn, :index))
      # If the user fails to sign in, I re-direct him / her to the topics index page
      {:error, _reason} ->
        conn
        |> put_flash(:error, "Error signing in")
        |> redirect(to: topic_path(conn, :index))
    end
  end

  # A private helper function
  defp insert_or_update_user(changeset) do
    # Filtering by email
    # It can return a User or nil
    case Repo.get_by(User, email: changeset.changes.email) do
      nil ->
        # It will return {:ok, data} or {:error, error_message}
        Repo.insert(changeset)
      user ->
        # It will return {:ok, data}
        {:ok, user}
    end
  end
end
