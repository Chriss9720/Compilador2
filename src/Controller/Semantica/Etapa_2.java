package Controller.Semantica;

import Model.Arreglo;
import Model.Errores;
import Model.Semantica_E_1;
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
    public LinkedList<Errores> resolver() {
        LinkedList<Errores> err = new LinkedList();
        Etapa_1 s1 = new Etapa_1(p);
        ultimo.getVars().forEach(i -> {
            i.getId().forEach(j -> {
                if (i.isVariable()) {
                    s1.getIds().add(i);
                } else if (i.isOperador()) {
                    s1.getOperadores().add(i.getId().getLast());
                }
            });
        });
        if (ultimo.getPrevio() != null) {
            ultimo = ultimo.getPrevio();
            ultimo.getLast().remDim();
            ultimo.setNext(null);
            boolean tercera = true;
            Variable var = ultimo.getLast();
            if (ultimo.getTarr() > -1) {
                int v = 0;
                p.getsE_2().add(new Semantica_E_2(1030, var.getTope(), var.getId().getFirst(),
                        var.getLinea(), "Acept", var.getAmb()));
                if (s1.getIds().size() > 1) {
                    tercera = false;
                } else if (s1.getIds().size() == 1) {
                    try {
                        v = Integer.parseInt(s1.getIds().getFirst().getId().getFirst());
                        tercera = true;
                    } catch (NumberFormatException e) {
                        tercera = false;
                    } catch (Exception e) {
                        tercera = false;
                    }
                }
                p.getsE_1().add(new Semantica_E_1());
                p.getsE_1().getLast().setLinea(var.getLinea());
                p.getsE_1().getLast().setAsig(var.getId().getLast());
                s1.Resolver(false).forEach(e -> err.add(new Errores(e)));
                if (s1.getIds().getFirst().getTipo().equals("INT")) {
                    p.getsE_2().add(new Semantica_E_2(1040, "Const_entero", s1.getIds().getFirst().getId().getLast(),
                            var.getLinea(), "Acept", var.getAmb()));
                    if (tercera) {
                        String[] dim = invertirDimensiones(var.getDimArr());
                        if (v < Integer.parseInt(dim[ultimo.getTarr()])) {
                            p.getsE_2().add(new Semantica_E_2(1050, "Const_entero", String.valueOf(v),
                                    var.getLinea(), "Acept", var.getAmb()));
                        } else {
                            err.add(new Errores(var.getLinea(), 1050, String.valueOf(v), "Fuera del rango",
                                    "Semantica 2", var.getAmb()));
                            p.getsE_2().add(new Semantica_E_2(1050, "Const_entero", String.valueOf(v),
                                    var.getLinea(), "ERROR", var.getAmb()));
                        }
                    } else {
                        p.getsE_2().add(new Semantica_E_2(1050, var.getTope(), String.valueOf(v),
                                var.getLinea(), "Acept", var.getAmb()));
                    }
                } else {
                    err.add(new Errores(var.getLinea(), 1040, "[ ... ]", "Debe de ser un valor INT",
                            "Semantica 2", var.getAmb()));
                    p.getsE_2().add(new Semantica_E_2(1040, var.getTope(), var.getId().getFirst(),
                            var.getLinea(), "ERROR", var.getAmb()));
                }
            } else {
                err.add(new Errores(var.getLinea(), 1030, "[ ... ]", "Dimension fuera del rango",
                        "Semantica 2", var.getAmb()));
                p.getsE_2().add(new Semantica_E_2(1030, var.getTope(), var.getId().getFirst(),
                        var.getLinea(), "ERROR", var.getAmb()));
            }
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

    public Arreglo getRaiz() {
        return raiz;
    }

    public void setRaiz(Arreglo raiz) {
        this.raiz = raiz;
    }

}
