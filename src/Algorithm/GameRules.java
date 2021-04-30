package Algorithm;

import GameObject.Card;
import GameObject.CardColor;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Uno game project
 * this is a class of uno game project
 * @author Pouya mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */

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

    /**
     * Constructor of GameRules
     * set contains all card rules and movement
     * @param field is a new field to be set as game field
     * @param turningMode is the turning mode, 1 is clockwise, -1 is counter-clockwise
     */
    public GameRules(GameField field, int turningMode){
        if(turningMode > 0)
            this.turningMode = 1;
        else
            this.turningMode = -1;
        this.field = field;
        numberOfPlayers = field.getNumberOfPlayers();
        lastPlayed = null;
        colorOfField = null;
    }

    /**
     * moves a card to the 'in cards' list.
     * @param indexOfCurrentPlayer is index of current player
     * @param playedCard is the played card by current player
     * @return true is it could move card to the 'in card' list
     */
    public boolean moveCard(int indexOfCurrentPlayer, Card playedCard){
        if(playedCard == null)
            return false;
        if(lastPlayed == null){
            lastPlayed = playedCard;
            colorOfField = lastPlayed.getCardColor();
            return field.moveCard(indexOfCurrentPlayer, -2,playedCard);
        }
        if(!(colorOfField == playedCard.getCardColor()))
            if(!playedCard.getCardType().equals("12#12"))
                if(!lastPlayed.getCardType().equals(playedCard.getCardType()))
                    return false;
        lastPlayed = playedCard;
        colorOfField = lastPlayed.getCardColor();
        return field.moveCard(indexOfCurrentPlayer, -2,playedCard);
    }

    /**
     * This is used to fine a player!
     * It can be use as N8 fining rule!
     * @param indexOfPlayer index if fined player
     * @return  true if the card moved!
     */
    public boolean finePlayer(int indexOfPlayer){
        getField();
        Random rand = new Random();
        int randomIndex = rand.nextInt(field.getOutCards().size());
        if(randomIndex >= field.getOutCards().size() || randomIndex < 0){
            return false;
        }
        Card finingCard = field.getOutCards().get(randomIndex);
        if(field.moveCard(-1, indexOfPlayer, finingCard))
            return true;
        System.out.println("Faied while fining a player");
        return false;
    }

    /**
     * gives a card from hand of current player
     * to the hand of selected player
     * according to rule card N2
     * @param indexOfCurrentPlayer of current player
     * @param selectedPlayer index of selected player
     * @param selectedIndexOfCard index of selected card
     * @return true if it code move it
     */
    public boolean ruleCardN2(int indexOfCurrentPlayer,
                              int selectedPlayer,
                              int selectedIndexOfCard){
        ArrayList<Card> handOfPlayer = field.getHandsOfPlayer(indexOfCurrentPlayer);
        if(selectedIndexOfCard >= handOfPlayer.size() || selectedIndexOfCard < 0){
            Random rand = new Random();
            selectedIndexOfCard = rand.nextInt(handOfPlayer.size());
        }
        return field.moveCard(indexOfCurrentPlayer, selectedPlayer, handOfPlayer.get(selectedIndexOfCard));
    }

    /**
     * it applies the rule of N7
     * and fins a player!
     * @param indexOfFinedPlayer index of fined player
     * @param numberOfFiningCards number of fining card
     * @return true if could fine the player
     */
    public boolean ruleCardN7(int indexOfFinedPlayer, int numberOfFiningCards){
        boolean failure = false;
        for(int i = 0; i < numberOfFiningCards; i++){
            if(!finePlayer(indexOfFinedPlayer))
                failure = true;
        }
        return (!failure);
    }

    /**
     * change the current direction moving
     * to the opposite of it
     * according to rule card N10
     */
    public void ruleCardN10(){
        turningMode *= (-1);
    }

    /**
     * Applies rule card A and skip
     * @param indexOfCurrentPlayer index of current player
     * @return index of next player
     */
    public int ruleCardA(int indexOfCurrentPlayer){
        return (turnPlayer(turnPlayer(indexOfCurrentPlayer)));
    }

    /**
     * applies rule of card B
     * @param color is the selected color for the field!
     */
    public void ruleCardB(CardColor color){
        colorOfField = color;
    }

    /**
     * give the turn to the next player!
     * @param indexOfCurrentPlayer index of current player
     * @return index of next player
     */
    public int turnPlayer(int indexOfCurrentPlayer){
        indexOfCurrentPlayer += turningMode;
        while (indexOfCurrentPlayer < 0){
            indexOfCurrentPlayer += numberOfPlayers;
        }
        return (indexOfCurrentPlayer % numberOfPlayers);
    }


    // Getter
    public GameField getField(){
        field.refreshOutCard();
        return field;
    }
    public int getTurningMode() {
        return turningMode;
    }
    public CardColor getColorOfField() {
        return colorOfField;
    }
    public String getLastType(){
        if(lastPlayed == null)
            return null;
        return lastPlayed.getCardType();
    }
    public Card getLastPlayed() {
        return lastPlayed;
    }

}
