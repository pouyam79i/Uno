import Graphic.ConsoleColors;

public class Menu implements ConsoleColors {
    public final String welcome = BLUE_BOLD + "Welcome to \n       " +
            RED_BOLD + "U" + YELLOW_BOLD + "N" + GREEN_BOLD + "O" +  RESET + " " +
            CYAN_BOLD + "C" + YELLOW_BOLD + "A" + GREEN_BOLD + "R" + PURPLE_BOLD + "D" + RESET +  " " +
            RED_BOLD + "G" + YELLOW_BOLD + "A" + CYAN_BOLD + "M" + PURPLE_BOLD + "E" + RESET + " " + RESET;
    public final String main =
            BLUE_BOLD + "\nMain Menu" + YELLOW_BOLD +
                    "\n   1 - Play" +
                    "\n   2 - Settings" +
                    "\n   3 - Exit" + RESET;

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
              """ +
            GREEN_BRIGHT + """
            To exit setting enter: '""" +
            RED_BOLD + "EXIT" + GREEN_BRIGHT + "'" + RESET;

}
