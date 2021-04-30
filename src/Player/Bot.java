package Player;

import GameObject.Card;
import GameObject.CardColor;
import Graphic.ConsoleColors;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Uno game project
 * this is a class of uno game project
 * @author Pouya mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */

public class Bot extends Player implements ConsoleColors {

    /**
     * Constructor of Bot
     * @param name of bot
     * @param cards which it has
     * @param playerIndex player index
     */
    public Bot(String name, ArrayList<Card> cards, int playerIndex){
        super(name, cards, playerIndex);
        setIdentity(Identity.Bot);
    }

    /**
     * Picks a random valid card to be put in the field
     * @param typeOfField type of field
     * @param colorOfField color of field
     * @param isFined it is true the player is fined in this move
     * @return a card if could pick one, else it will be null
     */
    @Override
    public Card moveCard(String typeOfField, CardColor colorOfField, boolean isFined){
        System.out.println(PURPLE_BOLD + "It is " + getName() + " turn!");
        if(isFined)
            return null;
        Random random = new Random();
        if(colorOfField == null || typeOfField == null)
            return getCards().get(random.nextInt(getCards().size()));
        ArrayList<Integer> listOfValidCard = new ArrayList<Integer>();
        for(Card card : getCards()){
            if(isValid(getCards().indexOf(card), typeOfField, colorOfField, isFined))
                listOfValidCard.add(getCards().indexOf(card));
        }
        if(listOfValidCard.size() == 0)
            return null;
        else if(listOfValidCard.size() == 1)
            return getCards().get(listOfValidCard.get(0));
        else
            return getCards().get(listOfValidCard.get(random.nextInt(listOfValidCard.size())));
    }

    /**
     * Checks if the selected index or selected card is valid
     * @param index is the selected index
     * @param typeOfField type of field
     * @param colorOfField color of field
     * @param isFined is true if the player is fined in this round
     * @return true if movement is valid
     */
    private boolean isValid(int index, String typeOfField, CardColor colorOfField, boolean isFined){
        if(isFined && index == -1)
            return true;
        if(index < 0 || index >= getCards().size()){
            return false;
        }
        if(colorOfField == null || typeOfField == null)
            return true;
        if(!(getCards().get(index).getCardColor() == colorOfField)){
            if(!getCards().get(index).getCardType().equals(typeOfField)){
                if(getCards().get(index).getCardType().equals("12#12"))
                    return true;
                return false;
            }
        }
        return true;
    }

}
