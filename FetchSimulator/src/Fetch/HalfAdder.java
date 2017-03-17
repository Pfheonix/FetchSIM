/**
 * Created by dylan on 2/2/17.
 */
package Fetch;

public class HalfAdder {
    public AndGate a1, a2;
    public OrGate o1;
    public NotGate n1;
    public Wire inputA, inputB, carryBit, outputBit;
    public String name;

    public HalfAdder(){
        this.inputA = new Wire();
        this.inputB = new Wire();
        this.o1 = new OrGate(inputA.getCurrent(), inputB.getCurrent());
        this.a1 = new AndGate(inputA.getCurrent(), inputB.getCurrent());
        this.n1 = new NotGate(a1.getOutput());
        this.a2 = new AndGate(inputA.getCurrent(), n1.getOutput());
        this.carryBit = new Wire(a1.getOutput());
        this.outputBit = new Wire(a2.getOutput());
    }

    public HalfAdder(boolean inForA, boolean inForB){
        this.inputA = new Wire(inForA);
        this.inputB = new Wire(inForB);
        this.o1 = new OrGate(inputA.getCurrent(), inputB.getCurrent());
        this.a1 = new AndGate(inputA.getCurrent(), inputB.getCurrent());
        this.n1 = new NotGate(a1.getOutput());
        this.a2 = new AndGate(o1.getOutput(), n1.getOutput());
        this.carryBit = new Wire(a1.getOutput());
        this.outputBit = new Wire(a2.getOutput());
    }

    public HalfAdder(String argName){
        this();
        this.name = argName;
    }

    public void Exec(){
        this.o1.setInputA(inputA.getCurrent());
        this.o1.setInputB(inputB.getCurrent());
        this.a1.setInputA(inputA.getCurrent());
        this.a1.setInputB(inputB.getCurrent());
        this.n1.setInput(a1.getOutput());
        this.a2.setInputA(o1.getOutput());
        this.a2.setInputB(n1.getOutput());
        this.carryBit.setCurrent(a1.getOutput());
        this.outputBit.setCurrent(a2.getOutput());
    }

    public void setInputs(boolean argA, boolean argB){
        this.inputA.setCurrent(argA);
        this.inputB.setCurrent(argB);
        this.Exec();
    }

    public AndGate getA1() {
        return a1;
    }

    public void setA1(AndGate a1) {
        this.a1 = a1;
    }

    public AndGate getA2() {
        return a2;
    }

    public void setA2(AndGate a2) {
        this.a2 = a2;
    }

    public OrGate getO1() {
        return o1;
    }

    public void setO1(OrGate o1) {
        this.o1 = o1;
    }

    public NotGate getN1() {
        return n1;
    }

    public void setN1(NotGate n1) {
        this.n1 = n1;
    }

    public Wire getInputA() {
        return inputA;
    }

    public void setInputA(Wire inputA) {
        this.inputA = inputA;
    }

    public Wire getInputB() {
        return inputB;
    }

    public void setInputB(Wire inputB) {
        this.inputB = inputB;
    }

    public Wire getCarryBit() {
        return carryBit;
    }

    public void setCarryBit(Wire carryBit) {
        this.carryBit = carryBit;
    }

    public Wire getOutputBit() {
        return outputBit;
    }

    public void setOutputBit(Wire outputBit) {
        this.outputBit = outputBit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void print(){
        System.out.printf("This Adder is a Half Adder, with inputs of %b and %b.\nIt has outputs of %b and carries %b.\n", this.inputA.getCurrent(), this.inputB.getCurrent(), this.outputBit.getCurrent(), this.carryBit.getCurrent());
    }
}
