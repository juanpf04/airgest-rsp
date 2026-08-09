package negocio.proveedor;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import integracion.factoria.EMFSingleton;
import negocio.factoria.FactoriaNegocioMall;
import negocio.producto.Producto;
import negocio.producto.TProducto;

public class SAProveedorImpTest {
	
	@Test
	public void altaProveedorTest(){
		SAProveedor sa = FactoriaNegocioMall.getInstance().crearSAProveedor();
		
		//EXITO NACIONAL
		TNacional tNacional = new TNacional(-1, "Espanya", true, "28008");
		int id = sa.altaProveedor(tNacional);
		assertEquals("El id debería ser 1", 1, id);
		
		//EXITO INTERNACIONAL
		TInternacional tInternacional = new TInternacional(-1, "RusiaAvion", true, "Rusia", 0.2);
		int id2 = sa.altaProveedor(tInternacional);
		assertEquals("El id debería ser 2", 2, id2);
		
		//YA EXISTE EL PROVEEDOR
		id = sa.altaProveedor(tNacional);
		assertEquals("El id debería ser -1 porque ya existe el proveedor", -1, id);
		
		id2 = sa.altaProveedor(tInternacional);
		assertEquals("El id debería ser -1 porque ya existe el proveedor", -1, id2);
		
		//REACTIVAR NACIONAL
		tNacional.setNombre("EspanyaReactivado");
		tNacional.setCodigoPostal("12345");
		tNacional.setActivo(false);
		sa.altaProveedor(tNacional);
		tNacional.setNombre("EspanyaReactivado");
		tNacional.setCodigoPostal("54321");
		tNacional.setActivo(true);
		id = sa.altaProveedor(tNacional);
		assertEquals("El id debería ser 3", 3, id);
		
		//REACTIVAR INTERNACIONAL
		tInternacional.setNombre("RusiaReactivado");
		tInternacional.setPais("Yugoslavia");
		tInternacional.setActivo(false);
		sa.altaProveedor(tInternacional);
		tInternacional.setNombre("RusiaReactivado");
		tInternacional.setPais("Colombia");
		tInternacional.setImpuesto(3.4);
		tInternacional.setActivo(true);
		id = sa.altaProveedor(tInternacional);
		assertEquals("El id debería ser 4", 4, id);
		
		
	}
	
	@Test
	public void consultarProveedorPorIdTest(){
		SAProveedor sa = FactoriaNegocioMall.getInstance().crearSAProveedor();
		//EXITO NACIONAL
		TNacional tNacional = new TNacional(-1, "Consulta", true, "28008");
		sa.altaProveedor(tNacional);
		tNacional = (TNacional) sa.consultarProveedorPorId(1);
		assertEquals("El nombre debería ser Consulta", "Consulta", tNacional.getNombre());
		
		//EXITO INTERNACIONAL
		TInternacional tInternacional = new TInternacional(-1, "Consultaa", true, "Chile", 2.0);
		sa.altaProveedor(tInternacional);
		tInternacional = (TInternacional) sa.consultarProveedorPorId(2);
		assertEquals("El nombre debería ser Consultaa", "Consultaa", tInternacional.getNombre());
		
		//NO EXISTE
		tNacional = (TNacional) sa.consultarProveedorPorId(10);
		assertNull("debería ser null", tNacional);
	}
	
	@Test
	public void consultarProveedoresTest(){
		SAProveedor sa = FactoriaNegocioMall.getInstance().crearSAProveedor();
		
		TProveedor tProveedor = new TNacional(-1, "ConsultaTodos", true, "00000");
		sa.altaProveedor(tProveedor);
		tProveedor = new TInternacional(-1, "ConsultaTodoss", true, "Francia", 1.2);
		sa.altaProveedor(tProveedor);
		tProveedor = new TNacional(-1, "ConsultaTodosss", true, "11111");
		sa.altaProveedor(tProveedor);
		
		List<TProveedor> lista = sa.consultarProveedores();
		assertEquals("debería haber 3 proveedores", 3, lista.size());
	}
	
