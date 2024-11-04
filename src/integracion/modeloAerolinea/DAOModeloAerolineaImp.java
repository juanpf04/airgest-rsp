package integracion.modeloAerolinea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import integracion.Queries;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;

public class DAOModeloAerolineaImp implements DAOModeloAerolinea {

	public boolean vincular(int idModelo, int idAerolinea) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.vincularModeloAerolinea);
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
			PreparedStatement ps = con.prepareStatement(Queries.desvincularModeloAerolinea);
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
			PreparedStatement ps = con.prepareStatement(Queries.comprobarvinculacion);
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
	
	public boolean comprobarVinculacionAerolinea(int id){
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.comprobarvinculacionAerolinea);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			boolean vinculados = rs.next();
			
			rs.close();
			ps.close();
			
			return vinculados;
		} catch(Exception e){
			return false;
		}
	}
	
	public boolean comprobarVinculacionModelo(int id){
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.comprobarvinculacionModelo);
			ps.setInt(1, id);
			
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