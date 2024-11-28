package negocio.marca;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import integracion.factoria.EMFSingleton;

public class SAMarcaImp implements SAMarca {

	public synchronized int altaMarca(TMarca marca) {
		EntityManager em = null;

		try {
			// ------------------------------------------------------
			em = EMFSingleton.getInstance().getEMF().createEntityManager();

			// ------------------------------------------------------
			em.getTransaction().begin();

			int id = -1;

			// ------------------------------------------------------
			// Marca oldMarca =
			// em.createNamedQuery("negocio.marca.Marca.findBynombre").getFirstResult();

			Marca newMarca = new Marca(marca);

			em.persist(newMarca);

			id = newMarca.getId();
			// ------------------------------------------------------

			em.getTransaction().commit();
			// ------------------------------------------------------

			em.close();

			return id;

		} catch (OptimisticLockException e5) { // excepcion para el bloqueo
			em.getTransaction().rollback();
			em.close();
			return -1;
		} catch (PersistenceException e) { // excepcion para denro de la transacion TODO
			em.getTransaction().rollback();
			em.close();
			return -1;
		} 
		catch (RuntimeException e2) { // excepcion por soi falla algo de transaccion
			em.close();
			return -1;
		} catch (Exception e3) { // excepcion por si peta otra cosa
			return -1;
		}
	}

	public boolean bajaMarca(int id) {

		return false;
	}

	public TMarca consultarMarcaPorId(int id) {

		return null;
	}

	public List<TMarca> consultarMarcas() {

		return null;
	}

	public boolean modificarMarca(TMarca marca) {

		return false;
	}
}