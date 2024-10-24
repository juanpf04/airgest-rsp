package negocio.modelo;

import java.util.List;

import integracion.aerolinea.DAOAerolinea;
import integracion.avion.DAOAvion;
import integracion.factoria.FactoriaIntegracion;
import integracion.modelo.DAOModelo;
import integracion.modeloAerolinea.DAOModeloAerolinea;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.UtilidadesN;
import negocio.aerolinea.TAerolinea;
import negocio.aerolinea.ValidadorAerolinea;
import negocio.modeloAerolinea.TModeloAerolinea;
import negocio.modeloAerolinea.ValidadorModeloAerolinea;

public class SAModeloImp implements SAModelo {

	public int altaModelo(TModelo tModelo) {
		
		int id = -1;
		if (ValidadorModelo.comprobarDatos(tModelo)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();
			TModelo leido = dm.leerModeloPorNombre(tModelo.getNombre());

			if (leido == null){
				id = dm.altaModelo(tModelo);
				if(id != -1){
					t.commit();
				}else t.rollback();
			}
			else if (!leido.getActivo()) {
				tModelo.setId(leido.getId());
				if (dm.modificarModelo(tModelo)){
					t.commit();
				} else{
					t.rollback();
				}
				id = tModelo.getId();
			} else{
				t.rollback();
			}
		}
			
		return id;
	}

	public boolean bajaModelo(int id) {
		if (UtilidadesN.comprobarId(id)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();

			TModelo leido = dm.leerModeloPorId(id);

			/*if (leido != null && leido.getActivo()) {
				DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();

				if (da.consultarAvionesActivosPorModelo(id).isEmpty()) {
					return dm.bajaModelo(id);
				}
			}*/ //TODO meter lo de avion
		}

		return false;
	}

	public TModelo consultarModelo(int id) {
		TModelo tm = null;
		if (UtilidadesN.comprobarId(id)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();
			
			tm = dm.leerModeloPorId(id);
			t.commit();
		}

		return tm;
	}

	public List<TModelo> consultarTodosModelos() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		
		DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();
		
		List<TModelo> list = dm.consultarTodosModelos();
		t.commit();
		
		return list;
	}

	public boolean modificarModelo(TModelo tModelo) {
		boolean ok = false;
		if (ValidadorModelo.comprobarModelo(tModelo)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();

			int id = tModelo.getId();
			String nombre = tModelo.getNombre();

			TModelo leido = dm.leerModeloPorId(id);

			if (leido != null) {
				if (leido.getActivo()
						&& (leido.getNombre().equals(nombre) || dm.leerModeloPorNombre(nombre) == null)) {
					ok = dm.modificarModelo(tModelo);
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
	public boolean vincularModelo(TModeloAerolinea tModeloAerolinea) {
		int idModelo = tModeloAerolinea.getIdModelo();
		int idAerolinea = tModeloAerolinea.getIdAerolinea();
		boolean ok = false;

		if (ValidadorModeloAerolinea.comprobarDatos(idModelo, idAerolinea)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();
			DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();

			TModelo mLeido = dm.leerModeloPorId(idModelo);
			TAerolinea aLeida = da.leerAerolineaPorId(idAerolinea);

			if (mLeido != null && mLeido.getActivo() && aLeida != null && aLeida.getActivo()) {
				DAOModeloAerolinea dma = FactoriaIntegracion.getInstance().crearDAOModeloAerolinea();

				if (!dma.comprobarVinculacion(idModelo, idAerolinea)) {
					ok = dma.vincular(idModelo, idAerolinea);
				}
				
			}if(ok){
				t.commit();
			}else{
				t.rollback();
			}
		}

		return ok;
	}

	public boolean desvincularModelo(TModeloAerolinea tModeloAerolinea) {
		int idModelo = tModeloAerolinea.getIdModelo();
		int idAerolinea = tModeloAerolinea.getIdAerolinea();
		boolean ok = false;

		if (ValidadorModeloAerolinea.comprobarDatos(idModelo, idAerolinea)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();
			DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();

			TModelo mLeido = dm.leerModeloPorId(idModelo);
			TAerolinea aLeida = da.leerAerolineaPorId(idAerolinea);

			if (mLeido != null && mLeido.getActivo() && aLeida != null && aLeida.getActivo()) {
				DAOModeloAerolinea dma = FactoriaIntegracion.getInstance().crearDAOModeloAerolinea();

				if (dma.comprobarVinculacion(idModelo, idAerolinea)) {
					ok = dma.desvincular(idModelo, idAerolinea);
				}
			}if(ok){
				t.commit();
			}else{
				t.rollback();
			}
		}

		return ok;
	}

	@Override
	public List<TModelo> consultarModelosPorAerolinea(int id_aerolinea) {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		
		DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();
		//List<TModelo> list = dm.consultarModelosPorAerolinea(id_aerolinea);
		t.commit();
		return null;
	}

}