package Algorithm;

import GameObject.Card;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Uno game project
 * this is a class of uno game project
 * @author Pouya mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */

public class GameField {

    /**
     * Number of players
     */
    private int numberOfPlayers;
    /**
     * Cards which are out of game
     */
    private ArrayList<Card> outCards;
    /**
     * The played cards which are played by player in recent rounds
     */
    private ArrayList<Card> inCards;
    /**
     * Cards in hands of players
     */
    private HashMap<Integer, ArrayList<Card>> playersCard;

    /**
     * Constructor of field
     * Contains field information
     * @param numberOfPlayers is the number of human + bots, or let's say all players
     */
    public GameField(int numberOfPlayers){
        outCards = new ArrayList<Card>();
        inCards = new ArrayList<Card>();
        playersCard = new HashMap<Integer, ArrayList<Card>>();
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Refresh in cards
     * witch are show and played in previous moves
     */
    public void refreshOutCard(){
        while (inCards.size() > 4){
            outCards.add(inCards.get(0));
            inCards.remove(0);
        }
    }

    /**
     * Move one card from one place to the other place
     * @param currentPlace of card 0 to 5 is for player, -1 outCard, -2 inCard
     * @param nextPlace of card 0 to 5 is for player, -1 outCard, -2 inCard
     * @param card is the one to be moved
     * @return true if it code move the card
     */
    public boolean moveCard(int currentPlace, int nextPlace, Card card){
        boolean cardIsInCurrentPlace = false;
        boolean isMoved = false;
        if(currentPlace == -2) {
            if(inCards.contains(card)){
                if(moveToNext(nextPlace, card)) {
                    inCards.remove(card);
                    cardIsInCurrentPlace = true;
                    isMoved = true;
                }
                cardIsInCurrentPlace = true;
            }
        }
        else if(currentPlace == -1) {
            if(outCards.contains(card)){
                if(moveToNext(nextPlace, card)) {
                    outCards.remove(card);
                    cardIsInCurrentPlace = true;
                    isMoved = true;
                }
                cardIsInCurrentPlace = true;
            }
        }
        else if(currentPlace >= 0 && currentPlace < numberOfPlayers){
            ArrayList<Card> cardsInHandOfPlayer = playersCard.get(currentPlace);
            if(cardsInHandOfPlayer.contains(card)){
                if(moveToNext(nextPlace, card)) {
                    cardsInHandOfPlayer.remove(card);
                    isMoved = true;
                    playersCard.put(currentPlace, cardsInHandOfPlayer);
                }
                cardIsInCurrentPlace = true;
            }
        }
        refreshOutCard();
        if(!cardIsInCurrentPlace){
            System.out.println("Card is not in current place!");
            return false;
        }
        return isMoved;
    }

    /**
     * place card to next place
     * @param nextPlace of card 0 to 5 is for player, -1 outCard, -2 inCard
     * @param card is going to be moved
     * @return true if it could move it
     */
    private boolean moveToNext(int nextPlace, Card card){
        if(nextPlace == -2) {
            inCards.add(card);
            return true;
        }
        else if(nextPlace == -1) {
            outCards.add(card);
            return true;
        }
        else if(nextPlace >= 0 && nextPlace < numberOfPlayers){
            ArrayList<Card> cardsInHandOfPlayer = playersCard.get(nextPlace);
            cardsInHandOfPlayer.add(card);
            playersCard.put(nextPlace, cardsInHandOfPlayer);
            return true;
        }
        System.out.println("Next place for card does not exists!");
        return false;
    }

    /**
     * Returns the hands of player
     * @param indexOfPlayer will be checked
     * @return hands of player will be returned
     */
    public ArrayList<Card> getHandsOfPlayer(int indexOfPlayer){
        if(indexOfPlayer >= 0 && indexOfPlayer < numberOfPlayers)
            return playersCard.get(indexOfPlayer);
        return null;
    }

    /**
     * Sets a new hand of a player
     * @param indexOfPlayer contains index of player
     * @param newHandOdPlayer new hand will be set
     * @return true if it could set new hand, else false
     */
    public boolean setHandOfPlayer(int indexOfPlayer, ArrayList<Card> newHandOdPlayer){
        if(indexOfPlayer >= 0 && indexOfPlayer < numberOfPlayers){
            playersCard.put(indexOfPlayer, newHandOdPlayer);
            return true;
        }
        System.out.println("Cannot set new hand of player! wrong index of player!");
        return false;
    }

    /**
     * Checks for player with no cards left!
     * @return true if it could find on empty hand
     */
    public boolean checkForEmptyHand(){
        for(int index = 0; index < numberOfPlayers; index++){
            ArrayList<Card> handOfPlayer = playersCard.get(index);
            if(handOfPlayer.size() == 0)
                return true;
        }
        return false;
    }

    // Getters
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    public ArrayList<Card> getOutCards() {
        return outCards;
    }
    public ArrayList<Card> getInCards() {
        return inCards;
    }
    public HashMap<Integer, ArrayList<Card>> getPlayerCards() {
        return playersCard;
    }

    // Setters
    public void setOutCards(ArrayList<Card> outCards) {
        this.outCards = outCards;
    }
    public void setInCards(ArrayList<Card> inCards) {
        this.inCards = inCards;
    }
    public void setPlayerCards(HashMap<Integer, ArrayList<Card>> playerCards) {
        this.playersCard = playerCards;
    }

}
