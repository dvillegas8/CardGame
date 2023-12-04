import java.util.ArrayList;
public class Player {
    // Instance Variables
    private ArrayList<Card> hand;
    private int points;
    private String name;

    // Constructor with no hand
    public Player(String name){
        this.name = name;
        hand = new ArrayList<Card>();
        points = 0;
    }
    // Constructor for existing hand
    public Player(String name, ArrayList<Card> hand){
        this.name = name;
        hand = new ArrayList<Card>();
        points = 0;
        for(int i = 0; i < hand.size(); i++){
            this.hand.add(hand.get(i));
        }
    }
    // Get method for hand
    public ArrayList<Card> getHand(){
        return hand;
    }
    // Set method for hand
    public void setHand(ArrayList<Card> hand){
        this.hand = hand;
    }
    // Get method for points
    public int getPoints(){
        return points;
    }
    // Set method for points
    public void setPoints(int points){
        this.points = points;
    }
    // Get method for name
    public String getName(){
        return name;
    }
    // Set method for name
    public void setName(String name){
        this.name = name;
    }
    // Adds points
    public void addPoints(int points){
        this.points += points;
    }
    // Adds card into hand
    public void addCard(Card card){
        hand.add(card);
    }
    // ToString method
    public String toString(){
        return name + " has " + points + " points " + "\n" + name + "'s cards: " + hand;
    }

}
