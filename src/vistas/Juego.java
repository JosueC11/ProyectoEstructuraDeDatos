package vistas;

import estructuras.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public final class Juego extends javax.swing.JFrame {
    
    ListaOrdenes lista = new ListaOrdenes(); 
    
    ListaCircular  listaCircular = new ListaCircular();
    
    private Timer tiempoOrdenes;
    private Timer tiempoJuego;
    
    private int centesimaOrdenes = 99, segundoOrdenes = 1;
    private int centesimaJuego = 99, segundoJuego = 59, minutoJuego = 4;
    
    private int inicioOrdenes = 0;
    private int puntuacion = 0;

    private ListaJugador listaJugadores;
    private ListaPuntuaciones listaPuntuaciones;
    
    public Juego(ListaJugador listaJugadores, ListaPuntuaciones listaPuntuaciones)
    {
        
        initComponents();
        this.listaJugadores = listaJugadores;
        this.listaPuntuaciones = listaPuntuaciones;
        // Setear posición a la pantalla
        setLocationRelativeTo(null);
        setResizable(false); 
 
        nombre_jugador.setText(listaJugadores.obtenerUltimoJugadorSesion().getNombre());
        
        //carga imagenes del header
        activarImagenes();
        
        //Llenar la lista de Ordenes
        lista.llenarLista();
        
        //agrega una orden a la cola
        lista.agregarOrdenCola();
        
        // imprime los ingredientes
        llenarListaCircular();
        imprimirIngredientes();
        
        //genera una orden en la pantalla al inicio del juego
        generarOrdenInicio();
        
        //setear el delay y la tarea a realizar 
        tiempoOrdenes = new Timer(1,generarOrdenes);
        tiempoOrdenes.start(); 
        
        //setear el delay y la tarea a realizar 
        tiempoJuego = new Timer(1,temporizadorJuego);
        tiempoJuego.start(); 

    }
    
    //Tarea de generar ordenes
    private ActionListener generarOrdenes = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            
            centesimaOrdenes --;
            lista.agregarOrdenCola();
            if(centesimaOrdenes == 0){
                
                segundoOrdenes --;
                centesimaOrdenes = 99;
            }
            if(segundoOrdenes == -1){
                
                lista.agregarOrdenCola();
                generarOrden();
                centesimaOrdenes = 99;
                segundoOrdenes = 1;     
            }  
        }   
    };
    
    //Tarea de temporizador
    private ActionListener temporizadorJuego = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            
            centesimaJuego --;
            
            if(centesimaJuego == -1){
                
                segundoJuego --;
                centesimaJuego = 99;
            }
            if(segundoJuego == -1){
                
                minutoJuego --;
                centesimaJuego = 99;
                segundoJuego = 59;     
            }
            if(minutoJuego == -1){
                
                terminarJuego();             
            } 
            
            //actualiza el label de la pantalla temporizador
            actualizarLabelTemporizador();
        }   
    };
    
    //actualiza el temporizador
    public void actualizarLabelTemporizador(){
        
        String tiempo = (minutoJuego <= 9? "0":"")+minutoJuego+":"+
                (segundoJuego <= 9? "0":"")+segundoJuego+":"+
                (centesimaJuego <= 9? "0":"")+centesimaJuego;
        
        labelTemporizador.setText(tiempo);   
    }
    
    //setea los campos de las ordenes
    public final void activarImagenes(){

        Icon header = new ImageIcon(new ImageIcon(getClass()
        .getResource("/imagenesJuego/overcookedHeaderGame.png")).getImage()
        .getScaledInstance(lblHeader.getWidth(), 
        lblHeader.getHeight(), 0));
        
        lblHeader.setIcon(header);           
    }
    
    //Genera una orden al inicio del juego
    public void generarOrdenInicio(){
        
        if(inicioOrdenes == 0){
            
            Orden orden = lista.devolverDeCola();
            
            Icon ordenIcon = new ImageIcon(new ImageIcon(getClass()
            .getResource("/imagenesOrdenes/"+orden.getNombre()+".png")).getImage()
            .getScaledInstance(labelOrden1.getWidth(), 
            labelOrden1.getHeight(),0));
            
            labelOrden1.setIcon(ordenIcon);
            labelOrden1.setText(orden.getNombre());    
            lblHamburguesaCreando.setText(orden.getNombre());
            
            inicioOrdenes ++;        
        }
    }
    
    //metodo para generar las ordenes cada 20 segundos
    public void generarOrden(){

        Orden orden = lista.devolverDeCola();

        Icon ordenIcon = new ImageIcon(new ImageIcon(getClass()
        .getResource("/imagenesOrdenes/"+orden.getNombre()+".png")).getImage()
        .getScaledInstance(labelOrden1.getWidth(), 
        labelOrden1.getHeight(),0));

        if(labelOrden1.getText().isBlank() && 
           labelOrden2.getText().isBlank() && 
           labelOrden3.getText().isBlank()){

            labelOrden1.setIcon(ordenIcon);
            labelOrden1.setText(orden.getNombre());
            lblHamburguesaCreando.setText(orden.getNombre());

        }else if(!labelOrden1.getText().isBlank()&& 
                labelOrden2.getText().isBlank()&& 
                labelOrden3.getText().isBlank()){
            
            labelOrden2.setIcon(ordenIcon);
            labelOrden2.setText(orden.getNombre());
                    
        }else if(!labelOrden1.getText().isBlank()
                && !labelOrden2.getText().isBlank()
                && labelOrden3.getText().isBlank()){

            labelOrden3.setIcon(ordenIcon);
            labelOrden3.setText(orden.getNombre());
        }
    }
    
    public void llenarListaCircular(){
        
        listaCircular.insertar(new Ingrediente("lechuga"));
        listaCircular.insertar(new Ingrediente("cebolla")); 
        listaCircular.insertar(new Ingrediente("tomate"));
        listaCircular.insertar(new Ingrediente("pan"));
        listaCircular.insertar(new Ingrediente("queso"));
        listaCircular.insertar(new Ingrediente("carne"));
        listaCircular.insertar(new Ingrediente("aguacate"));      
    }
    
    public void imprimirIngredientes(){
        
        int indice = 6;

        while(indice > 0){
            
            String nombre = listaCircular.imprimir();
            
            Icon ingrediente = new ImageIcon(new ImageIcon(getClass()
            .getResource("/imagenesIngredientes/"+nombre+".png")).getImage()
            .getScaledInstance(lblIngre1.getWidth(), 
            lblIngre1.getHeight(),0));
                  
            if(indice == 1){
                lblIngre1.setIcon(ingrediente);
                lblIngre1.setText(nombre);       
            }
            if(indice == 2){
                lblIngre2.setIcon(ingrediente);
                lblIngre2.setText(nombre);              
            }
            if(indice == 3){
                lblIngre3.setIcon(ingrediente);
                lblIngre3.setText(nombre);              
            }
            if(indice == 4){ 
                lblIngre4.setIcon(ingrediente);
                lblIngre4.setText(nombre);              
            }
            if(indice == 5){ 
                lblIngre5.setIcon(ingrediente);
                lblIngre5.setText(nombre);            
            }
            indice--;
        }
    }
    
    public void elegirIngrediente(int numeroLabel){

        switch(numeroLabel){           
            case 1 -> {
                construirOrden(lblIngre1.getText());                  
            }
            case 2 -> {
                construirOrden(lblIngre2.getText());                 
            } 
            case 3 -> {
                construirOrden(lblIngre3.getText());
            } 
            case 4 -> {
                construirOrden(lblIngre4.getText());
            }           
            case 5 -> {
                construirOrden(lblIngre5.getText());
            }
        } 
        imprimirIngredientes();
    }
    
    public void construirOrden(String ingrediente){
        
        Icon iconIngrediente = new ImageIcon(new ImageIcon(getClass()
        .getResource("/imagenesIngredientes/"+ingrediente+".png")).getImage()
        .getScaledInstance(lblPan.getWidth(), 
        lblPan.getHeight(),0));
        
        switch(ingrediente){
            case "pan" -> {
                lblPan.setText(ingrediente);
                lblPan.setIcon(iconIngrediente);
            }
            case "carne" -> {
                lblCarne.setText(ingrediente);
                lblCarne.setIcon(iconIngrediente);
            }
            case "queso" -> {
                lblQueso.setText(ingrediente);
                lblQueso.setIcon(iconIngrediente);
            }
            case "lechuga" -> {
                lblLechuga.setText(ingrediente);
                lblLechuga.setIcon(iconIngrediente);
            }           
            case "aguacate" -> {
                lblAguacate.setText(ingrediente);
                lblAguacate.setIcon(iconIngrediente);
            }    
            case "tomate" -> {
                lblTomate.setText(ingrediente);
                lblTomate.setIcon(iconIngrediente);
            } 
            case "cebolla" -> {
                lblCebolla.setText(ingrediente);
                lblCebolla.setIcon(iconIngrediente);
            } 
        }      
    }
    
    public void terminarOrden(){
      
        revisarOrden();
        
        labelOrden1.setText("");
        labelOrden1.setIcon(null);
        
        if(labelOrden1.getText().isBlank() && 
          !labelOrden2.getText().isBlank()&& 
           labelOrden3.getText().isBlank()){
            
            labelOrden1.setIcon(labelOrden2.getIcon());
            labelOrden2.setIcon(null);
            labelOrden1.setText(labelOrden2.getText());
            labelOrden2.setText("");
            lblHamburguesaCreando.setText(labelOrden1.getText());
  
        }else if(labelOrden1.getText().isBlank() && 
                !labelOrden2.getText().isBlank() && 
                !labelOrden3.getText().isBlank()){
            
            labelOrden1.setIcon(labelOrden2.getIcon());
            labelOrden2.setIcon(labelOrden3.getIcon());
            labelOrden3.setIcon(null);
            labelOrden1.setText(labelOrden2.getText());
            labelOrden2.setText(labelOrden3.getText());
            labelOrden3.setText("");
            lblHamburguesaCreando.setText(labelOrden1.getText());
        }   

        limpiarOrdenCreada();       
    }
    
    public void limpiarOrdenCreada(){
        
        lblPan.setIcon(null);
        lblPan.setText("");
        lblCarne.setIcon(null);
        lblCarne.setText("");
        lblQueso.setIcon(null);
        lblQueso.setText("");
        lblLechuga.setIcon(null);
        lblLechuga.setText("");
        lblAguacate.setIcon(null);
        lblAguacate.setText("");
        lblTomate.setIcon(null);
        lblTomate.setText("");
        lblCebolla.setIcon(null);
        lblCebolla.setText("");    
    }
    
    public void revisarOrden(){
        
        String nombreOrden = labelOrden1.getText();
        
        Orden ordenRevisar = lista.devolverOrden(nombreOrden);
        
        Boolean pan = ordenRevisar.getPan();
        Boolean carne = ordenRevisar.getCarne();
        Boolean queso = ordenRevisar.getQueso();
        Boolean lechuga = ordenRevisar.getLechuga();
        Boolean aguacate = false;
        Boolean tomate= false;
        Boolean cebolla = false;
        
        switch(ordenRevisar.getNombre()){
            case "hamburguesaDeCarne" -> {
                
                if(pan == !lblPan.getText().isEmpty() &&
                   carne == !lblCarne.getText().isEmpty() &&
                   queso != lblQueso.getText().isEmpty() &&
                   lechuga != lblLechuga.getText().isEmpty() &&
                   aguacate != lblAguacate.getText().isEmpty() &&
                   tomate != lblTomate.getText().isEmpty() &&
                   cebolla != lblCebolla.getText().isEmpty()){
                    
                   puntuacion += 5;    
                }
            }   
            case "hamburguesaConQueso" -> {
                
                if(pan == !lblPan.getText().isEmpty() &&
                   carne == !lblCarne.getText().isEmpty() &&
                   queso == !lblQueso.getText().isEmpty() &&
                   lechuga != lblLechuga.getText().isEmpty() &&
                   aguacate != lblAguacate.getText().isEmpty() &&
                   tomate != lblTomate.getText().isEmpty() &&
                   cebolla != lblCebolla.getText().isEmpty()){
                    
                   puntuacion += 10;    
                }
            }   
            case "hamburguesaClasica" -> {
                
                if(pan == !lblPan.getText().isEmpty() &&
                   carne == !lblCarne.getText().isEmpty() &&
                   queso == !lblQueso.getText().isEmpty() &&
                   lechuga == !lblLechuga.getText().isEmpty() &&
                   aguacate != lblAguacate.getText().isEmpty() &&
                   tomate != lblTomate.getText().isEmpty() &&
                   cebolla != lblCebolla.getText().isEmpty()){
                    
                   puntuacion += 15;    
                }    
            }
        }
        
        String StringPuntuacion = String.valueOf(puntuacion);
        lblPuntuacion.setText(StringPuntuacion);
    } 
    
    public void terminarJuego(){
        
        tiempoOrdenes.stop(); 
        tiempoJuego.stop(); 
        
        JOptionPane.showMessageDialog
        (null,"El juego terminó");
        
        PantallaInicio pantallaInicio = new PantallaInicio(listaJugadores, 
                                                        listaPuntuaciones);
        pantallaInicio.setVisible(true);
        this.dispose();
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
        jLabel1 = new javax.swing.JLabel();
        salirMenuPrincipal_Juego = new javax.swing.JButton();
        campo_nombre = new javax.swing.JLabel();
        nombre_jugador = new javax.swing.JTextField();
        btnTerminarOrden = new javax.swing.JButton();
        labelTemporizador = new javax.swing.JLabel();
        labelOrden2 = new javax.swing.JLabel();
        labelOrden3 = new javax.swing.JLabel();
        labelOrden1 = new javax.swing.JLabel();
        lblIngre2 = new javax.swing.JLabel();
        lblIngre3 = new javax.swing.JLabel();
        lblIngre4 = new javax.swing.JLabel();
        lblIngre5 = new javax.swing.JLabel();
        lblIngre1 = new javax.swing.JLabel();
        lblPan = new javax.swing.JLabel();
        lblCarne = new javax.swing.JLabel();
        lblQueso = new javax.swing.JLabel();
        lblAguacate = new javax.swing.JLabel();
        lblLechuga = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Elegir1 = new javax.swing.JButton();
        Elegir2 = new javax.swing.JButton();
        Elegir3 = new javax.swing.JButton();
        Elegir4 = new javax.swing.JButton();
        Elegir5 = new javax.swing.JButton();
        lblHamburguesaCreando = new javax.swing.JLabel();
        lblPuntuacion = new javax.swing.JLabel();
        lblTituloOrdenesCreadas = new javax.swing.JLabel();
        txtPuntuacion = new javax.swing.JLabel();
        txtTiempo = new javax.swing.JLabel();
        lblTomate = new javax.swing.JLabel();
        lblCebolla = new javax.swing.JLabel();
        lblCreandoOrden = new javax.swing.JLabel();
        lblHeader = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("OverCookedFide");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 190, 50));

        salirMenuPrincipal_Juego.setBackground(new java.awt.Color(204, 51, 0));
        salirMenuPrincipal_Juego.setText("Salir del Juego");
        salirMenuPrincipal_Juego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirMenuPrincipal_JuegoActionPerformed(evt);
            }
        });
        jPanel1.add(salirMenuPrincipal_Juego, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 810, -1, -1));

        campo_nombre.setBackground(new java.awt.Color(255, 255, 255));
        campo_nombre.setForeground(new java.awt.Color(255, 255, 255));
        campo_nombre.setText("Jugador:");
        jPanel1.add(campo_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 10, -1, 30));

        nombre_jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_jugadorActionPerformed(evt);
            }
        });
        jPanel1.add(nombre_jugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 10, 180, -1));

        btnTerminarOrden.setBackground(new java.awt.Color(204, 153, 0));
        btnTerminarOrden.setText("Terminar Orden");
        btnTerminarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarOrdenActionPerformed(evt);
            }
        });
        jPanel1.add(btnTerminarOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, 150, 40));

        labelTemporizador.setBackground(new java.awt.Color(255, 255, 255));
        labelTemporizador.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labelTemporizador.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(labelTemporizador, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 100, 60));
        jPanel1.add(labelOrden2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 80, 110, 130));
        jPanel1.add(labelOrden3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 80, 110, 130));
        jPanel1.add(labelOrden1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 110, 130));
        labelOrden1.getAccessibleContext().setAccessibleDescription("");

        jPanel1.add(lblIngre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 460, 100, 110));
        jPanel1.add(lblIngre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 460, 100, 110));
        jPanel1.add(lblIngre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 460, 100, 110));
        jPanel1.add(lblIngre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 450, 100, 110));
        jPanel1.add(lblIngre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 460, 100, 110));
        jPanel1.add(lblPan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 70, 60));
        jPanel1.add(lblCarne, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 70, 60));
        jPanel1.add(lblQueso, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 70, 60));
        jPanel1.add(lblAguacate, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 570, 70, 60));
        jPanel1.add(lblLechuga, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, 70, 60));

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setText("◀");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 510, -1, -1));

        Elegir1.setBackground(new java.awt.Color(0, 153, 153));
        Elegir1.setText("Elegir");
        Elegir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Elegir1ActionPerformed(evt);
            }
        });
        jPanel1.add(Elegir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 590, 100, -1));

        Elegir2.setBackground(new java.awt.Color(0, 153, 153));
        Elegir2.setText("Elegir");
        Elegir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Elegir2ActionPerformed(evt);
            }
        });
        jPanel1.add(Elegir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 590, 100, -1));

        Elegir3.setBackground(new java.awt.Color(0, 153, 153));
        Elegir3.setText("Elegir");
        Elegir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Elegir3ActionPerformed(evt);
            }
        });
        jPanel1.add(Elegir3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 590, 100, -1));

        Elegir4.setBackground(new java.awt.Color(0, 153, 153));
        Elegir4.setText("Elegir");
        Elegir4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Elegir4ActionPerformed(evt);
            }
        });
        jPanel1.add(Elegir4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 590, 100, -1));

        Elegir5.setBackground(new java.awt.Color(0, 153, 153));
        Elegir5.setText("Elegir");
        Elegir5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Elegir5ActionPerformed(evt);
            }
        });
        jPanel1.add(Elegir5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 590, 100, -1));

        lblHamburguesaCreando.setBackground(new java.awt.Color(255, 255, 255));
        lblHamburguesaCreando.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lblHamburguesaCreando, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 180, 20));

        lblPuntuacion.setBackground(new java.awt.Color(255, 255, 255));
        lblPuntuacion.setForeground(new java.awt.Color(255, 255, 255));
        lblPuntuacion.setText("0");
        jPanel1.add(lblPuntuacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 60, 50));

        lblTituloOrdenesCreadas.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloOrdenesCreadas.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloOrdenesCreadas.setText("Creando su Orden - Ingredientes");
        jPanel1.add(lblTituloOrdenesCreadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        txtPuntuacion.setBackground(new java.awt.Color(255, 255, 255));
        txtPuntuacion.setForeground(new java.awt.Color(255, 255, 255));
        txtPuntuacion.setText("Puntuación:");
        jPanel1.add(txtPuntuacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 80, 30));

        txtTiempo.setBackground(new java.awt.Color(255, 255, 255));
        txtTiempo.setForeground(new java.awt.Color(255, 255, 255));
        txtTiempo.setText("Tiempo Restante:");
        jPanel1.add(txtTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, -1, -1));
        jPanel1.add(lblTomate, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 750, 70, 60));
        jPanel1.add(lblCebolla, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 660, 70, 60));

        lblCreandoOrden.setBackground(new java.awt.Color(255, 255, 255));
        lblCreandoOrden.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lblCreandoOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 180, 620));
        jPanel1.add(lblHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 850));

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

    private void Elegir5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Elegir5ActionPerformed
        elegirIngrediente(5);
    }//GEN-LAST:event_Elegir5ActionPerformed

    private void Elegir4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Elegir4ActionPerformed
        elegirIngrediente(4);
    }//GEN-LAST:event_Elegir4ActionPerformed

    private void Elegir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Elegir3ActionPerformed
        elegirIngrediente(3);
    }//GEN-LAST:event_Elegir3ActionPerformed

    private void Elegir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Elegir2ActionPerformed
        elegirIngrediente(2);
    }//GEN-LAST:event_Elegir2ActionPerformed

    private void Elegir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Elegir1ActionPerformed
        elegirIngrediente(1);
    }//GEN-LAST:event_Elegir1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        imprimirIngredientes();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTerminarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarOrdenActionPerformed
        terminarOrden();
    }//GEN-LAST:event_btnTerminarOrdenActionPerformed

    private void nombre_jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_jugadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_jugadorActionPerformed

    private void salirMenuPrincipal_JuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirMenuPrincipal_JuegoActionPerformed
        PantallaInicio volvermenu = new PantallaInicio(listaJugadores, listaPuntuaciones);
        volvermenu.setVisible(true);
        volvermenu.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_salirMenuPrincipal_JuegoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Elegir1;
    private javax.swing.JButton Elegir2;
    private javax.swing.JButton Elegir3;
    private javax.swing.JButton Elegir4;
    private javax.swing.JButton Elegir5;
    private javax.swing.JButton btnTerminarOrden;
    private javax.swing.JLabel campo_nombre;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelOrden1;
    private javax.swing.JLabel labelOrden2;
    private javax.swing.JLabel labelOrden3;
    private javax.swing.JLabel labelTemporizador;
    private javax.swing.JLabel lblAguacate;
    private javax.swing.JLabel lblCarne;
    private javax.swing.JLabel lblCebolla;
    private javax.swing.JLabel lblCreandoOrden;
    private javax.swing.JLabel lblHamburguesaCreando;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblIngre1;
    private javax.swing.JLabel lblIngre2;
    private javax.swing.JLabel lblIngre3;
    private javax.swing.JLabel lblIngre4;
    private javax.swing.JLabel lblIngre5;
    private javax.swing.JLabel lblLechuga;
    private javax.swing.JLabel lblPan;
    private javax.swing.JLabel lblPuntuacion;
    private javax.swing.JLabel lblQueso;
    private javax.swing.JLabel lblTituloOrdenesCreadas;
    private javax.swing.JLabel lblTomate;
    private javax.swing.JTextField nombre_jugador;
    private javax.swing.JButton salirMenuPrincipal_Juego;
    private javax.swing.JLabel txtPuntuacion;
    private javax.swing.JLabel txtTiempo;
    // End of variables declaration//GEN-END:variables
}


