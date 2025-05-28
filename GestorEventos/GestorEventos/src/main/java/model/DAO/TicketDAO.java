
package model.DAO;

import model.Ticket;
import model.Conection.SqlDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    public void create(Ticket ticket) {
        String sql = "INSERT INTO ticket (id_cliente, id_evento, tipo, precio, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, ticket.getClienteID());
            stmt.setInt(2, ticket.getEventoID());
            stmt.setString(3, ticket.getTipo().name());
            stmt.setFloat(4, ticket.getPrecio());
            stmt.setString(5, ticket.getEstado().name());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    ticket.setTicketID(rs.getInt(1));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> readAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket";
        try (Connection conn = SqlDB.getCo().getCnn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Ticket t = new Ticket();
                t.setTicketID(rs.getInt("id_ticket"));
                t.setClienteID(rs.getInt("id_cliente"));
                t.setEventoID(rs.getInt("id_evento"));
                t.setTipo(Ticket.Tipo.valueOf(rs.getString("tipo")));
                t.setPrecio(rs.getFloat("precio"));
                t.setEstado(Ticket.Estado.valueOf(rs.getString("estado")));
                tickets.add(t);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public void update(Ticket ticket) {
        String sql = "UPDATE ticket SET id_cliente = ?, id_evento = ?, tipo = ?, precio = ?, estado = ? WHERE id_ticket = ?";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getClienteID());
            stmt.setInt(2, ticket.getEventoID());
            stmt.setString(3, ticket.getTipo().name());
            stmt.setFloat(4, ticket.getPrecio());
            stmt.setString(5, ticket.getEstado().name());
            stmt.setInt(6, ticket.getTicketID());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idTicket) {
        String sql = "DELETE FROM ticket WHERE id_ticket = ?";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTicket);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}