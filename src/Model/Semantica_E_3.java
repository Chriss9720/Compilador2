package Model;

/**
 *
 * @author Gonza
 */
public class Semantica_E_3 {

    private String funcion;
    private int entradas;
    private int salida;
    private int aceptados;
    private int erroes;

    public Semantica_E_3(String funcion) {
        this.funcion = funcion;
        this.entradas = 0;
        this.salida = 0;
        this.aceptados = 0;
        this.erroes = 0;
    }

    public void setEntradas() {
        this.entradas +=1;
    }

    public void setSalida() {
        this.salida +=1;
    }

    public void setAceptados() {
        this.aceptados +=1;
    }

    public void setErroes() {
        this.erroes +=1;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public int getEntradas() {
        return entradas;
    }

    public int getSalida() {
        return salida;
    }

    public int getAceptados() {
        return aceptados;
    }

    public int getErroes() {
        return erroes;
    }

}
