package Model;

import java.util.LinkedList;

/**
 *
 * @author Gonza
 */
public class Funcion extends Ids {

    private String id;
    private final LinkedList<Variable> params;

    public Funcion() {
        this.id = "";
        this.clase = "funcion";
        this.amb = 0;
        this.tPar = "1";
        this.params = new LinkedList();
        this.tipo = "";
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
        int total = 0;
        for (Variable v : getParams()) {
            total = v.getId().stream().map(x -> 1).reduce(total, Integer::sum);
        }
        return total;
    }

    @Override
    public String toString() {
        String para = "";
        para = params.stream().map(v -> "\n" + v.toString()).reduce(para, String::concat);
        return "ID: " + this.getId() + "\nClase: " + this.getClase()
                + "\nAmbito: " + this.getAmb() + "\nNoPar: " + this.getNoPar()
                + "\nTPar: " + this.gettPar() + "\nTipo: " + this.getTipo()
                + "\nDimArr: " + this.getDimArr() + "\nTArr: " + this.gettArr()
                + "\nParametros: " + para;
    }

}
