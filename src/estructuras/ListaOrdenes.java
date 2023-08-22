/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

import java.util.Random;

/**
 *
 * @author Josuu
 */
public class ListaOrdenes {
    
    private NodoLista cabeza;
    
    private ColaOrdenes cola = new ColaOrdenes();
    
    //Metodo para insertar una orden a la lista de ordenes 
    public void inserta(Orden orden){
        
        if(cabeza == null){
            
            cabeza = new NodoLista(orden);
            
        }else if(orden.getId() < cabeza.getOrden().getId()){
            
            NodoLista aux = new NodoLista(orden);
            aux.setSiguiente(cabeza);
            cabeza = aux;
            
        }else if(cabeza.getSiguiente() == null){
            
            cabeza.setSiguiente(new NodoLista(orden));
            
        }else{
            
           NodoLista aux = cabeza;
           
           while (aux.getSiguiente() != null &&
                   aux.getSiguiente().getOrden().getId()
                   < orden.getId()){
               aux=aux.getSiguiente();
           }
           
           NodoLista temp = new NodoLista(orden);
           temp.setSiguiente(aux.getSiguiente());
           aux.setSiguiente(temp);
        } 
    }
    
    //metodo para elegir una orden de la lista y agregarla en la cola
    public void agregarOrdenCola(){

        //Generar un numero random para elegir la orden 
        Random random = new Random();
        int ordenAleatoria = random.nextInt(3);

        if (cabeza != null){ 
            
            //revisa la lista y busca la orden y la encola 
            if (cabeza.getOrden().getId () == ordenAleatoria){
                
                cola.encola(cabeza.getOrden());
                
            }else{
                
                NodoLista aux = cabeza; 
                
                while (aux.getSiguiente() != null && aux.getSiguiente()
                        .getOrden().getId() != ordenAleatoria){
                    
                    aux = aux.getSiguiente();
                    
                }
                
                if (aux. getSiguiente () != null && aux.getSiguiente()
                        .getOrden().getId() == ordenAleatoria) {
                    
                    cola.encola(aux.getSiguiente().getOrden());
                }
            }
        }
    }
    
    //metodo para devolver una orden de la lista a la hora de revisar 
    //la orden creada
    public Orden devolverOrden(String nombreOrden){
        
        NodoLista aux = cabeza;
        
        if(cabeza != null){
            
            while(!aux.getOrden().getNombre().equals(nombreOrden)){           
                aux = aux.getSiguiente();  
            }
        }
        return aux.getOrden();
    }
    
    //Metodo para llenar la lista de ordenes
    public void llenarLista(){
        
        inserta(new Orden("hamburguesaDeCarne",true,true,
                false,false,2,0));
        inserta(new Orden("hamburguesaConQueso",true,true,
                true,false,3,1));
        inserta(new Orden("hamburguesaClasica",true,true,
                true,true,4,2));
    }
    
    //metodo para devolver una orden que está en el frente de la cola
    public Orden devolverDeCola(){
        
        return cola.atiende();
    }
}
