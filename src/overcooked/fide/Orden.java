/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package overcooked.fide;

/**
 *
 * @author Josuu
 */
public class Orden {
    
    private String nombre;
    private Boolean pan;
    private Boolean carne;
    private Boolean queso;
    private Boolean lechuga;
    private int cantIngredientes;
    private int id;

    public Orden(String nombre, Boolean pan, Boolean carne, Boolean queso, Boolean lechuga, int cantIngredientes, int id) {
        this.nombre = nombre;
        this.pan = pan;
        this.carne = carne;
        this.queso = queso;
        this.lechuga = lechuga;
        this.cantIngredientes = cantIngredientes;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getPan() {
        return pan;
    }

    public Boolean getCarne() {
        return carne;
    }

    public Boolean getQueso() {
        return queso;
    }

    public Boolean getLechuga() {
        return lechuga;
    }

    public int getCantIngredientes() {
        return cantIngredientes;
    }

    public int getId() {
        return id;
    }  
}
