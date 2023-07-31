/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package overcooked.fide;

/**
 *
 * @author Josuu
 */
public class ListaOrdenes {
    
    private NodoLista cabeza;
    
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
    
    public Orden extrae (int id) {
        
        Orden orden = null;
        
        if (cabeza != null){ 
            
            if (cabeza.getOrden().getId () == id){
                
                orden = cabeza.getOrden();
                
            }else{
                
                NodoLista aux = cabeza; 
                
                while (aux. getSiguiente() != null && aux.getSiguiente()
                        .getOrden().getId() < id){
                    
                    aux = aux. getSiguiente();
                    
                }
                
                if (aux. getSiguiente () != null && aux. getSiguiente()
                        .getOrden().getId() == id) {
                    
                    orden = aux.getSiguiente().getOrden();
                }
            }
        }
        return orden;
    }
}
