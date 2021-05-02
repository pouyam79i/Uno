package Algorithm;

import GameObject.Card;
import GameObject.CardColor;
import GameObject.CardType;
import Graphic.Console;
import Graphic.ConsoleColors;
import Player.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Uno game project
 * this is a class of uno game project
 * @author Pouya mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */

public class GameStream implements ConsoleColors {

    /**
     * Field of game
     */
    private GameField field;
    /**
     * rules class
     */
    private GameRules rules;
    /**
     * list of players
     * contains all players information
     */
    private ArrayList<Player> players;
    /**
     * number of players
     */
    private int numberOfPlayers;
    /**
     * index of current player
     */
    private int indexOfCurrentPlayer;
    /**
     * contain number of fining cards
     * used to handle N7 rule
     */
    private int numberOfFiningCards;
    /**
     * stream operation contains the status of steam
     * if gameFinished is the status it means the steam is off
     * and game is finished
     */
    private Operation streamOperation;
    /**
     * This class is used to print cards
     */
    private Console console;

    /**
     * Constructor of GameStream
     * Initialize what ever needed to run a new game
     * @param numberOfPlayers number of players
     * @param turningMode initial turning mode, 1 is clock wise, -1 is counter-clockwise
     * @param numberOfHuman number of human player, the remaining players are bot
     */
    public GameStream(int numberOfPlayers, int turningMode, int numberOfHuman){
        this.numberOfPlayers = numberOfPlayers;
        streamOperation = Operation.done;
        players = new ArrayList<Player>();
        console = new Console();
        GameLauncher gameLauncher = new GameLauncher();
        field = gameLauncher.launchField(numberOfPlayers, 7);
        rules = new GameRules(field, numberOfPlayers);
        Scanner scanner = new Scanner(System.in);
        int botCounter = 1;
        for(int i = 0; i < numberOfPlayers; i++){
            String name = "";
            Player newPlayer;
            if(numberOfHuman > 0){
                System.out.println("Please enter the name of human");
                name = scanner.nextLine();
                newPlayer = new Human(name, field.getHandsOfPlayer(i), i);
                numberOfHuman--;
            }
            else {
                name = "Bot " + botCounter;
                newPlayer = new Bot(name, field.getHandsOfPlayer(i), i);
                botCounter++;
            }
            players.add(newPlayer);
        }
        Random rand = new Random();
        indexOfCurrentPlayer = rand.nextInt(numberOfPlayers);
        numberOfFiningCards = 0;
        console.printLauncherInformation(players, indexOfCurrentPlayer ,turningMode);
    }

    /**
     * runs the game main stream
     * @return true if game is running, if game finishes return false
     */
    public boolean stream(){
        boolean playerIsFined = false;

        if(players.get(indexOfCurrentPlayer).getIdentity() != Identity.Bot)
            console.printField(rules.getField(), indexOfCurrentPlayer);

        if(rules.getField().getInCards().size() != 0){
            if(!checkIfHandIsAvailable(indexOfCurrentPlayer)){
                rules.finePlayer(indexOfCurrentPlayer);
                playerIsFined = true;
            }
        }

        players.get(indexOfCurrentPlayer).updateCards(rules.getField().getHandsOfPlayer(indexOfCurrentPlayer));
        Card playedCard = players.get(indexOfCurrentPlayer).moveCard(rules.getLastType(), rules.getColorOfField(), playerIsFined);
        if(playedCard != null){
            streamOperation =  doOneTurn(indexOfCurrentPlayer, playedCard);
            while (streamOperation == Operation.ruleCardN8){
                if(players.get(indexOfCurrentPlayer).getIdentity() != Identity.Bot)
                    console.printField(rules.getField(), indexOfCurrentPlayer);
                players.get(indexOfCurrentPlayer).updateCards(rules.getField().getHandsOfPlayer(indexOfCurrentPlayer));
                playedCard = players.get(indexOfCurrentPlayer).moveCard(rules.getLastType(), rules.getColorOfField(), true);
                streamOperation = doOneTurn(indexOfCurrentPlayer, playedCard);
            }
        }
        else
            indexOfCurrentPlayer = rules.turnPlayer(indexOfCurrentPlayer);

        players.get(indexOfCurrentPlayer).updateCards(rules.getField().getHandsOfPlayer(indexOfCurrentPlayer));
        // Checks if we have a winner
        if(rules.getField().checkForEmptyHand())
            return false;
        return streamOperation != Operation.gameFinished;   // if returns false it means the game is finished
    }

