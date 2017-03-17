package Fetch;

/**
 * Created by Pfheonix on 3/15/2017.
 */
public class FetchRegister {
    private int wordSize = 8;
    private boolean[] contents;
    private String name;

    public FetchRegister(){
        contents = new boolean[wordSize];
        name = "NA";
    }

    public FetchRegister(String inName){
        this();
        this.name = inName;
    }

    public void setBit(int index){
        if(index >= wordSize || index < 0){
            throw new IndexOutOfBoundsException("The size of this register is " + wordSize + ", you do not have a bit"
            + "there.\n");
        }
        contents[index] = true;
    }

    public void clearBit(int index){
        if(index >= wordSize || index < 0){
            throw new IndexOutOfBoundsException("The size of this register is " + wordSize + ", you do not have a bit"
                    + "there.\n");
        }
        contents[index] = false;
    }

    public boolean getBit(int index){
        if(index >= wordSize || index < 0){
            throw new IndexOutOfBoundsException("The size of this register is " + wordSize + ", you do not have a bit"
                    + "there.\n");
        }
        return contents[index];
    }

    public void setRegister(){
        for(int i = 0; i < wordSize; ++i){
            contents[i] = true;
        }
    }

    public void clearRegister(){
        for(int i = 0; i < wordSize; ++i){
            contents[i] = false;
        }
    }

    public boolean[] getBits(){
        return contents;
    }

    public void setBits(boolean[] inContents){
        this.contents = inContents;
    }

    public void print(){
        System.out.print(this.name + ": ");
        for(int i = 0; i < wordSize; ++i){
            System.out.print(contents[i]);
        }
        System.out.println();
    }
}
