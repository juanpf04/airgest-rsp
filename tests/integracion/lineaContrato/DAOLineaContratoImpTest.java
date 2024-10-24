package integracion.lineaContrato;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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
		TLineaContrato tlc = new TLineaContrato(1, 1, "16-01-2003", "02-07-2004", 10000);
		DAOLineaContrato dlc = FactoriaIntegracion.getInstance().crearDAOLineaContrato();
		boolean ok = dlc.altaLineaContrato(tlc);
		t.commit();
		assertTrue("Existe la linea", ok);
	}
	
	@Test
	public void modificar_linea_contrato_test() {
		UtilidadesI.esTest();
		DAOLineaContrato dc = new DAOLineaContratoImp();

		LocalDate fecha_ini = LocalDate.of(2024, 4, 12);
		LocalDate fecha_fin = LocalDate.of(2024, 9, 23);
		
		//TLineaContrato linea = new TLineaContrato(1, 2, fecha_ini, fecha_fin, 124.5);

		//assertTrue("Ha leido mal el fichero", dc.modificarLineaContrato(linea));
	}
	
	@Test 
	public void consultar_lineas_por_contrato_test(){
		UtilidadesI.esTest();

		DAOLineaContrato dc = new DAOLineaContratoImp();
		
		List<TLineaContrato> lineas = dc.leerLineasPorContrato(1);
		
		assertEquals("tiene que haber 1 linea con contrato 1", 1, lineas.size());
	}
	
	@Test 
	public void consultar_lineas_por_hangar_test(){
		UtilidadesI.esTest();

		DAOLineaContrato dc = new DAOLineaContratoImp();
		
		List<TLineaContrato> lineas = dc.leerLineasPorHangar(2);
		
		assertEquals("tiene que haber 2 linea con hangar 2", 2, lineas.size());
	}
	
	@Test
	public void leer_linea_contrato_test(){
		UtilidadesI.esTest();

		DAOLineaContrato dc = new DAOLineaContratoImp();
		
		//Prueba exitosa
		assertTrue("Existe la linea", dc.leerLineaContrato(1, 1));
		
		//Preuba fallida
		assertFalse("No existe la linea", dc.leerLineaContrato(9, 1));
	}
}