	@Test
	public void modificarProveedorTest(){
		SAProveedor sa = FactoriaNegocioMall.getInstance().crearSAProveedor();
		
		TNacional tProveedor = new TNacional(-1, "ModificarN", true, "34567");
		sa.altaProveedor(tProveedor);
		
		// exito
		tProveedor.setId(1);
		tProveedor.setNombre("ModificadoN");
		tProveedor.setCodigoPostal("11111");
		boolean exito = sa.modificarProveedor(tProveedor);
		assertTrue("se debería modificar el proveedor", exito);
		
		
		TInternacional tProveedor2 = new TInternacional(-1, "ModificarI", true, "Venezuela", 0.0);
		sa.altaProveedor(tProveedor2);
		
		// exito
		tProveedor2.setId(2);
		tProveedor2.setNombre("ModificadoI");
		tProveedor2.setPais("Estonia");
		exito = sa.modificarProveedor(tProveedor2);
		assertTrue("se debería modificar el proveedor", exito);
		
		// exito pero no modifico el nombre
		tProveedor.setCodigoPostal("22222");
		exito = sa.modificarProveedor(tProveedor);
		assertTrue("se debería modificar el proveedor", exito);
		
		// fallo no existe el proveedor
		tProveedor.setId(10);
		exito = sa.modificarProveedor(tProveedor);
		assertFalse("no se debería modificar el proveedor", exito);
		
		// fallo proveedor inactiva
		tProveedor = new TNacional(-1, "Desactivar", false, "06120");
		sa.altaProveedor(tProveedor);
		tProveedor.setId(3);
		tProveedor.setCodigoPostal("28010");
		exito = sa.modificarProveedor(tProveedor);
		assertFalse("no se debería modificar el proveedor", exito);
		
		//fallo nombre repetido
		tProveedor = new TNacional(-1, "ModificadoI", true, "06120");
		tProveedor.setId(1);
		exito = sa.modificarProveedor(tProveedor);
		assertFalse("no se debería modificar el proveedor", exito);
	}
	
	@Test
	public void bajaProveedorTest(){
		SAProveedor sa = FactoriaNegocioMall.getInstance().crearSAProveedor();
		
		TNacional tNacional = new TNacional(-1, "bajaNacional", true, "11111");
		sa.altaProveedor(tNacional);
		
		TInternacional tInternacional = new TInternacional(-1, "ConsultaTodoss", true, "Francia", 1.2);
		sa.altaProveedor(tInternacional);
		
		// exito nacional
		boolean exito = sa.bajaProveedor(1);
		assertTrue("se debería dar de baja el proveedor", exito);
		
		//exito internacional
		exito = sa.bajaProveedor(2);
		assertTrue("se debería dar de baja el proveedor", exito);
		
		// fallo proveedor inexistente
		exito = sa.bajaProveedor(10);
		assertFalse("no existe el proveedor", exito);
		
		// fallo proveedor inactivo
		tNacional = new TNacional(-1, "nacionalInactivo", false, "23455");
		sa.altaProveedor(tNacional);
		exito = sa.bajaProveedor(3);
		assertFalse("el proveedor esta inactivo", exito);
		
		// fallo tiene productos vinculados
		tNacional = new TNacional(-1, "ProdsVinculados", true, "28008");
		sa.altaProveedor(tNacional);
		
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		Producto p = new Producto(new TProducto(1, "cocacola", 5, 2.20, 82872, 1, true));
		em.persist(p);
		em.getTransaction().commit();
		sa.vincularProveedorProducto(1, 4);
		
		exito = sa.bajaProveedor(4);
		assertFalse("el proveedor tiene productos activos", exito);
		em.close();
	}
	
