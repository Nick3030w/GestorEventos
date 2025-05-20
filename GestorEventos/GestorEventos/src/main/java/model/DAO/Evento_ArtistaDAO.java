package model.DAO;

import model.Conection.SqlDB;
import model.Evento_Artista;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Evento_ArtistaDAO {

    private SqlDB cnx;

    public Evento_ArtistaDAO() {
        try {
            cnx = SqlDB.getCo();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Evento_ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Evento_Artista> readAll() {
        ArrayList<Evento_Artista> lista = new ArrayList<>();

        try {
            String SQL_READ_ALL = "SELECT * FROM evento_artista";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_READ_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Evento_Artista ea = new Evento_Artista();
                ea.setEventoID(rs.getInt("id_evento"));
                ea.setArtistaID(rs.getInt("id_artista"));
                lista.add(ea);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Evento_ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean insert(Evento_Artista ea) {
        try {
            String SQL_INSERT = "INSERT INTO evento_artista (id_evento, id_artista) VALUES (?, ?)";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_INSERT);
            ps.setInt(1, ea.getEventoID());
            ps.setInt(2, ea.getArtistaID());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(Evento_ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int eventoID, int artistaID) {
        try {
            String SQL_DELETE = "DELETE FROM evento_artista WHERE id_evento = ? AND id_artista = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, eventoID);
            ps.setInt(2, artistaID);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(Evento_ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
