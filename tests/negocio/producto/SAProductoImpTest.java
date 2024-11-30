package negocio.producto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import integracion.factoria.EMFSingleton;
import negocio.factoria.FactoriaNegocioMall;
import negocio.marca.Marca;
import negocio.marca.TMarca;
import negocio.proveedor.Proveedor;
import negocio.proveedor.TProveedor;

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
		assertEquals("El id debería ser -1 porque ya existe producto", -1, id);
		
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
	
	@Test
	public void consultar_producto_test() {
		
		SAProducto sp = FactoriaNegocioMall.getInstance().crearSAProducto();
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		TMarca tMarca = new TMarca(-1, "puma", "EEUU", true);
		Marca marca = new Marca(tMarca);
		em.persist(marca);
		em.getTransaction().commit();
		TProducto tProducto = new TProducto(-1,"prote", 10, 25.99, 1234, marca.getId(), true);
		sp.altaProducto(tProducto);
				
		//Exito
		TProducto p = sp.consultarProductoPorId(1);
		System.out.println(p.getId() + " " + p.getNombre() + " " + p.getRef());
		assertEquals("La ref deberia ser 1234", 1234, p.getRef());
		
		//No existe produto
		p = sp.consultarProductoPorId(10);
		assertNull("deberia ser null", p);
		
	}
	
	@Test
	public void consultar_todos_productos_test(){
		
		SAProducto sp = FactoriaNegocioMall.getInstance().crearSAProducto();
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		TMarca tMarca = new TMarca(-1, "puma", "EEUU", true);
		Marca marca = new Marca(tMarca);
		em.persist(marca);
		em.getTransaction().commit();
		TProducto tProducto = new TProducto(-1,"prote", 10, 25.99, 1, marca.getId(), true);
		sp.altaProducto(tProducto);
		
		tProducto = new TProducto(-1,"cafe", 10, 1.99, 2, marca.getId(), true);
		sp.altaProducto(tProducto);
		
		tProducto = new TProducto(-1,"napolitana", 10, 0.99, 3, marca.getId(), true);
		sp.altaProducto(tProducto);
		
		List<TProducto> lista = sp.consultarProductos();
		
		for(TProducto p : lista){
			System.out.println(p.getId() + " " + p.getNombre() + " " + p.getRef());
		}
		assertEquals("debería haber 3 productos", 3, lista.size());
		
	}
	
	@Test
	public void modificar_producto_test(){
	
		SAProducto sp = FactoriaNegocioMall.getInstance().crearSAProducto();
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		TMarca tMarca = new TMarca(-1, "puma", "EEUU", true);
		Marca marca = new Marca(tMarca);
		em.persist(marca);
		em.getTransaction().commit();
		TProducto tProducto = new TProducto(-1,"prote", 10, 25.99, 1, marca.getId(), true);
		sp.altaProducto(tProducto);
		
		//exito
		tProducto.setId(1);
		tProducto.setNombre("cafe");
		tProducto.setRef(2);
		tProducto.setPrecio(1.99);
		tProducto.setStock(200);
		boolean exito = sp.modificarProducto(tProducto);
		assertTrue("se deberia modificar producto", exito);
		
		//Exito pero no cambio la ref
		tProducto.setNombre("napolitana");
		tProducto.setPrecio(0.99);
		tProducto.setStock(500);
		exito = sp.modificarProducto(tProducto);
		assertTrue("se deberia modificar producto", exito);
		
		//Fallo no existe producto
		tProducto.setId(10);
		exito = sp.modificarProducto(tProducto);
		assertFalse("no se deberia modificar producto", exito);
		
		//Fallo no existe marca
		tProducto.setIdMarca(2);
		exito = sp.modificarProducto(tProducto);
		assertFalse("no se deberia modificar producto", exito);
		
		//Fallo marca inactiva
		em.getTransaction().begin();
		tProducto.setId(1);
		tProducto.setIdMarca(2);
		tMarca = new TMarca(-1, "nike", "USA", false);
		Marca marca2 = new Marca(tMarca);
		em.persist(marca2);
		em.getTransaction().commit();
		exito = sp.modificarProducto(tProducto);
		assertFalse("no se deberia modificar producto", exito);
		
		//Fallo producto inactivo
		sp.bajaProducto(1);
		exito = sp.modificarProducto(tProducto);
		assertFalse("no se deberia modificar producto", exito);
		
	}
	
	@Test
	public void consultar_por_marca_test(){
		
		SAProducto sp = FactoriaNegocioMall.getInstance().crearSAProducto();
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		
		//Marca1
		em.getTransaction().begin();
		TMarca tMarca = new TMarca(-1, "puma", "EEUU", true);
		Marca marca = new Marca(tMarca);
		em.persist(marca);
		em.getTransaction().commit();
		
		//Marca2
		em.getTransaction().begin();
		tMarca = new TMarca(-1, "nike", "USA", true);
		Marca marca2 = new Marca(tMarca);
		em.persist(marca2);
		em.getTransaction().commit();
		
		TProducto tProducto = new TProducto(-1,"hamburguesa", 10, 5.99, 1, marca.getId(), true);
		sp.altaProducto(tProducto);
		
		tProducto = new TProducto(-1,"cafe", 10, 1.99, 2, marca.getId(), true);
		sp.altaProducto(tProducto);
		
		tProducto = new TProducto(-1,"napolitana", 10, 0.99, 3, marca.getId(), true);
		sp.altaProducto(tProducto);
		
		tProducto = new TProducto(-1,"palmera", 10, 0.99, 4, marca2.getId(), true);
		sp.altaProducto(tProducto);
		
		tProducto = new TProducto(-1,"zumo", 10, 1.99, 5, marca2.getId(), true);
		sp.altaProducto(tProducto);
		
		//Exito
		List<TProducto> lista = sp.consultarProductosPorMarca(marca.getId());
		
		for(TProducto p : lista){
			System.out.println(p.getId() + " " + p.getNombre() + " " + p.getRef());
		}
		assertEquals("debería haber 3 productos", 3, lista.size());
		
		//Fallo marca no existe
		lista = sp.consultarProductosPorMarca(10);
		
		assertTrue("debería haber 0 productos", lista.isEmpty());
		
		//TODO no sé si hay que hacer Fallo por marca inactiva 
		//(no se puede dar de baja una marca con productos "vinculados")
		
		
	}
	
	@Test
	public void consultar_por_proveedor_test(){
		
		SAProducto sp = FactoriaNegocioMall.getInstance().crearSAProducto();
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		
		//Marca1
		em.getTransaction().begin();
		TMarca tMarca = new TMarca(-1, "puma", "EEUU", true);
		Marca marca = new Marca(tMarca);
		em.persist(marca);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		TProveedor tProveedor = new TProveedor(-1, "Arturo", true);
		Proveedor prov = new Proveedor(tProveedor);
		em.persist(prov);
		em.getTransaction().commit();
		
		
		sp.altaProducto(new TProducto(-1,"hamburguesa", 10, 5.99, 1, marca.getId(), true));
		sp.altaProducto(new TProducto(-1,"cafe", 10, 5.99, 2, marca.getId(), true));
		sp.altaProducto(new TProducto(-1,"zumo", 10, 1.99, 5, marca.getId(), true));
		
		em.getTransaction().begin();
		
		Producto p1 = em.find(Producto.class, 1);
		p1.getProveedores().add(prov);
		Producto p2 = em.find(Producto.class, 2);
		p2.getProveedores().add(prov);
		Producto p3 = em.find(Producto.class, 3);
		p3.getProveedores().add(prov);
		
		prov.getProductos().add(p1);
		prov.getProductos().add(p2);
		prov.getProductos().add(p3);
		em.getTransaction().commit();
		
		//Exito
		List<TProducto> l = sp.consultarProductosPorProveedor(1);
		for(TProducto p : l){
			System.out.println(p.getId() + " " + p.getNombre() + " " + p.getRef());
		}
		assertEquals("debería haber 3 productos", 3, l.size());
		
	}
	
}
