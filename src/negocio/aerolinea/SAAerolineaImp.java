package negocio.aerolinea;

import java.util.List;

import integracion.aerolinea.DAOAerolinea;
import integracion.avion.DAOAvion;
import integracion.contrato.DAOContrato;
import integracion.factoria.FactoriaIntegracion;
import integracion.modeloAerolinea.DAOModeloAerolinea;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.UtilidadesN;

public class SAAerolineaImp implements SAAerolinea {

	public int altaAerolinea(TAerolinea tAerolinea) {
		int id = -1;
		
		if (ValidadorAerolinea.comprobarAerolinea(tAerolinea)) {
			
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
			TAerolinea leido = da.leerAerolineaPorNombre(tAerolinea.getNombre());

			if (leido == null){
				id = da.altaAerolinea(tAerolinea);
				if (id != -1){
					t.commit();
				} else{
					t.rollback();
				}
			}
			else if (!leido.getActivo()) {
				tAerolinea.setId(leido.getId());
				if (da.modificarAerolinea(tAerolinea)){
					t.commit();
				} else{
					t.rollback();
				}
				id = tAerolinea.getId();
			} else{
				t.rollback();
			}
		}

		return id;
	}

	public boolean bajaAerolinea(int id) {
		boolean ok = false;
		if (UtilidadesN.comprobarId(id)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();

			TAerolinea leido = da.leerAerolineaPorId(id);
			
			if (leido != null && leido.getActivo()){
				DAOModeloAerolinea dam = FactoriaIntegracion.getInstance().crearDAOModeloAerolinea();
				
				if (!dam.comprobarVinculacionAerolinea(id)){
					ok = da.bajaAerolinea(id);
				}
			}
			
			if (ok){
				t.commit();
			} else{
				t.rollback();
			}
			
			// TODO Comprobar con contrato y avion

			/*if (leido != null && leido.getActivo()) {
				DAOAvion dav = FactoriaIntegracion.getInstance().crearDAOAvion();
				DAOContrato dc = FactoriaIntegracion.getInstance().crearDAOContrato();

				if (dav.consultarAvionesActivosPorAerolinea(id).isEmpty()
						&& dc.leerContratosPorAerolinea(id).isEmpty()) {
					return da.bajaAerolinea(id);
				}
			}*/
		}
		return ok;
	}

	public TAerolinea consultarAerolineaPorId(int id) {
		TAerolinea ta = null;
		if (UtilidadesN.comprobarId(id)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();

			ta = da.leerAerolineaPorId(id);
			t.commit();
		}

		return ta;
	}

	public List<TAerolinea> consultarTodasAerolineas() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		
		DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
		List<TAerolinea> list = da.consultarTodasAerolineas();
		t.commit();
		
		return list;
	}

	public boolean modificarAerolinea(TAerolinea tAerolinea) {
		boolean ok = false;
		if (ValidadorAerolinea.comprobarAerolinea(tAerolinea)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();

			int id = tAerolinea.getId();
			String nombre = tAerolinea.getNombre();

			TAerolinea leido = da.leerAerolineaPorId(id);

			if (leido != null) {
				if (leido.getActivo()
						&& (leido.getNombre().equals(nombre) || da.leerAerolineaPorNombre(nombre) == null)) {
					ok = da.modificarAerolinea(tAerolinea);
				}
			}
			
			if (ok){
				t.commit();
			} else{
				t.rollback();
			}
		}
		return ok;
	}

	@Override
	public List<TAerolinea> consultarAerolineasPorModelo(int id_modelo) {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		
		// TODO falta comprobar que el modelo exista y este activo
		
		DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
		List<TAerolinea> list = da.consultarAerolineasPorModelo(id_modelo);
		t.commit();
		
		return list;
	}

}