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
    
    public void mostrarPuntuaciones()
    {
        NodoLista actual = primerNodo;

        if (actual == null) {
//            JOptionPane.showMessageDialog(null, "No hay puntuaciones registradas.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("Puntuaciones:\n");

        while (actual != null) {
            Puntuacion puntuacion = actual.getPuntuacion();
            mensaje.append(puntuacion.getNombre()).append(": ").append(puntuacion.getPuntuacion()).append("\n");
            actual = actual.getSiguiente();
        }

        JOptionPane.showMessageDialog(null, mensaje.toString());
    }
}
