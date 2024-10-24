package integracion.aerolinea;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import integracion.Querys;
import negocio.aerolinea.TAerolinea;

public class DAOAerolineaImp implements DAOAerolinea {

	public int altaAerolinea(TAerolinea tAerolinea) {
		
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.altaAerolinea);
			ps.setString(1, tAerolinea.getNombre());
			ps.setBoolean(2, true);
			
			int filas = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			int id = filas == 1 && rs.next() ? rs.getInt(1) : -1;
			
			rs.close();
			ps.close();
			
			return id;
			
		} catch (Exception e) {
			return -1;
		}
	}
	
	public boolean bajaAerolinea(int id) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.bajaAerolinea);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			
			int filas = ps.executeUpdate();
			boolean eliminado = filas == 1;
			
			ps.close();
			
			return eliminado;
		} catch(Exception e){
			return false;
		}
	}

	public boolean modificarAerolinea(TAerolinea tAerolinea) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.modificarAerolinea);
			ps.setString(1, tAerolinea.getNombre());
			ps.setBoolean(2, tAerolinea.getActivo());
			ps.setInt(3, tAerolinea.getId());
			
			int filas = ps.executeUpdate();
			boolean modificado = filas == 1;
			
			ps.close();
			
			return modificado;
		} catch(Exception e){
			return false;
		}
	}

	

	public TAerolinea consultarAerolineaPorId(int idAerolinea) {
		
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAerolineaPorId);
			ps.setInt(1, idAerolinea);
			
			ResultSet rs = ps.executeQuery();
			
			TAerolinea ta = null;
			if (rs.next())
				ta = new TAerolinea(rs.getInt(1), rs.getString(2), rs.getBoolean(3)); 
			
			rs.close();
			ps.close();
			
			return ta;
			
		} catch(Exception e){
			return null;
		}
		
	}

	public TAerolinea consultarAerolineaPorNombre(String nombre) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAerolineaPorNombre);
			ps.setString(1, nombre);
			
			ResultSet rs = ps.executeQuery();
			
			TAerolinea ta = null;
			if (rs.next())
				ta = new TAerolinea(rs.getInt(1), rs.getString(2), rs.getBoolean(3)); 
			
			rs.close();
			ps.close();
			
			return ta;
			
		} catch(Exception e){
			return null;
		}
	}
	
	public List<TAerolinea> consultarTodasAerolineas() {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarTodasAerolineas);
			
			ResultSet rs = ps.executeQuery();
			List<TAerolinea> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TAerolinea(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
			}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TAerolinea>();
		}
	}

	@Override
	public List<TAerolinea> consultarAerolineasPorModelo(int id_modelo) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAerolineasPorModelo);
			ps.setInt(1, id_modelo);
			
			ResultSet rs = ps.executeQuery();
			List<TAerolinea> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TAerolinea(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
			}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TAerolinea>();
		}
	}
}