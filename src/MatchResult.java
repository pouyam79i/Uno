import Graphic.ConsoleColors;
import Player.Player;

import java.util.ArrayList;

/**
 * The Uno game project
 * this is a class of uno game project
 *
 * This class contains previous matches information
 *
 * @author Pouya mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */

public class MatchResult implements ConsoleColors {

    /**
     * List of players of the match
     */
    private final ArrayList<Player> players;

    /**
     * Constructor of match result
     * @param players of match
     */
    public MatchResult(ArrayList<Player> players){
        this.players = players;
    }

    /**
     * Put match information into a string
     * @return information of match
     */
    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        if(players == null)
            return "";
        Player winner = players.get(0);
        int lowestScore = players.get(0).getCurrentScore();
        for(Player player : players){
            if(lowestScore > player.getCurrentScore()) {
                lowestScore = player.getCurrentScore();
                winner = player;
            }
        }
        for(int index = 0; index < players.size(); index++){
            output.append(BLUE + "─────────────────────────────────────────────────────────" + RESET + "\n");
            if(players.indexOf(winner) == index)
                output.append(YELLOW_BOLD + "Winner: Player ").append(index).append(" - ").append(winner.getName()).append(" - Score: ").append(winner.getCurrentScore()).append(RESET).append("\n");
            else
                output.append(RED_BOLD + "Loser: Player ").append(index).append(" - ").append(players.get(index).getName()).append(" - Score: ").append(players.get(index).getCurrentScore()).append(RESET).append("\n");
        }
        output.append(BLUE + "─────────────────────────────────────────────────────────" + RESET + "\n");
        return output.toString();
    }

}
