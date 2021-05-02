import Algorithm.GameStream;
import Graphic.ConsoleColors;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * The Uno game project
 * this is a class of uno game project
 * @author Pouya mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public class Main implements ConsoleColors {

    /**
     * Reads a new line with scanner
     * @return string
     */
    public static String readLine(){
        Scanner scanner = new Scanner(System.in);
        String output;
        output = scanner.nextLine();
        if(output == null)
            return "";
        return output;
    }

    // main method of my app
    public static void main(String[] args) {

        ArrayList<MatchResult> results = new ArrayList<MatchResult>();
        Menu menu = new Menu();
        String input = "";
        int numberOfHuman = 1;
        int numberOfBot = 3;
        int numberOfPlayers = 4;
        int turningMode = 1;

        System.out.println(menu.welcome);

        // loop of app. as it is running it means its alive!3
        while (true){

            System.out.println(menu.main);
            input = readLine();
            if(Integer.parseInt(input) == 1){
                GameStream newGame = new GameStream(numberOfPlayers, turningMode, numberOfHuman);
                while (newGame.stream());
                MatchResult gameResult = new MatchResult(newGame.getPlayers());
                results.add(gameResult);
                System.out.println(PURPLE_BOLD + "\n    Game is finished !\n");
                System.out.println(gameResult);
            }
            else if(Integer.parseInt(input) == 2){
                while (true) {
                    System.out.println(PURPLE_BOLD + "\n\nSettings");
                    System.out.println(YELLOW_BOLD + "   Number of Players: " + RED_BOLD + numberOfHuman);
                    System.out.println(YELLOW_BOLD + "   Number of Bots: " + RED_BOLD + numberOfBot);
                    if(turningMode > 0)
                        System.out.println(YELLOW_BOLD + "   Turning Mode: " + RED_BOLD + "Clockwise");
                    else
                        System.out.println(YELLOW_BOLD + "   Turning Mode: " + RED_BOLD + "Counter Clockwise");
                    System.out.println(menu.settingInstruction);
                    input = readLine();
                    if(input.toUpperCase(Locale.ROOT).equals("SOLO")){
                        numberOfBot = 3;
                        numberOfHuman = 1;
                        numberOfPlayers = numberOfBot + numberOfHuman;
                    }
                    else if(input.toUpperCase(Locale.ROOT).equals("CLOCKWISE")){
                        turningMode = 1;
                    }
                    else if(input.toUpperCase(Locale.ROOT).equals("COUNTER")){
                        turningMode = -1;
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
                    }
                    else {
                        System.out.println(RED_BOLD + "Invalid input");
                    }
                }
            }
            else if(Integer.parseInt(input) == 3){
                System.out.println(menu.scoreInstruction);
                input = readLine();
                if(input.toUpperCase(Locale.ROOT).equals("LAST")){
                    if(results.size() == 0)
                        System.out.println(RED_BOLD + "\n No Match");
                    else
                        System.out.println(results.get(results.size() - 1).toString());
                }
                else if(input.toUpperCase(Locale.ROOT).equals("ALL")){
                    if(results.size() == 0)
                        System.out.println(RED_BOLD + "\n No Match");
                    else {
                        int index = 0;
                        for (MatchResult matchResult : results) {
                            System.out.println(BLUE_BOLD + "\n Match " + RED_BOLD + index + RESET);
                            System.out.println(matchResult.toString());
                        }
                    }
                }
            }
            else if(Integer.parseInt(input) == 4){
                break;
            }
            else{
                System.out.println(RED_BOLD +  "Invalid Input" + RESET);
            }

        }
    }
}