    // Edit Starts

    /**
     * Choose One player to be fined!
     * Use it for bots
     * @param indexOfCurrentPlayer index of current player
     * @return index of chosen player to be given a card
     */
    public int chooseOnePlayer(int indexOfCurrentPlayer){
        ArrayList<Integer> listOfAvailablePlayers = new ArrayList<Integer>();
        for (int index = 0; index < numberOfPlayers; index++){
            if(index == indexOfCurrentPlayer)
                continue;
            listOfAvailablePlayers.add(index);
        }
        if(players.get(indexOfCurrentPlayer).getIdentity() == Identity.Bot){
            Random rand = new Random();
            return rand.nextInt(listOfAvailablePlayers.size());
        }
        else {
            System.out.println(GREEN_BOLD + "You can giva one of your cards to one player!");
            System.out.println(GREEN_BOLD + "Enter the player index:");
            for(int index : listOfAvailablePlayers){
                System.out.print(RED_BOLD + index + ", ");
            }
            Scanner scanner = new Scanner(System.in);
            int chosenPlayer = scanner.nextInt();
            while (!listOfAvailablePlayers.contains(chosenPlayer)){
                System.out.println( RED_BOLD+ "Wrong input");
                chosenPlayer = scanner.nextInt();
            }
            return chosenPlayer;
        }
    }

    /**
     * This one pick a player to be fined
     * @param numberOfCardsLeft number of cards left
     * @return the chosen card
     */
    public int finePlayer(int numberOfCardsLeft, int indexOfFinedPlayer){
        if(players.get(indexOfFinedPlayer).getIdentity() == Identity.Bot){
            Random random = new Random();
            return random.nextInt(numberOfCardsLeft);
        }
        else {
            System.out.println(YELLOW_BOLD + "Player " + indexOfFinedPlayer + " - " + players.get(indexOfFinedPlayer).getName()
            +". "+ RED_BOLD + "You are fined!");
            System.out.println("Please enter a number between 0 and " + numberOfCardsLeft);
            Scanner scanner = new Scanner(System.in);
            int selectedCard = scanner.nextInt();
            while(selectedCard >= numberOfCardsLeft || selectedCard < 0){
                System.out.println(RED_BOLD + "Wrong number!");
                selectedCard = scanner.nextInt();
            }
            return selectedCard;
        }
    }

    /**
     * Choose One Color
     * @return the color
     */
    public CardColor pickColor(int indexOfCurrentPlayer) {
        int selected = 0;
        if(players.get(indexOfCurrentPlayer).getIdentity() == Identity.Bot){
            Random random = new Random();
            selected = random.nextInt(4);
        }
        else {
            System.out.println(YELLOW_BOLD + "Please pick a color for the field! indexes are from 0 to 3");
            System.out.println(RED_BOLD + "Red, " + BLUE_BOLD + "Blue, " + GREEN_BOLD + "Green, " + BLACK_BOLD + "Black" + RESET);
            Scanner scanner = new Scanner(System.in);
            selected = scanner.nextInt();
            while(selected > 3 || selected < 0){
                System.out.println(RED_BOLD + "Wrong input");
                selected = scanner.nextInt();
            }
        }
        if(selected == 0)
            return CardColor.Red;
        else if(selected == 1)
            return CardColor.Blue;
        else if(selected == 2)
            return CardColor.Green;
        else
            return CardColor.Black;
    }

    // Edit Finish

