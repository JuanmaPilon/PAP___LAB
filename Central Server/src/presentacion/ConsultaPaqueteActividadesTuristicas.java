/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import logica.DTPaquete;
import logica.IControlador;

/**
 *
 * @author natil
 */
public class ConsultaPaqueteActividadesTuristicas extends javax.swing.JInternalFrame {
     private IControlador control;
    /**
     * Creates new form ConsultaPaqueteActividadesTuristicas2
     */
    public ConsultaPaqueteActividadesTuristicas(IControlador icu) {
        control = icu;
        initComponents();
    }
    
    public ConsultaPaqueteActividadesTuristicas(String nombrePaquete, IControlador icu) {
        initComponents();
        //aca tengo la actividad
        control = icu;
        cmbPaquetes.removeAllItems();
        cmbPaquetes.addItem(nombrePaquete);
        //cmbActividades.setSelectedItem(salida.getNombreActividad());
 
        
        //para qeu no quede en loop y no se pueda modificar esta entrada
        cmbPaquetes.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVer = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        txtValidez = new javax.swing.JTextField();
        txtDescuento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbPaquetes = new javax.swing.JComboBox<>();
        cmbActividades = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCategorias = new javax.swing.JTable();

        setClosable(true);
        setTitle("Consulta Paquete de Actividad Turistica");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        btnVer.setText("Ver");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre:");

        txtNombre.setEditable(false);

        jLabel4.setText("Descripcion:");

        jLabel5.setText("Periodo de Validez:");

        jLabel6.setText("Descuento:");

        txtDescripcion.setEditable(false);

        txtValidez.setEditable(false);

        txtDescuento.setEditable(false);

        jLabel1.setText("Seleccione un paquete:");

        jLabel7.setText("Ver detalles de Actividad Turistica:");

        cmbPaquetes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPaquetesItemStateChanged(evt);
            }
        });

        jLabel2.setText("Datos del paquete seleccionado:");

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jLabel13.setText("Lista Categorias del Paquete:");

        tablaCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaCategorias);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(18, 18, 18)
                                                .addComponent(cmbActividades, 0, 312, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtNombre)
                                                    .addComponent(txtValidez)
                                                    .addComponent(txtDescripcion)
                                                    .addComponent(txtDescuento))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnVer))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(289, 289, 289)
                                .addComponent(btnCerrar)))
                        .addGap(0, 90, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtValidez, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbActividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVer))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        
         if(cmbActividades.getSelectedIndex() != -1){
            String nombreActividad = (String) cmbActividades.getSelectedItem();
            ConsultaActividadTuristica verConsultaActividadTuristica = new ConsultaActividadTuristica(nombreActividad, control);
            getParent().add(verConsultaActividadTuristica);        
            verConsultaActividadTuristica.show();
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        List<DTPaquete> paquetes = control.traerListaDTPaquetes();
        
        for (DTPaquete dtpaquete : paquetes){
            System.out.println("acap"+dtpaquete.getNombre());
            cmbPaquetes.addItem(dtpaquete.getNombre());
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmbPaquetesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPaquetesItemStateChanged
        cmbActividades.removeAllItems();
        // Verifica si se seleccionó un departamento
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String paqueteSeleccionado = (String) cmbPaquetes.getSelectedItem();
        
        
        // Verifica si el departamento seleccionado no es nulo ni está vacío
        if (paqueteSeleccionado != null && !paqueteSeleccionado.isEmpty()) {
            
            DTPaquete dtpaquete = control.traerDTPaquete(paqueteSeleccionado);
            
            txtNombre.setText(dtpaquete.getNombre());
            txtDescripcion.setText(dtpaquete.getDescripcion());
            txtValidez.setText(String.valueOf(dtpaquete.getValidez()));
            txtDescuento.setText(String.valueOf(dtpaquete.getDescuento()));
            
            
            ArrayList<String> actividades = control.listaActividadesDelPaquete(paqueteSeleccionado);

            cmbActividades.removeAllItems();

            // Verifica si se encontraron actividades
            if (actividades != null && !actividades.isEmpty()) {
                for (String actividad : actividades) {
                    cmbActividades.addItem(actividad);
                }
                
            //Cargo Categorias del paquete= concatenacion de categorias de las actividades que lo integran
            cargarTablaCategoria(paqueteSeleccionado);
            }
        } else {
            // Si no se seleccionó un departamento válido, puedes limpiar cmbActividades y cmbSalidas
            cmbActividades.removeAllItems();
        }
    }
        
        
        
    }//GEN-LAST:event_cmbPaquetesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<String> cmbActividades;
    private javax.swing.JComboBox<String> cmbPaquetes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaCategorias;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtValidez;
    // End of variables declaration//GEN-END:variables

     private void cargarTablaCategoria(String paquete) {
        //definir el modelo
        DefaultTableModel tablaCategoria = new DefaultTableModel(){
            //que filas y columnas no sean editables
            @Override
            public boolean isCellEditable(int row, int column){
                    return false;
            };
        };
        
        //establecemos los nombres de las columnas
        String titulos[] = {"Categoria"};
        
        tablaCategoria.setColumnIdentifiers(titulos);
        
        //carga de los datos desde la BD
        ArrayList<String> listaCategoriasPaquete = control.traerCategoriasPaquete(paquete);
        
        //recorrer la lista y mostrar cada elemento en la tabla
        if (listaCategoriasPaquete != null){
            for (String c : listaCategoriasPaquete){
                Object[] objeto = {c};
                tablaCategoria.addRow(objeto);   
  
            }
        }
        
        tablaCategorias.setModel(tablaCategoria);
        
        
    }



}
