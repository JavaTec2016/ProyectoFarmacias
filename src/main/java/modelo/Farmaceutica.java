package modelo;

public class Farmaceutica extends ModeloBD{
    String Nombre;
    String Telefono;
    String Lada;

    public Farmaceutica(String nombre, String telefono, String lada) {
        Nombre = nombre;
        Telefono = telefono;
        Lada = lada;
    }
    @Override
    public String toString() {
        return "Farmaceutica{" +
                "Nombre='" + Nombre + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", Lada='" + Lada + '\'' +
                '}';
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "VARCHAR", "VARCHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{50,10,5};
    }
    public static String[] obtenerLabels(){
        return new String[]{"Nombre","Teléfono","Lada"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JTextField", "JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }
}
