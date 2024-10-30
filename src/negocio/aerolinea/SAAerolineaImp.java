package negocio.aerolinea;

import java.util.ArrayList;
import java.util.List;

import integracion.aerolinea.DAOAerolinea;
import integracion.avion.DAOAvion;
import integracion.contrato.DAOContrato;
import integracion.factoria.FactoriaIntegracion;
import integracion.modelo.DAOModelo;
import integracion.modeloAerolinea.DAOModeloAerolinea;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.UtilidadesN;
import negocio.modelo.TModelo;

public class SAAerolineaImp implements SAAerolinea {

	public int altaAerolinea(TAerolinea tAerolinea) {
		int id = -1;
		
		if (ValidadorAerolinea.comprobarAerolinea(tAerolinea)) {
			
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
			TAerolinea leido = da.consultarAerolineaPorNombre(tAerolinea.getNombre());

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

			TAerolinea leido = da.consultarAerolineaPorId(id);
			
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

			ta = da.consultarAerolineaPorId(id);
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

			TAerolinea leido = da.consultarAerolineaPorId(id);

			if (leido != null) {
				if (leido.getActivo()
						&& (leido.getNombre().equals(nombre) || da.consultarAerolineaPorNombre(nombre) == null)) {
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
		if (UtilidadesN.comprobarId(id_modelo)){
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();
			TModelo modelo = dm.consultarModeloPorId(id_modelo);
			
			DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
			List<TAerolinea> list;
			
			if (modelo != null){
				list = da.consultarAerolineasPorModelo(id_modelo);
				t.commit();
			} else{
				list = new ArrayList<TAerolinea>();
				t.rollback();
			}
			
			return list;
		}
		
		return new ArrayList<TAerolinea>();
		
	}

}