
package overcooked.fide;

import javax.swing.JOptionPane;

public class Main{

    public static void main(String[] args)
    {
        ListaJugador listaJugadores = new ListaJugador();
        ListaPuntuaciones listaPuntuaciones = new ListaPuntuaciones();

        PantallaInicio verMenu = new PantallaInicio(listaJugadores, listaPuntuaciones); 
        verMenu.setVisible(true); 
        verMenu.setLocationRelativeTo(null);
    }
}
