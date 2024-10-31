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
import negocio.personalHangar.TPersonalHangar;

public class SAPersonalTest {

	@Test
	public void altaPersonalTest() {
		UtilidadesI.esTest();

		SAPersonal sp = new SAPersonalImp();
		TPersonal personal = new TPLimpieza(0, "12345678P", "fsdfds", true, "algo");
		assertEquals("no se puede dar de alta personal con el mismo id empleado", -1, sp.altaPersonal(personal));
		personal = new TPSeguridad(0, "12345678P", "yeah", true, 4535);
		assertEquals("el empleado con id 4 ya existia pero estaba dado de baja", 4, sp.altaPersonal(personal));

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
		UtilidadesI.esTest();

		SAPersonal sp = new SAPersonalImp();
		TPersonalHangar tph = new TPersonalHangar(2, 2);

		sp.desvincularPersonal(tph); // desvinculamos
		
		// prueba exitosa
		assertTrue("No se han podido vincular", sp.vincularPersonal(tph));

		// prueba ya vinculados
		assertFalse("ya estaban vinulados", sp.vincularPersonal(tph));

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
	public void consultarPersonalPorIdTest() {
		UtilidadesI.esTest();

		SAPersonal sp = new SAPersonalImp();
		
		assertEquals("no existe personal con id 500", null, sp.consultarPersonalPorId(500));
		assertEquals("el personal 1 tiene el area asignada seguridad", "Seguridad", sp.consultarPersonalPorId(1).getAreaAsignada());
		
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
