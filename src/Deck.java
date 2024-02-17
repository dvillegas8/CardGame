import java.awt.*;
import java.util.ArrayList;
public class Deck {
    // Instance Variables
    private ArrayList<Card> cards;
    private int cardsLeft;
    private GameViewer viewer;
    private Image[] cardImages;
    // Final Variables
    private final int WILD_INDEX = 52;
    private final int WILDADDFOUR_INDEX = 53;

    // Constructor
    public Deck(int[] ranks, String[] colors, Game a, Image[] cardImages){
        int counter = 0;
        cards = new ArrayList<Card>();
        this.cardImages = cardImages;
        // for the normal cards
        for(int i = 0; i < colors.length; i++){
            // adds 1 0 card to the deck
            cards.add(new Card(ranks[0], colors[i], false, cardImages[counter]));
            for(int j = 1; j < ranks.length; j++){
                counter++;
                cards.add(new Card(ranks[j], colors[i], false, cardImages[counter]));
                cards.add(new Card(ranks[j], colors[i], false, cardImages[counter]));
            }
            counter++;
            // Adds 2 +2 cards for each color
            cards.add(new Card(colors[i], true, "+2", true, cardImages[counter]));
            cards.add(new Card(colors[i], true, "+2", true, cardImages[counter]));
            counter++;
            // Adds 2 reverse cards for each color
            cards.add(new Card(colors[i], true, "reverse", true, cardImages[counter]));
            cards.add(new Card(colors[i], true, "reverse", true, cardImages[counter]));
            counter++;
            // Adds 2 skip cards for each color
            cards.add(new Card(colors[i], true, "skip", true, cardImages[counter]));
            cards.add(new Card(colors[i], true, "skip", true, cardImages[counter]));
            counter++;
            // adds wild cards into the deck
            cards.add(new Card("NA", true, "wild", false, cardImages[52]));
            // adds 4 wild +4 cards into the deck
            cards.add(new Card("NA", true, "wild+4", true, cardImages[53]));
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
