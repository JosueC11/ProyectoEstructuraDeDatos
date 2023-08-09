/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package overcooked.fide;

/**
 *
 * @author Josuu
 */
public class ListaCircular{
    
    private NodoLC cabeza;
    private NodoLC ultimo;
    private NodoLC siguiente;
    private int indice = 0;

    public void insertar(Ingrediente ingrediente){
        
        if(cabeza == null){
            cabeza = new NodoLC(ingrediente);
            ultimo = cabeza;
        }else{
            ultimo.setNext(new NodoLC(ingrediente));
            ultimo = ultimo.getNext();    
        }
        ultimo.setNext(cabeza);
    }
    
    public void rotar(){
        
        NodoLC aux = cabeza;
        
        while (aux.getNext() != ultimo) {
            aux = aux.getNext();
        }

        cabeza = cabeza.getNext();
        ultimo = ultimo.getNext();
        ultimo.setNext(cabeza);
    }  
    
    public String imprimir(){
        
        NodoLC aux = cabeza;
        String s = "";
        
        if(aux != null){
            
            if(indice == 0){
                s += aux.getDato().getNombre();
                indice++;
                siguiente = aux.getNext();
            }else{
                while(aux != siguiente){        
                    aux = aux.getNext();             
                }
                s += aux.getDato().getNombre();
                siguiente = aux.getNext();
            }
        } 
        return s;
    }
}
    

