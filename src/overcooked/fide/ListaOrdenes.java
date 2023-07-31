/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package overcooked.fide;

import java.util.Random;

/**
 *
 * @author Josuu
 */
public class ListaOrdenes {
    
    private NodoLista cabeza;
    
    private ColaOrdenes cola = new ColaOrdenes();
    
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
    
    public void agregarOrdenCola(){

        Random random = new Random();
        int ordenAleatoria = random.nextInt(3);

        if (cabeza != null){ 
            
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
    
    public void llenarLista(){
        
        inserta(new Orden("Hamburguesa de carne",true,true,
                false,false,2,0));
        inserta(new Orden("Hamburguesa con queso",true,true,
                true,false,3,1));
        inserta(new Orden("Hamburguesa clásica",true,true,
                true,true,4,2));
  
    }
    
    public Orden devolver(){
        
        return cola.atiende();
    }
}
