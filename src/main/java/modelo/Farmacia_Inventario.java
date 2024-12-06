package modelo;

import java.math.BigDecimal;

public class Farmacia_Inventario extends ModeloBD{
    String Nombre_Farmacia;
    BigDecimal Medicamento_Precio;
    String Nombre_Farmaceutica;
    String Nombre_Comercial;

    public Farmacia_Inventario(String nombre_Farmacia, BigDecimal medicamento_Precio, String nombre_Farmaceutica, String nombre_Comercial) {
        Nombre_Farmacia = nombre_Farmacia;
        Medicamento_Precio = medicamento_Precio;
        Nombre_Farmaceutica = nombre_Farmaceutica;
        Nombre_Comercial = nombre_Comercial;
    }

    @Override
    public String toString() {
        return "Farmacia_Inventario{" +
                "Nombre_Farmacia='" + Nombre_Farmacia + '\'' +
                ", Medicamento_Precio='" + Medicamento_Precio + '\'' +
                ", Nombre_Farmaceutica='" + Nombre_Farmaceutica + '\'' +
                ", Nombre_Comercial='" + Nombre_Comercial + '\'' +
                '}';
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "DECIMAL", "VARCHAR", "VARCHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{50,12,50,45};
    }
    public static String[] obtenerLabels(){
        return new String[]{"Nombre de la farmacia","Precio del medicamento $","Nombre de la farmaceutica","Nombre del medicamento"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JDecimalField", "JTextField", "JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0, 2, 3};
    }
}
