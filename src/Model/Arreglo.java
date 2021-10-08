package Model;

import java.util.LinkedList;

/**
 *
 * @author Gonza
 */
public class Arreglo {

    private LinkedList<Dato> var = new LinkedList();

    public void Reiniciar() {
        var = new LinkedList();
    }

    public void addVar(Variable var) {
        this.var.add(new Dato(var));
    }
    
    public void addDato(Variable var) {
        this.var.getLast().setVar(var);
    }
    
    public void addDato(String oper) {
        this.var.getLast().setOper(oper);
    }

    private class Dato {

        private final LinkedList<Variable> var;
        private String oper;
        private boolean operador;
        private boolean variable;

        public Dato(Variable var) {
            this.var = new LinkedList();
            this.var.add(var);
            this.operador = false;
            this.variable = false;

        }
        
        private void hacerVar() {
            this.variable = true;
            this.operador = false;
        }
        
        private void hacerOper() {
            this.variable = false;
            this.operador = true;
        }

        public int totalVar() {
            return this.var.size();
        }

        public LinkedList getVar() {
            return var;
        }

        public void setVar(Variable var) {
            this.var.add(var);
            this.hacerVar();
        }

        public String getOper() {
            return oper;
        }

        public void setOper(String oper) {
            this.oper = oper;
            this.hacerOper();
        }

        public boolean isOperador() {
            return operador;
        }

        public void setOperador(boolean operador) {
            this.operador = operador;
        }

        public boolean isVariable() {
            return variable;
        }

        public void setVariable(boolean variable) {
            this.variable = variable;
        }

    }

}
