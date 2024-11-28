package negocio.marca;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import integracion.factoria.EMFSingleton;

public class SAMarcaImp implements SAMarca {

	public synchronized int altaMarca(TMarca tMarca) {
		EntityManager em = null;
		int id = -1;

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();
			em.getTransaction().begin();
			
			
			Marca marca;
			
			//Hay que comprobar si la marca existe ya
			List<Marca> resultados = em.createNamedQuery("negocio.marca.Marca.findBynombre", Marca.class)
                    .setParameter("nombre", tMarca.getNombre())
                    .getResultList();
			
			if (resultados.isEmpty()){ //Si la lista está vacía quiere decir que no hay marcas
				marca = new Marca(tMarca);
				em.persist(marca);
				id = marca.getId();
				em.getTransaction().commit();
			} else{ // Si no está vacía hay un único registro, porque nombre es unique
				marca = resultados.get(0);
				if (!marca.getActivo()){ // Si está inactivo, lo reactivo
					marca.setActivo(true);
					em.persist(marca);
					id = marca.getId();
					em.getTransaction().commit();
				} else{ // Si está activa no puedo reactivarla
					em.getTransaction().rollback();
				}
			}

			em.close();

		} 
		catch (Exception e) { // excepcion por si falla algo de transaccion
			if (em != null && em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			
		} finally{
			if (em != null){
				em.close();
			}
			
		}
		
		return id;
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