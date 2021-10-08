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
        ultimo = raiz;
    }
    
    @SuppressWarnings("empty-statement")
    public void addItem(Variable var) {
        ultimo.getVars().add(var);
    }
    
    @SuppressWarnings("empty-statement")
    public void addNodo(Variable var) {
        Arreglo nuevo = new Arreglo();
        nuevo.getVars().add(var);
        ultimo.getVars().getLast().setDim();
        nuevo.setPrevio(ultimo);
        ultimo.setNext(nuevo);
        ultimo = nuevo;
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
//        for (; aux.next(); aux = aux.getNext()) {
//            if (aux.next()) {
//                prev = aux;
//            }
//        }
//        for (Variable v : aux.getVars()) {
//            System.out.print(v.getId().getLast() + "\t");
//        }
//        System.out.println("");
//        raiz = prev;
//        prod();
    }
    
    private void prod() {
        Arreglo aux = raiz;
        for (int i = 0; aux.next(); aux = aux.getNext(), i++) {
            System.out.println(i);
        }
    }
    
    public Arreglo getRaiz() {
        return raiz;
    }
    
    public void setRaiz(Arreglo raiz) {
        this.raiz = raiz;
    }
    
}
