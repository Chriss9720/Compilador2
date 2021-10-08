package Model;

/**
 *
 * @author Gonza
 */
public class ObjTemp extends Ids {

    private String id;
    private boolean error;

    public ObjTemp() {
        this.tipo = "";
        this.amb = 0;
        this.tArr = 0;
        this.dimArr = "";
        this.clase = "";
        this.id = "";
        this.error = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

}
