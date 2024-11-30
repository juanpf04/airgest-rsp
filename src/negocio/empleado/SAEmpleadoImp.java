
package negocio.empleado;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import integracion.factoria.EMFSingleton;
import negocio.departamento.Departamento;
import negocio.venta.Venta;
import negocio.UtilidadesN;

public class SAEmpleadoImp implements SAEmpleado {

	private synchronized Gerente altaGerente(TGerente gerente, Departamento d) {
		Gerente g = new Gerente(gerente);

		g.setDepartamento(d);
		g.setVentas(new ArrayList<Venta>());
		
		return g;
	}

	private synchronized Dependiente altaDependiente(TDependiente dependiente, Departamento d) {
		Dependiente dp = new Dependiente(dependiente);

		dp.setDepartamento(d);
		dp.setVentas(new ArrayList<Venta>());
		
		return dp;
	}

	public synchronized int altaEmpleado(TEmpleado empleado) {
		if (!ValidadorEmpleado.comprobarDatos(empleado))
			return -1;

		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		try {

			em.getTransaction().begin();

			Departamento d = em.find(Departamento.class, empleado.getIdDepartamento());
			em.lock(d, LockModeType.OPTIMISTIC_FORCE_INCREMENT);

			Empleado e;
			try {
				e = em.createNamedQuery("negocio.empleado.Empleado.findBytag", Empleado.class)
						.setParameter("tag", empleado.getTag()).getSingleResult();
			} catch (Exception ex) {
				e = null;
			}

			if (e == null) {
				if (empleado instanceof TGerente)
					e = altaGerente((TGerente) empleado, d);
				else
					e = altaDependiente((TDependiente) empleado, d);

				em.persist(e);
				
				if (!em.contains(e))
					em.getTransaction().rollback();
				else
					em.getTransaction().commit();
				
				return e.getId();

			} else {
				if (e.getActivo()) {
					em.getTransaction().rollback();
					return -1;
				} else {
					e.setActivo(true);
					em.persist(e);
					em.getTransaction().commit();
					return e.getId();
				}
			}

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
			return -1;
		} finally {
			if (em != null) em.close();
		}
	}

	public boolean bajaEmpleado(int id) {
		if (!UtilidadesN.comprobarId(id)) return false;
		boolean done = false;
		
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		try {
			
		em.getTransaction().begin();
		Empleado e = em.find(Empleado.class, id);
		
		if (e != null && e.getActivo()) {
			e.setActivo(false);
			done = true;
		}
		
		
		if (done) em.getTransaction().commit();
		else em.getTransaction().rollback();
		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
		} finally {
			if (em != null) em.close();			
		}
		
		return done;
		
		
	}

	public TEmpleado consultarEmpleadoPorId(int id) {
		
	}

	public List<TEmpleado> consultarEmpleados() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public boolean modificarEmpleado(TEmpleado empleado) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}

	public List<TEmpleado> consultarEmpleadosPorDepartamento(int idDepartamento) {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		try {
			em.getTransaction().begin();
		
		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
			return null;
		} finally {
			if (em != null) em.close();
		}
	}
}