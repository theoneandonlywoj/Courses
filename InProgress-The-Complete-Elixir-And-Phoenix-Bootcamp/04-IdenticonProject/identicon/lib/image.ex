defmodule Identicon.Image do
  @moduledoc """
  Module containing structs.
  - Adding a property with name 'hex'
  - Properties are enforcing that only defined names can be used.
    Properties that are not defined will return a 'KeyError'.

  """

  @doc """
  Identicon.Image property.
  - hex property with default value of nil (null)

  ## Examples

      iex> %Identicon.Image{}
      %Identicon.Image{color: nil, hex: nil}

      iex> %Identicon.Image{hex: []}
      %Identicon.Image{color: nil, hex: []}
  """
  defstruct hex: nil, color: nil, grid: nil
end
