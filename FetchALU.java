/**
 * Created by dylan on 2/12/17.

 */
package Fetch;

public class FetchALU {
    public ArithmeticLogicUnit alu1, alu2;
    public boolean[] inputA, inputB, output;
    public int inputAlow, inputAhigh, inputBlow, inputBhigh;
    public String name;
    public int fetchWordSize = 8;
    public int fetchMaxNumber = (int) Math.pow(2, fetchWordSize);
    private OrGate orGates[] = new OrGate[8];
    private AndGate andGates[] = new AndGate[8];
    private XorGate xorGates[] = new XorGate[8];
    private NotGate notGates[] = new NotGate[8];


    public FetchALU(boolean[] inputA, boolean[] inputB, boolean[] operation){
        this.inputA = inputA;
        this.inputB = inputB;
        inputPrep(inputA, inputB);
        System.out.printf("%d, %d, %d, %d.\n", inputAhigh, inputAlow, inputBhigh, inputBlow);
        parseOpCode(operation);
    }

    public void inputPrep(boolean[] inputA, boolean[] inputB){

        inputAlow = inputAhigh = inputBlow = inputBhigh = 0;

        for(int i = 8, j = 0; i >= 1; i /= 2, ++j){
            if(inputA[j]){
                inputAhigh += i;
            }
            if(inputB[j]){
                inputBhigh += i;
            }
        }
        for(int i = 8, j = 4; i >= 1; i /= 2, ++j){
            if(inputA[j]){
                inputAlow += i;
            }
            if(inputB[j]){
                inputBlow += i;
            }
        }

        /* Old code. To be deleted upon completion.
        this.inputAlow = inputA % 128;
        this.inputBlow = inputB % 128;
        for(int i = 4; i < 7; ++i){
            this.inputAlow = (int)(this.inputAlow % java.lang.Math.pow(2, i));
            this.inputBlow = (int)(this.inputBlow % java.lang.Math.pow(2, i));
        }
        this.inputAhigh = inputA - this.inputAlow;
        this.inputBhigh = inputB - this.inputBlow;
        */
    }

    public void parseOpCode(boolean[] opCode){
        //Check for well formed opCode.
        if(opCode.length > 4){
            System.out.printf("Invalid opCode. Please try again.\n");
            //Halt
        }
        //Check if the ALU handles that operation.
        if(opCode[0]){
            System.out.printf("Non ALU opcode encountered.\n");
            //Halt
        }
        //Check for second bit set.
        if(opCode[1]){
            //01__ Check last two bits.
            if(opCode[2]){
                //011_ Check last Bit
                if(opCode[3]){
                    //0111 MOV - Not performed by ALU, should never be sent to ALU. If it is, we'll simply ignore it.
                    return;
                } else {
                    //0110 DIV
                }
            } else {
                //010_
                if(opCode[3]){
                    //0101 MUL
                } else {
                    //0100 SUB
                    alu1 = new ArithmeticLogicUnit(this.inputAlow, this.inputBlow, true, true);
                    alu2 = new ArithmeticLogicUnit(this.inputAhigh, this.inputBhigh, true,
                            (this.alu1.getOutput() > 15));
                    getBinaryOutput();
                }
            }
        } else {
            //00__ check last two bits.
            if(opCode[2]){
                //001_ check last bit.
                if(opCode[3]){
                    //0011 ADD
                    alu1 = new ArithmeticLogicUnit(this.inputAlow, this.inputBlow, false, false);
                    alu2 = new ArithmeticLogicUnit(this.inputAhigh, this.inputBhigh, false,
                            (this.alu1.getOutput() > 15));
                    getBinaryOutput();
                } else {
                    //0010 XOR
                    doXorOp(inputA, inputB);
                }
            } else {
                //000_ check last bit.
                if(opCode[3]){
                    //0001 OR
                    doOrOp(inputA, inputB);
                } else {
                    //0000 AND
                    doAndOp(inputA, inputB);
                }
            }
        }
    }

    public void setInputs(boolean[] inForA, boolean[] inForB, boolean[] operation){
        this.inputA = inForA;
        this.inputB = inForB;
        inputPrep(inputA, inputB);
        parseOpCode(operation);
    }

    public void print(){
        System.out.printf("This is an Arithmetic Logic Unit. It takes inputs of %d and %d. It returns %d.\n",
                inputA, inputB, output);
    }

    public void getBinaryOutput(){

        output[0] = this.alu1.add1.getOutBit();
        output[1] = this.alu1.add2.getOutBit();
        output[2] = this.alu1.add3.getOutBit();
        output[3] = this.alu1.add4.getOutBit();
        output[4] = this.alu2.add1.getOutBit() || this.alu1.add4.getCarryBit();
        output[5] = this.alu2.add2.getOutBit();
        output[6] = this.alu2.add3.getOutBit();
        output[7] = this.alu2.add4.getOutBit();
        output[8] = this.alu2.add4.getCarryBit();
    }



    protected void doOrOp(boolean[] registerA, boolean[] registerB){

        for(int i = 0; i < 8; ++i){
            orGates[i].setInputA(registerA[i]);
            orGates[i].setInputB(registerB[i]);
            output[i] = orGates[i].getOutput();
        }
    }

    protected void doAndOp(boolean[] registerA, boolean[] registerB){

        for(int i = 0; i < 8; ++i){
            andGates[i].setInputA(registerA[i]);
            andGates[i].setInputB(registerB[i]);
            output[i] = andGates[i].getOutput();
        }
    }

    protected void doXorOp(boolean[] registerA, boolean[] registerB){

        for(int i = 0; i < 8; ++i){
            xorGates[i].setInputA(registerA[i]);
            xorGates[i].setInputB(registerB[i]);
            output[i] = xorGates[i].getOutput();
        }
    }

    protected void doNegOp(boolean[] registerA, boolean[] registerB){
        for(int i = 0; i < 8; ++i){
            notGates[i] = new NotGate(registerA[i]);
            registerA[i] = notGates[i].getOutput();
        }
        for(int i = 0; i < 8; ++i){

            notGates[i] = new NotGate(registerB[i]);
            registerB[i] = notGates[i].getOutput();
        }
        for(int i = 0; i < 8; ++i){
            notGates[i] = new NotGate(output[i]);
            output[i] = notGates[i].getOutput();
        }
    }
}
