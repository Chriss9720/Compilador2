package Model;

import java.util.LinkedList;

/**
 *
 * @author Gonza
 */
public class Registro extends Ids {

    private String id;
    private final LinkedList<Variable> params;

    public Registro() {
        this.id = "";
        this.clase = "reg";
        this.amb = 0;
        this.tPar = "1";
        this.params = new LinkedList();
    }

    public LinkedList<Variable> getParams() {
        return params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getNoPar() {
        return this.getParams().size();
    }

    @Override
    public String toString() {
        String para = "";
        para = params.stream().map(v -> "\n" + v.toString()).reduce(para, String::concat);
        return "ID: " + this.getId() + "\nClase: " + this.getClase()
                + "\nAmbito: " + this.getAmb() + "\nNoPar: " + this.getNoPar()
                + "\nTPar: " + this.gettPar() + "\nParametros: " + para;
    }

}
