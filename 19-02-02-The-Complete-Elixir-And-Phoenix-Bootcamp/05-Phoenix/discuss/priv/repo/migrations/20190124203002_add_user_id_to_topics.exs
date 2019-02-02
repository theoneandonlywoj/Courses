defmodule Discuss.Repo.Migrations.AddUserIdToTopics do
  use Ecto.Migration

  def change do
    alter table(:topics) do
      # Adding a new columns user_id
      # that references to the users table (matching user_id from the users table)
      add :user_id, references(:users)
    end
  end
end
