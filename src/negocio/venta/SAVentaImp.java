
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
		EntityManager em = null;
		int id = -1;
		
		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();
			em.getTransaction().begin();
			
			//Comprobamos que el empleado existe, está activo y que hay productos en el carrito
			Empleado empleado = em.find(Empleado.class, carrito.getIdEmpleado(), LockModeType.OPTIMISTIC);
			
			if (empleado != null && empleado.getActivo() && !carrito.getLineasVenta().isEmpty()){
				
				//Comprobamos que no hay productos repetidos
				for (TLineaVenta linea : carrito.getLineasVenta()){
					for (TLineaVenta l : carrito.getLineasVenta()){
						if (l != linea && l.getIdProducto() == linea.getIdProducto()){
							em.getTransaction().rollback();
							return id;
						}
					}
				}
				
				//Doy de alta la venta con precio 0
				Venta venta = new Venta(carrito.getVenta());
				// TODO igual no hace falta, depende de las vistas
				venta.setPrecio(0);
				venta.setEmpleado(empleado);
				
				double precioTotal = 0;
				//Recorro las lineas de venta y compruebo que los productos existan, estén activos y disponibles
				for (TLineaVenta linea : carrito.getLineasVenta()){
					Producto prod = em.find(Producto.class, linea.getIdProducto(), LockModeType.OPTIMISTIC);
					
					if (prod != null && prod.getActivo() && prod.getStock() >= linea.getCantidad() && linea.getCantidad() > 0){
						int nuevoStock = prod.getStock() - linea.getCantidad();
						prod.setStock(nuevoStock);
						
						LineaVenta lineaVenta = new LineaVenta(linea);
						lineaVenta.setVenta(venta);
						lineaVenta.setProducto(prod);
						double precioLinea = linea.getCantidad() * prod.getPrecio();
						lineaVenta.setPrecio(precioLinea);
						em.persist(lineaVenta);
						precioTotal += precioLinea;
					} else{
						em.getTransaction().rollback();
						return id;
					}
				}
				
				venta.setPrecio(precioTotal);
				em.persist(venta);
				empleado.getVentas().add(venta);
				
				em.getTransaction().commit();
				id = venta.getId();
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
		
		return id;
		
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
	    EntityManager em = null;
	    boolean exito = false;
	    
	    if (ValidadorLineaVenta.comprobarDatos(tLineaVenta)){
	    	try {
		        em = EMFSingleton.getInstance().getEMF().createEntityManager();
		        em.getTransaction().begin();

		        LineaVenta lineaVenta = em.find(LineaVenta.class, 
		            new Clave(tLineaVenta.getIdVenta(), tLineaVenta.getIdProducto()), 
		            LockModeType.OPTIMISTIC);

		        if (lineaVenta != null) {
		            Producto prod = lineaVenta.getProducto();
		            em.lock(prod, LockModeType.OPTIMISTIC);
		            
		            Venta venta = lineaVenta.getVenta();
		            em.lock(venta, LockModeType.OPTIMISTIC);

		            int cantidadDevolver = tLineaVenta.getCantidad();

		            if (cantidadDevolver <= lineaVenta.getCantidad()) {
		                // Actualizar la cantidad de la línea de venta
		                int nuevaCantidad = lineaVenta.getCantidad() - cantidadDevolver;
		                lineaVenta.setCantidad(nuevaCantidad);

		                // Actualizar el precio total de la línea de venta
		                double nuevoPrecioLinea = nuevaCantidad * prod.getPrecio();
		                double precioAnteriorLinea = lineaVenta.getPrecio();
		                lineaVenta.setPrecio(nuevoPrecioLinea);

		                // Actualizar el precio total de la venta
		                venta.setPrecio(venta.getPrecio() - precioAnteriorLinea + nuevoPrecioLinea);

		                // Si la cantidad es 0, eliminar la línea de venta
		                if (nuevaCantidad == 0) {
		                    em.remove(lineaVenta);
		                }

		                prod.setStock(prod.getStock() + cantidadDevolver);

		                exito = true;
		            }
		        }

		        if (exito){
		        	em.getTransaction().commit();
		        } else{
		        	em.getTransaction().rollback();
		        }
		    } catch (Exception e) {
		        if (em != null && em.getTransaction().isActive()) {
		            em.getTransaction().rollback();
		        }
		        e.printStackTrace();
		    } finally {
		        if (em != null) {
		            em.close();
		        }
		    }
	    }

	    return exito;
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
						venta.getEmpleado().getVentas().remove(venta);
						venta.setEmpleado(emp);
						venta.setFecha(tVenta.getFecha());
						venta.setPrecio(tVenta.getPrecio());
						emp.getVentas().add(venta);
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