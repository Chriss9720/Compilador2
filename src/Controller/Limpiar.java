package Controller;

import Vista.Pantalla;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Chriss Yañez
 */
public class Limpiar implements ActionListener {
    
    private final Pantalla pantalla;
    private final Dimension d;

    public Limpiar(Pantalla pantalla, Dimension d) {
        this.pantalla = pantalla;
        this.d = d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pantalla.getCodigo().setText("");
        pantalla.getNumeroLinea().setText("1\n");
        pantalla.getCodigo().setPreferredSize(d);
        pantalla.getNumeroLinea().setPreferredSize(new Dimension(55, d.height));
        pantalla.getPanelCodigo().setPreferredSize(d);
        pantalla.confTabla(pantalla.getTokens(), new String[]{"Linea", "Token", "Lexema"});
        pantalla.confTabla(pantalla.getErrores(), new String[]{"Token", "Descripción", "Lexema", "Tipo", "Linea"});
        pantalla.confTabla(pantalla.getContadores(), new String[]{"Nombre", "Cantidad"});
    }
}
