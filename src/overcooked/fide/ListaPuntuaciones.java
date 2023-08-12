package overcooked.fide;

import javax.swing.JOptionPane;

/**
 *
 * @author Dennis
 */

public class ListaPuntuaciones 
{
    private NodoLista primerNodo;

    public ListaPuntuaciones() {
        this.primerNodo = null;
    }

    public void agregarPuntuacion(Puntuacion puntuacion) {
        NodoLista nuevoNodo = new NodoLista(puntuacion);
        
        if (primerNodo == null) {
            primerNodo = nuevoNodo;
        } else {
            NodoLista actual = primerNodo;
            NodoLista anterior = null;

            while (actual != null && actual.getPuntuacion().getPuntuacion() >= puntuacion.getPuntuacion()) {
                anterior = actual;
                actual = actual.getSiguiente();
            }

            if (anterior == null) {
                nuevoNodo.setSiguiente(primerNodo);
                primerNodo = nuevoNodo;
            } else {
                anterior.setSiguiente(nuevoNodo);
                nuevoNodo.setSiguiente(actual);
            }
        }
    }    
    
    public void verListaPuntuaciones() {
        NodoLista actual = primerNodo;

        while (actual != null) {
            Puntuacion puntuacion = actual.getPuntuacion();
            System.out.println("Jugador: " + puntuacion.getNombre() + ", Puntuación: " + puntuacion.getPuntuacion());
            actual = actual.getSiguiente();
        }
    }
    
    /*public void mostrarListaPuntuaciones() 
    {
        NodoLista nodoActual = primerNodo;
        System.out.println("-------------------------------");
        while (nodoActual != null)
        {
            Puntuacion puntuacion = nodoActual.getPuntuacion();
            Jugador jugador = puntuacion.getJugador();
            System.out.println("Jugador: " + jugador.getNombre() + ", Puntuación: " 
                    + puntuacion.getPuntuacion());
            nodoActual = nodoActual.getSiguiente();
        }
        System.out.println( "-------------------------------");
    }*/
}
