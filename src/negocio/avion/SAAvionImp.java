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
			TModelo m = dm.leerModeloPorId(tAvion.getIdModelo());

			if (h != null && h.getActivo() && a != null && a.getActivo() && m != null && m.getActivo()) {
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
			TModelo m = dm.leerModeloPorId(tAvion.getIdModelo());

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
		List<TAvion> list = null;
		if (UtilidadesN.comprobarId(idModelo)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
			list = da.consultarAvionesPorModelo(idModelo);
			t.commit();
		}
		return list;
	}

	public List<TAvion> consultarAvionesPorAerolinea(int idAerolinea) {
		List<TAvion> list = null;
		if (UtilidadesN.comprobarId(idAerolinea)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
			list = da.consultarAvionesPorAerolinea(idAerolinea);
			t.commit();
		}

		return list;
	}

	public List<TAvion> consultarAvionesPorHangar(int idHangar) {
		List<TAvion> list = null;
		if (UtilidadesN.comprobarId(idHangar)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
			list = da.consultarAvionesPorHangar(idHangar);
			t.commit();
		}

		return list;
	}

	@Override
	public List<TAvion> consultarAvionesDeAerolineaPorHangar(int id_aerolinea, int id_hangar) {
		List<TAvion> listaAviones = new ArrayList<>();
		if (UtilidadesN.comprobarId(id_aerolinea) && UtilidadesN.comprobarId(id_hangar)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
			listaAviones = da.consultarAvionesDeAerolineaPorHangar(id_aerolinea, id_hangar);

			if (listaAviones != null) {
				t.commit();
			} else {
				t.rollback();
			}
		}
		return listaAviones;
	}

}
