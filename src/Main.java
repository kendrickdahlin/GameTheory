import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /**main function to compare different strategies for games */
    public static void main(String[] args) {
        
        //******EXAMPLE GAMES*******
        ArrayList<String> gameNames = new ArrayList<String>();
        ArrayList<Game> games = new ArrayList<Game>();

        //SHARE OR STEAL
        //The first move represents sharing, the second move represents stealing. On every move, there is 100 points in the pot. If both players decide to share, they each recieve 50. If one player steals and the other shares, the stealing player recieves 100, the other 0. If both players steal, they both recieve 0. 
        int[][][]shareOrStealPayoff = {{{50,50},{0,100}},{{100,0},{0,0}}};
        Game shareOrSteal = new Game(shareOrStealPayoff);
        gameNames.add("Share or Steal");
        games.add(shareOrSteal);

        //Prisoners Dilemma
        //the first move represents cooperating, the second defecting. If both players cooperate they both recieve 3 points. If one defects and the other cooperates, the defecting players recieves 5, the other 0. If both defect, they both receieve 1 point. 
        int[][][]prisonerDilemmaPayoff = {{{3,3},{0,5}},{{5,0},{1,1}}};
        Game prisonerDilemma = new Game(prisonerDilemmaPayoff);
        gameNames.add("Prisoner's Dilemma");
        games.add(prisonerDilemma);

        
        //********STRATEGIES ************/
        //strategies for two player games with 2 moves each
        
        Strategy cooperative = new ProbabilityStrat(new double[]{1, 0});
        Strategy greedy = new ProbabilityStrat(new double[]{0, 1});
        Strategy mixed = new ProbabilityStrat(new double[]{0.5, 0.5});
        Strategy mirror = new HistoricalStrat(0);
        ArrayList<Strategy> strategies = new ArrayList<Strategy>();
        strategies.add(cooperative);
        strategies.add(greedy);
        strategies.add(mixed);
        strategies.add(mirror);

        ArrayList<String> strategyNames = new ArrayList<String>();
        strategyNames.add("Cooperative - always chooses move 1");
        strategyNames.add("Greedy - always chooses move 2");
        strategyNames.add("Mixed - chooses equally between two moves");
        strategyNames.add("Mirror - picks what opponent did last");

        


        //**********USER INTERFACE********** */
        //SELECTING GAME
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Game Theory tournament. Please enter a number to choose a game or to create your own. ");
        int i = 0;
        for (i = 0; i < gameNames.size(); i++) {
            System.out.println(i+1+". "+gameNames.get(i));
        }
        System.out.println(i+1+". Create your own");
        int gameNum = -1;
        while(true){
            try{
                gameNum = input.nextInt()-1;
                if (gameNum>=0 && gameNum<=games.size()){
                    break;
                }
                System.out.println("Game number selection must be between 1 and "+games.size()+1);
            }catch(Exception e){
                System.out.println("Game number selection must be between 1 and "+games.size()+1);
            }
        }
        
        //MAKE YOUR OWN GAME
        if (gameNum == games.size()){
            System.out.println("Game Creation not yet supported. Defaulting to option 1.");
            gameNum = 0;
        }
        Game game = games.get(gameNum);

        
        //SELECTING STRATEGY 1
        Strategy strat1;

        System.out.println("\nSelect the first strategy: ");
        for (i = 0; i < strategyNames.size(); i++) {
            System.out.println(i+1+". "+strategyNames.get(i));
        }
        System.out.println(i+1+". Create your own");
        int strat1Num = -1;
        while(true){
            try{
                strat1Num = input.nextInt()-1;
                if (strat1Num>=0 && strat1Num<=strategies.size()){
                    break;
                }
                System.out.println("Strategy number selection must be between 1 and "+strategies.size()+1);
            }catch(Exception e){
                System.out.println("Strategy number selection must be between 1 and "+strategies.size()+1);
            }
        }

        //MAKE YOUR OWN STRATEGY 1
        if (strat1Num == strategies.size()){
            System.out.print("Selecting strategy not supported yet. Defaulting to option 1.");
            strat1 = strategies.get(strat1Num); 
        }else{
            strat1 = strategies.get(strat1Num);
        }

        //SELECTING STRATEGY 2
        Strategy strat2;
        System.out.println("\nSelect the second strategy: ");
        for (i = 0; i < strategyNames.size(); i++) {
            System.out.println(i+1+". "+strategyNames.get(i));
        }
        System.out.println(i+1+". Create your own");
        int strat2Num = -1;
        while(true){
            try{
                strat2Num = input.nextInt()-1;
                if (strat2Num>=0 && strat2Num<=strategies.size()){
                    break;
                }
                System.out.println("Strategy number selection must be between 1 and "+strategies.size()+1);
            }catch(Exception e){
                System.out.println("Strategy number selection must be between 1 and "+strategies.size()+1);
            }
        }
        //MAKE YOUR OWN STRATEGY 2

        if (strat2Num == strategies.size()){
            System.out.println("Strategy creation not supported yet. Defaulting to option 1.");
            strat2 = strategies.get(0);
            
        }else if (strategyNames.get(strat2Num).equals("mirror")){
            strat2 = new HistoricalStrat(1);
        }else{
            strat2 = strategies.get(strat1Num);
        }

        int[] result = game.trial(strat1, strat2);
        System.out.println("Player 1 sum: "+ result[0]);
        System.out.println("Player 2 sum: "+ result[1]);
    }
}
