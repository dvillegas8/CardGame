public class Game {
     Player playerOne;
     Player botOne;
     Player botTwo;
     Player botThree;
     Deck deck;
     int[] ranks;
     String[] colors;
     Card current_card;
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
         }

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
         System.out.println(playerOne);
         System.out.println(botOne);
         System.out.println(botTwo);
         System.out.println(botThree);
         System.out.println(current_card);
    }



}
