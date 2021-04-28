package Algorithm;

import GameObject.Card;
import GameObject.CardColor;
import GameObject.CardType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameLauncher {

    private GameLauncher() {}

    public GameField launchField(int numberOfPlayers, int basicCardInHandNumber){

        // Limiting Number of players between 3 to 5
        if(numberOfPlayers > 5)
            numberOfPlayers = 5;
        else if(numberOfPlayers < 3)
            numberOfPlayers = 3;

        // Limiting Number of basic card in hands between 5 to 7
        if(basicCardInHandNumber > 7)
            basicCardInHandNumber = 7;
        else if(basicCardInHandNumber < 5)
            basicCardInHandNumber = 5;

        GameField basicField = new GameField(numberOfPlayers);

        CardColor[] colors = {CardColor.Blue, CardColor.Green, CardColor.Red, CardColor.Black};
        CardType cardTypes = new CardType();
        ArrayList<Card> cardBox = new ArrayList<Card>();

        for(CardColor color : colors){
            for (String type : cardTypes.allCards){
                Card newCard = new Card(color, type);
                cardBox.add(newCard);
            }
        }

        HashMap<Integer, ArrayList<Card>> playersCard = new HashMap<Integer, ArrayList<Card>>();

        for(int player = 0; player < numberOfPlayers; player++){
            ArrayList<Card> cards = new ArrayList<Card>();
            for(int cardNumber = 0; cardNumber < basicCardInHandNumber; cardNumber++){
                Random rand = new Random();
                int random_index = rand.nextInt(cardBox.size());
                Card takenCard = cardBox.get(random_index);
                cards.add(takenCard);
                cardBox.remove(takenCard);
            }
            playersCard.put(player, cards);
        }

        basicField.setPlayerCards(playersCard);
        basicField.setOutCards(cardBox);

        return basicField;
    }

}