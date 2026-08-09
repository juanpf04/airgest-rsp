package negocio.venta;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import integracion.factoria.EMFSingleton;
import negocio.departamento.Departamento;
import negocio.departamento.TDepartamento;
import negocio.empleado.Gerente;
import negocio.empleado.TGerente;
import negocio.factoria.FactoriaNegocioMall;
import negocio.marca.Marca;
import negocio.marca.TMarca;
import negocio.producto.Producto;
import negocio.producto.TProducto;

public class SAVentaImpTest {

	@Test
	public void consultarVentasTest(){
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		Gerente g = new Gerente(new TGerente(0, 1, 160, 1, true, 1, 10));
		g.setDepartamento(d);
		em.persist(d);
		em.persist(g);
		
		Venta v1 = new Venta(new TVenta(-1, 1000, "12/04/2002", 1));
		v1.setEmpleado(g);
		Venta v2 = new Venta(new TVenta(-1, 1000, "12/04/2002", 1));
		v2.setEmpleado(g);
		em.persist(v1);
		em.persist(v2);
		em.getTransaction().commit();
		
		SAVenta sv = FactoriaNegocioMall.getInstance().crearSAVenta();
		
		List<TVenta> lista = sv.consultarVentas();
		assertEquals("Deberían existir dos ventas", 2, lista.size());
	}
	
	@Test
	public void consultarVentasPorEmpleadoTest(){
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		Gerente g = new Gerente(new TGerente(0, 1, 160, 1, true, 1, 10));
		g.setDepartamento(d);
		em.persist(d);
		em.persist(g);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		Venta v1 = new Venta(new TVenta(-1, 1000, "12/04/2002", 1));
		v1.setEmpleado(g);
		Venta v2 = new Venta(new TVenta(-1, 1000, "12/04/2002", 1));
		v2.setEmpleado(g);
		g.getVentas().add(v1);
		g.getVentas().add(v2);
		em.persist(v1);
		em.persist(v2);
		em.getTransaction().commit();
		
		SAVenta sv = FactoriaNegocioMall.getInstance().crearSAVenta();
		
		//Exito
		List<TVenta> lista = sv.consultarVentasPorEmpleado(1);
		assertEquals("Deberían existir dos ventas", 2, lista.size());
		
		//Fallo por empleado no existente
		lista = sv.consultarVentasPorEmpleado(10);
		assertEquals("Deberían existir 0 ventas", 0, lista.size());
	}
	
	@Test 
	public void consultarVentasPorIdTest(){
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		Gerente g = new Gerente(new TGerente(0, 1, 160, 1, true, 1, 10));
		g.setDepartamento(d);
		em.persist(d);
		em.persist(g);
		
		Marca m = new Marca(new TMarca(-1, "adidas", "EEUU", true));
		em.persist(m);
		Producto p = new Producto(new TProducto(1, "cocacola", 5, 2.20, 82872, 1, true));
		p.setMarca(m);
		em.persist(p);
		
		Venta v = new Venta(new TVenta(-1, 1000, "12/04/2024", 1));
		v.setEmpleado(g);
		em.persist(v);
		
		LineaVenta lv = new LineaVenta(new TLineaVenta(1, 1, 100, 1000));
		lv.setProducto(p);
		lv.setVenta(v);
		em.persist(lv);
		
		em.getTransaction().commit();
		
		SAVenta sv = FactoriaNegocioMall.getInstance().crearSAVenta();
		TInfoVenta info = sv.consultarVentaPorId(1);
		System.out.println(info);
		assertEquals("", 1, info.getVenta().getId());
	}
	
	@Test
	public void modificarVentaTest(){
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		Gerente g = new Gerente(new TGerente(0, 1, 160, 1, true, 1, 10));
		g.setDepartamento(d);
		em.persist(d);
		em.persist(g);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		Gerente g2 = new Gerente(new TGerente(0, 2, 1234, 1, true, 1, 10));
		g2.setDepartamento(d);
		em.persist(g2);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		Gerente g3 = new Gerente(new TGerente(0, 3, 1234, 1, false, 1, 10));
		g3.setDepartamento(d);
		em.persist(g3);
		
		Venta v1 = new Venta(new TVenta(-1, 1000, "12/04/2002", 1));
		v1.setEmpleado(g);
		em.persist(v1);
		
		em.getTransaction().commit();
		
		SAVenta sv = FactoriaNegocioMall.getInstance().crearSAVenta();
		
		//exito
		assertTrue("Se debería modificar venta", sv.modificarVenta(new TVenta(1, 1234, "16/01/2001", 1)));
		
		//exito cambiando empleado
		assertTrue("Se debería modificar venta", sv.modificarVenta(new TVenta(1, 123456, "16/01/2001", 2)));
		
		//fallo empleado inexistente
		assertFalse("No se debería modificar venta", sv.modificarVenta(new TVenta(1, 123456, "16/01/2001", 10)));
		
		//fallo empleado inactivo
		assertFalse("No se debería modificar venta", sv.modificarVenta(new TVenta(1, 123456, "16/01/2001", 3)));
	}
	
