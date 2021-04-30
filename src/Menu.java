import Graphic.ConsoleColors;

/**
 * The Uno game project
 * this is a class of uno game project
 * @author Pouya mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */

public class Menu implements ConsoleColors {

    /**
     * Contains welcome text
     */
    public final String welcome = BLUE_BOLD + "\nWelcome to \n       " +
            RED_BOLD + "U" + YELLOW_BOLD + "N" + GREEN_BOLD + "O" +  RESET + " " +
            CYAN_BOLD + "C" + YELLOW_BOLD + "A" + GREEN_BOLD + "R" + PURPLE_BOLD + "D" + RESET +  " " +
            RED_BOLD + "G" + YELLOW_BOLD + "A" + CYAN_BOLD + "M" + PURPLE_BOLD + "E" + RESET + " " + RESET;

    /**
     * Contains main menu information
     */
    public final String main =
            BLUE_BOLD + "\nMain Menu" + YELLOW_BOLD +
                    "\n   1 - Play" +
                    "\n   2 - Settings" +
                    "\n   3 - Scores" +
                    "\n   4 - Exit" + RESET;

    /**
     * setting instruction text
     */
    public final String settingInstruction = GREEN_BRIGHT + """
            To set solo play mode enter  '""" + RED_BOLD +
             "SOLO" + GREEN_BRIGHT + "'" +
            GREEN_BOLD + """
              
              \t- Default total number of players is 4""" +
            GREEN_BRIGHT +
            """
            
            To set custom setting enter new setting like:  'number of players'#'number of bots'""" +
            GREEN_BOLD + """
              
              \t- Like: 1#3
              \t- Remember: 6 >= total number of players >= 3
              \t- Remember: number of human players >= 1
              """ +
            GREEN_BRIGHT +
            "TO change turning mode enter: '" + RED_BOLD + "CLOCKWISE" + GREEN_BRIGHT + "' or '" + RED_BOLD +
            "COUNTER" + GREEN_BRIGHT + "'\n"
            + """
            To exit setting enter: '""" +
            RED_BOLD + "EXIT" + GREEN_BRIGHT + "'" + RESET;

    /**
     * score list menu text
     */
    public final String scoreInstruction =
                GREEN_BRIGHT + "\nTo print last match scores enter: '" + RED_BOLD + "LAST" + GREEN_BRIGHT + "'\n" +
                "To print all matches enter: '" + RED_BOLD + "ALL" + GREEN_BRIGHT + "'" +
                "\nTo exit just inter!";

}
