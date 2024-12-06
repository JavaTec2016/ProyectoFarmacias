package controlador;

import conexionBD.ConexionBD;
import conexionBD.ConexionBDLite;
import modelo.*;
import vista.Componedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DAO {
    ConexionBD conexion = ConexionBD.obtenerConector();
    ConexionBDLite conexionLite = ConexionBDLite.obtenerConector();

    public static DAO d = new DAO();
    public Usuario obtenerUsuario(){
        return conexionLite.getUsr();
    }
    //metodo puercote, identifica y registra dinamicamente los diferentes objetos que usa la BD
    //0: operacion exitosa
    //1: un campo no existe para el objeto dado
    //2: un campo es inaccesible para la clase dada
    public int agregarUniversal(Registrable registrable){
        //ArrayList extraidos = new ArrayList();
        String tipo = registrable.getClass().getName();
        for(int j = tipo.length()-1; j >= 0; j--){
            if(tipo.charAt(j) == '.'){
                tipo = tipo.substring(j+1);
                break;
            }
        }
        //obtener la informacion del registrable
        String[] camposNombres = registrable.propiedades();
        String[] camposTipos = registrable.tipoDatos();

        //preparar la sentencia sql
        System.out.println(tipo);
        String sql = "INSERT INTO " + tipo + " VALUES(";

        //reflexion truco a ver si no se rompe mi programa uyuyuy
        int i = 0;
        for(; i < camposTipos.length; i++){
            String campo = camposNombres[i];
            String dato = camposTipos[i];
            Object valor = null;
            //identificar el tipo de dato, si existe en el objeto
            try {
                Field f = registrable.getClass().getDeclaredField(campo);
                f.setAccessible(true);//con esto se pueden obtener los datos representados en el field
                valor = f.get(registrable);
            } catch (NoSuchFieldException e) {
                System.out.println(campo+ " > El campo que se busca no existe. " + tipo);
                return 1;
            } catch (IllegalAccessException e) {
                System.out.println(campo+ " > El campo no es accesible. " + tipo);
                return 2;
            }
            //la unica diferencia entre tipos de datos es que strings son valores que llevan comillas en sql
            String sqlValor = valor.toString();
            if(dato.equals("java.lang.String")){
                sqlValor = "'"+sqlValor+"'";
            }
            sql += sqlValor+", ";
            //System.out.println(valor.toString());
        }
        //al final sobran una coma y espacio en la sentencia, hay que arreglarlo
        int corte = sql.length()-2;
        sql = sql.substring(0, corte)+")";
        System.out.println(sql);
        return conexion.ejecutarInstruccionDML(sql);
        //nuestra sentencia esta lista, le falta una BD
        //conexion.ejecutarInstruccionDML(sql);

    }

    public int agregarPreparedUniversal(Registrable registrable){
        String tipo = registrable.getClass().getName();
        for(int j = tipo.length()-1; j >= 0; j--){
            if(tipo.charAt(j) == '.'){
                tipo = tipo.substring(j+1);
                break;
            }
        }

        String[] camposTipos = registrable.tipoDatos();
        Object[] camposValores = registrable.obtenerValores();

        System.out.println(Arrays.toString(camposTipos));
        String sql = "INSERT INTO " + tipo + " VALUES(";
        int i = 0;
        for(; i < camposTipos.length; i++){ sql += "?,"; }
        int corte = sql.length()-1;
        sql = sql.substring(0, corte)+")";

        try {
            conexionLite.prepararStatement(sql, camposTipos, camposValores);
            return conexionLite.ejecutarInstruccion();
        } catch (SQLException e) {
            System.out.println("Error en la insercion: " + e.getMessage());
            return  e.getErrorCode();
        }
    }

    //recibe una cantidad cualquiera de filtros con datos obtenidos y formateados desde la interfaz
    //por lo que no es necesario hacer cochinadas con clases
    public int EliminarUniversal(String tabla, String ...filtros){
        String sql = "DELETE FROM " + tabla + " WHERE ";
        for(String filtro : filtros){
            sql += filtro+" AND ";
        }
        //sobran 1 espacio, 3 caracteres AND y 1 espacio, 5 caracteres
        int corte = sql.length()-5;
        sql = sql.substring(0, corte);
        System.out.println(sql);

        return conexion.ejecutarInstruccionDML(sql);
    }

    public int EliminarPreparedUniversal(String tabla, String[] campos, String[] tipos, Object[] valores){
        String sql = "DELETE FROM " + tabla + " WHERE ";
        for(int i = 0; i < campos.length; i++){
            sql += campos[i]+"=? AND ";
        }
        int corte = sql.length()-5;
        sql = sql.substring(0, corte);
        try {
            conexionLite.prepararStatement(sql, tipos, valores);
            conexionLite.ejecutarInstruccion();
            return 0;
        } catch (SQLException e) {
            System.out.println("Error en la eliminacion: " + e.getMessage());
            return e.getErrorCode();
        }
    }

    //la interfaz toma los datos, genera un objeto y lo pasa al metodo
    public int actualizarUniversal(Registrable registrable, int primarias){
        //lectura del objeto, similar al agregarUniversal
        String tipo = registrable.getClass().getName();
        //indica que campos se dejan intactos y que campos se establecen en nulo
        boolean[] nnl = registrable.noNulos();
        for(int j = tipo.length()-1; j >= 0; j--){
            if(tipo.charAt(j) == '.'){
                tipo = tipo.substring(j+1);
                break;
            }
        }

        String[] camposNombres = registrable.propiedades();
        String[] camposTipos = registrable.tipoDatos();

        //preparar la sentencia sql
        String sql = "UPDATE " + tipo + " SET ";
        String restr = " WHERE ";
        if(primarias == 0) {
            System.out.println("no se establecieron claves primarias a excluir");
        }
        //formateo de las restricciones de claves primarias
        int i = 0;
        for(; i < primarias; i++){
            String campo = camposNombres[i];
            String dato = camposTipos[i];
            String sqlRes = obtenerValor(dato, campo, registrable, tipo);
            System.out.println("ID: " + sqlRes);
            if(sqlRes.equalsIgnoreCase("NULL")) return 1;
            restr += campo+"="+sqlRes+" AND ";
        }
        restr = restr.substring(0, restr.length()-5);

        //establecer la edicion del resto de campos
        int cambios = 0;
        for(; i < camposTipos.length; i++){
            String campo = camposNombres[i];
            String dato = camposTipos[i];
            String sqlValor = obtenerValor(dato, campo, registrable, tipo);
            //si es nulo se queda sin cambios
            //System.out.println(sqlValor + " valor");
            if(sqlValor.equals("NULL") && nnl[i]) continue;
            sql += campo + "="+sqlValor+", ";
            cambios++;
        }
        System.out.println("DAO actualizar, cambios: " + cambios);
        //si no hay cambios no hay nada que hacer;
        if(cambios == 0) return 1;
        int corte = sql.length()-2;
        sql = sql.substring(0, corte);

        sql+=restr;
        //la instrucción está lista para ejecutarse
        System.out.println(sql);
        return conexion.ejecutarInstruccionDML(sql);

    }

    public int actualizarPreparedUniversal(Registrable registrable, String[] filtrosNombres, String[] filtrosTipos, Object[] filtrosValores){
        //lectura del objeto, similar al agregarUniversal
        String tipo = registrable.getClass().getName();

        for(int j = tipo.length()-1; j >= 0; j--){
            if(tipo.charAt(j) == '.'){
                tipo = tipo.substring(j+1);
                break;
            }
        }
        String[] camposNombres = registrable.propiedades();
        String[] camposTipos = registrable.tipoDatos();
        Object[] camposValores = registrable.obtenerValores();
        String[] instruccionTipos = new String[camposTipos.length+filtrosTipos.length];
        Object[] instruccionValores = new Object[camposValores.length+filtrosValores.length];
        String sql = "UPDATE " + tipo + " SET ";
        String restr = " WHERE ";

        //el set actualiza TODOS los campos y el formulario inicia con los datos actuales en UI

        int i = 0;
        for(; i < camposValores.length; i++){
            sql += camposNombres[i]+"=?"+", ";
            instruccionTipos[i] = camposTipos[i]; //rellena primero los datos del SET
            instruccionValores[i] = camposValores[i];
        }
        int corte = sql.length()-2;
        sql = sql.substring(0, corte);
        //formateo del WHERE
        int j = 0;
        for(; j < filtrosTipos.length; i++, j++){
            restr += filtrosNombres[j]+"=?"+" AND ";
            instruccionTipos[i+j] = filtrosTipos[j]; //rellena despues los datos del WHERE
            instruccionValores[i+j] = filtrosValores[j];
        }

        restr = restr.substring(0, restr.length()-5);
        sql+=restr;
        //la sentencia y valores ya estan listos
        try {
            //System.out.println(sql);
            //System.out.println(Arrays.toString(instruccionTipos) +"m, "+ Arrays.toString(instruccionValores));
            conexionLite.prepararStatement(sql, instruccionTipos, instruccionValores);
            conexionLite.ejecutarInstruccion();
            return 0;
        } catch (SQLException e) {
            System.out.println("Error en la actualizacion: " + e.getMessage());
            return e.getErrorCode();
        }

    }

    public String obtenerValor(String dato, String campo, Registrable registrable, String tipo){
        Object valor = null;
        try {
            Field f = registrable.getClass().getDeclaredField(campo);
            f.setAccessible(true);//con esto se pueden obtener los datos representados en el field
            valor = f.get(registrable);
        } catch (NoSuchFieldException e) {
            System.out.println(campo+ " > El campo que se busca no existe. " + tipo);
            return null;
        } catch (IllegalAccessException e) {
            System.out.println(campo+ " > El campo no es accesible. " + tipo);
            return null;
        }
        if(valor == null) return "NULL";
        String sqlValor = valor.toString();
        if(dato.equals("java.lang.String")){
            sqlValor = "'"+sqlValor+"'";
        }
        return sqlValor;
    }

    public ArrayList<Registrable> consultarUniversal(String tabla){
        ArrayList<Registrable> registros = new ArrayList<Registrable>();

        String sql = "SELECT * FROM " + tabla;

        ResultSet rs = conexion.ejecutarConsultaSQL(sql);
        //argumentos para el registrable;
        Object[] args;
        int i = 0;
        try {
            //saber que clase es
            Class<?> modelo = Class.forName("modelo."+tabla);


            //saber que constructor tiene la clase
            Constructor<?> cons = modelo.getConstructors()[0];

            //iniciar un objeto con espacios segun la cantidad de parametros
            args = new Object[cons.getParameterCount()];

            rs.next();
            do{
                for(int j = 0; j < args.length; j++){
                    //rellenar los argumentos

                    args[j] = rs.getObject(j+1);
                    //System.out.println( rs.getObject(j+1).getClass() );
                    //resulta que si le metes un date a un constructor con strings truena
                    if(rs.getObject(j+1).getClass().getName().equals("java.sql.Date")){

                        args[j] = rs.getObject(j+1).toString();
                    }
                }
                //inicializar el objeto como registrable
                Registrable o = (Registrable) cons.newInstance(args);

                //y agregarlo al output
                registros.add(o);

            }while (rs.next());

        } catch (SQLException e) {
            System.out.println("Error en consulta SQL");
            //throw new RuntimeException(e);
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("modelo."+tabla+" no existe");
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            System.out.println("Error al invocar el constructor de modelo."+tabla);
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            System.out.println("modelo."+tabla+" no puede ser instanciado");
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            System.out.println("modelo."+tabla+" no puede ser accesado");
            throw new RuntimeException(e);
        }
        return registros;
    }
    public ArrayList<Registrable> consultarUniversal(String tabla, String filtro){
        ArrayList<Registrable> registros = new ArrayList<Registrable>();

        String sql = "SELECT * FROM " + tabla + " WHERE " + filtro;

        ResultSet rs = conexion.ejecutarConsultaSQL(sql);
        //argumentos para el registrable;
        Object[] args;
        int i = 0;
        try {
            //saber que clase es
            Class<?> modelo = Class.forName("modelo."+tabla);


            //saber que constructor tiene la clase
            Constructor<?> cons = modelo.getConstructors()[0];

            //iniciar un objeto con espacios segun la cantidad de parametros
            args = new Object[cons.getParameterCount()];

            rs.next();
            do{
                for(int j = 0; j < args.length; j++){
                    //rellenar los argumentos

                    args[j] = rs.getObject(j+1);
                    //System.out.println( rs.getObject(j+1).getClass() );
                    //resulta que si le metes un date a un constructor con strings truena
                    if(rs.getObject(j+1).getClass().getName().equals("java.sql.Date")){

                        args[j] = rs.getObject(j+1).toString();
                    }
                }
                //inicializar el objeto como registrable
                Registrable o = (Registrable) cons.newInstance(args);

                //y agregarlo al output
                registros.add(o);

            }while (rs.next());

        } catch (SQLException e) {
            System.out.println("Error en consulta SQL: " + e.getErrorCode());

            return null;
            //throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("modelo."+tabla+" no existe");
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            System.out.println("Error al invocar el constructor de modelo."+tabla);
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            System.out.println("modelo."+tabla+" no puede ser instanciado");
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            System.out.println("modelo."+tabla+" no puede ser accesado");
            throw new RuntimeException(e);
        }
        return registros;
    }

    public Object rellenarArgumento(String tipo){
        int idx = tipo.lastIndexOf('.');
        if(idx > -1) tipo = tipo.substring(0, idx);
        switch (tipo){
            case "String": return " ";
            case "Byte":
            case "Short":
            case "Integer": return 0;
            case "Float":
            case "Double": return 0.0;
            case "Boolean": return false;
        }
        return null;
    }
    public boolean esTexto(String tipo){
        int ix = tipo.lastIndexOf('.');
        tipo = ix == -1 ? tipo : tipo.substring(ix+1);
        switch (tipo.toLowerCase()){
            case "varchar":
            case "char":
            case "string":
            case "mediumtext": return true;
            default: return false;
        }
    }
    public ArrayList<Registrable> consultarPreparedUniversal(String tabla,
                                                             String[] selecNombres,
                                                             String[] filtroNombres, String[] filtroTipos, Object[] filtroValores, boolean parcial){

        ArrayList<Registrable> registros = new ArrayList<Registrable>();
        Object[] args;
        String select = "SELECT ";
        String where = " WHERE ";

        for(String nombre: selecNombres){ select += nombre+", "; }
        select = select.substring(0, select.length()-2) + " FROM " + tabla;
        int i = 0;
        for(String campo : filtroNombres){

            if(parcial && esTexto(filtroTipos[i]))where += campo+" LIKE ? AND ";
            else where += campo+"=? AND ";
            i++;
        }
        where = where.substring(0, where.length()-5);
        try {
            conexionLite.prepararStatement(select+where, filtroTipos, filtroValores, parcial);
            //System.out.println(Arrays.toString(filtroValores));
            ResultSet rs = conexionLite.ejecutarConsulta();
            //saber que clase es
            Class<?> modelo = Class.forName("modelo."+tabla);
            //saber que constructor tiene la clase
            Constructor<?> cons = modelo.getConstructors()[0];
            String[] argNames = new String[modelo.getDeclaredFields().length];
            String[] argTypes = new String[modelo.getDeclaredFields().length]; ///las longitudes siempre deberian coincidir
            Class[] paramTypes = cons.getParameterTypes();

            int i2 = 0;
            for(Field f : modelo.getDeclaredFields()){
                argNames[i2] = f.getName();
                argTypes[i2] = paramTypes[i2].getName();
                i2++;
            }
            //iniciar un objeto con espacios segun la cantidad de parametros
            args = new Object[cons.getParameterCount()];
            while(rs.next()) {

                for(int j = 0; j < args.length; j++){
                    String columna = rs.getMetaData().getColumnName(j+1);


                    ///LOS ARGUMENTOS Y LAS COLUMNAS NO COINCIDEN
                    //revisar el tipo de dato del argumento y rellenarlo con un valor nulo

                    if(!columna.equalsIgnoreCase(argNames[j])){
                        System.out.println(columna + " != "+argNames[j] +  " " + j + ":  "+ argTypes[j]);
                        args[j] = rellenarArgumento(argTypes[j]);
                    }else{
                        //rellenar los argumentos
                        //asegurar que el dato sea del tipo que el constructor requiere
                        args[j] = Componedor.extraerTexto(rs.getObject(j+1).toString(), argTypes[j]);
                        System.out.println(args[j].getClass().getName() + ", " + argTypes[j] + " ," + args[j]);

                        //resulta que si le metes un date a un constructor con strings truena
                        if(rs.getObject(j+1).getClass().getName().equals("java.sql.Date")){

                            args[j] = rs.getObject(j+1).toString();
                        }
                    }

                }

                //inicializar el objeto como registrable
                Registrable o = (Registrable) cons.newInstance(args);
                //y agregarlo al output
                registros.add(o);
            }

        } catch (SQLException e) {
            System.out.println("Error en consulta SQL: " + e.getErrorCode() + "\n" + e.getMessage());
            throw new RuntimeException(e);
            //return null;
        } catch (ClassNotFoundException e) {
            System.out.println("modelo."+tabla+" no existe");
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            System.out.println("Error al invocar el constructor de modelo."+tabla);
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            System.out.println("modelo."+tabla+" no puede ser instanciado");
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            System.out.println("modelo."+tabla+" no puede ser accesado");
            throw new RuntimeException(e);
        }
        return registros;
    }
    public String[] obtenerColumnas(String tabla){
        String[] out = null;
        try {
            conexionLite.prepararStatement("SELECT * FROM " + tabla, null, null);
            ResultSet rs = conexionLite.ejecutarConsulta();
            out = new String[rs.getMetaData().getColumnCount()];

            for(int i = 0; i < out.length; i++){
                out[i] = rs.getMetaData().getColumnName(i+1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return out;
    }
    public void commitear(){
        conexionLite.confirmar();
    }
    public void rolar(){
        conexionLite.descartar();
    }
}
