package overcooked.fide;


public class ListaJugador 
{
    private NodoLista primerNodo;
    private NodoLista ultimoJugadorSesion;

    public ListaJugador() 
    {
        primerNodo = null;
        ultimoJugadorSesion = null;
    }

    public void agregarJugador(Jugador jugador) {
        NodoLista nodoActual = primerNodo;

        while (nodoActual != null) {
            if (nodoActual.getJugador().getNombre().equals(jugador.getNombre())) {
                nodoActual.getJugador().setGenero(jugador.getGenero());
                nodoActual.getJugador().setIdentificado(jugador.getIdentificado());
                return;
            }
            nodoActual = nodoActual.getSiguiente();
        }

        NodoLista nuevoNodo = new NodoLista(jugador);

        if (primerNodo == null) {
            primerNodo = nuevoNodo;
            ultimoJugadorSesion = nuevoNodo;
        } else {
            nodoActual = primerNodo;
            while (nodoActual.getSiguiente() != null) {
                nodoActual = nodoActual.getSiguiente();
            }
            nodoActual.setSiguiente(nuevoNodo);
            ultimoJugadorSesion = nuevoNodo;
        }
    }

    // Método para obtener el último jugador que inició sesión
    public Jugador obtenerUltimoJugadorSesion() 
    {
        if (ultimoJugadorSesion != null) 
        {
            return ultimoJugadorSesion.getJugador();
        } 
        else 
        {
            return null;
        }
    }
}
