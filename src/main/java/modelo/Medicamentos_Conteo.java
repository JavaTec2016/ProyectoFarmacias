package modelo;

public class Medicamentos_Conteo extends ModeloBD{
    public String Nombre_Farmaceutica;
    public String Nombre_Comercial;
    public Integer Numero;

    public Medicamentos_Conteo(String nombre_Farmaceutica, String nombre_Comercial, Integer numero) {
        Nombre_Farmaceutica = nombre_Farmaceutica;
        Nombre_Comercial = nombre_Comercial;
        Numero = numero;
    }
    public boolean[] noNulos() {
        return new boolean[]{true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "VARCHAR", "INT"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{50, 50, -1};
    }
    public static String[] obtenerLabels(){
        return new String[]{"Farmaceutica","Farmacia","Existencias"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JTextField", "JNumberField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0, 1};
    }
}
