defmodule Discuss.User do
  use Discuss.Web, :model

  @derive {Poison.Encoder, only: [:email, :provider, :token]}

  schema "users" do
    field :email, :string
    field :provider, :string
    field :token, :string
    # The user has many topics
    has_many :topics, Discuss.Topic
    # A user can have multiple comments
    has_many :comments, Discuss.Comment

    timestamps()
  end

  def changeset(struct, params \\ %{}) do
    struct
    |> cast(params, [:email, :provider, :token])
    |> validate_required([:email, :provider, :token])
  end
end
