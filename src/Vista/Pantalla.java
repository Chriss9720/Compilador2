package Vista;

import Controller.Area;
import Controller.Cargar;
import Controller.Compilador;
import Controller.Limpiar;
import Model.Action;
import Model.Ambito;
import Model.Keys;
import Model.Semantica_E_1;
import Model.Semantica_E_2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chriss Yañez
 */
public class Pantalla extends JFrame {

    private final JScrollPane scroll = new JScrollPane();
    private JScrollPane scrollCodigo;
    private final JPanel panel = new JPanel();
    private JTextArea codigo, numeroLinea;
    private JTable contadores, errores, tokens;
    private JPanel panelCodigo;
    private final JLabel tiempo = new JLabel("0000");
    private LinkedList<Ambito> amb = new LinkedList();
    private LinkedList<Semantica_E_1> sE_1 = new LinkedList();
    private LinkedList<Semantica_E_2> sE_2 = new LinkedList();

    public Pantalla() {
        init();
    }

    private void init() {
        this.setTitle("Compilador de Yañez Gonzalez Christian Emmanuel");
        Dimension t = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(t);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1950, (int) (t.height * 1.16)));
        panel.setBackground(Color.GRAY);
        codigo();
        scroll.setViewportView(panel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setLocationRelativeTo(null);
        this.add(scroll);
        this.setVisible(true);
    }

    private void codigo() {
        scrollCodigo = new JScrollPane();
        getScrollCodigo().getVerticalScrollBar().setValueIsAdjusting(false);

        panelCodigo = new JPanel();
        panelCodigo.setLayout(null);

        Dimension d = new Dimension((int) (this.getSize().width * .6),
                (int) (this.getSize().height * .8));

        codigo = new JTextArea();
        codigo.setBackground(new Color(18, 30, 49));
        codigo.setForeground(Color.WHITE);
        codigo.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        codigo.setBounds(60, 0, d.width, d.height);
        codigo.setCaretColor(Color.RED);
        codigo.setTabSize(4);
        panelCodigo.add(codigo);

        numeroLinea = new JTextArea("1\n");
        numeroLinea.setBackground(new Color(18, 30, 49));
        numeroLinea.setForeground(Color.WHITE);
        numeroLinea.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        numeroLinea.setBounds(3, 0, 55, d.height);
        numeroLinea.setEditable(false);
        panelCodigo.add(numeroLinea);

        panelCodigo.setBounds(0, 60, d.width, d.height);
        panelCodigo.setPreferredSize(d);
        scrollCodigo.setViewportView(panelCodigo);
        scrollCodigo.setBounds(0, 60, d.width + 60, d.height + 5);

        Point point = new Point(scrollCodigo.getBounds().x + scrollCodigo.getBounds().width + 10, 10);

        JButton cargar = new JButton("Cargar(F1)");
        cargar.setFont(codigo.getFont());
        cargar.setBounds(point.x, point.y, cargar.getPreferredSize().width, cargar.getPreferredSize().height);

        JButton ejecutar = new JButton("Ejecutar(F2)");
        ejecutar.setName("Ejec");
        ejecutar.setFont(codigo.getFont());
        ejecutar.setBounds(cargar.getBounds().x + cargar.getBounds().width
                + 10, point.y, ejecutar.getPreferredSize().width, ejecutar.getPreferredSize().height);

        JButton generar = new JButton("Generar XLSX(F3)");
        generar.setName("Gen");
        generar.setFont(codigo.getFont());
        generar.setBounds(ejecutar.getBounds().x + ejecutar.getBounds().width
                + 10, point.y, generar.getPreferredSize().width, generar.getPreferredSize().height);

        JButton limpiar = new JButton("Limpiar(F4)");
        limpiar.setFont(codigo.getFont());
        limpiar.setBounds(generar.getBounds().x + generar.getBounds().width
                + 10, point.y, limpiar.getPreferredSize().width, limpiar.getPreferredSize().height);

        JPanel cod = new JPanel();
        JLabel label = new JLabel("Código");
        label.setFont(codigo.getFont());
        label.setForeground(Color.WHITE);
        cod.setBackground(codigo.getBackground());
        cod.setBounds(60, 10, codigo.getWidth(), 40);
        cod.add(label);

        JPanel tituloTokens = new JPanel();
        JLabel labelToken = new JLabel("Lista de Tokens");
        labelToken.setFont(codigo.getFont());
        tituloTokens.setBackground(new Color(51, 212, 156));
        tituloTokens.setBounds(point.x, scrollCodigo.getLocation().y, 505, 40);
        tituloTokens.add(labelToken);

        JScrollPane scrollTokens = new JScrollPane();
        tokens = new JTable();
        confTabla(tokens, new String[]{"Linea", "Token", "Lexema"});
        scrollTokens.setViewportView(tokens);
        scrollTokens.setBounds(point.x, scrollCodigo.getLocation().y
                + tituloTokens.getSize().height, tituloTokens.getSize().width,
                codigo.getSize().height - tituloTokens.getSize().height);

        JPanel tituloErrores = new JPanel();
        JLabel labelErrores = new JLabel("Lista de Erroes");
        labelErrores.setFont(codigo.getFont());
        labelErrores.setForeground(Color.WHITE);
        tituloErrores.setBackground(Color.RED);
        tituloErrores.setBounds(codigo.getBounds().x,
                scrollCodigo.getBounds().y + scrollCodigo.getBounds().height + 10,
                codigo.getBounds().width, 40);
        tituloErrores.add(labelErrores);

        JScrollPane scrollErrores = new JScrollPane();
        errores = new JTable();
        confTabla(errores, new String[]{"Token", "Descripción", "Lexema", "Tipo", "Linea"});
        scrollErrores.setBounds(tituloErrores.getBounds().x,
                tituloErrores.getBounds().y + tituloErrores.getBounds().height,
                tituloErrores.getBounds().width, (int) (d.height * .3));
        scrollErrores.setViewportView(errores);

        JPanel tituloContadores = new JPanel();
        JLabel labelContadores = new JLabel("Lista de Contadores");
        labelContadores.setFont(codigo.getFont());
        tituloContadores.setBackground(new Color(29, 230, 200));
        tituloContadores.setBounds(tituloTokens.getLocation().x,
                tituloErrores.getLocation().y, tituloTokens.getSize().width,
                tituloTokens.getSize().height);
        tituloContadores.add(labelContadores);

        JScrollPane scrollCont = new JScrollPane();
        contadores = new JTable();
        confTabla(contadores, new String[]{"Nombre", "Cantidad"});
        scrollCont.setBounds(tituloContadores.getBounds().x,
                tituloContadores.getBounds().y + tituloContadores.getBounds().height,
                tituloContadores.getBounds().width, scrollErrores.getSize().height);
        scrollCont.setViewportView(contadores);

        codigo.addKeyListener(new Area(codigo, numeroLinea, panelCodigo, d, getScrollCodigo()));
        ejecutar.addActionListener(new Compilador(this));
        generar.addActionListener(new Action(this));
        cargar.setPreferredSize(new Dimension(cargar.getBounds().width, cargar.getBounds().height));
        cargar.addActionListener(new Cargar(codigo, numeroLinea, panelCodigo, d));
        limpiar.addActionListener(new Limpiar(this, d));

        codigo.addKeyListener(new Keys(this, d));
        ejecutar.addKeyListener(new Keys(this, d));
        generar.addKeyListener(new Keys(this, d));
        cargar.addKeyListener(new Keys(this, d));
        limpiar.addKeyListener(new Keys(this, d));
        this.addKeyListener(new Keys(this, d));

        tiempo.setBounds(5, 5, 500, 50);
        tiempo.setFont(codigo.getFont());
        tiempo.setForeground(Color.WHITE);

        panel.add(tiempo);
        panel.add(scrollCont);
        panel.add(tituloContadores);
        panel.add(scrollErrores);
        panel.add(tituloErrores);
        panel.add(scrollTokens);
        panel.add(tituloTokens);
        panel.add(cod);
        panel.add(cargar);
        panel.add(ejecutar);
        panel.add(generar);
        panel.add(limpiar);
        panel.add(scrollCodigo);
    }

    public void confTabla(JTable table, String[] titulos) {
        table.setModel(new DefaultTableModel(new Object[][]{}, titulos));
        table.setFont(codigo.getFont());
        table.setBackground(codigo.getBackground());
        table.setForeground(codigo.getForeground());
        table.setEnabled(false);
        table.setRowHeight(30);
        table.getTableHeader().setFont(table.getFont());
    }

    public JTextArea getCodigo() {
        return codigo;
    }

    public JTable getContadores() {
        return contadores;
    }

    public JTable getErrores() {
        return errores;
    }

    public JTable getTokens() {
        return tokens;
    }

    public JTextArea getNumeroLinea() {
        return numeroLinea;
    }

    public JPanel getPanelCodigo() {
        return panelCodigo;
    }

    public JLabel getTiempo() {
        return tiempo;
    }

    public LinkedList<Ambito> getAmb() {
        return amb;
    }

    public void setAmb(LinkedList<Ambito> amb) {
        this.amb = amb;
    }

    public JScrollPane getScrollCodigo() {
        return scrollCodigo;
    }

    public void setScrollCodigo(JScrollPane scrollCodigo) {
        this.scrollCodigo = scrollCodigo;
    }

    public void setY(int value) {
        this.scrollCodigo.getVerticalScrollBar().setValue(value);
    }

    public LinkedList<Semantica_E_1> getsE_1() {
        return sE_1;
    }

    public void setsE_1() {
        this.sE_1 = new LinkedList();
    }

    public LinkedList<Semantica_E_2> getsE_2() {
        return sE_2;
    }

    public void setsE_2() {
        this.sE_2 = new LinkedList();
    }

}
