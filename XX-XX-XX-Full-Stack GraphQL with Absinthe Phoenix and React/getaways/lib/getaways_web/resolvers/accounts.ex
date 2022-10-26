defmodule GetawaysWeb.Resolvers.Accounts do
  alias Getaways.Accounts
  alias GetawaysWeb.Schema.ChangesetErrors

  def signup(_, args, _) do
    case Accounts.create_user(args) do
      {:error, changeset} ->
        {
          :error,
          message: "Could not create account", details: ChangesetErrors.error_details(changeset)
        }

      {:ok, user} ->
        token = GetawaysWeb.AuthToken.sign(user)
        {:ok, %{user: user, token: token}}
    end
  end

  def signin(_, %{username: username, password: password}, _) do
    case Accounts.authenticate(username, password) do
      :error ->
        {:error, "Whoops, invalid credentials!"}

      {:ok, user} ->
        token = GetawaysWeb.AuthToken.sign(user)
        {:ok, %{user: user, token: token}}
    end
  end
end