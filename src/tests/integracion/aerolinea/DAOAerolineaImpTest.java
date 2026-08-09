package integracion.aerolinea;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import integracion.factoria.FactoriaIntegracion;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.aerolinea.TAerolinea;

public class DAOAerolineaImpTest {
	
	@Test
	public void alta_aerolinea_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		TAerolinea a = new TAerolinea(-1, "sara", true);
		DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
		int id = da.altaAerolinea(a);
		t.commit();
		assertEquals("No ha devuelto el id correcto", 3, id);
	}
	
	@Test
	public void leerAerolineaPorIdTest(){
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
		TAerolinea ta = da.consultarAerolineaPorId(1);
		t.commit();
		assertNotNull("No existe aerolinea", ta);
		System.out.println(ta);
	}
	

	@Test
	public void leer_aerolinea_por_nombre_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
		TAerolinea ta = da.consultarAerolineaPorNombre("javi");
		t.commit();
		assertNotNull("No existe aerolinea", ta);
		System.out.println(ta);
	}
	
	
	@Test
	public void modificar_aerolinea_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
		TAerolinea ta = new TAerolinea(1, "pepito", false);
		boolean b = da.modificarAerolinea(ta);
		t.commit();
		assertTrue("No se ha modificado aerolínea", b);
		System.out.println(ta);
	}

	@Test
	public void baja_aerolinea_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
		boolean b = da.bajaAerolinea(2);
		t.commit();
		assertTrue("No se ha eliminado aerolínea", b);
	}

	@Test
	public void consultar_todas_aerolineas_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
		List<TAerolinea> lista = da.consultarTodasAerolineas();
		t.commit();
		assertEquals("No coincide el tamaño", 3, lista.size());
		System.out.println(lista);
	}
	
	@Test
	public void consultar_aerolineas_por_modelo_test(){
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
		List<TAerolinea> lista = da.consultarAerolineasPorModelo(1);
		t.commit();
		assertEquals("No coincide el tamaño", 2, lista.size());
		System.out.println(lista);
	}
}
