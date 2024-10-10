
package integracion.transacciones;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionMySQL implements Transaction {
	
	Connection connection;
	
	public void start() {
		
		// Conectar
		
		try {
			// connection = conexion que genera la clase conectar
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			
			throw new RuntimeException("No se ha podido iniciar conexión");
		}
	}

	public void commit() {
		try {
			connection.commit();
			connection.close();
			TransactionManager.getInstance().eliminarTransaccion();
		} catch (SQLException e) {
			throw new RuntimeException("No se ha podido hacer commit");
		}
	}

	public void rollback() {
		try {
			connection.rollback();
			connection.close();
			TransactionManager.getInstance().eliminarTransaccion();
		} catch (SQLException e) {
			throw new RuntimeException("No se ha podido hacer rollback");
		}
	}

	public Object getResource() {
		
		return connection;
		
	}
}