package Model;

import java.util.LinkedList;

/**
 *
 * @author Gonza
 */
public class Arreglo {
    
    private Arreglo previo = null;
    private Arreglo next = null;
    private LinkedList<Variable> vars;

    public Arreglo() {
        this.vars = new LinkedList();
    }

    public Arreglo getProfunidad() {
        return next;
    }

    public void setProfunidad(Arreglo profunidad) {
        this.next = profunidad;
    }

    public LinkedList<Variable> getVars() {
        return vars;
    }

    public void setVars(LinkedList<Variable> vars) {
        this.vars = vars;
    }
    
    
}
