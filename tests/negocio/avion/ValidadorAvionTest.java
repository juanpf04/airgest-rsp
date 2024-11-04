package negocio.avion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidadorAvionTest {

	private boolean noImporta = true;

	@Test
	public void comprobar_datos_test() {
		

		assertTrue("Avion comercial correcto", ValidadorAvion.comprobarDatos(
				new TAComercial(0, 80, "06-12-2004", "avionComercial", "EC-123", noImporta, 1, 1, 1, "Rock")));

		assertTrue("Avion privado correcto", ValidadorAvion.comprobarDatos(
				new TAPrivado(0, 8, "06-12-2004", "avionPrivado", "EC-12", noImporta, 1, 1, 1, "charlie", 23423)));
	}

	@Test
	public void comprobar_comercial_test() {
		

		assertTrue("Avion comercial correcto", ValidadorAvion.comprobarComercial(
				new TAComercial(0, 80, "06-12-2004", "avionComercial", "EC-123", noImporta, 1, 1, 1, "Trap")));
	}

	@Test
	public void comprobar_privado_test() {
		

		assertTrue("Avion privado correcto", ValidadorAvion.comprobarPrivado(
				new TAPrivado(0, 8, "06-12-2004", "avionPrivado", "EC-12", noImporta, 1, 1, 1, "charlie", 23423)));
	}

	@Test
	public void comprobar_info_test() {
		
		assertTrue("Avion correcto", ValidadorAvion
				.comprobarInfo(new TAvion(0, 8, "06-12-2004", "avionPrivado", "EC-12", noImporta, 1, 1, 1)));
	}

	@Test
	public void comprobar_carnet_test() {
		
		// Carnet valido
		assertTrue("Id carnet correcto", ValidadorAvion.comprobarCarnet(
				new TAPrivado(0, 8, "06-12-2004", "avionPrivado", "EC-12", noImporta, 1, 1, 1, "charlie", 23423)));

		// Carnet no puede ser negativo
		assertFalse("Id carnet correcto", ValidadorAvion.comprobarCarnet(
				new TAPrivado(0, 8, "06-12-2004", "avionPrivado", "EC-12", noImporta, 1, 1, 1, "charlie", -7)));

		// Carnet no puede ser 0
		assertFalse("Id carnet correcto", ValidadorAvion.comprobarCarnet(
				new TAPrivado(0, 8, "06-12-2004", "avionPrivado", "EC-12", noImporta, 1, 1, 1, "charlie", 0)));
	}

	@Test
	public void comprobar_empresa_test() {
		
		// Trabajadores valido
		assertTrue("numTrabajadores", ValidadorAvion.comprobarEmpresa(
				new TAComercial(0, 80, "06-12-2004", "avionComercial", "EC-123", noImporta, 1, 1, 1, "Windows")));

		// Trabajadores no puede ser negativo
		assertFalse("numTrabajadores", ValidadorAvion.comprobarEmpresa(
				new TAComercial(0, 80, "06-12-2004", "avionComercial", "EC-123", noImporta, 1, 1, 1, "!Google")));

		// Trabajadores no puede ser 0
		assertFalse("numTrabajadores", ValidadorAvion.comprobarEmpresa(
				new TAComercial(0, 80, "06-12-2004", "avionComercial", "EC-123", noImporta, 1, 1, 1, "23-Amazon")));
	}

	@Test
	public void comprobar_asientos_test() {
		

		// numAsientos valido
		assertTrue(" numAsientos 1 deberia ser valido", ValidadorAvion.comprobarAsientos(1));
		// numAsientos fallo
		assertFalse("no puede tener numAsientos 0", ValidadorAvion.comprobarAsientos(0));
		// numAsientos fallo
		assertFalse("no puede tener numAsientos negativo", ValidadorAvion.comprobarAsientos(-1));
	}

	@Test
	public void comprobar_matricula_test() {
		
		// matricula valido
		assertTrue("matrícula deberia ser valido", ValidadorAvion.comprobarMatricula("EC-1"));
		// matricula valido
		assertTrue("matrícula deberia ser valido", ValidadorAvion.comprobarMatricula("EC-1V"));
		// matricula fallo
		assertFalse("orden invertido", ValidadorAvion.comprobarMatricula("1-EC"));
		// matricula fallo
		assertFalse("falta guion", ValidadorAvion.comprobarMatricula("EC1"));
		// matricula fallo
		assertFalse("falta letras", ValidadorAvion.comprobarMatricula("-1"));
		// matricula fallo
		assertFalse("falta numeros", ValidadorAvion.comprobarMatricula("EC-"));
		// matricula fallo
		assertFalse("falta guion y numeros", ValidadorAvion.comprobarMatricula("EC"));
		// matricula fallo
		assertFalse("falta guion y letras", ValidadorAvion.comprobarMatricula("5"));
		// matricula fallo
		assertFalse("no pueden haber numeros antes del guion", ValidadorAvion.comprobarMatricula("EC2-1"));
	}

}
