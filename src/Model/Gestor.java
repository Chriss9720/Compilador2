package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Gonza
 */
public class Gestor {

    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;
    private final Connect Connect = new Connect();
    private String sql;
    private LinkedList<Errores> err;

    public void BorrarTodo() {
        abrir();
        try {
            sql = "DELETE FROM ids";
            pst = con.prepareCall(sql);
            if (pst.executeUpdate() > 0) {
                cerrar();
            } else {
                cerrar();
            }
        } catch (SQLException e) {
            System.out.println("Error al resert: " + e);
        }
    }

    public LinkedList<Errores> gudarRegistro(Registro reg) {
        err = new LinkedList();
        LinkedList<Integer> auxAmb = new LinkedList();
        auxAmb.add(reg.amb);
        if (existe(reg.getId(), auxAmb) != null) {
            err.add(new Errores(reg.getLinea(), 700, reg.getId(),
                    "Se repitio el registro", "Ambito", reg.getAmb()));
        } else {
            abrir();
            try {
                sql = "INSERT INTO ids(id, tipo, clase, amb, tarr, "
                        + "dimArr, noPar, tPar) values(?,?,?,?,?,?,?,?)";
                pst = con.prepareCall(sql);
                pst.setString(1, reg.getId());
                pst.setString(2, "");
                pst.setString(3, reg.getClase());
                pst.setString(4, tS(reg.getAmb()));
                pst.setString(5, "");
                pst.setString(6, "");
                pst.setString(7, tS(reg.getNoPar()));
                pst.setString(8, reg.gettPar());
                if (pst.executeUpdate() > 0) {
                    cerrar();
                } else {
                    System.out.println("Fallo al registrar");
                }
            } catch (Exception e) {
                System.out.println("Fallo al hacer el registro: " + e);
            }
            cerrar();
        }
        return err;
    }

    public LinkedList<Errores> guadarItemRegistro(LinkedList<Variable> lista) {
        err = new LinkedList();
        try {
            int param = 1;
            do {
                do {
                    LinkedList<Integer> auxAmb = new LinkedList();
                    auxAmb.add(lista.getFirst().getAmb());
                    if (lista.getFirst().isError()) {
                        lista.getFirst().getId().removeFirst();
                    } else {
                        if (this.existe(lista.getFirst().getId().getFirst(), auxAmb) != null) {
                            err.add(new Errores(lista.getFirst().getLinea(),
                                    701, lista.getFirst().getId().getFirst(),
                                    "Se repitio el item", "Ambito", lista.getFirst().getAmb()));
                            lista.getFirst().getId().removeFirst();
                        } else {
                            abrir();
                            sql = "INSERT INTO ids(id, tipo, clase, amb, tarr, "
                                    + "dimArr, noPar, tPar) values(?,?,?,?,?,?,?,?)";
                            pst = con.prepareCall(sql);
                            pst.setString(1, lista.getFirst().getId().getFirst());
                            pst.setString(2, lista.getFirst().getTipo());
                            pst.setString(3, lista.getFirst().getClase());
                            pst.setString(4, tS(lista.getFirst().getAmb()));
                            pst.setString(5, tS(lista.getFirst().gettArr()));
                            pst.setString(6, lista.getFirst().getDimArr());
                            pst.setString(7, tS(param));
                            pst.setString(8, lista.getFirst().gettPar());
                            if (pst.executeUpdate() > 0) {
                                lista.getFirst().getId().removeFirst();
                            } else {
                                lista.getFirst().getId().removeFirst();
                                err.add(new Errores(lista.getFirst().getLinea(),
                                        701, lista.getFirst().getId().getFirst(),
                                        "Se repitio el item", "Ambito", lista.getFirst().getAmb()));
                            }
                            param++;
                        }
                    }
                    cerrar();
                } while (!lista.getFirst().getId().isEmpty());
                lista.removeFirst();
            } while (!lista.isEmpty());
            cerrar();
        } catch (Exception e) {
            System.out.println("Fallo al registrar el parametro: " + e);
        }
        cerrar();
        return err;
    }

