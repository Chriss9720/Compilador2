package Model;

/**
 *
 * @author Chriss Yañez
 */
public class Contadores {
    
    private final String nombre;
    private int cant;

    public Contadores(int cant, String nombre) {
        this.cant = cant;
        this.nombre = nombre;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getCant() {
        return cant;
    }

    public String getNombre() {
        return nombre;
    }
    
}
