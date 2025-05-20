package model.Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlDB {

    private static SqlDB co = null;
    private Connection cnn = null;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/gestioneventos";
    private final String user = "root";
    private final String pass = "";

    private SqlDB() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        cnn = DriverManager.getConnection(url, user, pass);
    }

    public static synchronized SqlDB getCo() throws ClassNotFoundException, SQLException {
        if (co == null) {
            co = new SqlDB();
        }
        return co;
    }

    public Connection getCnn() {
        return cnn;
    }
    
    public void closeCnn(){
        if(cnn!=null){
            try {
                cnn.close();
                System.out.println("Cierra conexion");
            } catch (SQLException ex) {
                
                
                Logger.getLogger(SqlDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
