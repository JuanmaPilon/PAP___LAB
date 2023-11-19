package presentacion;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import logica.DTActividad;
import logica.DTSalidaTuristica;
import logica.IControlador;

public class VerTopDiez extends javax.swing.JInternalFrame {

    private IControlador control;
    private ArrayList<DTActividad> actividades;
    private ArrayList<DTSalidaTuristica> salidas;
    private List<Object> listaTop10;
    DefaultTableModel model;

    public VerTopDiez(IControlador icu) {
        control = icu;
        initComponents();
        model = new DefaultTableModel();
        tablaVisitas.setModel(model);
        // Agregar las columnas en el constructor o en initComponents
        model.addColumn("#");
        model.addColumn("Actividad / Salida Turistica");
        model.addColumn("Proveedor Actividad");
        model.addColumn("Tipo");
        model.addColumn("Cantidad de visitas");
        cargarActividadesYSalidas();
        llenarTabla();
    }

    private void cargarActividadesYSalidas() {
        actividades = control.listaActividadesConVisitas();
        salidas = control.listaSalidasConVisitas();
        listaTop10 = control.obtenerTopNMasVisitadas(salidas, actividades);

        // Imprimir información de la listaTop10
        System.out.println("Tamaño de listaTop10: " + listaTop10.size());
        for (Object obj : listaTop10) {
            System.out.println(obj.toString());
        }
    }

    private void llenarTabla() {
        // Limpiar el modelo antes de agregar nuevas filas
        model.setRowCount(0);

        int index = 1; // Variable para numerar las filas
        for (Object obj : listaTop10) {
            if (obj instanceof DTSalidaTuristica) {
                DTSalidaTuristica salida = (DTSalidaTuristica) obj;
                DTActividad act = control.traerDTActividad(salida.getNombreActividad());
                model.addRow(new Object[]{index++, salida.getNombre(), act.getNombreProveedor(), "Salida", salida.getVisitas()});
            } else if (obj instanceof DTActividad) {
                DTActividad actividad = (DTActividad) obj;
                model.addRow(new Object[]{index++, actividad.getNombre(), actividad.getNombreProveedor(), "Actividad", actividad.getVisitas()});
            }
        }

        // Refrescar la tabla
        model.fireTableDataChanged();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVisitas = new javax.swing.JTable(model);

        jScrollPane1.setViewportView(tablaVisitas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaVisitas;
    // End of variables declaration//GEN-END:variables
}
