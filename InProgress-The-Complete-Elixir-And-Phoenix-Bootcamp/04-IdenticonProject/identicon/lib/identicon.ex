defmodule Identicon do
  @moduledoc """
  Documentation for Identicon.

  First three number from the sequence of character will be RGB.

  If the number in the cell of the grid is odd, we will show it as white.
  If the number is even, we will fill it.
  """
  def main(input) do
    input
    |> hash_input
    |> pick_color
  end

  @doc """
  Hashing a string to an unique sequence of characters using MD5.
  It takes a string and return a list of number (the same each time we run it).

  ## Examples

      iex> Identicon.hash_input('my_string')
      %Identicon.Image{ hex: [61, 33, 43, 33, 250, 215, 190, 214, 60, 31, 181, 96, 198, 165, 197, 208],
                        color: nil }
      iex> Identicon.hash_input('my_string')
      %Identicon.Image{ hex: [61, 33, 43, 33, 250, 215, 190, 214, 60, 31, 181, 96, 198, 165, 197, 208],
                        color: nil }

  """
  def hash_input(input) do
    hex = :crypto.hash(:md5, input)
    |> :binary.bin_to_list

    %Identicon.Image{hex: hex}
  end

  @doc """
  Picking the color as the first 3 numbers from the 'image' struct.
  The numbers correspond to (R)ed, (G)reen and (B)lue channels.

  ## Examples

      iex> image = Identicon.hash_input('hash_me')
      iex> Identicon.pick_color(image)
      %Identicon.Image{ color: {202, 80, 91},
                        hex: [202, 80, 91, 154, 26, 69, 237, 0, 38, 137, 34, 139, 223, 37, 34, 93] }
  """
  def pick_color(image) do
    # Accessing first three values using pattern matching
    %Identicon.Image{hex: hex_list} = image
    # Pattern matching and tossing away the tail
    [r, g, b | _tail] = hex_list
    # Updating the value for a given key using the 'pipe' syntax
    #% Identicon.Image{image | color: {r, g, b}}
    # Update the value for a given key using Map.put
    Map.put(image, :color, {r, g, b})
  end

end
