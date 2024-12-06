package reportes;

import conexionBD.ConexionBDLite;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Reportador {

    JasperDesign design;
    JasperReport report;
    JasperPrint print;
    String path;
    public void cargarReporte(String r){
        path = r;
        try {
            System.out.println("Cargando: " + path);
            design = JRXmlLoader.load(path);
            System.out.println(design);
            report = JasperCompileManager.compileReport(design);
            print = JasperFillManager.fillReport(report, (Map<String, Object>) null, (Connection) null);
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    public void compilarReporte(){
        try {
            //System.out.println("Cargando: " + path);

            design = JRXmlLoader.load("src/main/resources/reports/Blank_A4_1.jrxml");
            System.out.println(design);
            report = JasperCompileManager.compileReport(design);
            JasperPrint print = JasperFillManager.fillReport(report, (Map<String, Object>) null, ConexionBDLite.obtenerConector().getConexion());//.fillReport(report, null, ConexionBDLite.obtenerConector().getConexion());
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    public void compilarReporte2(){
        try {
            System.out.println("Cargando: " + path);

            JasperReport rp = JasperCompileManager.compileReport("src/main/resources/reports/report.jrxml");
            System.out.println("Cargao: " + path);

            JasperPrint print = JasperFillManager.fillReport(rp, null, ConexionBDLite.obtenerConector().getConexion());//.fillReport(report, null, ConexionBDLite.obtenerConector().getConexion());
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    public void reporteRapido(){
        String p = "src/main/resources/reports/report.jrxml";
        cargarReporte(p);
    }
    public String formatearQueryWHERE(String sql, Object[] valores){
        StringBuilder st = new StringBuilder(sql);
        int i = 0;
        for(Object valor : valores){
            int j = st.indexOf("?");
            String puestable = valor.toString();
            if(valor instanceof String){
                puestable = "'"+puestable+"%'";
            }
            st.replace(j, 1, puestable);
            i++;
        }
        return st.toString();
    }
    public void compilarReporte3(){
        try {
            JasperReport rp = JasperCompileManager.compileReport("src/main/resources/reports/report-din.jrxml");
            System.out.println("Cargao: " + path);
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("query", "Nombre LIKE 'ras%'");

            JasperPrint print = JasperFillManager.fillReport(rp, params, ConexionBDLite.obtenerConector().getConexion());
            System.out.println("params chidos");
            JasperViewer.viewReport(print, false);
            System.out.println("listote");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //System.out.println("Cargando: " + path);


        //JasperPrint print = JasperFillManager.fillReport("src/main/resources/reports/report.jasper", null, ConexionBDLite.obtenerConector().getConexion());//.fillReport(report, null, ConexionBDLite.obtenerConector().getConexion());
        //JasperViewer.viewReport(print, false);

        new Reportador().compilarReporte2();
    }
}
