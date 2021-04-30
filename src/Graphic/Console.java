package Graphic;

import Algorithm.GameField;
import GameObject.Card;
import GameObject.CardColor;

import java.util.ArrayList;

public class Console implements ConsoleColors{

    public Console(){}

    public void printField(GameField field, int indexOfCurrentPlayer){
        System.out.println("\n");
        field.refreshOutCard();
        for(int index = 0; index < field.getNumberOfPlayers(); index++){
            if(index == indexOfCurrentPlayer)
                continue;
            System.out.println("Number of player " + index + " card: " + field.getHandsOfPlayer(index).size());
        }
        System.out.print("\nYour Cards:");
        printACardCollection(field.getHandsOfPlayer(indexOfCurrentPlayer),5);
        System.out.print("\nIn Cards:");
        printACardCollection(field.getInCards(),4);

    }

    public void printACardCollection(ArrayList<Card> cardList, int maxCol){
        if(cardList == null)
            return;
        if (cardList.size() == 0)
            return;

        if(maxCol > 5)
            maxCol = 6;
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
            System.out.println(RESET);
            System.out.println(RESET);
            for(int j = 0; j < 6; j++){
                for(int i = 0; i < (maxColInLoop * 8); i++){
                    if(i % 8 < 2)
                        System.out.print(RESET + "  ");
                    else {
                        int indexOfCard = (i / 8);
                        indexOfCard += maxCol * (currentRow);
                        Card currentCard = cardList.get(indexOfCard);
                        if(j == 3 && (i % 8 == 4)){
                            if(currentCard.getCardSign().length() == 2)
                                System.out.print(RESET + currentCard.getCardSign());
                            else
                                System.out.print(RESET + currentCard.getCardSign() + " ");
                            continue;
                        }
                        System.out.print(getBackgroundColor(currentCard.getCardColor()) + "  ");
                    }
                }
                System.out.println(RESET + "");
            }
        }



    }

    public String getBackgroundColor(CardColor color){
        if(color == CardColor.Blue){
            return BLUE_BACKGROUND;
        }
        else if(color == CardColor.Red){
            return RED_BACKGROUND;
        }
        else if(color == CardColor.Yellow){
            return YELLOW_BACKGROUND;
        }
        else if(color == CardColor.Green){
            return GREEN_BACKGROUND;
        }
        return RESET;
    }

}