	@Test
	public void cerrarVentaTest(){
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		Gerente g = new Gerente(new TGerente(0, 1, 160, 1, true, 1, 10));
		g.setDepartamento(d);
		em.persist(d);
		em.persist(g);
		
		Marca m = new Marca(new TMarca(-1, "adidas", "EEUU", true));
		em.persist(m);
		Producto p = new Producto(new TProducto(1, "cocacola", 5, 2.20, 82872, 1, true));
		p.setMarca(m);
		em.persist(p);
		Producto p1 = new Producto(new TProducto(1, "pepsi", 5, 2.20, 82873, 1, true));
		p1.setMarca(m);
		em.persist(p1);
		
		TCarritoVenta carrito = new TCarritoVenta(1);
		carrito.setVenta(new TVenta(-1, 0, "03/12/2024", 1));
		TLineaVenta tl1 = new TLineaVenta();
		tl1.setIdProducto(1);
		tl1.setCantidad(4);
		carrito.anyadirLinea(tl1);
		
		TLineaVenta tl2 = new TLineaVenta();
		tl2.setIdProducto(2);
		tl2.setCantidad(4);
		carrito.anyadirLinea(tl2);
		
		em.getTransaction().commit();
		
		SAVenta sv = FactoriaNegocioMall.getInstance().crearSAVenta();
		int id = sv.cerrarVenta(carrito);
		
		// exito
		assertEquals("El id debería ser 1", 1, id);
		
		//fallo empleado no existente
		carrito = new TCarritoVenta(10);
		carrito.anyadirLinea(tl1);
		id = sv.cerrarVenta(carrito);
		assertEquals("El id debería ser -1", -1, id);
		
		//fallo empleado inactivo
		em.getTransaction().begin();
		g.setActivo(false);
		em.getTransaction().commit();
		
		carrito = new TCarritoVenta(1);
		carrito.anyadirLinea(tl1);
		id = sv.cerrarVenta(carrito);
		assertEquals("El id debería ser -1", -1, id);
		
		//fallo producto repetido
		em.getTransaction().begin();
		g.setActivo(true);
		em.getTransaction().commit();
		carrito = new TCarritoVenta(1);
		carrito.anyadirLinea(tl1);
		tl2.setIdProducto(1);
		carrito.anyadirLinea(tl2);
		id = sv.cerrarVenta(carrito);
		assertEquals("El id debería ser -1", -1, id);
		
		//fallo producto inexistente
		carrito = new TCarritoVenta(1);
		carrito.setVenta(new TVenta(-1, 0, "03/12/2024", 1));
		tl1 = new TLineaVenta();
		tl1.setIdProducto(10);
		tl1.setCantidad(4);
		carrito.anyadirLinea(tl1);
		id = sv.cerrarVenta(carrito);
		assertEquals("El id debería ser -1", -1, id);
		
		//fallo producto inactivo
		em.getTransaction().begin();
		Producto p2 = new Producto(new TProducto(1, "fanta", 5, 2.20, 82877, 1, false));
		p.setMarca(m);
		em.persist(p2);
		em.getTransaction().commit();
		
		carrito = new TCarritoVenta(1);
		carrito.setVenta(new TVenta(-1, 0, "03/12/2024", 1));
		tl1 = new TLineaVenta();
		tl1.setIdProducto(3);
		tl1.setCantidad(4);
		tl2.setCantidad(1);
		carrito.anyadirLinea(tl2);
		carrito.anyadirLinea(tl1);
		id = sv.cerrarVenta(carrito);
		assertEquals("El id debería ser -1", -1, id);
		
		//fallo no hay cantidad suficiente
		carrito = new TCarritoVenta(1);
		carrito.setVenta(new TVenta(-1, 0, "03/12/2024", 1));
		tl1 = new TLineaVenta();
		tl1.setIdProducto(1);
		tl1.setCantidad(4);
		carrito.anyadirLinea(tl1);
		id = sv.cerrarVenta(carrito);
		assertEquals("El id debería ser -1", -1, id);
	}
	
	@Test
	public void devolucionTest(){
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		Gerente g = new Gerente(new TGerente(0, 1, 160, 1, true, 1, 10));
		g.setDepartamento(d);
		em.persist(d);
		em.persist(g);
		
		Marca m = new Marca(new TMarca(-1, "adidas", "EEUU", true));
		em.persist(m);
		Producto p = new Producto(new TProducto(1, "cocacola", 5, 2.20, 82872, 1, true));
		p.setMarca(m);
		em.persist(p);
		Producto p1 = new Producto(new TProducto(1, "pepsi", 5, 2.20, 82873, 1, true));
		p1.setMarca(m);
		em.persist(p1);
		
		TCarritoVenta carrito = new TCarritoVenta(1);
		carrito.setVenta(new TVenta(-1, 0, "03/12/2024", 1));
		TLineaVenta tl1 = new TLineaVenta();
		tl1.setIdProducto(1);
		tl1.setCantidad(4);
		carrito.anyadirLinea(tl1);
		
		TLineaVenta tl2 = new TLineaVenta();
		tl2.setIdProducto(2);
		tl2.setCantidad(4);
		carrito.anyadirLinea(tl2);
		
		em.getTransaction().commit();
		
		SAVenta sv = FactoriaNegocioMall.getInstance().crearSAVenta();
		sv.cerrarVenta(carrito);
		
		// exito
		TLineaVenta devolucion = new TLineaVenta(1, 1, 3, 0);
		boolean exito = sv.devolucion(devolucion);
		assertTrue("Se debería realizar la devolucion", exito);
		
		// exito pero devolviendo todo
		devolucion = new TLineaVenta(1, 2, 4, 0);
		exito = sv.devolucion(devolucion);
		assertTrue("Se debería realizar la devolucion", exito);
		
		// fallo la cantidad a devolver es mayor que la comprada
		devolucion = new TLineaVenta(1, 1, 10, 0);
		exito = sv.devolucion(devolucion);
		assertFalse("No se debería realizar la devolucion", exito);
	}
}
