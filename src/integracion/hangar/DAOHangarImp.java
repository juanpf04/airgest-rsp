package integracion.hangar;

import negocio.hangar.THangar;

import java.util.List;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONTokener;

import integracion.Querys;
import integracion.UtilidadesI;
import integracion.transacciones.TransactionManager;

public class DAOHangarImp implements DAOHangar {

	public THangar leerHangarPorId(int id) {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.leerHangarPorId);
			ps.setInt(1, id);
			
			ResultSet res = ps.executeQuery();
			THangar t;
			if(res.next()){
				boolean activo = res.getInt("activo") == 1 ? true : false;
				t = new THangar(id, res.getString("direccion"), res.getInt("stock"), res.getDouble("coste_Dia"), res.getInt("espacio_Almacenaje"), activo);
			}else
				t = new THangar(-1, "mal", 1, 1, 1, false);
			
			res.close();
			ps.close();

			return t;		
			
		}catch(SQLException e){
			return new THangar(-1, "mal", 1, 1, 1, false);
		}
	}

	public boolean actualizarStock(int id, int stock) {//revisar
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.actualizaStock);
			ps.setInt(1,  stock);
			ps.setInt(2, id);
			int filasNuevas = ps.executeUpdate();
			boolean modificado = filasNuevas == 1 ? true : false;
			
			ps.close();

			return modificado;		
			
		}catch(SQLException e){
			return false;
		}
	}

	public int altaHangar(THangar tHangar){
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.alta_hangar, PreparedStatement.RETURN_GENERATED_KEYS);
			
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
			
		}catch(SQLException e){
			return -1;
		}
		
	}

	public boolean bajaHangar(int id) {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.baja_hangar);
			ps.setInt(1, id);
			int filasNuevas = ps.executeUpdate();
			boolean eliminado = filasNuevas == 1 ? true : false;
			
			ps.close();

			return eliminado;		
			
		}catch(SQLException e){
			return false;
		}
	}

	public List<THangar> consultarTodosHangares() {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarTodosHangares);
			
			ResultSet res = ps.executeQuery();
			List<THangar> t = new ArrayList<>();
			while(res.next()){
				boolean activo = res.getInt("activo") == 1 ? true : false;
				t.add(new THangar(res.getInt("id"), res.getString("direccion"), res.getInt("stock"), res.getDouble("coste_Dia"), res.getInt("espacio_Almacenaje"), activo));
			}
			res.close();
			ps.close();

			return t;		
			
		}catch(SQLException e){
			return new ArrayList<THangar>();
		}
	}

	public boolean modificarHangar(THangar tHangar) {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.modificarHangar);
			ps.setInt(1,  tHangar.getStock());
			ps.setString(2, tHangar.getDireccion());
			ps.setInt(3, tHangar.getEspacioAlmacenaje());
			ps.setDouble(4,  tHangar.getCosteDia());
			int activo = tHangar.getActivo() ? 1 : 0;
			ps.setInt(5, activo);
			ps.setInt(6, tHangar.getId());
			int filasNuevas = ps.executeUpdate();
			boolean modificado = filasNuevas == 1 ? true : false;
			
			ps.close();

			return modificado;		
			
		}catch(SQLException e){
			return false;
		}
	}

	public THangar leerHangarPorDireccion(String direccion) {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.leerHangarPorDireccion);
			ps.setString(1, direccion);
			
			ResultSet res = ps.executeQuery();
			THangar t;
			if(res.next())
				t = new THangar(res.getInt("id"), direccion, res.getInt("stock"), res.getDouble("costeDia"), res.getInt("espacioAlmacenaje"), res.getBoolean("activo"));
			else
				t = new THangar(-1, "mal", 1, 1, 1, false);
			
			res.close();
			ps.close();

			return t;		
			
		}catch(SQLException e){
			return new THangar(-1, "mal", 1, 1, 1, false);
		}
	}



}