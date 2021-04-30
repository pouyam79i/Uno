package GameObject;

/**
 * The Uno game project
 * this is a class of uno game project
 * @author Pouya mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */

public class Card{

    private final String cardType;
    private final CardColor cardColor;

    /**
     * Contains card main information
     * @param cardColor color of card
     * @param cardType type of card
     */
    public Card(CardColor cardColor, String cardType){
        this.cardColor = cardColor;
        this.cardType = cardType;
    }

    /**
     * check the card score and return it
     * @return the score of card
     */
    public int cardScore() {
        String[] infoArray = cardType.split("#");
        int score = Integer.parseInt(infoArray[1]);
        if (cardColor == CardColor.Black && Integer.parseInt(infoArray[0]) == 7)
            return score + 5;
        return score;
    }

    /**
     * checks of equality of cards type
     * @param card is other card to be compared with this.Card
     * @return true if the types are the same
     */
    public boolean equalsType(Card card){
        return (card.getCardType().equals(cardType));
    }

    /**
     * checks of equality of cards color
     * @param card is other card to be compared with this.Card
     * @return true if the colors are the same
     */
    public boolean equalsColor(Card card){
        return (card.getCardColor() == cardColor);
    }

    /**
     * Returns Information of card card: type and color
     * @return type_color of card
     */
    @Override
    public String toString(){
        String output = "";
        CardType cardType = new CardType();

        if(this.cardType.equals(cardType.A))
            output = "A";
        else if(this.cardType.equals(cardType.B))
            output = "B";
        else if(this.cardType.equals(cardType.C))
            output = "C";
        else if(this.cardType.equals(cardType.D))
            output = "D";
        else
            output = this.cardType.split("#")[0];

        if(cardColor == CardColor.Blue)
            output += "_Blue";
        else if(cardColor == CardColor.Red)
            output += "_Red";
        else if(cardColor == CardColor.Green)
            output += "_Green";
        else if(cardColor == CardColor.Black)
            output += "_Black";

        return output;
    }

    /**
     * signed of card, it is among 2 to D
     * @return signed of card
     */
    public String getCardSign(){
        String output = "";
        int typeNumber = Integer.parseInt(cardType.split("#")[0]);
        if(typeNumber < 11)
            output += typeNumber;
        else if(typeNumber == 11)
            output = "A";
        else if(typeNumber == 12)
            output = "B";
        else if(typeNumber == 13)
            output = "C";
        else if(typeNumber == 14)
            output = "D";
        return output;
    }

    // Getter
    public String getCardType() {
        return cardType;
    }
    public CardColor getCardColor() {
        return cardColor;
    }

}
