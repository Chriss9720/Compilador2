package Controller;

import Model.Ambito;
import Model.Gestor;
import Model.Semantica_E_1;
import Model.Semantica_E_2;
import Model.Semantica_E_3;
import Model.Cuadruplos_1;
import Model.Cuadruplos_Contadores;
import Vista.Pantalla;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 *
 * @author Chriss Yañez
 */
public class GenerarExcel {

    private final JTable contadores;
    private final JTable errores;
    private final JTable tokens;
    private final LinkedList<Ambito> ambitos;
    private final Gestor gestor = new Gestor();
    private final LinkedList<Semantica_E_1> sE_1;
    private final LinkedList<Semantica_E_2> sE_2;
    private final LinkedList<Semantica_E_3> sE_3;
    private final LinkedList<Cuadruplos_1> c1;
    private final LinkedList<Cuadruplos_Contadores> cc;

    public GenerarExcel(Pantalla p) {
        this.contadores = p.getContadores();
        this.errores = p.getErrores();
        this.tokens = p.getTokens();
        this.ambitos = p.getAmb();
        this.sE_1 = p.getsE_1();
        this.sE_2 = p.getsE_2();
        this.sE_3 = p.getsE_3();
        this.c1 = p.getCuadruplos();
        this.cc = p.getCuadruplosCont();
    }

