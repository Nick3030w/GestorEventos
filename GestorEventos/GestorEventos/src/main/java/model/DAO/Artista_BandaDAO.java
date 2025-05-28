
package model.DAO;

import model.Artista_Banda;
import model.Conection.SqlDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Artista_BandaDAO {
    public void create(Artista_Banda artista) {
        String sql = "INSERT INTO artista_banda (nombre, genero, integrantes) VALUES (?, ?, ?)";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, artista.getNombre());
            stmt.setString(2, artista.getGenero());
            stmt.setString(3, artista.getIntegrantes());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    artista.setArtistaID(rs.getInt(1));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Artista_Banda> readAll() {
        List<Artista_Banda> artistas = new ArrayList<>();
        String sql = "SELECT * FROM artista_banda";
        try (Connection conn = SqlDB.getCo().getCnn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Artista_Banda a = new Artista_Banda();
                a.setArtistaID(rs.getInt("id_artista"));
                a.setNombre(rs.getString("nombre"));
                a.setGenero(rs.getString("genero"));
                a.setIntegrantes(rs.getString("integrantes"));
                artistas.add(a);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return artistas;
    }

    public void update(Artista_Banda artista) {
        String sql = "UPDATE artista_banda SET nombre = ?, genero = ?, integrantes = ? WHERE id_artista = ?";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, artista.getNombre());
            stmt.setString(2, artista.getGenero());
            stmt.setString(3, artista.getIntegrantes());
            stmt.setInt(4, artista.getArtistaID());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idArtista) {
        String sql = "DELETE FROM artista_banda WHERE id_artista = ?";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idArtista);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}