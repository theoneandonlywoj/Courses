defmodule Discuss.Topic do
  use Discuss.Web, :model

  # Implementing model schema
  schema "topics" do
    field :title, :string
    # A topic belongs to a user
    belongs_to :user, Discuss.User
    # A topic can have multiple comments
    has_many :comments, Discuss.Comment
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
