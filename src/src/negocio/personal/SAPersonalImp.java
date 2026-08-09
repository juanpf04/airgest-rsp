package negocio.personal;

import java.util.ArrayList;
import java.util.List;

import integracion.factoria.FactoriaIntegracion;
import integracion.hangar.DAOHangar;
import integracion.personal.DAOPersonal;
import integracion.personalHangar.DAOPersonalHangar;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.UtilidadesN;
import negocio.hangar.THangar;
import negocio.personalHangar.TPersonalHangar;
import negocio.personalHangar.ValidadorPersonalHangar;

public class SAPersonalImp implements SAPersonal {

	@Override
	public int altaPersonal(TPersonal tPersonal) {
		int id = -1;
		if (ValidadorPersonal.comprobarDatos(tPersonal)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();
			TPersonal leido = dp.consultarPersonalPorDni(tPersonal.getDni());

			if (leido == null)
				id = dp.altaPersonal(tPersonal);
			else if (!leido.getActivo()) {
				tPersonal.setId(leido.getId());

				if (dp.modificarPersonal(tPersonal))
					id = tPersonal.getId();
			}

			if (id == -1)
				t.rollback();
			else
				t.commit();
		}

		return id;
	}

	@Override
	public boolean bajaPersonal(int id) {
		boolean ok = false;
		if (UtilidadesN.comprobarId(id)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();

			TPersonal leido = dp.consultarPersonalPorId(id);
			if (leido != null && leido.getActivo()) {
				DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
				List<THangar> lista = dh.consultarHangaresPorPersonal(id);
				if (lista.isEmpty())
					ok = dp.bajaPersonal(id);
			}

			if (ok)
				t.commit();
			else
				t.rollback();
		}

		return ok;
	}

	@Override
	public boolean vincularPersonal(TPersonalHangar tPersonalHangar) {
		int idPersonal = tPersonalHangar.getIdPersonal();
		int idHangar = tPersonalHangar.getIdHangar();
		boolean vinculado = false;

		if (ValidadorPersonalHangar.comprobarDatos(tPersonalHangar)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();

			TPersonal pLeido = dp.consultarPersonalPorId(idPersonal);
			THangar hLeida = dh.consultarHangarPorId(idHangar);

			if (pLeido != null && pLeido.getActivo() && hLeida != null && hLeida.getActivo()) {
				DAOPersonalHangar dph = FactoriaIntegracion.getInstance().crearDAOPersonalHangar();

				if (!dph.comprobarVinculacion(idPersonal, idHangar)) {
					vinculado = dph.vincular(idPersonal, idHangar);
				}
			}

			if (vinculado)
				t.commit();
			else
				t.rollback();
		}

		return vinculado;
	}

	@Override
	public boolean desvincularPersonal(TPersonalHangar tPersonalHangar) {
		int idPersonal = tPersonalHangar.getIdPersonal();
		int idHangar = tPersonalHangar.getIdHangar();
		boolean ok = false;
		if (ValidadorPersonalHangar.comprobarDatos(tPersonalHangar)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();

			TPersonal pLeido = dp.consultarPersonalPorId(idPersonal);
			THangar hLeida = dh.consultarHangarPorId(idHangar);

			if (pLeido != null && pLeido.getActivo() && hLeida != null && hLeida.getActivo()) {
				DAOPersonalHangar dph = FactoriaIntegracion.getInstance().crearDAOPersonalHangar();

				if (dph.comprobarVinculacion(idPersonal, idHangar)) {
					ok = dph.desvincular(idPersonal, idHangar);
				}
			}
			if (ok)
				t.commit();
			else
				t.rollback();
		}

		return ok;
	}

	@Override
	public boolean modificarPersonal(TPersonal tPersonal) {
		boolean ok = false;
		if (UtilidadesN.comprobarId(tPersonal.getId()) && ValidadorPersonal.comprobarDatos(tPersonal)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();

			TPersonal leido = dp.consultarPersonalPorId(tPersonal.getId());

			if (leido != null) {
				if (leido.getActivo() && (leido.getDni().equals(tPersonal.getDni())
						|| dp.consultarPersonalPorDni(tPersonal.getDni()) == null)) {
					ok = dp.modificarPersonal(tPersonal);
				}
			}

			if (ok)
				t.commit();
			else
				t.rollback();
		}
		return ok;
	}

	@Override
	public TPersonal consultarPersonalPorId(int id) {
		TPersonal ret = null;
		if (UtilidadesN.comprobarId(id)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();

			ret = dp.consultarPersonalPorId(id);
			t.commit();
		}

		return ret;
	}

	@Override
	public List<TPersonal> consultarPersonalExistente() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();
		List<TPersonal> list = dp.consultarPersonalExistente();
		t.commit();

		return list;
	}

	@Override
	public List<TPersonal> consultarPersonalPorHangar(int id_hangar) {
		List<TPersonal> lista = new ArrayList<>();
		if (UtilidadesN.comprobarId(id_hangar)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();

			DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();

			THangar leido = dh.consultarHangarPorId(id_hangar);

			if (leido != null && leido.getActivo()) {
				lista = dp.consultarPersonalPorHangar(id_hangar);
			}

			t.commit();

		}
		return lista;
	}
}