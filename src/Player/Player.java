package Player;

import GameObject.Card;
import GameObject.CardColor;

import java.util.ArrayList;

public abstract class Player {

    /**
     * Player name
     */
    private final String name;
    /**
     * Player index
     */
    private final int playerIndex;
    /**
     * cards of player
     */
    private ArrayList<Card> cards;
    /**
     * current score of player
     */
    private int currentScore;
    /**
     * identity of player
     */
    private Identity identity;

    /**
     *
     * @param name of player
     * @param cards of player
     * @param playerIndex index of player!
     */
    public Player(String name, ArrayList<Card> cards, int playerIndex){
        this.name = name;
        this.cards = cards;
        this.playerIndex = playerIndex;
        identity = Identity.Player;
        updateScore();
    }

    /**
     * Handles one move of a player
     * @param typeOfField type of field
     * @param colorOfField color of field
     * @param isFined it is true the player is fined in this move
     * @return the selected card of player!
     */
    public abstract Card moveCard(String typeOfField, CardColor colorOfField, boolean isFined);

    /**
     * Update score of player
     */
    public void updateScore(){
        currentScore = 0;
        if(cards == null)
            return;
        for (Card card : cards){
            currentScore += card.cardScore();
        }
    }

    /**
     * Update cards of player
     * @param cards which is the new list which has to be set
     */
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
