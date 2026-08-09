package integracion.contrato;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import integracion.factoria.FactoriaIntegracion;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.contrato.TContrato;

public class DAOContratoImpTest {
	@Test
	public void alta_contrato_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		TContrato tc = new TContrato(1);
		DAOContrato dc = FactoriaIntegracion.getInstance().crearDAOContrato();
		int id = dc.altaContrato(tc);
		t.commit();
		assertEquals("No ha devuelto el id correcto", 5, id);
	}
	
	@Test
	public void leer_contrato_por_id_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOContrato dc = FactoriaIntegracion.getInstance().crearDAOContrato();
		TContrato tc = dc.consultarContratoPorId(1);
		t.commit();
		assertNotNull("No existe contrato", tc);
		System.out.println(tc);
	}
	
	@Test
	public void consultar_todos_contratos_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOContrato dc = FactoriaIntegracion.getInstance().crearDAOContrato();
		List<TContrato> lista = dc.consultarTodosContratos();
		t.commit();
		assertEquals("No coincide el tamaño", 4, lista.size());
		System.out.println(lista);
	}
	
	@Test 
	public void consultar_contratos_por_aerolinea_test(){
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOContrato dc = FactoriaIntegracion.getInstance().crearDAOContrato();
		List<TContrato> lista = dc.consultarContratosPorAerolinea(1);
		t.commit();
		assertEquals("No coincide el tamaño", 3, lista.size());
		System.out.println(lista);
	}
	
	@Test
	public void modificar_contrato_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOContrato dc = FactoriaIntegracion.getInstance().crearDAOContrato();
		TContrato tc = new TContrato(1, 3, 80.7);
		boolean b = dc.modificarContrato(tc);
		t.commit();
		assertTrue("No se ha modificado contrato", b);
		System.out.println(tc);
	}
	
}