    public void ejecutar() {
        String nombreArchivo = "src/Excel/Yañez_Gonzalez_Christian_Emmanuel.xlsx";
        XSSFWorkbook libro = new XSSFWorkbook();

        XSSFSheet hoja1 = libro.createSheet("Tokens");
        String[] head = {"Estado", "Lexema", "Linea"};
        XSSFRow row = hoja1.createRow(0);
        XSSFCell cell;
        for (int i = 0; i < head.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(head[i]);
        }
        for (int i = 0, j = 1; i < tokens.getModel().getRowCount(); i++, j++) {
            row = hoja1.createRow(j);
            cell = row.createCell(0);
            cell.setCellValue(String.valueOf(tokens.getValueAt(i, 0)));
            cell = row.createCell(1);
            cell.setCellValue(String.valueOf(tokens.getValueAt(i, 1)));
            cell = row.createCell(2);
            cell.setCellValue(String.valueOf(tokens.getValueAt(i, 2)));
        }

        XSSFSheet hoja2 = libro.createSheet("Errores");
        head = new String[]{"Token", "Descripción", "Lexema", "Tipo", "Linea"};
        row = hoja2.createRow(0);
        for (int i = 0; i < head.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(head[i]);
        }
        for (int i = 0, j = 1; i < errores.getModel().getRowCount(); i++, j++) {
            row = hoja2.createRow(j);
            cell = row.createCell(0);
            cell.setCellValue(String.valueOf(errores.getValueAt(i, 0)));
            cell = row.createCell(1);
            cell.setCellValue(String.valueOf(errores.getValueAt(i, 1)));
            cell = row.createCell(2);
            cell.setCellValue(String.valueOf(errores.getValueAt(i, 2)));
            cell = row.createCell(3);
            cell.setCellValue(String.valueOf(errores.getValueAt(i, 3)));
            cell = row.createCell(4);
            cell.setCellValue(String.valueOf(errores.getValueAt(i, 4)));
        }

        XSSFSheet hoja3 = libro.createSheet("Contadores");
        row = hoja3.createRow(0);
        for (int i = 0; i < contadores.getModel().getRowCount(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(String.valueOf(contadores.getValueAt(i, 0)));
        }
        row = hoja3.createRow(1);
        for (int i = 0; i < contadores.getModel().getRowCount(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(String.valueOf(contadores.getValueAt(i, 1)));
        }

        XSSFSheet hoja4 = libro.createSheet("Ambito");
        head = new String[]{"Ambito", "CHAR", "String (CHAR[])", "INT", "REAL",
            "BOOL", "EXP", "REG", "VOID", "FILE", "ERRORES", "Total de ambito"};
        row = hoja4.createRow(0);
        for (int i = 0; i < head.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(head[i]);
        }

        Object[] total = new Object[]{"Total", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (Ambito amb : ambitos) {
            int aux = amb.getAmbito();
            row = hoja4.createRow((aux + 1));
            int[] data = {aux, totalChar(aux), totalString(aux),
                total(aux, "INT"), total(aux, "REAL"), total(aux, "BOOL"),
                total(aux, "EXP"), registros(aux), total(aux, "VOID"),
                total(aux, "FILE"), amb.getErrores(), totalAmb(aux)};
            for (int i = 1; i < total.length; i++) {
                total[i] = tI(total[i]) + data[i];
            }
            for (int i = 0; i < data.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(data[i]);
            }
        }

        row = hoja4.createRow((ambitos.getLast().getAmbito() + 2));
        for (int i = 0; i < total.length; i++) {
            cell = row.createCell(i);
            if (i == 0) {
                cell.setCellValue(tS(total[0]));
            } else {
                cell.setCellValue(tI(total[i]));
            }
        }

        XSSFSheet hoja5 = libro.createSheet("Semtica_Etapa_1");
        head = new String[]{"Linea", "Tch", "TS", "TE", "TR", "TB", "TX",
            "TR", "TO", "TF", "TV", "Asignacion", "Errores"};

        row = hoja5.createRow(0);
        for (int i = 0; i < head.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(head[i]);
        }
        for (int i = 0, j = 1; i < sE_1.size(); i++, j++) {
            String ext = "";
            row = hoja5.createRow(j);
            cell = row.createCell(0);
            cell.setCellValue(sE_1.get(i).getLinea());
            ext = evaluar(ext, sE_1.get(i).gettC(), row.createCell(1), "CH");
            ext = evaluar(ext, sE_1.get(i).gettS(), row.createCell(2), "S");
            ext = evaluar(ext, sE_1.get(i).gettE(), row.createCell(3), "E");
            ext = evaluar(ext, sE_1.get(i).gettR(), row.createCell(4), "R");
            ext = evaluar(ext, sE_1.get(i).gettB(), row.createCell(5), "B");
            ext = evaluar(ext, sE_1.get(i).gettX(), row.createCell(6), "X");
            ext = evaluar(ext, sE_1.get(i).gettG(), row.createCell(7), "G");
            ext = evaluar(ext, sE_1.get(i).gettO(), row.createCell(8), "O");
            ext = evaluar(ext, sE_1.get(i).gettF(), row.createCell(9), "F");
            ext = evaluar(ext, sE_1.get(i).gettV(), row.createCell(10), "V");
            cell = row.createCell(11);
            cell.setCellValue(sE_1.get(i).getAsig() + ext);
            cell = row.createCell(12);
            cell.setCellValue(sE_1.get(i).getErr());
        }

        XSSFSheet hoja6 = libro.createSheet("Semtica_Etapa_2");
        head = new String[]{"Regla", "Funcion", "Tope Pila", "Valor Real", "Linea", "Edo", "Ambito"};
        row = hoja6.createRow(0);
        for (int i = 0; i < head.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(head[i]);
        }
        for (int i = 0, j = 1; i < sE_2.size(); i++, j++) {
            row = hoja6.createRow(j);
            cell = row.createCell(0);
            cell.setCellValue(sE_2.get(i).getRegla());
            cell = row.createCell(1);
            cell.setCellValue(sE_2.get(i).getFuncion());
            cell = row.createCell(2);
            cell.setCellValue(sE_2.get(i).getTopePila());
            cell = row.createCell(3);
            cell.setCellValue(sE_2.get(i).getValorReal());
            cell = row.createCell(4);
            cell.setCellValue(sE_2.get(i).getLinea());
            cell = row.createCell(5);
            cell.setCellValue(sE_2.get(i).getEstado());
            cell = row.createCell(6);
            cell.setCellValue(sE_2.get(i).getAmb());
        }

        XSSFSheet hoja7 = libro.createSheet("Semtica_Etapa_3");
        head = new String[]{"Funcion", "Entradas", "Salidas", "Aceptados", "Errores"};
        row = hoja7.createRow(0);
        for (int i = 0; i < head.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(head[i]);
        }
        for (int i = 0, j = 1; i < sE_3.size(); i++, j++) {
            if (sE_3.get(i).getEntradas() > 0 || sE_3.get(i).getSalida() > 0
                    || sE_3.get(i).getAceptados() > 0 || sE_3.get(i).getErroes() > 0) {
                row = hoja7.createRow(j);
                cell = row.createCell(0);
                cell.setCellValue(sE_3.get(i).getFuncion());
                cell = row.createCell(1);
                cell.setCellValue(sE_3.get(i).getEntradas());
                cell = row.createCell(2);
                cell.setCellValue(sE_3.get(i).getSalida());
                cell = row.createCell(3);
                cell.setCellValue(sE_3.get(i).getAceptados());
                cell = row.createCell(4);
                cell.setCellValue(sE_3.get(i).getErroes());
            } else {
                j--;
            }
        }

        XSSFSheet hoja8 = libro.createSheet("Cl");
        head = new String[]{"Etiqueta", "Accion", "Arg1", "Arg2", "Resultado"};
        row = hoja8.createRow(0);
        for (int i = 0; i < head.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(head[i]);
        }
        for (int i = 0, j = 1; i < c1.size(); i++, j++) {
            row = hoja8.createRow(j);
            cell = row.createCell(0);
            cell.setCellValue(c1.get(i).getEtiqueta());
            cell = row.createCell(1);
            cell.setCellValue(c1.get(i).getAccion());
            cell = row.createCell(2);
            cell.setCellValue(c1.get(i).getArg1());
            cell = row.createCell(3);
            cell.setCellValue(c1.get(i).getArg2());
            cell = row.createCell(4);
            cell.setCellValue(c1.get(i).getResultado());
        }

        XSSFSheet hoja9 = libro.createSheet("Cont-CI");
        head = new String[]{"Ambitos", "TE", "TR", "TS", "TCH", "TEX", "TB",
            "TRX", "TF", "Arr", "call", "=", "Op-Rel", "Ope-Log", "Oper-Arit",
            "Op-Un", "JF", "JMP", "valor", "if-E", "SW-E", "For-E", "Whi-E",
            "DEF", "PPAL"};
        int[] totales = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        row = hoja9.createRow(0);
        for (int i = 0; i < head.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(head[i]);
        }
        for (int i = 0, j = 1; i < cc.size(); i++, j++) {
            row = hoja9.createRow(j);
            cell = row.createCell(0);
            cell.setCellValue(cc.get(i).getAmb());
            totales[0] += cc.get(i).getAmb();
            cell = row.createCell(1);
            cell.setCellValue(cc.get(i).getTE());
            totales[1] += cc.get(i).getTE();
            cell = row.createCell(2);
            cell.setCellValue(cc.get(i).getTR());
            totales[2] += cc.get(i).getTR();
            cell = row.createCell(3);
            cell.setCellValue(cc.get(i).getTS());
            totales[3] += cc.get(i).getTS();
            cell = row.createCell(4);
            cell.setCellValue(cc.get(i).getTCH());
            totales[4] += cc.get(i).getTCH();
            cell = row.createCell(5);
            cell.setCellValue(cc.get(i).getTEX());
            totales[5] += cc.get(i).getTEX();
            cell = row.createCell(6);
            cell.setCellValue(cc.get(i).getTB());
            totales[6] += cc.get(i).getTB();
            cell = row.createCell(7);
            cell.setCellValue(cc.get(i).getTRX());
            totales[7] += cc.get(i).getTRX();
            cell = row.createCell(8);
            cell.setCellValue(cc.get(i).getTF());
            totales[8] += cc.get(i).getTF();
            cell = row.createCell(9);
            cell.setCellValue(cc.get(i).getArr());
            totales[9] += cc.get(i).getArr();
            cell = row.createCell(10);
            cell.setCellValue(cc.get(i).getCall());
            totales[10] += cc.get(i).getCall();
            cell = row.createCell(11);
            cell.setCellValue(cc.get(i).getIgual());
            totales[11] += cc.get(i).getIgual();
            cell = row.createCell(12);
            cell.setCellValue(cc.get(i).getOpRel());
            totales[12] += cc.get(i).getOpRel();
            cell = row.createCell(13);
            cell.setCellValue(cc.get(i).getOperLog());
            totales[13] += cc.get(i).getOperLog();
            cell = row.createCell(14);
            cell.setCellValue(cc.get(i).getOperArit());
            totales[14] += cc.get(i).getOperArit();
            cell = row.createCell(15);
            cell.setCellValue(cc.get(i).getOperUn());
            totales[15] += cc.get(i).getOperUn();
            cell = row.createCell(16);
            cell.setCellValue(cc.get(i).getJF());
            totales[16] += cc.get(i).getJF();
            cell = row.createCell(17);
            cell.setCellValue(cc.get(i).getJMP());
            totales[17] += cc.get(i).getJMP();
            cell = row.createCell(18);
            cell.setCellValue(cc.get(i).getValor());
            totales[18] += cc.get(i).getValor();
            cell = row.createCell(19);
            cell.setCellValue(cc.get(i).getIfE());
            totales[19] += cc.get(i).getIfE();
            cell = row.createCell(20);
            cell.setCellValue(cc.get(i).getSWE());
            totales[20] += cc.get(i).getSWE();
            cell = row.createCell(21);
            cell.setCellValue(cc.get(i).getForE());
            totales[21] += cc.get(i).getForE();
            cell = row.createCell(22);
            cell.setCellValue(cc.get(i).getWhiE());
            totales[22] += cc.get(i).getWhiE();
            cell = row.createCell(23);
            cell.setCellValue(cc.get(i).getDEF());
            totales[23] += cc.get(i).getDEF();
            cell = row.createCell(24);
            cell.setCellValue(cc.get(i).getPPALL());
            totales[24] += cc.get(i).getPPALL();
        }
        row = hoja9.createRow(cc.size() + 1);
        for (int i = 0; i < totales.length; i++) {
            cell = row.createCell(i);
            if (i == 0) {
                cell.setCellValue("Totales");
            } else {
                cell.setCellValue(totales[i]);
            }
        }

        File file = new File(nombreArchivo);
        try (FileOutputStream fileOuS = new FileOutputStream(file)) {
            if (file.exists()) {
                file.delete();
            }
            libro.write(fileOuS);
            fileOuS.flush();
            fileOuS.close();
            JOptionPane.showMessageDialog(null, "Excel creado exitosamente", "Excel", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al crear el excel:\n" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String evaluar(String ext, int c, XSSFCell cell, String l) {
        cell.setCellValue(c);
        if (c > 0) {
            ext += " T" + l + c;
        }
        return ext;
    }

    private int tI(Object o) {
        return Integer.parseInt(tS(o));
    }

    private String tS(Object o) {
        return String.valueOf(o);
    }

    private int registros(int amb) {
        return gestor.totalRegistros(amb);
    }

    private int totalAmb(int amb) {
        return (gestor.totalTipoAmb(amb));
    }

    private int total(int amb, String tipo) {
        return (gestor.getTotal(amb, tipo));
    }

    private int totalChar(int amb) {
        return (gestor.getTotalChar(amb));
    }

    private int totalString(int amb) {
        return (gestor.getTotalString(amb));
    }

}
