package Player;

import GameObject.Card;

import java.util.ArrayList;

public abstract class Player {

    private final String name;
    private final int playerIndex;
    private ArrayList<Card> cards;
    private int currentScore;
    private Identity identity;


    public Player(String name, ArrayList<Card> cards, int playerIndex){
        this.name = name;
        this.cards = cards;
        this.playerIndex = playerIndex;
        identity = Identity.Player;
        updateScore();
    }

    public abstract Card moveCard(Card lastInCard);

    public void updateScore(){
        currentScore = 0;
        if(cards == null)
            return;
        for (Card card : cards){
            currentScore += card.cardScore();
        }
    }
    public void updateCards(ArrayList<Card> cards) {
        this.cards = cards;
        updateScore();
    }

    // Setter
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    // Getters
    public String getName() {
        return name;
    }
    public int getCurrentScore() {
        updateScore();
        return currentScore;
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
    public int getPlayerIndex() {
        return playerIndex;
    }
    public Identity getIdentity() {
        return identity;
    }
}
