package Fetch;

/**
 * Created by Pfheonix on 3/15/2017.
 */
public class FetchMem {
    private FetchMem coreMem;
    private byte[] coreMemory;
    private int offset = 0000;
    private int size = 1000;
    private boolean type;

    public FetchMem(){
        this.coreMemory = new byte[size];
    }


    public void setByte(int index){
        if(index >= coreMemory.length || index < 0){
            throw new IndexOutOfBoundsException("Index is out of bounds, please download more deditated wam.\n");
        }
        coreMemory[index] = 127;
    }

    public void replaceByte(int index, byte value){
        coreMemory[index] = value;
    }

    public void clearByte(int index){
        if(index >= coreMemory.length || index < 0){
            throw new IndexOutOfBoundsException("Index is out of bounds, please download more deditated wam.\n");
        }
        coreMemory[index] = 0;
    }

    public byte getByte(int index){
        if(index >= coreMemory.length || index < 0){
            throw new IndexOutOfBoundsException("Index is out of bounds, please download more deditated wam.\n");
        }
        return coreMemory[index];
    }

    public void print(int index){
        if(index >= coreMemory.length || index < 0){
            throw new IndexOutOfBoundsException("Index is out of bounds, please download more deditated wam.\n");
        }
        System.out.println("Mem " + index + ": " + coreMemory[index] + "." );
    }

    public void testMem(){
        this.print(10);
        this.print(20);

        this.setByte(10);
        this.setByte(20);

        this.print(10);
        this.print(20);
    }
}