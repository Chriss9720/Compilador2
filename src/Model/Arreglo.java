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
    private boolean regla1 = true;
    private boolean regla2 = true;
    private boolean regla3 = true;

    public Arreglo() {
        this.vars = new LinkedList();
        this.previo = null;
        this.next = null;
        this.resuelto = false;
    }
    
    public void addVar(Variable var) {
        this.getVars().add(var);
        this.getLast().setVariable();
    }
    
    public void addOper(Variable oper) {
        this.getVars().add(oper);
        this.getLast().setOperador();
    }
    
    public Variable getLast() {
        return this.getVars().getLast();
    }
    
    public int getTarr() {
        return this.getLast().gettArr();
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

    public boolean isRegla1() {
        return regla1;
    }

    public void setRegla1(boolean regla1) {
        this.regla1 = regla1;
    }

    public boolean isRegla2() {
        return regla2;
    }

    public void setRegla2(boolean regla2) {
        this.regla2 = regla2;
    }

    public boolean isRegla3() {
        return regla3;
    }

    public void setRegla3(boolean regla3) {
        this.regla3 = regla3;
    }
}
