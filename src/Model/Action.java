package Model;

import Controller.GenerarExcel;
import Vista.Pantalla;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gonza
 */
public class Action implements ActionListener {

    private final Pantalla pantalla;

    public Action(Pantalla pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new GenerarExcel(pantalla).ejecutar();
    }

}
