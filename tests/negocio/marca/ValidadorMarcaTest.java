package negocio.marca;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidadorMarcaTest {

	@Test
	public void comprobarDatosTest() {

		assertTrue("Marca correcta", ValidadorMarca.comprobarDatos(new TMarca(0, "Puma", "Alemania", true)));

		assertTrue("Marca correcta", ValidadorMarca.comprobarDatos(new TMarca(0, "7-Up", "EEUU", true)));

		assertFalse("Marca incorrecta",
				ValidadorMarca.comprobarDatos(new TMarca(0, "nombre_marca", "Japon", true)));

		assertFalse("Marca incorrecta",
				ValidadorMarca.comprobarDatos(new TMarca(0, "Nike", "El patio de mi casa", true)));
	}

}
