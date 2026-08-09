package negocio.marca;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import integracion.factoria.EMFSingleton;
import negocio.factoria.FactoriaNegocioMall;
import negocio.producto.Producto;
import negocio.producto.TProducto;

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
		tMarca.setOrigen("espanya");
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
	
	@Test
	public void bajaMarcaTest(){
		SAMarca sm = FactoriaNegocioMall.getInstance().crearSAMarca();
		
		TMarca tMarca = new TMarca(-1, "puma", "EEUU", true);
		sm.altaMarca(tMarca);
		
		// exito
		tMarca.setId(1);
		boolean exito = sm.bajaMarca(1);
		assertTrue("se debería modificar marca", exito);
		
		// fallo marca inexistente
		exito = sm.bajaMarca(10);
		assertFalse("no existe marca", exito);
		
		// fallo marca inactiva
		tMarca = new TMarca(-1, "adidas", "EEUU", false);
		sm.altaMarca(tMarca);
		exito = sm.bajaMarca(2);
		assertFalse("la marca esta inactiva", exito);
		
		// fallo tiene productos inactivos
		tMarca = new TMarca(-1, "kalise", "espanya", true);
		sm.altaMarca(tMarca);
		
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		Marca marca = em.find(Marca.class, 3);
		Producto p = new Producto(new TProducto(1, "cocacola", 5, 2.20, 82872, 1, true));
		p.setMarca(marca);
		em.persist(p);
		marca.getProductos().add(p);
		em.getTransaction().commit();
		
		exito = sm.bajaMarca(3);
		assertFalse("la marca tiene productos activos", exito);
	}
}
