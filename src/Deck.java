import java.util.ArrayList;
public class Deck {
    // Instance Variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor
    public Deck(int[] ranks, String[] colors){
        cards = new ArrayList<Card>();
        // for the normal cards
        for(int i = 0; i < colors.length; i++){
            for(int j = 1; j < ranks.length; j++){
                cards.add(new Card(ranks[j], colors[i], false));
                cards.add(new Card(ranks[j], colors[i], false));
            }
            // adds 1 0 card to the deck
            cards.add(new Card(ranks[0], colors[i], false));
            // Adds 2 +2 cards for each color
            cards.add(new Card(colors[i], true, "+2"));
            cards.add(new Card(colors[i], true, "+2"));
            // Adds 2 reverse cards for each color
            cards.add(new Card(colors[i], true, "reverse"));
            cards.add(new Card(colors[i], true, "reverse"));
            // Adds 2 skip cards for each color
            cards.add(new Card(colors[i], true, "skip"));
            cards.add(new Card(colors[i], true, "skip"));
            // adds wild cards into the deck
            cards.add(new Card("NA", true, "wild"));
            // adds 4 wild +4 cards into the deck
            cards.add(new Card("NA", true, "wild+4"));
        }
        cardsLeft = cards.size();
        // Shuffle deck
    }
    // checks if the deck is empty
    public boolean isEmpty(){
        if(cardsLeft == 0){
            return true;
        }
        return false;
    }
    public int getCardsLeft(){
        return cardsLeft;
    }
    public Card deal(){
        if(isEmpty()){
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }
    public void shuffle(){
        int r = 0;
        for(int i = cards.size() - 1; i > 0; i--){
            r = (int) (Math.random() * (i + 1));
            // switches the placement of cards[r] and cards[i]
            Card card = cards.get(r);
            card = cards.set(i, card);
            cards.set(r, card);
        }
        cardsLeft = cards.size();
    }
    public ArrayList<Card> getCards(){
        return cards;
    }
}
