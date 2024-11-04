package negocio.avion;

import java.util.ArrayList;
import java.util.List;

import integracion.aerolinea.DAOAerolinea;
import integracion.avion.DAOAvion;
import integracion.factoria.FactoriaIntegracion;
import integracion.hangar.DAOHangar;
import integracion.modelo.DAOModelo;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.UtilidadesN;
import negocio.aerolinea.TAerolinea;
import negocio.hangar.THangar;
import negocio.modelo.TModelo;

public class SAAvionImp implements SAAvion {

	public int altaAvion(TAvion tAvion) {
		int id = -1;
		if (ValidadorAvion.comprobarDatos(tAvion)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
			TAvion leido = da.consultarAvionPorMatricula(tAvion.getMatricula());
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
			THangar h = dh.leerHangarPorId(tAvion.getIdHangar());
			DAOAerolinea dar = FactoriaIntegracion.getInstance().crearDAOAerolinea();
			TAerolinea a = dar.consultarAerolineaPorId(tAvion.getIdAerolinea());
			DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();
			TModelo m = dm.consultarModeloPorId(tAvion.getIdModelo());

			if (h != null && h.getActivo() && a != null && a.getActivo() && m != null && m.getActivo() && h.getStock() != 0) {
				
				int nuevo_stock = dh.leerHangarPorId(tAvion.getIdHangar()).getStock() - 1;

				if (leido == null && nuevo_stock >= 0) {
					dh.actualizarStock(tAvion.getIdHangar(), nuevo_stock);
					id = da.altaAvion(tAvion);
					if (id != -1)
						t.commit();
					else
						t.rollback();
				} else if (!leido.getActivo() && nuevo_stock >= 0) {
					dh.actualizarStock(tAvion.getIdHangar(), nuevo_stock);
					tAvion.setId(leido.getId());
					boolean ok = da.modificarAvion(tAvion);
					if (ok) {
						id = tAvion.getId();
						t.commit();
					} else {
						t.rollback();
					}
				} else {
					t.rollback();
				}
			} else {
				t.rollback();
			}
		}
		return id;
	}

	public boolean bajaAvion(int idAvion) {
		boolean ok = false;
		if (UtilidadesN.comprobarId(idAvion)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
			TAvion leido = da.consultarAvionPorId(idAvion);

			if (leido != null && leido.getActivo()) {
				DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
				dh.actualizarStock(leido.getIdHangar(), dh.leerHangarPorId(leido.getIdHangar()).getStock() + 1);
				ok = da.bajaAvion(idAvion);
			}

			if (ok)
				t.commit();
			else
				t.rollback();
		}
		return ok;
	}

	public TAvion consultarAvionPorId(int idAvion) {
		TAvion avion = null;
		if (UtilidadesN.comprobarId(idAvion)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
			avion = da.consultarAvionPorId(idAvion);
			t.commit();
		}
		return avion;
	}

	public List<TAvion> consultarTodosAviones() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
		List<TAvion> list = da.consultarTodosAviones();
		t.commit();

		return list;
	}

	public boolean modificarAvion(TAvion tAvion) {
		boolean ok = false;
		if (UtilidadesN.comprobarId(tAvion.getId()) && ValidadorAvion.comprobarDatos(tAvion)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
			THangar h = dh.leerHangarPorId(tAvion.getIdHangar());
			DAOAerolinea dar = FactoriaIntegracion.getInstance().crearDAOAerolinea();
			TAerolinea a = dar.consultarAerolineaPorId(tAvion.getIdAerolinea());
			DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();
			TModelo m = dm.consultarModeloPorId(tAvion.getIdModelo());

			TAvion leido = da.consultarAvionPorId(tAvion.getId());
			if (h != null && h.getActivo() && a != null && a.getActivo() && m != null && m.getActivo()) {
				int nuevo_stock = dh.leerHangarPorId(tAvion.getIdHangar()).getStock();

				if (leido != null && leido.getIdHangar() != tAvion.getIdHangar())
					nuevo_stock--;

				if (leido != null && leido.getActivo() && (leido.getMatricula().equals(tAvion.getMatricula())
						|| da.consultarAvionPorMatricula(tAvion.getMatricula()) == null) && nuevo_stock >= 0) {
					dh.actualizarStock(leido.getIdHangar(), dh.leerHangarPorId(leido.getIdHangar()).getStock() + 1);
					dh.actualizarStock(tAvion.getIdHangar(), nuevo_stock);
					ok = da.modificarAvion(tAvion);
				}
			}

			if (ok)
				t.commit();
			else
				t.rollback();
		}
		return ok;
	}

	public List<TAvion> consultarAvionesPorModelo(int idModelo) {
		if (UtilidadesN.comprobarId(idModelo)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();
			TModelo modelo = dm.consultarModeloPorId(idModelo);

			DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
			List<TAvion> list;
			
			if (modelo != null){
				list = da.consultarAvionesPorModelo(idModelo);
				t.commit();
			} else{
				list = new ArrayList<TAvion>();
				t.rollback();
			}
			
			return list;
		}
		return new ArrayList<TAvion>();
	}

	public List<TAvion> consultarAvionesPorAerolinea(int idAerolinea) {
		
		if (UtilidadesN.comprobarId(idAerolinea)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
			TAerolinea aerolinea = da.consultarAerolineaPorId(idAerolinea);
			
			List<TAvion> list;
			DAOAvion dav = FactoriaIntegracion.getInstance().crearDAOAvion();
			
			if (aerolinea != null){
				list = dav.consultarAvionesPorAerolinea(idAerolinea);
				t.commit();
			} else{
				list = new ArrayList<TAvion>();
				t.rollback();
			}
			
			return list;
			
		}

		return new ArrayList<TAvion>();
	}

	public List<TAvion> consultarAvionesPorHangar(int idHangar) {
		
		if (UtilidadesN.comprobarId(idHangar)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
			THangar hangar = dh.leerHangarPorId(idHangar);
			
			List<TAvion> list;

			DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
			
			if (hangar != null){
				list = da.consultarAvionesPorHangar(idHangar);
				t.commit();
			} else{
				list = new ArrayList<TAvion>();
				t.rollback();
			}
			
			return list;
		}

		return new ArrayList<TAvion>();
	}

	@Override
	public List<TAvion> consultarAvionesDeAerolineaPorHangar(int id_aerolinea, int id_hangar) {
		
		if (UtilidadesN.comprobarId(id_aerolinea) && UtilidadesN.comprobarId(id_hangar)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
			TAerolinea aerolinea = da.consultarAerolineaPorId(id_aerolinea);
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
			THangar hangar = dh.leerHangarPorId(id_hangar);

			DAOAvion dav = FactoriaIntegracion.getInstance().crearDAOAvion();
			
			List<TAvion> list;
			
			if (aerolinea != null && hangar != null){
				list = dav.consultarAvionesDeAerolineaPorHangar(id_aerolinea, id_hangar);
				t.commit();
			} else{
				list = new ArrayList<TAvion>();
				t.rollback();
			}
			
			return list;
		}
		return new ArrayList<TAvion>();
	}

}
