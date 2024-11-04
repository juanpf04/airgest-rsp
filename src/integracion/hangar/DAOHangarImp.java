package integracion.hangar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import integracion.Queries;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.hangar.THangar;

public class DAOHangarImp implements DAOHangar {

	public THangar leerHangarPorId(int id) {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Queries.leerHangarPorId);
			ps.setInt(1, id);
			
			ResultSet res = ps.executeQuery();
			THangar t;
			if(res.next())
				t = new THangar(id, res.getString("direccion"), res.getInt("stock"), res.getDouble("coste_Dia"), res.getInt("espacio_Almacenaje"), res.getBoolean("activo"));
			else
				t = null;
			
			res.close();
			ps.close();

			return t;		
			
		}catch(Exception e){
			return null;
		}
	}

	public boolean actualizarStock(int id, int stock) {//revisar
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Queries.actualizaStock);
			ps.setInt(1,  stock);
			ps.setInt(2, id);
			int filasNuevas = ps.executeUpdate();
			boolean modificado = filasNuevas == 1 ? true : false;
			
			ps.close();

			return modificado;		
			
		}catch(Exception e){
			return false;
		}
	}

	public int altaHangar(THangar tHangar){
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Queries.alta_hangar, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1,  tHangar.getStock());
			ps.setString(2, tHangar.getDireccion());
			ps.setInt(3, tHangar.getEspacioAlmacenaje());
			ps.setDouble(4,  tHangar.getCosteDia());
			
			int filasNuevas = ps.executeUpdate();
			ResultSet res = ps.getGeneratedKeys();
			int id = filasNuevas == 1 && res.next() ? res.getInt(1) : -1;
			
			res.close();
			ps.close();

			return id;		
			
		}catch(Exception e){
			return -1;
		}
		
	}

	public boolean bajaHangar(int id) {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Queries.baja_hangar);
			ps.setInt(1, id);
			int filasNuevas = ps.executeUpdate();
			boolean eliminado = filasNuevas == 1;
			
			ps.close();

			return eliminado;		
			
		}catch(Exception e){
			return false;
		}
	}

	public List<THangar> consultarTodosHangares() {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Queries.consultarTodosHangares);
			
			ResultSet res = ps.executeQuery();
			List<THangar> t = new ArrayList<>();
			while(res.next())
				t.add(new THangar(res.getInt("id"), res.getString("direccion"), res.getInt("stock"), res.getDouble("coste_Dia"), res.getInt("espacio_Almacenaje"), res.getBoolean("activo")));
			
			res.close();
			ps.close();

			return t;		
			
		}catch(Exception e){
			return new ArrayList<THangar>();
		}
	}

	public boolean modificarHangar(THangar tHangar) {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Queries.modificarHangar);
			ps.setInt(1,  tHangar.getStock());
			ps.setString(2, tHangar.getDireccion());
			ps.setInt(3, tHangar.getEspacioAlmacenaje());
			ps.setDouble(4,  tHangar.getCosteDia());
			ps.setBoolean(5, tHangar.getActivo());
			ps.setInt(6, tHangar.getId());
			int filasNuevas = ps.executeUpdate();
			boolean modificado = filasNuevas == 1 ? true : false;
			
			ps.close();

			return modificado;		
			
		}catch(Exception e){
			return false;
		}
	}

	public THangar leerHangarPorDireccion(String direccion) {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Queries.leerHangarPorDireccion);
			ps.setString(1, direccion);
			
			ResultSet res = ps.executeQuery();
			THangar t;
			if(res.next())
				t = new THangar(res.getInt("id"), direccion, res.getInt("stock"), res.getDouble("coste_Dia"), res.getInt("espacio_Almacenaje"), res.getBoolean("activo"));
			else
				t = null;
			
			res.close();
			ps.close();

			return t;		
			
		}catch(Exception e){
			return null;
		}
	}
	
	public List<THangar> consultarHangarPorPersonal(int id_personal) {//perfe
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.consultarHangarPorPersonal);
			ps.setInt(1, id_personal);
			
			ResultSet res = ps.executeQuery();
			List<THangar> lista = new ArrayList<>();
			
			while (res.next()){
				lista.add(new THangar(res.getInt("id"), res.getString("direccion"), res.getInt("stock"), res.getDouble("coste_Dia"), res.getInt("espacio_Almacenaje"), res.getBoolean("activo")));
			}
			
			res.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<THangar>();
		}
	}
	
	
	
	



}