
package model.DAO;

import model.Evento;
import model.Conection.SqlDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
    public void create(Evento evento) {
        String sql = "INSERT INTO evento (id_lugar, nombre_evento, fecha) VALUES (?, ?, ?)";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, evento.getLugarID());
            stmt.setString(2, evento.getNombreEvento());
            stmt.setDate(3, new java.sql.Date(evento.getFecha().getTime()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    evento.setEventoID(rs.getInt(1));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Evento> readAll() {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM evento";
        try (Connection conn = SqlDB.getCo().getCnn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Evento e = new Evento();
                e.setEventoID(rs.getInt("id_evento"));
                e.setLugarID(rs.getInt("id_lugar"));
                e.setNombreEvento(rs.getString("nombre_evento"));
                e.setFecha(rs.getDate("fecha"));
                eventos.add(e);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    public void update(Evento evento) {
        String sql = "UPDATE evento SET id_lugar = ?, nombre_evento = ?, fecha = ? WHERE id_evento = ?";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, evento.getLugarID());
            stmt.setString(2, evento.getNombreEvento());
            stmt.setDate(3, new java.sql.Date(evento.getFecha().getTime()));
            stmt.setInt(4, evento.getEventoID());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idEvento) {
        String sql = "DELETE FROM evento WHERE id_evento = ?";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEvento);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}