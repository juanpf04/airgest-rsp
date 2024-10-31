package integracion.personal;

import negocio.personal.TPLimpieza;
import negocio.personal.TPSeguridad;
import negocio.personal.TPersonal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import integracion.Querys;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;

import java.util.ArrayList;
import java.util.List;

public class DAOPersonalImp implements DAOPersonal {

	@Override
	public int altaPersonal(TPersonal tPersonal) {//perfe

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

	private Boolean altaLimpieza(TPLimpieza personal) {//perfe
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

    private Boolean altaSeguridad(TPSeguridad personal) {//perfe
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
	
    private boolean eliminarLimpieza(int id) {//perfe
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

    private boolean eliminarSeguridad(int id) {//perfe
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
	public boolean modificarPersonal(TPersonal tPersonal) {//perfe
		
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
	            } else if(modificado){
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

	private boolean modificarLimpieza(TPLimpieza personal) {//perfe
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

    private boolean modificarSeguridad(TPSeguridad personal) {//perfe
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
	public TPersonal consultarPersonalPorDni(String dni) {//perfe
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
	public List<TPersonal> consultarPersonalExistente() {//perfe
		try{
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarPersonalExistente);
			
			ResultSet res = ps.executeQuery();
			List<TPersonal> t = new ArrayList<>();
			while(res.next()){
				TPersonal tp = new TPersonal(res.getInt("id"), res.getBoolean("activo"), res.getString("dni"), res.getString("area_Asignada"));
				TPLimpieza limpieza = consultarLimpiezaPorId(tp.getId(), tp);
				TPSeguridad seguridad = null;
				if(limpieza != null) t.add(limpieza);
				else{
					seguridad = consultarSeguridadPorId(tp.getId(), tp);
					if(seguridad != null) t.add(seguridad);
				}
				
			}
			res.close();
			ps.close();

			return t;		
			
		}catch(Exception e){
			return new ArrayList<TPersonal>();
		}
	}

	@Override
	public List<TPersonal> consultarPersonalPorHangar(int id_hangar) {//perfe
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarPersonalPorHangar);
			ps.setInt(1, id_hangar);
			
			ResultSet rs = ps.executeQuery();
			List<TPersonal> lista = new ArrayList<>();
			
			while (rs.next()){
				TPersonal tp = new TPersonal(rs.getInt("id"), rs.getBoolean("activo"), rs.getString("dni"), rs.getString("area_Asignada"));
				TPLimpieza limpieza = consultarLimpiezaPorId(tp.getId(), tp);
				TPSeguridad seguridad = null;
				if(limpieza != null) lista.add(limpieza);
				else{
					seguridad = consultarSeguridadPorId(tp.getId(), tp);
					if(seguridad != null) lista.add(seguridad);
				}
			}
			
			rs.close();
			ps.close();
			
			return lista;
		}catch(Exception e){
			return new ArrayList<TPersonal>();
		}
	}

	@Override
	public TPersonal consultarPersonalPorId(int idPersonal) {//corregido
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarPersonalPorId);
			ps.setInt(1, idPersonal);
			
			ResultSet rs = ps.executeQuery();
			
			TPersonal tp = null;
			if (rs.next())
				tp = new TPersonal(rs.getInt("id"), rs.getBoolean("activo"), rs.getString("dni"), rs.getString("area_asignada")); 
			
			TPLimpieza limpieza = consultarLimpiezaPorId(idPersonal, tp);
			TPSeguridad seguridad = null;
			if(limpieza != null) tp = limpieza;
			else{
				seguridad = consultarSeguridadPorId(idPersonal, tp);
				if(seguridad != null) tp = seguridad;
			}
			
			rs.close();
			ps.close();
			
			return tp;
			
		} catch(Exception e){
			return null;
		}
		
	}
	
	private TPLimpieza consultarLimpiezaPorId(int id, TPersonal tp) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarLimpiezaPorId);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			String rol = null;
			TPLimpieza limpieza = null;
			if (rs.next()) {
				rol = rs.getString("rol");
				limpieza = new TPLimpieza(id, tp.getDni(), tp.getAreaAsignada(), tp.getActivo(), rol);
			}
			rs.close();
			ps.close();
			return limpieza;

		} catch (Exception e) {
			return null;
		}

	}

	private TPSeguridad consultarSeguridadPorId(int id, TPersonal tp) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarSeguridadPorId);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			int numPlaca;
			TPSeguridad seguridad = null;
			if (rs.next()) {
				numPlaca = rs.getInt("numPlaca");
				seguridad = new TPSeguridad(id, tp.getDni(), tp.getAreaAsignada(), tp.getActivo(), numPlaca);
			}
			rs.close();
			ps.close();
			return seguridad;

		} catch (Exception e) {
			return null;
		}

	}
}