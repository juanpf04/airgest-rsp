package negocio.marca;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import integracion.factoria.EMFSingleton;
import negocio.UtilidadesN;
import negocio.producto.Producto;

public class SAMarcaImp implements SAMarca {

	public synchronized int altaMarca(TMarca tMarca) {
		EntityManager em = null;
		int id = -1;

		if (ValidadorMarca.comprobarDatos(tMarca)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				// Utilizamos .getResultList() para evitarnos excepcion en caso
				// de no encontrar niguna marca con el nombre
				List<Marca> marcas = em.createNamedQuery("negocio.marca.Marca.findBynombre", Marca.class)
						.setParameter("nombre", tMarca.getNombre()).getResultList();

				if (marcas.isEmpty()) { // si esta vacia, no hay marca con
										// ese nombre
					Marca marca = new Marca(tMarca);

					em.persist(marca);

					em.getTransaction().commit();

					id = marca.getId(); // El id despues del commit para que se
										// genere

				} else { // si hay marca con ese nombre comprobamos su estado en
							// la base de datos
					Marca marca = marcas.get(0);

					if (!marca.getActivo()) {

						marca.setActivo(true);
						marca.setOrigen(tMarca.getOrigen());

						em.getTransaction().commit();

						id = marca.getId();
					} else
						em.getTransaction().rollback();
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

	public boolean bajaMarca(int id) {
		EntityManager em = null;
		boolean exito = false;

		if (UtilidadesN.comprobarId(id)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Marca marca = em.find(Marca.class, id);

				if (marca != null && marca.getActivo()) {
					for (Producto producto : marca.getProductos()) {
						em.lock(producto, LockModeType.OPTIMISTIC);
						if (producto.getActivo()) {
							em.getTransaction().rollback();
							return exito;
						}
					}
					// si no hay ningun producto activo con la marca
					marca.setActivo(false);
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

	public TMarca consultarMarcaPorId(int id) {
		EntityManager em = null;
		TMarca tMarca = null;

		if (UtilidadesN.comprobarId(id)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Marca marca = em.find(Marca.class, id);

				if (marca != null)
					tMarca = marca.toTransfer();

				em.getTransaction().commit();

			} catch (Exception e) {
				if (em != null && em.getTransaction().isActive())
					em.getTransaction().rollback();
			} finally {
				if (em != null)
					em.close();
			}
		}

		return tMarca;
	}

	public List<TMarca> consultarMarcas() {
		EntityManager em = null;
		List<TMarca> tMarcas = new ArrayList<>();

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();

			em.getTransaction().begin();

			List<Marca> marcas = em.createNamedQuery("negocio.marca.Marca.findAll", Marca.class).getResultList();

			for (Marca marca : marcas)
				tMarcas.add(marca.toTransfer());

			em.getTransaction().commit();

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			if (em != null)
				em.close();
		}

		return tMarcas;
	}

	public boolean modificarMarca(TMarca tMarca) {
		EntityManager em = null;
		boolean exito = false;

		if (ValidadorMarca.comprobarDatos(tMarca)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Marca marca = em.find(Marca.class, tMarca.getId());

				if (marca != null && marca.getActivo()) {
					List<Marca> marcas = em.createNamedQuery("negocio.marca.Marca.findBynombre", Marca.class)
							.setParameter("nombre", tMarca.getNombre()).setLockMode(LockModeType.OPTIMISTIC)
							.getResultList();

					if (marca.getNombre().equals(tMarca.getNombre()) || marcas.isEmpty()) {
						exito = true;
						marca.setNombre(tMarca.getNombre());
						marca.setOrigen(tMarca.getOrigen());

						em.getTransaction().commit();
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