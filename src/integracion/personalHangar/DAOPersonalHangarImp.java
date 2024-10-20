package integracion.personalHangar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import integracion.Querys;
import integracion.transacciones.TransactionManager;

public class DAOPersonalHangarImp implements DAOPersonalHangar {

	@Override
	public boolean vincular(int idPersonal, int idHangar) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.vincularPersonalHangar);
			ps.setInt(1, idPersonal);
			ps.setInt(2, idHangar);
			
			int filas = ps.executeUpdate();
			boolean vinculado = filas == 1 ? true : false;
			
			ps.close();

			return vinculado;	
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean desvincular(int idPersonal, int idHangar) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.desvincularPersonalHangar);
			ps.setInt(1, idPersonal);
			ps.setInt(2, idHangar);
			
			int filas = ps.executeUpdate();
			boolean desvinculado = filas == 1 ? true : false;
			
			ps.close();

			return desvinculado;	
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean comprobarVinculacion(int idPersonal, int idHangar) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.comprobarvinculacionPersonalHangar);
			ps.setInt(1, idPersonal);
			ps.setInt(2, idHangar);
			
			ResultSet rs = ps.executeQuery();
			boolean vinculado = false;
			if(rs.next()){
				vinculado = rs.getInt("NUM") == 1;
			}
			
			ps.close();

			return vinculado;	
		} catch (SQLException e) {
			return false;
		}
	}

}