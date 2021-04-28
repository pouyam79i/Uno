package GameObject;

public class Card implements CardOperation{

    private String cardType;
    private CardColor cardColor;

    public Card(CardColor cardColor, String cardType){
        this.cardColor = cardColor;
        this.cardType = cardType;
    }

    @Override
    public int cardScore() {
        String[] infoArray = cardType.split("#");
        int score = Integer.parseInt(infoArray[1]);
        if (cardColor == CardColor.Black && Integer.parseInt(infoArray[0]) == 7)
            return score + 5;
        return score;
    }

    @Override
    public int cardRule(String... args) {
        return 0;
    }

    // Getter
    public String getCardType() {
        return cardType;
    }
    public CardColor getCardColor() {
        return cardColor;
    }

}
