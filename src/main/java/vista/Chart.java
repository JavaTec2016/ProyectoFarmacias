package vista;

import conexionBD.ConexionBDLite;
import controlador.DAO;
import modelo.Medicamentos_Conteo;
import modelo.Registrable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chart extends JFrame {
    public Chart(String title) {
        super(title);
        DefaultCategoryDataset dataset = getMedicamentoDataset();
        //String[] labels = Medicamentos_Conteo.obtenerLabels();
        JFreeChart chart = ChartFactory.createBarChart(
                "Existencias de medicamentos",
                "Medicamento",
                "Existencias",
                dataset);
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(560, 300));
        setContentPane(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void packMediChart(){
        Chart c = new Chart("Grafico de medicamentos");
        c.pack();
        c.setVisible(true);
    }
    public static void main(String[] args) {
        Chart c = new Chart("Buenas");
        c.pack();
        c.setVisible(true);
    }
    public static DefaultCategoryDataset getMedicamentoDataset(){

        String[] sl = {"Nombre_Farmaceutica", "Nombre_Comercial", "COUNT(*) AS Numero"};
        String[] fn = {};
        String[] ft = {};
        Object[] fv = {};
        //ConexionBDLite.obtenerConector().abrirConexion("Admin", "Admin");
        ArrayList<Registrable> conteos = DAO.d.consultarPreparedUniversalCOUNT("Farmacia_Inventario", "Medicamentos_Conteo", sl, fn, ft, fv, true, "Nombre_Farmaceutica, Nombre_Comercial");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        System.out.println(conteos.size());
        for(Registrable r : conteos){
            Medicamentos_Conteo c = (Medicamentos_Conteo)r;
            String[] labels = Medicamentos_Conteo.obtenerLabels();
            dataset.addValue(c.Numero, labels[2], (c.Nombre_Farmaceutica+", "+c.Nombre_Comercial));
        }
        //ConexionBDLite.obtenerConector().cerrarConexion();
        return dataset;
    };
}
