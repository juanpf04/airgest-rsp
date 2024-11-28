package negocio.producto;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

import integracion.factoria.EMFSingleton;
import negocio.factoria.FactoriaNegocioMall;
import negocio.marca.Marca;
import negocio.marca.TMarca;

public class SAProductoImpTest {

	@Test
	public void alta_producto_test() {
		
		SAProducto sp = FactoriaNegocioMall.getInstance().crearSAProducto();
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		TMarca tMarca = new TMarca(-1, "puma", "EEUU", true);
		Marca marca = new Marca(tMarca);
		em.persist(marca);
		em.getTransaction().commit();
		TProducto tProducto = new TProducto(-1,"prote", 10, 25.99, 1234, marca.getId(), true);
		int id = sp.altaProducto(tProducto);
		
		//Exito
		assertEquals("El id debería ser 1", 1, id);
		
		//Como ya existe el producto
		id = sp.altaProducto(tProducto);
		assertEquals("El id debería ser -1 porque ya existe marca", -1, id);
		
		//Reactivar producto
		tProducto.setRef(1235);
		tProducto.setActivo(false);
		id = sp.altaProducto(tProducto);
		
		tProducto.setActivo(true);
		id = sp.altaProducto(tProducto);
		assertEquals("El id debería ser 2", 2, id);
		
	}
	
	@Test
	public void baja_producto_test() {
		
		SAProducto sp = FactoriaNegocioMall.getInstance().crearSAProducto();
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		TMarca tMarca = new TMarca(-1, "puma", "EEUU", true);
		Marca marca = new Marca(tMarca);
		em.persist(marca);
		em.getTransaction().commit();
		TProducto tProducto = new TProducto(-1,"prote", 10, 25.99, 1234, marca.getId(), true);
		int id = sp.altaProducto(tProducto);
		
		//Exito
		tProducto.setId(id);
		boolean exito = sp.bajaProducto(id);
		assertTrue("se debería modificar marca", exito);
		
		//Fallo producto inactivo
		exito = sp.bajaProducto(id);
		assertFalse("la marca esta inactiva", exito);
		
		//Fallo producto inexistente
		exito = sp.bajaProducto(10);
		assertFalse("no existe marca", exito);
		
		//TODO Fallo poovedores vinculados
		
		
		
		//TODO Fallo lineas de venta activas
		
		
		
	}
	
	
	
}
