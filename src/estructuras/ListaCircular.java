/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author Josuu
 */
public class ListaCircular{
    
    private NodoLC cabeza;
    private NodoLC ultimo;
    //Atributo para controlar el siguinete ingrediente a mostrar
    private NodoLC siguiente;
    private int indice = 0;

    //Metodo que inserta un ingrediente en la lista Circular 
    //recibe un ingrediente
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
    
    //Metodo que devuelve un ingrediente para imprimir en el jframe
    public String imprimir(){
        
        NodoLC aux = cabeza;
        String ingrediente = "";
        
        if(aux != null){
            //if para imprimir la cabeza
            if(indice == 0){
                ingrediente += aux.getDato().getNombre();
                indice++;
                //setear el siguiente que se imprime
                siguiente = aux.getNext();
            }else{
                while(aux != siguiente){        
                    aux = aux.getNext();             
                }
                ingrediente += aux.getDato().getNombre();
                //setear el siguiente que se imprime
                siguiente = aux.getNext();
            }
        } 
        return ingrediente;
    }
}
    

