package integracion.lineaContrato;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import integracion.UtilidadesI;
import integracion.contrato.DAOContrato;
import integracion.factoria.FactoriaIntegracion;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.contrato.TContrato;
import negocio.lineaContrato.TLineaContrato;

public class DAOLineaContratoImpTest {
	@Test
	public void alta_linea_contrato_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		TLineaContrato tlc = new TLineaContrato(4, 1, "16-01-2003", "02-07-2004", 100);
		DAOLineaContrato dlc = FactoriaIntegracion.getInstance().crearDAOLineaContrato();
		boolean ok = dlc.altaLineaContrato(tlc);
		t.commit();
		assertTrue("Existe la linea", ok);
	}
	
	@Test
	public void modificar_linea_contrato_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		TLineaContrato tlc = new TLineaContrato(1, 1, "12-04-2003", "02-07-2004", 150);
		DAOLineaContrato dlc = FactoriaIntegracion.getInstance().crearDAOLineaContrato();
		boolean ok = dlc.modificarLineaContrato(tlc);
		t.commit();
		assertTrue("No se ha modificado", ok);
	}
	
	@Test 
	public void consultar_lineas_por_contrato_test(){
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOLineaContrato dlc = FactoriaIntegracion.getInstance().crearDAOLineaContrato();
		List<TLineaContrato> lista = dlc.consultarLineasPorContrato(1);
		t.commit();
		assertEquals("No se han encontrado todas", 2, lista.size());
		System.out.println(lista);
	}
	
	@Test 
	public void consultar_lineas_por_hangar_test(){
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOLineaContrato dlc = FactoriaIntegracion.getInstance().crearDAOLineaContrato();
		List<TLineaContrato> lista = dlc.consultarLineasPorHangar(1);
		t.commit();
		assertEquals("No se han encontrado todas", 3, lista.size());
		System.out.println(lista);
	}
	
	@Test
	public void leer_linea_contrato_test(){
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOLineaContrato dlc = FactoriaIntegracion.getInstance().crearDAOLineaContrato();
		TLineaContrato tlc = dlc.consultarLineaContrato(1, 1);
		t.commit();
		assertNotNull("No se ha encontrado linea contrato", tlc);
		System.out.println(tlc);
	}
	
	@Test
	public void consultar_contratos_aerolinea_precio_duracion(){
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOLineaContrato dlc = FactoriaIntegracion.getInstance().crearDAOLineaContrato();
		List<TLineaContrato> list = dlc.consultarContratoPorAerolinea(1, 205, 4);
		t.commit();
		assertEquals("Deberia haber 2 lineas contrato", 2, list.size());
		System.out.println(list);
	}
}
