public class Card {
    // Instance Variables
    private String rank;
    private String suit;
    private int point;
    // Constructor
    public Card(String rank, String suit, int point){
        this.rank = rank;
        this.suit = suit;
        this.point = point;
    }
    public String getRank(){
        return rank;
    }
    public void setRank(String rank){
        this.rank = rank;
    }
    public String getSuit(){
        return suit;
    }
    public void setSuit(String suit){
        this.suit = suit;
    }
    public int getPoint(){
        return point;
    }
    public void setPoint(int point){
        this.point = point;
    }
    // Prints [rank] of [suit}
    public String toString(){
        return rank + " of " + suit;
    }
}
