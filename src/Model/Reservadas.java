package Model;

/**
 *
 * @author Chriss Yañez
 */
public class Reservadas {
    
    private final String palabra;
    private final int token;

    public Reservadas(String palabra, int token) {
        this.palabra = palabra;
        this.token = token;
    }

    public String getPalabra() {
        return palabra;
    }

    public int getToken() {
        return token;
    }
    
}
