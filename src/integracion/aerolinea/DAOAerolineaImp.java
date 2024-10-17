package integracion.aerolinea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;

import integracion.UtilidadesI;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.aerolinea.TAerolinea;

public class DAOAerolineaImp implements DAOAerolinea {

	public TAerolinea leerAerolineaPorId(int idAerolinea) {
		
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Aerolinea WHERE Id=?; ");
			ps.setInt(1, idAerolinea);
			
			ResultSet rs = ps.executeQuery();
			
			TAerolinea ta = null;
			if (rs.next()){
				boolean activo = rs.getInt(3) == 1 ? true : false;
				ta = new TAerolinea(rs.getInt(1), rs.getString(2), activo); 
			}
			
			return ta;
			
		} catch(SQLException e){
			return null;
		}
		
	}

	public TAerolinea leerAerolineaPorNombre(String nombre) {
		File carpeta = new File(UtilidadesI.ruta("aerolinea"));
		File[] lista = carpeta.listFiles();

		int i = 0;
		while (i < lista.length) {
			JSONObject data = new JSONObject();
			try {
				data = new JSONObject(new JSONTokener(new FileReader(lista[i])));
			} catch (FileNotFoundException e) {
			}

			if (data.getString("nombre").equals(nombre)) {
				return new TAerolinea(data.getInt("id"), data.getString("nombre"), data.getBoolean("activo"));
			}

			i++;
		}
		return null;
	}

	public int altaAerolinea(TAerolinea tAerolinea) {
		
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("INSERT INTO AEROLINEA (Nombre, Activo) VALUES(?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tAerolinea.getNombre());
			ps.setInt(2, 1);
			
			int filas = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			int id = filas == 1 && rs.next() ? rs.getInt(1) : -1;
			
			rs.close();
			ps.close();
			
			return id;
			
		} catch (SQLException e) {
			return -1;
		}
	}

	public boolean modificarAerolinea(TAerolinea tAerolinea) {
		try {
			FileWriter archivo = new FileWriter(
					UtilidadesI.ruta("aerolinea") + String.format("%05d", tAerolinea.getId()) + ".json");
			archivo.write(this.toJSON(tAerolinea).toString());
			archivo.close();

			return true;
		} catch (IOException e) {
		}
		return false;
	}

	public boolean bajaAerolinea(int id) {
		try {
			JSONObject data = new JSONObject(new JSONTokener(
					new FileReader(UtilidadesI.ruta("aerolinea") + String.format("%05d", id) + ".json")));

			data.put("activo", false);

			FileWriter archivo = new FileWriter(UtilidadesI.ruta("aerolinea") + String.format("%05d", id) + ".json");

			archivo.write(data.toString());
			archivo.close();

			return true;
		} catch (IOException e) {
		}
		return false;
	}

	public List<TAerolinea> consultarTodasAerolineas() {
		File carpeta = new File(UtilidadesI.ruta("aerolinea"));
		File[] lista = carpeta.listFiles();

		List<TAerolinea> aerolineas = new ArrayList<>();

		for (File f : lista) {
			try {
				JSONObject data = new JSONObject(new JSONTokener(new FileReader(f)));
				aerolineas.add(new TAerolinea(data.getInt("id"), data.getString("nombre"), data.getBoolean("activo")));
			} catch (FileNotFoundException e) {
			}
		}

		return aerolineas;
	}

	private JSONObject toJSON(TAerolinea taerolinea) {
		JSONObject jo = new JSONObject();

		jo.put("id", taerolinea.getId());
		jo.put("nombre", taerolinea.getNombre());
		jo.put("activo", taerolinea.getActivo());

		return jo;
	}

	@Override
	public List<TAerolinea> consultarAerolineasPorModelo(int id_modelo) {
		// TODO Auto-generated method stub
		return null;
	}

}