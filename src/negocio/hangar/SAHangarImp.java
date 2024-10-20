package negocio.hangar;

import java.util.List;

import integracion.avion.DAOAvion;
import integracion.factoria.FactoriaIntegracion;
import integracion.hangar.DAOHangar;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.UtilidadesN;

public class SAHangarImp implements SAHangar {

	public int altaHangar(THangar tHangar) {
		if (ValidadorHangar.comprobarDatos(tHangar)) {
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
			THangar leido = dh.leerHangarPorDireccion(tHangar.getDireccion());

			if (leido == null)
				return dh.altaHangar(tHangar);
			else if (!leido.getActivo()) {
				tHangar.setId(leido.getId());
				dh.modificarHangar(tHangar);
				return tHangar.getId();
			}
		}

		return -1;
	}

	public boolean bajaHangar(int id) {
		if (UtilidadesN.comprobarId(id)) {
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();

			THangar leido = dh.leerHangarPorId(id);

			if (leido != null && leido.getActivo()) {
				DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();

				if (da.consultarAvionesActivosPorHangar(id).isEmpty()) {
					return dh.bajaHangar(id);
				}
			}
		}

		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

}