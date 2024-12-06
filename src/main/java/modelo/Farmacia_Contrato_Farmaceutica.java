package modelo;

public class Farmacia_Contrato_Farmaceutica extends ModeloBD{
    String Id_Contrato;
    String Nombre_Farmacia;
    String Nombre_Farmaceutica;
    String Fecha_Inicio;
    String Fecha_Fin;
    String Contenido;
    String SSN_Supervisor;

    public Farmacia_Contrato_Farmaceutica(String Id_Contrato, String nombre_Farmacia, String nombre_Farmaceutica, String fecha_Inicio, String fecha_Fin, String contenido, String ssn_supervisor) {
        this.Id_Contrato = Id_Contrato;
        Nombre_Farmacia = nombre_Farmacia;
        Nombre_Farmaceutica = nombre_Farmaceutica;
        Fecha_Inicio = fecha_Inicio;
        Fecha_Fin = fecha_Fin;
        Contenido = contenido;
        SSN_Supervisor = ssn_supervisor;
    }
    @Override
    public String toString() {
        return "Farmacia_Contrato_Farmaceutica{" +
                "Nombre_Farmacia='" + Nombre_Farmacia + '\'' +
                ", Nombre_Farmaceutica='" + Nombre_Farmaceutica + '\'' +
                ", Fecha_Inicio='" + Fecha_Inicio + '\'' +
                ", Fecha_Fin='" + Fecha_Fin + '\'' +
                ", Contenido='" + Contenido + '\'' +
                '}';
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, true, true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "VARCHAR", "DATE", "DATE", "MEDIUMTEXT", "CHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true, true, true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{50, 50, -1, -1, -1, 16};
    }
    public static String[] obtenerLabels(){
        return new String[]{"Nombre de la farmacia","Nombre de la farmaceutica","Fecha de inicio","Fecha de término", "Contenido"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JTextField", "JDateField", "JDateField", "JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }

}
