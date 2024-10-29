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

//	private TPersonal leerFichero(File file) {
//		TPersonal transfer;
//		try {
//			JSONObject data = new JSONObject(new JSONTokener(new FileReader(file)));
//
//			if (data.has("rol")) {
//				transfer = new TPLimpieza(data.getInt("id"), data.getString("dni"), data.getString("areaAsignada"),
//						data.getBoolean("activo"), data.getString("rol"));
//			} else {
//				transfer = new TPSeguridad(data.getInt("id"), data.getString("dni"), data.getString("areaAsignada"),
//						data.getBoolean("activo"), data.getInt("numPlaca"));
//			}
//
//		} catch (FileNotFoundException e) {
//			transfer = null;
//		}
//		return transfer;
//	}
//
//	private boolean escribirFichero(TPersonal tPersonal) {
//		boolean exito = true;
//
//		try {
//			FileWriter archivo = new FileWriter(
//					UtilidadesI.ruta("personal") + String.format("%05d", tPersonal.getId()) + ".json");
//
//			JSONObject data = new JSONObject();
//
//			data.put("id", tPersonal.getId());
//			data.put("dni", tPersonal.getDni());
//			data.put("areaAsignada", tPersonal.getAreaAsignada());
//			data.put("activo", tPersonal.getActivo());
//
//			if (tPersonal instanceof TPSeguridad)
//				data.put("numPlaca", ((TPSeguridad) tPersonal).getNumPlaca());
//			else
//				data.put("rol", ((TPLimpieza) tPersonal).getRol());
//
//			archivo.write(data.toString());
//			archivo.close();
//
//		} catch (IOException e) {
//			exito = false;
//		}
//
//		return exito;
//	}

	@Override
	public int altaPersonal(TPersonal tPersonal) {

		int id = -1;
		
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.altaPersonal, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tPersonal.getDni());
			ps.setString(2, tPersonal.getAreaAsignada());
			
			int filasNuevas = ps.executeUpdate();			
			ResultSet rs = ps.getGeneratedKeys();
			
			id = filasNuevas == 1 && rs.next() ? rs.getInt(1) : -1;
            tPersonal.setId(id);
            boolean ok = false;
            if (id != -1 && tPersonal instanceof TPLimpieza)
                ok = altaLimpieza((TPLimpieza) tPersonal);
            else
                ok = altaSeguridad((TPSeguridad) tPersonal);

            if (!ok)
                id = -1;
            
			rs.close();
			ps.close();

			return id;
			
		}catch(Exception e){
			return id;
		}
	}

	private Boolean altaLimpieza(TPLimpieza personal) {
        try {
            Transaction t = TransactionManager.getInstance().getTransaccion();
            Connection con = (Connection) t.getResource();
            PreparedStatement ps = con.prepareStatement(Querys.altaLimpieza);
            ps.setInt(1, personal.getId());
            ps.setString(2, personal.getRol());
            int filas = ps.executeUpdate();
            ps.close();
            return filas == 1;
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean altaSeguridad(TPSeguridad personal) {
        try {
            Transaction t = TransactionManager.getInstance().getTransaccion();
            Connection con = (Connection) t.getResource();
            PreparedStatement ps = con.prepareStatement(Querys.altaSeguridad);
            ps.setInt(1, personal.getId());
            ps.setInt(2, personal.getNumPlaca());
            int filas = ps.executeUpdate();
            ps.close();
            return filas == 1;
        } catch (Exception e) {
            return false;
        }
    }
	
    private boolean eliminarLimpieza(int id) {
        try {
            Transaction t = TransactionManager.getInstance().getTransaccion();
            Connection con = (Connection) t.getResource();
            PreparedStatement ps = con.prepareStatement(Querys.eliminarLimpieza);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            ps.close();
            return filas == 1;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean eliminarSeguridad(int id) {
        try {
            Transaction t = TransactionManager.getInstance().getTransaccion();
            Connection con = (Connection) t.getResource();
            PreparedStatement ps = con.prepareStatement(Querys.eliminarSeguridad);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            ps.close();
            return filas == 1;
        } catch (Exception e) {
            return false;
        }
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
		
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.modificarPersonal);
			ps.setString(1, tPersonal.getAreaAsignada());
			ps.setString(2, tPersonal.getDni());
			ps.setBoolean(3, tPersonal.getActivo());
			ps.setInt(4, tPersonal.getId());
			
			
			int filas = ps.executeUpdate();
			boolean modificado = filas == 1;
			
			 if (modificado && tPersonal instanceof TPLimpieza) {
	                boolean limpiezaModificado = modificarLimpieza((TPLimpieza) tPersonal);
	                if (!limpiezaModificado) {
	                    modificado = eliminarSeguridad(tPersonal.getId());
	                    modificado = altaLimpieza((TPLimpieza) tPersonal);
	                }
	            } else {
	                boolean seguridadModificado = modificarSeguridad((TPSeguridad) tPersonal);
	                if (!seguridadModificado) {
	                    modificado = eliminarLimpieza(tPersonal.getId());
	                    modificado = altaSeguridad((TPSeguridad) tPersonal);
	                }
	            }
			
			ps.close();
			
			return modificado;
		} catch(Exception e){
			return false;
		}
	}

	private boolean modificarLimpieza(TPLimpieza personal) {
        try {
            Transaction t = TransactionManager.getInstance().getTransaccion();
            Connection con = (Connection) t.getResource();
            PreparedStatement ps = con.prepareStatement(Querys.modificarLimpieza);
            ps.setString(1, personal.getRol());
            ps.setInt(2, personal.getId());
            int filas = ps.executeUpdate();
            ps.close();
            return filas == 1;

        } catch (Exception e) {
            return false;
        }
    }

    private boolean modificarSeguridad(TPSeguridad personal) {
        try {
            Transaction t = TransactionManager.getInstance().getTransaccion();
            Connection con = (Connection) t.getResource();
            PreparedStatement ps = con.prepareStatement(Querys.modificarSeguridad);
            ps.setInt(1, personal.getNumPlaca());
            ps.setInt(2, personal.getId());
            int filas = ps.executeUpdate();
            ps.close();
            return filas == 1;

        } catch (Exception e) {
            return false;
        }
    }
    
	@Override
	public TPersonal consultarPersonalPorDni(String dni) {
		TPersonal ret = null;
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarPersonalPorDni);
			ps.setString(1, dni);
			ResultSet res = ps.executeQuery();
			
			if (res.next()) 
				ret = new TPersonal(res.getInt("id"), res.getBoolean("activo"), dni, res.getString("area_asignada"));
			
			res.close();
			ps.close();

			return ret;
			
		}catch(Exception e){
			return ret;
		}
	}
	
	@Override
	public List<TPersonal> consultarPersonalExistente() {
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarPersonalExistente);
			
			ResultSet res = ps.executeQuery();
			List<TPersonal> t = new ArrayList<>();
			while(res.next())
				t.add(new TPersonal(res.getInt("id"), res.getBoolean("activo"), res.getString("dni"), res.getString("area_Asignada")));
			
			res.close();
			ps.close();

			return t;		
			
		}catch(Exception e){
			return new ArrayList<TPersonal>();
		}
	}

	@Override
	public List<TPersonal> consultarPersonalPorHangar(int id_hangar) {//hay q poner tamb el rol del personal? si
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarPersonalPorHangar);
			ps.setInt(1, id_hangar);
			
			ResultSet rs = ps.executeQuery();
			List<TPersonal> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TPersonal(rs.getInt("id"), rs.getBoolean("activo"), rs.getString("dni"), rs.getString("area_asignada")));
			}
			
			rs.close();
			ps.close();
			
			return lista;
		}catch(Exception e){
			return new ArrayList<TPersonal>();
		}
	}

	@Override
	public TPersonal consultarPersonalPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}