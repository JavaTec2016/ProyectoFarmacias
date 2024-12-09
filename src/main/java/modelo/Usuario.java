package modelo;

public class Usuario extends ModeloBD{
    public String Nombre = "";
    public String Pass = ""; //xd
    public Integer consulta = 0;
    public Integer inserta = 0;
    public Integer elimina = 0;
    public Integer actualiza = 0;
    public Integer creaUsuarios = 0;

    public Usuario(String nombre, String pass, Integer consulta, Integer inserta, Integer elimina, Integer actualiza, Integer creaUsuarios) {
        Nombre = nombre;
        Pass = pass;
        this.consulta = consulta;
        this.inserta = inserta;
        this.elimina = elimina;
        this.actualiza = actualiza;
        this.creaUsuarios = creaUsuarios;
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, true,true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "VARCHAR", "INT", "INT", "INT", "INT", "INT"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true,true, true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{50,50,-1,-1,-1,-1,-1};
    }
    //validacion de datos especial
    public static String[] obtenerLabels(){
        return new String[]{"Nombre de usuario","Contraseña","Puede consultar datos","Puede consultar insertar datos", "Puede eliminar datos", "Puede modificar datos", "Administrador"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JTextField", "JCheckBox", "JCheckBox", "JCheckBox", "JCheckBox", "JCheckBox"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }
}
