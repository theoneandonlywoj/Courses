defmodule Discuss.Topic do
  use Discuss.Web, :model

  # Implementing model schema
  schema "topics" do
    field :title, :string
    # A topic belongs to a user
    belongs_to :user, Discuss.User
  end
  # Changeset validation
  # \\ means the default value
  # if params is nil => params = %{}
  def changeset(struct, params \\ %{}) do
    struct
    |> cast(params, [:title])
    |> validate_required([:title])
  end

end
