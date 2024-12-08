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

	public int cerrarVenta(TCarritoVenta tCarrito) {
		EntityManager em = null;
		int id = -1;

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();

			em.getTransaction().begin();

			// Comprobamos que el empleado existe, está activo y que hay
			// productos en el carrito
			Empleado empleado = em.find(Empleado.class, tCarrito.getIdEmpleado(), LockModeType.OPTIMISTIC);

			if (empleado != null && empleado.getActivo() && !tCarrito.getLineasVenta().isEmpty()) {

				// Comprobamos que no hay productos repetidos
				for (TLineaVenta tLineaVenta : tCarrito.getLineasVenta()) {
					for (TLineaVenta tLineaVenta2 : tCarrito.getLineasVenta()) {
						if (tLineaVenta2 != tLineaVenta
								&& tLineaVenta2.getIdProducto() == tLineaVenta.getIdProducto()) {
							em.getTransaction().rollback();
							return id;
						}
					}
				}

				// Doy de alta la venta con precio 0
				Venta venta = new Venta(tCarrito.getVenta());
				venta.setEmpleado(empleado);

				double precioTotal = 0;
				// Recorro las lineas de venta y compruebo que los productos
				// existan, estén activos y disponibles
				for (TLineaVenta tLineaVenta : tCarrito.getLineasVenta()) {
					Producto producto = em.find(Producto.class, tLineaVenta.getIdProducto(), LockModeType.OPTIMISTIC);

					if (producto != null && producto.getActivo() && producto.getStock() >= tLineaVenta.getCantidad()
							&& tLineaVenta.getCantidad() > 0) {
						int nuevoStock = producto.getStock() - tLineaVenta.getCantidad();
						producto.setStock(nuevoStock);

						LineaVenta lineaVenta = new LineaVenta(tLineaVenta);
						lineaVenta.setVenta(venta);
						lineaVenta.setProducto(producto);
						double precioLinea = tLineaVenta.getCantidad() * producto.getPrecio();
						lineaVenta.setPrecio(precioLinea);
						em.persist(lineaVenta);
						precioTotal += precioLinea;
					} else {
						em.getTransaction().rollback();
						return id;
					}
				}

				venta.setPrecio(precioTotal);
				em.persist(venta);
				empleado.getVentas().add(venta);

				em.getTransaction().commit();

				id = venta.getId();
			} else
				em.getTransaction().rollback();

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			if (em != null)
				em.close();
		}

		return id;

	}

	public TInfoVenta consultarVentaPorId(int id) {
		EntityManager em = null;
		TInfoVenta tInfoVenta = null;

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();
			em.getTransaction().begin();

			Venta venta = em.find(Venta.class, id);

			if (venta != null) {
				tInfoVenta = new TInfoVenta();

				// Asigno a la venta
				tInfoVenta.setVenta(venta.toTransfer());

				// Asigno el empleado
				tInfoVenta.setEmpleado(venta.getEmpleado().toTransfer());

				// Asigno las lineas de venta y productos
				List<LineaVenta> lineasVenta = em
						.createNamedQuery("negocio.venta.LineaVenta.findByventa", LineaVenta.class)
						.setParameter("venta", venta).getResultList();

				List<TLineaVenta> tLineasVenta = new ArrayList<>();
				HashMap<Integer, TProducto> tProductos = new HashMap<>();

				for (LineaVenta linea : lineasVenta) {
					tLineasVenta.add(linea.toTransfer());

					Producto prod = linea.getProducto();
					tProductos.put(prod.getId(), prod.toTransfer());
				}

				tInfoVenta.setLineasVenta(tLineasVenta);
				tInfoVenta.setProductos(tProductos);
			}

			em.getTransaction().commit();

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			if (em != null)
				em.close();
		}

		return tInfoVenta;
	}

	public List<TVenta> consultarVentas() {
		EntityManager em = null;
		List<TVenta> tVentas = new ArrayList<>();

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();

			em.getTransaction().begin();

			List<Venta> ventas = em.createNamedQuery("negocio.venta.Venta.findAll", Venta.class).getResultList();

			for (Venta venta : ventas)
				tVentas.add(venta.toTransfer());

			em.getTransaction().commit();

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			if (em != null)
				em.close();
		}

		return tVentas;
	}

	public boolean devolucion(TLineaVenta tLineaVenta) {
		EntityManager em = null;
		boolean exito = false;

		if (ValidadorLineaVenta.comprobarDatos(tLineaVenta)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				LineaVenta lineaVenta = em.find(LineaVenta.class,
						new Clave(tLineaVenta.getIdVenta(), tLineaVenta.getIdProducto()), LockModeType.OPTIMISTIC);

				if (lineaVenta != null) {
					Producto producto = lineaVenta.getProducto();
					em.lock(producto, LockModeType.OPTIMISTIC);

					Venta venta = lineaVenta.getVenta();
					em.lock(venta, LockModeType.OPTIMISTIC);

					int cantidadDevolver = tLineaVenta.getCantidad();

					if (cantidadDevolver <= lineaVenta.getCantidad()) {
						// Actualizar la cantidad de la línea de venta
						int nuevaCantidad = lineaVenta.getCantidad() - cantidadDevolver;
						lineaVenta.setCantidad(nuevaCantidad);

						// Actualizar el precio total de la línea de venta
						double nuevoPrecioLinea = nuevaCantidad * producto.getPrecio();
						double precioAnteriorLinea = lineaVenta.getPrecio();
						lineaVenta.setPrecio(nuevoPrecioLinea);

						// Actualizar el precio total de la venta
						venta.setPrecio(venta.getPrecio() - precioAnteriorLinea + nuevoPrecioLinea);

						// Si la cantidad es 0, eliminar la línea de venta
						if (nuevaCantidad == 0)
							em.remove(lineaVenta);

						producto.setStock(producto.getStock() + cantidadDevolver);

						exito = true;
					}
				}

				if (exito)
					em.getTransaction().commit();
				else
					em.getTransaction().rollback();

			} catch (Exception e) {
				if (em != null && em.getTransaction().isActive())
					em.getTransaction().rollback();
			} finally {
				if (em != null)
					em.close();
			}
		}

		return exito;
	}

	public List<TVenta> consultarVentasPorEmpleado(int id) {
		EntityManager em = null;
		List<TVenta> tVentas = new ArrayList<>();

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();

			em.getTransaction().begin();

			Empleado empleado = em.find(Empleado.class, id);

			if (empleado != null) {
				for (Venta venta : empleado.getVentas())
					tVentas.add(venta.toTransfer());

				em.getTransaction().commit();
			} else
				em.getTransaction().rollback();

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			if (em != null)
				em.close();
		}

		return tVentas;
	}

	public boolean modificarVenta(TVenta tVenta) {
		EntityManager em = null;
		boolean exito = false;

		if (ValidadorVenta.comprobarDatos(tVenta)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Venta venta = em.find(Venta.class, tVenta.getId());

				if (venta != null) {
					Empleado empleado = em.find(Empleado.class, tVenta.getIdEmpleado(),
							LockModeType.OPTIMISTIC_FORCE_INCREMENT);

					if (empleado != null && empleado.getActivo()) {
						venta.getEmpleado().getVentas().remove(venta);
						venta.setEmpleado(empleado);
						venta.setFecha(tVenta.getFecha());
						venta.setPrecio(tVenta.getPrecio());
						empleado.getVentas().add(venta);
						em.getTransaction().commit();
						exito = true;
					} else
						em.getTransaction().rollback();
				} else
					em.getTransaction().rollback();

			} catch (Exception e) {
				if (em != null && em.getTransaction().isActive())
					em.getTransaction().rollback();
			} finally {
				if (em != null)
					em.close();
			}
		}

		return exito;
	}
}