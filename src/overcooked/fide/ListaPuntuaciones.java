package overcooked.fide;

import javax.swing.JOptionPane;

/**
 *
 * @author Dennis
 */
public class ListaPuntuaciones 
{
    private NodoLista primerNodo;

    public ListaPuntuaciones() 
    {
        this.primerNodo = null;
    }

    public void agregarPuntuacion(Puntuacion puntuacion) 
    {
        // Se crea un nuevo nodo con la puntuación proporcionada
        NodoLista nuevoNodo = new NodoLista(puntuacion);

        // Obtenemos el último jugador que inició sesión
        ListaJugador listaJugadores = new ListaJugador();
        Jugador ultimoJugador = listaJugadores.obtenerUltimoJugadorSesion();

        // Se valida que haya un jugador para asociar la puntuación
        if (ultimoJugador != null) 
        {
            // Se le da la puntuación al jugador y agregamos el nodo a la lista
            puntuacion.setJugador(ultimoJugador);
            if (primerNodo == null) 
            {
                primerNodo = nuevoNodo;
            } 
            else 
            {
                NodoLista nodoActual = primerNodo;
                while (nodoActual.getSiguiente() != null) 
                {
                    nodoActual = nodoActual.getSiguiente();
                }
                nodoActual.setSiguiente(nuevoNodo);
            }
        } 
        else 
        {
            //JOptionPane.showMessageDialog(null, 
                    //"No hay jugadores en la lista.");
        }
    }
    
    public void mostrarListaPuntuaciones() 
    {
        NodoLista nodoActual = primerNodo;
        while (nodoActual != null)
        {
            Puntuacion puntuacion = nodoActual.getPuntuacion();
            Jugador jugador = puntuacion.getJugador();
            System.out.println("Jugador: " + jugador.getNombre() + ", Puntuación: " + puntuacion.getPuntuacion());
            nodoActual = nodoActual.getSiguiente();
        }
    }
}
