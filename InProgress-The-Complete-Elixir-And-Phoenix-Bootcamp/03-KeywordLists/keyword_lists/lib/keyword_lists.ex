defmodule KeywordLists do
  @moduledoc """
  Documentation for KeywordLists.
  """
  @doc """
  Creating a keyword list.

  ## Examples

      iex> KeywordLists.create_keyword_list
      [primary: "red", secondary: "blue", last: "yellow"]
  """
  def create_keyword_list do
    keyword_list = [{:primary, "red"}, {:secondary, "blue"}, {:last, "yellow"}]
    keyword_list
  end

  @doc """
  Obtain a value for given keyword list and a key.

  ## Examples

      iex> my_kwl = KeywordLists.create_keyword_list
      iex> KeywordLists.obtain_data_from_key(my_kwl, :last)
      "yellow"
  """
  def obtain_data_from_key(keyword_list, key) do
    keyword_list[key]
  end
end
