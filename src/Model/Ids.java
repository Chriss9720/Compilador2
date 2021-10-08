package Model;

/**
 *
 * @author Gonza
 */
public class Ids {

    protected String clase;
    protected String tipo;
    protected String dimArr;
    protected int amb;
    protected int linea;
    protected int tArr;
    protected int noPar;
    protected String tPar;
    protected boolean error;
    protected boolean variant;

    public Ids() {

    }

    public Ids(String clase, String tipo, String dimArr, int amb, int linea, int tArr, int noPar, String tPar, boolean error, boolean variant) {
        this.clase = clase;
        this.tipo = tipo;
        this.dimArr = dimArr;
        this.amb = amb;
        this.linea = linea;
        this.tArr = tArr;
        this.noPar = noPar;
        this.tPar = tPar;
        this.error = error;
        this.variant = variant;
    }

    public boolean isVariant() {
        return variant;
    }

    public void setVariant(boolean variant) {
        this.variant = variant;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String gettPar() {
        return tPar;
    }

    public void settPar(String tPar) {
        this.tPar = tPar;
    }

    public int getNoPar() {
        return noPar;
    }

    public void setNoPar(int noPar) {
        this.noPar = noPar;
    }

    public int gettArr() {
        return tArr;
    }

    public void settArr(int tArr) {
        this.tArr = tArr;
    }

    public String getDimArr() {
        return dimArr;
    }

    public void setDimArr(String dimArr) {
        this.dimArr = dimArr;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAmb() {
        return amb;
    }

    public void setAmb(int amb) {
        this.amb = amb;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

}
