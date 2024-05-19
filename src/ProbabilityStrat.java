import java.util.LinkedList;
import java.util.Random;
/**Srategy that plays moves based off probability vector */
public class ProbabilityStrat implements Strategy{
    Random random = new Random();
    private double[] probabilities;
    public ProbabilityStrat(double[] probabilities){
        this.probabilities= probabilities;
    }

    
    
    @Override
    public int play() {
        //Generate a random number between 0 and 1.
        double rand = random.nextDouble();
        double cumulativeProbability = 0.0;
        
        //Iterate through the probability vector and keep a running sum of probabilities.
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            //Select the index where the running sum exceeds the random number.
            if (rand < cumulativeProbability) {
                return i;
            }
        }

        // In case of rounding errors, return the last index
        return probabilities.length - 1;
    }

    @Override
    public int play(LinkedList<int[]> history){
        return play();
    }
    /**Main function
     * testing probability selection works properly */
    public static void main(String[] args) {
        
        double[] probabilities = {0.7,0.25,0.05};
        ProbabilityStrat strat = new ProbabilityStrat(probabilities);
        int numTrials = 100;
        double[] results = {0,0,0};
        for (int i = 0; i < numTrials; i++) {
            int result = strat.play();
            results[result] = results[result]+1;
        }

        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]/numTrials);
        }
    }



    @Override
    public void switchPlayer() {
        //unneccessary method for probability strat
        
       }
}
