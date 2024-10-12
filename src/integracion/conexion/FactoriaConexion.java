package integracion.conexion;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class FactoriaConexion  {

	private static FactoriaConexion factoriaConexion;
	
	public abstract Connection getConnection() throws SQLException;
	
	public synchronized static FactoriaConexion getInstance(){
		if(factoriaConexion == null){
			factoriaConexion = new FactoriaConexionImp();
		}
		return factoriaConexion;
	}
}
