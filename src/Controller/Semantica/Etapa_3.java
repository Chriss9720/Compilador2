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

    public Variable resolverSqrt(LinkedList<Integer> amb, int linea) {
        err = new LinkedList();
        Etapa_1 e1 = new Etapa_1(p);
        ids.forEach(v -> e1.getIds().add(v));
        operadores.forEach(o -> e1.getOperadores().add(o));
        e1.Resolver(false).forEach(e -> err.add(e));
        Reiniciar();
        Variable funcion = e1.getIds().getFirst();
        if (funcion.isVariant()) {
            getsE_3().stream().filter(f -> f.getFuncion().equals("sqrt_p1"))
                    .forEachOrdered(a -> {
                        a.setEntradas();
                        a.setAceptados();
                        getSemanticaE_2().add(new Semantica_E_2(2001,
                                "sqrt_p1", "Cont_Entero", linea,
                                "Acept", amb.getLast()));
                        getSemanticaE_2().getLast().setFuncion("sqrt_p1");
                    });
        } else {
            getsE_3().stream().filter(f -> f.getFuncion().equals("sqrt_p1"))
                    .forEachOrdered(a -> {
                        a.setEntradas();
                        if (funcion.getTipo().equals("CHAR")
                                || funcion.getTipo().equals("INT")
                                || funcion.getTipo().equals("REAL")
                                || funcion.getTipo().equals("EXP")) {
                            a.setAceptados();
                            getSemanticaE_2().add(new Semantica_E_2(2001,
                                    "sqrt_p1", funcion.getId().getLast(), linea,
                                    "Acept", amb.getLast()));
                            getSemanticaE_2().getLast().setFuncion("sqrt_p1");
                        } else {
                            a.setErroes();
                            err.add(new Errores(linea, 2001,
                                    "sqrt_p1", "Debe de ser entero",
                                    "Semantica 3", amb.getLast()));
                            getSemanticaE_2().add(new Semantica_E_2(2001,
                                    "sqrt_p1", funcion.getId().getLast(), linea,
                                    "ERROR", amb.getLast()));
                            getSemanticaE_2().getLast().setFuncion("sqrt_p1");
                        }
                    });
        }
        e1.getIds().getFirst().setTipo("EXP");
        return e1.getIds().getFirst();
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
