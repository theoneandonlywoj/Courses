defmodule Getaways.Repo do
  use Ecto.Repo,
    otp_app: :getaways,
    adapter: Ecto.Adapters.Postgres
end
