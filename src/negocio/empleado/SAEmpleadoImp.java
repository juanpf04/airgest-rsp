
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
		EntityManager em = null;
		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();

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

				List<Empleado> newList = d.getEmpleados();
				newList.add(e);
				
				d.setEmpleados(newList);
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
		if (!UtilidadesN.comprobarId(id)) return null;
		
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		TEmpleado te = null;
		try {
			em.getTransaction().begin();
			
			Empleado e = em.find(Empleado.class, id);
			if (e != null) {
				te = e.toTransfer();
			}
			
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
		} finally {
			if (em != null) em.close();
		}
		return te;
	}

	public List<TEmpleado> consultarEmpleados() {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		
		List<TEmpleado> l = new ArrayList<>();
		try {
			em.getTransaction().begin();
			List<Empleado> le = em.createNamedQuery("negocio.empleado.Empleado.findAll", Empleado.class).getResultList();
			
			for(Empleado emple : le){
				l.add(emple.toTransfer());
			}
					
			em.getTransaction().commit();
		
		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
			return null;
		} finally {
			if (em != null) em.close();
		}
		
		return l;
	}
	
	public boolean modificarEmpleado(TEmpleado empleado) {
		if (!ValidadorEmpleado.comprobarDatos(empleado)) return false;
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		boolean done = false;
		
		try {
			em.getTransaction().begin();
			
			Departamento d = em.find(Departamento.class, empleado.getIdDepartamento());
			Empleado e = em.find(Empleado.class, empleado.getId());
			if (e != null && d != null) {
				if (e.getActivo() && e.getTag() == empleado.getTag()) {
					em.lock(d, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
					em.lock(e, LockModeType.OPTIMISTIC);
					
					if (empleado instanceof TGerente) {
						TGerente tg = (TGerente) empleado;
						Gerente g = (Gerente) e;
						
						g.setDespacho(tg.getDespacho());
						g.setHorasExtra(tg.getHorasExtra());
					} else {
						TDependiente tdp = (TDependiente) empleado; // teledeporte xd
						Dependiente dp = (Dependiente) e;
						
						dp.setNoches(tdp.getNoches());
						dp.setSeccion(tdp.getSeccion());

					}	
					done = true;
				}
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

	public List<TEmpleado> consultarEmpleadosPorDepartamento(int idDepartamento) {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		
		List<TEmpleado> l = new ArrayList<>();
		try {
			em.getTransaction().begin();
			List<Empleado> le = em.createNamedQuery("negocio.empleado.Empleado.findBydepartamento", Empleado.class)
					.setParameter("departamento", em.find(Departamento.class, idDepartamento)).getResultList();
			
			for(Empleado emple : le){
				l.add(emple.toTransfer());
			}
			em.getTransaction().commit();
		
		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
			return null;
		} finally {
			if (em != null) em.close();
		}
		
		return l;
	}
}