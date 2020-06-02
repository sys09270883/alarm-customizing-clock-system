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
    protected int fid;
    protected long lastOperateTime;

    protected void changeMode() {

    }

    /**
     * @param diff
     */
    public abstract void changeValue(int diff);

    /**
     * 
     */
    public abstract void changeType();

    protected int getMode() {
        return this.mode;
    }

    protected long getLastOperateTime() {
        return this.lastOperateTime;
    }
}