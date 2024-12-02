package negocio.empleado;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidadorEmpleadoTest {
	@Test
	public void comprobarDatosTest(){
		TGerente empleado = new TGerente(0, 1, 2, 1, true, 2, 2);
		assertTrue("ok", ValidadorEmpleado.comprobarDatos(empleado));
		
		TDependiente empleado2 = new TDependiente(0, 1, 2, 1, true, 2, true);
		assertTrue("ok", ValidadorEmpleado.comprobarDatos(empleado2));
		
		TDependiente empleado3 = new TDependiente(0, 1, 2, 1, true, 2, false);
		assertTrue("ok", ValidadorEmpleado.comprobarDatos(empleado3));
		
		TGerente empleado4 = new TGerente(0, 1, 2, 1, true, 2, 0);
		assertTrue("ok", ValidadorEmpleado.comprobarDatos(empleado4));
	}
	
	@Test
	public void comprobarTagTest(){
		assertTrue("debería ser válido", ValidadorEmpleado.comprobarTag(2));
	}
	
	@Test
	public void comprobarHorasMensualesTest(){
		assertTrue("debería ser válido", ValidadorEmpleado.comprobarHorasMensuales(20));
		assertFalse("debería dar error", ValidadorEmpleado.comprobarHorasMensuales(0));
	}
	
	@Test
	public void comprobarIdDepartamentoTest(){
		assertTrue("debería ser válido", ValidadorEmpleado.comprobarIdDepartamento(2));
		assertFalse("debería dar error", ValidadorEmpleado.comprobarIdDepartamento(0));
	}
	
	@Test
	public void comprobarDespachoTest(){
		assertTrue("debería ser válido", ValidadorEmpleado.comprobarDespacho(2));
		assertFalse("debería dar error", ValidadorEmpleado.comprobarDespacho(0));
	}
	
	@Test
	public void comprobarHorasExtraTest(){
		assertTrue("debería ser válido", ValidadorEmpleado.comprobarHorasExtra(20));
		assertTrue("debería ser válido", ValidadorEmpleado.comprobarHorasExtra(0));
		assertFalse("debería dar error", ValidadorEmpleado.comprobarHorasExtra(-1));
	}
	
	@Test
	public void comprobarGerenteTest(){
		TGerente empleado = new TGerente(0, 1, 2, 1, true, 2, 2);
		TGerente empleado2 = new TGerente(0, 1, 2, 1, true, 0, 2);
		assertTrue("debería ser válido", ValidadorEmpleado.comprobarGerente(empleado));
		assertFalse("debería dar error", ValidadorEmpleado.comprobarGerente(empleado2));
	}
	
	@Test
	public void comprobarSeccionTest(){
		assertTrue("debería ser válido", ValidadorEmpleado.comprobarSeccion(2));
		assertFalse("debería dar error", ValidadorEmpleado.comprobarSeccion(0));
	}
	
	@Test
	public void comprobarDependienteTest(){
		TDependiente empleado = new TDependiente(0, 1, 2, 1, true, 2, true);
		TDependiente empleado2 = new TDependiente(0, 1, 2, 1, true, 0, false);
		assertTrue("debería ser válido", ValidadorEmpleado.comprobarDependiente(empleado));
		assertFalse("debería dar error", ValidadorEmpleado.comprobarDependiente(empleado2));
	}
}
