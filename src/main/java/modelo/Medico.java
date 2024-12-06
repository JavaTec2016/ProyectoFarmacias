package modelo;

public class Medico extends ModeloBD{
    String SSN;
    String Nombre;
    String Apellido;
    String Especialidad;
    Short Anios_Experiencia;

    public Medico(String SSN, String nombre, String apellido, String especialidad, Short anios_Experiencia) {
        this.SSN = SSN;
        Nombre = nombre;
        Apellido = apellido;
        Especialidad = especialidad;
        Anios_Experiencia = anios_Experiencia;
    }
    @Override
    public String toString() {
        return "Medico{" +
                "SSN='" + SSN + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Especialidad='" + Especialidad + '\'' +
                ", Anios_Experiencia=" + Anios_Experiencia +
                '}';
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"CHAR", "VARCHAR", "VARCHAR", "VARCHAR", "SMALLINT"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{16, 45, 45, 100, -1};
    }
    public static String[] obtenerLabels(){
        return new String[]{"SSN", "Nombre", "Apellido", "Especialidad", "Años de experiencia"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JTextField", "JTextField", "JTextField", "JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }
}