	@Test
	public void consultarProveedoresPorProductoTest() 
	{
		SAProveedor sa = FactoriaNegocioMall.getInstance().crearSAProveedor();
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		Producto p = new Producto(new TProducto(1, "cocacola", 5, 2.20, 82872, 1, true));
		Producto p2 = new Producto(new TProducto(2, "nestea", 5, 2.20, 82873, 2, true));
		em.persist(p);
		em.persist(p2);
		em.getTransaction().commit();
		
		TNacional tProveedor1 = new TNacional(-1, "consultarPorProdUno", true, "11111");
		sa.altaProveedor(tProveedor1);
		sa.vincularProveedorProducto(1, 1);
		
		TInternacional tProveedor2 = new TInternacional(-1, "consultarPorProdDos", true, "Francia", 1.2);
		sa.altaProveedor(tProveedor2);
		sa.vincularProveedorProducto(1, 2);
		
		TInternacional tProveedor3 = new TInternacional(-1, "consultarPorProdTres", true, "Argentina", 0.0);
		sa.altaProveedor(tProveedor3);
		sa.vincularProveedorProducto(2, 3);
		
		List<TProveedor> lista = sa.consultarProveedoresPorProducto(1);
		assertEquals("debería haber 3 proveedores", 2, lista.size());
		
		lista = sa.consultarProveedoresPorProducto(2);
		assertEquals("debería haber 0 proveedores", 1, lista.size());
		
		lista = sa.consultarProveedoresPorProducto(3);
		assertEquals("debería haber 0 proveedores", 0, lista.size());
		em.close();
	}
	
	
	@Test
	public void vincularProveedorProductoTest() 
	{
		SAProveedor sa = FactoriaNegocioMall.getInstance().crearSAProveedor();
		TNacional tNacional = new TNacional(-1, "vincular", true, "11111");
		sa.altaProveedor(tNacional);
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		Producto p = new Producto(new TProducto(1, "cocacola", 5, 2.20, 82872, 1, true));
		em.persist(p);
		em.getTransaction().commit();
		
		//exito nacional
		boolean exito = sa.vincularProveedorProducto(1, 1);
		assertTrue("se deberia vincular el proveedor y el producto", exito);
		
		//fallo ya ha sido vinculado antes
		exito = sa.vincularProveedorProducto(1, 1);
		assertFalse("no se deberia vincular el proveedor y el producto", exito);
		
		//fallo el producto no existe
		exito = sa.vincularProveedorProducto(4, 1);
		assertFalse("no se deberia vincular el proveedor y el producto", exito);
		
		//fallo el proveedor no existe
		exito = sa.vincularProveedorProducto(1, 4);
		assertFalse("no se deberia vincular el proveedor y el producto", exito);
		
		//exito internacional
		TInternacional tInternacional = new TInternacional(-1, "vincular2", true, "Francia", 1.2);
		sa.altaProveedor(tInternacional);
		
		exito = sa.vincularProveedorProducto(1, 2);
		assertTrue("se deberia vincular el proveedor y el producto", exito);
		
		//fallo el proveedor está inactivo asi que no se puede vincular
		tNacional = new TNacional(-1, "vincularFallo", false, "28008");
		sa.altaProveedor(tNacional);
		exito = sa.vincularProveedorProducto(1, 3);
		assertFalse("no se deberia vincular el proveedor y el producto", exito);
		em.close();

	}
	
	@Test
	public void desvincularProveedorProductoTest() 
	{
		SAProveedor sa = FactoriaNegocioMall.getInstance().crearSAProveedor();
		TNacional tNacional = new TNacional(-1, "desvincular", true, "11111");
		sa.altaProveedor(tNacional);
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		Producto p = new Producto(new TProducto(1, "cocacola", 5, 2.20, 82872, 1, true));
		em.persist(p);
		em.getTransaction().commit();
		sa.vincularProveedorProducto(1, 1);
		//exito nacional
		boolean exito = sa.desvincularProveedorProducto(1, 1);
		assertTrue("se deberia desvincular el proveedor y el producto", exito);
		
		//fallo ya ha sido desvinculado antes
		exito = sa.desvincularProveedorProducto(1, 1);
		assertFalse("no se deberia vincular el proveedor y el producto", exito);
		
		//fallo el producto no existe
		exito = sa.desvincularProveedorProducto(4, 1);
		assertFalse("no se deberia vincular el proveedor y el producto", exito);
				
		//fallo el proveedor no existe
		exito = sa.desvincularProveedorProducto(1, 4);
		assertFalse("no se deberia vincular el proveedor y el producto", exito);
		
		//exito internacional
		TInternacional tInternacional = new TInternacional(-1, "desvincular2", true, "Francia", 1.2);
		sa.altaProveedor(tInternacional);
		exito = sa.vincularProveedorProducto(1, 2);
		
		exito = sa.desvincularProveedorProducto(1, 2);
		assertTrue("se deberia desvincular el proveedor y el producto", exito);
		em.close();
		
	}
	

}
