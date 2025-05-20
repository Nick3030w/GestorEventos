
package model;

import java.util.Date;


public class Evento {
    
    private int eventoID;
    private int lugarID;
    private String nombreEvento;
    private Date fecha;

    public Evento() {
    }

    public int getEventoID() {
        return eventoID;
    }

    public void setEventoID(int eventoID) {
        this.eventoID = eventoID;
    }

    public int getLugarID() {
        return lugarID;
    }

    public void setLugarID(int lugarID) {
        this.lugarID = lugarID;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Evento{" + "eventoID=" + eventoID + ", lugarID=" + lugarID + ", nombreEvento=" + nombreEvento + ", fecha=" + fecha + '}';
    }
    
    
}
