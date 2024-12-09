package vista;

import controlador.DAO;
import modelo.Registrable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class Componedor {

    public static JComponent[] identificarComponente(String nombre){
        if(nombre.equals("JTextField")) return new JComponent[]{new JTextField(10)};
        if(nombre.equals("JComboBox")) return new JComponent[]{new JComboBox<Object>()};
        if(nombre.equals("JDateField")){//formateo de combobox que si jala
            JComboBox<String> dias = new JComboBox<String>();
            JComboBox<String> meses = new JComboBox<String>();
            JComboBox<String> anios = new JComboBox<String>();
            for(int i = 0; i < 150; i++){
                anios.addItem(String.valueOf(1890+i));
                if(i<31){
                    if(i < 9) dias.addItem("0"+(i+1));
                    else dias.addItem(String.valueOf(i+1));
                    if(i<12){
                        if(i < 9) meses.addItem("0"+(i+1));
                        else meses.addItem(String.valueOf(i+1));
                    }
                }
            }
            return new JComponent[]{dias, meses, anios};
        }
        if(nombre.equals("JDecimalField")) {
            JComboBox<String> cents =  new JComboBox<String>();
            JComponent[] elem = {new JTextField(10), cents};
            for(int i = 0; i < 100; i++){
                String c = "0"+i;
                if(i > 9) c = ""+i;
                cents.addItem(c);
            }
            return elem;
        }
        if(nombre.equals("JNumberField")){
            JComboBox<String> num =  new JComboBox<String>();
            //JComponent[] elem = {new JTextField(10), cents};
            for(int i = 0; i < 101; i++){
                num.addItem(""+i);
            }
            return new JComponent[]{num};
        }
        if(nombre.equals("JCheckBox")) return new JComponent[]{new JCheckBox()};
        System.out.println("Componente no identificado: " + nombre);
        return null;
    }


    public static Object maximo(String tipo){
        switch (tipo.toLowerCase()){
            case "short", "smallint":return Short.MAX_VALUE;
            case "byte", "tinyint":return Byte.MAX_VALUE;
            case "integer", "int": return Integer.MAX_VALUE;
        }
        return -1;
    }
    /**
     * Ubica indices de los campos validos del formulario
     * @param obtenido valores extraidos del formulario
     */
    public static int[] ubicarCamposValidos(Object[] obtenido){
        ArrayList<Integer> out = new ArrayList<>();
        int[] idxs;
        int i = 0;
        for(Object dato : obtenido){
            if(dato != null) out.add(i);
            i++;
        }

        i = 0;
        idxs = new int[out.size()];
        for(int d : out){
            idxs[i] = out.get(i);
            i++;
        }
        return idxs;
    }
    public static int[] ubicarCamposCoincidencia(String[] obtenido, String match){
        ArrayList<Integer> out = new ArrayList<>();
        int[] idxs;
        int i = 0;
        for(String dato : obtenido){
            if(Objects.equals(dato, match)) out.add(i);
            i++;
        }

        i = 0;
        idxs = new int[out.size()];
        for(int d : out){
            idxs[i] = out.get(i);
            i++;
        }
        return idxs;
    }


    public static String[] obtenerFiltrosNombres(String[] nombres, int[] indices){
        Object[] o = Inspector.filtrarIndices(nombres, indices);
        String[] s = new String[o.length];
        for(int i = 0; i < s.length; i++){
            s[i] = o[i].toString();
        }
        return s;
    };
    public static String[] obtenerFiltrosTipos(String[] tipos, int[] indices){
        Object[] o = Inspector.filtrarIndices(tipos, indices);
        String[] s = new String[o.length];
        for(int i = 0; i < s.length; i++){
            s[i] = o[i].toString();
        }
        return s;
    };
    public static Object[] obtenerFiltrosValores(Object[] valores, int[] indices){
        return Inspector.filtrarIndices(valores, indices);
    };


    public static void columnasTabla(JTable tabla, String modelo){
        String[] column = DAO.d.obtenerColumnas(modelo);
        DefaultTableModel model = new DefaultTableModel(null, column);
        tabla.setModel(model);
    }
    public static void limpiarTabla(JTable tabla){
        DefaultTableModel m = ((DefaultTableModel)tabla.getModel());
        while (m.getRowCount() > 0){
            m.removeRow(0);
        }
    }
    public static void filaTabla(JTable tabla, Registrable res){
        ((DefaultTableModel)tabla.getModel()).addRow(res.obtenerValores());
    }
    public static void autoRellenar(ArrayList<JComponent> inputs, String[] comps, Object[] datos){
        for (int i = 0, j = 0; i < comps.length; i++){
            String comp = comps[i];
            Object dato = datos[i];
            JComponent input = inputs.get(i+j);

            switch (comp.toLowerCase()){
                case "jtextfield":
                    ((JTextField)input).setText(dato.toString());
                    break;
                case "jdatefield":
                    String[] breakeado = dato.toString().split("-");
                    ((JComboBox)inputs.get(i+j)).setSelectedItem(breakeado[0]);
                    j++;
                    ((JComboBox)inputs.get(i+j)).setSelectedItem(breakeado[1]);
                    j++;
                    ((JComboBox)inputs.get(i+j)).setSelectedItem(breakeado[2]);
                    break;
                case "jnumberfield":
                    ((JComboBox)input).setSelectedItem(dato);
                    break;
                case "jdecimalfield":
                    String[] decimal = dato.toString().split("\\.");
                    if(decimal.length < 1){
                        //System.out.println("COMPONE> NULO" + dato);
                        decimal = new String[]{dato.toString(), "00"};
                    }
                    ((JTextField)inputs.get(i+j)).setText(decimal[0]);
                    j++;
                    ((JComboBox)inputs.get(i+j)).setSelectedItem(decimal[1]);
                    break;
            }
        }
    }
    public static String[] formatearVacios(Object[] o){
        String[] k = new String[o.length];
        for (int i = 0; i < o.length; i++) {
            k[i] = (String) o[i];
            if(o[i] == null) k[i] = "";
        }
        return k;
    }
}
