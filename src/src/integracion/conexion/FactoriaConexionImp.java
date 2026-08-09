package integracion.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoriaConexionImp extends FactoriaConexion {
	private static final String DB = "ms";
	private static final String DDBB = "jdbc:mysql://localhost:3306/" + DB;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DDBB, USERNAME, PASSWORD);

	}

}
