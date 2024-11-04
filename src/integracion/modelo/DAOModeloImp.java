package integracion.modelo;

import negocio.modelo.TModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integracion.Queries;
import integracion.transacciones.TransactionManager;

public class DAOModeloImp implements DAOModelo {

	public TModelo consultarModeloPorNombre(String nombre) {
		try{
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		
		PreparedStatement ps = con.prepareStatement(Queries.consultar_modelo_por_nombre);
		ps.setString(1, nombre);
		
		ResultSet res = ps.executeQuery();
		
		TModelo t;
		
		if(res.next()) t = new TModelo(res.getInt("id"), nombre, res.getString("motor"), res.getBoolean("activo"));
		else t =  null;
		
		res.close();
		ps.close();

		return t;
		}catch (Exception e){
			return null;
		}
	}

	public int altaModelo(TModelo tModelo) {

		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			
			PreparedStatement ps = con.prepareStatement(Queries.alta_modelo, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tModelo.getNombre());
			ps.setString(2, tModelo.getMotor());
			
			int filasNuevas = ps.executeUpdate();
			ResultSet res = ps.getGeneratedKeys();
			
			int id = filasNuevas == 1 && res.next() ? res.getInt(1) : -1;
			
			res.close();
			ps.close();
			
			return id;
			
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean modificarModelo(TModelo tModelo) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			
			PreparedStatement ps = con.prepareStatement(Queries.modificar_modelo);
			
			ps.setString(1, tModelo.getNombre());
			ps.setString(2, tModelo.getMotor());
			ps.setBoolean(3, tModelo.getActivo());
			ps.setInt(4, tModelo.getId());
			
			int filasNuevas = ps.executeUpdate();
			
			boolean ok = filasNuevas == 1 ? true : false;
			
			ps.close();
			
			return ok;
			
		} catch (Exception e) {
			return false;
		}
	}

	public boolean bajaModelo(int id) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			
			PreparedStatement ps = con.prepareStatement(Queries.baja_modelo);
			
			ps.setInt(1, id);
			
			int filasNuevas = ps.executeUpdate();
			
			boolean ok = filasNuevas == 1 ? true : false;
			
			ps.close();
			
			return ok;
			
		} catch (Exception e) {
			return false;
		}
	}

	public List<TModelo> consultarTodosModelos() {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Queries.consultar_todos_modelos);			
			ResultSet res = ps.executeQuery();
			
			List<TModelo> t = new ArrayList<>();
			
			while(res.next()) t.add(new TModelo(res.getInt("id"), res.getString("nombre"), res.getString("motor"), res.getBoolean("activo")));
			
			res.close();
			ps.close();

			return t;
			}catch (Exception e){
				return new ArrayList<TModelo>();
			}
	}

	public TModelo consultarModeloPorId(int id) {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			
			PreparedStatement ps = con.prepareStatement(Queries.consultar_modelo_por_id);
			ps.setInt(1, id);
			
			ResultSet res = ps.executeQuery();
			
			TModelo t;
			
			if(res.next()) t = new TModelo(id, res.getString("nombre"), res.getString("motor"), res.getBoolean("activo"));
			else t =  null;
			
			res.close();
			ps.close();

			return t;
		}catch (Exception e){
				return null;
		}
	}
	
	public List<TModelo> consultarModelosPorAerolinea(int idAerolinea) {
		try{
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		
		PreparedStatement ps = con.prepareStatement(Queries.consultar_modelo_por_aerolinea);
		ps.setInt(1, idAerolinea);
		
		ResultSet res = ps.executeQuery();
		
		List<TModelo> t = new ArrayList<>();
		
		while(res.next())
		{
			t.add(new TModelo(res.getInt("id"), res.getString("nombre"), res.getString("motor"), res.getBoolean("activo")));
		}
		
		
		res.close();
		ps.close();

		return t;
		}catch (Exception e){
			return new ArrayList<>();
		}
	} 



}