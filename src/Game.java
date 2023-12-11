// Diego Villegas December 11th 2023
import java.util.Scanner;
public class Game {
     // Instance variables
     Player playerOne;
     Player botOne;
     Player botTwo;
     Player botThree;
     Deck deck;
     int[] ranks;
     String[] colors;
     Card current_card;
     Scanner scan;
     public Game(String playerName){
         playerOne = new Player(playerName);
         botOne = new Player("Bot 1");
         botTwo = new Player("Bot 2");
         botThree = new Player("Bot 3");
         colors = new String[]{"red", "yellow", "blue", "green"};
         ranks = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
         deck = new Deck(ranks, colors);
         deck.shuffle();
         while(true){
             current_card = deck.deal();
             if(!current_card.getSpecial()){
                 break;
             }
         }
         scan = new Scanner(System.in);

         //System.out.println(deck.getCardsLeft());
         //System.out.println(deck.getCards());
     }
    public String printInstructions(){
        String instructions = "Welcome to Uno";
        return instructions;
    }
    public void unoGame(){
        int turn = 0;
        boolean skip = false;
        boolean already_skipped = false;
        String direction = "right";
        deck.shuffle();
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
                if(current_card.getSpecialMove().equals("reverse") && direction.equals("right")){
                    direction = "left";
                }
                else if(current_card.getSpecialMove().equals("reverse") && direction.equals("left")){
                    direction = "right";
                    turn++;
                    if(turn > 3){
                        turn = 0;
                    }
                }
            }
            // Figures out if a turn is going to be skipped
            if(current_card.getSpecial()){
                if(current_card.getSpecialMove().equals("skip") && !already_skipped){
                    skip = true;
                }
            }
            already_skipped = false;
            // Turns
            if(direction.equals("right")){
                // Player's turn
                if(turn == 0){
                    // Checks if turns should be skipped
                    if(!skip){
                        System.out.println(playerOne.getName() + "'s turn: ");
                        playerTurn();
                    }
                    else{
                        System.out.println(playerOne.getName() + " got skipped");
                        skip = false;
                        already_skipped = true;
                    }
                    turn++;
                }
                // Bot 1 turn
                else if(turn == 1){
                    if(!skip){
                        System.out.println(botOne.getName() + "'s turn: ");
                        botTurn(botOne);
                    }
                    else{
                        System.out.println(botOne.getName() + " got skipped");
                        skip = false;
                        already_skipped = true;
                    }
                    turn++;
                }
                // Bot 2 turn
                else if(turn == 2){
                    if(!skip){
                        System.out.println(botTwo.getName() + "'s turn: ");
                        botTurn(botTwo);
                    }
                    else{
                        System.out.println(botTwo.getName() + " got skipped");
                        skip = false;
                        already_skipped = true;
                    }
                    turn++;
                }
                // Bot 3 turn
                else if(turn == 3){
                    if(!skip){
                        System.out.println(botThree.getName() + "'s turn: ");
                        botTurn(botThree);
                    }
                    else{
                        System.out.println(botThree.getName() + " got skipped");
                        skip = false;
                        already_skipped = true;
                    }
                    turn = 0;
                }
                else {
                    System.out.println("Error");
                }
            }
            else if(direction.equals("left")){
                // reset turn so that it doesn't equal a negative number
                if(turn == 0){
                    turn = 4;
                }
                turn--;
                // Players turn
                if(turn == 0){
                    if(!skip){
                        System.out.println(botOne.getName() + "'s turn: ");
                        playerTurn();
                    }
                    else{
                        System.out.println(botOne.getName() + " got skipped");
                        skip = false;
                        already_skipped = true;
                    }
                }
                // Bot 1's turn
                else if(turn == 1){
                    if(!skip){
                        System.out.println(botOne.getName() + "'s turn: ");
                        botTurn(botOne);
                    }
                    else{
                        System.out.println(botOne.getName() + " got skipped");
                        skip = false;
                        already_skipped = true;
                    }
                }
                // Bot 2's turn
                else if(turn == 2){
                    if(!skip){
                        System.out.println(botTwo.getName() + "'s turn: ");
                        botTurn(botTwo);
                    }
                    else{
                        System.out.println(botTwo.getName() + " got skipped");
                        skip = false;
                        already_skipped = true;
                    }
                }
                // Bot 3's turn
                else if(turn == 3){
                    if(!skip){
                        System.out.println(botThree.getName() + "'s turn: ");
                        botTurn(botThree);
                    }
                    else{
                        System.out.println(botThree.getName() + " got skipped");
                        skip = false;
                        already_skipped = true;
                    }
                }
            }
        }
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
    public void playerTurn() {
        System.out.println("Current card is : " + current_card);
        if(current_card.getSpecial()){
            // If the card is + 4, then add +4 cards to your hand
            if(current_card.getSpecialMove().equals("wild+4")){
                for(int i = 0; i < 3; i++){
                    playerOne.addCard(deck.deal());
                }
            }
            // If is +2 card, then add 2 cards to the deck
            else if(current_card.getSpecialMove().equals("+2")){
                playerOne.addCard(deck.deal());
                playerOne.addCard(deck.deal());
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
                    // Attempts to see if that card that was just drawn is able to be played
                    if(playerOne.getHand().get(playerOne.getHand().size() - 1).getSpecial()) {
                        if (playerOne.getHand().get(playerOne.getHand().size() - 1).getSpecialMove().equals("wild") || playerOne.getHand().get(playerOne.getHand().size() - 1).getSpecialMove().equals("wild+4")) {
                            System.out.println("You drew a wild card and placed it");
                            wildMove(playerOne.getHand().size() - 1);
                        } else if (isSpecialValid(playerOne.getHand().size() - 1, playerOne)) {
                            System.out.println("You drew a " + playerOne.getHand().get(playerOne.getHand().size() - 1) + " and placed it");
                            current_card = playerOne.getHand().get(playerOne.getHand().size() - 1);
                        }
                    }
                    else if(isValid(playerOne.getHand().size() - 1, playerOne)){
                        System.out.println("You drew a " + playerOne.getHand().get(playerOne.getHand().size() - 1) + " and placed it");
                        current_card = playerOne.getHand().get(playerOne.getHand().size() - 1);
                    }
                    break;
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
            if (current_card.getSpecialMove().equals("wild+4")) {
                for (int i = 0; i < 3; i++) {
                    bot.addCard(deck.deal());
                }
            }
            // If is +2 card, then add 2 cards to the deck
            else if (current_card.getSpecialMove().equals("+2")) {
                bot.addCard(deck.deal());
                bot.addCard(deck.deal());
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
    }


    public static void main(String[] args) {
        Game game = new Game("Diego");
        game.unoGame();
    }
}
