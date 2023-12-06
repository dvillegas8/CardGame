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
                 //removes the cards players already have from the deck
             }

             //player turn or bot
         }
    }
    public void playerTurn(){
         System.out.println("Current card is : " + current_card);
         System.out.println("In order to choose a card, you must input the index of that (the number next to it)");
         System.out.println("If you want to draw, just input 'draw'");
         System.out.println("Here are your cards:");
         System.out.println(playerOne);
         String choice = "";
         // While loop that loops as long as the user inputs a valid choice
         while(true){
             System.out.print("Please choose an index: ");
             choice = scan.nextLine();
             // draw card
             if(choice.equals("draw")){
                 playerOne.addCard(deck.deal());
                 break;
             }
             // checks if it is a special card
             if(playerOne.getHand().get(Integer.parseInt(choice)).getSpecial()){
                 if(playerOne.getHand().get(Integer.parseInt(choice)).equals("wild") || playerOne.getHand().get(Integer.parseInt(choice)).equals("wild+4")){
                     String chosen_color = "";
                     System.out.println("You placed a wild card, which color do you want? (red/yellow/blue/green)");
                     while(true){
                         chosen_color = scan.nextLine();
                         // Sets wild card as red and makes it the current card
                         if(chosen_color.equals("red")){
                             playerOne.getHand().get(Integer.parseInt(choice)).setColor("red");
                             current_card = playerOne.getHand().remove(Integer.parseInt(choice));
                             break;
                         }
                         // Sets wild card as yellow and makes it the current card
                         else if(chosen_color.equals("yellow")){
                             playerOne.getHand().get(Integer.parseInt(choice)).setColor("yellow");
                             current_card = playerOne.getHand().remove(Integer.parseInt(choice));
                             break;
                         }
                         // Sets wild card as blue and makes it the current card
                         else if(chosen_color.equals("blue")){
                             playerOne.getHand().get(Integer.parseInt(choice)).setColor("blue");
                             current_card = playerOne.getHand().remove(Integer.parseInt(choice));
                             break;
                         }
                         // Sets wild card as green and makes it the current card
                         else if(chosen_color.equals("green")){
                             playerOne.getHand().get(Integer.parseInt(choice)).setColor("green");
                             current_card = playerOne.getHand().remove(Integer.parseInt(choice));
                             break;
                         }
                         System.out.println("Error: Invalid color");
                     }
                 }
             }
         }

    }
    public boolean checkWin(){
         if(playerOne.getHand().size() == 0 || botOne.getHand().size() == 0 || botTwo.getHand().size() == 0 || botThree.getHand().size() == 0){
             return true;
         }

         return false;
    }



    public static void main(String[] args) {
        Game game = new Game("Diego");
        game.unoGame();
    }
}
