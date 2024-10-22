package negocio.hangar;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;

public class SAHangarImpTest {
	private boolean inmodificable = true;
	private int id_inmodificable = 0;

	@Test
	public void alta_modelo_test() {

		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		SAHangar sh = new SAHangarImp();

		t.commit();

		// Prueba exitosa
		THangar hangar = new THangar(id_inmodificable, "kjfhjksa", 4, 50.8, 4, inmodificable);
		assertEquals("debería darse de alta el hangar", 4, sh.altaHangar(hangar));

		
		// Fallo por nombre repetido
		hangar = new THangar(id_inmodificable, "javi", 4, 50.8, 4, inmodificable);
		assertEquals("un hangar no activo no se puede modificar", -1, sh.altaHangar(hangar));

		
		// Reactivar hangar exito
		hangar = new THangar(id_inmodificable, "javi", 5, 60.8, 7, inmodificable);
		assertEquals("no se puede modificar un hangar que no existe",3, sh.altaHangar(hangar));
	}
	
	@Test
	public void baja_hangar_test() {//TODAVÍA NO SE PUEDE COMPROBAR
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		SAHangar sh = new SAHangarImp();

		t.commit();
		//Prueba exitosa
		//assertTrue("se deberia poder dar de baja", sh.bajaHangar(1));

		//Prueba id inexsistente
		//assertFalse("no existe un hangar con id20", sh.bajaHangar(20));
		
		//Prueba modelo ya inactivo
		//sh.bajaHangar(4);
		//assertFalse("el hangar ya estaba inactivo", sh.bajaHangar(4));
		
		// Prueba avion activo                                                 				TODO NO LO PODEMOS PROBAR TODAVIA
		assertFalse("No se puede dar de baja hangar con aviones activos", sh.bajaHangar(1));
	}
	
	@Test
	public void modificar_hangar_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		SAHangar sh = new SAHangarImp();

		// Prueba exitosa
		THangar hangar = new THangar(1, "paula", 4, 50.8, 4, false);
		t.commit();
		assertTrue("debería modificarse hangar", sh.modificarHangar(hangar));

		// Fallo por hangar no activo
		hangar = new THangar(1, "adiosss", 4, 50.8, 4, true);
		t.commit();
		assertFalse("un hangar no activo no se puede modificar", sh.modificarHangar(hangar));

		// Fallo id no existente
		hangar = new THangar(200, "kjfhjksa", 4, 50.8, 4, inmodificable);
		t.commit();
		assertFalse("no se puede modificar un modelo que no existe", sh.modificarHangar(hangar));

		// Fallo nombre existente
		hangar = new THangar(1, "javi", 4, 50.8, 4, inmodificable);
		t.commit();
		assertFalse("Se ha modificado el modelo, al estar el nombre repetido, no debería dejar",
				sh.modificarHangar(hangar));
	}
	
	@Test 
	public void consultar_hangar_por_id_test(){
		SAHangar sh = new SAHangarImp();
		
		//Prueba exitosa
		THangar hangar = sh.consultarHangarPorId(3);
		assertEquals("no se ha encontrado hangar", 5, hangar.getStock());
		
		//Prueba no existe hangar
		hangar = sh.consultarHangarPorId(20);
		assertNull("si existe hangar", hangar);
	}
	
	@Test 
	public void consultar_todos_hangares_test(){
		SAHangar sh = new SAHangarImp();
		
		List<THangar> list = sh.consultarTodosHangares();
		assertEquals("Fallo terrible", 4, list.size());
	}
}
