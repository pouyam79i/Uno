package Algorithm;

import GameObject.Card;
import GameObject.CardColor;
import GameObject.CardType;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameLauncher {

    public GameLauncher() {}

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

        CardColor[] colors = {CardColor.Blue, CardColor.Green, CardColor.Red, CardColor.Yellow};
        CardType cardTypes = new CardType();
        ArrayList<Card> cardBox = new ArrayList<Card>();

        for(CardColor color : colors){
            for (String type : cardTypes.allCards){
                Card newCard = new Card(
                        color,
                        type,
                        buildImageAddress(color, type, "small"),
                        buildImageAddress(color, type, "large")
                );

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

    private Image buildImageAddress(CardColor color, String type, String size){
        String fileAddress = "./images/uno_assets_2d/PNGs/" + size + "/";
        CardType cardTypes = new CardType();

        if(color == CardColor.Yellow)
            fileAddress += "yellow_";
        else if(color == CardColor.Blue)
            fileAddress += "blue_";
        else if(color == CardColor.Red)
            fileAddress += "red_";
        else if(color == CardColor.Green)
            fileAddress += "green_";
        else {
            System.out.println("Cannot find address of image");
            return null;
        }

        int typeInNumber = Integer.parseInt(type.split("#")[0]);

        if(typeInNumber == 10)
            fileAddress += "reverse";
        else if(typeInNumber == 11)
            fileAddress += "skip";
        else if(typeInNumber == 12)
            fileAddress += "picker";
        else if(typeInNumber == 13)
            fileAddress += "1";
        else if(typeInNumber == 14)
            fileAddress += "0";
        else if(typeInNumber >= 2 && typeInNumber <= 9)
            fileAddress += typeInNumber;
        else {
            System.out.println("Failed to make appropriate address style!");
            return null;
        }

        if(size.equals("small"))
            fileAddress += ".png";
        else if(size.equals("large"))
            fileAddress += "_large.png";
        else{
            System.out.println("wrong size input");
            return null;
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(fileAddress);

        if(image == null)
            System.out.println("This image address does not exists");

        return image;
    }

}
