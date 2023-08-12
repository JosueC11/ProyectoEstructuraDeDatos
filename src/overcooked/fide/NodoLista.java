package overcooked.fide;


public class NodoLista {
    
    private Orden orden;
    private Jugador jugador;
    private Puntuacion puntuacion;
    private NodoLista siguiente;

    public NodoLista(Orden orden) {
        this.orden = orden;
    }

    public NodoLista(Puntuacion puntuacion) {
        this.puntuacion = puntuacion;
    }

    public NodoLista(Jugador jugador) {
        this.jugador = jugador;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Puntuacion getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Puntuacion puntuacion) {
        this.puntuacion = puntuacion;
    }

    public NodoLista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
}
