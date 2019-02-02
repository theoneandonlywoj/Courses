defmodule Discuss.Repo.Migrations.AddTopics do
  use Ecto.Migration

  def change do
    # Create table with name topics
    # containing columns "title" of type string
    create table(:topics) do
      add :title, :string
    end
  end
end
