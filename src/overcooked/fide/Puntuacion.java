
package overcooked.fide;


public class Puntuacion 
{
    private int puntuacion_jugador;
    private Jugador jugador;

    public Puntuacion() 
    {
        this.puntuacion_jugador = 0;
        this.jugador = null;
    }

    public Puntuacion(int puntuacion_jugador, Jugador jugador) 
    {
        this.puntuacion_jugador = puntuacion_jugador;
        this.jugador = jugador;
    }

    public int getPuntuacionJugador() 
    {
        return puntuacion_jugador;
    }

    public void setPuntuacionJugador(int puntuacion_jugador) 
    {
        this.puntuacion_jugador = puntuacion_jugador;
    }

    public Jugador getJugador() 
    {
        return jugador;
    }

    public void setJugador(Jugador jugador) 
    {
        this.jugador = jugador;
    }
}

