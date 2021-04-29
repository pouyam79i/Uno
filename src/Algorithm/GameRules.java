package Algorithm;

import GameObject.Card;
import GameObject.CardColor;
import GameObject.CardType;

import java.util.ArrayList;
import java.util.Random;

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
        if(turningMode > 0)
            this.turningMode = 1;
        else
            this.turningMode = -1;
        this.field = field;
        numberOfPlayers = field.getNumberOfPlayers();
        lastPlayed = null;
        colorOfField = null;
    }

    public boolean moveCard(int indexOfCurrentPlayer, Card playedCard){
        if(lastPlayed == null){
            lastPlayed = playedCard;
            colorOfField = lastPlayed.getCardColor();
            return field.moveCard(indexOfCurrentPlayer, -2,playedCard);
        }
        if(!(colorOfField == playedCard.getCardColor()))
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
        Random rand = new Random();
        int randomIndex = rand.nextInt(field.getOutCards().size());
        Card finingCard = field.getOutCards().get(randomIndex);
        if(field.moveCard(-1, indexOfPlayer, finingCard))
            return true;
        System.out.println("Faied while fining a player");
        return false;
    }

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

    public boolean ruleCardN7(int indexOfFinedPlayer, int numberOfFiningCards){
        boolean failure = false;
        for(int i = 0; i < numberOfFiningCards; i++){
            if(!finePlayer(indexOfFinedPlayer))
                failure = true;
        }
        return (!failure);
    }

    public void ruleCardN10(){
        turningMode *= -1;
    }

    public int ruleCardA(int indexOfCurrentPlayer){
        return (turnPlayer(turnPlayer(indexOfCurrentPlayer)));
    }

    public void ruleCardB(CardColor color){
        colorOfField = color;
    }

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
        return lastPlayed.getCardType();
    }

}
