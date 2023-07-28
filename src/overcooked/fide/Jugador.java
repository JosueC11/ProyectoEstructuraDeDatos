/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package overcooked.fide;

/**
 *
 * @author Josuu
 */
public class Jugador {
    
    private String nombre;
    private String genero;
    private Boolean identificado;

    public Jugador() {
       
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
