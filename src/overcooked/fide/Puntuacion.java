
package overcooked.fide;


public class Puntuacion 
{
    private Jugador jugador;
    private int puntuacion;

    public Puntuacion(Jugador jugador, int puntuacion) 
    {
        this.jugador = jugador;
        this.puntuacion = puntuacion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}

