package Model;

/**
 * @author Yoonseop Shin
 */
public abstract class Function {

    /**
     * Default constructor
     */
    public Function() {
    }

    /**
     * 
     */
    protected int mode;


    /**
     * 
     */
    public abstract void timeout();

    /**
     * 
     */
    public abstract void cancel();

    /**
     * 
     */
    public abstract void changeMode();

    /**
     * @param diff
     */
    public abstract void changeValue(int diff);

    /**
     * 
     */
    public abstract void changeType();

    /**
     * 
     */

}