
package estructuras;

import vistas.PantallaInicio;
import javax.swing.JOptionPane;

public class Main{

    public static void main(String[] args)
    {
        ListaJugador listaJugadores = new ListaJugador();
        ListaPuntuaciones listaPuntuaciones = new ListaPuntuaciones();
        
        Jugador jugador_predeterminado = new Jugador("Jugador1", 
                                                    "M", true);
        listaJugadores.agregarJugador(jugador_predeterminado);

        PantallaInicio verMenu = new PantallaInicio(listaJugadores, listaPuntuaciones); 
        verMenu.setVisible(true); 
        verMenu.setLocationRelativeTo(null);
    }
}
