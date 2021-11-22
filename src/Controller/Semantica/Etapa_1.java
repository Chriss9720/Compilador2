package Controller.Semantica;

import Model.Cuadruplos_1;
import Model.Cuadruplos_Contadores;
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
    private int ambAct;

    public Etapa_1(Pantalla pantalla) {
        this.pantalla = pantalla;
    }

    public void Reiniciar() {
        ids = new LinkedList();
        operadores = new LinkedList();
    }

    public void mostrarEcuacion() {
        String e = "";
        LinkedList<Variable> idsAux = new LinkedList();
        LinkedList<String> operadoresAux = new LinkedList();
        getIds().forEach(i -> idsAux.add(new Variable(i)));
        getOperadores().forEach(i -> operadoresAux.add(i));
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

    public LinkedList<Errores> Resolver(boolean s1, int amb) {
        ambAct = amb;
        filtro();
        if (s1) {
            getCuadruplos().add(new Cuadruplos_1());
        }
        LinkedList<Errores> err = new LinkedList();
        ResolverEcuacion(err, s1);
        return err;
    }

    private void filtro() {
        ids.stream().filter(i -> i.getClase().contains("funcion"))
                .forEachOrdered(i -> i.getId().set(0, "Tdef" + i.getId().getFirst()));
    }

    private void ResolverEcuacion(LinkedList<Errores> err, boolean s1) {
        String[] oprs = new String[]{"/", "*", "#", "&", "%", "&&"};
        int p = BuscarOp(oprs);
        while (p != -1) {
            ejecutar(p, err, operadores.get(p), s1);
            p = BuscarOp(oprs);
        }
        oprs = new String[]{"<", ">=", "=>", "<=", "=<", "!=", "==", ">"};
        p = BuscarOp(oprs);
        while (p != -1) {
            ejecutar(p, err, operadores.get(p), s1);
            p = BuscarOp(oprs);
        }
        oprs = new String[]{"+", "-", "|", "||"};
        p = BuscarOp(oprs);
        while (p != -1) {
            ejecutar(p, err, operadores.get(p), s1);
            p = BuscarOp(oprs);
        }
        p = BuscarIguales();
        while (p != -1) {
            igualar(err, p, s1);
            p = BuscarIguales();
        }
    }

    private int BuscarOp(String[] opers) {
        for (int i = 0; i < operadores.size(); i++) {
            if (BuscarOper(opers, operadores.get(i))) {
                filtroOper(operadores.get(i));
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

    private void igualar(LinkedList<Errores> err, int p, boolean s1) {
        operadores.remove(p);
        Variable v = new Variable();
        int aux = p + 1;
        String id1 = ids.get(p).getId().getFirst();
        String id2 = ids.get(aux).getId().getFirst();
        if (!isErrC()) {
            getCuadruplos().add(new Cuadruplos_1());
            getCuadruplos().getLast().setAccion("=");
            getCuadruplos().getLast().setArg1(id1);
            getCuadruplos().getLast().setResultado(id2);
            getCuadruplosCont().get(ambAct).setIgual();
        }
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
                    setErrC();
                    v.setVariant(true);
                    err.add(new Errores(l, 807, lex, msj, "Semantica:Etapa 1", amb));
                    if (s1) {
                        getSemanticaE_1().setErr();
                    }
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
            setErrC();
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

    private void ejecutar(int p, LinkedList<Errores> err, String c, boolean s1) {
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
        if (!isErrC()) {
            if (!getCuadruplos().getLast().getArg2().isEmpty()) {
                getCuadruplos().add(new Cuadruplos_1());
            }
            getCuadruplos().getLast().setArg1(id1);
            getCuadruplos().getLast().setArg2(id2);
        }
        if (v1 || v2) {
            if (!v1 && v2) {
                tipo = t1;
                v = tipo(tipo, v, c, s1);
            } else if (v1 && !v2) {
                tipo = t2;
                v = tipo(tipo, v, c, s1);
            } else {
                v.setVariant(true);
                setErrC();
                if (s1) {
                    getSemanticaE_1().settV();
                }
            }
        } else {
            v = tipo(t1, t2, v, c, s1);
        }
        if (v.getTipo().equals("ERROR")) {
            int l = ids.get(p).getLinea();
            int amb = ids.get(p).getAmb();
            String msj = "No se puede desarrollar una " + v.getOp() + " de " + tipo;
            err.add(new Errores(l, v.getTE(), id1 + c + id2, msj, "Semantica:Etapa 1", amb));
            if (s1) {
                getSemanticaE_1().setErr();
            }
            v.setVariant(true);
            setErrC();
            if (s1) {
                getSemanticaE_1().settV();
            }
        }
        ids.remove(aux);
        ids.remove(p);
        if (v.getId().isEmpty()) {
            v.setId(id1);
        }
        v.setTope(tope);
        v.setLinea(linea);
        ids.add(p, v);
        operadores.remove(p);
    }

    private Variable tipo(String t1, String t2, Variable v, String c, boolean s1) {
        int fila = sacarFilaColumaMD(t1);
        int col = sacarFilaColumaMD(t2);
        ponerTipo(v, c, fila, col, s1);
        return v;
    }

    private Variable tipo(String t1, Variable v, String c, boolean s1) {
        int fila = sacarFilaColumaMD(t1);
        int col = sacarFilaColumaMD(t1);
        ponerTipo(v, c, fila, col, s1);
        return v;
    }

    private void ponerTipo(Variable v, String c, int fila, int col, boolean s1) {
        if (fila == -1 || col == -1) {
            c = "ERROR";
            setErrC();
        }
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
            case "==":
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
                setErrC();
                break;
        }
        if (!isErrC()) {
            getCuadruplos().getLast().setAccion(c);
            v.setId(temporal(v.getTipo()));
            getCuadruplos().getLast().setResultado(v.getId().getLast());
        }
        if (s1) {
            getSemanticaE_1().calcularTipo(v.getTipo());
        }
    }

    private void filtroOper(String oper) {
        if (!isErrC()) {
            switch (oper) {
                case "|":
                case "||":
                case "&":
                case "&&":
                case "!":
                    getCuadruplosCont().get(ambAct).setOperLog();
                    break;
                case "+":
                case "-":
                case "/":
                case "*":
                    getCuadruplosCont().get(ambAct).setOperArit();
                    break;
                case "<":
                case "<=":
                case "=<":
                case ">":
                case ">=":
                case "=>":
                case "!=":
                case "==":
                    getCuadruplosCont().get(ambAct).setOpRel();
                    break;
            }
        }
    }

    private String temporal(String tipo) {
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

    public int contieneIguales() {
        for (String o : operadores) {
            if (o.equals("=")) {
                return 2;
            }
        }
        return 0;
    }

    public boolean contieneDecOInc() {
        return operadores.stream().anyMatch(o -> (o.equals("+") || o.equals("-")
                || o.equals("*") || o.equals("/")));
    }

    public LinkedList<String> getOperadores() {
        return operadores;
    }

    public Semantica_E_1 getSemanticaE_1() {
        return pantalla.getsE_1().getLast();
    }

    public void setSemanticaE_1() {
        pantalla.setsE_1();
    }

    public LinkedList<Cuadruplos_1> getCuadruplos() {
        return pantalla.getCuadruplos();
    }

    public LinkedList<Cuadruplos_Contadores> getCuadruplosCont() {
        return pantalla.getCuadruplosCont();
    }

    public boolean isErrC() {
        return pantalla.isErrC();
    }

    public void setErrC() {
        pantalla.setErrC();
    }

}
