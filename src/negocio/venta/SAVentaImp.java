
package negocio.venta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import integracion.factoria.EMFSingleton;
import negocio.empleado.Empleado;
import negocio.producto.Producto;
import negocio.producto.TProducto;


public class SAVentaImp implements SAVenta {
	
	public TCarritoVenta abrirCarrito(int idEmpleado) {
		return new TCarritoVenta(idEmpleado);
	}

	public int cerrarVenta(TCarritoVenta carrito) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	public TInfoVenta consultarVentaPorId(int id) {
		EntityManager em = null;
		TInfoVenta tInfo = null;

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();
			em.getTransaction().begin();
			
			Venta venta = em.find(Venta.class, id);
			
			if (venta != null){
				tInfo = new TInfoVenta();
				
				// Asigno a la venta
				tInfo.setVenta(venta.toTransfer());
				
				// Asigno el empleado
				tInfo.setEmpleado(venta.getEmpleado().toTransfer());
				
				// Asigno las lineas de venta y productos
				List<LineaVenta> lineasVenta = em.createNamedQuery("negocio.venta.LineaVenta.findByventa", LineaVenta.class)
						.setParameter("venta", venta)
						.getResultList();
				
				List<TLineaVenta> listaTransfersLV = new ArrayList<>();
				HashMap<Integer, TProducto> productos = new HashMap<>();
				
				for (LineaVenta linea : lineasVenta){
					listaTransfersLV.add(linea.toTransfer());
					
					Producto prod = linea.getProducto();
					productos.put(prod.getId(), prod.toTransfer());
				}
				
				tInfo.setLineasVenta(listaTransfersLV);
				tInfo.setProductos(productos);
			}
			
			
			em.getTransaction().commit();	

		} catch (Exception e) {
								
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return tInfo;
	}

	public List<TVenta> consultarVentas() {
		
		EntityManager em = null;
		List<TVenta> listaVentas = new ArrayList<TVenta>();

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();
			em.getTransaction().begin();
			
			List<Venta> ventas = em.createNamedQuery("negocio.venta.Venta.findAll", Venta.class).getResultList();
			
			for (Venta venta : ventas){
				listaVentas.add(venta.toTransfer());
			}
			
			em.getTransaction().commit();	

		} catch (Exception e) {
								
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return listaVentas;
	}

	public boolean devolucion(TLineaVenta tLineaVenta) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}

	public List<TVenta> consultarVentasPorEmpleado(int id) {
		EntityManager em = null;
		List<TVenta> listaVentas = new ArrayList<TVenta>();

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();
			em.getTransaction().begin();
			
			Empleado emp = em.find(Empleado.class, id);
			
			if (emp != null){
				for (Venta venta : emp.getVentas()){
					listaVentas.add(venta.toTransfer());
				}
				
				em.getTransaction().commit();
			} else {
				em.getTransaction().rollback();
			}

		} catch (Exception e) {
								
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return listaVentas;
	}

	public boolean modificarVenta(TVenta tVenta) {
		EntityManager em = null;
		boolean exito = false;

		if (ValidadorVenta.comprobarDatos(tVenta)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();
				
				Venta venta = em.find(Venta.class, tVenta.getId());
				
				if (venta != null){
					Empleado emp = em.find(Empleado.class, tVenta.getIdEmpleado(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
					
					if (emp != null && emp.getActivo()){
						venta.setEmpleado(emp);
						venta.setFecha(tVenta.getFecha());
						venta.setPrecio(tVenta.getPrecio());
						em.getTransaction().commit();
						exito = true;
					} else{
						em.getTransaction().rollback();
					}
				} else {
					em.getTransaction().rollback();
				}
				
			} catch (Exception e) { 
									
				if (em != null && em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}

			} finally {
				if (em != null) {
					em.close();
				}

			}
		}

		return exito;
	}
}