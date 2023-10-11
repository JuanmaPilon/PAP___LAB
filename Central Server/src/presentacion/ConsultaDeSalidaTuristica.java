/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

import java.util.ArrayList;
import java.util.List;
import logica.Controlador;
import logica.DTSalidaTuristica;

public class ConsultaDeSalidaTuristica extends javax.swing.JInternalFrame {

    Controlador control = Controlador.getInstance();
    
    public ConsultaDeSalidaTuristica() {
        initComponents();
    }

    public ConsultaDeSalidaTuristica(String nombreSalida) {
        initComponents();
        //aca tengo la actividad
        DTSalidaTuristica salida = control.traerDTSalidaTuristica(nombreSalida);
        String departamentoSalida = control.traerDepartamentoSalida(salida.getNombreActividad());
        
        cmbDepartamentos.removeAllItems();
        cmbDepartamentos.addItem(departamentoSalida);
        //cmbDepartamentos.setSelectedItem(departamentoSalida);
        
        cmbActividades.removeAllItems();
        cmbActividades.addItem(salida.getNombreActividad());
        //cmbActividades.setSelectedItem(salida.getNombreActividad());
        
        cmbSalidas.removeAllItems();
        cmbSalidas.addItem(nombreSalida);
        //cmbSalidas.setSelectedItem(nombreSalida);
        
        //para qeu no quede en loop y no se pueda modificar esta entrada
        cmbSalidas.setEnabled(false);
        cmbActividades.setEnabled(false);
        cmbDepartamentos.setEnabled(false);
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbActividades = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cmbDepartamentos = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbSalidas = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        sprFecha = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        txtMaxTur = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtLugar = new javax.swing.JTextField();
        btnCerrar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Consulta de Salida Turistica");
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

        cmbActividades.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbActividadesItemStateChanged(evt);
            }
        });

        jLabel8.setText("Actividades Turisticas");

        cmbDepartamentos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDepartamentosItemStateChanged(evt);
            }
        });

        jLabel3.setText("Departamento");

        jLabel4.setText("Nombre Salida");

        cmbSalidas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSalidasItemStateChanged(evt);
            }
        });

        jLabel7.setText("Lugar");

        sprFecha.setModel(new javax.swing.SpinnerDateModel());
        sprFecha.setEnabled(false);

        jLabel5.setText("Max. Cant. de Turistas");

        txtMaxTur.setEditable(false);

        jLabel6.setText("Fecha y Hora");

        txtLugar.setEditable(false);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(sprFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 151, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(txtLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cmbSalidas, 0, 281, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmbDepartamentos, 0, 281, Short.MAX_VALUE)
                                            .addComponent(cmbActividades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtMaxTur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(66, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(btnCerrar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbActividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(sprFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaxTur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(btnCerrar)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbActividadesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbActividadesItemStateChanged
      cmbSalidas.removeAllItems();
    
    // Verifica si se seleccionó una actividad
    if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        String actividadSeleccionada = (String) cmbActividades.getSelectedItem();
        
        // Verifica si la actividad seleccionada no es nula ni está vacía
        if (actividadSeleccionada != null && !actividadSeleccionada.isEmpty()) {
            ArrayList<String> salidas = control.listaSalActividadTuristica(actividadSeleccionada);
            
            // Verifica si se encontraron salidas
            if (salidas != null && !salidas.isEmpty()) {
                for (String salida : salidas) {
                    cmbSalidas.addItem(salida);
                }
            }
        }
    }
    }//GEN-LAST:event_cmbActividadesItemStateChanged

    private void cmbDepartamentosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDepartamentosItemStateChanged
   // Verifica si se seleccionó un departamento
    if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        String departamentoSeleccionado = (String) cmbDepartamentos.getSelectedItem();
        
        // Verifica si el departamento seleccionado no es nulo ni está vacío
        if (departamentoSeleccionado != null && !departamentoSeleccionado.isEmpty()) {
            List<String> actividades = control.listaActividadesTuristicas(departamentoSeleccionado);

            cmbActividades.removeAllItems();
            cmbSalidas.removeAllItems();

            // Verifica si se encontraron actividades
            if (actividades != null && !actividades.isEmpty()) {
                for (String actividad : actividades) {
                    cmbActividades.addItem(actividad);
                }
            }
        } else {
            // Si no se seleccionó un departamento válido, puedes limpiar cmbActividades y cmbSalidas
            cmbActividades.removeAllItems();
            cmbSalidas.removeAllItems();
        }
    }
     
    }//GEN-LAST:event_cmbDepartamentosItemStateChanged

    private void cmbSalidasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSalidasItemStateChanged
        
            String salida = (String) cmbSalidas.getSelectedItem();
    
    // Verifica si la salida seleccionada no es nula ni está vacía
    if (salida != null && !salida.isEmpty()) {
        DTSalidaTuristica dtsalida = control.traerDTSalidaTuristica(salida);
        if (dtsalida != null) {
            txtLugar.setText(dtsalida.getLugar());
            sprFecha.setValue(dtsalida.getfSalida());
            txtMaxTur.setText(String.valueOf(dtsalida.getCantMax()));
        }
    } else {
            txtLugar.setText("");
            txtMaxTur.setText("");
    }

    }//GEN-LAST:event_cmbSalidasItemStateChanged

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        ArrayList<String> nicks = control.listaDeptos();
        for (String nick : nicks) {
            cmbDepartamentos.addItem(nick);
        }
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JComboBox<String> cmbActividades;
    private javax.swing.JComboBox<String> cmbDepartamentos;
    private javax.swing.JComboBox<String> cmbSalidas;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSpinner sprFecha;
    private javax.swing.JTextField txtLugar;
    private javax.swing.JTextField txtMaxTur;
    // End of variables declaration//GEN-END:variables
}
