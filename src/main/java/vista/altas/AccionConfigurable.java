/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.altas;

import controlador.DAO;
import modelo.ModeloBD;
import modelo.Registrable;
import vista.Componedor;
import vista.Extractor;
import vista.Inspector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author TheGr
 */
public class AccionConfigurable extends javax.swing.JFrame {

    /**
     * Creates new form pacienteAltas
     */
    public AccionConfigurable() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelFormulario = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtAgregar = new javax.swing.JLabel();
        panelHeader = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAgregar = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        panelFormulario.setBackground(new java.awt.Color(153, 153, 153));
        panelFormulario.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        txtAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtAgregar.setForeground(new java.awt.Color(255, 255, 255));
        txtAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAgregar.setText("Agregar ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAgregar)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        panelFormulario.add(jPanel3);
        jPanel3.setBounds(0, 0, 440, 39);

        panelHeader.setBackground(new java.awt.Color(61, 61, 61));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Formulario de datos");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelFormulario.add(panelHeader);
        panelHeader.setBounds(0, 45, 440, 16);

        jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));

        tablaAgregar.setBackground(new java.awt.Color(71, 71, 71));
        tablaAgregar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 3, true));
        tablaAgregar.setForeground(new java.awt.Color(255, 255, 255));
        tablaAgregar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaAgregar.setGridColor(new java.awt.Color(153, 153, 153));
        tablaAgregar.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tablaAgregar.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tablaAgregar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
                    .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void configurarLabel(String texto){
        txtAgregar.setText(texto);
    }
    public void nombreTabla(String t){
        tabla = t;
    }
    public String getNombreTabla(){
        return tabla;
    }
    public void configurarTabla(){
        Componedor.columnasTabla(tablaAgregar, tabla);
    }
    public void datosGeneracion(String[] lb, String[] cs, String[] ts, int[] ls, boolean[] os, String[][] ps){

        labels = lb;
        campos = cs;
        tipos = ts;
        longitudes = ls;
        obligatorios = os;
        predefinidos = ps;
        indicesPrimarias = ModeloBD.primariasDe(tabla);
    }
    public boolean esPrimario(int i){
        for(int idx : indicesPrimarias){
            if(i==idx)return true;
        }
        return false;
    }
    public void generar(){
        //i es el indice del campo
        //sep es la separacion vertical

        int i = 0, sep = 10;
        Point p = panelHeader.getLocation();
        checksBusqueda = new JCheckBox[campos.length];
        inputs = new ArrayList<>();

        for(String campo : campos){

            JComponent[] comps = Componedor.identificarComponente(campo);
            if(campo.equalsIgnoreCase("jtextfield")){
                comps[0].setBounds(p.x + panelFormulario.getWidth() / 3 + 80, p.y+30+i*30, panelFormulario.getWidth()/3+30, 30);
            }
            if(campo.equalsIgnoreCase("jdatefield")) { //JDateField consta de 3 JText DD/MM/AAAA
                //poner los 3 en filita india
                int wid = (panelFormulario.getWidth() / 3 + 30) / 3; //largura del jlabel pero entre 3
                comps[0].setBounds(p.x + panelFormulario.getWidth() / 3 + 80, p.y + 30 + i * 30, wid, 30);
                comps[1].setBounds(p.x + panelFormulario.getWidth() / 3 + 80 + wid, p.y + 30 + i * 30, wid, 30);
                comps[2].setBounds(p.x + panelFormulario.getWidth() / 3 + 80 + wid*2, p.y + 30 + i * 30, wid+10, 30);
            }
            if(campo.equalsIgnoreCase("jdecimalfield")){//JDecimalField consta de 2 JText
                int wid = (int)((panelFormulario.getWidth() / 3 + 30) * 0.65);
                int fwid = panelFormulario.getWidth()/3+30;
                comps[0].setBounds(fwid+50, p.y + 30 + i * 30, wid, 30);
                JLabel l = new JLabel(".");
                l.setBounds(p.x+fwid+50+wid, p.y + 30 + i * 30, 5, 30);
                panelFormulario.add(l);
                comps[1].setBounds(p.x + fwid+50 + wid, p.y + 30 + i * 30, fwid-wid, 30);
            }
            if(campo.equalsIgnoreCase("jnumberfield")){
                comps[0].setBounds(p.x + panelFormulario.getWidth() / 3 + 80, p.y+30+i*30, panelFormulario.getWidth()/3+30, 30);
            }
            if(campo.equalsIgnoreCase("jcheckbox")){
                comps[0].setBounds(p.x + panelFormulario.getWidth() / 3 + 80, p.y+30+i*30, 30, 30);
            }
            for(JComponent c : comps){
                inputs.add(c);
                panelFormulario.add(c);
            }

            checksBusqueda[i] = new JCheckBox("");
            checksBusqueda[i].setVisible(false);
            checksBusqueda[i].setBounds((p.x + panelFormulario.getWidth() / 3+30)*2+60, p.y+30+i*30, 20, 30);

            JLabel label = new JLabel();
            label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
            label.setForeground(new java.awt.Color(0, 0, 0));
            if(esPrimario(i)) {
                label.setFont(new java.awt.Font("Segoe UI", Font.BOLD + Font.ITALIC, 14));
                label.setForeground(new java.awt.Color(100, 100, 0));
            }
            label.setHorizontalAlignment(SwingConstants.TRAILING);
            label.setText(labels[i]);
            panelFormulario.add(label);
            label.setBounds(p.x, p.y+30+i*30, panelFormulario.getWidth()/3+70, 30);
            i++;
            p.y+=sep;
        }

        btnPrimario = botonNuevo(" ", new Color(255, 255, 255), new Color(255, 51, 51),
                p.x+230, p.y+30+i*30+30, 150, 50);

        btnRestablecer = botonNuevo("Restablecer", new Color(255, 255, 255), new Color(51, 102, 255),
                p.x+60, p.y+30+i*30+30, 150, 50);

        btnCancelar = botonNuevo("Cancelar", new Color(255, 255, 255), new Color(255, 51, 51),
                p.x+150, p.y+30+i*30+120, 150, 50);

        btnRestablecer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputs.forEach(component -> { //a canijote como el js
                    limpiarInput(component);
                });
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputs.forEach(component -> limpiarInput(component));
                if(limbo){
                    int opcion = JOptionPane.showOptionDialog(getContentPane(), "Hay cambios sin guardar, Seleccione una opcion", "Cambios pendientes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Confirmar cambios","Descartar cambios","Regresar"}, null);
                    System.out.println(opcion);
                    if(opcion==0){
                        DAO.d.commitear();
                        limbo = false;
                    }
                    if(opcion==1){
                        DAO.d.rolar();
                        limbo = false;
                    }
                }
                if(!limbo) dispose();

            }
        });
    }
    public void limpiarInput(JComponent input){
        if(input instanceof JTextField) ((JTextField) input).setText("");
        if(input instanceof JComboBox<?>) ((JComboBox<?>) input).setSelectedIndex(0);
    }

    public void configurarBotonPrimario(byte accion, String t, Color fg, Color bg){
        btnPrimario.setText(t);
        btnPrimario.setBackground(bg);
        btnPrimario.setForeground(fg);
        ActionListener listener = null;
        switch (accion){
            case 0:
            listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object[] datos = extraerDatos();
                    if(datos == null) return;
                    byte estado = validarFormulario(datos, obligatorios, longitudes, tipos);
                    if(estado!=0)return;

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Registrable r = ModeloBD.instanciar(datos, tabla);
                            int codigo = DAO.d.agregarPreparedUniversal(r);
                            if(codigo != 0){
                                panelError(codigo);
                                return;
                            }
                            JOptionPane.showMessageDialog(getContentPane(), "Registro exitoso", "Aviso de inserción", JOptionPane.INFORMATION_MESSAGE);
                            limbo = true;
                        }
                    }).start();


                }
            };
            break;
            case 1:
                prepararCheckBusqueda();
                checkListeners();
                desactivarInputs();
                tablaAgregar.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tablaClick();
                    }
                });
                listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object[] pk = extraerDatos();
                        //Registrable registro = ModeloBD.instanciar(pk, tabla);
                        String[] primariaNombres = Componedor.obtenerFiltrosNombres(ModeloBD.nombresDe(tabla), indicesPrimarias);
                        String[] primariaTipos = Componedor.obtenerFiltrosNombres(tipos, indicesPrimarias);
                        Object[] primariaValores = Componedor.obtenerFiltrosValores(pk, indicesPrimarias);

                        if(validarFormulario(pk, obligatorios, longitudes, tipos, checksBusqueda)!=0) return;

                        for(int i : indicesPrimarias){
                            if(!checksBusqueda[i].isSelected()){
                                JOptionPane.showMessageDialog(getContentPane(), "Asegurese de habilitar todas las llaves primarias", "Eliminacion riesgosa", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        if(tabla.equalsIgnoreCase("farmaceutica")){
                            int opcion = JOptionPane.showOptionDialog(getContentPane(),
                                    "ADVERTENCIA: eliminar este registro provocará que cualquier registro relacionado sea eliminado", "Alerta de eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                                    null, new String[]{"Cancelar","Continuar"}, null);
                            if(opcion==0){
                                System.out.println("cancelao");
                                return;
                            }
                        }
                        //es obligatorio dar las llaves primarias (se necesitara un boton de llaves primarias aqui)
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int i = DAO.d.EliminarPreparedUniversal(tabla, primariaNombres, primariaTipos, primariaValores);
                                if(i != 0){
                                    panelError(i);
                                    return;
                                }
                                JOptionPane.showMessageDialog(getContentPane(), "Eliminacion completada");
                                limbo = true;
                            }
                        }).start();

                    }
                };
                break;
            case 2:
                tablaAgregar.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tablaClick();
                    }
                });
                listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object[] pk = extraerDatos();
                        if(validarFormulario(pk, obligatorios, longitudes, tipos)!=0) return;

                        Registrable registro = ModeloBD.instanciar(pk, tabla);
                        String[] primariaNombres = Componedor.obtenerFiltrosNombres(ModeloBD.nombresDe(tabla), indicesPrimarias);
                        String[] primariaTipos = Componedor.obtenerFiltrosNombres(tipos, indicesPrimarias);
                        Object[] primariaValores = Componedor.obtenerFiltrosValores(pk, indicesPrimarias);

                        //es obligatorio dar las llaves primarias (se necesitara un boton de llaves primarias aqui)
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int i = DAO.d.actualizarPreparedUniversal(registro, primariaNombres, primariaTipos, primariaValores);
                                if(i != 0){
                                    panelError(i);
                                    return;
                                }
                                JOptionPane.showMessageDialog(getContentPane(), "Cambios efectuados");

                                System.out.println("modificadera: " + Arrays.toString(pk));
                                limbo = true;
                            }
                        }).start();
                    }
                };
                break;
            case 3:
                prepararCheckBusqueda();
                checkListeners();
                desactivarInputs();
                listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object[] pk = extraerDatos();
                        System.out.println("consultadera: " + Arrays.toString(pk));
                        if(pk == null) return;

                        if(validarFormulario(pk, obligatorios, longitudes, tipos, checksBusqueda)!=0) return;

                        int[] indicesValidos = Componedor.ubicarCamposValidos(pk);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayList<Registrable> rs = formatearConsulta(indicesValidos, pk);
                                System.out.println("uy uy uy " + rs.size());
                                Componedor.limpiarTabla(tablaAgregar);
                                rs.forEach(reg ->{Componedor.filaTabla(tablaAgregar, reg);});
                            }
                        }).start();


                    }
                };
                break;
        }
        btnPrimario.addActionListener(listener);
    }

    public void tablaClick(){
        int rowa = tablaAgregar.getSelectedRow();
        Object[] selector = Extractor.extraerFila(tablaAgregar, rowa);
        //System.out.println(Arrays.toString(selector));
        Componedor.autoRellenar(inputs, campos, selector);
    }
    public void desactivarInputs(){
        for(int i = 0; i < inputs.size(); i++){
            inputs.get(i).setEnabled(false);
        }
    }
    public void checkListeners(){
        for(int i = 0; i < checksBusqueda.length; i++){
            JCheckBox jcaja = checksBusqueda[i];
            int indice = i;
            jcaja.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JComponent[] inps = obtenerComponentes(indice);
                    //System.out.println(inps.length);

                    for(int i = 0; i < inps.length; i++){
                        inps[i].setEnabled(jcaja.isSelected());
                    }
                }
            });
        }
    }
    public JComponent[] obtenerComponentes(int idx){
        int i = 0, j = 0;
        //recorrer componentes con sus respectivos desplazamientos
        for(; i < idx; i++){
            if(campos[i].equals("JDateField")) j+=2;
            if(campos[i].equals("JDecimalField")) j+=1;
        }
        //identificar cuantos componentes corresponden al input
        int indiceInput = i+j;
        if(campos[i].equals("JDateField")) {
            return new JComponent[]{
                    inputs.get(i+j), inputs.get(i+j+1), inputs.get(i+j+2)
            };
        };
        if(campos[i].equals("JDecimalField")) {
            return new JComponent[]{
                    inputs.get(i+j), inputs.get(i+j+1)
            };
        }
        return new JComponent[]{
                inputs.get(i+j)
        };
    }
    public void prepararCheckBusqueda(){
        for(int i = 0; i < checksBusqueda.length; i++){
            checksBusqueda[i].setVisible(true);
            panelFormulario.add(checksBusqueda[i]);
        }
    }
    public byte validarFormulario(Object[] datos, boolean [] nonulos, int[] longitudes, String[] tipos){
        for(int i = 0; i < datos.length; i++){

            Object dato = datos[i];
            boolean nonulo = nonulos[i];
            int longitud = longitudes[i];
            String tipo = tipos[i];

            //System.out.println(dato + " , " + tipo + ", " + longitud);
            if(dato==null && (nonulo || esPrimario(i))){
                //contrastar no nulos con el modelo y verificar
                JOptionPane.showMessageDialog(getContentPane(), "El campo '"+labels[i]+"' no debe ser nulo", "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
                return 1;
            }else if (tipo.equalsIgnoreCase("char") && dato.toString().length() != longitud){
                JOptionPane.showMessageDialog(getContentPane(), "la longitud de '"+labels[i]+"' ("+dato.toString().length()+") debe ser de "+longitud, "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
                return 2;
            }
            else if(longitud > 0 && dato.toString().length() > longitud){
                JOptionPane.showMessageDialog(getContentPane(), "la longitud de '"+labels[i]+"' ("+dato.toString().length()+") no debe exceder "+longitud, "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
                return 3;
            }
            //en el caso optimo estos deberian cacharse con eventos de teclado
            //if dato excede longitud maxima
            //if dato no es del tipo correcto
        }
        //todo bonito
        return 0;
    }
    public byte validarFormulario(Object[] datos, boolean [] nonulos, int[] longitudes, String[] tipos, JCheckBox[] checks){
        for(int i = 0; i < datos.length; i++){

            Object dato = datos[i];
            boolean nonulo = nonulos[i];
            int longitud = longitudes[i];
            String tipo = tipos[i];

            //System.out.println(dato + " , " + tipo + ", " + longitud);
            //System.out.println( "VALIDA : "+labels[i] + ", " + dato +", " + checksBusqueda[i].isSelected());
            if(dato==null){
                if((nonulo || esPrimario(i)) && checks[i].isSelected()){
                    //contrastar no nulos con el modelo y verificar
                    JOptionPane.showMessageDialog(getContentPane(), "El campo '"+labels[i]+"' no debe ser nulo", "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
                    return 1;
                }
            }
            else if (tipo.equalsIgnoreCase("char") && dato.toString().length() > longitud ){
                JOptionPane.showMessageDialog(getContentPane(), "la longitud de '"+labels[i]+"' ("+dato.toString().length()+") debe ser no mayor a "+longitud, "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
                return 2;
            }
            else if(longitud > 0 && dato.toString().length() > longitud){
                JOptionPane.showMessageDialog(getContentPane(), "la longitud de '"+labels[i]+"' ("+dato.toString().length()+") no debe exceder "+longitud, "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
                return 3;
            }
            //en el caso optimo estos deberian cacharse con eventos de teclado
            //if dato excede longitud maxima
            //if dato no es del tipo correcto
        }
        //todo bonito
        return 0;
    }
    public JButton botonNuevo(String t, Color fg, Color bg, int x, int y, int w, int h){
        JButton btn = new JButton(t);
        btn.setBackground(bg);
        btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn.setForeground(fg);
        panelFormulario.add(btn);
        btn.setBounds(x, y, w, h);
        return btn;
    }
    public Object[] extraerDatos(){

        JComponent[] inps = new JComponent[inputs.size()];
        int i = 0;

        for(JComponent in : inputs){
            inps[i] = in;
            try{

                Extractor.extraerDatos(campos, tipos, inputs, i);
                i++;
            }catch (NumberFormatException e){
                i--;
                System.out.println(i+", AAAAAAAAA " + inputs.size());
                Object tope = Componedor.maximo(tipos[i]);
                JOptionPane.showMessageDialog(getContentPane(), "El campo '"+labels[i]+"' solo admite numeros"+
                        (Integer.parseInt(tope.toString()) > 0 ? " no mayores a " + tope : ""), "Error de datos", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        i--;
        try{
            Extractor.extraerDatos(campos, tipos, inps);
        }catch (NumberFormatException e){
            Object tope = Componedor.maximo(tipos[i]);
            JOptionPane.showMessageDialog(getContentPane(), "El campo '"+labels[i]+"' solo admite numeros"+
                    (Integer.parseInt(tope.toString()) > 0 ? " no mayores a " + tope : ""), "Error de datos", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return Extractor.extraerDatos(campos, tipos, inps);
    }
    public int[] primaryValidas(){
        ArrayList<Integer> a = new ArrayList<Integer>();
        for(int i : indicesPrimarias){

            if(!checksBusqueda[i].isVisible() || (checksBusqueda[i].isVisible() && checksBusqueda[i].isSelected())){
                a.add(i);
            }
        }
        int[] o = new int[a.size()];
        int j = 0;
        for(int i : a){
            o[j]=i;
            j++;
        }
        return o;
    }
    public ArrayList<Registrable> formatearConsulta(int[] indices, Object[] datos){
        String[] filtroNombres = Componedor.obtenerFiltrosNombres(ModeloBD.nombresDe(tabla), indices);
        String[] filtrosTipos = Componedor.obtenerFiltrosTipos(tipos, indices);
        Object[] filtrosValores = Componedor.obtenerFiltrosValores(datos, indices);
        //el dao excluye coindicencias parciales, habra que arreglarlo
        //arreglada 1, dao y conexionBDLite

        System.out.println("CONCAMPOS: " + Arrays.toString(indices) + ", " + Arrays.toString(filtrosValores));
        //System.out.println(Arrays.to  String(indices));
        //System.out.println("FORMATEA " + Arrays.toString(filtrosValores));

        return DAO.d.consultarPreparedUniversal(tabla, new String[]{"*"}, filtroNombres, filtrosTipos, filtrosValores, true);
    }
    public void consultaCampos(){

        Object[] datos = null;
        try {
            datos = extraerDatos();
            //System.out.println(Arrays.toString(datos));
            if(datos == null) return;
        } catch (NumberFormatException e) {
            //System.out.println(Arrays.toString(datos));
            //JOptionPane.showMessageDialog(getContentPane(), "la longitud de '"+labels[i]+"' ("+dato.toString().length()+") no debe exceder ", "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int[] indices = primaryValidas();
        int[] comb = Inspector.intersectar(indices, indicesPrimarias);
        comb = Inspector.intersectar(comb, Componedor.ubicarCamposValidos(datos));
        //datos = Componedor.filtrarIndices(datos, indices);
        //el dao excluye coindicencias parciales, habra que arreglarlo
        //arreglada 1, dao y conexionBDLite

        ArrayList<Registrable> rs = formatearConsulta(comb, datos); //DAO.d.consultarPreparedUniversal(tabla, new String[]{"*"}, filtroNombres, filtrosTipos, filtrosValores, true);

        System.out.println("uy uy uy " + rs.size());
        Componedor.limpiarTabla(tablaAgregar);
        rs.forEach(reg ->{Componedor.filaTabla(tablaAgregar, reg);});

    }
    public int[] indicesComponentes(String campo){
        return Componedor.ubicarCamposCoincidencia(campos, campo);
    }
    public void activarPK(){
        int k = 0;
        System.out.println("PK puesta: " + labels[k]);
        agregarListenerTecleo(new int[]{k}); //por defecto la PK siempre provoca consultas
    }
    //funciones al tecleo:
    //1. consultar y actualizar tabla
    //2. impedir tecleos de caracteres invalidos
    public void agregarListenerTecleo(int[] indices){

        for(int i : indices){
            inputs.get(i).addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    super.keyTyped(e);
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    super.keyPressed(e);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            consultaCampos();
                        }
                    }).start();

                }
            });
        }
    }
    public void panelError(int codigo){
        switch (codigo){
            case 19: JOptionPane.showMessageDialog(getContentPane(), getMensajeFK(tabla), "Error de relacion", JOptionPane.ERROR_MESSAGE);
            default:
                JOptionPane.showMessageDialog(getContentPane(), "Los datos no son correctos: " + codigo, "Error de relacion", JOptionPane.ERROR_MESSAGE);
                System.out.println("Codigo " + codigo);
        }
    }
    public String getMensajeFK(String tabla){
        switch (tabla){
            case "Paciente": return "No se encontró al medico de cabecera";
            case "Medico": return "Test";
            case "Medicamento": return "No se encontró la farmacéutica";
            case "Recetas": return "Los datos apuntan a un paciente, medico o medicamento que no existe";
            case "Farmacia_Contrato_Farmaceutica": return "No se encontró la farmacia, la farmacéutica o el paciente";
            case "Farmacia_Inventario": return "No se encontro la farmacia o el medicamento";
        }
        return "El registro contiene datos de registros inexistentes";
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AccionConfigurable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccionConfigurable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccionConfigurable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccionConfigurable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccionConfigurable().setVisible(true);
            }
        });
    }
    private ArrayList<JComponent> inputs;

    private String tabla;
    private String[] nombresFiltros;
    private Object[] valores;
    private String[] labels;
    private String[] campos;
    private String[] tipos;
    private int[] longitudes;
    private boolean[] obligatorios;
    private int[] indicesPrimarias;
    private String[][] predefinidos;
    private JCheckBox[] checksBusqueda;
    private String mensajeExito;

    private boolean limbo = false;
    private JButton btnPrimario;
    private JButton btnRestablecer;
    private JButton btnCancelar;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JTable tablaAgregar;
    private javax.swing.JLabel txtAgregar;
    // End of variables declaration//GEN-END:variables
}
