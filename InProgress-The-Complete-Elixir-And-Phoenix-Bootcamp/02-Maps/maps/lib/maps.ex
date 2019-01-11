defmodule Maps do
  @moduledoc """
  Documentation for Maps.
  """

  @doc """
  Function creates a simple map

  ## Examples

      iex> Maps.create_map
      %{primary: 'red', secondary: 'blue', last: 'yellow'}

  """
  def create_map do
    map = %{primary: 'red', secondary: 'blue', last: 'yellow'}
    map
  end

  @doc """
  Function that reads a property using 'map dot' approach

  ## Examples

      iex> my_map = Maps.create_map
      iex> Maps.read_property_from_map_with_dot(my_map)
      'yellow'
  """
  def read_property_from_map_with_dot(map) do
    map.last
  end

  @doc """
  Function that reads a property using pattern matching

  ## Examples

      iex> my_map = Maps.create_map
      iex> Maps.read_property_from_map_with_pattern_matching(my_map)
      'yellow'
  """
  def read_property_from_map_with_pattern_matching(map) do
    %{last: last_data} = map
    last_data
  end

  @doc """
  Returning a new map with a key changed / inserted if does not exist already.

  ## Examples

      iex> my_map = Maps.create_map
      iex> Maps.change_value_for_a_key(my_map, :primary, 'new_value')
      %{last: 'yellow', primary: 'new_value', secondary: 'blue'}

  """
  def change_value_for_a_key(map, key, value) do
    map = Map.put(map, key, value)
    map
  end

end
