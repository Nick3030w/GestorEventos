
package model;

import model.enums.EstadoTicket;
import model.enums.TipoTicket;


public class Ticket {
    
    private int ticketID;
    private int clienteID;
    private int eventoID;
    private TipoTicket tipo;
    private double precio;
    private EstadoTicket estado;

    public Ticket() {
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public int getEventoID() {
        return eventoID;
    }

    public void setEventoID(int eventoID) {
        this.eventoID = eventoID;
    }

    public TipoTicket getTipo() {
        return tipo;
    }

    public void setTipo(TipoTicket tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketID=" + ticketID + ", clienteID=" + clienteID + ", eventoID=" + eventoID + ", tipo=" + tipo + ", precio=" + precio + ", estado=" + estado + '}';
    }
    
    
    
    
    
}
