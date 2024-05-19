import java.util.LinkedList;
import java.util.List;

public class Game {
    
    Strategy strat1;
    Strategy strat2;
    List<int[]> history = new LinkedList<int[]>();
    int[][][] payoffMatrix;
    public Game(int[][][]payoffMatrix){
        this.payoffMatrix = payoffMatrix;
    }

    public Game(){
        int[][][] payoff = {{{50,50},{0,100}},{{100,0},{0,0}}};
        new Game(payoff);
    }

    public int[] run(Strategy strat1, Strategy strat2, LinkedList<int[]> history){
        int p1Move = strat1.play(history);
        int p2Move = strat2.play(history);
        history.add(new int[]{p1Move,p2Move});
        return payoffMatrix[p1Move][p2Move];

    }
    public int[] run(Strategy strat1, Strategy strat2){
        int p1Move = strat1.play();
        int p2Move = strat2.play();
        return payoffMatrix[p1Move][p2Move];
    }
    /**
     * 
     * @param strat1 
     * @param strat2
     * default numTrials = 1000
     * @return average payoff for both players
     */
    public int[] trial(Strategy strat1, Strategy strat2){
        return trial(strat1, strat2,1000);
    }

    /**
     * 
     * @param strat1
     * @param strat2
     * @param numTrials
     * @return average payoff for both players after `numTrials` trials 
     */
    public int[] trial(Strategy strat1, Strategy strat2, int numTrials){
        LinkedList<int[]> history = new LinkedList<int[]>();
        int[] payoffSum = {0,0};
        for (int i = 0; i < numTrials; i++) {
            int[] singlePayoff = run(strat1, strat2, history);
            payoffSum[0] += singlePayoff[0];
            payoffSum[1] += singlePayoff[1];
        }
        payoffSum[0] = payoffSum[0]/numTrials;
        payoffSum[1] = payoffSum[1]/numTrials;
        return payoffSum;
    }

}
