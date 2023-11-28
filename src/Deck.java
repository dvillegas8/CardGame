import java.util.ArrayList;
public class Deck {
    // Instance Variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor
    public Deck(String[] ranks, String[] suits, int[] points){
        cards = new ArrayList<Card>();
        for(int i = 0; i < ranks.length; i++){
            for(int j = 0; j < suits.length; j++){
                cards.add(new Card(ranks[i], suits[j], points[i]));
            }
        }
        cardsLeft = cards.size();
        // Shuffle deck
    }
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
        for(int i = cards.size() - 1; i > -1; i--){
            r = (int) (Math.random() * cards.size() - 1);
            // switches the placement of cards[r] and cards[i]
            Card card = cards.get(r);
            card = cards.set(i, card);
            cards.set(r, card);
        }
    }
}
