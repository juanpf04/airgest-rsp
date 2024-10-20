package negocio.hangar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import integracion.UtilidadesI;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;

public class SAHangarImpTest {
	private boolean inmodificable = true;
	private int id_inmodificable = 0;

	@Test
	public void alta_modelo_test() {
		UtilidadesI.esTest();

		SAHangar sh = new SAHangarImp();

		// Prueba exitosa
		THangar hangar = new THangar(id_inmodificable, "holi", 4, 50.8, 4, inmodificable);
		File carpeta = new File(UtilidadesI.ruta("hangar"));
		File[] lista = carpeta.listFiles();
		assertEquals("debería darse de alta el hangar", lista.length + 1, sh.altaHangar(hangar));

		// Fallo por nombre repetido
		hangar = new THangar(id_inmodificable, "kjfhjksa", 4, 50.8, 4, inmodificable);
		assertEquals("un modelo no activo no se puede modificar", -1, sh.altaHangar(hangar));

		// Reactivar hangar exito
		hangar = new THangar(id_inmodificable, "kjfhjksa", 4, 50.8, 4, inmodificable);
		assertEquals("no se puede modificar un modelo que no existe",5, sh.altaHangar(hangar));
	}
	
	@Test
	public void baja_hangar_test() {//TODAVÍA NO SE PUEDE COMPROBAR
		UtilidadesI.esTest();

		SAHangar sh = new SAHangarImp();

		//Prueba exitosa
		assertTrue("se deberia poder dar de baja", sh.bajaHangar(5));
		
		//Prueba id inexsistente
		assertFalse("no existe un modelo con id20", sh.bajaHangar(20));
		
		//Prueba modelo ya inactivo
				assertFalse("el modelo ya estaba inactivo", sh.bajaHangar(4));
		
		// Prueba avion activo
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
