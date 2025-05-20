package model.DAO;

import model.Conection.SqlDB;
import model.Evento_Promotor;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Evento_PromotorDAO {

    private SqlDB cnx;

    public Evento_PromotorDAO() {
        try {
            cnx = SqlDB.getCo();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Evento_PromotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Evento_Promotor> readAll() {
        ArrayList<Evento_Promotor> lista = new ArrayList<>();

        try {
            String SQL_READ_ALL = "SELECT * FROM evento_promotor";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_READ_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Evento_Promotor ep = new Evento_Promotor();
                ep.setEventoID(rs.getInt("id_evento"));
                ep.setPromotorID(rs.getInt("id_promotor"));
                lista.add(ep);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Evento_PromotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean insert(Evento_Promotor ep) {
        try {
            String SQL_INSERT = "INSERT INTO evento_promotor (id_evento, id_promotor) VALUES (?, ?)";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_INSERT);
            ps.setInt(1, ep.getEventoID());
            ps.setInt(2, ep.getPromotorID());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(Evento_PromotorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int eventoID, int promotorID) {
        try {
            String SQL_DELETE = "DELETE FROM evento_promotor WHERE id_evento = ? AND id_promotor = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, eventoID);
            ps.setInt(2, promotorID);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(Evento_PromotorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
