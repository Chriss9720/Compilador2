package Controller;

import Model.Ambito;
import Model.Gestor;
import Model.Semantica_E_1;
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

    public GenerarExcel(Pantalla p) {
        this.contadores = p.getContadores();
        this.errores = p.getErrores();
        this.tokens = p.getTokens();
        this.ambitos = p.getAmb();
        this.sE_1 = p.getsE_1();
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
