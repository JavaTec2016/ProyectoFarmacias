package modelo;

public class Farmacia extends ModeloBD{
    String Nombre;
    String Ciudad;
    String Calle;
    String Codigo_Postal;
    String Telefono;
    String Lada;

    public Farmacia(String nombre, String ciudad, String calle, String codigo_Postal, String telefono, String lada) {
        Nombre = nombre;
        Ciudad = ciudad;
        Calle = calle;
        Codigo_Postal = codigo_Postal;
        Telefono = telefono;
        Lada = lada;
    }

    @Override
    public String toString() {
        return "Farmacia{" +
                "Nombre='" + Nombre + '\'' +
                ", Ciudad='" + Ciudad + '\'' +
                ", Calle='" + Calle + '\'' +
                ", Codigo_Postal='" + Codigo_Postal + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", Lada='" + Lada + '\'' +
                '}';
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true, true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{50,45,45,10,10,5};
    }
    public static String[] obtenerLabels(){
        return new String[]{"Nombre de la farmacia","Ciudad","Calle","Codigo postal","Telefono","Lada"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JTextField", "JTextField","JTextField", "JTextField", "JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }
}
