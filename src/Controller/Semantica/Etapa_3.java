package Controller.Semantica;

import Model.Cuadruplos_1;
import Model.Cuadruplos_Contadores;
import Model.Errores;
import Model.Semantica_E_2;
import Model.Semantica_E_3;
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
        if (!isErrC()) {
            getCuadruplos().add(new Cuadruplos_1());
            getCuadruplos().getLast().setAccion("Call");
            getCuadruplos().getLast().setArg1(func);
            getCuadruplosCont().get(amb.getLast()).setCall();
        }
        getSemanticaE_2().add(new Semantica_E_2(clave, func, func, linea, "Acept", amb.getLast()));
        getSemanticaE_2().getLast().setFuncion(func);
        getsE_3().stream().filter(f -> f.getFuncion().equals(func)).forEachOrdered(a -> a.setSalida());
    }

    public Variable resolver2001(LinkedList<Integer> amb, int linea, String res, boolean add) {
        int regla = 2001;
        err = new LinkedList();
        Etapa_1 e1 = new Etapa_1(p);
        ids.forEach(v -> e1.getIds().add(v));
        operadores.forEach(o -> e1.getOperadores().add(o));
        e1.Resolver(false, amb.getLast()).forEach(e -> {
            err.add(e);
            setErrC();
        });
        Reiniciar();
        Variable funcion = e1.getIds().getFirst();
        if (funcion.isVariant()) {
            setErrC();
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                a.setAceptados();
                acept(res, linea, amb.getLast(), regla, "Cont_Entero");
            });
        } else {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                if (funcion.getTipo().equals("CHAR") || funcion.getTipo().equals("INT")
                        || funcion.getTipo().equals("REAL") || funcion.getTipo().equals("EXP")) {
                    a.setAceptados();
                    acept(res, linea, amb.getLast(), regla, funcion.getId().getLast());
                } else {
                    setErrC();
                    a.setErroes();
                    e1.getIds().getFirst().setVariant(true);
                    error(res, linea, amb.getLast(), regla, "Debe de ser entero/char/real/exp", funcion.getId().getLast());
                }
            });
        }
        e1.getIds().getFirst().setTipo("EXP");
        filtroCuadruplos(amb.getLast(), add, e1);
        return e1.getIds().getFirst();
    }

    public Variable resolver2002(LinkedList<Integer> amb, int linea, String res, boolean add) {
        int regla = 2002;
        err = new LinkedList();
        Etapa_1 e1 = new Etapa_1(p);
        ids.forEach(v -> e1.getIds().add(v));
        operadores.forEach(o -> e1.getOperadores().add(o));
        e1.Resolver(false, amb.getLast()).forEach(e -> {
            err.add(e);
            setErrC();
        });
        Reiniciar();
        Variable func = e1.getIds().getFirst();
        if (func.isVariant()) {
            setErrC();
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                a.setAceptados();
                acept(res, linea, amb.getLast(), regla, "Cont_Entero");
            });
        } else {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                if (func.getTipo().equals("INT")) {
                    a.setAceptados();
                    acept(res, linea, amb.getLast(), regla, func.getId().getLast());
                } else {
                    setErrC();
                    a.setErroes();
                    e1.getIds().getFirst().setVariant(true);
                    error(res, linea, amb.getLast(), regla, "Debe de ser entero", func.getId().getLast());
                }
            });
        }
        if (res.contains("asc")) {
            e1.getIds().getFirst().setTipo("CHAR");
        } else {
            e1.getIds().getFirst().setTipo("EXP");
        }
        filtroCuadruplos(amb.getLast(), add, e1);
        return e1.getIds().getFirst();
    }

    public Variable resolver2003(LinkedList<Integer> amb, int linea, String res, boolean add) {
        int regla = 2003;
        err = new LinkedList();
        Etapa_1 e1 = new Etapa_1(p);
        ids.forEach(v -> e1.getIds().add(v));
        operadores.forEach(o -> e1.getOperadores().add(o));
        e1.Resolver(false, amb.getLast()).forEach(e -> {
            err.add(e);
            setErrC();
        });
        Reiniciar();
        Variable func = e1.getIds().getFirst();
        if (func.isVariant()) {
            setErrC();
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                a.setAceptados();
                acept(res, linea, amb.getLast(), regla, "Cont_cadena");
            });
        } else {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                if (func.getTipo().equals("CHAR[]")) {
                    a.setAceptados();
                    acept(res, linea, amb.getLast(), regla, func.getId().getLast());
                } else {
                    setErrC();
                    a.setErroes();
                    e1.getIds().getFirst().setVariant(true);
                    error(res, linea, amb.getLast(), regla, "Debe de ser una cadena", func.getId().getLast());
                }
            });
        }
        e1.getIds().getFirst().setTipo("CHAR[]");
        filtroCuadruplos(amb.getLast(), add, e1);
        return e1.getIds().getFirst();
    }

    public Variable resolver2004(LinkedList<Integer> amb, int linea, String res, boolean add) {
        int regla = 2004;
        if (ids.size() == 1) {
            try {
                int v = Integer.parseInt(ids.getFirst().getId().getFirst());
                ids.getFirst().setTipo((v < 16) ? "INT" : "CHAR[]");
            } catch (Exception e) {
                getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                    a.setEntradas();
                    setErrC();
                    a.setErroes();
                    ids.getFirst().setVariant(true);
                    error(res, linea, amb.getLast(), regla, "Debe de ser una constante entero", ids.getFirst().getId().getFirst());
                });
            }
        } else {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                setErrC();
                a.setErroes();
                ids.getFirst().setVariant(true);
                error(res, linea, amb.getLast(), regla, "Debe de ser una constante entero", ids.getFirst().getId().getFirst());
            });
        }
        Variable func = ids.getFirst();
        if (!isErrC()) {
            if (getCuadruplos().getLast().isCall() || getCuadruplos().getLast().nuevo()) {
                getCuadruplos().add(new Cuadruplos_1());
            }
            if (getCuadruplos().getLast().primero()) {
                getCuadruplos().getLast().setArg1(func.getId().getFirst());
            } else if (getCuadruplos().getLast().segundo()) {
                getCuadruplos().getLast().setArg2(func.getId().getFirst());
            }
            if (add) {
                String temp = temporal(func.getTipo(), amb.getLast());
                getCuadruplos().getLast().setResultado(temp);
                func.getId().set(0, temp);
            }
        }
        Reiniciar();
        return func;
    }

    public Variable resolver2005(LinkedList<Integer> amb, int linea, String res, boolean add) {
        int regla = 2005;
        err = new LinkedList();
        Etapa_1 e1 = new Etapa_1(p);
        ids.forEach(v -> e1.getIds().add(v));
        operadores.forEach(o -> e1.getOperadores().add(o));
        e1.Resolver(false, amb.getLast()).forEach(e -> {
            err.add(e);
            setErrC();
        });
        Reiniciar();
        Variable func = e1.getIds().getFirst();
        if (func.isVariant()) {
            setErrC();
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                a.setAceptados();
                acept(res, linea, amb.getLast(), regla, "id");
            });
        } else {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                if (func.getTipo().contains("CHAR")) {
                    a.setAceptados();
                    acept(res, linea, amb.getLast(), regla, func.getId().getLast());
                } else {
                    setErrC();
                    a.setErroes();
                    e1.getIds().getFirst().setVariant(true);
                    error(res, linea, amb.getLast(), regla, "Debe de ser una variable tipo FILE", func.getId().getLast());
                }
            });
        }
        filtroCuadruplos(amb.getLast(), add, e1);
        return e1.getIds().getFirst();
    }

    public Variable resolver2006(LinkedList<Integer> amb, int linea, String res, boolean add) {
        int regla = 2006;
        err = new LinkedList();
        Etapa_1 e1 = new Etapa_1(p);
        ids.forEach(v -> e1.getIds().add(v));
        operadores.forEach(o -> e1.getOperadores().add(o));
        e1.Resolver(false, amb.getLast()).forEach(e -> {
            err.add(e);
            setErrC();
        });
        Variable func = e1.getIds().getFirst();
        Reiniciar();
        if (func.isVariant()) {
            setErrC();
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                a.setAceptados();
                acept(res, linea, amb.getLast(), regla, "Cont_cadena");
            });
        } else {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                if (func.getTipo().equals("CHAR[]")) {
                    a.setAceptados();
                    acept(res, linea, amb.getLast(), regla, func.getId().getLast());
                } else {
                    setErrC();
                    a.setErroes();
                    e1.getIds().getFirst().setVariant(true);
                    error(res, linea, amb.getLast(), regla, "Debe de ser una cadena", func.getId().getLast());
                }
            });
        }
        if (res.contains("<+") || res.contains("$")) {
            e1.getIds().getFirst().setTipo("BOOL");
        } else if (res.contains(">+")) {
            e1.getIds().getFirst().setTipo("VOID");
        } else if (res.contains("len")) {
            e1.getIds().getFirst().setTipo("INT");
        }
        filtroCuadruplos(amb.getLast(), add, e1);
        return e1.getIds().getFirst();
    }

    public Variable resolver2007(LinkedList<Integer> amb, int linea, String res, boolean add) {
        int regla = 2007;
        err = new LinkedList();
        Etapa_1 e1 = new Etapa_1(p);
        ids.forEach(v -> e1.getIds().add(v));
        operadores.forEach(o -> e1.getOperadores().add(o));
        e1.Resolver(false, amb.getLast()).forEach(e -> {
            err.add(e);
            setErrC();
        });
        Reiniciar();
        Variable func = e1.getIds().getFirst();
        if (func.isVariant()) {
            setErrC();
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                a.setAceptados();
                acept(res, linea, amb.getLast(), regla, "Cont_caracter");
            });
        } else {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                if (func.getTipo().equals("CHAR")) {
                    a.setAceptados();
                    acept(res, linea, amb.getLast(), regla, func.getId().getLast());
                } else {
                    setErrC();
                    a.setErroes();
                    e1.getIds().getFirst().setVariant(true);
                    error(res, linea, amb.getLast(), regla, "Debe de ser una cadena", func.getId().getLast());
                }
            });
        }
        e1.getIds().getFirst().setTipo("INT");
        filtroCuadruplos(amb.getLast(), add, e1);
        return e1.getIds().getFirst();
    }

    public Variable resolver2008(LinkedList<Integer> amb, int linea, String res, boolean add) {
        int regla = 2008;
        err = new LinkedList();
        Etapa_1 e1 = new Etapa_1(p);
        ids.forEach(v -> e1.getIds().add(v));
        operadores.forEach(o -> e1.getOperadores().add(o));
        e1.Resolver(false, amb.getLast()).forEach(e -> {
            err.add(e);
            setErrC();
        });
        Reiniciar();
        Variable func = e1.getIds().getFirst();
        if (func.isVariant()) {
            setErrC();
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                a.setAceptados();
                acept(res, linea, amb.getLast(), regla, "id");
            });
        } else {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                if (func.getTipo().equals("FILE")) {
                    a.setAceptados();
                    acept(res, linea, amb.getLast(), regla, func.getId().getLast());
                } else {
                    setErrC();
                    a.setErroes();
                    e1.getIds().getFirst().setVariant(true);
                    error(res, linea, amb.getLast(), regla, "Debe de ser una variable tipo FILE", func.getId().getLast());
                }
            });
        }
        if (res.contains("<+") || res.contains("$") || res.contains("~")) {
            e1.getIds().getFirst().setTipo("BOOL");
        } else if (res.contains(">+")) {
            e1.getIds().getFirst().setTipo("VOID");
        }
        filtroCuadruplos(amb.getLast(), add, e1);
        return e1.getIds().getFirst();
    }

    public Variable resolver2009(LinkedList<Integer> amb, int linea, String res, boolean add) {
        int regla = 2009;
        err = new LinkedList();
        Etapa_1 e1 = new Etapa_1(p);
        ids.forEach(v -> e1.getIds().add(v));
        operadores.forEach(o -> e1.getOperadores().add(o));
        e1.Resolver(false, amb.getLast()).forEach(e -> {
            err.add(e);
            setErrC();
        });
        Reiniciar();
        Variable func = e1.getIds().getFirst();
        if (func.isVariant()) {
            setErrC();
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                a.setAceptados();
                acept(res, linea, amb.getLast(), regla, "Cont_cadena");
            });
        } else {
            getsE_3().stream().filter(f -> f.getFuncion().equals(res)).forEachOrdered(a -> {
                a.setEntradas();
                if (func.getTipo().equals("CHAR[]")) {
                    if (func.getClase().isEmpty()) {
                        String cons = func.getId().getFirst();
                        if (cons.equals("w") || cons.equals("r")
                                || cons.equals("w+") || cons.equals("r+")) {
                            a.setAceptados();
                            acept(res, linea, amb.getLast(), regla, cons);
                        } else {
                            setErrC();
                            a.setErroes();
                            e1.getIds().getFirst().setVariant(true);
                            error(res, linea, amb.getLast(), regla, "Debe de ser una cadena", func.getId().getLast());
                        }
                    } else {
                        a.setAceptados();
                        acept(res, linea, amb.getLast(), regla, func.getId().getLast());
                    }
                } else {
                    setErrC();
                    a.setErroes();
                    e1.getIds().getFirst().setVariant(true);
                    error(res, linea, amb.getLast(), regla, "Debe de ser una cadena", func.getId().getLast());
                }
            });
        }
        e1.getIds().getFirst().setTipo("BOOL");
        filtroCuadruplos(amb.getLast(), add, e1);
        return e1.getIds().getFirst();
    }

    private void filtroCuadruplos(int amb, boolean add, Etapa_1 e1) {
        if (!isErrC()) {
            if (getCuadruplos().getLast().isCall() || getCuadruplos().getLast().nuevo()) {
                getCuadruplos().add(new Cuadruplos_1());
            }
            if (getCuadruplos().getLast().primero()) {
                getCuadruplos().getLast().setArg1(e1.getIds().getFirst().getId().getFirst());
            } else if (getCuadruplos().getLast().segundo()) {
                getCuadruplos().getLast().setArg2(e1.getIds().getFirst().getId().getFirst());
            }
            if (add) {
                String temp = temporal(e1.getIds().getFirst().getTipo(), amb);
                getCuadruplos().getLast().setResultado(temp);
                e1.getIds().getFirst().getId().set(0, temp);
            }
        }
    }

    private void acept(String res, int linea, int amb, int clave, String tope) {
        getSemanticaE_2().add(new Semantica_E_2(clave, res, tope, linea, "Acept", amb));
        getSemanticaE_2().getLast().setFuncion(res);
    }

    private void error(String res, int linea, int amb, int clave, String desc, String tope) {
        setErrC();
        err.add(new Errores(linea, clave, res, desc, "Semantica 3", amb));
        getSemanticaE_2().add(new Semantica_E_2(clave, res, tope, linea, "ERROR", amb));
        getSemanticaE_2().getLast().setFuncion(res);
    }

    private String temporal(String tipo, int ambAct) {
        String temp = "T";
        switch (tipo) {
            case "INT":
                getCuadruplosCont().get(ambAct).setTE();
                temp += "E" + getCuadruplosCont().get(ambAct).getTE();
                break;
            case "REAL":
                getCuadruplosCont().get(ambAct).setTR();
                temp += "R" + getCuadruplosCont().get(ambAct).getTR();
                break;
            case "EXP":
                getCuadruplosCont().get(ambAct).setTEX();
                temp += "EX" + getCuadruplosCont().get(ambAct).getTEX();
                break;
            case "CHAR":
                getCuadruplosCont().get(ambAct).setTCH();
                temp += "CH" + getCuadruplosCont().get(ambAct).getTCH();
                break;
            case "CHAR[]":
                getCuadruplosCont().get(ambAct).setTS();
                temp += "S" + getCuadruplosCont().get(ambAct).getTS();
                break;
            case "BOOL":
                getCuadruplosCont().get(ambAct).setTB();
                temp += "B" + getCuadruplosCont().get(ambAct).getTB();
                break;
            case "REG":
                getCuadruplosCont().get(ambAct).setTRX();
                temp += "RX" + getCuadruplosCont().get(ambAct).getTRX();
                break;
            case "FILE":
                getCuadruplosCont().get(ambAct).setTF();
                temp += "F" + getCuadruplosCont().get(ambAct).getTF();
                break;
        }
        return temp;
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

    public boolean isErrC() {
        return p.isErrC();
    }

    public void setErrC() {
        p.setErrC();
    }

    public LinkedList<Cuadruplos_1> getCuadruplos() {
        return p.getCuadruplos();
    }

    public LinkedList<Cuadruplos_Contadores> getCuadruplosCont() {
        return p.getCuadruplosCont();
    }

}
