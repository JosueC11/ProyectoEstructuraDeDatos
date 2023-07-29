
package overcooked.fide;


public class Jugador 
{
    
    private String nombre;
    private String genero;
    private Boolean identificado;

    //Se crea una instancia estática del jugador para llamarlo cuando sea necesario
    private static Jugador jugadorInstance = null;
    
    private Jugador() 
    {
       
    }

    // Se crea el metodo para llamar al usuario registrado
    
    public static Jugador getInstance() 
    {
        if (jugadorInstance == null) 
        {
            jugadorInstance = new Jugador();
        }
        return jugadorInstance;
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


