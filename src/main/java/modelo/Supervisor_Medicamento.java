package modelo;

public class Supervisor_Medicamento extends ModeloBD {
    private String
            Nombre_Farmacia;
    private String
            Nombre_Comercial;
    private String
            Nombre_Farmaceutica;
    private String
            SSN_Supervisor;

    public Supervisor_Medicamento(String Nombre_Farmacia, String Nombre_Comercial, String Nombre_Farmaceutica, String SSN_Supervisor) {
        this.Nombre_Farmacia = Nombre_Farmacia;
        this.Nombre_Comercial = Nombre_Comercial;
        this.Nombre_Farmaceutica = Nombre_Farmaceutica;
        this.SSN_Supervisor = SSN_Supervisor;
    }

    public String getNombre_Farmacia() {
        return Nombre_Farmacia;
    }

    public void setNombre_Farmacia(String nombre_Farmacia) {
        Nombre_Farmacia = nombre_Farmacia;
    }

    public String getNombre_Comercial() {
        return Nombre_Comercial;
    }

    public void setNombre_Comercial(String nombre_Comercial) {
        Nombre_Comercial = nombre_Comercial;
    }

    public String getNombre_Farmaceutica() {
        return Nombre_Farmaceutica;
    }

    public void setNombre_Farmaceutica(String nombre_Farmaceutica) {
        Nombre_Farmaceutica = nombre_Farmaceutica;
    }

    public String getSSN_Supervisor() {
        return SSN_Supervisor;
    }

    public void setSSN_Supervisor(String SSN_Supervisor) {
        this.SSN_Supervisor = SSN_Supervisor;
    }

    public static String[] obtenerLabels(){
        return new String[]{"Farmacia","Medicamento","Farmaceutica","Supervisor"};
    }
}
