package Graphic;

import Algorithm.GameField;
import GameObject.Card;
import GameObject.CardColor;

import java.util.ArrayList;

/**
 * The Uno game project
 * this is a class of uno game project
 * @author Pouya mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */

public class Console implements ConsoleColors{

    /**
     * Constructor of Console
     */
    public Console(){}

    /**
     * Print the field of game
     * @param field of game
     * @param indexOfCurrentPlayer index of current player
     */
    public void printField(GameField field, int indexOfCurrentPlayer){
        System.out.println("\n");
        field.refreshOutCard();
        for(int index = 0; index < field.getNumberOfPlayers(); index++){
            if(index == indexOfCurrentPlayer)
                continue;
            System.out.println(PURPLE_BOLD + "Number of player " + index + " card: " + field.getHandsOfPlayer(index).size());
        }
        System.out.println(RED_BOLD + "Number of Outcards: " + field.getOutCards().size());
        System.out.print(YELLOW_BOLD + "\nYour Cards:");
        printACardCollection(field.getHandsOfPlayer(indexOfCurrentPlayer), 8);
        System.out.print(YELLOW_BOLD + "\nIn Cards:");
        printACardCollection(field.getInCards(),6);
    }

    /**
     * print a collection of cards
     * @param cardList is the collection of cards
     * @param maxCol number of max cols, used to organize a line of printed cards
     */
    public void printACardCollection(ArrayList<Card> cardList, int maxCol){
        if(cardList == null)
            return;
        if (cardList.size() == 0) {
            System.out.println(RESET + "");
            return;
        }

        if(maxCol > 7)
            maxCol = 7;
        if(maxCol < 1)
            maxCol = 1;

        int maxRow = 1;
        if(maxCol >= cardList.size())
            maxCol = cardList.size();
        else
            maxRow += (cardList.size() / maxCol);

        int maxColInLoop = maxCol;
        for(int currentRow = 0; currentRow < maxRow; currentRow++){
            if(currentRow == (maxRow-1) && maxRow > 1){
                maxColInLoop = cardList.size() - ((maxRow - 1) * maxCol);
            }
//            System.out.println(RESET);
            for(int j = 0; j < 7; j++){
                for(int i = 0; i < (maxColInLoop * 8); i++){
                    if(i % 8 < 2)
                        System.out.print(RESET + "  ");
                    else {
                        int indexOfCard = (i / 8);
                        indexOfCard += maxCol * (currentRow);
                        Card currentCard = cardList.get(indexOfCard);
                        if(j == 2 && (i % 8 == 3)){
                            if(currentCard.getCardSign().length() == 2)
                                System.out.print(getColor(currentCard.getCardColor()) + currentCard.getCardSign());
                            else
                                System.out.print(getColor(currentCard.getCardColor()) + currentCard.getCardSign() + " ");
                            continue;
                        }
                        if(j == 5 && (i % 8 == 3)){
                            if(indexOfCard >= 10)
                                System.out.print(RESET + indexOfCard);
                            else
                                System.out.print(RESET + indexOfCard + " ");
                            continue;
                        }
                        if(j == 1) {
                            if(i % 8 == 2){
                                System.out.print(getColor(currentCard.getCardColor()) + "┌─");
                            }
                            else if(i % 8 == 7){
                                System.out.print("─┐");
                            }
                            else
                                System.out.print(getColor(currentCard.getCardColor()) + "──");
                        }
                        else if(j == 6){
                            if(i % 8 == 2){
                                System.out.print(getColor(currentCard.getCardColor()) + "└─");
                            }
                            else if(i % 8 == 7){
                                System.out.print("─┘");
                            }
                            else
                                System.out.print(getColor(currentCard.getCardColor()) + "──");
                        }
                        else {
                            if(i % 8 == 2 && j > 0) {
                                System.out.print(getColor(currentCard.getCardColor()) + "│");
                                System.out.print(RESET + " ");
                            }
                            else if(i % 8 == 7 && j > 0){
                                System.out.print(RESET + " ");
                                System.out.print(getColor(currentCard.getCardColor()) + "│");
                            }
                            else
                                System.out.print(RESET + "  ");
                        }
                    }
                }
                System.out.println(RESET + "");
            }
        }
    }

    /**
     * Returns a proper back ground color
     * @param color of card
     * @return a ansi value in string
     */
    public String getBackgroundColor(CardColor color){
        if(color == CardColor.Blue){
            return BLUE_BACKGROUND;
        }
        else if(color == CardColor.Red){
            return RED_BACKGROUND;
        }
        else if(color == CardColor.Black){
            return BLACK_BACKGROUND;
        }
        else if(color == CardColor.Green){
            return GREEN_BACKGROUND;
        }
        return RESET;
    }

    /**
     * returns a proper color for texts
     * @param color of card
     * @return an ansi value in string
     */
    public String getColor(CardColor color){
        if(color == CardColor.Blue){
            return BLUE_BOLD;
        }
        else if(color == CardColor.Red){
            return RED_BOLD;
        }
        else if(color == CardColor.Black){
            return BLACK_BOLD;
        }
        else if(color == CardColor.Green){
            return GREEN_BOLD;
        }
        return RESET;
    }

}


