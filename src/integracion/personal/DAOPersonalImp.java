package integracion.personal;

import negocio.personal.TPLimpieza;
import negocio.personal.TPSeguridad;
import negocio.personal.TPersonal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONObject;
import org.json.JSONTokener;

import integracion.Querys;
import integracion.UtilidadesI;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;

import java.util.ArrayList;
import java.util.List;

public class DAOPersonalImp implements DAOPersonal {

	private TPersonal leerFichero(File file) {
		TPersonal transfer;
		try {
			JSONObject data = new JSONObject(new JSONTokener(new FileReader(file)));

			if (data.has("rol")) {
				transfer = new TPLimpieza(data.getInt("id"), data.getInt("idEmpleado"), data.getString("areaAsignada"),
						data.getBoolean("activo"), data.getString("rol"));
			} else {
				transfer = new TPSeguridad(data.getInt("id"), data.getInt("idEmpleado"), data.getString("areaAsignada"),
						data.getBoolean("activo"), data.getInt("numPlaca"));
			}

		} catch (FileNotFoundException e) {
			transfer = null;
		}
		return transfer;
	}

	private boolean escribirFichero(TPersonal tPersonal) {
		boolean exito = true;

		try {
			FileWriter archivo = new FileWriter(
					UtilidadesI.ruta("personal") + String.format("%05d", tPersonal.getId()) + ".json");

			JSONObject data = new JSONObject();

			data.put("id", tPersonal.getId());
			data.put("idEmpleado", tPersonal.getIdEmpleado());
			data.put("areaAsignada", tPersonal.getAreaAsignada());
			data.put("activo", tPersonal.getActivo());

			if (tPersonal instanceof TPSeguridad)
				data.put("numPlaca", ((TPSeguridad) tPersonal).getNumPlaca());
			else
				data.put("rol", ((TPLimpieza) tPersonal).getRol());

			archivo.write(data.toString());
			archivo.close();

		} catch (IOException e) {
			exito = false;
		}

		return exito;
	}

	@Override
	public int altaPersonal(TPersonal tPersonal) {
		File carpeta = new File(UtilidadesI.ruta("personal"));
		File[] lista = carpeta.listFiles();
		int id = lista.length + 1;

		tPersonal.setId(id);

		if (!this.escribirFichero(tPersonal))
			id = -1;

		return id;
	}

	@Override
	public boolean bajaPersonal(int id) {//perfe
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.bajaPersonal);
			ps.setInt(1, id);
			int filasNuevas = ps.executeUpdate();
			boolean eliminado = filasNuevas == 1 ? true : false;
			
			ps.close();

			return eliminado;
			
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean modificarPersonal(TPersonal tPersonal) {
		return escribirFichero(tPersonal);
	}

	@Override
	public TPersonal consultarPersonalPorId(int id) {
		return this.leerFichero(new File(UtilidadesI.ruta("personal") + String.format("%05d", id) + ".json"));
	}

	@Override
	public TPersonal consultarPersonalPorIdEmpleado(int idEmpleado) {
		File carpeta = new File(UtilidadesI.ruta("personal"));
		File[] lista = carpeta.listFiles();

		int i = 0;
		TPersonal transfer = null;
		while (i < lista.length && transfer == null) {
			transfer = leerFichero(lista[i]);
			if (transfer.getIdEmpleado() != idEmpleado)
				transfer = null;
			i++;
		}

		return transfer;
	}

	@Override
	public List<TPersonal> consultarPersonalExistente() {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarPersonalExistente);
			
			ResultSet res = ps.executeQuery();
			List<TPersonal> t = new ArrayList<>();
			while(res.next())
				t.add(new TPersonal(res.getInt("id"), res.getBoolean("activo"), res.getInt("dni"), res.getString("area_Asignada")));
			
			res.close();
			ps.close();

			return t;		
			
		}catch(Exception e){
			return new ArrayList<TPersonal>();
		}
	}

	@Override
	public List<TPersonal> consultarPersonalPorHangar(int id_hangar) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarPersonalPorHangar);
			ps.setInt(1, id_hangar);
			
			ResultSet rs = ps.executeQuery();
			List<TPersonal> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TPersonal(rs.getInt(1), rs.getBoolean(2), rs.getInt(3), rs.getString(4)));
			}
			
			rs.close();
			ps.close();
			
			return lista;
		}catch(Exception e){
			return new ArrayList<TPersonal>();
		}
	}
}