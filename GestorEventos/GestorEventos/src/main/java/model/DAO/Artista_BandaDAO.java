
package model.DAO;

import model.Artista_Banda;
import model.Conection.SqlDB;
import model.enums.BandaGenero;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Artista_BandaDAO {

    private SqlDB cnx;

    public Artista_BandaDAO() {
        try {
            cnx = SqlDB.getCo();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Artista_BandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Artista_Banda> readAll() {
        ArrayList<Artista_Banda> lista = new ArrayList<>();
        try {
            String SQL_READ_ALL = "SELECT * FROM artista_banda";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_READ_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Artista_Banda ab = new Artista_Banda();
                ab.setArtistaID(rs.getInt("id_artista"));
                ab.setNombre(rs.getString("nombre"));

                String generoRaw = rs.getString("genero");
                if (generoRaw != null && !generoRaw.trim().isEmpty()) {
                    ab.setGenero(BandaGenero.valueOf(generoRaw.trim()));
                }

                ab.setIntegrantes(rs.getString("integrantes"));
                lista.add(ab);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Artista_BandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean insert(Artista_Banda ab) {
        try {
            String SQL_INSERT = "INSERT INTO artista_banda (nombre, genero, integrantes) VALUES (?, ?, ?)";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1, ab.getNombre());
            ps.setString(2, ab.getGenero().name());
            ps.setString(3, ab.getIntegrantes());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(Artista_BandaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(Artista_Banda ab) {
        try {
            String SQL_UPDATE = "UPDATE artista_banda SET nombre = ?, genero = ?, integrantes = ? WHERE id_artista = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1, ab.getNombre());
            ps.setString(2, ab.getGenero().name());
            ps.setString(3, ab.getIntegrantes());
            ps.setInt(4, ab.getArtistaID());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(Artista_BandaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int artistaID) {
        try {
            String SQL_DELETE = "DELETE FROM artista_banda WHERE id_artista = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, artistaID);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(Artista_BandaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