    public LinkedList<Errores> guadarSimples(Variable v) {
        err = new LinkedList();
        try {
            do {
                LinkedList<Integer> auxAmb = new LinkedList();
                auxAmb.add(v.getAmb());
                if (v.isError()) {
                    v.getId().removeFirst();
                } else {
                    if (this.existe(v.getId().getFirst(), auxAmb) != null) {
                        err.add(new Errores(v.getLinea(), 702, v.getId().getFirst(),
                                "Se repitio la variable", "Ambito", v.getAmb()));
                        v.getId().removeFirst();
                    } else {
                        abrir();
                        sql = "INSERT INTO ids(id, tipo, clase, amb, tarr, "
                                + "dimArr, noPar, tPar) values(?,?,?,?,?,?,?,?)";
                        pst = con.prepareCall(sql);
                        pst.setString(1, v.getId().getFirst());
                        pst.setString(2, v.getTipo());
                        pst.setString(3, v.getClase());
                        pst.setString(4, tS(v.getAmb()));
                        pst.setString(5, tS(v.gettArr()));
                        pst.setString(6, v.getDimArr());
                        pst.setString(7, tS(v.getNoPar()));
                        pst.setString(8, v.gettPar());
                        if (pst.executeUpdate() > 0) {
                            v.getId().removeFirst();
                        } else {
                            err.add(new Errores(v.getLinea(), 702, v.getId().getFirst(),
                                    "Se repitio la variable", "Ambito", v.getAmb()));
                        }
                    }
                }
            } while (!v.getId().isEmpty());
            cerrar();
        } catch (Exception e) {
            System.out.println("Fallo al guardarSimple: " + e);
        }
        cerrar();
        return err;
    }

    public LinkedList<Errores> guardarConstante(Variable v) {
        err = new LinkedList();
        try {
            LinkedList<Integer> auxAmb = new LinkedList();
            auxAmb.add(v.getAmb());
            if (this.existe(v.getId().getFirst(), auxAmb) != null) {
                err.add(new Errores(v.getLinea(), 703, v.getId().getFirst(),
                        "Se repitio la constante", "Ambito", v.getAmb()));
            } else {
                abrir();
                sql = "INSERT INTO ids(id, tipo, clase, amb, tarr, "
                        + "dimArr, noPar, tPar) values(?,?,?,?,?,?,?,?)";
                pst = con.prepareCall(sql);
                pst.setString(1, v.getId().getFirst());
                pst.setString(2, v.getTipo());
                pst.setString(3, v.getClase());
                pst.setString(4, tS(v.getAmb()));
                pst.setString(5, tS(v.gettArr()));
                pst.setString(6, v.getDimArr());
                pst.setString(7, tS(v.getNoPar()));
                pst.setString(8, v.gettPar());
                boolean aux = (pst.executeUpdate() > 0);
                cerrar();
                if (!aux) {
                    err.add(new Errores(v.getLinea(), 703, v.getId().getFirst(),
                            "Se repitio la constante", "Ambito", v.getAmb()));
                }
            }
        } catch (Exception e) {
            System.out.println("Fallo al guardar la constante: " + v);
        }
        cerrar();
        return err;
    }

    public LinkedList<Errores> guardarFuncion(Funcion func) {
        err = new LinkedList();
        LinkedList<Integer> auxAmb = new LinkedList();
        auxAmb.add(func.amb);
        if (!func.isError()) {
            if (existe(func.getId(), auxAmb) != null) {
                err.add(new Errores(func.getLinea(), 704, func.getId(),
                        "Se repitio el item", "Ambito", func.getAmb()));
            } else {
                if (!func.isError()) {
                    abrir();
                    try {
                        sql = "INSERT INTO ids(id, tipo, clase, amb, tarr, "
                                + "dimArr, noPar, tPar) values(?,?,?,?,?,?,?,?)";
                        pst = con.prepareCall(sql);
                        pst.setString(1, func.getId());
                        pst.setString(2, func.getTipo());
                        pst.setString(3, func.getClase());
                        pst.setString(4, tS(func.getAmb()));
                        pst.setString(5, tS(func.gettArr()));
                        pst.setString(6, func.getDimArr());
                        pst.setString(7, tS(func.getNoPar()));
                        pst.setString(8, func.gettPar());
                        if (pst.executeUpdate() > 0) {
                            cerrar();
                        } else {
                            err.add(new Errores(func.getLinea(), 704, func.getId(),
                                    "Se repitio la variable (nombre de "
                                    + "la funcion)", "Ambito", func.getAmb()));
                        }
                    } catch (Exception e) {
                        System.out.println("Fallo al registrar la funcion: " + e);
                    }
                }
                cerrar();
            }
        }
        return err;
    }

