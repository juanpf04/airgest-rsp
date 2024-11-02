package negocio.personal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import integracion.UtilidadesI;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.aerolinea.SAAerolinea;
import negocio.aerolinea.SAAerolineaImp;
import negocio.aerolinea.TAerolinea;
import negocio.hangar.SAHangar;
import negocio.hangar.SAHangarImp;
import negocio.hangar.THangar;
import negocio.personalHangar.TPersonalHangar;

public class SAPersonalTest {

	@Test
	public void altaPersonalTest() {
		SAPersonal sp = new SAPersonalImp();
		
		TPersonal personal = new TPLimpieza(1, "12345678P", "fsdfds", true, "algo");
		assertTrue(sp.altaPersonal(personal) > 0);

		assertEquals(sp.altaPersonal(personal), -1);
		
	}

	@Test
	public void bajaPersonalTest() {//perfe
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		SAPersonal sp = new SAPersonalImp();
		t.commit();
		
		assertFalse("no se puede dar de baja un empleaod que no existe", sp.bajaPersonal(3));
		assertTrue("deberia poder darse de baja el personal 2", sp.bajaPersonal(2));
		
	}

	@Test
	public void vincularPersonalTest() {
		SAPersonal sp = new SAPersonalImp();
		SAHangar sh = new SAHangarImp();
		
		THangar h = new THangar(1, "nada", 10, 10, 10, true);
		TPersonal p = new TPLimpieza(1, "11111111Z", "asdfsa", true, "algo");
		
		int idh = sh.altaHangar(h);
		int idp = sp.altaPersonal(p);
		
		TPersonalHangar tph = new TPersonalHangar(idp, idh);
		// prueba exitosa
		assertTrue(sp.vincularPersonal(tph));

		// prueba ya vinculados
		assertFalse(sp.vincularPersonal(tph));

	}

	@Test
	public void desvincularPersonalTest() {//perfe
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		
		SAPersonal sp = new SAPersonalImp();
		TPersonalHangar tph = new TPersonalHangar(1, 1);

//		sp.vincularPersonal(tph); // vinculamos
		
		t.commit();
		// prueba exitosa
		assertTrue("No se han podido desvincular", sp.desvincularPersonal(tph));

		// prueba ya desvinculados
		assertFalse("ya estaban desvinulados", sp.desvincularPersonal(tph));
	}

	@Test
	public void modificarPersonalTest() {//terminado

		SAPersonal sp = new SAPersonalImp();
		TPersonal personal = new TPSeguridad(1, "44444444A", "AAAA", true, 7);
		
		assertTrue("deberia modificarse", sp.modificarPersonal(personal));

	}

	@Test
	public void consultarPersonalPorIdTest() {//hecho
		SAPersonal sp = new SAPersonalImp();

		TPersonal p = sp.consultarPersonalPorId(2);
		
		assertTrue(p != null);
		System.out.println(p);
	}
	
	@Test
	public void cosultarPersonalPorHangarTest(){
		SAPersonal sp = new SAPersonalImp();
		List<TPersonal> list = sp.consultarPersonalPorHangar(1);
		assertEquals("Fallo terrible", 2, list.size());
	}
	
	@Test
	public void consultarPersonalExistenteTest() {//perfee
		
		SAPersonal sp = new SAPersonalImp();
		
		List<TPersonal> list = sp.consultarPersonalExistente();
		assertEquals("Fallo terrible", 1, list.size());
		System.out.println(list);
	}
}
