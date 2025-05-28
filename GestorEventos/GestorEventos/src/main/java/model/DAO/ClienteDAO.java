package model.DAO;

import model.Cliente;
import model.Conection.SqlDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public void create(Cliente cliente) {
        String sql = "INSERT INTO cliente (id_persona) VALUES (?)";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, cliente.getPersonaID());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setClienteID(rs.getInt(1));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> readAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = SqlDB.getCo().getCnn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setClienteID(rs.getInt("id_cliente"));
                c.setPersonaID(rs.getInt("id_persona"));
                clientes.add(c);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE cliente SET id_persona = ? WHERE id_cliente = ?";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getPersonaID());
            stmt.setInt(2, cliente.getClienteID());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int idCliente) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (Connection conn = SqlDB.getCo().getCnn();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}