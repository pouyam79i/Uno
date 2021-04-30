import Graphic.ConsoleColors;

import java.util.Locale;
import java.util.Scanner;

public class Main implements ConsoleColors {

    public static String readLine(){
        Scanner scanner = new Scanner(System.in);
        String output;
        output = scanner.nextLine();
        if(output == null)
            return "";
        return output;
    }

    public static void main(String[] args) {

        Menu menu = new Menu();
        String input = "";
        int numberOfHuman = 4;
        int numberOfBot = 0;
        int numberOfPlayers = 4;

        while (true){
            System.out.println(menu.welcome);
            System.out.println(menu.main);
            input = readLine();
            if(Integer.parseInt(input) == 1){

            }
            else if(Integer.parseInt(input) == 2){
                while (true) {
                    System.out.println(PURPLE_BOLD + "\n\nSettings");
                    System.out.println(YELLOW_BOLD + "   Number of Players: " + RED_BOLD + numberOfHuman);
                    System.out.println(YELLOW_BOLD + "   Number of Bots: " + RED_BOLD + numberOfBot);
                    System.out.println(menu.settingInstruction);
                    input = readLine();
                    if(input.toUpperCase(Locale.ROOT).equals("SOLO")){
                        numberOfBot = 3;
                        numberOfHuman = 1;
                        numberOfPlayers = numberOfBot + numberOfHuman;
                        continue;
                    }
                    else if(input.toUpperCase(Locale.ROOT).equals("EXIT")){
                        break;
                    }
                    else if(input.contains("#")){
                        String[] inputArray = input.split("#");
                        if(Integer.parseInt(inputArray[0]) < 1){
                            System.out.println( RED_BOLD + "Invalid input");
                            continue;
                        }
                        if(Integer.parseInt(inputArray[0]) + Integer.parseInt(inputArray[1]) > 5 ||
                                Integer.parseInt(inputArray[0]) + Integer.parseInt(inputArray[1]) < 3){
                            System.out.println( RED_BOLD + "Invalid input");
                            continue;
                        }
                        numberOfHuman = Integer.parseInt(inputArray[0]);
                        numberOfBot = Integer.parseInt(inputArray[1]);
                        numberOfPlayers = numberOfBot + numberOfHuman;
                        continue;
                    }
                    System.out.println( RED_BOLD + "Invalid input");
                }
            }
            else if(Integer.parseInt(input) == 3){
                break;
            }
        }

    }

}

