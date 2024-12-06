package vista;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;

import java.awt.*;

public class Chart extends ApplicationFrame {
    public Chart(String title) {
        super(title);
        JFreeChart chart = ChartFactory.createPieChart(
                "Sample pie chart", null, true, true, false
        );
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(560, 300));
        setContentPane(panel);

    }

    public static void main(String[] args) {
        Chart c = new Chart("Buenas");
        c.pack();
        c.setVisible(true);
    }
}
