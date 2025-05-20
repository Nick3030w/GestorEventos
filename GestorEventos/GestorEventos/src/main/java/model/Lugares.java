
package model;


public class Lugares {
    
    private int lugarID;
    private String nombre;
    private String direccion;
    private double capacidad;

    public Lugares() {
    }

    public int getLugarID() {
        return lugarID;
    }

    public void setLugarID(int lugarID) {
        this.lugarID = lugarID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Lugares{" + "lugarID=" + lugarID + ", nombre=" + nombre + ", direccion=" + direccion + ", capacidad=" + capacidad + '}';
    }
    
    
    
}
