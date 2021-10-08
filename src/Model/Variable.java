package Model;

import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author Gonza
 */
public class Variable extends Ids {
    
    private final LinkedList<String> id;
    private String op;
    private int TE;
    private int clave;
    private String tope;
    
    public Variable() {
        this.id = new LinkedList();
        this.tipo = "";
        this.clase = "";
        this.amb = 0;
        this.tArr = 0;
        this.dimArr = "";
        this.noPar = 0;
        this.tPar = "";
        this.op = "";
        this.TE = 800;
        this.clave = 0;
        tope = "";
    }

    public String getTope() {
        return tope;
    }

    public void setTope(String tope) {
        this.tope = tope;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public int getTE() {
        return TE;
    }

    public void setTE(int TE) {
        this.TE = TE;
    }
    
    public Variable(LinkedList<String> id) {
        this.id = id;
    }
    
    public Variable(Variable v) {
        super(v.getClase(), v.getTipo(), v.getDimArr(), v.getAmb(),
                v.getLinea(), v.gettArr(), v.getNoPar(), v.gettPar(),
                v.isError(), v.isVariant());
        this.id = v.getId();
    }
    
    public Variable Cargar(ResultSet rs) {
        try {
            this.id.add(rs.getString("id"));
            this.tipo = rs.getString("tipo");
            this.clase = rs.getString("clase");
            if (tipo.equals("CHAR") && (clase.contains("Arr") || clase.contains("arr"))) {
                tipo += "[]";
            }
            this.amb = rs.getInt("amb");
            this.tArr = rs.getInt("tarr");
            this.dimArr = rs.getString("dimArr");
            this.noPar = rs.getInt("noPar");
            this.tPar = rs.getString("tPar");
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e);
        }
        
        return this;
    }
    
    public LinkedList<String> getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id.add(id);
    }
    
    @Override
    public String toString() {
        String ids = "";
        ids = getId().stream().map(item -> item + " ").reduce(ids, String::concat);
        return "ID: " + ids + "\ntipo: " + this.getTipo()
                + "\nClase: " + this.getClase() + "\nAmb: " + this.getAmb()
                + "\nTarr: " + this.gettArr() + "\nDimArr: " + this.getDimArr()
                + "\nNoPar: " + this.getNoPar() + "\nTParr: " + this.gettPar()
                + "\n-----------------------------------------------------";
    }
    
    public String Semantica() {
        return "ID: " + id.getFirst() + "\tTipo: " + tipo + "\tVariante: " + variant;
    }
    
}
