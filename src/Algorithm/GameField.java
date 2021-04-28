package Algorithm;

import GameObject.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class GameField {

    /**
     * Number of players
     */
    private int numberOfPlayers;
    /**
     * Cards which are out of game
     */
    private ArrayList<Card> OutCards;
    /**
     * The played cards which are played by player in recent rounds
     */
    private ArrayList<Card> InCards;
    /**
     * Cards in hands of players
     */
    private HashMap<Integer, ArrayList<Card>> playersCard;


    public GameField(int numberOfPlayers){
        OutCards = new ArrayList<Card>();
        InCards = new ArrayList<Card>();
        playersCard = new HashMap<Integer, ArrayList<Card>>();
        this.numberOfPlayers = numberOfPlayers;
    }

    // Getters
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    public ArrayList<Card> getOutCards() {
        return OutCards;
    }
    public ArrayList<Card> getInCards() {
        return InCards;
    }
    public HashMap<Integer, ArrayList<Card>> getPlayerCards() {
        return playersCard;
    }

    // Setters
    public void setOutCards(ArrayList<Card> outCards) {
        OutCards = outCards;
    }
    public void setInCards(ArrayList<Card> inCards) {
        InCards = inCards;
    }
    public void setPlayerCards(HashMap<Integer, ArrayList<Card>> playerCards) {
        this.playersCard = playerCards;
    }

}
