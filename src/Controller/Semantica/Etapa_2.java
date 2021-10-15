package Controller.Semantica;

import Model.Arreglo;
import Model.Errores;
import Model.Semantica_E_2;
import Model.Variable;
import Vista.Pantalla;
import java.util.LinkedList;

/**
 *
 * @author Gonza
 */
public class Etapa_2 {

    private Arreglo raiz = new Arreglo();
    private Arreglo ultimo = new Arreglo();
    private final Pantalla p;

    public Etapa_2(Pantalla p) {
        this.p = p;
    }

    public void empezar(Variable var) {
        raiz = new Arreglo();
        raiz.addVar(var);
        ultimo = raiz;
    }

    public void addItem(Variable var) {
        ultimo.addVar(var);
    }

    public LinkedList<Errores> addNodo(LinkedList<Integer> amb) {
        LinkedList<Errores> err = (ultimo.isRegla1()) ? validarRegla1(amb) : new LinkedList();
        if (ultimo.isRegla1()) {
            Arreglo nuevo = new Arreglo();
            nuevo.setPrevio(ultimo);
            ultimo.setNext(nuevo);
            ultimo = nuevo;
        }
        return err;
    }

    private LinkedList<Errores> validarRegla1(LinkedList<Integer> amb) {
        LinkedList<Errores> err = new LinkedList();
        last().remDim();
        Variable var = last();
        if (ultimo.getTarr() > -1) {
            ultimo.setRegla1(true);
            p.getsE_2().add(new Semantica_E_2(1030, "[", "[", var.getLinea(), "Acept", amb.getLast()));
        } else {
            ultimo.setRegla1(false);
            err.add(new Errores(var.getLinea(), 1030, "[ ... ]", "Dimension fuera del rango", "Semantica 2", amb.getLast()));
            p.getsE_2().add(new Semantica_E_2(1030, "[", "[", var.getLinea(), "ERROR", amb.getLast()));
        }
        return err;
    }

    public void addItem(String op) {
        Variable var = new Variable();
        var.getId().add(op);
        ultimo.addOper(var);
    }

    public LinkedList<Errores> resolver(LinkedList<Integer> amb) {
        LinkedList<Errores> err = new LinkedList();
        Etapa_1 e1 = new Etapa_1(p);
        //LLenar las listas
        ultimo.getVars().forEach(i -> {
            if (i.isOperador()) {
                e1.getOperadores().add(i.getId().getLast());
            } else if (i.isVariable()) {
                e1.getIds().add(i);
            }
        });
        //Resolver
        e1.Resolver(false).forEach(e -> err.add(new Errores(e)));
        //Validar
        Variable var = ultimo.getVars().getFirst();
        if (e1.getIds().getFirst().getTipo().equals("INT")) {
            System.out.println("OKCAS");
            p.getsE_2().add(new Semantica_E_2(1040, "const_entero", "const_entero", var.getLinea(), "Acept", amb.getLast()));
        } else {
            err.add(new Errores(var.getLinea(), 1040, "Temporal", "Se requiere un valor entero", "Semantica 2", amb.getLast()));
            p.getsE_2().add(new Semantica_E_2(1040, "const_entero", "Temporal", var.getLinea(), "ERROR", amb.getLast()));
        }
        e1.Reiniciar();
        if (ultimo.getPrevio() != null) {
            ultimo = ultimo.getPrevio();
            ultimo.setNext(null);
        }

        return err;
    }

    private String[] invertirDimensiones(String dim) {
        String[] arr = dim.split(",");
        String[] r = new String[arr.length];
        for (int i = arr.length - 1, j = 0; i > -1; i--, j++) {
            r[j] = arr[i];
        }
        return r;
    }

    public LinkedList<Errores> revisarFunciones(LinkedList<Variable> vars, LinkedList<Integer> amb) {
        LinkedList<Errores> err = new LinkedList();
        vars.stream().filter(i -> i.getClase().contains("funcion")).forEachOrdered(i -> {
            if (!i.getTipo().equals("VOID")) {
                p.getsE_2().add(new Semantica_E_2(1120, "id", i.getId().getFirst(), i.getLinea(), "Acept", amb.getLast()));
            } else {
                err.add(new Errores(i.getLinea(), 1120, i.getId().getFirst(), "Debe de ser una funcion valida",
                        "Semantica 2", amb.getLast()));
                p.getsE_2().add(new Semantica_E_2(1120, i.getTope(), i.getId().getFirst(),
                        i.getLinea(), "ERROR", amb.getLast()));
            }
        });
        return err;
    }

    public LinkedList<Errores> revisarREG(LinkedList<Variable> vars, LinkedList<String> op, LinkedList<Integer> amb) {
        LinkedList<Errores> err = new LinkedList();
        if (op.getFirst().equals("=") && op.size() == 1) {
            Variable v1 = vars.getFirst();
            Variable v2 = vars.getLast();
            if (v2.getClase().contains("REG")) {
                if (v1.getTipo().equals(v2.getTipo())) {
                    p.getsE_2().add(new Semantica_E_2(1161, v2.getTope(), vars.get(0).getId().getFirst(),
                            vars.get(0).getLinea(), "Acept", amb.getLast()));
                } else {
                    err.add(new Errores(vars.get(0).getLinea(), 1161, vars.get(0).getId().getFirst(),
                            "Para asignar debe de ser del mismo registro", "Semantica 2", amb.getLast()));
                    p.getsE_2().add(new Semantica_E_2(1161, v2.getTope(), vars.get(0).getId().getFirst(),
                            vars.get(0).getLinea(), "ERROR", amb.getLast()));
                }
            } else {
                err.add(new Errores(vars.get(0).getLinea(), 1170, vars.get(0).getId().getFirst(),
                        "Este valor solo puede ser asginado a un elemento", "Semantica 2", amb.getLast()));
                p.getsE_2().add(new Semantica_E_2(1170, v2.getTope(), vars.get(0).getId().getFirst(),
                        vars.get(0).getLinea(), "ERROR", amb.getLast()));
            }
        } else {
            err.add(new Errores(vars.get(0).getLinea(), 1161, vars.get(0).getId().getFirst(),
                    "Para asignar debe de ser del mismo registro", "Semantica 2", amb.getLast()));
            p.getsE_2().add(new Semantica_E_2(1161, vars.get(0).getTope(), vars.get(0).getId().getFirst(),
                    vars.get(0).getLinea(), "ERROR", amb.getLast()));
        }
        return err;
    }

    public void removeLast() {
        ultimo = ultimo.getPrevio();
        ultimo.setNext(null);
    }

    public Arreglo getRaiz() {
        return raiz;
    }

    public void setRaiz(Arreglo raiz) {
        this.raiz = raiz;
    }

    public boolean pasoRegla1() {
        return this.ultimo.isRegla1();
    }

    public Arreglo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Arreglo ultimo) {
        this.ultimo = ultimo;
    }

    public Variable last() {
        return this.ultimo.getLast();
    }

}
