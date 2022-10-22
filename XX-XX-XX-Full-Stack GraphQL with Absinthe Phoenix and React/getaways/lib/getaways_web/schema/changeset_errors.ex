defmodule GetawaysWeb.Schema.ChangesetErrors do
  @doc """
  Traverses the changeset errors and returns a map of
  error messages. For example:

  %{start_date: ["can't be blank"], end_date: ["can't be blank"]}
  """
  def error_details(changeset) do
    Ecto.Changeset.traverse_errors(changeset, fn {msg, opts} ->
      Enum.reduce(opts, msg, fn {key, value}, acc ->
        String.replace(acc, "%{#{key}}", to_string(value))
      end)
    end)
  end
end
