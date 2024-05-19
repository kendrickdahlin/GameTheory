import java.util.LinkedList;

/**Simple interface for a Strategy */
public interface Strategy {
    public static final int P1 = 0;
    public static final int P2 = 1;
    
    public int play();
    public int play(LinkedList<int[]> history);
    public void switchPlayer();
    
}
