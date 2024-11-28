package negocio.marca;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import integracion.factoria.EMFSingleton;
import negocio.UtilidadesN;

public class SAMarcaImp implements SAMarca {

	public synchronized int altaMarca(TMarca tMarca) {
		EntityManager em = null;
		int id = -1;

		if (ValidadorMarca.comprobarDatos(tMarca)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();

				Marca marca;

				// Hay que comprobar si la marca existe ya, devuelve lista
				// porque esta función no lanza excepción y es mas facil de
				// tratar
				List<Marca> resultados = em.createNamedQuery("negocio.marca.Marca.findBynombre", Marca.class)
						.setParameter("nombre", tMarca.getNombre())
						// .setLockMode(arg0)
						.getResultList();
				// Si es necesario bloquear añadir -> .setLockMode

				if (resultados.isEmpty()) { // Si la lista está vacía quiere
											// decir que no hay marcas con ese
											// nombre
					marca = new Marca(tMarca);
					em.persist(marca);
					em.getTransaction().commit();
					// IMPORTANTE si quieres obtener el id se hace
					// obligatoriamente después de hacer el commit
					id = marca.getId();
				} else { // Si no está vacía hay un único registro, porque
							// nombre es unique
					marca = resultados.get(0);
					if (!marca.getActivo()) { // Si está inactivo, lo reactivo y asignamos datos de entrada para modificarlos. Para modificar una entidad basta con hacer sets y commit
						marca.setActivo(true);
						marca.setOrigen(tMarca.getOrigen());
						em.getTransaction().commit();
						id = marca.getId();
					} else { // Si está activa no puedo reactivarla
						em.getTransaction().rollback();
					}
				}
			} catch (Exception e) { // excepcion por si falla algo de
									// transaccion
				if (em != null && em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}

			} finally {
				if (em != null) {
					em.close();
				}

			}
		}

		return id;
	}

	public boolean bajaMarca(int id) {

		return false;
	}

	public TMarca consultarMarcaPorId(int id) {
		EntityManager em = null;
		TMarca tMarca = null;

		if (UtilidadesN.comprobarId(id)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();

				Marca marca = em.find(Marca.class, id);

				if (marca != null) {
					tMarca = marca.toTransfer();
				}

				em.getTransaction().commit();

			} catch (Exception e) { // excepcion por si falla algo de
									// transaccion
				if (em != null && em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}

			} finally {
				if (em != null) {
					em.close();
				}

			}
		}

		return tMarca;
	}

	public List<TMarca> consultarMarcas() {
		EntityManager em = null;
		List<TMarca> listaMarcas = new ArrayList<TMarca>();

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();
			em.getTransaction().begin();

			List<Marca> resultados = em.createNamedQuery("negocio.marca.Marca.findAll", Marca.class).getResultList();

			for (Marca marca : resultados) {
				listaMarcas.add(marca.toTransfer());
			}

			em.getTransaction().commit();

		} catch (Exception e) { // excepcion por si falla algo de
								// transaccion
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if (em != null) {
				em.close();
			}
		}

		return listaMarcas;

	}

	public boolean modificarMarca(TMarca tMarca) {
		EntityManager em = null;
		boolean exito = false;

		if (ValidadorMarca.comprobarDatos(tMarca)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();

				Marca marca;
				
				marca = em.find(Marca.class, tMarca.getId());
				
				if (marca != null) {
					List<Marca> resultados = em.createNamedQuery("negocio.marca.Marca.findBynombre", Marca.class)
							.setParameter("nombre", tMarca.getNombre())
							.setLockMode(LockModeType.OPTIMISTIC)
							.getResultList();
					
					if (marca.getActivo()																	// Puedo modificar la marca si esta activa y si no existe marca con ese nombre
							&& (marca.getNombre().equals(tMarca.getNombre()) || resultados.isEmpty())) {	// o la que estoy modicando es la que he leido por nombre
						exito = true;
						marca.setNombre(tMarca.getNombre());
						marca.setOrigen(tMarca.getOrigen());
						em.getTransaction().commit();
					} else{
						em.getTransaction().rollback();
					}
				} else{
					em.getTransaction().rollback();
				}
			} catch (Exception e) { // excepcion por si falla algo de
									// transaccion
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