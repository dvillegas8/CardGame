import java.awt.*;

public class Card {
    // Instance Variables
    private int rank;
    private String color;
    private boolean special;
    private String special_move;
    private boolean skipped;
    private Image card_image;
    // Final variables
    private final int CARDWIDTH = 70;
    private final int CARDHEIGHT = 105;
    // Constructor for normal card
    public Card(int rank, String suit, boolean special, Image image){
        this.rank = rank;
        this.color = suit;
        this.special = special;
        this.special_move = "no special_move";
        card_image = image;
    }
    // Constructor for a special card
    public Card(String color, boolean special, String special_move, boolean skipped, Image image){
        this.rank = -1;
        this.color = color;
        this.special = special;
        this.special_move = special_move;
        this.skipped = skipped;
        card_image = image;
    }
    public Image getImage(){
        return card_image;
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
    public void setColor(String color){
        this.color = color;
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
    public boolean getSkipped(){
        return skipped;
    }
    public void setSkipped(boolean skipped){
        this.skipped = skipped;
    }
    // Prints [rank] of [suit}
    public String toString(){
        // checks if it is a special card to it returns a different string
        if(special){
            if(special_move.equals("+2") || special_move.equals("skip") || special_move.equals("reverse")){
                return special_move + " of " + color;
            }
            else{
                return special_move + " of " + color;
            }
        }
        return rank + " of " + color;
    }
    // Draw the image of the card
    public void draw(Graphics g, GameViewer viewer, int x, int y){
        g.drawImage(card_image, x, y, CARDWIDTH, CARDHEIGHT, viewer);

    }

}
