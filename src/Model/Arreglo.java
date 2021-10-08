package Model;

import java.util.LinkedList;

/**
 *
 * @author Gonza
 */
public class Arreglo {

    private Arreglo previo;
    private Arreglo next;
    private LinkedList<Variable> vars;
    private boolean resuelto;

    public Arreglo() {
        this.vars = new LinkedList();
        this.previo = null;
        this.next = null;
        this.resuelto = false;
    }

    public boolean next() {
        return next != null;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }
    
    public LinkedList<Variable> getVars() {
        return vars;
    }

    public void setVars(LinkedList<Variable> vars) {
        this.vars = vars;
    }

    public Arreglo getPrevio() {
        return previo;
    }

    public void setPrevio(Arreglo previo) {
        this.previo = previo;
    }

    public Arreglo getNext() {
        return next;
    }

    public void setNext(Arreglo next) {
        this.next = next;
    }
}
