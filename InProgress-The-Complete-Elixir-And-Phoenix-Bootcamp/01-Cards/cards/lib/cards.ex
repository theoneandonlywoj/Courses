defmodule Cards do
  @moduledoc """
  Documentation for Cards.
  """

  @doc """
  Hello world.

  ## Examples

      iex> Cards.hello()
      :world

  """
  def hello do
    "hi there!"
  end

  def create_deck do
    values = ["Ace", "Two", "Three", "Four", "Five"]
    suits = ["Spades", "Clubs", "Hearts", "Diamonds"]
    ## The block below will iterate through the list and return a new list of list
    for value <- values, suit <- suits do
      "#{value} of #{suit}"
    end
  end

  def shuffle(deck) do
    Enum.shuffle(deck)
  end

  def contains?(deck, card) do
    Enum.member?(deck, card)
  end
  @doc """
  Splitting the deck into hand and the rest.

  ## Examples

      iex>deck = ['Ace of Spades', 'Two of Diamonds', 'Three of Clubs']
      iex>{hand, the_rest} = Cards.deal(deck, 2)
      iex>hand
      ['Ace of Spades', 'Two of Diamonds']
      iex>the_rest
      ['Three of Clubs']

  """
  def deal(deck, hand_size) do
    Enum.split(deck, hand_size)
  end

  def save(deck, filename) do
    # Calling erlang object:
    binary = :erlang.term_to_binary(deck)
    File.write(filename, binary)
  end

  def load(filename) do
    case File.read(filename) do
      # :name is an atom
      {:ok, binary} -> :erlang.binary_to_term(binary)
      # _ means 'I do not care about this variable.'
      {:error, _} -> "That file does not exist!"
    end
  end

  def create_hand(hand_size) do
    # The pipe operator passes the result
    # as the first argument to the next function
    Cards.create_deck
    |> Cards.shuffle
    |> Cards.deal(hand_size)
  end
end
