package negocio.empleado;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import integracion.factoria.EMFSingleton;
import negocio.departamento.Departamento;
import negocio.UtilidadesN;

public class SAEmpleadoImp implements SAEmpleado {

	private synchronized Gerente altaGerente(TGerente tGerente, Departamento departamento) {
		Gerente gerente = new Gerente(tGerente);
		gerente.setDepartamento(departamento);

		return gerente;
	}

	private synchronized Dependiente altaDependiente(TDependiente tDependiente, Departamento departamento) {
		Dependiente dependiente = new Dependiente(tDependiente);
		dependiente.setDepartamento(departamento);

		return dependiente;
	}

	public synchronized int altaEmpleado(TEmpleado tEmpleado) {
		EntityManager em = null;
		int id = -1;

		if (ValidadorEmpleado.comprobarDatos(tEmpleado)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Departamento departamento = em.find(Departamento.class, tEmpleado.getIdDepartamento(),
						LockModeType.OPTIMISTIC_FORCE_INCREMENT);

				List<Empleado> empleados = em.createNamedQuery("negocio.empleado.Empleado.findBytag", Empleado.class)
						.setParameter("tag", tEmpleado.getTag()).getResultList();

				if (empleados.isEmpty() && departamento != null && departamento.getActivo()) {
					Empleado empleado;
					if (tEmpleado instanceof TGerente)
						empleado = altaGerente((TGerente) tEmpleado, departamento);
					else
						empleado = altaDependiente((TDependiente) tEmpleado, departamento);

					departamento.getEmpleados().add(empleado);
					em.persist(empleado);

					em.getTransaction().commit();

					id = empleado.getId();

				} else {
					Empleado empleado = empleados.get(0);

					if (empleado.getActivo())
						em.getTransaction().rollback();
					else {
						if (tEmpleado instanceof TGerente && empleado instanceof Gerente) {
							((Gerente) empleado).setHorasExtra(((TGerente) tEmpleado).getHorasExtra());
							((Gerente) empleado).setDespacho(((TGerente) tEmpleado).getDespacho());
							empleado.getDepartamento().getEmpleados().remove(empleado);
							empleado.setActivo(true);
							empleado.setDepartamento(departamento);
							empleado.setHorasMensuales(tEmpleado.getHorasMensuales());
							empleado.setTag(tEmpleado.getTag());
							departamento.getEmpleados().add(empleado);

							em.persist(empleado);

							em.getTransaction().commit();

							id = empleado.getId();
						} else if (tEmpleado instanceof TDependiente && empleado instanceof Dependiente) {
							((Dependiente) empleado).setSeccion(((TDependiente) tEmpleado).getSeccion());
							((Dependiente) empleado).setNoches(((TDependiente) tEmpleado).getNoches());
							empleado.getDepartamento().getEmpleados().remove(empleado);
							empleado.setActivo(true);
							empleado.setDepartamento(departamento);
							empleado.setHorasMensuales(tEmpleado.getHorasMensuales());
							empleado.setTag(tEmpleado.getTag());
							departamento.getEmpleados().add(empleado);

							em.persist(empleado);

							em.getTransaction().commit();

							id = empleado.getId();
						} else
							em.getTransaction().rollback();
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

	public boolean bajaEmpleado(int id) {
		EntityManager em = null;
		boolean exito = false;

		if (UtilidadesN.comprobarId(id)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

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
		}

		return exito;

	}

	public TEmpleado consultarEmpleadoPorId(int id) {
		EntityManager em = null;
		TEmpleado tEmpleado = null;

		if (UtilidadesN.comprobarId(id)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Empleado empleado = em.find(Empleado.class, id);

				if (empleado != null)
					tEmpleado = empleado.toTransfer();

				em.getTransaction().commit();
			} catch (Exception e) {
				if (em != null && em.getTransaction().isActive())
					em.getTransaction().rollback();
			} finally {
				if (em != null)
					em.close();
			}
		}

		return tEmpleado;
	}

	public List<TEmpleado> consultarEmpleados() {
		EntityManager em = null;
		List<TEmpleado> tEmpleados = new ArrayList<>();

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();

			em.getTransaction().begin();

			List<Empleado> empleados = em.createNamedQuery("negocio.empleado.Empleado.findAll", Empleado.class)
					.getResultList();

			for (Empleado empleado : empleados)
				tEmpleados.add(empleado.toTransfer());

			em.getTransaction().commit();

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			if (em != null)
				em.close();
		}

		return tEmpleados;
	}

	public boolean modificarEmpleado(TEmpleado tEmpleado) {
		EntityManager em = null;
		boolean exito = false;

		if (ValidadorEmpleado.comprobarDatos(tEmpleado)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Departamento departamento = em.find(Departamento.class, tEmpleado.getIdDepartamento(),
						LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				Empleado empleado = em.find(Empleado.class, tEmpleado.getId());

				if (empleado != null && departamento != null && empleado.getActivo() && departamento.getActivo()) {

					List<Empleado> empleados = em
							.createNamedQuery("negocio.empleado.Empleado.findBytag", Empleado.class)
							.setLockMode(LockModeType.OPTIMISTIC)
							.setParameter("tag", tEmpleado.getTag()).getResultList();

					if (empleados.size() == 0 || empleados.get(0).equals(empleado)) {

						empleado.setTag(tEmpleado.getTag());
						empleado.setHorasMensuales(tEmpleado.getHorasMensuales());

						// Elimino del antiguo departamento
						empleado.getDepartamento().getEmpleados().remove(empleado);

						empleado.setDepartamento(departamento);
						departamento.getEmpleados().add(empleado);
						if (tEmpleado instanceof TGerente && empleados.get(0) instanceof Gerente) {
							TGerente tgerente = (TGerente) tEmpleado;

							((Gerente) empleado).setDespacho(tgerente.getDespacho());
							((Gerente) empleado).setHorasExtra(tgerente.getHorasExtra());
							exito = true;
						} else if (tEmpleado instanceof TDependiente && empleados.get(0) instanceof Dependiente) {
							TDependiente tdependiente = (TDependiente) tEmpleado;

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
		}

		return exito;
	}

	public List<TEmpleado> consultarEmpleadosPorDepartamento(int idDepartamento) {
		EntityManager em = null;
		List<TEmpleado> tEmpleados = new ArrayList<>();

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();

			em.getTransaction().begin();

			Departamento departamento = em.find(Departamento.class, idDepartamento);

			for (Empleado empleado : departamento.getEmpleados())
				tEmpleados.add(empleado.toTransfer());

			em.getTransaction().commit();

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			if (em != null)
				em.close();
		}

		return tEmpleados;
	}
}