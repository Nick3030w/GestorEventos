
package model;


public class Evento_Promotor {
    
    private int eventoID;
    private int promotorID;

    public Evento_Promotor() {
    }

    public int getEventoID() {
        return eventoID;
    }

    public void setEventoID(int eventoID) {
        this.eventoID = eventoID;
    }

    public int getPromotorID() {
        return promotorID;
    }

    public void setPromotorID(int promotorID) {
        this.promotorID = promotorID;
    }

    @Override
    public String toString() {
        return "Evento_Promotor{" + "eventoID=" + eventoID + ", promotorID=" + promotorID + '}';
    }
    
    
    
}
