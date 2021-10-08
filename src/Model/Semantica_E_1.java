package Model;

/**
 *
 * @author Gonza
 */
public class Semantica_E_1 {

    private int linea;
    private int tC;
    private int tS;
    private int tE;
    private int tR;
    private int tB;
    private int tX;
    private int tO;
    private int tG;
    private int tF;
    private int tV;
    private String asig;
    private int err;

    public Semantica_E_1() {
        err = 0;
        tC = 0;
        tS = 0;
        tE = 0;
        tR = 0;
        tB = 0;
        tG = 0;
        tX = 0;
        tO = 0;
        tF = 0;
        tV = 0;
    }

    public void calcularTipo(String t) {
        switch (t) {
            case "INT":
                this.settE();
                break;
            case "REAL":
                this.settR();
                break;
            case "EXP":
                this.settX();
                break;
            case "CHAR":
                this.settC();
                break;
            case "CHAR[]":
                this.settS();
                break;
            case "BOOL":
                this.settB();
                break;
            case "REG":
                this.gettG();
                break;
            case "VOID":
                this.settO();
                break;
            case "FILE":
                this.settF();
                break;
        }
    }

    public int gettG() {
        return tG;
    }

    public void settG() {
        this.tG++;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int gettC() {
        return tC;
    }

    public void settC() {
        this.tC++;
    }

    public int gettS() {
        return tS;
    }

    public void settS() {
        this.tS++;
    }

    public int gettE() {
        return tE;
    }

    public void settE() {
        this.tE++;
    }

    public int gettR() {
        return tR;
    }

    public void settR() {
        this.tR++;
    }

    public int gettB() {
        return tB;
    }

    public void settB() {
        this.tB++;
    }

    public int gettX() {
        return tX;
    }

    public void settX() {
        this.tX++;
    }

    public int gettO() {
        return tO;
    }

    public void settO() {
        this.tO++;
    }

    public int gettF() {
        return tF;
    }

    public void settF() {
        this.tF++;
    }

    public int gettV() {
        return tV;
    }

    public void settV() {
        this.tV++;
    }

    public String getAsig() {
        return asig;
    }

    public void setAsig(String asig) {
        this.asig = asig + " -> ";
    }

    public int getErr() {
        return err;
    }

    public void setErr() {
        this.err++;
    }

}
