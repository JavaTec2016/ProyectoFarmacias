package modelo;

public class Medicamento extends ModeloBD{
    String Nombre_Comercial;
    String Formula;
    String Nombre_Farmaceutica;

    public Medicamento(String nombre_Comercial, String formula, String nombre_Farmaceutica) {
        Nombre_Comercial = nombre_Comercial;
        Formula = formula;
        Nombre_Farmaceutica = nombre_Farmaceutica;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "Nombre_Comercial='" + Nombre_Comercial + '\'' +
                ", Formula='" + Formula + '\'' +
                ", Nombre_Farmaceutica='" + Nombre_Farmaceutica + '\'' +
                '}';
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "VARCHAR", "VARCHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{45, 200, 50};
    }
    public static String[] obtenerLabels(){
        return new String[]{"Nombre comercial","Formula","Nombre de la farmaceutica"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JTextField", "JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0, 2};
    }
}
