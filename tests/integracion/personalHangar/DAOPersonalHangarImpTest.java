package integracion.personalHangar;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import integracion.UtilidadesI;
import integracion.factoria.FactoriaIntegracionImp;
import integracion.hangar.DAOHangar;
import integracion.modeloAerolinea.DAOModeloAerolinea;
import integracion.modeloAerolinea.DAOModeloAerolineaImp;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;

public class DAOPersonalHangarImpTest {
	@Test
	public void comprobarVinculacionTest() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOPersonalHangar dma = FactoriaIntegracionImp.getInstance().crearDAOPersonalHangar();

		boolean vinculado = dma.comprobarVinculacion(6, 1);
		t.commit();
		
		assertTrue("No existe vinculación", vinculado);
	}

	@Test
	public void vincularTest() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOPersonalHangar dma = FactoriaIntegracionImp.getInstance().crearDAOPersonalHangar();

		boolean vinculado = dma.vincular(3, 1);
		t.commit();
		
		assertTrue("No existe vinculación", vinculado);
	}

	@Test
	public void desvincularTest() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOPersonalHangar dma = FactoriaIntegracionImp.getInstance().crearDAOPersonalHangar();

		boolean desvinculado = dma.desvincular(6, 1);
		t.commit();
		
		assertTrue("No existe vinculación", desvinculado);
	}
}
