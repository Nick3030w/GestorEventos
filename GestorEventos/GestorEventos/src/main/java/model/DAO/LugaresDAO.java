package model.DAO;

import model.Conection.SqlDB;
import model.Lugares;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LugaresDAO {

    private SqlDB cnx;

    public LugaresDAO() {
        try {
            cnx = SqlDB.getCo();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LugaresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Lugares> readAll() {
        ArrayList<Lugares> lista = new ArrayList<>();
        try {
            String SQL_READ_ALL = "SELECT * FROM lugares";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_READ_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Lugares lugar = new Lugares();
                lugar.setLugarID(rs.getInt("id_lugar"));
                lugar.setNombre(rs.getString("nombre"));
                lugar.setDireccion(rs.getString("direccion"));
                lugar.setCapacidad(rs.getDouble("capacidad"));
                lista.add(lugar);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LugaresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean insert(Lugares lugar) {
        try {
            String SQL_INSERT = "INSERT INTO lugares (nombre, direccion, capacidad) VALUES (?, ?, ?)";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1, lugar.getNombre());
            ps.setString(2, lugar.getDireccion());
            ps.setDouble(3, lugar.getCapacidad());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(LugaresDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(Lugares lugar) {
        try {
            String SQL_UPDATE = "UPDATE lugares SET nombre = ?, direccion = ?, capacidad = ? WHERE id_lugar = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1, lugar.getNombre());
            ps.setString(2, lugar.getDireccion());
            ps.setDouble(3, lugar.getCapacidad());
            ps.setInt(4, lugar.getLugarID());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(LugaresDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int lugarID) {
        try {
            String SQL_DELETE = "DELETE FROM lugares WHERE id_lugar = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, lugarID);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(LugaresDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
