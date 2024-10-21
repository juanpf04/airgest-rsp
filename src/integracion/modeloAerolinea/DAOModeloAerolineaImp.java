package integracion.modeloAerolinea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;

public class DAOModeloAerolineaImp implements DAOModeloAerolinea {

	public boolean vincular(int idModelo, int idAerolinea) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("INSERT INTO aerolinea_modelo (Id_Aerolinea, Id_Modelo) VALUES (?, ?);");
			ps.setInt(1, idAerolinea);
			ps.setInt(2, idModelo);
			
			int filas = ps.executeUpdate();
			boolean insertado = filas == 1 ? true : false;
			
			ps.close();
			
			return insertado;
			
		} catch(Exception e){
			return false;
		}
	}

	public boolean desvincular(int idModelo, int idAerolinea) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("DELETE FROM aerolinea_modelo WHERE Id_Aerolinea = ? AND Id_Modelo = ?;");
			ps.setInt(1, idAerolinea);
			ps.setInt(2, idModelo);
			
			int filas = ps.executeUpdate();
			boolean borrado = filas == 1 ? true : false;
			
			ps.close();
			
			return borrado;
			
		} catch(Exception e){
			return false;
		}
	}

	public boolean comprobarVinculacion(int idModelo, int idAerolinea) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM aerolinea_modelo WHERE Id_Aerolinea = ? AND Id_Modelo = ? FOR UPDATE;");
			ps.setInt(1, idAerolinea);
			ps.setInt(2, idModelo);
			
			ResultSet rs = ps.executeQuery();
			boolean vinculados = rs.next();
			
			rs.close();
			ps.close();
			
			return vinculados;
		} catch(Exception e){
			return false;
		}
	}
}