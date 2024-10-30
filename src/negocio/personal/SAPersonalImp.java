package negocio.personal;

import negocio.UtilidadesN;
import negocio.hangar.THangar;
import negocio.personalHangar.TPersonalHangar;
import negocio.personalHangar.ValidadorPersonalHangar;

import java.util.ArrayList;
import java.util.List;

import integracion.factoria.FactoriaIntegracion;
import integracion.hangar.DAOHangar;
import integracion.personal.DAOPersonal;
import integracion.personalHangar.DAOPersonalHangar;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;

public class SAPersonalImp implements SAPersonal {

	@Override
	public int altaPersonal(TPersonal tPersonal) {
		int id = -1;
		if (ValidadorPersonal.comprobarDatos(tPersonal)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();
			TPersonal leido = dp.consultarPersonalPorId(tPersonal.getId());
			
			if (leido == null) 
				id = dp.altaPersonal(tPersonal);
			else if (!leido.getActivo()) {
				tPersonal.setId(leido.getId());
				
				if (dp.modificarPersonal(tPersonal)) id = tPersonal.getId();
			}
			
			if (id == -1) t.rollback();
			else t.commit();
		}

		return id;
	}

	@Override
	public boolean bajaPersonal(int id) {//perfe
		boolean ok = false;
		if (UtilidadesN.comprobarId(id)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();

			TPersonal leido = dp.consultarPersonalPorId(id);

			if (leido != null && leido.getActivo()) {
				ok = dp.bajaPersonal(id);
			}
			
			if(ok) t.commit();
			else t.rollback();
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
			THangar hLeida = dh.leerHangarPorId(idHangar);

			if (pLeido != null && pLeido.getActivo() && hLeida != null && hLeida.getActivo()) {
				DAOPersonalHangar dph = FactoriaIntegracion.getInstance().crearDAOPersonalHangar();

				if (!dph.comprobarVinculacion(idPersonal, idHangar)) {
					vinculado =  dph.vincular(idPersonal, idHangar);
				}
			}
			
			if (vinculado) t.commit();
			else t.rollback();
		}

		return vinculado;
	}

	@Override
	public boolean desvincularPersonal(TPersonalHangar tPersonalHangar) {//perfe
		int idPersonal = tPersonalHangar.getIdPersonal();
		int idHangar = tPersonalHangar.getIdHangar();
		boolean ok = false;
		if (ValidadorPersonalHangar.comprobarDatos(tPersonalHangar)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();

			TPersonal pLeido = dp.consultarPersonalPorId(idPersonal);
			THangar hLeida = dh.leerHangarPorId(idHangar);

			if (pLeido != null && pLeido.getActivo() && hLeida != null && hLeida.getActivo()) {
				DAOPersonalHangar dph = FactoriaIntegracion.getInstance().crearDAOPersonalHangar();

				if (dph.comprobarVinculacion(idPersonal, idHangar)) {
					ok = dph.desvincular(idPersonal, idHangar);
				}
			}
			if(ok)t.commit();
			else t.rollback();
		}

		return ok;
	}

	@Override
	public boolean modificarPersonal(TPersonal tPersonal) {
		boolean ok = false;
       /* if (UtilidadesN.comprobarId(tAvion.getId()) && ValidadorAvion.comprobarDatos(tAvion)) {
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
        }*/
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
	public List<TPersonal> consultarPersonalExistente() {//revisar
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
		if(UtilidadesN.comprobarId(id_hangar)){
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
			
			THangar leido = dh.leerHangarPorId(id_hangar);
			
			if(leido != null && leido.getActivo()){
				lista = dp.consultarPersonalPorHangar(id_hangar);
			}
			
			t.commit();
			
		}
		return lista;
	}
}