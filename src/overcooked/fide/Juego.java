
package overcooked.fide;

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

    DefaultTableModel modeloListado;
    String[] info = new String[2];
    
    private Timer tiempoOrdenes;
    private Timer tiempoJuego;
    
    private int centesimaOrdenes = 99, segundoOrdenes = 1;
    private int centesimaJuego = 99, segundoJuego = 59, minutoJuego = 4;
    
    private int inicioOrdenes = 0;

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
        
        //setear text label
        labelOrden1.setText("vacio");
        labelOrden2.setText("vacio");
        labelOrden3.setText("vacio");
        
        nombre_jugador.setText(listaJugadores.obtenerUltimoJugadorSesion().getNombre());
        
        //setear text area
        txtOrdenCompleta.setText("Creando Orden\n\nIngredientes:\n");
        
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
        
        modeloListado = new DefaultTableModel();
        modeloListado.addColumn("Jugador");
        modeloListado.addColumn("Puntuación");
        
        puntuaciones_table.setModel(modeloListado);
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
                        lbl1.setText("lechuga");
                    }
                    case "pan" -> {
                        lbl1.setIcon(ingrediente);
                        lbl1.setText("pan");
                    }
                    case "queso" -> {
                        lbl1.setIcon(ingrediente);
                        lbl1.setText("queso");
                    }
                    case "carne" -> {
                        lbl1.setIcon(ingrediente);
                        lbl1.setText("carne");
                    } 
                    case "aguacate" -> {
                        lbl1.setIcon(ingrediente);
                        lbl1.setText("aguacate");
                    } 
                }                
            }
            if(indice == 1){
                switch(nombre){
                    case "lechuga" -> {
                        lbl2.setIcon(ingrediente);
                        lbl2.setText("lechuga");
                    }
                    case "pan" -> {
                        lbl2.setIcon(ingrediente);
                        lbl2.setText("pan");
                    }
                    case "queso" -> {
                        lbl2.setIcon(ingrediente);
                        lbl2.setText("queso");
                    }
                    case "carne" -> {
                        lbl2.setIcon(ingrediente);
                        lbl2.setText("carne");
                    } 
                    case "aguacate" -> {
                        lbl2.setIcon(ingrediente);
                        lbl2.setText("aguacate");
                    } 
                }                
            }
            if(indice == 2){
                switch(nombre){
                    case "lechuga" -> {
                        lbl3.setIcon(ingrediente);
                        lbl3.setText("lechuga");
                    }
                    case "pan" -> {
                        lbl3.setIcon(ingrediente);
                        lbl3.setText("pan");
                    }
                    case "queso" -> {
                        lbl3.setIcon(ingrediente);
                        lbl3.setText("queso");
                    }
                    case "carne" -> {
                        lbl3.setIcon(ingrediente);
                        lbl3.setText("carne");
                    } 
                    case "aguacate" -> {
                        lbl3.setIcon(ingrediente);
                        lbl3.setText("aguacate");
                    } 
                }                
            }
            if(indice == 3){ 
                switch(nombre){
                    case "lechuga" -> {
                        lbl4.setIcon(ingrediente);
                        lbl4.setText("lechuga");
                    }
                    case "pan" -> {
                        lbl4.setIcon(ingrediente);
                        lbl4.setText("pan");
                    }
                    case "queso" -> {
                        lbl4.setIcon(ingrediente);
                        lbl4.setText("queso");
                    }
                    case "carne" -> {
                        lbl4.setIcon(ingrediente);
                        lbl4.setText("carne");
                    } 
                    case "aguacate" -> {
                        lbl4.setIcon(ingrediente);
                        lbl4.setText("aguacate");
                    } 
                }                
            }
            if(indice == 4){ 
                switch(nombre){
                    case "lechuga" -> {
                        lbl5.setIcon(ingrediente);
                        lbl5.setText("lechuga");
                    }
                    case "pan" -> {
                        lbl5.setIcon(ingrediente);
                        lbl5.setText("pan");
                    }
                    case "queso" -> {
                        lbl5.setIcon(ingrediente);
                        lbl5.setText("queso");
                    }
                    case "carne" -> {
                        lbl5.setIcon(ingrediente);
                        lbl5.setText("carne");
                    } 
                    case "aguacate" -> {
                        lbl5.setIcon(ingrediente);
                        lbl5.setText("aguacate");
                    } 
                }                 
            }
            indice++;
        }
    }
    
    public void rotarIngredientes(){   
        listaCircular.rotar();
    }
    
    public void elegirIngrediente(int numeroLabel){
        
        Icon ingredienteBlanco = new ImageIcon(new ImageIcon(getClass()
                        .getResource("ordenBlanco.png")).getImage()
                        .getScaledInstance(lbl1.getWidth(), 
                        lbl1.getHeight(),0));
        
        switch(numeroLabel){
            
            case 1 -> {
                txtOrdenCompleta.append("\n" + lbl1.getText());
                lbl1.setIcon(ingredienteBlanco);  
            }
            
            case 2 -> {
                txtOrdenCompleta.append("\n" + lbl2.getText());
                lbl2.setIcon(ingredienteBlanco);
            }
            
            case 3 -> {
                txtOrdenCompleta.append("\n" + lbl3.getText());
                lbl3.setIcon(ingredienteBlanco);
            }
            
            case 4 -> {
                txtOrdenCompleta.append("\n" + lbl4.getText());
                lbl4.setIcon(ingredienteBlanco);
            }
            
            case 5 -> {
                txtOrdenCompleta.append("\n" + lbl5.getText());
                lbl5.setIcon(ingredienteBlanco);
            }
        } 
        
        imprimirIngredientes();
    }
    
    public void terminarJuego(){
        
        tiempoOrdenes.stop(); 
        tiempoJuego.stop(); 
        
        JOptionPane.showMessageDialog
        (null,"El juego terminó");
        
        PantallaInicio pantallaInicio = new PantallaInicio(listaJugadores, listaPuntuaciones);
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
        agregar_puntuacion = new javax.swing.JButton();
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOrdenCompleta = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        puntuaciones_table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("OverCookedFide");

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

        agregar_puntuacion.setText("Enviar");
        agregar_puntuacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_puntuacionActionPerformed(evt);
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

        jButton2.setText("Elegir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Elegir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Elegir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Elegir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Elegir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txtOrdenCompleta.setColumns(20);
        txtOrdenCompleta.setRows(5);
        jScrollPane1.setViewportView(txtOrdenCompleta);

        puntuaciones_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(puntuaciones_table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(agregar_puntuacion)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txt_puntuacion)
                                    .addGap(18, 18, 18)
                                    .addComponent(spiner_puntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelOrden1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(campo_nombre)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnTerminarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelOrden2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelOrden3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nombre_jugador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32))
                            .addComponent(labelTemporizador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(250, 250, 250))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(salirMenuPrincipal_Juego)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5)
                                    .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 479, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelTemporizador, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campo_nombre))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelOrden1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOrden2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOrden3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton6)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_puntuacion)
                            .addComponent(spiner_puntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(agregar_puntuacion)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(156, 156, 156)
                                .addComponent(btnTerminarOrden)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addComponent(salirMenuPrincipal_Juego)))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirMenuPrincipal_JuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirMenuPrincipal_JuegoActionPerformed
        PantallaInicio volvermenu = new PantallaInicio(listaJugadores, listaPuntuaciones); 
        volvermenu.setVisible(true); 
        volvermenu.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_salirMenuPrincipal_JuegoActionPerformed

    private void nombre_jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_jugadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_jugadorActionPerformed

    private void agregar_puntuacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_puntuacionActionPerformed

        int puntuacion = (int) spiner_puntuacion.getValue();

        // Obtener el jugador que inició sesión
        Jugador ultimoJugador = listaJugadores.obtenerUltimoJugadorSesion();

        if (ultimoJugador != null) 
        {
            String nombreJugador = ultimoJugador.getNombre();

            Puntuacion nuevaPuntuacion = new Puntuacion(nombreJugador, puntuacion);
            listaPuntuaciones.agregarPuntuacion(nuevaPuntuacion);
            
            listaPuntuaciones.verListaPuntuaciones();
        } 
        else
        {
            JOptionPane.showMessageDialog(null, "No hay jugador en sesión.");
        }
    }//GEN-LAST:event_agregar_puntuacionActionPerformed

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
        
        txtOrdenCompleta.setText("");
        txtOrdenCompleta.setText("Creando Orden\n\nIngredientes:\n");
    }//GEN-LAST:event_btnTerminarOrdenActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        imprimirIngredientes();           
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        elegirIngrediente(4); 
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        elegirIngrediente(1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        elegirIngrediente(2);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        elegirIngrediente(3); 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        elegirIngrediente(5); 
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar_puntuacion;
    private javax.swing.JButton btnTerminarOrden;
    private javax.swing.JLabel campo_nombre;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
    private javax.swing.JTable puntuaciones_table;
    private javax.swing.JButton salirMenuPrincipal_Juego;
    private javax.swing.JSpinner spiner_puntuacion;
    private javax.swing.JTextArea txtOrdenCompleta;
    private javax.swing.JLabel txt_puntuacion;
    // End of variables declaration//GEN-END:variables
}


