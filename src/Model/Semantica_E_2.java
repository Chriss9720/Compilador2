package Model;

/**
 *
 * @author Gonza
 */
public class Semantica_E_2 {
    
    private final int regla;
    private final String topePila;
    private final String valorReal;
    private final int linea;
    private final String estado;
    private final int amb;

    public Semantica_E_2(int regla, String topePila, String valorReal, 
            int linea, String estado, int amb) {
        this.regla = regla;
        this.topePila = topePila;
        this.valorReal = valorReal;
        this.linea = linea;
        this.estado = estado;
        this.amb = amb;
    }

    public int getRegla() {
        return regla;
    }

    public String getTopePila() {
        return topePila;
    }

    public String getValorReal() {
        return valorReal;
    }

    public int getLinea() {
        return linea;
    }

    public String getEstado() {
        return estado;
    }

    public int getAmb() {
        return amb;
    }
    
    @Override
    public String toString() {
        return "Regla: " + regla 
                + "\nTope: " + topePila
                + "\nValor: " + valorReal
                + "\nLinea: " + linea
                + "\nEstado: " + estado
                + "\nAmb: " + amb
                + "\n*********************************";
    }
    
}
