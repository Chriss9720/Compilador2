package Controller.Semantica;

import Model.Errores;
import Model.Semantica_E_2;
import Model.Semantica_E_3;
import Model.Tokens;
import Model.Variable;
import Vista.Pantalla;
import java.util.LinkedList;

/**
 *
 * @author Gonza
 */
public class Etapa_3 {

    private LinkedList<Variable> ids;
    private LinkedList<String> operadores;
    private final Pantalla p;
    LinkedList<Errores> err;

    public Etapa_3(Pantalla p) {
        ids = new LinkedList();
        operadores = new LinkedList();
        this.p = p;
    }

    public void Reiniciar() {
        this.setIds();
        this.setOperadores();
    }

    public void marcar(LinkedList<Integer> amb, String func, int linea, int clave) {
        getSemanticaE_2().add(new Semantica_E_2(clave, func, func, linea, "Acept", amb.getLast()));
        getSemanticaE_2().getLast().setFuncion(func);
        getsE_3().stream().filter(f -> f.getFuncion().equals(func)).forEachOrdered(a -> a.setSalida());
    }

    public Variable resolver2001(LinkedList<Integer> amb, int linea, String res) {
        err = new LinkedList();
        Etapa_1 e1 = new Etapa_1(p);
        ids.forEach(v -> e1.getIds().add(v));
        operadores.forEach(o -> e1.getOperadores().add(o));
        e1.Resolver(false).forEach(e -> err.add(e));
        Reiniciar();
        Variable funcion = e1.getIds().getFirst();
        if (funcion.isVariant()) {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                a.setAceptados();
                acept(res, linea, amb.getLast(), 2001, "Cont_Entero");
            });
        } else {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                if (funcion.getTipo().equals("CHAR") || funcion.getTipo().equals("INT")
                        || funcion.getTipo().equals("REAL") || funcion.getTipo().equals("EXP")) {
                    a.setAceptados();
                    acept(res, linea, amb.getLast(), 2001, funcion.getId().getLast());
                } else {
                    a.setErroes();
                    e1.getIds().getFirst().setVariant(true);
                    error(res, linea, amb.getLast(), 2001, "Debe de ser entero/char/real/exp", funcion.getId().getLast());
                }
            });
        }
        e1.getIds().getFirst().setTipo("EXP");
        return e1.getIds().getFirst();
    }

    public Variable resolver2002(LinkedList<Integer> amb, int linea, String res) {
        err = new LinkedList();
        Etapa_1 e1 = new Etapa_1(p);
        ids.forEach(v -> e1.getIds().add(v));
        operadores.forEach(o -> e1.getOperadores().add(o));
        e1.Resolver(false).forEach(e -> err.add(e));
        Reiniciar();
        Variable func = e1.getIds().getFirst();
        if (func.isVariant()) {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                a.setAceptados();
                acept(res, linea, amb.getLast(), 2002, "Cont_Entero");
            });
        } else {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                if (func.getTipo().equals("INT")) {
                    a.setAceptados();
                    acept(res, linea, amb.getLast(), 2002, func.getId().getLast());
                } else {
                    a.setErroes();
                    e1.getIds().getFirst().setVariant(true);
                    error(res, linea, amb.getLast(), 2002, "Debe de ser entero", func.getId().getLast());
                }
            });
        }
        e1.getIds().getFirst().setTipo("EXP");
        return e1.getIds().getFirst();
    }

    private void acept(String res, int linea, int amb, int clave, String tope) {
        getSemanticaE_2().add(new Semantica_E_2(clave, res, tope, linea, "Acept", amb));
        getSemanticaE_2().getLast().setFuncion(res);
    }

    private void error(String res, int linea, int amb, int clave, String desc, String tope) {
        err.add(new Errores(linea, clave, res, desc, "Semantica 3", amb));
        getSemanticaE_2().add(new Semantica_E_2(clave, res, tope, linea, "ERROR", amb));
        getSemanticaE_2().getLast().setFuncion(res);
    }

    public LinkedList<Variable> getIds() {
        return ids;
    }

    public void setIds() {
        this.ids = new LinkedList();
    }

    public LinkedList<String> getOperadores() {
        return operadores;
    }

    public void setOperadores() {
        this.operadores = new LinkedList();
    }

    public LinkedList<Errores> getErr() {
        return err;
    }

    public void setErr(LinkedList<Errores> err) {
        this.err = err;
    }

    public LinkedList<Semantica_E_3> getsE_3() {
        return p.getsE_3();
    }

    public LinkedList<Semantica_E_2> getSemanticaE_2() {
        return p.getsE_2();
    }

}
