
package negocio.empleado;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import integracion.factoria.EMFSingleton;
import negocio.departamento.Departamento;
import negocio.venta.Venta;

public class SAEmpleadoImp implements SAEmpleado {

	public synchronized int altaEmpleado(TEmpleado empleado) {
		if (!ValidadorEmpleado.comprobarDatos(empleado))
			return -1;

		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		try {

			em.getTransaction().begin();
			
			Departamento d = em.find(Departamento.class, empleado.getIdDepartamento());
			em.lock(d, LockModeType.OPTIMISTIC_FORCE_INCREMENT);

			Empleado e = em.createNamedQuery("Empleado.findBytag", Empleado.class).getSingleResult();

			if (e == null) {
				e = new Empleado(empleado);
				e.setDepartamento(d);
				e.setVentas(new ArrayList<Venta>());

				em.persist(e);

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
			em.getTransaction().rollback();
			return -1;
		} finally {
			em.close();
			emf.getEMF().close();
		}
	}

	public boolean bajaEmpleado(int id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}

	public TEmpleado consultarEmpleadoPorId(int id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
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
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}