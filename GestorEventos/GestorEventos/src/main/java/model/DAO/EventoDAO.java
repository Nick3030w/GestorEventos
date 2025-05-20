
package model.DAO;

import model.Conection.SqlDB;
import model.Evento;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventoDAO {

    private SqlDB cnx;

    public EventoDAO() {
        try {
            cnx = SqlDB.getCo();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Evento> readAll() {
        ArrayList<Evento> lista = new ArrayList<>();
        try {
            String SQL_READ_ALL = "SELECT * FROM evento";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_READ_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Evento e = new Evento();
                e.setEventoID(rs.getInt("id_evento"));
                e.setLugarID(rs.getInt("id_lugar"));
                e.setNombreEvento(rs.getString("nombre_evento"));
                e.setFecha(rs.getDate("fecha"));
                lista.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean insert(Evento e) {
        try {
            String SQL_INSERT = "INSERT INTO evento (id_lugar, nombre_evento, fecha) VALUES (?, ?, ?)";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_INSERT);
            ps.setInt(1, e.getLugarID());
            ps.setString(2, e.getNombreEvento());
            ps.setDate(3, new java.sql.Date(e.getFecha().getTime()));

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(Evento e) {
        try {
            String SQL_UPDATE = "UPDATE evento SET id_lugar = ?, nombre_evento = ?, fecha = ? WHERE id_evento = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1, e.getLugarID());
            ps.setString(2, e.getNombreEvento());
            ps.setDate(3, new java.sql.Date(e.getFecha().getTime()));
            ps.setInt(4, e.getEventoID());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            String SQL_DELETE = "DELETE FROM evento WHERE id_evento = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
