package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Gonza
 */
public class Edut extends JDialog {
    
    private final Pantalla p;
    
    public Edut(Pantalla p) {
        this.p = p;
        init();
    }
    
    private void init() {
        this.setTitle("Area de edicion sencilla, puse ESC para guardar");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JScrollPane scroll = new JScrollPane();
        JTextArea area = new JTextArea();
        area.setText(p.getCodigo().getText());
        area.setBackground(new Color(18, 30, 49));
        area.setForeground(Color.WHITE);
        area.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        area.setCaretColor(Color.RED);
        area.setTabSize(4);
        area.addKeyListener(new Key(this, p));
        scroll.setViewportView(area);
        this.add(scroll);
        this.setSize(p.getSize());
    }
    
    private class Key implements KeyListener {
        
        private final Edut dialog;
        private final Pantalla p;
        
        public Key(Edut dialog, Pantalla p) {
            this.dialog = dialog;
            this.p = p;
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    if (e.getSource() instanceof JTextArea) {
                        JTextArea cod = (JTextArea) e.getSource();
                        p.getCodigo().setText(cod.getText());
                        dialog.dispose();
                    }
                    break;
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
        }
        
    }
    
}
