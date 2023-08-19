package vistas;
import estructuras.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Puntuaciones extends javax.swing.JFrame {

    private ListaJugador listaJugadores;
    private ListaPuntuaciones listaPuntuaciones;
    
    DefaultTableModel modeloListado;
    String[] info = new String[8];
    
    public Puntuaciones(ListaJugador listaJugadores, 
            ListaPuntuaciones listaPuntuaciones) 
    {
        this.listaJugadores = listaJugadores;
        this.listaPuntuaciones = listaPuntuaciones;
        initComponents();
        
        modeloListado = new DefaultTableModel();
        modeloListado.addColumn("Nombre");
        modeloListado.addColumn("Puntuación");
        this.jTable1.setModel(modeloListado);
        
        mostrarPuntuaciones();
    }

    
    public void mostrarPuntuaciones()
    {
        listaPuntuaciones.mostrarPuntuaciones();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblHeaderPuntuacion = new javax.swing.JLabel();
        bttn_volver = new javax.swing.JButton();

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, 550));
        jPanel1.add(lblHeaderPuntuacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 740));

        bttn_volver.setBackground(new java.awt.Color(0, 153, 153));
        bttn_volver.setForeground(new java.awt.Color(255, 255, 255));
        bttn_volver.setText("Volver al menú principal");
        bttn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_volverActionPerformed(evt);
            }
        });
        jPanel1.add(bttn_volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 650, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_volverActionPerformed
        PantallaInicio volvermenu = new PantallaInicio(listaJugadores, listaPuntuaciones);
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
