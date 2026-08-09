
package integracion.transacciones;

import java.sql.Connection;
import java.sql.SQLException;

import integracion.conexion.FactoriaConexion;

public class TransactionMySQL implements Transaction {

	Connection connection;

	public void start() {

		try {
			connection = FactoriaConexion.getInstance().getConnection();
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