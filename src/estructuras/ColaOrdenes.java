/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author Josuu
 */
public class ColaOrdenes{
    
    private NodoCola frente;
    private NodoCola ultimo;
    private int largo;

    public ColaOrdenes() {
        this.frente = null;
        this.ultimo = null;
        this.largo = 0;
    }
    
    //Encola una Orden
    public void encola(Orden orden){
        
        if(frente == null){ 
            
            frente = new NodoCola(orden);
            ultimo = new NodoCola(orden);
            
        }else{
            
            ultimo.setAtras(new NodoCola(orden));
            ultimo = new NodoCola(orden);
        }
        largo++;
    }
    
    //Atiende una cola
    public Orden atiende(){
        NodoCola aux = frente;
        Orden orden = null;
        
        if(frente != null){
            
            orden = frente.getOrden();
            frente = frente.getAtras();
            largo--;   
        }
        return orden;
    }
}
