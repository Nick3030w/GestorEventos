
package model;

import model.enums.TipoPromotor;


public class Promotor {
    
    private int promotorID;
    private String nombre;
    private TipoPromotor tipo;
    private String celular;
    private String correo;

    public Promotor() {
    }

    public int getPromotorID() {
        return promotorID;
    }

    public void setPromotorID(int promotorID) {
        this.promotorID = promotorID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoPromotor getTipo() {
        return tipo;
    }

    public void setTipo(TipoPromotor tipo) {
        this.tipo = tipo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Promotor{" + "promotorID=" + promotorID + ", nombre=" + nombre + ", tipo=" + tipo + ", celular=" + celular + ", correo=" + correo + '}';
    }
    
    
    
}
