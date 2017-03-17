package Fetch;

/**
 * Created by Pfheonix on 3/15/2017.
 */
public class FetchPSW {

    private boolean N, Z, V, C;
    private String name;

    public FetchPSW(){
        N = Z = V = C = false;
        name = "PSW";
    }

    public void setFlags(boolean inN, boolean inZ, boolean inV, boolean inC){
        this.N = inN;
        this.Z = inZ;
        this.V = inV;
        this.C = inC;
    }

    public boolean getN() {
        return N;
    }

    public void setN() {
        N = true;
    }

    public void clrN() {
        N = false;
    }

    public boolean getZ() {
        return Z;
    }

    public void setZ() {
        Z = true;
    }

    public void clrZ() {
        Z = false;
    }

    public boolean getV() {
        return V;
    }

    public void setV() {
        V = true;
    }

    public void clrV() {
        V = false;
    }

    public boolean getC() {
        return C;
    }

    public void setC() {
        C = true;
    }

    public void clrC() {
        C = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
