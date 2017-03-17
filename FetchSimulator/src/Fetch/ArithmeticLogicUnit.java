/**
 * Created by dylan on 2/5/17.
 */
package Fetch;

public class ArithmeticLogicUnit {
    protected FullAdder add1, add2, add3, add4;
    protected XorGate xor1, xor2, xor3, xor4;
    protected Wire opSig, a0, a1, a2, a3, b0, b1, b2, b3;
    protected int inputA, inputB, output;

    public ArithmeticLogicUnit(int inForA, int inForB, boolean operation, boolean carryInBit){
        this.inputA = inForA;
        this.inputB = inForB;
        a0 = new Wire(false);
        a1 = new Wire(false);
        a2 = new Wire(false);
        a3 = new Wire(false);
        b0 = new Wire(false);
        b1 = new Wire(false);
        b2 = new Wire(false);
        b3 = new Wire(false);

        intToBoolSet(this.inputA, this.inputB);

        opSig = new Wire(operation);
        xor1 = new XorGate(b3.getCurrent(), opSig.getCurrent());
        xor2 = new XorGate(b2.getCurrent(), opSig.getCurrent());
        xor3 = new XorGate(b1.getCurrent(),opSig.getCurrent());
        xor4 = new XorGate(b0.getCurrent(), opSig.getCurrent());
        add1 = new FullAdder(false, a3.getCurrent(), xor1.getOutput());
        add2 = new FullAdder(add1.getCarryBit(), a2.getCurrent(), xor2.getOutput());
        add3 = new FullAdder(add2.getCarryBit(), a1.getCurrent(), xor3.getOutput());
        add4 = new FullAdder(add3.getCarryBit(), a0.getCurrent(), xor4.getOutput());

        if(opSig.getCurrent()){
            this.subOutputToInt();
        } else {
            this.outputToInt();
        }
    }

    public void intToBoolSet(int numA, int numB){
        if(numA > 15 || numA < 0){
            System.out.printf("Number must be a positive, four bit number: 0 through 15.\n");
            return;
        }
        a0.setCurrent(false);
        a1.setCurrent(false);
        a2.setCurrent(false);
        a3.setCurrent(false);
        b0.setCurrent(false);
        b1.setCurrent(false);
        b2.setCurrent(false);
        b3.setCurrent(false);
        if(numA % 8 != numA) {
            a0.setCurrent(true);
            numA = numA % 8;
        }
        if(numA % 4 != numA) {
            a1.setCurrent(true);
            numA = numA % 4;
        }
        if(numA % 2 != numA){
            a2.setCurrent(true);
            numA = numA % 2;
        }
        if(numA % 1 != numA) {
            a3.setCurrent(true);
        }
        if(numB > 15 || numB < 0){
            System.out.printf("Number must be a positive, four bit number: 0 through 15.\n");
            return;
        }
        if(numB % 8 != numB) {
            b0.setCurrent(true);
            numB = numB % 8;
        }
        if(numB % 4 != numB) {
            b1.setCurrent(true);
            numB = numB % 4;
        }
        if(numB % 2 != numB){
            b2.setCurrent(true);
            numB = numB % 2;
        }
        if(numB % 1 != numB) {
            b3.setCurrent(true);
        }
    }


    public void outputToInt(){
        output += add1.getOutBit() ? 1 : 0;
        output += add2.getOutBit() ? 2 : 0;
        output += add3.getOutBit() ? 4 : 0;
        output += add4.getOutBit() ? 8 : 0;
        output += add4.getCarryBit() ? 16 : 0;
    }

    public void subOutputToInt(){
        output -= add1.getOutBit() ? 0 : 1;
        output -= add2.getOutBit() ? 0 : 2;
        output -= add3.getOutBit() ? 0 : 4;
        output -= add4.getOutBit() ? 0 : 8;
        output += add4.getCarryBit() ? 16 : 0;
    }

    private void execute(){
        this.output = 0;
        intToBoolSet(inputA, inputB);
        xor1.setInputA(b3.getCurrent());
        xor1.setInputB(opSig.getCurrent());
        xor2.setInputA(b2.getCurrent());
        xor2.setInputB(opSig.getCurrent());
        xor3.setInputA(b1.getCurrent());
        xor3.setInputB(opSig.getCurrent());
        xor4.setInputA(b0.getCurrent());
        xor4.setInputB(opSig.getCurrent());
        add1.setInputs(false, a3.getCurrent(), xor1.getOutput());
        add2.setInputs(add1.getCarryBit(), a2.getCurrent(), xor2.getOutput());
        add3.setInputs(add2.getCarryBit(), a1.getCurrent(), xor3.getOutput());
        add4.setInputs(add3.getCarryBit(), a0.getCurrent(), xor4.getOutput());
        this.subOutputToInt();

    }

    public void setInputs(int inForA, int inForB, boolean operation, boolean carryInBit){
        this.inputA = inForA;
        this.inputB = inForB;
        this.opSig.setCurrent(operation);
        this.execute();
    }

    public int getOutput(){
        return this.output;
    }

    public void print(){
        System.out.printf("This is an Arithmetic Logic Unit. It takes inputs of %d and %d. It returns %d.\n", inputA, inputB, output);
    }

    public void printBinaryOutput(){
        System.out.printf("%b %b %b %b %b\n", add1.getOutBit(), add2.getOutBit(), add3.getOutBit(), add4.getOutBit(), add4.getCarryBit());
    }
}
