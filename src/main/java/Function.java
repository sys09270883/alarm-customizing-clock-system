/**
 * @author Yoonseop Shin
 */
public abstract class Function {

    public Function() {
    }

    protected int mode;
    protected int fid;

    protected void changeMode() { }

    public abstract void changeValue(int diff);
    public abstract void changeType();
    protected int getMode() {
        return this.mode;
    }
}