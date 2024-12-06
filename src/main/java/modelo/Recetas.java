package modelo;

public class Recetas extends ModeloBD {
    String Id_Receta;
    String Fecha;
    Integer Unidades;
    String SSN_Pacientes;
    String SSN_Cabecera;
    String Nombre_Farmaceutica;
    String Nombre_comercial;

    public Recetas(String idReceta, String fecha, Integer unidades, String SSN_Pacientes, String SSN_Cabecera, String Nombre_Farmaceutica, String nombre_comercial) {
        Id_Receta = idReceta;
        Fecha = fecha;
        Unidades = unidades;
        this.SSN_Pacientes = SSN_Pacientes;
        this.SSN_Cabecera = SSN_Cabecera;
        this.Nombre_Farmaceutica = Nombre_Farmaceutica;
        Nombre_comercial = nombre_comercial;
    }

    @Override
    public String toString() {
        return "Recetas{" +
                "Id='" + Id_Receta + '\'' +
                ", Fecha='" + Fecha + '\'' +
                ", Unidades='" + Unidades + '\'' +
                ", SSN_Paciente='" + SSN_Pacientes + '\'' +
                ", SSN_Cabecera='" + SSN_Cabecera + '\'' +
                ", Farmaceutica_Nombre='" + Nombre_Farmaceutica + '\'' +
                ", Nombre_comercial='" + Nombre_comercial + '\'' +
                '}';
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, true, true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "DATE", "INT", "CHAR", "CHAR", "VARCHAR", "VARCHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true, true, true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{10, -1, -1, 16, 16, 50, 45};
    }
    public static String[] obtenerLabels(){
        return new String[]{"ID de la receta","Fecha de emision","Unidades","SSN del paciente", "SSN del médico de cabecera", "Nombre de la farmaceutica", "Nombre del medicamento"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JDateField", "JNumberField", "JTextField", "JTextField", "JTextField", "JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }
}
