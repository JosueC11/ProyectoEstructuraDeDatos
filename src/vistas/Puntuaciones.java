package vistas;
import estructuras.*;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class Puntuaciones extends javax.swing.JFrame {

    private ListaJugador listaJugadores;
    private ListaPuntuaciones listaPuntuaciones;

    DefaultTableModel modeloListado;
    String[] info = new String[8];

    public Puntuaciones(ListaJugador listaJugadores, ListaPuntuaciones 
            listaPuntuaciones) {
        this.listaJugadores = listaJugadores;
        this.listaPuntuaciones = listaPuntuaciones;
        initComponents();

        // Crear un modelo de tabla para la visualización de puntuaciones
        modeloListado = new DefaultTableModel();
        modeloListado.addColumn("Nombre");
        modeloListado.addColumn("Puntuación");
        this.jTable1.setModel(modeloListado);

        // Mostrar las puntuaciones en la tabla
        mostrarPuntuacionesEnTabla();
    }

    // Método para actualizar la tabla con las puntuaciones almacenadas
    public void mostrarPuntuacionesEnTabla() {
        // Limpiar el contenido actual de la tabla
        modeloListado.setRowCount(0);

        // Obtener las puntuaciones como una matriz de objetos
        Object[][] puntuacionesMatriz = listaPuntuaciones.
                obtenerPuntuacionesComoMatriz();

        // Agregar cada fila de puntuación a la tabla
        for (Object[] puntuacion : puntuacionesMatriz) {
            modeloListado.addRow(puntuacion);
        }
    }
    
    // Método para cargar una imagen y asignarla a un JLabel
    public final void activarImagenes() {
        // Cargar la imagen desde un recurso y escalarla al tamaño del JLabel
        Icon header = new ImageIcon(new ImageIcon(getClass().
                getResource("/imagenesJuego/39030_Overcooked.jpg"))
            .getImage().getScaledInstance(lblHeaderPuntuacion.getWidth(),
                    lblHeaderPuntuacion.getHeight(), 0));

        // Asignar el icono cargado al JLabel
        lblHeaderPuntuacion.setIcon(header);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bttn_volver = new javax.swing.JButton();
        lblHeaderPuntuacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, 550));

        bttn_volver.setBackground(new java.awt.Color(0, 153, 153));
        bttn_volver.setForeground(new java.awt.Color(255, 255, 255));
        bttn_volver.setText("Volver al menú principal");
        bttn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_volverActionPerformed(evt);
            }
        });
        jPanel1.add(bttn_volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 770, -1, -1));

        lblHeaderPuntuacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesJuego/39030_Overcooked.jpg"))); // NOI18N
        jPanel1.add(lblHeaderPuntuacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 840));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_volverActionPerformed
        PantallaInicio volvermenu = new PantallaInicio(listaJugadores,
                listaPuntuaciones);
        volvermenu.setVisible(true);
        volvermenu.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_bttn_volverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttn_volver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblHeaderPuntuacion;
    // End of variables declaration//GEN-END:variables
}
