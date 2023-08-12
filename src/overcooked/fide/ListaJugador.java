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

    public void agregarJugador(Jugador jugador) 
    {
        NodoLista nuevoNodo = new NodoLista(jugador);

        if (primerNodo == null) 
        {
            primerNodo = nuevoNodo;
            ultimoJugadorSesion = nuevoNodo;
        } 
        else
        {
            NodoLista nodoActual = primerNodo;
            while (nodoActual.getSiguiente() != null) 
            {
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
    
    
    
    // Método para establecer el último jugador que inició sesión
    public void setUltimoJugadorSesion(NodoLista nodo) 
    {
        ultimoJugadorSesion = nodo;
    }
}
