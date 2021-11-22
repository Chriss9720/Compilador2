package Model;

import Controller.GenerarExcel;
import Vista.Edut;
import Vista.Pantalla;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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
        if (e.getSource() instanceof JButton) {
            switch (((JButton) e.getSource()).getName()) {
                case "edit":
                    new Edut(pantalla).setVisible(true);
                    break;
                case "Gen":
                    new GenerarExcel(pantalla).ejecutar();
                    break;
            }
        }
    }

}
