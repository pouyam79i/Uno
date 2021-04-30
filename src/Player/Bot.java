package Player;

import GameObject.Card;

import java.util.ArrayList;

public class Bot extends Player{

    public Bot(String name, ArrayList<Card> cards, int playerIndex){
        super(name, cards, playerIndex);
    }

    @Override
    public Card moveCard(Card lastInCard){
        return null;
    }

    private Card guess(Card lastInCard){
        return null;
    }

}
