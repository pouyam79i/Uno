package Player;

import GameObject.Card;
import GameObject.CardColor;
import Graphic.ConsoleColors;

import java.util.ArrayList;
import java.util.Scanner;

public class Human extends Player implements ConsoleColors{

    public Human(String name, ArrayList<Card> cards, int playerIndex){
        super(name, cards, playerIndex);
    }

    @Override
    public Card moveCard(Card lastInCard){
        System.out.println(RESET + "It is your turn " + getName() + " :");
        System.out.print(RESET + "Please enter index of card : " );
        Scanner scanner = new Scanner(System.in);
        boolean isValidIndex = false;
        boolean isValidCard = false;
        int selectedIndex;
        selectedIndex = scanner.nextInt();
        while (!isValid(selectedIndex, lastInCard)){
            System.out.print(RESET + "Please enter index of card : " );
            selectedIndex = scanner.nextInt();
        }
        return getCards().get(selectedIndex);
    }

    private boolean isValid(int index, Card lastInCard){
        if(index < 0 || index >= getCards().size()){
            System.out.println(RED + "Wrong index of card");
            return false;
        }
        if(!getCards().get(index).equalsColor(lastInCard)){
            if(!getCards().get(index).equalsType(lastInCard)){
                if(getCards().get(index).getCardType().equals("12#12"))
                    return true;
                System.out.println(RED + "Not a valid card!");
                return false;
            }
        }
        return true;
    }

}
