package Algorithm;

import GameObject.Card;
import GameObject.CardColor;
import GameObject.CardType;

import java.util.ArrayList;
import java.util.Random;

public class GameStream {

    private GameField field;
    private GameRules rules;
    private int numberOfPlayers;
    private int indexOfCurrentPlayer;
    private int numberOfFiningCards;

    public GameStream(int numberOfPlayers, int turningMode){
        this.numberOfPlayers = numberOfPlayers;
        GameLauncher gameLauncher = new GameLauncher();
        field = gameLauncher.launchField(numberOfPlayers, 7);
        rules = new GameRules(field, numberOfPlayers);
        Random rand = new Random();
        indexOfCurrentPlayer = rand.nextInt(numberOfPlayers);
        numberOfFiningCards = 0;
    }

    public Card pickOneCard(int indexOfCard, ArrayList<Card> handOfPlayer){
        if(handOfPlayer.size() == 0){
            System.out.println("There is nothing to pick");
            return null;
        }
        if(indexOfCard < 0 || indexOfCard >= handOfPlayer.size()) {
            System.out.println("Wrong index of card. please pick one from 0 to " + (handOfPlayer.size()-1));
            return null;
        }
        return handOfPlayer.get(indexOfCard);
    }

    // Edit Starts

    /**
     * Choose One player to be fined!
     * @param indexOfCurrentPlayer
     * @return
     */
    public int chooseOnePlayer(int indexOfCurrentPlayer){
        ArrayList<Integer> listOfAvailablePlayers = new ArrayList<Integer>();
        for (int index = 0; index < numberOfPlayers; index++){
            if(index == indexOfCurrentPlayer)
                continue;
            listOfAvailablePlayers.add(index);
        }
        //                                                      ############# Complete this shit
        return 0;
    }

    /**
     * This one pick a player to be fined
     * @param numberOfCardsLeft
     * @return the chosencard
     */
    public int finePlayer(int numberOfCardsLeft){
        int indexOfChosenCard = 0;                              // ######## Complete this
        return indexOfChosenCard;
    }

    /**
     * Choose One Color
     * @return the color
     */
    public CardColor pickColor() {
        return CardColor.Yellow;                // ##################### Complete this shit
    }

    // Edit Finish

    public Operation doOneTurn(int indexOfCurrentPlayer, Card playedCard){
        CardType cardType = new CardType();
        if(!rules.moveCard(indexOfCurrentPlayer, playedCard))
            return Operation.failed;
        field = getField();
        if(field.getHandsOfPlayer(indexOfCurrentPlayer).size() == 0 && (!playedCard.getCardType().equals(cardType.N8)))
            return Operation.gameFinished;
        if(playedCard.getCardType().equals(cardType.N2)){
            int indexOfFinedPlayer = chooseOnePlayer(indexOfCurrentPlayer);
            int indexOfChosenCard = finePlayer(field.getHandsOfPlayer(indexOfCurrentPlayer).size());
            if(!rules.ruleCardN2(indexOfCurrentPlayer, indexOfFinedPlayer, indexOfChosenCard))
                return Operation.failed;
            if(rules.getField().checkForEmptyHand())
                return Operation.gameFinished;
        }
        else if(playedCard.getCardType().equals(cardType.N7)){
            boolean hasN7 = false;
            for(Card card : rules.getField().getHandsOfPlayer(rules.turnPlayer(indexOfCurrentPlayer))){
                if(card.getCardType().equals(cardType.N7)){
                    hasN7 = true;
                    break;
                }
            }
            if(playedCard.cardScore() == 15)
                numberOfFiningCards += 4;
            else
                numberOfFiningCards += 2;
            if(!hasN7){
                if(!rules.ruleCardN7(rules.turnPlayer(indexOfCurrentPlayer), numberOfFiningCards)){
                    System.out.println("Failed to fine next player!");
                    return Operation.failed;
                }
                numberOfFiningCards = 0;
            }
        }
        else if(playedCard.getCardType().equals(cardType.N8)){
            if(rules.getField().getHandsOfPlayer(indexOfCurrentPlayer).size() == 0){
                rules.finePlayer(indexOfCurrentPlayer);
                field = getField();
                return Operation.ruleCardN8;
            }
            // Do not want to change index of player because of rule card N8!
            return Operation.done;
        }
        else if(playedCard.getCardType().equals(cardType.N10)){
           rules.ruleCardN10();
        }
        else if(playedCard.getCardType().equals(cardType.A)){
            this.indexOfCurrentPlayer = rules.ruleCardA(indexOfCurrentPlayer);
            return Operation.done;
        }
        else if(playedCard.getCardType().equals(cardType.B)) {
            rules.ruleCardB(pickColor());
        }
        field = getField();
        this.indexOfCurrentPlayer = rules.turnPlayer(indexOfCurrentPlayer);
        return Operation.done;
    }

    // Getters
    public GameField getField() {
        field = rules.getField();
        field.refreshOutCard();
        return field;
    }
    public CardColor getColorOfField(){
        return rules.getColorOfField();
    }
    public String getLastType(){
        return rules.getLastType();
    }
    public int getIndexOfCurrentPlayer() {
        return indexOfCurrentPlayer;
    }

}
