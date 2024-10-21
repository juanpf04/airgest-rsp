package integracion.modeloAerolinea;

import org.junit.Test;

import integracion.factoria.FactoriaIntegracion;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;

import static org.junit.Assert.*;

public class DAOModeloAerolineaImpTest {

	@Test
	public void comprobarVinculacionTest() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOModeloAerolinea da = FactoriaIntegracion.getInstance().crearDAOModeloAerolinea();
		boolean b = da.comprobarVinculacion(2, 3);
		t.commit();
		assertTrue("No estan vinculados aerolínea y modelo", b);
	}

	@Test
	public void vincular() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOModeloAerolinea da = FactoriaIntegracion.getInstance().crearDAOModeloAerolinea();
		boolean b = da.vincular(2, 1);
		t.commit();
		assertTrue("No se ha vinculado aerolínea y modelo", b);
	}

	@Test
	public void desvincular() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOModeloAerolinea da = FactoriaIntegracion.getInstance().crearDAOModeloAerolinea();
		boolean b = da.desvincular(2, 1);
		t.commit();

		assertTrue("No se ha podido desvincular", b);
	}
}
