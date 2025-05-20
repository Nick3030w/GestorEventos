package model.DAO;

import model.Conection.SqlDB;
import model.Promotor;
import model.enums.TipoPromotor;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PromotorDAO {

    private SqlDB cnx;

    public PromotorDAO() {
        try {
            cnx = SqlDB.getCo();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PromotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Promotor> readAll() {
        ArrayList<Promotor> lista = new ArrayList<>();
        try {
            String SQL_READ_ALL = "SELECT * FROM promotor";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_READ_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Promotor p = new Promotor();
                p.setPromotorID(rs.getInt("id_promotor"));
                p.setNombre(rs.getString("nombre"));
                p.setTipo(TipoPromotor.valueOf(rs.getString("tipo")));
                p.setCelular(rs.getString("celular"));
                p.setCorreo(rs.getString("correo"));
                lista.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PromotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean insert(Promotor p) {
        try {
            String SQL_INSERT = "INSERT INTO promotor (nombre, tipo, celular, correo) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipo().name());
            ps.setString(3, p.getCelular());
            ps.setString(4, p.getCorreo());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(PromotorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(Promotor p) {
        try {
            String SQL_UPDATE = "UPDATE promotor SET nombre = ?, tipo = ?, celular = ?, correo = ? WHERE id_promotor = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipo().name());
            ps.setString(3, p.getCelular());
            ps.setString(4, p.getCorreo());
            ps.setInt(5, p.getPromotorID());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(PromotorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            String SQL_DELETE = "DELETE FROM promotor WHERE id_promotor = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(PromotorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
