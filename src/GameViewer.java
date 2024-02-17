import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    // Instance variables
    private int x;
    private int xTwo;
    private Game game;
    private Image UnoBackground;
    // Final variables
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;
    private final String title = "Uno";
    private final int YSTARTING = 600;
    private final int SECONDROWYSTARTING = 600;
    private final int SPACE = 10;
    private final int CARDWIDTH = 70;
    private final int MAXCARDSFORROWONE = 14;
    public GameViewer(Game a){
        x = 0;
        xTwo = 0;
        this.game = a;
        this.UnoBackground = new ImageIcon("Resources/Uno Background.jpg").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle(title);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    public void paint(Graphics g){
        // Gets the coordinate of the first starting x
        if(game.getPlayer().getHand().size() > 14){
            x = 500 - (MAXCARDSFORROWONE / 2) * CARDWIDTH + SPACE;
            // Allows for the creation of a 2nd row of cards for a max of 28 cards in a hand
            xTwo = 500 - ((game.getPlayer().getHand().size() - MAXCARDSFORROWONE) / 2) * CARDWIDTH + SPACE;

        }
        else {
            x = 500 - (game.getPlayer().getHand().size() / 2) * CARDWIDTH + SPACE;
        }
        // Clear the window.
        g.setColor(Color.white);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        // Draw the background
        g.drawImage(UnoBackground, 0, 0, this);
        // Print Instructions
        g.setFont(new Font("Serif", Font.PLAIN, 15));
        g.drawString("Instructions:", 600, 100);
        g.drawString("The order in which you place the cards starts at 0", 600, 115);
        g.drawString("EX: 0 , 1 , 2 , 3...", 600, 130);
        g.drawString("Input -1 to draw a card if you need too", 600, 145);
        g.drawString("Try to match the color of the current card", 600, 160);
        g.drawString("Or you can try to match the symbol of the current card", 600, 175);
        g.drawString("If you have a wild card or a wild+4, you can place it whenever", 600, 190);
        g.drawString("and choose the color", 600, 205);
        g.drawString("The goal is to use all your cards before the other players", 600, 220);
        g.drawString("Good Luck", 600, 235);
        // Draw current card
        g.setFont(new Font("Serif", Font.PLAIN, 30));
        g.drawImage(game.getCurrent_card().getImage(), 465, 420, 70, 105, this);
        g.drawString("Current Card:", 425, 400);
        // the amount of cards each bot has
        g.drawString(Integer.toString(game.getBotOne().getHand().size()), 120, 550);
        g.drawString(Integer.toString(game.getBotTwo().getHand().size()), 495, 310);
        g.drawString(Integer.toString(game.getBotThree().getHand().size()), 843, 550);
        // Draw the players hands
        for(int i = 0; i < game.getPlayer().getHand().size(); i++){
            // Prints color of card when it is a wild or wild+4
            if(game.getCurrent_card().getSpecialMove().equals("wild") || game.getCurrent_card().getSpecialMove().equals("wild+4")){
                if(game.getCurrent_card().getColor().equals("red")){
                    g.setColor(Color.red);
                    g.drawString("Current Color: Red", 425, 368);
                    g.setColor(Color.black);
                }
                else if(game.getCurrent_card().getColor().equals("blue")){
                    g.setColor(Color.blue);
                    g.drawString("Current Color: blue", 425, 368);
                    g.setColor(Color.black);
                }
                else if(game.getCurrent_card().getColor().equals("yellow")){
                    g.setColor(Color.yellow);
                    g.drawString("Current Color: yellow", 425, 368);
                    g.setColor(Color.black);
                }
                else if(game.getCurrent_card().getColor().equals("green")){
                    g.setColor(Color.green);
                    g.drawString("Current Color: green", 425, 368);
                    g.setColor(Color.black);
                }
            }
            // Determines placement of the cards
            if(i < MAXCARDSFORROWONE) {
                game.getPlayer().getHand().get(i).draw(g, this, x, YSTARTING);
                x += 70;
            }
            if(i > MAXCARDSFORROWONE){
                game.getPlayer().getHand().get(i).draw(g, this, xTwo, SECONDROWYSTARTING);
                xTwo += 70;
            }
        }
        // Prints winner on screen
        if(game.checkWin()){
            g.setFont(new Font("Serif", Font.PLAIN, 100));
            if(game.getPlayer().getHand().isEmpty()){
                g.setColor(Color.green);
                g.drawString("You Win!", 200, 500);
            }
            else if(game.getBotOne().getHand().isEmpty()){
                g.setColor(Color.red);
                g.drawString("Bot One Wins", 200, 500);
            }
            else if(game.getBotTwo().getHand().isEmpty()){
                g.setColor(Color.red);
                g.drawString("Bot Two Wins", 200, 500);
            }
            else if(game.getBotThree().getHand().isEmpty()){
                g.setColor(Color.red);
                g.drawString("Bot Three Wins", 200, 500);
            }
        }
    }
}
