public class Card {
    // Instance Variables
    private int rank;
    private String color;
    private boolean special;
    private String special_move;

    // Constructor for normal card + skip (has a value of -1)
    public Card(int rank, String suit, boolean special){
        this.rank = rank;
        this.color = suit;
        this.special = special;
    }
    public Card(String color, boolean special, String special_move){
        this.color = color;
        this.special = special;
        this.special_move = special_move;
    }
    public int getRank(){
        return rank;
    }
    public void setRank(int rank){
        this.rank = rank;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String suit){
        this.color = suit;
    }
    public boolean getSpecial(){
        return special;
    }
    public void setSpecial(boolean special){
        this.special = special;
    }
    public String getSpecialMove(){
        return special_move;
    }
    public void setSpecialMove(String special_move){
        this.special_move = special_move;
    }
    // Prints [rank] of [suit}
    public String toString(){
        if(special){
            if(special_move.equals("+2") || special_move.equals("skip") || special_move.equals("reverse")){
                return special_move + " of " + color;
            }
            else{
                return special_move;
            }
        }
        return rank + " of " + color;
    }
}
