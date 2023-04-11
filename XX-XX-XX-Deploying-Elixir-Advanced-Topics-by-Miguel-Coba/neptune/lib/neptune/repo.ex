defmodule Neptune.Repo do
  use Ecto.Repo,
    otp_app: :neptune,
    adapter: Ecto.Adapters.Postgres
end
