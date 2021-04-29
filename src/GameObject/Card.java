package GameObject;

import java.awt.*;

public class Card{

    private final String cardType;
    private final CardColor cardColor;
    private final Image image;
    private final Image imageHighQ;

    public Card(CardColor cardColor, String cardType, Image image, Image imageHighQ){
        this.cardColor = cardColor;
        this.cardType = cardType;
        this.image = image;
        this.imageHighQ = imageHighQ;
    }

    public int cardScore() {
        String[] infoArray = cardType.split("#");
        int score = Integer.parseInt(infoArray[1]);
        if (cardColor == CardColor.Yellow && Integer.parseInt(infoArray[0]) == 7)
            return score + 5;
        return score;
    }

    public boolean equalsType(Card card){
        return (card.getCardType().equals(cardType));
    }

    public boolean equalsColor(Card card){
        return (card.getCardColor() == cardColor);
    }

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
        else if(cardColor == CardColor.Yellow)
            output += "_Black";

        return output;
    }

    // Getter
    public String getCardType() {
        return cardType;
    }
    public CardColor getCardColor() {
        return cardColor;
    }
    public Image getImage() {
        return image;
    }
    public Image getImageHighQ(){
        return imageHighQ;
    }

}
