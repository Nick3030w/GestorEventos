package model.DAO;

import model.Conection.SqlDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;

public class ClienteDAO {

    private SqlDB cnx;

    public ClienteDAO() {
        try {
            cnx = SqlDB.getCo();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error al conectar con la base de datos");
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Cliente> readAll() {
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            String SQL_READ_ALL = "SELECT * FROM cliente";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_READ_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente dto = new Cliente();
                dto.setClienteID(rs.getInt("id_cliente"));
                dto.setPersonaID(rs.getInt("id_persona"));

                lista.add(dto);
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar readAll");
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

   
    public boolean insertar(Cliente c) {
        String SQL_INSERT = "INSERT INTO cliente (id_persona) VALUES (?)";
        try {
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_INSERT);
            ps.setInt(1, c.getPersonaID());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al insertar cliente");
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean eliminar(int idCliente) {
        String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente = ?";
        try {
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, idCliente);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar cliente");
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean actualizar(Cliente c) {
        String SQL_UPDATE = "UPDATE cliente SET id_persona = ? WHERE id_cliente = ?";
        try {
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1, c.getPersonaID());
            ps.setInt(2, c.getClienteID());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar cliente");
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
