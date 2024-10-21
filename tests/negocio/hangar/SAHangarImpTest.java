package negocio.hangar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


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
		THangar hangar = new THangar(id_inmodificable, "holi", 4, 50.8, 4, inmodificable);
		assertEquals("debería darse de alta el hangar", 5, sh.altaHangar(hangar));

		// Fallo por nombre repetido
		hangar = new THangar(id_inmodificable, "holi", 4, 50.8, 4, inmodificable);
		assertEquals("un modelo no activo no se puede modificar", -1, sh.altaHangar(hangar));

		// Reactivar hangar exito
		hangar = new THangar(id_inmodificable, "adiosss", 5, 60.8, 7, inmodificable);
		assertEquals("no se puede modificar un modelo que no existe",1, sh.altaHangar(hangar));
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
		//assertFalse("no existe un modelo con id20", sh.bajaHangar(20));
		
		//Prueba modelo ya inactivo
		//sh.bajaHangar(4);
		//assertFalse("el modelo ya estaba inactivo", sh.bajaHangar(4));
		
		// Prueba avion activo                                                 				TODO NO LO PODEMOS PROBAR TODAVIA
		assertFalse("No se puede dar de baja modelo con aviones activos", sh.bajaHangar(1));
	}
	
	@Test
	public void modificar_hangar_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		SAHangar sh = new SAHangarImp();

		// Prueba exitosa
//		THangar hangar = new THangar(1, "adiosss", 4, 50.8, 4, false);
//		t.commit();
//		assertTrue("debería modificarse modelo", sh.modificarHangar(hangar));

		// Fallo por hangar no activo
//		THangar hangar = new THangar(2, "adiosss", 4, 50.8, 4, true);
//		t.commit();
//		assertFalse("un modelo no activo no se puede modificar", sh.modificarHangar(hangar));
//
//		// Fallo id no existente
//		THangar hangar = new THangar(200, "kjfhjksa", 4, 50.8, 4, inmodificable);
//		t.commit();
//		assertFalse("no se puede modificar un modelo que no existe", sh.modificarHangar(hangar));

////		// Fallo nombre existente
		THangar hangar = new THangar(1, "adiosss", 4, 50.8, 4, inmodificable);
		t.commit();
		assertFalse("Se ha modificado el modelo, al estar el nombre repetido, no debería dejar",
				sh.modificarHangar(hangar));
	}
}
