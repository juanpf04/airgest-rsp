package integracion.hangar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import integracion.factoria.FactoriaIntegracionImp;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.hangar.THangar;

public class DAOHangarImpTest {
	@Test
	public void alta_hangar_test() {
		
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOHangar dh = FactoriaIntegracionImp.getInstance().crearDAOHangar();

		THangar hangar = new THangar(0, "adiossss", 4, 50.8, 4, true);

		int id = dh.altaHangar(hangar);
		t.commit();
		assertEquals("No ha devuelto el id correcto", 1, id);
	}
	
	@Test
	public void baja_hangar_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOHangar dh = FactoriaIntegracionImp.getInstance().crearDAOHangar();

		boolean id = dh.bajaHangar(1);
		t.commit();
		assertTrue("No se ha podido dar de baja", id);
	}
	
	@Test
	public void leer_hangar_por_id_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOHangar dh = FactoriaIntegracionImp.getInstance().crearDAOHangar();

		THangar id = dh.leerHangarPorId(3);
		t.commit();
		assertTrue(id != null);
		System.out.println(id);
		}
	
	@Test
	public void leerHangarPorDireccion_test(){
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOHangar dh = FactoriaIntegracionImp.getInstance().crearDAOHangar();

		THangar id = dh.leerHangarPorDireccion("adiosss");
		t.commit();
		assertTrue(id != null);
		System.out.println(id);
	}
	
	@Test
	public void consultar_todos_hangares_test() {

		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOHangar dh = FactoriaIntegracionImp.getInstance().crearDAOHangar();

		List<THangar> id = dh.consultarTodosHangares();
		t.commit();

		assertEquals("No hay el mismo numero de entidades que en la base de datos", 3, id.size());
		System.out.println(id);
	}
	
	@Test
	public void modificar_hangar_test() {
		
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOHangar dh = FactoriaIntegracionImp.getInstance().crearDAOHangar();
		
		THangar hangar = new THangar(1, "adios", 5, 50.8, 4, true);

		boolean id = dh.modificarHangar(hangar);
		t.commit();

		assertTrue("No se ha modificado", id);
		System.out.println(hangar);
	}
	
	@Test
	public void actualizar_stock_test(){
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOHangar dh = FactoriaIntegracionImp.getInstance().crearDAOHangar();
		
		boolean ok = dh.actualizarStock(1, -1);
		t.commit();

		assertTrue("No encuentra hangar", ok);
	}
}
