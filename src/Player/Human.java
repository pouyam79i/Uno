package Player;

import GameObject.Card;
import GameObject.CardColor;
import Graphic.Console;
import Graphic.ConsoleColors;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Uno game project
 * this is a class of uno game project
 * @author Pouya mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */

public class Human extends Player implements ConsoleColors{

    /**
     * Constructor of human player
     * @param name of player
     * @param cards of player
     * @param playerIndex index of player
     */
    public Human(String name, ArrayList<Card> cards, int playerIndex){
        super(name, cards, playerIndex);
        setIdentity(Identity.Human);
    }

    /**
     * Handles one move of a human
     * @param typeOfField type of field
     * @param colorOfField color of field
     * @param isFined it is true the player is fined in this move
     * @return the selected card of player!
     */
    @Override
    public Card moveCard(String typeOfField, CardColor colorOfField, boolean isFined){
        System.out.println(RESET + "It is your turn " + getName() + " :");
        Console console = new Console();
        if(colorOfField != null)
            System.out.println(RESET + "Color of field is: "+ console.getBackgroundColor(colorOfField) + "   " + RESET);
        System.out.print(RESET + "Please enter index of card : " );
        if(isFined){
            System.out.println(RED_BOLD + "\nYou are fined");
            System.out.println(YELLOW_BOLD + "  Reson: Because your cards does not match the field");
            System.out.println(GREEN_BOLD + "- if you want to skip enter: " + RED_BOLD + " -1" + RESET);
        }
        Scanner scanner = new Scanner(System.in);
        boolean isValidIndex = false;
        boolean isValidCard = false;
        int selectedIndex;
        selectedIndex = scanner.nextInt();
        while (!isValid(selectedIndex, typeOfField, colorOfField, isFined)){
            System.out.print(RESET + "Please enter index of card : " );
            selectedIndex = scanner.nextInt();
        }
        if(selectedIndex == -1){
            System.out.println(RED_BOLD + "Skiped");
            return null;
        }
        return getCards().get(selectedIndex);
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
            System.out.println(RED + "Wrong index of card");
            return false;
        }
        if(colorOfField == null || typeOfField == null)
            return true;
        if(!(getCards().get(index).getCardColor() == colorOfField)){
            if(!getCards().get(index).getCardType().equals(typeOfField)){
                if(getCards().get(index).getCardType().equals("12#12"))
                    return true;
                System.out.println(RED + "Not a valid card!");
                return false;
            }
        }
        return true;
    }

}
