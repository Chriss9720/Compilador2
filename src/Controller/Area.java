package Controller;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Chriss Yañez
 */
public class Area extends KeyAdapter {

    private final JTextArea area, num;
    private final JPanel panelCodigo;
    private final Dimension d;
    private JScrollPane scrollCodigo;

    public Area(JTextArea area, JTextArea num, JPanel panelCodigo, Dimension d, JScrollPane scrollCodigo) {
        this.area = area;
        this.num = num;
        this.panelCodigo = panelCodigo;
        this.d = d;
        this.scrollCodigo = scrollCodigo;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String[] texts = area.getText().split("\n");
        int line = 0, space = 0;
        if (texts.length > 0) {
            space = texts[0].length() * 12;
        }
        String txt = String.valueOf(++line) + "\n";
        if (texts.length < area.getLineCount()) {
            String aux[] = new String[area.getLineCount()];
            for (int i = 0; i < area.getLineCount(); i++) {
                aux[i] = (i < texts.length) ? texts[i] : " ";
            }
            texts = aux;
        }
        for (int i = 1; i < texts.length; i++) {
            txt += String.valueOf(++line) + "\n";
            if (space < texts[i].length() * 12) {
                space = texts[i].length() * 12;
            }
        }
        num.setText(txt);
        line = line * 28;
        panelCodigo.setPreferredSize(new Dimension(d));
        if (line > panelCodigo.getPreferredSize().height) {
            panelCodigo.setPreferredSize(new Dimension(panelCodigo.getPreferredSize().width, line));
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
        area.setCaretPosition(area.getCaretPosition());
    }

}
