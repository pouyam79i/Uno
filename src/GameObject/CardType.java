package GameObject;

/**
 * The Uno game project
 * this is a class of uno game project
 * @author Pouya mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */

public class CardType{

    // CardNumber#CardScore
    public final String N2 = "2#2";        // Card 2
    public final String N3 = "3#3";        // Card 3
    public final String N4 = "4#4";        // Card 4
    public final String N5 = "5#5";        // Card 5
    public final String N6 = "6#6";        // Card 6
    public final String N7 = "7#10";       // Card 7
    public final String N8 = "8#8";        // Card 8
    public final String N9 = "9#9";        // Card 9
    public final String N10 = "10#10";     // Card reverser
    public final String A = "11#11";       // 11 means A Card skip
    public final String B = "12#12";       // 12 means B Card picker
    public final String C = "13#12";       // 13 means C Card 1
    public final String D = "14#13";       // 14 means D Card 0

    public final String[] allCards = {
            N2, N3, N4, N5, N6, N7, N8, N9, N10, A, B, C, D
    };

}
