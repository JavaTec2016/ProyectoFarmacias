package conexionBD;
import controlador.DAO;
import modelo.Registrable;
import modelo.Usuario;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;


public class ConexionBDLite {
    private Connection conexion;
    private PreparedStatement st;
    private static ConexionBDLite conector = null;
    private ResultSet rs;
    private Usuario usr;
    private ConexionBDLite() {

    }
    public Usuario getUsr(){return usr;}
    public int buscarCredenciales(String usuario, String pass) {
        String url = "jdbc:sqlite:C:/Users/TheGr/SQLite/FarmaciasRX.db";
        try {
            Class.forName("org.sqlite.JDBC");

            conexion = DriverManager.getConnection(url, "dominik", "larraja27");
            prepararStatement("SELECT nombre, pass FROM usuario WHERE nombre=?", new String[]{"VARCHAR"}, new Object[]{usuario});
            ResultSet rs = ejecutarConsulta();
            if(rs.getObject(1) == null){
                conexion = null;
                System.out.println("error 1");
                return 1;
            } //no existe el usuario
            prepararStatement("SELECT nombre, pass FROM usuario WHERE nombre=? AND pass=?", new String[]{"VARCHAR", "VARCHAR"}, new Object[]{usuario, pass});
            rs = ejecutarConsulta();
            if(rs.getObject(1) == null){
                conexion = null;
                System.out.println("error 2");
                return 2;
            } //la contrasenia no coincide con ningun usuario
            conexion.close();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return e.getErrorCode();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //abrir conexion con usuario ya autenticado
    public void abrirConexion(String usuario, String pass){
        try {
            Class.forName("org.sqlite.JDBC");
            //si la url está incorrecta, va a generar una BD en donde diga y con el nombre que diga, aguas
            String url = "jdbc:sqlite:C:/Users/TheGr/SQLite/FarmaciasRX.db";
            conexion = DriverManager.getConnection(url, usuario, pass);
            System.out.println("Atte el licenciado GG");

            //arraylist de uno solito para definir el usuario de la conexion
            ArrayList<Registrable> usrs = DAO.d.consultarPreparedUniversal("Usuario", new String[]{"*"}, new String[]{"Nombre", "Pass"},new String[]{"VARCHAR", "VARCHAR"}, new Object[]{usuario, pass}, false);
            usr = (Usuario)usrs.get(0);

            establecerAutocommit(false);
        } catch (SQLException e) {
            System.out.println("url incorrecta");
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println("Error en el driver");
            e.printStackTrace();
        }
    }
    public void cerrarConexion(){
        conexion = null;
    }
    /**
     * Configura el preparedStatement en base a listas de valores y sus tipos de datos
     * @param inst instruccion a preparar
     * @param tipos tipos de datos de todos los valores de la instruccion
     * @param valores todos los valores que utilizara la instruccion
     * @throws SQLException si sale mal
     */
    public void prepararStatement(String inst, String[] tipos, Object[] valores) throws SQLException {
        st = conexion.prepareStatement(inst);

        if(tipos == null || valores == null) return;

        for(int i = 0; i < tipos.length; i++){
            int indice = i+1;
            String tipo = tipos[i];
            int ix = tipo.lastIndexOf('.');

            tipo = ix == -1 ? tipo : tipo.substring(ix+1);
            switch (tipo.toLowerCase()){
                case "smallint", "short": st.setShort(indice, (short)valores[i]); break;
                case "int": st.setInt(indice, (int)valores[i]); break;
                case "byte": st.setByte(indice, (byte)valores[i]); break;
                case "varchar", "char", "mediumtext", "string": st.setString(indice, (String)valores[i]); break;
                case "float": st.setFloat(indice, (float)valores[i]); break;
                case "double": st.setDouble(indice, (double)valores[i]); break;
                case "decimal": st.setBigDecimal(indice, (BigDecimal)valores[i]); break;
                case "date": st.setDate(indice, (Date)valores[i]); break;
            }
            System.out.println(st.getParameterMetaData());
        }
    }
    public void prepararStatement(String inst, String[] tipos, Object[] valores, boolean parcial) throws SQLException {
        st = conexion.prepareStatement(inst);

        if(tipos == null || valores == null) return;
        for(int i = 0; i < tipos.length; i++){
            int indice = i+1;
            String tipo = tipos[i];
            int ix = tipo.lastIndexOf('.');
            tipo = ix == -1 ? tipo : tipo.substring(ix+1);

            switch (tipo.toLowerCase()){
                case "smallint", "short": st.setShort(indice, (short)valores[i]); break;
                case "int":
                case "integer": st.setInt(indice, (int)valores[i]); break;
                case "varchar":
                case "char":
                case "mediumtext":
                case "string":
                    if(parcial) st.setString(indice, (String)valores[i]+"%");
                    else st.setString(indice, (String)valores[i]);
                    break;
                case "float": st.setFloat(indice, (float)valores[i]); break;
                case "double": st.setDouble(indice, (double)valores[i]); break;
                case "decimal": st.setBigDecimal(indice, (BigDecimal)valores[i]); break;
                case "date": st.setDate(indice, (Date)valores[i]); break;
                //case "boolean": st.setBoolean();
            }
        }
    }
    ///////MANEJO DE TRANSACCIONES
    public  void establecerAutocommit(boolean b){
        try {
            conexion.setAutoCommit(b);
        } catch (SQLException e) {
            System.out.println("Error al actualizar autocommit: " + e.getMessage());
        }
    }
    public void confirmar(){
        try {
            conexion.commit();
        } catch (SQLException e) {
            System.out.println("Error al confirmar cambios: " + e.getMessage());
        }
    }
    public void descartar(){
        try {
            conexion.rollback();
        } catch (SQLException e) {
            System.out.println("Error al descartar cambios: " + e.getMessage());
        }
    }
    ////////USAR DESPUES DE PREPARAR EL STATEMENT


    public int ejecutarInstruccion() {

        try {
            if(st.isClosed()){
                System.out.println("no se ha preparado ninguna instruccion");
                return 1;
            }
            st.executeUpdate();
            st.close();
            return 0;
        } catch (SQLException e) {
            System.out.println("Error en la instruccion: " + e.getMessage());
            return e.getErrorCode();
        }
    }
    public ResultSet ejecutarConsulta(){

        try {
            if(st.isClosed()){
                System.out.println("no se ha preparado ninguna instruccion");
                return null;
            }
            rs = st.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error en la instruccion: " + e.getMessage());
        }
        return rs;
    }
    public static ConexionBDLite obtenerConector(){
        if(conector == null){
            conector = new ConexionBDLite();
        }
        return conector;
    }

    public Connection getConexion() {
        return conexion;
    }

    public static void main(String[] args) {
        obtenerConector();
        try {
            String[] tipo = {"varchar"};
            Object[] valor = {"B005"};
            conector.prepararStatement("select * from branch WHERE branchNo=?", tipo, valor);

            ResultSet r = conector.ejecutarConsulta();
            //numero de columnas de la fila
            int length = r.getMetaData().getColumnCount();
            //iterar cada fila
            while (r.next()){
                for(int i = 0; i < length; i++){
                    System.out.print(r.getObject(i+1) + " ");
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
