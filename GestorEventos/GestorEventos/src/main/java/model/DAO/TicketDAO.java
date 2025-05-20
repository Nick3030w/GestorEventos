
package model.DAO;

import model.Conection.SqlDB;
import model.Ticket;
import model.enums.EstadoTicket;
import model.enums.TipoTicket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketDAO {

    private SqlDB cnx;

    public TicketDAO() {
        try {
            cnx = SqlDB.getCo();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Ticket> readAll() {
        ArrayList<Ticket> lista = new ArrayList<>();
        try {
            String SQL_READ_ALL = "SELECT * FROM ticket";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_READ_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ticket t = new Ticket();
                t.setTicketID(rs.getInt("id_ticket"));
                t.setClienteID(rs.getInt("id_cliente"));
                t.setEventoID(rs.getInt("id_evento"));
                t.setTipo(TipoTicket.valueOf(rs.getString("tipo").replace(" ", "_")));
                t.setPrecio(rs.getDouble("precio"));
                t.setEstado(EstadoTicket.valueOf(rs.getString("estado").replace(" ", "_")));
                lista.add(t);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean insert(Ticket t) {
        try {
            String SQL_INSERT = "INSERT INTO ticket (id_cliente, id_evento, tipo, precio, estado) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_INSERT);
            ps.setInt(1, t.getClienteID());
            ps.setInt(2, t.getEventoID());
            ps.setString(3, t.getTipo().name());
            ps.setDouble(4, t.getPrecio());
            ps.setString(5, t.getEstado().name());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(Ticket t) {
        try {
            String SQL_UPDATE = "UPDATE ticket SET id_cliente = ?, id_evento = ?, tipo = ?, precio = ?, estado = ? WHERE id_ticket = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1, t.getClienteID());
            ps.setInt(2, t.getEventoID());
            ps.setString(3, t.getTipo().name());
            ps.setDouble(4, t.getPrecio());
            ps.setString(5, t.getEstado().name());
            ps.setInt(6, t.getTicketID());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            String SQL_DELETE = "DELETE FROM ticket WHERE id_ticket = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
