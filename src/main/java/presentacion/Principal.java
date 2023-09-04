package presentacion;

//import logica.Fabrica;
//import logica.IControlador;
//import logica.Usuario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author natil
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
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

        contenedorPrincipal = new javax.swing.JDesktopPane();
        barraMenuPrincipal = new javax.swing.JMenuBar();
        menuAltas = new javax.swing.JMenu();
        Usuario = new javax.swing.JMenu();
        altaUsuarioTurista = new javax.swing.JMenuItem();
        altaUsuarioProveedor = new javax.swing.JMenuItem();
        altaActividadTuristica = new javax.swing.JMenuItem();
        altaSalidaTuristica = new javax.swing.JMenuItem();
        altaInscripcionSalidaTuristica = new javax.swing.JMenuItem();
        altaCrearPaquete = new javax.swing.JMenuItem();
        altaAgregarActividadPaquete = new javax.swing.JMenuItem();
        altaDepartamento = new javax.swing.JMenuItem();
        menuConsultas = new javax.swing.JMenu();
        consUsuario = new javax.swing.JMenu();
        consTurista = new javax.swing.JMenuItem();
        consProveedor = new javax.swing.JMenuItem();
        consActividadTuristica = new javax.swing.JMenuItem();
        consSalidaTuristica = new javax.swing.JMenuItem();
        consPaquete = new javax.swing.JMenuItem();
        menuModificar = new javax.swing.JMenu();
        ModificarUsuario = new javax.swing.JMenu();
        modTurista = new javax.swing.JMenuItem();
        modProveedor = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" turismo.uy Grupo 4");

        contenedorPrincipal.setName(""); // NOI18N

        javax.swing.GroupLayout contenedorPrincipalLayout = new javax.swing.GroupLayout(contenedorPrincipal);
        contenedorPrincipal.setLayout(contenedorPrincipalLayout);
        contenedorPrincipalLayout.setHorizontalGroup(
            contenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 898, Short.MAX_VALUE)
        );
        contenedorPrincipalLayout.setVerticalGroup(
            contenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );

        menuAltas.setText("Altas");

        Usuario.setText("Usuario");

        altaUsuarioTurista.setText("Turista");
        altaUsuarioTurista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altaUsuarioTuristaActionPerformed(evt);
            }
        });
        Usuario.add(altaUsuarioTurista);

        altaUsuarioProveedor.setText("Proveedor");
        altaUsuarioProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altaUsuarioProveedorActionPerformed(evt);
            }
        });
        Usuario.add(altaUsuarioProveedor);

        menuAltas.add(Usuario);

        altaActividadTuristica.setText("Actividad Turistica");
        altaActividadTuristica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altaActividadTuristicaActionPerformed(evt);
            }
        });
        menuAltas.add(altaActividadTuristica);

        altaSalidaTuristica.setText("Salida Turistica");
        menuAltas.add(altaSalidaTuristica);

        altaInscripcionSalidaTuristica.setText("Inscripcion Salida Turistica");
        menuAltas.add(altaInscripcionSalidaTuristica);

        altaCrearPaquete.setText("Crear Paquete");
        altaCrearPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altaCrearPaqueteActionPerformed(evt);
            }
        });
        menuAltas.add(altaCrearPaquete);

        altaAgregarActividadPaquete.setText("Agregar Actividad Paquete");
        altaAgregarActividadPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altaAgregarActividadPaqueteActionPerformed(evt);
            }
        });
        menuAltas.add(altaAgregarActividadPaquete);

        altaDepartamento.setText("Departamento");
        altaDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altaDepartamentoActionPerformed(evt);
            }
        });
        menuAltas.add(altaDepartamento);

        barraMenuPrincipal.add(menuAltas);

        menuConsultas.setText("Consultas");

        consUsuario.setText("Usuario");

        consTurista.setText("Turista");
        consTurista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consTuristaActionPerformed(evt);
            }
        });
        consUsuario.add(consTurista);

        consProveedor.setText("Proveedor");
        consProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consProveedorActionPerformed(evt);
            }
        });
        consUsuario.add(consProveedor);

        menuConsultas.add(consUsuario);

        consActividadTuristica.setText("Actividad Turistica");
        consActividadTuristica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consActividadTuristicaActionPerformed(evt);
            }
        });
        menuConsultas.add(consActividadTuristica);

        consSalidaTuristica.setText("Salida Tursitica");
        menuConsultas.add(consSalidaTuristica);

        consPaquete.setText("Paquete");
        consPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consPaqueteActionPerformed(evt);
            }
        });
        menuConsultas.add(consPaquete);

        barraMenuPrincipal.add(menuConsultas);

        menuModificar.setText("Modificar");

        ModificarUsuario.setText("Usuario");

        modTurista.setText("Turista");
        modTurista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modTuristaActionPerformed(evt);
            }
        });
        ModificarUsuario.add(modTurista);

        modProveedor.setText("Proveedor");
        modProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modProveedorActionPerformed(evt);
            }
        });
        ModificarUsuario.add(modProveedor);

        menuModificar.add(ModificarUsuario);

        barraMenuPrincipal.add(menuModificar);

        setJMenuBar(barraMenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedorPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedorPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void altaCrearPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaCrearPaqueteActionPerformed
        CrearPaqueteActividadTuristica verCrearPaquete = new CrearPaqueteActividadTuristica();
        contenedorPrincipal.add(verCrearPaquete);
        verCrearPaquete.show();
    }//GEN-LAST:event_altaCrearPaqueteActionPerformed

    private void altaUsuarioTuristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaUsuarioTuristaActionPerformed
        AltaUsuarioTurista verAltaUsuarioTurista = new AltaUsuarioTurista();
        contenedorPrincipal.add(verAltaUsuarioTurista);
        verAltaUsuarioTurista.show();
    }//GEN-LAST:event_altaUsuarioTuristaActionPerformed

    private void altaUsuarioProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaUsuarioProveedorActionPerformed
        AltaUsuarioProveedor verAltaUsuarioProveedor = new AltaUsuarioProveedor();
        contenedorPrincipal.add(verAltaUsuarioProveedor);
        verAltaUsuarioProveedor.show();
    }//GEN-LAST:event_altaUsuarioProveedorActionPerformed

    private void altaActividadTuristicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaActividadTuristicaActionPerformed
        AltaActividadTuristica verAltaActividadTuristica = new AltaActividadTuristica();
        contenedorPrincipal.add(verAltaActividadTuristica);
        verAltaActividadTuristica.show();
    }//GEN-LAST:event_altaActividadTuristicaActionPerformed

    private void consPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consPaqueteActionPerformed
        ConsultaPaqueteActividadesTuristicas verConsultaPaquete = new ConsultaPaqueteActividadesTuristicas();
        contenedorPrincipal.add(verConsultaPaquete);
        verConsultaPaquete.show();
    }//GEN-LAST:event_consPaqueteActionPerformed

    private void altaDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaDepartamentoActionPerformed
        AltaDepartamento verAltaDepartamento = new AltaDepartamento();
        contenedorPrincipal.add(verAltaDepartamento);
        verAltaDepartamento.show();
    }//GEN-LAST:event_altaDepartamentoActionPerformed

    private void modTuristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modTuristaActionPerformed
        ModificarDatosTurista verModificarTurista = new ModificarDatosTurista();
        contenedorPrincipal.add(verModificarTurista);
        verModificarTurista.show();
    }//GEN-LAST:event_modTuristaActionPerformed

    private void modProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modProveedorActionPerformed
        ModificarDatosProveedor verModificarProveedor = new ModificarDatosProveedor();
        contenedorPrincipal.add(verModificarProveedor);
        verModificarProveedor.show();
    }//GEN-LAST:event_modProveedorActionPerformed

    private void consActividadTuristicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consActividadTuristicaActionPerformed
        ConsultaActividadTuristica verConsultaActividadTuristica = new ConsultaActividadTuristica();
        contenedorPrincipal.add(verConsultaActividadTuristica);
        verConsultaActividadTuristica.show();
    }//GEN-LAST:event_consActividadTuristicaActionPerformed

    private void altaAgregarActividadPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaAgregarActividadPaqueteActionPerformed
        AgregarActividadTuristicaPaquete verAgregarActividadPaquete = new AgregarActividadTuristicaPaquete();
        contenedorPrincipal.add(verAgregarActividadPaquete);
        verAgregarActividadPaquete.show();
    }//GEN-LAST:event_altaAgregarActividadPaqueteActionPerformed

    private void consTuristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consTuristaActionPerformed
        ConsultaUsuarioTurista verConsultarUsuarioTurista = new ConsultaUsuarioTurista();
        contenedorPrincipal.add(verConsultarUsuarioTurista);
        verConsultarUsuarioTurista.show();
    }//GEN-LAST:event_consTuristaActionPerformed

    private void consProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consProveedorActionPerformed
        ConsultaUsuarioProveedor verConsultarUsuarioProveedor = new ConsultaUsuarioProveedor();
        contenedorPrincipal.add(verConsultarUsuarioProveedor);
        verConsultarUsuarioProveedor.show();
    }//GEN-LAST:event_consProveedorActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
        
        /* ESTO ES UNA PRUEBA y funciona! PARA VER Q ANDE LA FABRICA; CONTROLADOR; ALTAUSUARUIO Y USUARIO.      
        LOS IMPORT QUEDARON COMENTADOS TAMBIEN
        
        //creo un obj Usuario
        Usuario u = new Usuario("natalia", "natalia", "natalia", "natalia", null);
        System.out.println(u.getApellido());
               
        // Inicialización de la Fabrica
        Fabrica fabrica = Fabrica.getInstance();
        IControlador IC = fabrica.getIControlador();
 
        IC.AltaDeUsuario();
        
        */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ModificarUsuario;
    private javax.swing.JMenu Usuario;
    private javax.swing.JMenuItem altaActividadTuristica;
    private javax.swing.JMenuItem altaAgregarActividadPaquete;
    private javax.swing.JMenuItem altaCrearPaquete;
    private javax.swing.JMenuItem altaDepartamento;
    private javax.swing.JMenuItem altaInscripcionSalidaTuristica;
    private javax.swing.JMenuItem altaSalidaTuristica;
    private javax.swing.JMenuItem altaUsuarioProveedor;
    private javax.swing.JMenuItem altaUsuarioTurista;
    private javax.swing.JMenuBar barraMenuPrincipal;
    private javax.swing.JMenuItem consActividadTuristica;
    private javax.swing.JMenuItem consPaquete;
    private javax.swing.JMenuItem consProveedor;
    private javax.swing.JMenuItem consSalidaTuristica;
    private javax.swing.JMenuItem consTurista;
    private javax.swing.JMenu consUsuario;
    private javax.swing.JDesktopPane contenedorPrincipal;
    private javax.swing.JMenu menuAltas;
    private javax.swing.JMenu menuConsultas;
    private javax.swing.JMenu menuModificar;
    private javax.swing.JMenuItem modProveedor;
    private javax.swing.JMenuItem modTurista;
    // End of variables declaration//GEN-END:variables
}
