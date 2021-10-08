package Controller;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Chriss Yañez
 */
public class Cargar implements ActionListener {

    private final JTextArea area, num;
    private final JPanel panelCodigo;
    private final Dimension d;

    public Cargar(JTextArea area, JTextArea num, JPanel panelCodigo, Dimension d) {
        this.area = area;
        this.num = num;
        this.panelCodigo = panelCodigo;
        this.d = d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ejecutar();
    }

    public void ejecutar() {
        File fsv = FileSystemView.getFileSystemView().getHomeDirectory();
        JFileChooser jfc = new JFileChooser(fsv);
        jfc.setDialogTitle("Selecione un archivo para Abrir");
        jfc.setAcceptAllFileFilterUsed(false);
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            FileReader fr = null;
            try {
                File fichero = jfc.getSelectedFile();
                fr = new FileReader(fichero);
                BufferedReader br = new BufferedReader(fr);
                String linea = br.readLine(), linea2, numero;
                int line = 0, space = linea.length() * 12;
                numero = String.valueOf(++line) + "\n";
                while ((linea2 = br.readLine()) != null) {
                    numero += String.valueOf(++line) + "\n";
                    linea += "\n" + linea2;
                    if (space < linea2.length() * 12) {
                        space = linea2.length() * 12;
                    }
                }
                area.setText(linea);
                num.setText(numero);
                line = line * 28;
                panelCodigo.setPreferredSize(new Dimension(d));
                if (line > panelCodigo.getPreferredSize().height) {
                    panelCodigo.setPreferredSize(new Dimension(panelCodigo.getPreferredSize().width,
                            line));
                    Rectangle r = area.getBounds();
                    r.height = panelCodigo.getPreferredSize().height;
                    area.setBounds(r);
                    r = num.getBounds();
                    r.height = area.getBounds().height;
                    num.setBounds(r);
                }
                if (space > panelCodigo.getPreferredSize().width) {
                    panelCodigo.setPreferredSize(new Dimension(space, panelCodigo.getPreferredSize().height));
                    Rectangle r = area.getBounds();
                    r.width = panelCodigo.getPreferredSize().width;
                    area.setBounds(r);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Cargar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Cargar.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (fr != null) {
                        fr.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Cargar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
