defmodule CardsTest do
  use ExUnit.Case
  doctest Cards

  test "greets the world" do
    assert Cards.hello() == "hi there!"
  end

  test "Create_deck makes 20 Cards" do
    deck_length = length(Cards.create_deck)
    assert deck_length === 20
  end

end
