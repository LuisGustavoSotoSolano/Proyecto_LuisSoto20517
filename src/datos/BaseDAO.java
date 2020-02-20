package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO {
    private String cadenaConexion = "jdbc:mysql://localhost/club_nautico?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String usuario = "root";
    private String password = "1234";
    
    protected Connection getConnection() throws SQLException{
        return DriverManager.getConnection(this.cadenaConexion, 
                this.usuario, this.password);
    }
}
