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
    |> build_grid
    |> filter_odd_squares
    |> build_pixel_map
    |> draw_image
  end

  @doc """
  Hashing a string to an unique sequence of characters using MD5.
  It takes a string and return a list of number (the same each time we run it).

  ## Examples

      iex> Identicon.hash_input('my_string')
      %Identicon.Image{ hex: [61, 33, 43, 33, 250, 215, 190, 214, 60, 31, 181, 96, 198, 165, 197, 208],
                        color: nil,
                        grid: nil }
      iex> Identicon.hash_input('my_string')
      %Identicon.Image{ hex: [61, 33, 43, 33, 250, 215, 190, 214, 60, 31, 181, 96, 198, 165, 197, 208],
                        color: nil,
                        grid: nil }

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
                        hex: [202, 80, 91, 154, 26, 69, 237, 0, 38, 137, 34, 139, 223, 37, 34, 93],
                        grid: nil }

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

  @doc """
  Building the grid.
  We are using chunk size = 3, because we are mirroring the number to get 5 values.
  Using pattern matching directly from the parameter.

  ## Examples

      iex> image = Identicon.hash_input('hash_me')
      %Identicon.Image{
      color: nil,
      hex: [202, 80, 91, 154, 26, 69, 237, 0, 38, 137, 34, 139, 223, 37, 34, 93],
      grid: nil
      }
      iex> Identicon.build_grid(image)
      %Identicon.Image{
        color: nil,
        grid: [
          {202, 0},
          {80, 1},
          {91, 2},
          {80, 3},
          {202, 4},
          {154, 5},
          {26, 6},
          {69, 7},
          {26, 8},
          {154, 9},
          {237, 10},
          {0, 11},
          {38, 12},
          {0, 13},
          {237, 14},
          {137, 15},
          {34, 16},
          {139, 17},
          {34, 18},
          {137, 19},
          {223, 20},
          {37, 21},
          {34, 22},
          {37, 23},
          {223, 24}
        ],
        hex: [202, 80, 91, 154, 26, 69, 237, 0, 38, 137, 34, 139, 223, 37, 34, 93]
      }

  """
  def build_grid(%Identicon.Image{hex: hex_list} = image) do
    # Enum.chunk creates list of lists
    grid =
    hex_list
      # Chunking the list by 3 with step 3 and discarding the rest
      |> Enum.chunk_every(3, 3, :discard)
      # Passing reference to a function mirror_row that takes 1 argument.
      |> Enum.map(&mirror_row/1)
      # Flattening the list for operations simplicity
      |> List.flatten()
      # Adding indexes
      |> Enum.with_index

    # Updating the grid property with the "pipe" syntax
    %Identicon.Image{image | grid: grid}
  end

  @doc """
  Mirroring a 3 elements row [a, b, c].
  Returning a 5 elements row [a, b, c, d, e]

  ## Examples

      iex> Identicon.mirror_row([1, 2, 3])
      [1, 2, 3, 2, 1]

  """
  def mirror_row(row) do
    # [ 1, 2, 3] => [1, 2, 3, 2, 1]
    [first, second, _tail] = row
    ## Joining lists with '++'
    row ++ [second, first]
  end

  @doc """
  Filtering out squares with odd color code number.

  ## Examples

      iex> image = Identicon.hash_input('hash_me') |> Identicon.build_grid
      iex> Identicon.filter_odd_squares(image)
      %Identicon.Image{
        color: nil,
        grid: [
          {202, 0},
          {80, 1},
          {80, 3},
          {202, 4},
          {154, 5},
          {26, 6},
          {26, 8},
          {154, 9},
          {0, 11},
          {38, 12},
          {0, 13},
          {34, 16},
          {34, 18},
          {34, 22}
        ],
        hex: [202, 80, 91, 154, 26, 69, 237, 0, 38, 137, 34, 139, 223, 37, 34, 93]
      }
  """
  def filter_odd_squares(%Identicon.Image{grid: grid} = image) do
    grid = Enum.filter(grid, fn(cell) ->
      {color_code, _} = cell
      # Calculating the remainder to see if the number is odd or even
      rem(color_code, 2) == 0
    end )
    %Identicon.Image{image| grid: grid}
  end

  @doc """
  Building a pixel map.
  Each cell must be defined as {{x_top_left, y_top_left}, {x_bottom_right, y_bottom_right}}
  ## Examples

      iex> fake_img = %Identicon.Image{grid: [{202, 0}, {80, 1}, {90, 3}, {98, 5}]}
      iex> Identicon.build_pixel_map(fake_img)
      %Identicon.Image{
        color: nil,
        grid: [{202, 0}, {80, 1}, {90, 3}, {98, 5}],
        hex: nil,
        pixel_map: [
          {{0, 0}, {50, 50}},
          {{50, 0}, {100, 50}},
          {{150, 0}, {200, 50}},
          {{0, 50}, {50, 100}}
        ]
      }
  """
  def build_pixel_map(%Identicon.Image{grid: grid} = image) do
    pixel_map = Enum.map(grid, fn({_, index}) ->
      horizontal = rem(index, 5) * 50
      vertical = div(index, 5) * 50

      top_left_corner = {horizontal, vertical}
      bottom_right_corner = {horizontal + 50, vertical + 50}

      {top_left_corner, bottom_right_corner}
    end)

    %Identicon.Image{image | pixel_map: pixel_map}
  end

  @doc """
  Drawing the image with EGD.
  """
  def draw_image(%Identicon.Image{color: color, pixel_map: pixel_map}) do
    image = :egd.create(250, 250)
    fill = :egd.color(color)

    # EGD weirdness - we are editing an existing image
    # instead of returning a new one each time we draw something.
    Enum.each(pixel_map, fn({start, stop}) ->
      :egd.filledRectangle(image, start, stop, fill)
    end)

    :egd.render(image)
  end
end
