package model.DAO;

import model.Lugares;
import model.Conection.SqlDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LugaresDAO {
    public void create(Lugares lugar) {
        String sql = "INSERT INTO lugares (nombre, direccion, capacidad) VALUES (?, ?, ?)";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, lugar.getNombre());
            stmt.setString(2, lugar.getDireccion());
            stmt.setDouble(3, lugar.getCapacidad());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    lugar.setLugarID(rs.getInt(1));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Lugares> readAll() {
        List<Lugares> lugares = new ArrayList<>();
        String sql = "SELECT * FROM lugares";
        try (Connection conn = SqlDB.getCo().getCnn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Lugares l = new Lugares();
                l.setLugarID(rs.getInt("id_lugar"));
                l.setNombre(rs.getString("nombre"));
                l.setDireccion(rs.getString("direccion"));
                l.setCapacidad(rs.getInt("capacidad"));
                lugares.add(l);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lugares;
    }

    public void update(Lugares lugar) {
        String sql = "UPDATE lugares SET nombre = ?, direccion = ?, capacidad = ? WHERE id_lugar = ?";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lugar.getNombre());
            stmt.setString(2, lugar.getDireccion());
            stmt.setDouble(3, lugar.getCapacidad());
            stmt.setInt(4, lugar.getLugarID());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idLugar) {
        String sql = "DELETE FROM lugares WHERE id_lugar = ?";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLugar);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}