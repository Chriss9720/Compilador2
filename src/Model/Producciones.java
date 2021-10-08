package Model;

/**
 *
 * @author Chriss Yañez
 */
public class Producciones {
    
    private final String produccion;
    private final String produce;

    public Producciones(String produccion, String produce) {
        this.produccion = produccion;
        this.produce = produce;
    }

    public String getProduccion() {
        return produccion;
    }

    public String getProduce() {
        return produce;
    }
    
}
