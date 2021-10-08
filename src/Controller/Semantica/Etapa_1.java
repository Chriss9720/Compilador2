package Controller.Semantica;

import Model.Errores;
import Model.Semantica_E_1;
import Model.Variable;
import Vista.Pantalla;
import java.util.LinkedList;

/**
 *
 * @author Gonza
 */
public class Etapa_1 {

    private LinkedList<Variable> ids = new LinkedList();
    private LinkedList<String> operadores = new LinkedList();
    private int clave;

    private final String[] entradas = new String[]{
        "INT", "REAL", "EXP", "CHAR", "CHAR[]", "BOOL", "REG", "VOID", "FILE"};
    //Para la suma
    private final String[][] matrizSuma = new String[][]{
        {"INT", "REAL", "EXP", "INT", "CHAR[]", "ERROR", "REG", "ERROR", "ERROR"},
        {"REAL", "REAL", "EXP", "REAL", "CHAR[]", "ERROR", "REG", "ERROR", "ERROR"},
        {"EXP", "EXP", "EXP", "EXP", "CHAR[]", "ERROR", "REG", "ERROR", "ERROR"},
        {"INT", "REAL", "EXP", "INT", "CHAR[]", "ERROR", "REG", "ERROR", "ERROR"},
        {"CHAR[]", "CHAR[]", "CHAR[]", "CHAR[]", "CHAR[]", "CHAR[]", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "CHAR[]", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"REG", "REG", "REG", "REG", "REG", "REG", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"}
    };
    //Para la resta
    private final String[][] matrizResta = new String[][]{
        {"INT", "REAL", "EXP", "INT", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"REAL", "REAL", "EXP", "REAL", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"EXP", "EXP", "EXP", "EXP", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"INT", "REAL", "EXP", "INT", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "CHAR[]", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "CHAR[]", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"REG", "REG", "REG", "REG", "REG", "REG", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"}
    };
    //Para multiplicacion
    private final String[][] matrizMultiplicacion = new String[][]{
        {"INT", "REAL", "EXP", "INT", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"REAL", "REAL", "EXP", "REAL", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"EXP", "EXP", "EXP", "EXP", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"INT", "REAL", "EXP", "INT", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"REG", "REG", "REG", "REG", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"}
    };
    //Para divisiones
    private final String[][] matrizDiv = new String[][]{
        {"INT", "REAL", "EXP", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"REAL", "REAL", "EXP", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"EXP", "EXP", "EXP", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"REG", "REG", "REG", "REG", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"}
    };
    //Para relacionales
    private final String[][] matrizRelacionales = new String[][]{
        {"BOOL", "BOOL", "BOOL", "BOOL", "BOOL", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"BOOL", "BOOL", "BOOL", "BOOL", "BOOL", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"BOOL", "BOOL", "BOOL", "BOOL", "BOOL", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"BOOL", "BOOL", "BOOL", "BOOL", "BOOL", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"BOOL", "BOOL", "BOOL", "BOOL", "BOOL", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"}
    };
    //Para logicos
    private final String[][] matrizLogicos = new String[][]{
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "BOOL", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"}
    };
    //Residuos
    private final String[][] matrizResiduos = new String[][]{
        {"INT", "ERROR", "ERROR", "INT", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"INT", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"REG", "REG", "REG", "REG", "REG", "REG", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"},
        {"ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "REG", "ERROR", "ERROR"}
    };
    private final Pantalla pantalla;

    public Etapa_1(Pantalla pantalla) {
        this.pantalla = pantalla;
    }

    public void Reiniciar() {
        this.ids = new LinkedList();
        this.operadores = new LinkedList();
    }

    public void mostrarEcuacion() {
        String e = "";
        LinkedList<Variable> idsAux = new LinkedList();
        LinkedList<String> operadoresAux = new LinkedList();
        this.getIds().forEach(i -> idsAux.add(new Variable(i)));
        this.getOperadores().forEach(i -> operadoresAux.add(i));
        while (!idsAux.isEmpty()) {
            e += idsAux.getFirst().getId().getFirst();
            if (!operadoresAux.isEmpty()) {
                e += " " + operadoresAux.getFirst() + " ";
                operadoresAux.removeFirst();
            }
            idsAux.removeFirst();
        }
        System.out.println(e + " ->  SE_1");
    }

    public LinkedList<Errores> Resolver() {
        LinkedList<Errores> err = new LinkedList();
        ResolverEcuacion(err);
        operadores.forEach(o -> System.out.print(o + "\t"));
        return err;
    }

    private void ResolverEcuacion(LinkedList<Errores> err) {
        String[] oprs = new String[]{"/", "*", "#", "&", "%", "&&"};
        int p = BuscarOp(oprs);
        while (p != -1) {
            ejecutar(p, err, operadores.get(p));
            p = BuscarOp(oprs);
        }
        oprs = new String[]{"+", "-", "|", "||"};
        p = BuscarOp(oprs);
        while (p != -1) {
            ejecutar(p, err, operadores.get(p));
            p = BuscarOp(oprs);
        }
        oprs = new String[]{"<", ">=", "=>", "<=", "=<", "!=", "==", ">"};
        p = BuscarOp(oprs);
        while (p != -1) {
            ejecutar(p, err, operadores.get(p));
            p = BuscarOp(oprs);
        }
        p = BuscarIguales();
        while (p != -1) {
            igualar(err, p);
            p = BuscarIguales();
        }
    }

    private int BuscarOp(String[] opers) {
        for (int i = 0; i < operadores.size(); i++) {
            if (BuscarOper(opers, operadores.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private boolean BuscarOper(String[] opers, String c) {
        for (String op : opers) {
            if (op.equals(c)) {
                return true;
            }
        }
        return false;
    }

    private int BuscarIguales() {
        LinkedList<String> aux = new LinkedList();
        operadores.forEach(i -> aux.add(i));
        while (!aux.isEmpty()) {
            if (aux.getLast().equals("=")) {
                return aux.size() - 1;
            }
            aux.removeLast();
        }
        return -1;
    }

    private void igualar(LinkedList<Errores> err, int p) {
        operadores.remove(p);
        Variable v = new Variable();
        int aux = p + 1;
        String id1 = ids.get(p).getId().getFirst();
        String id2 = ids.get(aux).getId().getFirst();
        String t1 = ids.get(p).getTipo();
        String t2 = ids.get(aux).getTipo();
        int amb = ids.getFirst().getAmb();
        boolean v1 = ids.get(p).isVariant();
        boolean v2 = ids.get(aux).isVariant();
        int linea = ids.get(p).getLinea();
        if (!v1 && !v2) {
            if (!t1.equals(t2)) {
                int l = ids.get(p).getLinea();
                String lex = id1 + "=" + id2;
                String msj = "No se puede igualar un: " + t1 + " a " + t2;
                boolean error;
                switch (t1) {
                    case "EXP":
                        error = !(t2.equals("REAL") || t2.equals("INT") || t2.equals("CHAR"));
                        break;
                    case "REAL":
                        error = !(t2.equals("INT") || t2.equals("CHAR"));
                        break;
                    case "INT":
                        error = !(t2.equals("CHAR"));
                        break;
                    default:
                        error = true;
                }
                if (error) {
                    v.setVariant(true);
                    err.add(new Errores(l, 807, lex, msj, "Semantica:Etapa 1", amb));
                    this.getSemanticaE_1().setErr();
                } else {
                    v.setVariant(false);
                    v.setTipo(t1);
                }
            } else {
                v.setVariant(false);
                v.setTipo(t1);
            }
        } else if (v1 && v2) {
            v.setVariant(true);
        } else if (v1 && !v2) {
            v.setVariant(false);
            v.setTipo(t1);
        } else if (!v1 && v2) {
            v.setVariant(false);
            v.setTipo(t2);
        } else {
            System.err.println("oh oh");
        }
        v.setLinea(linea);
        v.setId(id1);
        ids.remove(aux);
        ids.remove(p);
        ids.add(v);
    }

    private void ejecutar(int p, LinkedList<Errores> err, String c) {
        Variable v = new Variable();
        int aux = p + 1;
        boolean v1 = ids.get(p).isVariant();
        boolean v2 = ids.get(aux).isVariant();
        String id1 = ids.get(p).getId().getFirst();
        String id2 = ids.get(aux).getId().getFirst();
        String t1 = ids.get(p).getTipo();
        String t2 = ids.get(aux).getTipo();
        String tipo = "";
        String tope = ids.get(p).getTope();
        int linea = ids.get(p).getLinea();
        clave = ids.get(p).getClave();
        if (v1 || v2) {
            if (!v1 && v2) {
                tipo = t1;
                v = tipo(tipo, v, c);
            } else if (v1 && !v2) {
                tipo = t2;
                v = tipo(tipo, v, c);
            } else {
                v.setVariant(true);
                this.getSemanticaE_1().settV();
            }
        } else {
            v = tipo(t1, t2, v, c);
        }
        if (v.getTipo().equals("ERROR")) {
            int l = ids.get(p).getLinea();
            int amb = ids.get(p).getAmb();
            String msj = "No se puede desarrollar una " + v.getOp() + " de " + tipo;
            err.add(new Errores(l, v.getTE(), id1 + c + id2, msj, "Semantica:Etapa 1", amb));
            this.getSemanticaE_1().setErr();
            v.setVariant(true);
            this.getSemanticaE_1().settV();
        }
        ids.remove(aux);
        ids.remove(p);
        v.setId(id1);
        v.setTope(tope);
        v.setLinea(linea);
        ids.add(p, v);
        operadores.remove(p);
    }

    private Variable tipo(String t1, String t2, Variable v, String c) {
        int fila = sacarFilaColumaMD(t1);
        int col = sacarFilaColumaMD(t2);
        ponerTipo(v, c, fila, col);
        return v;
    }

    private Variable tipo(String t1, Variable v, String c) {
        int fila = sacarFilaColumaMD(t1);
        int col = sacarFilaColumaMD(t1);
        ponerTipo(v, c, fila, col);
        return v;
    }

    private void ponerTipo(Variable v, String c, int fila, int col) {
        switch (c) {
            case "/":
                v.setTipo(matrizDiv[fila][col]);
                v.setOp("division");
                v.setTE(800);
                break;
            case "*":
                v.setTipo(matrizMultiplicacion[fila][col]);
                v.setOp("multiplicacion");
                v.setTE(801);
                break;
            case "+":
                v.setTipo(matrizSuma[fila][col]);
                v.setOp("suma");
                v.setTE(802);
                break;
            case "-":
                v.setTipo(matrizResta[fila][col]);
                v.setOp("resta");
                v.setTE(803);
                break;
            case "<":
            case ">=":
            case "=>":
            case "<=":
            case "=<":
            case "!=":
            case ">":
            case "=="://igual o ==
                v.setTipo(matrizRelacionales[fila][col]);
                v.setOp("relacional");
                v.setTE(804);
                break;
            case "#":
            case "&":
            case "&&":
            case "||":
            case "|":
                v.setTipo(matrizLogicos[fila][col]);
                v.setOp("logicos");
                v.setTE(805);
                break;
            case "%":
                v.setTipo(matrizResiduos[fila][col]);
                v.setOp("residuo");
                v.setTE(806);
                break;
            case "ERROR":
                v.setVariant(true);
                v.setOp(c);
                v.setTE(807);
                break;
        }
        if (clave != 1010 && clave != 1011 && clave != 1012) {
            this.getSemanticaE_1().calcularTipo(v.getTipo());
        }
    }

    private int sacarFilaColumaMD(String in) {
        for (int i = 0; i < entradas.length; i++) {
            if (entradas[i].equals(in)) {
                return i;
            }
        }
        return -1;
    }

    public LinkedList<Variable> getIds() {
        return ids;
    }

    public LinkedList<String> getOperadores() {
        return operadores;
    }

    public Semantica_E_1 getSemanticaE_1() {
        return pantalla.getsE_1().getLast();
    }

    public void setSemanticaE_1() {
        this.pantalla.setsE_1();
    }

}
