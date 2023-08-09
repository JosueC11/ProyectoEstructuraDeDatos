
package overcooked.fide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public final class Juego extends javax.swing.JFrame {

    public static ArrayList<Puntuacion> arrPuntuaciones = new ArrayList<>();
    
    Jugador jugador = Jugador.getInstance();
    
    ListaOrdenes lista = new ListaOrdenes();
    
    ListaCircular  listaCircular = new ListaCircular();

    private Timer tiempoOrdenes;
    private Timer tiempoJuego;
    
    private int centesimaOrdenes = 99, segundoOrdenes = 1;
    private int centesimaJuego = 99, segundoJuego = 59, minutoJuego = 4;
    
    private int inicioOrdenes = 0;

    public Juego(){
        
        initComponents();
        // Setear posición a la pantalla
        setLocationRelativeTo(null);
        setResizable(false);  
        
        nombre_jugador.setText(jugador.getNombre());
        
        //setear text label
        labelOrden1.setText("vacio");
        labelOrden2.setText("vacio");
        labelOrden3.setText("vacio");
        
        //carga imagenes de lo campos de las ordenes
        setImagenesOrdenesBlanco();
        
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
    public void setImagenesOrdenesBlanco(){
        
        //Se crea un objeto Icon con la libreria Icon, se le pasa la posición 
        //del label 
        Icon ordenBlanco = new ImageIcon(new ImageIcon
        (getClass().getResource("ordenBlanco.png")).getImage()
        .getScaledInstance(labelOrden1.getWidth(), 
        labelOrden1.getHeight(), 0));
                        
        // Se le setea el Icon a el label
        labelOrden1.setIcon(ordenBlanco);
        labelOrden2.setIcon(ordenBlanco);
        labelOrden3.setIcon(ordenBlanco);

    }
    
    //genera las ordenes al inicio del juego
    public void generarOrdenInicio(){
        
        if(inicioOrdenes == 0){
            
            // metodo que devulve una orden
            Orden orden = lista.devolver();
            
            //revisar si hay algo en las ordenes
            
            if (labelOrden1.getText().equals("vacio") && 
                labelOrden2.getText().equals("vacio") && 
                labelOrden3.getText().equals("vacio")){

                //casos posibles segun el id de orden
                switch(orden.getId()){
                    
                    case 0:
                        
                        //Se crea un objeto Icon con la libreria Icon, se le 
                        //pasa la posición del label 
                        Icon OrdenCarne = new ImageIcon(new ImageIcon
                        (getClass().getResource
                        ("hamburguesaDeCarne.png")).getImage()
                        .getScaledInstance(labelOrden1.getWidth(), 
                        labelOrden1.getHeight(),0));

                        // Se le setea el Icon a el label
                        labelOrden1.setIcon(OrdenCarne);
                        labelOrden1.setText("Lleno");   
                        break;

                    case 1:

                        //Se crea un objeto Icon con la libreria Icon, se le 
                        //pasa la posición del label 
                        Icon OrdenQueso = new ImageIcon(new ImageIcon
                        (getClass().getResource
                        ("hamburguesaConQueso.png")).getImage()
                        .getScaledInstance(labelOrden2.getWidth(), 
                        labelOrden2.getHeight(),0));

                        // Se le setea el Icon a el label
                        labelOrden1.setIcon(OrdenQueso);
                        labelOrden1.setText("Lleno");
                        break;
                    case 2:

                        //Se crea un objeto Icon con la libreria Icon, se le
                        //pasa la posición del label 
                        Icon OrdenClasica = new ImageIcon(new ImageIcon
                        (getClass().getResource
                        ("hamburguesaClasica.png")).getImage()
                        .getScaledInstance(labelOrden3.getWidth(), 
                        labelOrden3.getHeight(),0));

                        // Se le setea el Icon a el label
                        labelOrden1.setIcon(OrdenClasica);
                        labelOrden1.setText("Lleno");
                        break;
                } 
            }
            inicioOrdenes ++;        
        }
    }
    
    //metodo para generar las ordenes cada 20 segundos
    public void generarOrden(){
        
        //metodo devulve una orden
        Orden orden = lista.devolver();

        //Se crea un objeto Icon con la libreria Icon, se le pasa la posición 
        //del label 
        Icon OrdenCarne = new ImageIcon(new ImageIcon(getClass()
                .getResource("hamburguesaDeCarne.png")).getImage()
                .getScaledInstance(labelOrden1.getWidth(), 
                labelOrden1.getHeight(),0));

        Icon OrdenQueso = new ImageIcon(new ImageIcon(getClass()
                .getResource("hamburguesaConQueso.png")).getImage()
                .getScaledInstance(labelOrden1.getWidth(), 
                        labelOrden1.getHeight(),0));

        Icon OrdenClasica = new ImageIcon(new ImageIcon(getClass()
                .getResource("hamburguesaClasica.png")).getImage()
                .getScaledInstance(labelOrden1.getWidth(), 
                        labelOrden1.getHeight(),0));

        if (labelOrden1.getText().equals("vacio")
                && labelOrden2.getText().equals("vacio")
                && labelOrden3.getText().equals("vacio")) {

            switch (orden.getId()) {
                case 0 -> {
                    // Se le setea el Icon a el label
                    labelOrden1.setIcon(OrdenCarne);
                    labelOrden1.setText("Lleno");
                }
                case 1 -> {
                    // Se le setea el Icon a el label
                    labelOrden1.setIcon(OrdenQueso);
                    labelOrden1.setText("Lleno");
                }
                case 2 -> {
                    // Se le setea el Icon a el label
                    labelOrden1.setIcon(OrdenClasica);
                    labelOrden1.setText("Lleno");
                }
            }
        } else if (labelOrden1.getText().equals("Lleno")
                && labelOrden2.getText().equals("vacio")
                && labelOrden3.getText().equals("vacio")) {
            
            switch (orden.getId()) {
                case 0 -> {
                    // Se le setea el Icon a el label
                    labelOrden2.setIcon(OrdenCarne);
                    labelOrden2.setText("Lleno");
                }
                case 1 -> {
                    // Se le setea el Icon a el label
                    labelOrden2.setIcon(OrdenQueso);
                    labelOrden2.setText("Lleno");
                }
                case 2 -> {
                    // Se le setea el Icon a el label
                    labelOrden2.setIcon(OrdenClasica);
                    labelOrden2.setText("Lleno");
                }
            }

        } else if (labelOrden1.getText().equals("Lleno")
                && labelOrden2.getText().equals("Lleno")
                && labelOrden3.getText().equals("vacio")) {

            switch (orden.getId()) {
                case 0 -> {
                    // Se le setea el Icon a el label
                    labelOrden3.setIcon(OrdenCarne);
                    labelOrden3.setText("Lleno");
                }
                case 1 -> {
                    // Se le setea el Icon a el label
                    labelOrden3.setIcon(OrdenQueso);
                    labelOrden3.setText("Lleno");
                }
                case 2 -> {
                    // Se le setea el Icon a el label
                    labelOrden3.setIcon(OrdenClasica);
                    labelOrden3.setText("Lleno");
                }
            }
        }
    }
    
    public void llenarListaCircular(){
        
        listaCircular.insertar(new Ingrediente("lechuga"));
        listaCircular.insertar(new Ingrediente("pan"));
        listaCircular.insertar(new Ingrediente("queso"));
        listaCircular.insertar(new Ingrediente("carne"));
        listaCircular.insertar(new Ingrediente("aguacate"));
    }
    
    public void imprimirIngredientes(){
        
        int indice = 0;
        
        String nombre = listaCircular.imprimir();
        while(indice < 5){
            
            nombre = listaCircular.imprimir();
            Icon ingrediente = new ImageIcon(new ImageIcon(getClass()
                        .getResource(nombre+".png")).getImage()
                        .getScaledInstance(lbl1.getWidth(), 
                        lbl1.getHeight(),0));
            
            
            if(indice == 0){
                switch(nombre){
                    case "lechuga" -> {
                        lbl1.setIcon(ingrediente);
                    }
                    case "pan" -> {
                        lbl1.setIcon(ingrediente);
                    }
                    case "queso" -> {
                        lbl1.setIcon(ingrediente);
                    }
                    case "carne" -> {
                        lbl1.setIcon(ingrediente);
                    } 
                    case "aguacate" -> {
                        lbl1.setIcon(ingrediente);
                    } 
                }                
            }
            if(indice == 1){
                switch(nombre){
                    case "lechuga" -> {
                        lbl2.setIcon(ingrediente);
                    }
                    case "pan" -> {
                        lbl2.setIcon(ingrediente);
                    }
                    case "queso" -> {
                        lbl2.setIcon(ingrediente);
                    }
                    case "carne" -> {
                        lbl2.setIcon(ingrediente);
                    }
                    case "aguacate" -> {
                        lbl2.setIcon(ingrediente);
                    } 
                }                
            }
            if(indice == 2){
                switch(nombre){
                    case "lechuga" -> {
                        lbl3.setIcon(ingrediente);
                    }
                    case "pan" -> {
                        lbl3.setIcon(ingrediente);
                    }
                    case "queso" -> {
                        lbl3.setIcon(ingrediente);
                    }
                    case "carne" -> {
                        lbl3.setIcon(ingrediente);
                    } 
                    case "aguacate" -> {
                        lbl3.setIcon(ingrediente);
                    } 
                }                
            }
            if(indice == 3){ 
                switch(nombre){
                    case "lechuga" -> {
                        lbl4.setIcon(ingrediente);
                    }
                    case "pan" -> {
                        lbl4.setIcon(ingrediente);
                    }
                    case "queso" -> {
                        lbl4.setIcon(ingrediente);
                    }
                    case "carne" -> {
                        lbl4.setIcon(ingrediente);
                    } 
                    case "aguacate" -> {
                        lbl4.setIcon(ingrediente);
                   } 
                }                
            }
            if(indice == 4){ 
                switch(nombre){
                    case "lechuga" -> {
                        lbl5.setIcon(ingrediente);
                    }
                    case "pan" -> {
                        lbl5.setIcon(ingrediente);
                    }
                    case "queso" -> {
                        lbl5.setIcon(ingrediente);
                    }
                    case "carne" -> {
                        lbl5.setIcon(ingrediente);
                    } 
                    case "aguacate" -> {
                        lbl5.setIcon(ingrediente);
                    } 
                }                
            }
            indice++;
        }
    }
    
    public void rotarIngredientes(){   
        listaCircular.rotar();
    }
    
    public void terminarJuego(){
        
        tiempoOrdenes.stop(); 
        tiempoJuego.stop(); 
        
        JOptionPane.showMessageDialog
        (null,"El juego terminó");
        
        PantallaInicio pantallaInicio = new PantallaInicio();
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

        jLabel1 = new javax.swing.JLabel();
        salirMenuPrincipal_Juego = new javax.swing.JButton();
        campo_nombre = new javax.swing.JLabel();
        nombre_jugador = new javax.swing.JTextField();
        spiner_puntuacion = new javax.swing.JSpinner();
        txt_puntuacion = new javax.swing.JLabel();
        enviar_puntuacion = new javax.swing.JButton();
        btnTerminarOrden = new javax.swing.JButton();
        labelTemporizador = new javax.swing.JLabel();
        labelOrden2 = new javax.swing.JLabel();
        labelOrden3 = new javax.swing.JLabel();
        labelOrden1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

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

        btnTerminarOrden.setText("Terminar Orden");
        btnTerminarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarOrdenActionPerformed(evt);
            }
        });

        labelOrden2.setText("vacio");

        labelOrden3.setText("vacio");

        labelOrden1.setText("vacio");

        jButton1.setText("◀");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(spiner_puntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_puntuacion)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campo_nombre)
                        .addGap(18, 18, 18)
                        .addComponent(nombre_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(labelTemporizador, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(238, 238, 238)
                                .addComponent(jButton1)
                                .addGap(27, 27, 27)
                                .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(305, 305, 305)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(salirMenuPrincipal_Juego)
                                                .addGap(528, 528, 528))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(labelOrden1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnTerminarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(9, 9, 9))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addComponent(enviar_puntuacion)
                                        .addGap(805, 805, 805)
                                        .addComponent(labelOrden2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(labelOrden3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 27, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTemporizador, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nombre_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campo_nombre)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelOrden3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOrden2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOrden1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spiner_puntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enviar_puntuacion))
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(txt_puntuacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(btnTerminarOrden)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(253, 253, 253))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(282, 282, 282)))
                .addComponent(salirMenuPrincipal_Juego)
                .addGap(21, 21, 21))
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

        Puntuacion nuevaPuntuacion = new Puntuacion
        (puntuacion, jugador);

        arrPuntuaciones.add(nuevaPuntuacion);
        
        spiner_puntuacion.setValue(0);
    }//GEN-LAST:event_enviar_puntuacionActionPerformed

    private void btnTerminarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarOrdenActionPerformed
        
        //Se crea un objeto Icon con la libreria Icon, se le pasa la posición 
        //del label 
        Icon ordenBlanco = new ImageIcon(new ImageIcon(getClass()
                .getResource("ordenBlanco.png")).getImage()
                .getScaledInstance(labelOrden1.getWidth(), 
                labelOrden1.getHeight(),0));
                        
        labelOrden1.setText("vacio");
        labelOrden1.setIcon(ordenBlanco);

        if(labelOrden1.getText().equals("vacio") && 
          labelOrden2.getText().equals("Lleno") && 
           labelOrden3.getText().equals("vacio")){
            
            labelOrden1.setIcon(labelOrden2.getIcon());
            labelOrden2.setIcon(ordenBlanco);
            labelOrden1.setText("Lleno");
            labelOrden2.setText("vacio");
            
        }else if(labelOrden1.getText().equals("vacio") && 
                labelOrden2.getText().equals("Lleno") && 
                labelOrden3.getText().equals("Lleno")){
            
            labelOrden1.setIcon(labelOrden2.getIcon());
            labelOrden2.setIcon(labelOrden3.getIcon());
            labelOrden3.setIcon(ordenBlanco);
            labelOrden1.setText("Lleno");
            labelOrden3.setText("vacio");
        }
    }//GEN-LAST:event_btnTerminarOrdenActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        imprimirIngredientes();           
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton btnTerminarOrden;
    private javax.swing.JLabel campo_nombre;
    private javax.swing.JButton enviar_puntuacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelOrden1;
    private javax.swing.JLabel labelOrden2;
    private javax.swing.JLabel labelOrden3;
    private javax.swing.JLabel labelTemporizador;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JTextField nombre_jugador;
    private javax.swing.JButton salirMenuPrincipal_Juego;
    private javax.swing.JSpinner spiner_puntuacion;
    private javax.swing.JLabel txt_puntuacion;
    // End of variables declaration//GEN-END:variables
}


