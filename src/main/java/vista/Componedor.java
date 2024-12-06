package vista;

import controlador.DAO;
import modelo.Registrable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class Componedor {

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

    /**
     * Convierte el texto al tipo de dato indicado, util para parsear y corregir inconsistencias de datos
     * las inconsistencias con sqlite pueden volverse comunes
     * @param t texto con el valor
     * @param tipo nombre de la clase del tipo de dato, el nombre completo es opcional
     * @return dato convertido
     * @throws NumberFormatException si el texto no se puede parsear a numero
     */
    public static Object extraerTexto(String t, String tipo) throws NumberFormatException{

        if(t.isEmpty()) return null;
        int ix = tipo.lastIndexOf('.');
        tipo = ix == -1 ? tipo : tipo.substring(ix+1);
        //System.out.println(tipo);
        switch (tipo.toLowerCase()){
            case "varchar":
            case "char":
            case "date":
            case "string":return t;
            case "tinyint":
            case "byte": return Byte.parseByte(t);
            case "short":
            case "smallint": return Short.parseShort(t);
            case "mediumint":
            case "int":
            case "integer": return Integer.parseInt(t);
            case "float": return Float.parseFloat(t);
            case "double": return Double.parseDouble(t);
            case "boolean": return t.equalsIgnoreCase("true") ? 1 : 0;
            case "bigdecimal": return new BigDecimal(t);
            default:
                //System.out.println(tipo.toLowerCase());
                break;
        }
        return "nose";
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

    /**
     * Filtra una lista de valores en base a los indices dados
     * @param lista lista de valores a filtrar
     * @param indices lista de indices admitidos
     * @return lista de valores filtrados
     */
    public static Object[] filtrarIndices(Object[] lista, int[] indices){
        Object[] out = new Object[indices.length];
        for(int i = 0; i < indices.length; i++){
            out[i] = lista[indices[i]];
        }
        return out;
    }
    public static String[] obtenerFiltrosNombres(String[] nombres, int[] indices){
        Object[] o = filtrarIndices(nombres, indices);
        String[] s = new String[o.length];
        for(int i = 0; i < s.length; i++){
            s[i] = o[i].toString();
        }
        return s;
    };
    public static String[] obtenerFiltrosTipos(String[] tipos, int[] indices){
        Object[] o = filtrarIndices(tipos, indices);
        String[] s = new String[o.length];
        for(int i = 0; i < s.length; i++){
            s[i] = o[i].toString();
        }
        return s;
    };
    public static Object[] obtenerFiltrosValores(Object[] valores, int[] indices){
        return filtrarIndices(valores, indices);
    };

    /**
     *
     * @param campos tipo de cada componente
     * @param tipos tipos de dato de cada componente
     * @param inputs lista de componentes
     * @return lista de valores extraidos, instanciables por ModeloBD
     */
    public static Object[] extraerDatos(String[] campos, String[] tipos, JComponent[] inputs) throws NumberFormatException{
        Object[] out = new Object[campos.length];
        for(int i = 0, j = 0; i < campos.length; i++){
            String campo = campos[i];
            String tipo = tipos[i];
            JComponent input = inputs[i+j];

            //identificar el componente a parsear
            if(campo.equalsIgnoreCase("JDateField")){//dias, meses, anios
                String dia = ""+((JComboBox<String>)inputs[i+j]).getSelectedItem();
                j+=1;
                String mes = ""+((JComboBox<String>)inputs[i+j]).getSelectedItem();
                j+=1;
                String anio = ""+((JComboBox<String>)inputs[i+j]).getSelectedItem();
                out[i] = anio+"-"+mes+"-"+dia;
                System.out.println(out[i].toString());
                if(out[i].toString().length() < 10) out[i] = null;

            }
            else if(campo.equalsIgnoreCase("JDecimalField")){
                String enteros = ((JTextField)inputs[i+j]).getText();
                j+=1;
                String decimal = (String)((JComboBox<String>)inputs[i+j]).getSelectedItem();
                out[i] = new BigDecimal(enteros+"."+decimal);
                //System.out.println("COMPONEDOR: " + out[i] + " : " + i);
            }
            else if(campo.equalsIgnoreCase("JNumberField")){
                out[i] = Integer.parseInt((String) ((JComboBox<String>)input).getSelectedItem());
            }
            else if(input instanceof JTextField){

                out[i] = extraerTexto(((JTextField) input).getText(), tipo);

            }else if(input instanceof JRadioButton){
                out[i] = ((JRadioButton) input).isSelected() ? 1 : 0;
            }
            else if(input instanceof JCheckBox){
                out[i] = ((JCheckBox) input).isSelected() ? 1 : 0;
            }

            if(!input.isEnabled()) {
                out[i] = null;
            }
        }
        return out;
    }
    public static Object[] extraerDatos(String[] campos, String[] tipos, ArrayList<JComponent> inputs) throws NumberFormatException{
        Object[] out = new Object[campos.length];
        for(int i = 0, j = 0; i < campos.length; i++){
            String campo = campos[i];
            String tipo = tipos[i];
            JComponent input = inputs.get(i+j);

            //identificar el componente a parsear
            if(campo.equalsIgnoreCase("JDateField")){//dias, meses, anios
                String fecha = ""+((JComboBox<String>)inputs.get(i+j)).getSelectedItem();
                j+=1;
                fecha += "-"+((JComboBox<String>)inputs.get(i+j)).getSelectedItem();
                j+=1;
                fecha += "-"+((JComboBox<String>)inputs.get(i+j)).getSelectedItem();
                out[i] = fecha;
                if(fecha.length() < 10) out[i] = null;
            }
            else if(campo.equalsIgnoreCase("JDecimalField")){
                String enteros = ((JTextField)inputs.get(i+j)).getText();
                j+=1;
                String decimal = (String)((JComboBox<String>)inputs.get(i+j)).getSelectedItem();
                out[i] = new BigDecimal(enteros+"."+decimal);
            }
            else if(campo.equalsIgnoreCase("JNumberField")){
                out[i] = Integer.parseInt((String) ((JComboBox<String>)input).getSelectedItem());
            }
            else if(input instanceof JTextField){

                out[i] = extraerTexto(((JTextField) input).getText(), tipo);

            }else if(input instanceof JRadioButton){
                out[i] = ((JRadioButton) input).isSelected() ? 1 : 0;
            }
            else if(input instanceof JCheckBox){
                out[i] = ((JCheckBox) input).isSelected() ? 1 : 0;
            }

            if(!input.isEnabled()) {
                out[i] = null;
            }
        }
        return out;
    }
    public static Object[] extraerDatos(String[] campos, String[] tipos, ArrayList<JComponent> inputs, int cap) throws NumberFormatException{
        Object[] out = new Object[campos.length];
        for(int i = 0, j = 0; i < Math.min(campos.length, cap); i++){
            String campo = campos[i];
            String tipo = tipos[i];
            JComponent input = inputs.get(i+j);

            System.out.println("COMPONE: campo " + i + " es " + input.getClass().getName());
            //identificar el componente a parsear
            if(campo.equalsIgnoreCase("JDateField")){//dias, meses, anios
                String fecha = ""+((JComboBox<String>)inputs.get(i+j)).getSelectedItem();
                j+=1;
                fecha += "-"+((JComboBox<String>)inputs.get(i+j)).getSelectedItem();
                j+=1;
                fecha += "-"+((JComboBox<String>)inputs.get(i+j)).getSelectedItem();
                out[i] = fecha;

                if(fecha.length() < 10) out[i] = null;
            }
            else if(campo.equalsIgnoreCase("JDecimalField")){
                String enteros = ((JTextField)inputs.get(i+j)).getText();
                j+=1;
                String decimal = (String)((JComboBox<String>)inputs.get(i+j)).getSelectedItem();
                out[i] = new BigDecimal(enteros+"."+decimal);

            }
            else if(campo.equalsIgnoreCase("JNumberField")){
                out[i] = Integer.parseInt((String) ((JComboBox<String>)input).getSelectedItem());

            }
            else if(input instanceof JTextField){

                out[i] = extraerTexto(((JTextField) input).getText(), tipo);

            }else if(input instanceof JRadioButton){
                out[i] = ((JRadioButton) input).isSelected() ? 1 : 0;

            }
            else if(input instanceof JCheckBox){
                out[i] = ((JCheckBox) input).isSelected() ? 1 : 0;

            }

            if(!input.isEnabled()){
                System.out.println("COMPONE: " + i + " nulo");
                out[i] = null;
            }
        }
        System.out.println("COMPONE: resultao" + Arrays.toString(out));
        return out;
    }
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
    public static Object[] extraerFila(JTable tabla, int row){
        Object[] out = new Object[tabla.getColumnCount()];
        for(int i = 0; i < out.length; i++){
            out[i] = tabla.getValueAt(row, i);
        }
        return out;
    }
    public static void autoRellenar(JComponent[] inputs, String[] comps, Object[] datos){
        for (int i = 0, j = 0; i < comps.length; i++){
            String comp = comps[i];
            Object dato = datos[i];
            JComponent input = inputs[i+j];

            switch (comp.toLowerCase()){
                case "jtextfield":
                    ((JTextField)input).setText(dato.toString());
                    break;
                case "jdatefield":
                    String[] breakeado = dato.toString().split("-");
                    ((JComboBox)inputs[i+j]).setSelectedItem(breakeado[0]);
                    j++;
                    ((JComboBox)inputs[i+j]).setSelectedItem(breakeado[1]);
                    j++;
                    ((JComboBox)inputs[i+j]).setSelectedItem(breakeado[2]);
                    break;
                case "jnumberfield":
                    ((JComboBox)input).setSelectedItem(dato);
                    break;
                case "jdecimalfield":
                    String[] decimal = dato.toString().split(".");
                    ((JTextField)inputs[i+j]).setText(decimal[0]);
                    j++;
                    ((JComboBox)inputs[i+j]).setSelectedItem(decimal[1]);
                    break;
            }
        }
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
                    String[] decimal = dato.toString().split(".");
                    ((JTextField)inputs.get(i+j)).setText(decimal[0]);
                    j++;
                    ((JComboBox)inputs.get(i+j)).setSelectedItem(decimal[1]);
                    break;
            }
        }
    }
}
