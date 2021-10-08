package Controller.Semantica;

import Model.Arreglo;
import Model.Variable;
import java.util.LinkedList;

/**
 *
 * @author Gonza
 */
public class Etapa_2 {

    private Arreglo raiz = new Arreglo();
    private Arreglo ultimo = new Arreglo();

    public void empezar(Variable var) {
        System.out.println("star: " + var.getId().getLast());
        raiz.getVars().add(var);
        raiz.getVars().getLast().setVariable();
        ultimo = raiz;
    }

    public void addItem(Variable var) {
        ultimo.getVars().add(var);
        ultimo.getVars().getLast().setVariable();
    }

    public void addNodo() {
        Arreglo nuevo = new Arreglo();
        ultimo.getVars().getLast().setDim();
        nuevo.setPrevio(ultimo);
        ultimo.setNext(nuevo);
        ultimo = nuevo;
    }
    
    public void addItem(String op) {
        Variable var = new Variable();
        var.isOperador();
        var.getId().add(op);
        ultimo.getVars().add(var);
    }

    @SuppressWarnings("empty-statement")
    public void resolver() {
        ultimo.getVars().forEach(i -> {
            i.getId().forEach(j -> {
                System.out.println(j + " " + i.getDim());
            });
        });
        if (ultimo.getPrevio() != null) {
            ultimo = ultimo.getPrevio();
            ultimo.setNext(null);
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
