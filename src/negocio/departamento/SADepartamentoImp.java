package negocio.departamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import integracion.factoria.EMFSingleton;
import negocio.UtilidadesN;
import negocio.empleado.Empleado;

public class SADepartamentoImp implements SADepartamento {

	public TDepartamento consultarDepartamentoPorId(int id) {
		EntityManager em = null;
		TDepartamento tDepartamento = null;

		if (UtilidadesN.comprobarId(id)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Departamento departamento = em.find(Departamento.class, id);

				if (departamento != null)
					tDepartamento = departamento.toTransfer();

				em.getTransaction().commit();

			} catch (Exception e) {
				if (em != null && em.getTransaction().isActive())
					em.getTransaction().rollback();
			} finally {
				if (em != null)
					em.close();
			}
		}

		return tDepartamento;
	}

	public synchronized int altaDepartamento(TDepartamento tDepartamento) {
		EntityManager em = null;
		int id = -1;

		if (ValidadorDepartamento.comprobarDatos(tDepartamento)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				List<Departamento> departamentos = em
						.createNamedQuery("negocio.departamento.Departamento.findBynombre", Departamento.class)
						.setParameter("nombre", tDepartamento.getNombre()).getResultList();

				if (departamentos.isEmpty()) {
					Departamento departamento = new Departamento(tDepartamento);
					em.persist(departamento);

					em.getTransaction().commit();

					id = departamento.getId();
				} else {
					Departamento departamento = departamentos.get(0);

					if (!departamento.getActivo()) {

						departamento.setActivo(true);
						departamento.setSala(tDepartamento.getSala());
						departamento.setSueldoHora(tDepartamento.getSueldoHora());

						em.getTransaction().commit();

						id = departamento.getId();
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

	public boolean bajaDepartamento(int id) {
		EntityManager em = null;
		boolean exito = false;

		if (UtilidadesN.comprobarId(id)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Departamento departamento = em.find(Departamento.class, id);

				if (departamento != null && departamento.getActivo()) {

					for (Empleado empleado : departamento.getEmpleados()) {
						em.lock(empleado, LockModeType.OPTIMISTIC);

						if (empleado.getActivo()) {
							em.getTransaction().rollback();
							return exito;
						}
					}

					departamento.setActivo(false);
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

	public List<TDepartamento> consultarDepartamentos() {
		EntityManager em = null;
		List<TDepartamento> tDepartamentos = new ArrayList<>();

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();

			em.getTransaction().begin();

			List<Departamento> departamentos = em
					.createNamedQuery("negocio.departamento.Departamento.findAll", Departamento.class).getResultList();

			for (Departamento departamento : departamentos)
				tDepartamentos.add(departamento.toTransfer());

			em.getTransaction().commit();

		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			if (em != null)
				em.close();
		}

		return tDepartamentos;

	}

	public boolean modificarDepartamento(TDepartamento tDepartamento) {
		EntityManager em = null;
		boolean exito = false;

		if (ValidadorDepartamento.comprobarDatos(tDepartamento)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Departamento departamento = em.find(Departamento.class, tDepartamento.getId());

				if (departamento != null && departamento.getActivo()) {
					List<Departamento> departamentos = em
							.createNamedQuery("negocio.departamento.Departamento.findBynombre", Departamento.class)
							.setParameter("nombre", tDepartamento.getNombre()).setLockMode(LockModeType.OPTIMISTIC)
							.getResultList();

					if (departamento.getNombre().equals(tDepartamento.getNombre()) || departamentos.isEmpty()) {

						exito = true;
						departamento.setNombre(tDepartamento.getNombre());
						departamento.setSala(tDepartamento.getSala());
						departamento.setSueldoHora(tDepartamento.getSueldoHora());

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

	public double calcularNomina(int id) {
		EntityManager em = null;
		double nomina = -1;

		if (UtilidadesN.comprobarId(id)) {

			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				em.getTransaction().begin();

				Departamento departamento = em.find(Departamento.class, id, LockModeType.OPTIMISTIC);

				if (departamento != null) {
					nomina = 0.0;
					for (Empleado empleado : departamento.getEmpleados()) {
						em.lock(empleado, LockModeType.OPTIMISTIC);

						if (empleado.getActivo())
							nomina += empleado.calcularSueldo();
					}
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

		return nomina;
	}
}