package Graphic;

import Algorithm.GameField;

public class Console {

    public Console(){}

    public void printField(GameField field, int indexOfCurrentPlayer){
        System.out.println("\n");
        for(int index = 0; index < field.getNumberOfPlayers(); index++){
            if(index == indexOfCurrentPlayer)
                continue;
            System.out.println("Number of player " + index + " card: " + field.getHandsOfPlayer(index).size());
        }
        System.out.println("\n Your Hand: ");
        for (int index = 0; index < field.getHandsOfPlayer(indexOfCurrentPlayer).size(); index++){
            System.out.print(" " + field.getHandsOfPlayer(indexOfCurrentPlayer).get(index) + " ");
            if(index % 5 == 4)
                System.out.println();
        }
        System.out.println("\n In cards: ");
        for (int index = 0; index < field.getInCards().size(); index++){
            System.out.print(" " + field.getInCards().get(index) + " ");
            if(index % 5 == 4)
                System.out.println();
        }

    }



}
