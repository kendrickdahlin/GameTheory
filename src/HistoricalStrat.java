import java.util.LinkedList;

public class HistoricalStrat implements Strategy{
    int player;

    public HistoricalStrat(int player){
        this.player = player;
    }
    /*if no history, default to 0 */
    @Override
    public int play() {
        return this.player;
    }

    /**does what opponent did last time */
    @Override
    public int play(LinkedList<int[]> history) {
        if (history == null || history.isEmpty()){
            return play();
        }
        return history.getLast()[other()];
    }

    private int other(){
        return this.player == Strategy.P1 ? Strategy.P2: Strategy.P1;
    }
    @Override
    public void switchPlayer() {
        this.player = other();
    }

    
    public static void main(String[] args) {
        LinkedList<int[]> history = new LinkedList<>();
        
        history.add(new int[] {1,0});
        history.add(new int[] {1,1});
        HistoricalStrat strat = new HistoricalStrat(Strategy.P1);
        System.out.println(strat.play(history));

    }
    
    
}
