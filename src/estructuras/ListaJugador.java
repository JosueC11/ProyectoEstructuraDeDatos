package estructuras;


public class ListaJugador 
{
    private NodoLista primerNodo;
    
    // Se crea el nodo para encontrar al ultimo jugador en iniciar sesion
    // Esto quiere decir que es el jugador actual que está jugando
    // Esto para atribuirle las puntuaciones
    private NodoLista ultimoJugadorSesion;

    public ListaJugador() 
    {
        primerNodo = null;
        ultimoJugadorSesion = null;
    }

    
    //Metodo para agregar jugador nuevo
    public void agregarJugador(Jugador jugador) 
    {
        // Se comienza desde el primer nodo de la lista
        NodoLista nodoActual = primerNodo;

        // Se recorre la lista hasta que se llegue al final
        while (nodoActual != null) {
            // Se verifica si el nombre del jugador actual es igual al nombre 
            //del jugador que se quiere agregar
            if (nodoActual.getJugador().getNombre().equals(jugador.
                    getNombre())) {
                // Si el nombre ya existe, se actualizan los datos del 
                //jugador existente
                nodoActual.getJugador().setGenero(jugador.
                        getGenero());
                nodoActual.getJugador().setIdentificado(jugador.
                        getIdentificado());
                return; // Se sale del método, ya que no es necesario 
                //agregar un nuevo nodo
            }
            nodoActual = nodoActual.getSiguiente(); // Se avanza al siguiente 
                                                    // nodo
        }

        // Si no se encontró el jugador en la lista, 
        //se procede a agregarlo como un nuevo nodo
        
        NodoLista nuevoNodo = new NodoLista(jugador);

        if (primerNodo == null) {
            // Si la lista estaba vacía, se establece el nuevo nodo como el 
            //primer y último nodo de la lista
            primerNodo = nuevoNodo;
            ultimoJugadorSesion = nuevoNodo;
        } else {
            nodoActual = primerNodo;
            while (nodoActual.getSiguiente() != null) {
                nodoActual = nodoActual.getSiguiente(); // Se avanza al último 
                                                        // nodo actual
            }
            nodoActual.setSiguiente(nuevoNodo); // Se establece el nuevo
                                                        // nodo como el 
                                                        //siguiente del último 
                                                        //nodo actual
            ultimoJugadorSesion = nuevoNodo; // Se actualiza el puntero al 
                                             //último jugador en sesión
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
