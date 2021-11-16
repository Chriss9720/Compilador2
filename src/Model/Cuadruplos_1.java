package Model;

/**
 *
 * @author Gonza
 */
public class Cuadruplos_1 {

    private String etiqueta;
    private String Accion;
    private String Arg1;
    private String Arg2;
    private String Resultado;

    public Cuadruplos_1() {
        this.etiqueta = "";
        this.Accion = "";
        this.Arg1 = "";
        this.Arg2 = "";
        this.Resultado = "";
    }

    public Cuadruplos_1(Cuadruplos_1 data) {
        this.etiqueta = data.getEtiqueta();
        this.Accion = data.getAccion();
        this.Arg1 = data.getArg1();
        this.Arg2 = data.getArg2();
        this.Resultado = data.getResultado();
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getAccion() {
        return Accion;
    }

    public void setAccion(String Accion) {
        this.Accion = Accion;
    }

    public String getArg1() {
        return Arg1;
    }

    public void setArg1(String Arg1) {
        this.Arg1 = Arg1;
    }

    public String getArg2() {
        return Arg2;
    }

    public void setArg2(String Arg2) {
        this.Arg2 = Arg2;
    }

    public String getResultado() {
        return Resultado;
    }

    public void setResultado(String Resultado) {
        this.Resultado = Resultado;
    }

    public boolean primero() {
        return this.getArg1().isEmpty();
    }

    public boolean segundo() {
        return this.getArg2().isEmpty();
    }

    public boolean nuevo() {
        return !this.getArg2().isEmpty();
    }

    public boolean remover() {
        return getEtiqueta().isEmpty() && getAccion().isEmpty()
                && primero() && segundo() && getResultado().isEmpty();
    }

    public boolean isCall() {
        return getAccion().equals("Call");
    }

    @Override
    public String toString() {
        return "Etiqueta: " + this.getEtiqueta()
                + "\nAccion: " + this.getAccion()
                + "\nArg1: " + this.getArg1()
                + "\nArg2: " + this.getArg2()
                + "\nResultado: " + this.getResultado();
    }

}
