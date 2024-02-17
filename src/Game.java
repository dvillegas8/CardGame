// Diego Villegas December 11th 2023
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
     // Instance variables
     private Image[] cards;
     private GameViewer g;
     private Player playerOne;
     private Player botOne;
     private Player botTwo;
     private Player botThree;
     private Deck deck;
     private int[] ranks;
     private String[] colors;
     private Card current_card;
     private Scanner scan;
     public Game(String playerName){
         playerOne = new Player(playerName);
         botOne = new Player("Bot 1");
         botTwo = new Player("Bot 2");
         botThree = new Player("Bot 3");
         colors = new String[]{"red", "blue", "green", "yellow"};
         ranks = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
         cards = new Image[54];
         initializeImages();
         deck = new Deck(ranks, colors, this, cards);
         deck.shuffle();
         while(true){
             current_card = deck.deal();
             if(!current_card.getSpecial()){
                 break;
             }
         }
         scan = new Scanner(System.in);
        g = new GameViewer(this);
         //System.out.println(deck.getCardsLeft());
         //System.out.println(deck.getCards());
     }
    public String printInstructions(){
        String instructions = "Welcome to Uno";
        return instructions;
    }
    public void unoGame(){
        int turn = 1;
        String direction = "right";
        deck.shuffle();
        // Starting cards for everyone
        for(int i = 0; i < 7; i++){
            playerOne.addCard(deck.deal());
            botOne.addCard(deck.deal());
            botTwo.addCard(deck.deal());
            botThree.addCard(deck.deal());
        }
        while(!checkWin()){
            // Checks if the deck is empty so it shuffles it again
            if(deck.isEmpty()){
                deck.shuffle();
            }
            // Figures out the direction of the game
            if(current_card.getSpecial()){
                if(current_card.getSpecialMove().equals("reverse") && direction.equals("right") && current_card.getSkipped()){
                    Card new_card = new Card(current_card.getColor(), true, "reverse", false, current_card.getImage());
                    current_card = new_card;
                    direction = "left";
                    turn *= -1;
                }
                else if(current_card.getSpecialMove().equals("reverse") && direction.equals("left") && current_card.getSkipped()){
                    Card new_card = new Card(current_card.getColor(), true, "reverse", false, current_card.getImage());
                    current_card = new_card;
                    direction = "right";
                    turn *= -1;
                }
            }

            // Turns
            if(direction.equals("right")){
                // Player's turn
                if(turn == 1){
                    // Checks if turns should be skipped
                    System.out.println(playerOne.getName() + "'s turn: ");
                    playerTurn();
                    turn++;
                    g.repaint();
                    try {
                        // to sleep 10 seconds
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // recommended because catching InterruptedException clears interrupt flag
                        Thread.currentThread().interrupt();
                        // you probably want to quit if the thread is interrupted
                        return;
                    }
                }
                // Bot 1 turn
                else if(turn == 2){
                    System.out.println(botOne.getName() + "'s turn: ");
                    botTurn(botOne);
                    turn++;
                    g.repaint();
                    try {
                        // to sleep 10 seconds
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // recommended because catching InterruptedException clears interrupt flag
                        Thread.currentThread().interrupt();
                        // you probably want to quit if the thread is interrupted
                        return;
                    }
                }
                // Bot 2 turn
                else if(turn == 3){
                    System.out.println(botTwo.getName() + "'s turn: ");
                    botTurn(botTwo);
                    turn++;
                    g.repaint();
                    try {
                        // to sleep 10 seconds
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // recommended because catching InterruptedException clears interrupt flag
                        Thread.currentThread().interrupt();
                        // you probably want to quit if the thread is interrupted
                        return;
                    }
                }
                // Bot 3 turn
                else if(turn == 4){
                    System.out.println(botThree.getName() + "'s turn: ");
                    botTurn(botThree);
                    turn = 1;
                    g.repaint();
                    try {
                        // to sleep 10 seconds
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // recommended because catching InterruptedException clears interrupt flag
                        Thread.currentThread().interrupt();
                        // you probably want to quit if the thread is interrupted
                        return;
                    }
                }
                else {
                    System.out.println("Error");
                }
            }
            else if(direction.equals("left")){
                // Players turn
                if(turn == -3){
                    System.out.println(playerOne.getName() + "'s turn: ");
                    playerTurn();
                    turn++;
                    g.repaint();
                    try {
                        // to sleep 10 seconds
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // recommended because catching InterruptedException clears interrupt flag
                        Thread.currentThread().interrupt();
                        // you probably want to quit if the thread is interrupted
                        return;
                    }
                }
                // Bot 1's turn
                else if(turn == -4){
                    System.out.println(botOne.getName() + "'s turn: ");
                    botTurn(botOne);
                    turn++;
                    g.repaint();
                    try {
                        // to sleep 10 seconds
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // recommended because catching InterruptedException clears interrupt flag
                        Thread.currentThread().interrupt();
                        // you probably want to quit if the thread is interrupted
                        return;
                    }
                }
                // Bot 2's turn
                else if(turn == -1){
                    System.out.println(botTwo.getName() + "'s turn: ");
                    botTurn(botTwo);
                    turn = -4;
                    g.repaint();
                    try {
                        // to sleep 10 seconds
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // recommended because catching InterruptedException clears interrupt flag
                        Thread.currentThread().interrupt();
                        // you probably want to quit if the thread is interrupted
                        return;
                    }
                }
                // Bot 3's turn
                else if(turn == -2){
                        System.out.println(botThree.getName() + "'s turn: ");
                        botTurn(botThree);
                        turn++;
                        g.repaint();
                    try {
                        // to sleep 10 seconds
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // recommended because catching InterruptedException clears interrupt flag
                        Thread.currentThread().interrupt();
                        // you probably want to quit if the thread is interrupted
                        return;
                    }
                }
            }
        }
        this.printWinner();

    }
    public void playerTurn() {
        System.out.println("Current card is : " + current_card);
        if(current_card.getSpecial()){
            // If the card is + 4, then add +4 cards to your hand
            if(current_card.getSpecialMove().equals("wild+4") && current_card.getSkipped()){
                for(int i = 0; i < 4; i++){
                    playerOne.addCard(deck.deal());
                }
                Card new_card = new Card(current_card.getColor(), true, "wild+4", false, current_card.getImage());
                current_card = new_card;
                System.out.println(playerOne.getName() + " draws 4 cards");
                return;
            }
            // If is +2 card, then add 2 cards to the deck
            else if(current_card.getSpecialMove().equals("+2") && current_card.getSkipped()){
                playerOne.addCard(deck.deal());
                playerOne.addCard(deck.deal());
                Card new_card = new Card(current_card.getColor(), true, "+2", false, current_card.getImage());
                current_card = new_card;
                System.out.println(playerOne.getName() + " draws 2 cards");
                return;
            }
            // If skipped is true, then it skips and sets it to false in order to skip the turn
            else if(current_card.getSpecialMove().equals("skip") && current_card.getSkipped()){
                Card new_card = new Card(current_card.getColor(), true, "skip", false, current_card.getImage());
                current_card = new_card;
                System.out.println(playerOne.getName() + " got skipped");
                return;
            }
        }
        System.out.println("In order to choose a card, you must input the index of that (the number next to it)");
        System.out.println("If you want to draw, just input -1");
        System.out.println("Here are your cards:");
        System.out.println(playerOne);
        int choice = 0;
        int numOfChoice = 0;
        // While loop that loops as long as the user inputs a valid choice
        while(true) {
            System.out.print("Please choose an index: ");
            choice = scan.nextInt();
            if (choice < playerOne.getHand().size()) {
                // draw card
                if (choice == -1) {
                    playerOne.addCard(deck.deal());
                    System.out.println(playerOne.getName() + " draws a card");
                    // Attempts to see if that card that was just drawn is able to be played
                    if(playerOne.getHand().get(playerOne.getHand().size() - 1).getSpecial()) {
                        if (playerOne.getHand().get(playerOne.getHand().size() - 1).getSpecialMove().equals("wild") || playerOne.getHand().get(playerOne.getHand().size() - 1).getSpecialMove().equals("wild+4")) {
                            System.out.println("You drew a " + playerOne.getHand().get(playerOne.getHand().size() - 1).getSpecialMove() + " card and placed it");
                            wildMove(playerOne.getHand().size() - 1);
                        } else if (isSpecialValid(playerOne.getHand().size() - 1, playerOne)) {
                            System.out.println("You drew a " + playerOne.getHand().get(playerOne.getHand().size() - 1) + " and placed it");
                            current_card = playerOne.getHand().remove(playerOne.getHand().size() - 1);
                        }
                    }
                    else if(isValid(playerOne.getHand().size() - 1, playerOne)){
                        System.out.println("You drew a " + playerOne.getHand().get(playerOne.getHand().size() - 1) + " and placed it");
                        current_card = playerOne.getHand().remove(playerOne.getHand().size() - 1);
                    }
                    return;
                }
                // checks if it is a special card
                else if (playerOne.getHand().get(choice).getSpecial()) {
                    // Wild card move
                    if (playerOne.getHand().get(choice).getSpecialMove().equals("wild") || playerOne.getHand().get(choice).getSpecialMove().equals("wild+4")) {
                        wildMove(choice);
                        break;
                    }
                    // +2, reverse, or skip move
                    else if (isSpecialValid(choice, playerOne)) {
                        current_card = playerOne.getHand().remove(choice);
                        System.out.println(playerOne.getName() + " plays " + current_card.toString());
                        break;
                    }
                    System.out.println("Could not play this card");

                }
                // checks if a normal card is valid and able to be placed
                else if (isValid(choice, playerOne)) {
                    current_card = playerOne.getHand().remove(choice);
                    System.out.println(playerOne.getName() + " plays " + current_card.toString());
                    break;
                }
                System.out.println("Error: Invalid Index");
            }
            System.out.println("Error: Invalid Index");
        }

    }
    // Return true if anyone in the game has an empty hand, false if not
    public boolean checkWin(){
         if(playerOne.getHand().isEmpty() || botOne.getHand().isEmpty() || botTwo.getHand().isEmpty() || botThree.getHand().isEmpty()){
             return true;
         }

         return false;
    }
    // Allows Players to play a wild card
    public void wildMove(int choice){
        scan.nextLine();
        String chosen_color = "";
        System.out.println("You placed a wild card, which color do you want? (red/yellow/blue/green)");
        while(true){
            chosen_color = scan.nextLine();
            System.out.println();
            // Sets wild card as red and makes it the current card
            if (chosen_color.equals("red")) {
                playerOne.getHand().get(choice).setColor("red");
                current_card = playerOne.getHand().remove(choice);
                break;
            }
            // Sets wild card as yellow and makes it the current card
            else if (chosen_color.equals("yellow")) {
                playerOne.getHand().get(choice).setColor("yellow");
                current_card = playerOne.getHand().remove(choice);
                break;
            }
            // Sets wild card as blue and makes it the current card
            else if (chosen_color.equals("blue")) {
                playerOne.getHand().get(choice).setColor("blue");
                current_card = playerOne.getHand().remove(choice);
                break;
            }
            // Sets wild card as green and makes it the current card
            else if (chosen_color.equals("green")) {
                playerOne.getHand().get(choice).setColor("green");
                current_card = playerOne.getHand().remove(choice);
                break;
            }
            System.out.println("Error: Invalid color");
        }
    }
    // Check if any special card other than wild can be played, if so it replaces current card, if not then it prints "cannot place this card"
    public boolean isSpecialValid(int choice, Player player){
         if(current_card.getColor().equals(player.getHand().get(choice).getColor()) || current_card.getSpecialMove().equals(player.getHand().get(choice).getSpecialMove())){
             return true;
         }
         return false;
    }
    // Returns true if the card is able to be played, false if not
    public boolean isValid(int choice, Player player){
         if(current_card.getColor().equals(player.getHand().get(choice).getColor()) || current_card.getRank() == player.getHand().get(choice).getRank()){
             return true;
         }
         return false;
    }
    public void botTurn(Player bot){
         int bot_choice = 0;
        if(current_card.getSpecial()) {
            // If the card is + 4, then add +4 cards to your hand
            if (current_card.getSpecialMove().equals("wild+4") && current_card.getSkipped()) {
                for (int i = 0; i < 4; i++) {
                    bot.addCard(deck.deal());
                }
                Card new_card = new Card(current_card.getColor(), true, "wild+4", false, current_card.getImage());
                current_card = new_card;
                System.out.println(bot.getName() + " draws 4 cards");
                return;

            }
            // If is +2 card, then add 2 cards to the deck
            else if (current_card.getSpecialMove().equals("+2") && current_card.getSkipped()) {
                bot.addCard(deck.deal());
                bot.addCard(deck.deal());
                Card new_card = new Card(current_card.getColor(), true, "+2", false, current_card.getImage());
                current_card = new_card;
                System.out.println(bot.getName() + " draws 2 cards");
                return;
            }
            else if(current_card.getSpecialMove().equals("skip") && current_card.getSkipped()){
                Card new_card = new Card(current_card.getColor(), true, "skip", false, current_card.getImage());
                current_card = new_card;
                System.out.println(bot.getName() + " got skipped");
                return;
            }
        }
        // Picks the first card in the hand that is playable, if not, then draw
        for(int i = 0; i < bot.getHand().size(); i++){
            if(bot.getHand().get(i).getSpecial()) {
                // For wild Cards
                if (bot.getHand().get(i).getSpecialMove().equals("wild") || bot.getHand().get(i).getSpecialMove().equals("wild+4")) {
                    // Picks a random color
                    bot_choice = (int) (Math.random() * 4);
                    if(bot_choice == 0){
                        bot.getHand().get(i).setColor("green");
                        current_card = bot.getHand().remove(i);
                        System.out.println(bot.getName() + " places " + current_card);
                        return;
                    }
                    else if(bot_choice == 1){
                        bot.getHand().get(i).setColor("red");
                        current_card = bot.getHand().remove(i);
                        System.out.println(bot.getName() + " places " + current_card);
                        return;
                    }
                    else if(bot_choice == 2){
                        bot.getHand().get(i).setColor("yellow");
                        current_card = bot.getHand().remove(i);
                        System.out.println(bot.getName() + " places " + current_card);
                        return;
                    }
                    else if(bot_choice == 3){
                        bot.getHand().get(i).setColor("blue");
                        current_card = bot.getHand().remove(i);
                        System.out.println(bot.getName() + " places " + current_card);
                        return;
                    }
                }
                else if (isSpecialValid(i, bot)) {
                    System.out.println(bot.getName() + " places " + bot.getHand().get(i));
                    current_card = bot.getHand().remove(i);
                    return;
                }
            }
            else if(isValid(i, bot)){
                System.out.println(bot.getName() + " places " + bot.getHand().get(i));
                current_card = bot.getHand().remove(i);
                return;
            }
        }
        bot.addCard(deck.deal());
        System.out.println(bot.getName() + " draws a card");
        if(bot.getHand().get(bot.getHand().size() - 1).getSpecial()) {
            // For wild Cards
            if (bot.getHand().get(bot.getHand().size() - 1).getSpecialMove().equals("wild") || bot.getHand().get(bot.getHand().size() - 1).getSpecialMove().equals("wild+4")) {
                // Picks a random color
                bot_choice = (int) (Math.random() * 4);
                if(bot_choice == 0){
                    bot.getHand().get(bot.getHand().size() - 1).setColor("green");
                    current_card = bot.getHand().remove(bot.getHand().size() - 1);
                    System.out.println(bot.getName() + " places " + current_card);
                    return;
                }
                else if(bot_choice == 1){
                    bot.getHand().get(bot.getHand().size() - 1).setColor("red");
                    current_card = bot.getHand().remove(bot.getHand().size() - 1);
                    System.out.println(bot.getName() + " places " + current_card);
                    return;
                }
                else if(bot_choice == 2){
                    bot.getHand().get(bot.getHand().size() - 1).setColor("yellow");
                    current_card = bot.getHand().remove(bot.getHand().size() - 1);
                    System.out.println(bot.getName() + " places " + current_card);
                    return;
                }
                else if(bot_choice == 3){
                    bot.getHand().get(bot.getHand().size() - 1).setColor("blue");
                    current_card = bot.getHand().remove(bot.getHand().size() - 1);
                    System.out.println(bot.getName() + " places " + current_card);
                    return;
                }
            }
            else if (isSpecialValid(bot.getHand().size() - 1, bot)) {
                System.out.println(bot.getName() + " places " + bot.getHand().get(bot.getHand().size() - 1));
                current_card = bot.getHand().remove(bot.getHand().size() - 1);
                return;
            }
        }
        else if(isValid(bot.getHand().size() - 1, bot)){
            System.out.println(bot.getName() + " places " + bot.getHand().get(bot.getHand().size() - 1));
            current_card = bot.getHand().remove(bot.getHand().size() - 1);
        }
    }
    // Prints the winner of the game
    public void printWinner(){
        if(playerOne.getHand().isEmpty()){
            System.out.println("You win!");
        }
        else if(botOne.getHand().isEmpty()){
            System.out.println("Bot 1 wins");
        }
        else if(botTwo.getHand().isEmpty()){
            System.out.println("Bot 2 wins");
        }
        else if(botThree.getHand().isEmpty()){
            System.out.println("Bot 3 wins");
        }
    }
    public void initializeImages(){
        // All red cards
        cards[0] = new ImageIcon("Uno cards/0Red.png").getImage();
        cards[1] = new ImageIcon("Uno cards/1Red.png").getImage();
        cards[2] = new ImageIcon("Uno cards/2Red.png").getImage();
        cards[3] = new ImageIcon("Uno cards/3Red.png").getImage();
        cards[4] = new ImageIcon("Uno cards/4Red.png").getImage();
        cards[5] = new ImageIcon("Uno cards/5Red.png").getImage();
        cards[6] = new ImageIcon("Uno cards/6Red.png").getImage();
        cards[7] = new ImageIcon("Uno cards/7Red.png").getImage();
        cards[8] = new ImageIcon("Uno cards/8Red.png").getImage();
        cards[9] = new ImageIcon("Uno cards/9Red.png").getImage();
        cards[10] = new ImageIcon("Uno cards/Red+2.png").getImage();
        cards[11] = new ImageIcon("Uno cards/RedReverse.png").getImage();
        cards[12] = new ImageIcon("Uno cards/RedSkip.png").getImage();
        // All blue cards
        cards[13] = new ImageIcon("Uno cards/0Blue.png").getImage();
        cards[14] = new ImageIcon("Uno cards/1Blue.png").getImage();
        cards[15] = new ImageIcon("Uno cards/2Blue.png").getImage();
        cards[16] = new ImageIcon("Uno cards/3Blue.png").getImage();
        cards[17] = new ImageIcon("Uno cards/4Blue.png").getImage();
        cards[18] = new ImageIcon("Uno cards/5Blue.png").getImage();
        cards[19] = new ImageIcon("Uno cards/6Blue.png").getImage();
        cards[20] = new ImageIcon("Uno cards/7Blue.png").getImage();
        cards[21] = new ImageIcon("Uno cards/8Blue.png").getImage();
        cards[22] = new ImageIcon("Uno cards/9Blue.png").getImage();
        cards[23] = new ImageIcon("Uno cards/Blue+2.png").getImage();
        cards[24] = new ImageIcon("Uno cards/BlueReverse.png").getImage();
        cards[25] = new ImageIcon("Uno cards/BlueSkip.png").getImage();
        // All green cards
        cards[26] = new ImageIcon("Uno cards/0Green.png").getImage();
        cards[27] = new ImageIcon("Uno cards/1Green.png").getImage();
        cards[28] = new ImageIcon("Uno cards/2Green.png").getImage();
        cards[29] = new ImageIcon("Uno cards/3Green.png").getImage();
        cards[30] = new ImageIcon("Uno cards/4Green.png").getImage();
        cards[31] = new ImageIcon("Uno cards/5Green.png").getImage();
        cards[32] = new ImageIcon("Uno cards/6Green.png").getImage();
        cards[33] = new ImageIcon("Uno cards/7Green.png").getImage();
        cards[34] = new ImageIcon("Uno cards/8Green.png").getImage();
        cards[35] = new ImageIcon("Uno cards/9Green.png").getImage();
        cards[36] = new ImageIcon("Uno cards/Green+2.png").getImage();
        cards[37] = new ImageIcon("Uno cards/GreenReverse.png").getImage();
        cards[38] = new ImageIcon("Uno cards/GreenSkip.png").getImage();
        // All Yellow Cards
        cards[39] = new ImageIcon("Uno cards/0Yellow.png").getImage();
        cards[40] = new ImageIcon("Uno cards/1Yellow.png").getImage();
        cards[41] = new ImageIcon("Uno cards/2Yellow.png").getImage();
        cards[42] = new ImageIcon("Uno cards/3Yellow.png").getImage();
        cards[43] = new ImageIcon("Uno cards/4Yellow.png").getImage();
        cards[44] = new ImageIcon("Uno cards/5Yellow.png").getImage();
        cards[45] = new ImageIcon("Uno cards/6Yellow.png").getImage();
        cards[46] = new ImageIcon("Uno cards/7Yellow.png").getImage();
        cards[47] = new ImageIcon("Uno cards/8Yellow.png").getImage();
        cards[48] = new ImageIcon("Uno cards/9Yellow.png").getImage();
        cards[49] = new ImageIcon("Uno cards/Yellow+2.png").getImage();
        cards[50] = new ImageIcon("Uno cards/YellowReverse.png").getImage();
        cards[51] = new ImageIcon("Uno cards/YellowSkip.png").getImage();
        // Other cards
        cards[52] = new ImageIcon("Uno cards/Wild.png").getImage();
        cards[53] = new ImageIcon("Uno cards/Wild+4.png").getImage();
    }
    public Player getPlayer(){
         return playerOne;
    }
    public Player getBotOne(){
         return botOne;
    }

    public Player getBotTwo(){
        return botTwo;
    }
    public Player getBotThree(){
        return botThree;
    }
    public Card getCurrent_card(){
         return current_card;
    }

    public static void main(String[] args) {
        Game game = new Game("Diego");
        game.unoGame();
    }
}
