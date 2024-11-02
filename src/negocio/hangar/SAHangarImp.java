package negocio.hangar;

import java.util.ArrayList;
import java.util.List;

import integracion.avion.DAOAvion;
import integracion.factoria.FactoriaIntegracion;
import integracion.hangar.DAOHangar;
import integracion.personal.DAOPersonal;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.UtilidadesN;
import negocio.avion.TAvion;
import negocio.personal.TPersonal;

public class SAHangarImp implements SAHangar {

	public int altaHangar(THangar tHangar) {
		int id = -1;
		if (ValidadorHangar.comprobarDatos(tHangar)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
			
			THangar leido = dh.leerHangarPorDireccion(tHangar.getDireccion());
			
			if (leido == null){
				id = dh.altaHangar(tHangar);
				if (id != -1) t.commit();
				else t.rollback();
			}
			else if (!leido.getActivo()) {
				tHangar.setId(leido.getId());
				boolean ok = dh.modificarHangar(tHangar);
				if(ok){ 
					id = tHangar.getId();
					t.commit();
				}
				else t.rollback();
			}else t.rollback();
		}

		return id;
	}

	public boolean bajaHangar(int id) {
		boolean ok = false;
		if (UtilidadesN.comprobarId(id)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
			THangar leido = dh.leerHangarPorId(id);
			
			if (leido != null && leido.getActivo()) {
					
				DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
				List<TAvion> listaa = da.consultarAvionesActivosPorHangar(id);
				DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();
				List<TPersonal> listap = dp.consultarPersonalPorHangar(id);
				if(listaa.isEmpty() && listap.isEmpty())
					ok = dh.bajaHangar(id);
				
			}
			
			if(ok) t.commit();
			else t.rollback();
		}
		return ok;
	}

	public THangar consultarHangarPorId(int id) {
		THangar th = null;
		
		if (UtilidadesN.comprobarId(id)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
			
			th = dh.leerHangarPorId(id);
			
			t.commit();
		}

		return th;
	}

	public List<THangar> consultarTodosHangares() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		
		DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
		List<THangar> list= dh.consultarTodosHangares();
		t.commit();
		
		return list;
	}

	public boolean modificarHangar(THangar tHangar) {
		boolean ok = false;
		if (UtilidadesN.comprobarId(tHangar.getId()) && ValidadorHangar.comprobarDatos(tHangar)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();

			int id = tHangar.getId();
			String direccion = tHangar.getDireccion();

 			THangar leido = dh.leerHangarPorId(id);

			
			if (leido != null) {
				if (leido.getActivo()
						&& (leido.getDireccion().equals(direccion) || dh.leerHangarPorDireccion(direccion) == null)) {
					ok = dh.modificarHangar(tHangar);
				}
			}
			
			if(ok) t.commit();
			else t.rollback();
		}
		return ok;
	}

	@Override
	public List<THangar> consultarHangarPorPersonal(int id_personal) {
		
		List<THangar> lista =  new ArrayList<>();
		
		if (UtilidadesN.comprobarId(id_personal)){
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
			
			DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();
			
			TPersonal leido = dp.consultarPersonalPorId(id_personal);
			
			if(leido != null && leido.getActivo())
				lista = dh.consultarHangarPorPersonal(id_personal);
			
			t.commit();
		}
		
		return lista;
	}
}