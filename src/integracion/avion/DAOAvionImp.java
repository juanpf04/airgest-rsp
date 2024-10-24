package integracion.avion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import integracion.Querys;

import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.avion.TAvion;

public class DAOAvionImp implements DAOAvion {

	@Override
	public int altaAvion(TAvion tAvion) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.altaAvion, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tAvion.getNombre());
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
	
	

	@Override
	public boolean bajaAvion(int id) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.bajaAvion);
			ps.setInt(1, id);
			
			int filas = ps.executeUpdate();
			boolean eliminado = filas == 1;
			
			ps.close();
			
			return eliminado;
		} catch(Exception e){
			return false;
		}
	}
	
	@Override
	public boolean modificarAvion(TAvion tAvion) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.modificarAvion);
			ps.setString(1, tAvion.getNombre());
			ps.setBoolean(2, tAvion.getActivo());
			ps.setInt(3, tAvion.getId());
			
			int filas = ps.executeUpdate();
			boolean modificado = filas == 1;
			
			ps.close();
			
			return modificado;
		} catch(Exception e){
			return false;
		}
	}
	

	@Override
	public TAvion consultarAvionPorId(int id) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionPorId);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			TAvion tav = null;
			if (rs.next())
				tav = new TAvion(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7),
						rs.getInt(8), rs.getInt(9)); 
			
			rs.close();
			ps.close();
			
			return tav;
			
		} catch(Exception e){
			return null;
		}
	}

	

	@Override
	public TAvion consultarAvionPorMatricula(String matricula) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionPorMatricula);
			ps.setString(1, matricula);
			
			ResultSet rs = ps.executeQuery();
			
			TAvion tav = null;
			
			if (rs.next())
				tav = new TAvion(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7),
						rs.getInt(8), rs.getInt(9)); 
			
			
			rs.close();
			ps.close();
			
			return tav;
			
		} catch(Exception e){
			return null;
		}
	}
	
	
	@Override
	public List<TAvion> consultarTodosAviones() {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarTodosAviones);
			
			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TAvion(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7),
						rs.getInt(8), rs.getInt(9)));
			}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TAvion>();
		}
	}
	
	public List<TAvion> consultarAvionesPorModelo(int idModelo) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesPorModelo);
			ps.setInt(1, idModelo);
			
			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TAvion(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7),
						rs.getInt(8), rs.getInt(9)));
			}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TAvion>();
		}
	}

	public List<TAvion> consultarAvionesActivosPorModelo(int idModelo) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesActivosPorModelo);
			ps.setInt(1, idModelo);
			
			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TAvion(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7),
						rs.getInt(8), rs.getInt(9)));
			}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TAvion>();
		}
	}

	@Override
	public List<TAvion> consultarAvionesPorAerolinea(int idAerolinea) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesPorAerolinea);
			ps.setInt(1, idAerolinea);
			
			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TAvion(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7),
						rs.getInt(8), rs.getInt(9)));			
				}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TAvion>();
		}
	}

	@Override
	public List<TAvion> consultarAvionesActivosPorAerolinea(int idAerolinea) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesActivosPorAerolinea);
			ps.setInt(1, idAerolinea);
			
			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TAvion(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7),
						rs.getInt(8), rs.getInt(9)));			
				}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TAvion>();
		}
	}

	@Override
	public List<TAvion> consultarAvionesPorHangar(int idHangar) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesPorHangar);
			ps.setInt(1, idHangar);
			
			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TAvion(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7),
						rs.getInt(8), rs.getInt(9)));			
				}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TAvion>();
		}
	}

	@Override
	public List<TAvion> consultarAvionesActivosPorHangar(int idHangar) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesActivosPorHangar);
			ps.setInt(1, idHangar);
			
			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TAvion(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7),
						rs.getInt(8), rs.getInt(9)));			
				}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TAvion>();
		}
	}
	
	
	public List<TAvion> consultarAvionesDeAerolineaPorHangar(int id_aerolinea, int id_hangar) {
	    try {
	        Transaction t = TransactionManager.getInstance().getTransaccion();
	        Connection con = (Connection) t.getResource();
	        
	        
	        PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesDeAerolineaPorHangar);
	        ps.setInt(1, id_aerolinea);
	        ps.setInt(2, id_hangar);
	        
	       
	        ResultSet rs = ps.executeQuery();
	        List<TAvion> lista = new ArrayList<>();
	        
	        
	        while (rs.next()) {
	            lista.add(new TAvion(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), 
	                                 rs.getInt(7), rs.getInt(8), rs.getInt(9)));
	        }
	        
	        rs.close();
	        ps.close();
	        
	        return lista;
	        
	    } catch (Exception e) {
	        return new ArrayList<TAvion>();  // Si ocurre un error, devolvemos una lista vacía
	    }
	}

}