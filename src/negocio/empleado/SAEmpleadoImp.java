
package negocio.empleado;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import integracion.factoria.EMFSingleton;
import negocio.departamento.Departamento;
import negocio.UtilidadesN;

public class SAEmpleadoImp implements SAEmpleado {

	private synchronized Gerente altaGerente(TGerente gerente, Departamento d) {
		Gerente gerenteN = new Gerente(gerente);

		gerenteN.setDepartamento(d);

		return gerenteN;
	}

	private synchronized Dependiente altaDependiente(TDependiente dependiente, Departamento d) {
		Dependiente dependienteN = new Dependiente(dependiente);

		dependienteN.setDepartamento(d);

		return dependienteN;
	}

	public synchronized int altaEmpleado(TEmpleado templeado) {
		if (!ValidadorEmpleado.comprobarDatos(templeado))
			return -1;
		EntityManager em = null;
		int id = -1;
		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();

			em.getTransaction().begin();

			Departamento departamento = em.find(Departamento.class, templeado.getIdDepartamento());
			em.lock(departamento, LockModeType.OPTIMISTIC_FORCE_INCREMENT);

			Empleado empleado;
			List<Empleado> lista = em.createNamedQuery("negocio.empleado.Empleado.findBytag", Empleado.class)
					.setParameter("tag", templeado.getTag()).getResultList();

			if (lista.isEmpty() && departamento != null && departamento.getActivo()) {
				if (templeado instanceof TGerente)
					empleado = altaGerente((TGerente) templeado, departamento);
				else
					empleado = altaDependiente((TDependiente) templeado, departamento);

				departamento.getEmpleados().add(empleado);
				em.persist(empleado);
				em.getTransaction().commit();

				id = empleado.getId();
				

			} else {
				empleado = lista.get(0);
				if (empleado.getActivo()) {
					em.getTransaction().rollback();
					return id;
					
				} else {
					if(templeado instanceof TGerente && empleado instanceof Gerente){
						((Gerente)empleado).setHorasExtra(((TGerente) templeado).getHorasExtra());
						((Gerente)empleado).setDespacho(((TGerente) templeado).getDespacho());
						empleado.getDepartamento().getEmpleados().remove(empleado);
						empleado.setActivo(true);
						empleado.setDepartamento(departamento);
						empleado.setHorasMensuales(templeado.getHorasMensuales());
						empleado.setTag(templeado.getTag());
						departamento.getEmpleados().add(empleado);
						em.persist(empleado);
						em.getTransaction().commit();
						id = empleado.getId();
					}
					else if (templeado instanceof TDependiente && empleado instanceof Dependiente){
						((Dependiente)empleado).setSeccion(((TDependiente) templeado).getSeccion());
						((Dependiente)empleado).setNoches(((TDependiente) templeado).getNoches());
						empleado.getDepartamento().getEmpleados().remove(empleado);
						empleado.setActivo(true);
						empleado.setDepartamento(departamento);
						empleado.setHorasMensuales(templeado.getHorasMensuales());
						empleado.setTag(templeado.getTag());
						departamento.getEmpleados().add(empleado);
						em.persist(empleado);
						em.getTransaction().commit();
						id = empleado.getId();
					}
					else em.getTransaction().rollback();
					//elimino del antiguo dpto
					
				}
			}

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
			return -1;
		} finally {
			if (em != null)
				em.close();
		}
		
		return id;
	}

	public boolean bajaEmpleado(int id) {
		if (!UtilidadesN.comprobarId(id))
			return false;
		boolean exito = false;

		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		try {

			em.getTransaction().begin();
			Empleado empleado = em.find(Empleado.class, id);

			if (empleado != null && empleado.getActivo() && empleado.getVentas().isEmpty()) {
				empleado.setActivo(false);
				exito = true;
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

		return exito;

	}

	public TEmpleado consultarEmpleadoPorId(int id) {
		if (!UtilidadesN.comprobarId(id))
			return null;

		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		TEmpleado templeado = null;
		try {
			em.getTransaction().begin();

			Empleado empleado = em.find(Empleado.class, id);
			if (empleado != null) {
				templeado = empleado.toTransfer();
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			if (em != null)
				em.close();
		}
		return templeado;
	}

	public List<TEmpleado> consultarEmpleados() {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();

		List<TEmpleado> listaEmpleados = new ArrayList<>();
		try {
			em.getTransaction().begin();
			List<Empleado> resultados = em.createNamedQuery("negocio.empleado.Empleado.findAll", Empleado.class)
					.getResultList();

			for (Empleado empleado : resultados) {
				listaEmpleados.add(empleado.toTransfer());
			}

			em.getTransaction().commit();

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
			return null;
		} finally {
			if (em != null)
				em.close();
		}

		return listaEmpleados;
	}

	public boolean modificarEmpleado(TEmpleado templeado) {
		if (!ValidadorEmpleado.comprobarDatos(templeado))
			return false;
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		boolean exito = false;

		try {
			em.getTransaction().begin();

			Departamento departamento = em.find(Departamento.class, templeado.getIdDepartamento());
			Empleado empleado = em.find(Empleado.class, templeado.getId());
			em.lock(departamento, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			em.lock(empleado, LockModeType.OPTIMISTIC);

			if (empleado != null && departamento != null && empleado.getActivo() && departamento.getActivo()) {

				List<Empleado> listaEmpleados = em.createNamedQuery("negocio.empleado.Empleado.findBytag", Empleado.class)
						.setParameter("tag", templeado.getTag()).getResultList();

				if (listaEmpleados.size() == 0 || listaEmpleados.get(0).equals(empleado)) {

					empleado.setTag(templeado.getTag());
					empleado.setHorasMensuales(templeado.getHorasMensuales());
					
					//Elimino del antiguo departamento
					empleado.getDepartamento().getEmpleados().remove(empleado);
					
					empleado.setDepartamento(departamento);
					departamento.getEmpleados().add(empleado);
					if (templeado instanceof TGerente && listaEmpleados.get(0) instanceof Gerente) {
						TGerente tgerente = (TGerente) templeado;

						((Gerente) empleado).setDespacho(tgerente.getDespacho());
						((Gerente) empleado).setHorasExtra(tgerente.getHorasExtra());
						exito = true;
					} else if (templeado instanceof TDependiente && listaEmpleados.get(0) instanceof Dependiente) {
						TDependiente tdependiente = (TDependiente) templeado;

						((Dependiente) empleado).setNoches(tdependiente.getNoches());
						((Dependiente) empleado).setSeccion(tdependiente.getSeccion());
						exito = true;
					}
					
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
		return exito;
	}

	public List<TEmpleado> consultarEmpleadosPorDepartamento(int idDepartamento) {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();

		List<TEmpleado> listaEmpleado = new ArrayList<>();
		try {
			em.getTransaction().begin();
			
			Departamento dpto = em.find(Departamento.class, idDepartamento);

			for (Empleado empleado : dpto.getEmpleados()) {
				listaEmpleado.add(empleado.toTransfer());
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
			return null;
		} finally {
			if (em != null)
				em.close();
		}

		return listaEmpleado;
	}
}