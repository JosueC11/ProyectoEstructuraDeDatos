package estructuras;


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
