import Algorithm.GameField;
import Algorithm.GameLauncher;
import Graphic.Console;

public class Main {
    public static void main(String[] args) {
        GameField field;
        Console console = new Console();
        GameLauncher launcher = new GameLauncher();
        field = launcher.launchField(4, 7);
        console.printField(field, 0);
    }
}
