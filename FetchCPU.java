package Fetch;

/**
 * Created by Pfheonix on 3/15/2017.
 */
public class FetchCPU {
    private FetchRegister[] register;
    private FetchRegister pc, sp;
    private FetchALU dually;
    private FetchMem mainMem;
    private FetchPSW psw;
    private boolean[] temp = {false, false, false, false};

    public FetchCPU(){
        register = new FetchRegister[4];
        pc = new FetchRegister();
        sp = new FetchRegister();
        dually = new FetchALU(byteToBool(mainMem.getByte(0)), byteToBool(mainMem.getByte(0)), temp);
        mainMem = new FetchMem();
        psw = new FetchPSW();
    }

    public void parseAndExec(int address){
        byte op, srcMode, dstMode, source, dest;
        if(address % 2 != 0){
            System.out.printf("Trap state encountered: non-even address requested.\nEven address required for instructions.\n");
            //Place HALT
        }
        op = this.mainMem.getByte(address);
        temp = byteToBool(op);
        srcMode = (byte)(this.mainMem.getByte(address+1) & (byte)192);
        srcMode = (byte)(srcMode >> 6);
        dstMode = (byte)(this.mainMem.getByte(address+1) & (byte)12);
        dstMode = (byte)(srcMode >> 2);
        source = (byte)(this.mainMem.getByte(address+1) & (byte)48);
        source = (byte)(srcMode >> 4);
        dest = (byte)(this.mainMem.getByte(address+1) & (byte)3);

        switch(op){
            case 0:
                dually.setInputs(byteToBool(source), byteToBool(dest), temp);
                break;
            case 1:
                dually.setInputs(byteToBool(source), byteToBool(dest), temp);
                break;
            case 2:
                dually.setInputs(byteToBool(source), byteToBool(dest), temp);
                break;
            case 3:
                dually.setInputs(byteToBool(source), byteToBool(dest), temp);
                break;
            case 4:
                dually.setInputs(byteToBool(source), byteToBool(dest), temp);
                break;
            case 5:
                dually.setInputs(byteToBool(source), byteToBool(dest), temp);
                break;
            case 6:
                dually.setInputs(byteToBool(source), byteToBool(dest), temp);
                break;
            case 7:
                switch(srcMode){
                    case 0:
                        //Source uses Register mode.
                        switch(dstMode){
                            case 0:
                                register[dest].setBits(register[source].getBits());
                                break;
                            case 1:
                                mainMem.replaceByte(boolToByte(register[dest].getBits()), boolToByte(register[source].getBits()));
                                break;
                            case 2:
                                mainMem.replaceByte(mainMem.getByte(address + 2), boolToByte(register[source].getBits()));
                                break;
                            case 3:
                                mainMem.replaceByte(mainMem.getByte(mainMem.getByte(address + 2)), boolToByte(register[source].getBits()));
                                break;
                            default:
                                System.out.printf("Something went so wrong, man.\n");
                        }
                        break;
                    case 1:
                        //Source uses Deferred mode.
                        switch(dstMode){
                            case 0:
                                //Register mode.
                                register[dest].setBits(byteToBool(mainMem.getByte(boolToByte(register[source].getBits()))));
                                break;
                            case 1:
                                //Deferred mode.
                                mainMem.replaceByte(boolToByte(register[dest].getBits()), mainMem.getByte(boolToByte(register[source].getBits())));
                                break;
                            case 2:
                                //Immediate mode.
                                mainMem.replaceByte(mainMem.getByte(address + 2), mainMem.getByte(boolToByte(register[source].getBits())));
                                break;
                            case 3:
                                //Absolute mode.
                                mainMem.replaceByte(mainMem.getByte(mainMem.getByte(address + 2)), mainMem.getByte(boolToByte(register[source].getBits())));
                                break;
                            default:
                                System.out.printf("Something went so wrong, man.\n");
                        }
                        break;
                    case 2:
                        //Source uses Immediate mode.
                        switch(dstMode){
                            case 0:
                                //Register mode.
                                register[dest].setBits(byteToBool(mainMem.getByte(address + 2)));
                                break;
                            case 1:
                                //Deferred mode.
                                mainMem.replaceByte(boolToByte(register[dest].getBits()), mainMem.getByte(address + 2));
                                break;
                            case 2:
                                //Immediate mode.
                                mainMem.replaceByte(mainMem.getByte(address + 2), mainMem.getByte(address + 2));
                                break;
                            case 3:
                                //Absolute mode.
                                mainMem.replaceByte(mainMem.getByte(mainMem.getByte(address + 2)), mainMem.getByte(address + 2));
                                break;
                            default:
                                System.out.printf("Something went so wrong, man.\n");
                        }
                        break;
                    case 3:
                        //Source uses Absolute mode.
                        switch(dstMode){
                            case 0:
                                register[dest].setBits(byteToBool(mainMem.getByte(mainMem.getByte(address + 2))));
                                break;
                            case 1:
                                mainMem.replaceByte(boolToByte(register[dest].getBits()), mainMem.getByte(mainMem.getByte(address + 2)));
                                break;
                            case 2:
                                mainMem.replaceByte(mainMem.getByte(address + 2), mainMem.getByte(mainMem.getByte(address + 2)));
                                break;
                            case 3:
                                mainMem.replaceByte(mainMem.getByte(mainMem.getByte(address + 2)), mainMem.getByte(mainMem.getByte(address + 2)));
                                break;
                            default:
                                System.out.printf("Something went so wrong, man.\n");
                        }
                        break;
                    default:
                        System.out.printf("Something went so wrong, man.\n");
                }
                break;
            case 8:
                switch(dstMode){
                    case 0:
                        register[dest].clearRegister();
                        psw.setC();
                        break;
                    case 1:
                        mainMem.clearByte(boolToByte(register[dest].getBits()));
                        psw.setC();
                        break;
                    case 2:
                        mainMem.clearByte(address + 2);
                        psw.setC();
                        break;
                    case 3:
                        mainMem.clearByte(mainMem.getByte(address + 2));
                        psw.setC();
                        break;
                    default:
                        System.out.printf("Something went so wrong, man.\n");
                }
                break;
            case 9:
                switch(dstMode){
                    case 0:
                        register[dest].setRegister();
                        break;
                    case 1:
                        mainMem.setByte(boolToByte(register[dest].getBits()));
                        break;
                    case 2:
                        mainMem.setByte(address + 2);
                        break;
                    case 3:
                        mainMem.setByte(mainMem.getByte(address + 2));
                        break;
                    default:
                        System.out.printf("Something went so wrong, man.\n");
                }
                break;
            case 10:
                switch(dstMode){
                    case 0:
                        for(int i = 7; i >= 0; --i){
                            if(register[dest].getBits()[i]){
                                register[dest].getBits()[i] = false;
                                if(i == 7){
                                    psw.setC();
                                }
                            }
                            register[dest].getBits()[i] = true;
                            break;
                        }
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        System.out.printf("Something went so wrong, man.\n");
                }
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:

                break;
            case 14:
                break;
            case 15:
                System.exit(0);
                break;
            default:
                System.out.printf("Something went so wrong, man.\n");
        }
    }

    public boolean[] byteToBool(byte toBool){
        boolean[] temp = {false, false, false, false, false, false, false, false};

        for(int i = 128, j = 0; i >= 1; i /= 2, ++j){
            temp[j] = (toBool % i < toBool);
            toBool %= toBool;
        }

        return temp;
    }

    public byte boolToByte(boolean[] toByte){
        byte temp = 0;

        for(int i = 128, j = 0; i >= 1; i /= 2, ++j){
            if(toByte[j])
                temp += i;
        }

        return temp;
    }

    public void andOp(){
    }

    public void orOp(){
    }

    public void xorOp(){

    }

    public void addOp(){

    }

    public void subOp(){

    }

    public void mulOp(){

    }

    public void divOp(){

    }

    public void movOp(){

    }

    public void clrOp(){

    }

    public void setOp(){

    }

    public void incOp(){

    }

    public void decOp(){

    }

    public void negOp(){

    }

    public void bneOp(){

    }

    public void beqOp(){

    }

    public void halt(){

    }
}
