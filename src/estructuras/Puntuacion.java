package estructuras;


public class Puntuacion 
{
    private String Nombre;
    private int Puntuacion;

    public Puntuacion(String Nombre, int Puntuacion) {
        this.Nombre = Nombre;
        this.Puntuacion = Puntuacion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getPuntuacion() {
        return Puntuacion;
    }

    public void setPuntuacion(int Puntuacion) {
        this.Puntuacion = Puntuacion;
    } 
}

