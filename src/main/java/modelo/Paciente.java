package modelo;

public class Paciente extends ModeloBD{
    String SSN;
    String Nombre;
    String Apellido;
    String Ciudad;
    String Calle;
    String Num_Domicilio;
    String Codigo_Postal;
    String Fecha_Nac;
    String SSN_Cabecera;

    public Paciente(String SSN, String nombre, String apellido, String ciudad, String calle, String num_Domicilio, String codigo_Postal, String fecha_Nac, String SSN_Cabecera) {
        this.SSN = SSN;
        Nombre = nombre;
        Apellido = apellido;
        Ciudad = ciudad;
        Calle = calle;
        Num_Domicilio = num_Domicilio;
        Codigo_Postal = codigo_Postal;
        Fecha_Nac = fecha_Nac;
        this.SSN_Cabecera = SSN_Cabecera;
    }
    @Override
    public String toString() {
        return "Paciente{" +
                "SSN='" + SSN + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Ciudad='" + Ciudad + '\'' +
                ", Calle='" + Calle + '\'' +
                ", Num_Domicilio='" + Num_Domicilio + '\'' +
                ", Codigo_Postal='" + Codigo_Postal + '\'' +
                ", Fecha_Nac='" + Fecha_Nac + '\'' +
                ", SSN_Cabecera='" + SSN_Cabecera + '\'' +
                '}';
    }
    @Override
    public boolean[] noNulos() {
        return new boolean[]{true, true, false, true, true, false, false, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"CHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "DATE", "CHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, false, true, true, false, false, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{16,45,45,45,45,10,10,-1,16};
    }
    public static String[] obtenerLabels(){
        return new String[]{"SSN", "Nombre", "Apellido", "Ciudad", "Calle", "Num. Domicilio", "Codigo postal", "Nacimiento", "SSN Medico de cabecera"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField","JTextField","JTextField","JTextField","JTextField","JTextField","JTextField","JDateField","JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }
}
