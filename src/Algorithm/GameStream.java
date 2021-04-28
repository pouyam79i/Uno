package Algorithm;

public class GameStream {

    private GameField field;

    public GameStream(){
        GameLauncher gameLauncher = new GameLauncher();
        field = gameLauncher.launchField(4, 7);
    }

}