    public Variable existe(String id, LinkedList<Integer> amb) {
        abrir();
        boolean r;
        Variable v;
        try {
            do {
                if (amb.isEmpty()) {
                    cerrar();
                    return null;
                }
                sql = "select TRIM(id) as id, TRIM(tipo) as tipo, "
                        + "TRIM(clase) as clase, TRIM(amb) as amb, "
                        + "TRIM(tarr) as tarr, TRIM(dimArr) as dimArr, "
                        + "TRIM(noPar) as noPar, TRIM(tPar) as tPar "
                        + "from ids where id = ? and amb = ? and tipo is not null";
                pst = con.prepareCall(sql);
                pst.setString(1, id);
                pst.setString(2, tS(amb.getLast()));
                rs = pst.executeQuery();
                r = rs.next();
                amb.removeLast();
                if (r) {
                    v = new Variable();
                    v.Cargar(rs);
                    cerrar();
                    return v;
                }
            } while (!r);
        } catch (Exception e) {
            System.out.println("Fallo al verificar si existe: " + e);
        }
        cerrar();
        return null;
    }

    public boolean validarREG(String id, LinkedList<Integer> amb) {
        abrir();
        boolean r = false;
        try {
            do {
                if (amb.isEmpty() || r) {
                    cerrar();
                    return r;
                }
                sql = "select * from ids where id = ? and amb = ? and clase = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, id);
                pst.setString(2, tS(amb.getLast()));
                pst.setString(3, "reg");
                rs = pst.executeQuery();
                r = rs.next();
                amb.removeLast();
            } while (!r);
        } catch (Exception e) {
            System.out.println("Error al buscar el reg: " + e);
        }
        cerrar();
        return r;
    }

    public int getTotal(int ambito, String tipo) {
        abrir();
        try {
            sql = "SELECT COUNT(*) AS TOTAL FROM ids WHERE tipo = ? AND amb = ?";
            pst = con.prepareCall(sql);
            pst.setString(1, tipo);
            pst.setString(2, tS(ambito));
            rs = pst.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (Exception e) {
            System.out.println("Fallo al sacar el tipo: " + tipo + " del hambito: " + ambito + "\n" + e);
        }
        cerrar();
        return 0;
    }

    public int getTotalChar(int ambito) {
        abrir();
        try {
            sql = "select count(*) as total from ids where tipo like \"CHAR\""
                    + " and clase not like \"%arr%\""
                    + " and amb = ?;";
            pst = con.prepareCall(sql);
            pst.setString(1, tS(ambito));
            rs = pst.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (Exception e) {
            System.out.println("Fallo al sacar el tipo: char del hambito: " + ambito + "\n" + e);
        }
        cerrar();
        return 0;
    }

    public int getTotalString(int ambito) {
        abrir();
        try {
            sql = "select count(*) as total from ids where tipo like \"char\""
                    + " and clase like \"%arr%\""
                    + " and amb = ?;";
            pst = con.prepareCall(sql);
            pst.setString(1, tS(ambito));
            rs = pst.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (Exception e) {
            System.out.println("Fallo al sacar el tipo: char del hambito: " + ambito + "\n" + e);
        }
        cerrar();
        return 0;
    }

    public int totalTipoAmb(int amb) {
        abrir();
        try {
            sql = "select count(*) as total from ids where amb = ? and tipo not like \"\";";
            pst = con.prepareCall(sql);
            pst.setString(1, tS(amb));
            rs = pst.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (Exception e) {
            System.out.println("Fallo al sacar el total de datos: " + e);
        }
        cerrar();
        return 0;
    }

    public int totalRegistros(int amb) {
        abrir();
        try {
            sql = "select count(*) as total from ids where amb = ? and tipo not like \"\" and clase LIKE \"%REG%\";";
            pst = con.prepareCall(sql);
            pst.setString(1, tS(amb));
            rs = pst.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (Exception e) {
            System.out.println("Fallo al sacar el total de datos: " + e);
        }
        cerrar();
        return 0;
    }

    private void abrir() {
        con = Connect.abrir();
    }

    private void cerrar() {
        if (con != null) {
            con = Connect.cerrar();
        }
    }

    private String tS(Object o) {
        return String.valueOf(o);
    }

}
