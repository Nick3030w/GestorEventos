package model.DAO;

import model.Conection.SqlDB;
import model.Empleado;
import model.enums.Cargo;
import model.enums.Contrato;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmpleadoDAO {

    private SqlDB cnx;

    public EmpleadoDAO() {
        try {
            cnx = SqlDB.getCo();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error al conectar con la base de datos");
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Empleado> readAll() {
        ArrayList<Empleado> lista = new ArrayList<>();

        try {
            String SQL_READ_ALL = "SELECT * FROM empleado";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_READ_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setEmpleadoID(rs.getInt("id_empleado"));
                emp.setPersonaID(rs.getInt("id_persona"));

                String cargoRaw = rs.getString("cargo");
                if (cargoRaw != null && !cargoRaw.trim().isEmpty()) {
                    emp.setCargo(Cargo.valueOf(cargoRaw.trim()));
                }

                String contratoRaw = rs.getString("contrato");
                if (contratoRaw != null && !contratoRaw.trim().isEmpty()) {
                    emp.setContrato(Contrato.valueOf(contratoRaw.trim()));
                }

                emp.setEps(rs.getString("eps"));
                lista.add(emp);
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar readAll");
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean insert(Empleado emp) {
        try {
            String SQL_INSERT = "INSERT INTO empleado (id_persona, cargo, contrato, eps) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_INSERT);
            ps.setInt(1, emp.getPersonaID());
            ps.setString(2, emp.getCargo().name());
            ps.setString(3, emp.getContrato().name());
            ps.setString(4, emp.getEps());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error al insertar empleado");
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(Empleado emp) {
        try {
            String SQL_UPDATE = "UPDATE empleado SET id_persona = ?, cargo = ?, contrato = ?, eps = ? WHERE id_empleado = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1, emp.getPersonaID());
            ps.setString(2, emp.getCargo().name());
            ps.setString(3, emp.getContrato().name());
            ps.setString(4, emp.getEps());
            ps.setInt(5, emp.getEmpleadoID());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error al actualizar empleado");
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int empleadoID) {
        try {
            String SQL_DELETE = "DELETE FROM empleado WHERE id_empleado = ?";
            PreparedStatement ps = cnx.getCnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, empleadoID);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error al eliminar empleado");
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

