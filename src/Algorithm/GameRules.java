package Algorithm;

import GameObject.Card;
import GameObject.CardColor;

public class GameRules {

    /**
     * if +1 it is clockwise
     * if -1 it is anticlockwise
     */
    private int turningMode;
    /**
     * Number of players
     */
    private int numberOfPlayers;
    /**
     * field of game
     */
    private GameField field;
    /**
     * Last played card
     */
    private Card lastPlayed;
    /**
     * Last Color of field
     */
    private CardColor colorOfField;

    public GameRules(GameField field, int turningMode){
        this.turningMode = turningMode;
        this.field = field;
        numberOfPlayers = field.getNumberOfPlayers();
        lastPlayed = null;
        colorOfField = null;
    }

    public boolean ruleLessCard(int indexOfCurrentPlayer, Card playedCard){
        String cardType = playedCard.getCardType().split("#")[0];
        if(lastPlayed == null){
            lastPlayed = playedCard;
            colorOfField = lastPlayed.getCardColor();
            return field.moveCard(indexOfCurrentPlayer, -2,playedCard);
        }
        if(!(colorOfField == playedCard.getCardColor()))
            if(!lastPlayed.getCardType().equals(playedCard.getCardType()))
                return false;
        return field.moveCard(indexOfCurrentPlayer, -2,playedCard);
    }




}
