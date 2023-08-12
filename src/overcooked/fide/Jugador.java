
package overcooked.fide;


public class Jugador 
{
    
    private String nombre;
    private String genero;
    private Boolean identificado;

    public Jugador(String nombre, String genero, Boolean identificado) {
        this.nombre = nombre;
        this.genero = genero;
        this.identificado = identificado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Boolean getIdentificado() {
        return identificado;
    }

    public void setIdentificado(Boolean identificado) {
        this.identificado = identificado;
    }

}


