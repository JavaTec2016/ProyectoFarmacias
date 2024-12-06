package modelo;

public class Recetas extends ModeloBD {
    String Id_Receta;
    String Fecha;
    String Unidades;
    String SSN_Paciente;
    String SSN_Cabecera;
    String Farmaceutica_Nombre;
    String Nombre_comercial;

    public Recetas(String idReceta, String fecha, String unidades, String SSN_Paciente, String SSN_Cabecera, String farmaceutica_Nombre, String nombre_comercial) {
        Id_Receta = idReceta;
        Fecha = fecha;
        Unidades = unidades;
        this.SSN_Paciente = SSN_Paciente;
        this.SSN_Cabecera = SSN_Cabecera;
        Farmaceutica_Nombre = farmaceutica_Nombre;
        Nombre_comercial = nombre_comercial;
    }

    @Override
    public String toString() {
        return "Recetas{" +
                "Id='" + Id_Receta + '\'' +
                ", Fecha='" + Fecha + '\'' +
                ", Unidades='" + Unidades + '\'' +
                ", SSN_Paciente='" + SSN_Paciente + '\'' +
                ", SSN_Cabecera='" + SSN_Cabecera + '\'' +
                ", Farmaceutica_Nombre='" + Farmaceutica_Nombre + '\'' +
                ", Nombre_comercial='" + Nombre_comercial + '\'' +
                '}';
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, true, true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "DATE", "SMALLINT", "CHAR", "CHAR", "VARCHAR", "VARCHAR"};
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
