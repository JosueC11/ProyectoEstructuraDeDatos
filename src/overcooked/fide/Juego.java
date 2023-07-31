
package overcooked.fide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;


public class Juego extends javax.swing.JFrame {

    public static ArrayList<Puntuacion> arrPuntuaciones = new ArrayList<>();
    
    Jugador jugador = Jugador.getInstance();
    
    ListaOrdenes lista = new ListaOrdenes();

    private Timer tiempo;
    
    private int centesima = 99, segundo = 19;
    
    private ActionListener accion = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            
            centesima --;
            lista.llenarLista();
            lista.agregarOrdenCola();
            generarOrdenInicio();
            if(centesima == 0){
                
                segundo --;
                centesima = 99;
            }
            if(segundo == -1){
                
                lista.agregarOrdenCola();
                generarOrden();
                centesima = 99;
                segundo = 19;     
            }  
        }   
    };
    
    public Juego(){
        
        initComponents();
        
        nombre_jugador.setText(jugador.getNombre());
        
        tiempo = new Timer(1,accion);
        tiempo.start();  
    }
    
    public void generarOrdenInicio(){
        
        int inicio = 0;
        if(inicio == 0){
            
            Orden orden = lista.devolver();
            
            if (TxtPaneOrden1.getText().isEmpty() && 
                TxtPaneOrden2.getText().isEmpty() && 
                TxtPaneOrden3.getText().isEmpty()){

                TxtPaneOrden1.setText(orden.getNombre());
            }
            inicio ++;        
        }
    }
    
    public void generarOrden(){
        
        if (segundo == -1) {

            Orden orden = lista.devolver();

            if (TxtPaneOrden1.getText().isEmpty() && 
                TxtPaneOrden2.getText().isEmpty() && 
                TxtPaneOrden3.getText().isEmpty()) {

                TxtPaneOrden1.setText(orden.getNombre());
                

            } else if (!TxtPaneOrden1.getText().isEmpty() && 
                        TxtPaneOrden2.getText().isEmpty() && 
                        TxtPaneOrden3.getText().isEmpty()){

                TxtPaneOrden2.setText(orden.getNombre());

            } else if (!TxtPaneOrden1.getText().isEmpty() && 
                       !TxtPaneOrden2.getText().isEmpty() && 
                       TxtPaneOrden3.getText().isEmpty()){

                TxtPaneOrden3.setText(orden.getNombre());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        salirMenuPrincipal_Juego = new javax.swing.JButton();
        campo_nombre = new javax.swing.JLabel();
        nombre_jugador = new javax.swing.JTextField();
        spiner_puntuacion = new javax.swing.JSpinner();
        txt_puntuacion = new javax.swing.JLabel();
        enviar_puntuacion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtPaneOrden3 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtPaneOrden2 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        TxtPaneOrden1 = new javax.swing.JTextPane();
        btnTerminarOrden = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Juego");

        salirMenuPrincipal_Juego.setText("Salir al menú");
        salirMenuPrincipal_Juego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirMenuPrincipal_JuegoActionPerformed(evt);
            }
        });

        campo_nombre.setText("Jugador");

        nombre_jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_jugadorActionPerformed(evt);
            }
        });

        txt_puntuacion.setText("Puntuacion");

        enviar_puntuacion.setText("Enviar");
        enviar_puntuacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviar_puntuacionActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(TxtPaneOrden3);

        jScrollPane2.setViewportView(TxtPaneOrden2);

        jScrollPane3.setViewportView(TxtPaneOrden1);

        btnTerminarOrden.setText("Terminar Orden");
        btnTerminarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarOrdenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(salirMenuPrincipal_Juego)
                        .addGap(111, 111, 111))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(enviar_puntuacion)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txt_puntuacion)
                                            .addGap(18, 18, 18)
                                            .addComponent(spiner_puntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campo_nombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nombre_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(317, 317, 317)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTerminarOrden, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(campo_nombre)
                                        .addComponent(nombre_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(19, 19, 19)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(spiner_puntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_puntuacion))
                                    .addGap(18, 18, 18)
                                    .addComponent(enviar_puntuacion)
                                    .addGap(53, 53, 53))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addComponent(btnTerminarOrden)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                .addComponent(salirMenuPrincipal_Juego)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirMenuPrincipal_JuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirMenuPrincipal_JuegoActionPerformed
        PantallaInicio volvermenu = new PantallaInicio(); 
        volvermenu.setVisible(true); 
        volvermenu.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_salirMenuPrincipal_JuegoActionPerformed

    private void nombre_jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_jugadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_jugadorActionPerformed

    private void enviar_puntuacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviar_puntuacionActionPerformed

        int puntuacion = (int) spiner_puntuacion.getValue();

        Puntuacion nuevaPuntuacion = new Puntuacion(puntuacion, jugador);

        arrPuntuaciones.add(nuevaPuntuacion);
        
        spiner_puntuacion.setValue(0);
    }//GEN-LAST:event_enviar_puntuacionActionPerformed

    private void btnTerminarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarOrdenActionPerformed
        TxtPaneOrden1.setText("");
        
        if(TxtPaneOrden1.getText().isEmpty() && 
          !TxtPaneOrden2.getText().isEmpty() && 
           TxtPaneOrden3.getText().isEmpty()){
            
            TxtPaneOrden1.setText(TxtPaneOrden2.getText());
            TxtPaneOrden2.setText("");
            
        }else if(TxtPaneOrden1.getText().isEmpty() && 
                !TxtPaneOrden2.getText().isEmpty() && 
                !TxtPaneOrden3.getText().isEmpty()){
            
            TxtPaneOrden1.setText(TxtPaneOrden2.getText());
            TxtPaneOrden2.setText(TxtPaneOrden3.getText());
            TxtPaneOrden3.setText("");
        }
    }//GEN-LAST:event_btnTerminarOrdenActionPerformed

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
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Juego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane TxtPaneOrden1;
    private javax.swing.JTextPane TxtPaneOrden2;
    private javax.swing.JTextPane TxtPaneOrden3;
    private javax.swing.JButton btnTerminarOrden;
    private javax.swing.JLabel campo_nombre;
    private javax.swing.JButton enviar_puntuacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField nombre_jugador;
    private javax.swing.JButton salirMenuPrincipal_Juego;
    private javax.swing.JSpinner spiner_puntuacion;
    private javax.swing.JLabel txt_puntuacion;
    // End of variables declaration//GEN-END:variables
}


