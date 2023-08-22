
package estructuras;

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

    //Método para agregar puntuaciones
    
    public void agregarPuntuacion(Puntuacion puntuacion) {
        // Crear un nuevo nodo de lista con la puntuación proporcionada
        NodoLista nuevoNodo = new NodoLista(puntuacion);

        // Comprobar si la lista está vacía
        if (primerNodo == null) {
            // Si está vacía, el nuevo nodo se convierte en el primer nodo de 
            //la lista
            primerNodo = nuevoNodo;
        } else {
            // Si la lista no está vacía, se necesita encontrar la posición 
            //adecuada para insertar el nuevo nodo
            NodoLista actual = primerNodo; // Nodo actual para recorrer la lista
            NodoLista anterior = null;      // Nodo anterior al nodo actual

            // Recorrer la lista mientras el nodo actual no sea nulo y su 
            //puntuación sea mayor o igual que la puntuación del nuevo nodo
            while (actual != null && actual.getPuntuacion().getPuntuacion() >= 
                    puntuacion.getPuntuacion()) {
                anterior = actual;   // Guardar el nodo actual como el anterior
                actual = actual.getSiguiente(); // Mover al siguiente nodo en 
                //la lista
            }

            // Comprobar si el nuevo nodo debe ser el primer nodo de la lista
            if (anterior == null) {
                // Si es el primer nodo, ajustar los enlaces para que el nuevo 
                //nodo sea el primero
                nuevoNodo.setSiguiente(primerNodo);
                primerNodo = nuevoNodo;
            } else {
                // Si no es el primer nodo, insertar el nuevo nodo entre el 
                //nodo anterior y el nodo actual
                anterior.setSiguiente(nuevoNodo); // El siguiente del 
                //nodo anterior apunta al nuevo nodo
                nuevoNodo.setSiguiente(actual);   // El siguiente del 
                //nuevo nodo apunta al nodo actual
            }
        }
    }   
    
    public Object[][] obtenerPuntuacionesComoMatriz() {
        // Inicializar el nodo actual para recorrer la lista desde 
        //el primer nodo
        NodoLista actual = primerNodo;

        // Obtener el tamaño de la lista llamando al método 
        //auxiliar obtenerTamaño
        int tamaño = obtenerTamaño();

        // Crear una matriz para almacenar las puntuaciones y nombres, 
        //con filas igual al tamaño y columnas igual a 2
        Object[][] puntuacionesMatriz = new Object[tamaño][2];

        // Inicializar la variable fila para rastrear la fila actual 
        //en la matriz
        int fila = 0;

        // Recorrer la lista y agregar las puntuaciones y nombres 
        //en la matriz
        while (actual != null) {
            // Obtener la puntuación del nodo actual
            Puntuacion puntuacion = actual.getPuntuacion();

            // Agregar el nombre y la puntuación a la fila actual de la matriz
            puntuacionesMatriz[fila][0] = puntuacion.getNombre();
            puntuacionesMatriz[fila][1] = puntuacion.getPuntuacion();

            // Mover al siguiente nodo en la lista
            actual = actual.getSiguiente();

            // Avanzar a la siguiente fila en la matriz
            fila++;
        }

        // Devolver la matriz que contiene las puntuaciones y nombres
        return puntuacionesMatriz;
    }

// Método auxiliar para obtener el tamaño de la lista
    private int obtenerTamaño() {
        // Inicializar el tamaño en 0
        int tamaño = 0;

        // Inicializar el nodo actual para recorrer la lista 
        //desde el primer nodo
        NodoLista actual = primerNodo;

        // Recorrer la lista y aumentar el tamaño por cada nodo encontrado
        while (actual != null) {
            tamaño++;
            // Mover al siguiente nodo en la lista
            actual = actual.getSiguiente();
        }

        // Devolver el tamaño calculado
        return tamaño;
    }
}
