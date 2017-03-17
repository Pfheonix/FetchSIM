/**
 * Created by Dylan Stahl on 2/1/2017.
 */
package Fetch;

public abstract class Gate{
    protected boolean inputA;
    protected boolean inputB;
    protected boolean output;

    public Gate(){

    }

    public Gate(boolean a, boolean b){
        this.inputA = a;
        this.inputB = b;
        this.execute();
    }

    public boolean getInputA(){
        return this.inputA;
    }

    public void setInputA(boolean inputA) {
        this.inputA = inputA;
        this.execute();
    }

    public boolean getInputB(){
        return this.inputB;
    }

    public void setInputB(boolean inputB) {
        this.inputB = inputB;
        this.execute();
    }

    public boolean getOutput(){
        return this.output;
    }

    public abstract void execute();

    public abstract void print();
}

class AndGate extends Gate{
    public void makeEqual(AndGate otherGate){
        this.setInputA(otherGate.getInputA());
        this.setInputB(otherGate.getInputB());
        this.execute();
    }

    public AndGate(boolean a, boolean b){
        super(a, b);
    }

    public void execute(){
        this.output = this.getInputA() && this.getInputB();
    }

    public void print(){
        System.out.printf("AND Gate, composed of inputs %b and %b, with output %b.\n",
                this.getInputA(), this.getInputB(), this.getOutput());
    }
    public boolean equals(AndGate otherGate){
        if(this.inputA == otherGate.getInputA()){
            if(this.inputB == otherGate.getInputB())
                return this.output == otherGate.getOutput();
        }
        return false;
    }
}

class OrGate extends Gate{
    public void makeEqual(OrGate otherGate){
            this.setInputA(otherGate.getInputA());
            this.setInputB(otherGate.getInputB());
            this.execute();
    }

    public OrGate(boolean a, boolean b){
        super(a, b);
    }

    public void execute(){
        this.output = this.getInputA() || this.getInputB();
    }

    public void print(){
        System.out.printf("OR Gate, composed of inputs %b and %b, with output %b.\n",
                this.getInputA(), this.getInputB(), this.getOutput());
    }
    public boolean equals(OrGate otherGate){
        if(this.inputA == otherGate.getInputA()){
            if(this.inputB == otherGate.getInputB())
                return this.output == otherGate.getOutput();
        }
        return false;
    }
}


class XorGate extends Gate{
    public void makeEqual(XorGate otherGate){
        this.setInputA(otherGate.getInputA());
        this.setInputB(otherGate.getInputB());
        this.execute();
    }

    public XorGate(boolean a, boolean b){
        super(a, b);
    }

    public void execute(){
        if(this.inputA == this.inputB){
            this.output = false;
        } else {
            this.output = true;
        }
    }

    public void print(){
        System.out.printf("XOR Gate, composed of inputs %b and %b, with output %b.\n",
                this.getInputA(), this.getInputB(), this.getOutput());
    }
    public boolean equals(XorGate otherGate){
        if(this.inputA == otherGate.getInputA()){
            if(this.inputB == otherGate.getInputB())
                return this.output == otherGate.getOutput();
        }
        return false;
    }
}

class NotGate extends Gate{
    private boolean input;
    private boolean output;

    public NotGate(boolean inInput){
        this.setInput(inInput);
    }

    public void setInput(boolean inInput){
        this.input = inInput;
        this.execute();
    }
    public void setInputA(boolean inInput){
        this.input = inInput;
        this.execute();
    }
    public void setInputB(boolean inInput){
        this.input = inInput;
        this.execute();
    }

    public boolean getInput(){
        return this.input;
    }
    public boolean getInputA(){
        return this.input;
    }
    public boolean getInputB(){
        return this.input;
    }

    public boolean getOutput(){
        return this.output;
    }

    public void print(){
        System.out.printf("NOT Gate, composed of input %b, with output %b.\n",
                this.input, this.output);
    }

    public void makeEqual(NotGate inGate){
        setInput(inGate.getInput());
        this.execute();
    }
    public boolean equals(NotGate otherGate){
        if(this.input == otherGate.getInput())
            return this.output == otherGate.getOutput();
        return false;
    }

    public void execute(){
        this.output = !this.input;
    }
}