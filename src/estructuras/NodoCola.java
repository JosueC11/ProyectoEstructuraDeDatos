package estructuras;


public class NodoCola{
    
    private Orden orden;
    private NodoCola atras;

    public NodoCola(Orden orden) {
        this.orden = orden;
        this.atras = null;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public NodoCola getAtras() {
        return atras;
    }

    public void setAtras(NodoCola atras) {
        this.atras = atras;
    }
}
