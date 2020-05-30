import javafx.util.Pair;

/**
 * @author Yoonseop Shin
 */
public class Time implements Runnable {

    private int hour;
    private int min;
    private int sec;
    private int timeFlag;

    /**
     * Default constructor
     */
    private Time() {

    }

    /**
     * 
     */
    public void pauseTime() {
        // TODO implement here
    }

    /**
     * 
     */
    public Pair<Pair<String, String>, String> getCurrentTime() {
        return new Pair<new Pair<Integer.toString(hour), Integer.toString(min)>, Integer.toString(sec)>;
    }

    /**
     * 
     */
    public void clearTime() {
        // TODO implement here
    }

    /**
     * 
     */
    public void run() {
        // TODO implement here
    }

    /**
     * 
     */


}