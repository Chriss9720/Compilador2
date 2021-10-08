package Model;

/**
 *
 * @author Gonza
 */
public class Ambito {

    private int errores;
    private final int ambito;

    public Ambito(int ambito) {
        this.errores = 0;
        this.ambito = ambito;
    }

    public int getErrores() {
        return errores;
    }

    public void setErrores() {
        this.errores++;
    }

    public int getAmbito() {
        return ambito;
    }

}
