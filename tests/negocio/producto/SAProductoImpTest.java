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
		
		assertEquals("El id debería ser 1", 1, id);
		
	}
	
}
