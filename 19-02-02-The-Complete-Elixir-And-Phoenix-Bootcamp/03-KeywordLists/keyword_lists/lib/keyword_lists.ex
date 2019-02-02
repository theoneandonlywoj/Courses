defmodule KeywordLists do
  @moduledoc """
  Documentation for KeywordLists.

  """
  @doc """
  Creating a keyword list created with tuples syntax.

  ## Examples

      iex> KeywordLists.create_keyword_list_with_tuples
      [primary: "red", secondary: "blue", last: "yellow"]
  """
  def create_keyword_list_with_tuples do
    keyword_list = [{:primary, "red"}, {:secondary, "blue"}, {:last, "yellow"}]
    keyword_list
  end

  @doc """
  Creating a keyword list created with simple syntax.

  ## Examples

      iex> KeywordLists.create_keyword_list_simple_syntax
      [primary: "red", secondary: "blue", last: "yellow"]
  """
  def create_keyword_list_simple_syntax do
    keyword_list = [primary: "red", secondary: "blue", last: "yellow"]
    keyword_list
  end

  @doc """
  Obtain a value for given keyword list and a key.

  ## Examples

      iex> my_kwl = KeywordLists.create_keyword_list_simple_syntax
      iex> KeywordLists.obtain_data_from_key(my_kwl, :last)
      "yellow"
  """
  def obtain_data_from_key(keyword_list, key) do
    keyword_list[key]
  end

end
