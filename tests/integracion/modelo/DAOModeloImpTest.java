package integracion.modelo;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import integracion.UtilidadesI;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.modelo.TModelo;

public class DAOModeloImpTest {

	@Test
	public void leer_modelo_por_nombre_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOModelo dm = new DAOModeloImp();
		
		TModelo m = dm.leerModeloPorNombre("florencia");
		
		t.commit();
		assertTrue(m != null);
		
		System.out.println(m);
	}

	@Test
	public void alta_modelo_test() {

		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		
		DAOModelo dm = new DAOModeloImp();

		TModelo modelo = new TModelo(0, "arturo", "siuuuu", true);

		int id = dm.altaModelo(modelo);
		t.commit();
		
		assertEquals("No ha devuelto el id correcto", 2, id);
	}

	@Test
	public void modificar_modelo_test() {

		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOModelo dm = new DAOModeloImp();
		TModelo modelo = new TModelo(1, "florencia", "hola", true);
		
		boolean ok = dm.modificarModelo(modelo);
		t.commit();

		assertTrue("No se puede modificar", ok);
	}

	@Test
	public void baja_modelo_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOModelo dm = new DAOModeloImp();
		boolean ok = dm.bajaModelo(4);
		t.commit();
		assertTrue("No se ha dado de baja", ok);
	}

	@Test
	public void consultar_todos_modelos_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOModelo dm = new DAOModeloImp();
		
		List<TModelo> m = dm.consultarTodosModelos();
		
		t.commit();
		assertTrue(m.size() == 4);
		
		System.out.println(m);
	}
	
	@Test
	public void leer_modelo_por_id_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOModelo dm = new DAOModeloImp();
		
		TModelo m = dm.leerModeloPorId(2);
		
		t.commit();
		assertTrue(m != null);
		
		System.out.println(m);
	}

	public void consultar_modelos_por_aerolinea(){
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOModelo dm = new DAOModeloImp();
		
		List<TModelo> m = dm.consultarModelosPorAerolinea(1);
		
		t.commit();
		assertTrue(m.size() > 0);
		
		System.out.println(m);
		
	}
	
}
