/**
 * Created by dylan on 2/4/17.
 */
package Fetch;

public class FullAdder {
    private HalfAdder add1, add2;
    private Wire inputA, inputB, inputC, outBit, carryBit;
    private OrGate o1;

    public FullAdder(boolean firstIn, boolean secIn, boolean thirdIn){
        this.inputA = new Wire(firstIn);
        this.inputB = new Wire(secIn);
        this.inputC = new Wire(thirdIn);
        this.add1 = new HalfAdder(inputB.getCurrent(), inputC.getCurrent());
        this.add2 = new HalfAdder(inputA.getCurrent(), add1.getOutputBit().getCurrent());
        this.o1 = new OrGate(add1.getCarryBit().getCurrent(), add2.getCarryBit().getCurrent());
        this.carryBit = new Wire(o1.getOutput());
        this.outBit = new Wire(add2.getOutputBit().getCurrent());
        this.Exec();
    }

    public void Exec(){
        this.add1.setInputs(inputB.getCurrent(),inputC.getCurrent());
        this.add2.setInputs(inputA.getCurrent(), add1.getOutputBit().getCurrent());
        this.o1.setInputA(add1.getCarryBit().getCurrent());
        this.o1.setInputB(add2.getCarryBit().getCurrent());
        this.outBit = add2.getOutputBit();
        this.carryBit.setCurrent(o1.getOutput());
    }

    public void setInputs(boolean inForA, boolean inForB, boolean inForC){
        this.inputA.setCurrent(inForA);
        this.inputB.setCurrent(inForB);
        this.inputC.setCurrent(inForC);
        this.Exec();
    }

    public boolean getInputA() {
        return inputA.getCurrent();
    }

    public void setInputA(boolean inForA) {
        this.inputA.setCurrent(inForA);
    }

    public boolean getInputB() {
        return inputB.getCurrent();
    }

    public void setInputB(boolean inForB) {
        this.inputB.setCurrent(inForB);
    }

    public boolean getInputC() {
        return inputC.getCurrent();
    }

    public void setInputC(boolean inForC) {
        this.inputC.setCurrent(inForC);
    }

    public boolean getCarryBit(){
        return this.carryBit.getCurrent();
    }

    public boolean getOutBit(){
        return this.outBit.getCurrent();
    }

    public void print(){
        System.out.printf("This Adder is a Full Adder, with inputs of %b, %b, and %b.\nIt has outputs of %b and carries %b.\n", inputA.getCurrent(), inputB.getCurrent(), inputC.getCurrent(), outBit.getCurrent(), carryBit.getCurrent());
    }
}
