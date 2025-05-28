package model.DAO;

import model.Promotor;
import model.Conection.SqlDB;
import model.enums.TipoPromotor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromotorDAO {
    public void create(Promotor promotor) {
        String sql = "INSERT INTO promotor (nombre, tipo, celular, correo) VALUES (?, ?, ?, ?)";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, promotor.getNombre());
            stmt.setString(2, promotor.getTipo().name());
            stmt.setString(3, promotor.getCelular());
            stmt.setString(4, promotor.getCorreo());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    promotor.setPromotorID(rs.getInt(1));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Promotor> readAll() {
        List<Promotor> promotores = new ArrayList<>();
        String sql = "SELECT * FROM promotor";
        try (Connection conn = SqlDB.getCo().getCnn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Promotor p = new Promotor();
                p.setPromotorID(rs.getInt("id_promotor"));
                p.setNombre(rs.getString("nombre"));
                p.setTipo(TipoPromotor.valueOf(rs.getString("tipo")));
                p.setCelular(rs.getString("celular"));
                p.setCorreo(rs.getString("correo"));
                promotores.add(p);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return promotores;
    }

    public void update(Promotor promotor) {
        String sql = "UPDATE promotor SET nombre = ?, tipo = ?, celular = ?, correo = ? WHERE id_promotor = ?";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, promotor.getNombre());
            stmt.setString(2, promotor.getTipo().name());
            stmt.setString(3, promotor.getCelular());
            stmt.setString(4, promotor.getCorreo());
            stmt.setInt(5, promotor.getPromotorID());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idPromotor) {
        String sql = "DELETE FROM promotor WHERE id_promotor = ?";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPromotor);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}