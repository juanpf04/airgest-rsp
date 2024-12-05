package negocio.departamento;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
public class ValidadorDepartamentoTest {
	
	@Test
	public void comprobarDatosTest() {

		assertTrue("Departamento deberia ser correcto", ValidadorDepartamento.comprobarDatos(new TDepartamento(0, "sara16", 3, 4.3, true)));

		assertFalse("Departamento deberia ser incorrecto", ValidadorDepartamento.comprobarDatos(new TDepartamento(0, "16sara", 3, 4.3, true)));
		
		assertFalse("Departamento deberia ser incorrecto", ValidadorDepartamento.comprobarDatos(new TDepartamento(0, "sara16", -6, 4.3, true)));

		assertFalse("Departamento deberia ser incorrecto", ValidadorDepartamento.comprobarDatos(new TDepartamento(0, "sara16", 5, -4.3, true)));
	
	}
}
