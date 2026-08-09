package negocio.producto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import integracion.factoria.EMFSingleton;
import negocio.UtilidadesN;
import negocio.marca.Marca;
import negocio.proveedor.Proveedor;
import negocio.venta.LineaVenta;

public class SAProductoImp implements SAProducto {

	public synchronized int altaProducto(TProducto tProducto) {
		EntityManager em = null;
		int id = -1;

		if (ValidadorProducto.comprobarDatos(tProducto)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				List<Producto> productos = em.createNamedQuery("negocio.producto.Producto.findByref", Producto.class)
						.setParameter("ref", tProducto.getRef()).getResultList();

				Marca marca = em.find(Marca.class, tProducto.getIdMarca(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);

				if (productos.isEmpty()) {
					if (marca == null || !marca.getActivo())
						em.getTransaction().rollback();
					else {
						Producto producto = new Producto(tProducto);
						producto.setMarca(marca);
						em.persist(producto);
						marca.getProductos().add(producto);

						em.getTransaction().commit();

						id = producto.getId();
					}
				} else {
					Producto producto = productos.get(0);

					if (producto.getActivo())
						em.getTransaction().rollback();
					else {
						if (marca == null || !marca.getActivo())
							em.getTransaction().rollback();
						else {
							producto.getMarca().getProductos().remove(producto);
							producto.setActivo(true);
							producto.setMarca(marca);
							marca.getProductos().add(producto);
							producto.setNombre(tProducto.getNombre());
							producto.setPrecio(tProducto.getPrecio());
							producto.setRef(tProducto.getRef());
							producto.setStock(tProducto.getStock());

							em.getTransaction().commit();

							id = producto.getId();
						}
					}
				}
			} catch (Exception e) {
				if (em != null && em.getTransaction().isActive())
					em.getTransaction().rollback();
			} finally {
				if (em != null)
					em.close();
			}
		}

		return id;
	}

	public boolean bajaProducto(int id) {
		EntityManager em = null;
		boolean exito = false;

		if (UtilidadesN.comprobarId(id)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Producto producto = em.find(Producto.class, id);

				List<LineaVenta> lineasVenta = em
						.createNamedQuery("negocio.venta.LineaVenta.findByproducto", LineaVenta.class)
						.setParameter("producto", producto).getResultList();

				if (producto != null && producto.getActivo() && producto.getProveedores().isEmpty()
						&& lineasVenta.isEmpty()) {
					producto.setActivo(false);
					exito = true;

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
		}

		return exito;
	}

	public TProducto consultarProductoPorId(int id) {
		EntityManager em = null;
		TProducto tProducto = null;

		if (UtilidadesN.comprobarId(id)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				tProducto = em.find(Producto.class, id).toTransfer();

				em.getTransaction().commit();
			} catch (Exception e) {
				if (em != null && em.getTransaction().isActive())
					em.getTransaction().rollback();
			} finally {
				if (em != null)
					em.close();
			}
		}

		return tProducto;
	}

	public List<TProducto> consultarProductos() {
		EntityManager em = null;
		List<TProducto> tProductos = new ArrayList<>();
		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();

			em.getTransaction().begin();

			List<Producto> productos = em.createNamedQuery("negocio.producto.Producto.findAll", Producto.class)
					.getResultList();

			for (Producto producto : productos)
				tProductos.add(producto.toTransfer());

			em.getTransaction().commit();
		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			if (em != null)
				em.close();
		}

		return tProductos;
	}

	public boolean modificarProducto(TProducto tProducto) {
		EntityManager em = null;
		boolean exito = false;

		if (ValidadorProducto.comprobarDatos(tProducto)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Producto producto = em.find(Producto.class, tProducto.getId());

				if (producto != null && producto.getActivo()) {

					List<Producto> productos = em
							.createNamedQuery("negocio.producto.Producto.findByref", Producto.class)
							.setParameter("ref", tProducto.getRef()).setLockMode(LockModeType.OPTIMISTIC)
							.getResultList();

					if (producto.getRef() == tProducto.getRef() || productos.isEmpty()) {

						Marca marca = em.find(Marca.class, tProducto.getIdMarca(),
								LockModeType.OPTIMISTIC_FORCE_INCREMENT);

						if (marca == null || !marca.getActivo())
							em.getTransaction().rollback();
						else {
							producto.getMarca().getProductos().remove(producto);
							producto.setMarca(marca);
							marca.getProductos().add(producto);
							producto.setNombre(tProducto.getNombre());
							producto.setPrecio(tProducto.getPrecio());
							producto.setRef(tProducto.getRef());
							producto.setStock(tProducto.getStock());
							em.getTransaction().commit();
							exito = true;
						}
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

	public List<TProducto> consultarProductosPorMarca(int idMarca) {
		EntityManager em = null;
		List<TProducto> tProductos = new ArrayList<>();

		if (UtilidadesN.comprobarId(idMarca)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Marca marca = em.find(Marca.class, idMarca);

				if (marca != null && marca.getActivo()) {
					for (Producto producto : marca.getProductos())
						tProductos.add(producto.toTransfer());

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
		}

		return tProductos;
	}

	public List<TProducto> consultarProductosPorProveedor(int idProveedor) {
		EntityManager em = null;
		List<TProducto> tProductos = new ArrayList<>();

		if (UtilidadesN.comprobarId(idProveedor)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Proveedor proveedor = em.find(Proveedor.class, idProveedor);

				if (proveedor != null && proveedor.getActivo()) {
					for (Producto p : proveedor.getProductos())
						tProductos.add(p.toTransfer());

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
		}

		return tProductos;
	}
}