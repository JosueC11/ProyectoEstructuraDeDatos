package vistas;

import estructuras.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class PantallaInicio extends javax.swing.JFrame {
    
    private ListaJugador listaJugadores;
    private ListaPuntuaciones listaPuntuaciones;
    
    public PantallaInicio(ListaJugador listaJugadores, 
            ListaPuntuaciones listaPuntuaciones) 
    {
        this.listaJugadores = listaJugadores;
        this.listaPuntuaciones = listaPuntuaciones;
        initComponents();
        activarCancionInicio();
        activarImagenes(listaJugadores);
        
        Jugador jugador_predeterminado = new Jugador("Jugador1", 
                                                    "M", true);
        listaJugadores.agregarJugador(jugador_predeterminado);
        
        // Setear posición a la pantalla
        
        setLocationRelativeTo(null);
        setResizable(false);   
        
        grupoGenero.add(f);
        grupoGenero.add(m);

        Jugador ultimoJugador = listaJugadores.obtenerUltimoJugadorSesion();

        txtNombre.setText(ultimoJugador.getNombre());
        if (ultimoJugador.getGenero() != null)
        {
            if (ultimoJugador.getGenero().equalsIgnoreCase("M")) 
            {
                m.setSelected(true);
                f.setSelected(false);
            } 
            else if (ultimoJugador.getGenero().equalsIgnoreCase("F")) 
            {
                m.setSelected(false);
                f.setSelected(true);
            }
        }
    }

    public final void activarCancionInicio() 
    {
        Reproductor_Musica reproductor = Reproductor_Musica.getInstance();
        if (!reproductor.reproduciendo) 
        {
            Thread hiloMusica = new Thread(reproductor);
            hiloMusica.start();
        }
    }
    
    public final void activarImagenes(ListaJugador listaJugadores){

        // Utilizamos el parámetro listaJugadores para obtener el último jugador
        
        Icon header = new ImageIcon(new ImageIcon(getClass()
        .getResource("/imagenesJuego/overcookedHeaderHome.png")).getImage()
        .getScaledInstance(lblImageHeader.getWidth(), 
        lblImageHeader.getHeight(), 0));
       
        // Se le setea el Icon a el label
        lblImageHeader.setIcon(header);
        
        Jugador ultimoJugador = listaJugadores.obtenerUltimoJugadorSesion();

        if (ultimoJugador == null || ultimoJugador.getIdentificado() == null) 
        {
            Icon user = new ImageIcon(new ImageIcon(getClass()
            .getResource("/imagenesJuego/user.png")).getImage()
            .getScaledInstance(lblImageUser.getWidth(), 
            lblImageUser.getHeight(), 0));

            lblImageUser.setIcon(user);
            
        } else {
            actualizarImagenes();
        }  
    }
    
    public void actualizarImagenes(){
        
        Jugador ultimoJugador = listaJugadores.obtenerUltimoJugadorSesion();
        
        String genero = ultimoJugador.getGenero();

        if(genero != null && genero.equalsIgnoreCase("M")){
            
           Icon manPlayer = new ImageIcon(new ImageIcon
            (getClass().getResource("/imagenesJuego/manPlayer.png")).getImage()
            .getScaledInstance(lblImageUser.getWidth(), 
            lblImageUser.getHeight(), 0)); 
           
           lblImageUser.setIcon(manPlayer);  
           
        }else if(genero != null && genero.equalsIgnoreCase("F")){
            
            Icon womanPlayer = new ImageIcon(new ImageIcon
            (getClass().getResource("/imagenesJuego/womanPlayer.png")).getImage()
            .getScaledInstance(lblImageUser.getWidth(), 
            lblImageUser.getHeight(), 0));
            
            lblImageUser.setIcon(womanPlayer);  
            
        }  
    }
    
    public void iniciarSesion(){
        
        if(!txtNombre.getText().isEmpty()){
            
            if(m.isSelected() || f.isSelected()){
                
                if (m.isSelected()) {
                    Jugador jugadorM = new Jugador(txtNombre.getText(), 
                            "M", true);
                    listaJugadores.agregarJugador(jugadorM);
                } else {
                    Jugador jugadorF = new Jugador(txtNombre.getText(), 
                            "F", true);
                    listaJugadores.agregarJugador(jugadorF);
                }

                JOptionPane.showMessageDialog(null, 
                        "Guardado Correctamente");
                
                actualizarImagenes();
                
            }else{              
                JOptionPane.showMessageDialog
                            (null,"Debe llenar el campo de "
                            + "genero para continuar");               
            }
        }
        else
        {           
            JOptionPane.showMessageDialog
            (null,"Debe llenar el campo de nombre "
                            + " para continuar");       
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

        grupoGenero = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblImageUser = new javax.swing.JLabel();
        btnJugar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        m = new javax.swing.JRadioButton();
        f = new javax.swing.JRadioButton();
        btnGuardar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        lblImageHeader = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImageUser.setBackground(new java.awt.Color(255, 153, 153));
        lblImageUser.setForeground(new java.awt.Color(255, 153, 153));
        jPanel1.add(lblImageUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 110, 90));

        btnJugar.setBackground(new java.awt.Color(0, 153, 153));
        btnJugar.setText("Jugar");
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });
        jPanel1.add(btnJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 600, 150, -1));

        btnSalir.setBackground(new java.awt.Color(255, 0, 51));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 610, 150, -1));

        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 50, 30));

        m.setText("M");
        jPanel1.add(m, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 600, -1, -1));

        f.setText("F");
        f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fActionPerformed(evt);
            }
        });
        jPanel1.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 600, -1, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 600, -1, -1));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 540, 230, 30));
        jPanel1.add(lblImageHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 660));

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

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        JOptionPane.showMessageDialog(null,"Muchas gracias "
                + "por jugar!","Saliendo....",
                JOptionPane.INFORMATION_MESSAGE);
        
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugarActionPerformed
        
        Jugador ultimoJugador = listaJugadores.obtenerUltimoJugadorSesion();

        if (ultimoJugador.getIdentificado() == null) 
        {
            JOptionPane.showMessageDialog(null, 
                    "Debe Identificarse antes de jugar");
        } 
        else 
        {
            Juego mostrar_juego = new Juego(listaJugadores, listaPuntuaciones); 
            mostrar_juego.setVisible(true);
            mostrar_juego.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_btnJugarActionPerformed

    private void fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        iniciarSesion();
        activarImagenes(listaJugadores);      
    }//GEN-LAST:event_btnGuardarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnJugar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JRadioButton f;
    private javax.swing.ButtonGroup grupoGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblImageHeader;
    private javax.swing.JLabel lblImageUser;
    private javax.swing.JRadioButton m;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}