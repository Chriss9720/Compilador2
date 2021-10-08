package Model;

import Controller.Cargar;
import Controller.Compilador;
import Controller.GenerarExcel;
import Vista.Pantalla;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Chriss Yañez
 */
public class Keys implements KeyListener {

    private final Pantalla pantalla;
    private final Dimension d;

    public Keys(Pantalla pantalla, Dimension d) {
        this.pantalla = pantalla;
        this.d = d;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_F1:
                new Cargar(pantalla.getCodigo(), pantalla.getNumeroLinea(), pantalla.getPanelCodigo(), d).ejecutar();
                break;
            case KeyEvent.VK_F2:
                new Compilador(pantalla).ejecutar();
                break;
            case KeyEvent.VK_F3:
                new GenerarExcel(pantalla).ejecutar();
                break;
            case KeyEvent.VK_F4:
                pantalla.getCodigo().setText("");
                pantalla.getNumeroLinea().setText("1\n");
                pantalla.getCodigo().setPreferredSize(d);
                pantalla.getNumeroLinea().setPreferredSize(new Dimension(55, d.height));
                pantalla.getPanelCodigo().setPreferredSize(d);
                pantalla.confTabla(pantalla.getTokens(), new String[]{"Linea", "Token", "Lexema"});
                pantalla.confTabla(pantalla.getErrores(), new String[]{"Token", "Descripción", "Lexema", "Tipo", "Linea"});
                pantalla.confTabla(pantalla.getContadores(), new String[]{"Nombre", "Cantidad"});
                break;
        }
    }

}
