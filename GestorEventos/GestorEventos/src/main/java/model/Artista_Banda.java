
package model;

import model.enums.BandaGenero;


public class Artista_Banda {
    
    private int artistaID;
    private String nombre;
    private BandaGenero genero;
    private String integrantes;

    public Artista_Banda() {
    }

    public int getArtistaID() {
        return artistaID;
    }

    public void setArtistaID(int artistaID) {
        this.artistaID = artistaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BandaGenero getGenero() {
        return genero;
    }

    public void setGenero(BandaGenero genero) {
        this.genero = genero;
    }

    public String getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(String integrantes) {
        this.integrantes = integrantes;
    }

    @Override
    public String toString() {
        return "Artista_Banda{" + "artistaID=" + artistaID + ", nombre=" + nombre + ", genero=" + genero + ", integrantes=" + integrantes + '}';
    }
    
    
    
}
