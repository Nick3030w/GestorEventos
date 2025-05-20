
package model;


public class Evento_Artista {
    
    private int eventoID;
    private int artistaID;

    public Evento_Artista() {
    }

    public int getEventoID() {
        return eventoID;
    }

    public void setEventoID(int eventoID) {
        this.eventoID = eventoID;
    }

    public int getArtistaID() {
        return artistaID;
    }

    public void setArtistaID(int artistaID) {
        this.artistaID = artistaID;
    }

    @Override
    public String toString() {
        return "Evento_Artista{" + "eventoID=" + eventoID + ", artistaID=" + artistaID + '}';
    }
    
    
    
}
