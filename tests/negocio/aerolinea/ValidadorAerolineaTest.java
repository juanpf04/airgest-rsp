package negocio.aerolinea;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidadorAerolineaTest {

	private boolean noImporta = true;

	@Test
	public void comprobar_aerolinea_test() {

		TAerolinea aerolinea = new TAerolinea(1, "puerco", noImporta);

		assertTrue("puerco", ValidadorAerolinea.comprobarAerolinea(aerolinea));
	}

	@Test
	public void comprobar_nombre_test() {

		assertTrue("miguelito deberia valer", ValidadorAerolinea.comprobarNombre("miguelito"));
		assertFalse("letras y numeros", ValidadorAerolinea.comprobarNombre("TAP AIR PORTUGAL 20"));
		assertFalse("mal1", ValidadorAerolinea.comprobarNombre("!hola"));
		assertFalse("mal2", ValidadorAerolinea.comprobarNombre("boeing7-747"));
		assertFalse("mal3", ValidadorAerolinea.comprobarNombre("_987"));
	}
}
