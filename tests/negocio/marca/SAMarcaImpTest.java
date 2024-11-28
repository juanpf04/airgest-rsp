package negocio.marca;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import negocio.factoria.FactoriaNegocioMall;

public class SAMarcaImpTest {

	@Test
	public void altaMarcaTest(){
		SAMarca sm = FactoriaNegocioMall.getInstance().crearSAMarca();
		
		TMarca tMarca = new TMarca(-1, "puma", "EEUU", true);
		int id = sm.altaMarca(tMarca);
		
		// Exito
		assertEquals("El id debería ser 1", 1, id);
		
		// Caso ya existe la marca
		id = sm.altaMarca(tMarca);
		assertEquals("El id debería ser -1 porque ya existe marca", -1, id);
		
		// Reactivar marca
		tMarca.setNombre("adidas");
		tMarca.setActivo(false);
		sm.altaMarca(tMarca);
		
		tMarca.setActivo(true);
		id = sm.altaMarca(tMarca);
		assertEquals("El id debería ser 2", 2, id);
		
	}
	
	@Test
	public void consultarMarcaPorIdTest(){
		SAMarca sm = FactoriaNegocioMall.getInstance().crearSAMarca();
		
		TMarca tMarca = new TMarca(-1, "puma", "EEUU", true);
		sm.altaMarca(tMarca);
		
		// Exito
		tMarca = sm.consultarMarcaPorId(1);
		
		assertEquals("El nombre debería ser puma", "puma", tMarca.getNombre());
		
		// No existe marca
		tMarca = sm.consultarMarcaPorId(10);
		assertNull("debería ser null", tMarca);
	}
	
	@Test
	public void consultarMarcasTest(){
		SAMarca sm = FactoriaNegocioMall.getInstance().crearSAMarca();
		
		TMarca tMarca = new TMarca(-1, "puma", "EEUU", true);
		sm.altaMarca(tMarca);
		tMarca = new TMarca(-1, "adidas", "EEUU", true);
		sm.altaMarca(tMarca);
		tMarca = new TMarca(-1, "nike", "EEUU", true);
		sm.altaMarca(tMarca);
		
		List<TMarca> lista = sm.consultarMarcas();
		assertEquals("debería haber 3 marcas", 3, lista.size());
	}
	
	@Test
	public void modificarMarcaTest(){
		SAMarca sm = FactoriaNegocioMall.getInstance().crearSAMarca();
		
		TMarca tMarca = new TMarca(-1, "puma", "EEUU", true);
		sm.altaMarca(tMarca);
		
		// exito
		tMarca.setId(1);
		tMarca.setOrigen("españa");
		tMarca.setNombre("nike");
		boolean exito = sm.modificarMarca(tMarca);
		assertTrue("se debería modificar marca", exito);
		
		// exito pero no modifico el nombre
		tMarca.setOrigen("filipinas");
		exito = sm.modificarMarca(tMarca);
		assertTrue("se debería modificar marca", exito);
		
		// fallo no existe marca
		tMarca.setId(10);
		exito = sm.modificarMarca(tMarca);
		assertFalse("se debería modificar marca", exito);
		
		// fallo marca inactiva
		tMarca = new TMarca(-1, "puma", "EEUU", false);
		sm.altaMarca(tMarca);
		tMarca.setId(2);
		tMarca.setOrigen("francia");
		exito = sm.modificarMarca(tMarca);
		assertFalse("se debería modificar marca", exito);
	}
}
