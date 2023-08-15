/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author Josuu
 */
public class NodoLC{
    
    private Ingrediente ingrediente;
    private NodoLC next;

    public NodoLC(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Ingrediente getDato() {
        return ingrediente;
    }

    public void setDato(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public NodoLC getNext() {
        return next;
    }

    public void setNext(NodoLC next) {
        this.next = next;
    } 
    
    
}
