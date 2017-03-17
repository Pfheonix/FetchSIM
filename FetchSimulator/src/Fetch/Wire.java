package Fetch;


/**
 * Created by dylan on 2/2/17.
 */
public class Wire{
    protected boolean current;

    public Wire(){
        this.current = false;
    }

    public Wire(boolean input){
        this.current = input;
    }

    public void setCurrent(boolean inCurrent){
        this.current = inCurrent;
    }

    public boolean getCurrent(){
        return this.current;
    }
}