    public Operation doOneTurn(int indexOfCurrentPlayer, Card playedCard){

        // Handling errors
        if(playedCard == null)
            return Operation.failed;
        if(indexOfCurrentPlayer < 0 || indexOfCurrentPlayer >= numberOfPlayers)
            return Operation.failed;

        // cardType has all defined type of cards!
        CardType cardType = new CardType();

        // checks if the card is moved!
        if(!rules.moveCard(indexOfCurrentPlayer, playedCard))
            return Operation.failed;

        // updates the field after changes!
        field = getField();

        // checks if hand of player is empty
        if(field.getHandsOfPlayer(indexOfCurrentPlayer).size() == 0){
            if((!playedCard.getCardType().equals(cardType.N8))){
                return Operation.gameFinished;
            }else {
                //fines player because his hand is empty and he played card N8
                rules.finePlayer(indexOfCurrentPlayer);
                this.indexOfCurrentPlayer = indexOfCurrentPlayer;
                return Operation.ruleCardN8;
            }
        }

        // Checks if the player dont scape from rule card N7
        if(playedCard.getCardType().equals(cardType.N7) && numberOfFiningCards > 0){
            if(!playedCard.getCardType().equals(cardType.N7)){
                if (rules.ruleCardN7(indexOfCurrentPlayer, numberOfFiningCards)){
                    numberOfFiningCards = 0;
                }
                else {
                    System.out.println(RED_BOLD + "Failed to fine!");
                }
                indexOfCurrentPlayer = rules.turnPlayer(indexOfCurrentPlayer);
                field = getField();
                return Operation.done;
            }
        }

        // checks for a player to be fined
        if(playedCard.getCardType().equals(cardType.N2)){
            int indexOfFinedPlayer = chooseOnePlayer(indexOfCurrentPlayer);
            int indexOfChosenCard = finePlayer(rules.getField().getHandsOfPlayer(indexOfCurrentPlayer).size(), indexOfFinedPlayer);
            if(!rules.ruleCardN2(indexOfCurrentPlayer, indexOfFinedPlayer, indexOfChosenCard))
                return Operation.failed;
            if(rules.getField().checkForEmptyHand())
                return Operation.gameFinished;
        }

        // Rule card N7
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
                    System.out.println(RED_BOLD + "Failed to fine next player!");
                    return Operation.failed;
                }
                System.out.println(RED_BOLD + "You are fined by rule N7");
                this.indexOfCurrentPlayer = rules.turnPlayer(indexOfCurrentPlayer);
                numberOfFiningCards = 0;
                return Operation.done;
            }
        }

        // Rule card N8
        else if(playedCard.getCardType().equals(cardType.N8)){
            if(rules.getField().getHandsOfPlayer(indexOfCurrentPlayer).size() == 0){
                rules.finePlayer(indexOfCurrentPlayer);
                field = getField();
                this.indexOfCurrentPlayer = rules.turnPlayer(indexOfCurrentPlayer);
                return Operation.done;
            }
            // Do not want to change index of player because of rule card N8!
            return Operation.ruleCardN8;
        }

        // Rule card N10
        else if(playedCard.getCardType().equals(cardType.N10)){
           rules.ruleCardN10();
        }

        // Rule card A
        else if(playedCard.getCardType().equals(cardType.A)){
            field = getField();
            this.indexOfCurrentPlayer = rules.ruleCardA(indexOfCurrentPlayer);
            return Operation.done;
        }

        // Rule card B
        else if(playedCard.getCardType().equals(cardType.B)) {
            rules.ruleCardB(pickColor(indexOfCurrentPlayer));
        }
        field = getField();
        this.indexOfCurrentPlayer = rules.turnPlayer(indexOfCurrentPlayer);
        return Operation.done;
    }

    public boolean checkIfHandIsAvailable(int indexOfCurrentPlayer){
        field = getField();
        ArrayList<Card> handOfPlayer = field.getHandsOfPlayer(indexOfCurrentPlayer);
        CardColor colorOfField;
        String lastTypeOfCard;
        if(field.getInCards().size() == 0)
            return true;
        else{
            colorOfField = rules.getColorOfField();
            lastTypeOfCard = rules.getLastType();
        }
        for(Card card : handOfPlayer){
            if(card.getCardColor() == colorOfField)
                return true;
            if(card.getCardType().equals(lastTypeOfCard))
                return true;
        }
        return false;
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
    public ArrayList<Player> getPlayers() {
        return players;
    }
}

