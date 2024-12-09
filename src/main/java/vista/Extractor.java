package vista;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public interface Extractor {
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

            //System.out.println("COMPONE: campo " + i + " es " + input.getClass().getName());
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
                if(enteros.isEmpty()) out[i] = null;

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
                //System.out.println("COMPONE: " + i + " nulo");
                out[i] = null;
            }
        }
        //System.out.println("COMPONE: resultao" + Arrays.toString(out));
        return out;
    }
    public static Object[] extraerFila(JTable tabla, int row){
        Object[] out = new Object[tabla.getColumnCount()];
        for(int i = 0; i < out.length; i++){
            out[i] = tabla.getValueAt(row, i);
        }
        return out;
    }
}
