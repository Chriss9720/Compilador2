package Controller.Semantica;

import Model.Arreglo;
import Model.Variable;

/**
 *
 * @author Gonza
 */
public class Etapa_2 {

    private Arreglo raiz = new Arreglo();
    private Arreglo ultimo = new Arreglo();

    public void empezar(Variable var) {
        raiz.addVar(var);
        ultimo = raiz;
    }

    public void addItem(Variable var) {
        ultimo.addVar(var);
    }

    public void addNodo() {
        Arreglo nuevo = new Arreglo();
        nuevo.setPrevio(ultimo);
        ultimo.setNext(nuevo);
        ultimo = nuevo;
    }

    public void addItem(String op) {
        Variable var = new Variable();
        var.getId().add(op);
        ultimo.addOper(var);
    }

    @SuppressWarnings("empty-statement")
    public void resolver() {
        ultimo.getVars().forEach(i -> {
            i.getId().forEach(j -> {
                System.out.println(j + " " + i.isVariable() + " " + i.isOperador());
            });
        });
        if (ultimo.getPrevio() != null) {
            ultimo = ultimo.getPrevio();
            ultimo.getLast().remDim();
            ultimo.setNext(null);
            if (ultimo.getTarr() > -1) {
                System.out.println("Aceptado");
            }
        }
        System.out.println("*********");
    }

    public Arreglo getRaiz() {
        return raiz;
    }

    public void setRaiz(Arreglo raiz) {
        this.raiz = raiz;
    }

}
