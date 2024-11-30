package negocio.empleado;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
import org.junit.Test;

import integracion.factoria.EMFSingleton;
import negocio.departamento.Departamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocioMall;
import negocio.factoria.FactoriaNegocioMallImp;

public class SAEmpleadoImpTest {

	@Test
	public void altaEmpleadoTest() {
		EMFSingleton emf = EMFSingleton.getInstance();

		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();

		int id = sa.altaEmpleado(new TGerente(0, 1, 160, d.getId(), true, 1, 10));

		assertTrue(id > 0);
		em.close();
	}

	@Test
	public void altaYaMetido() {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();

		int id = sa.altaEmpleado(new TGerente(0, 1, 160, d.getId(), true, 1, 10));
		int newId = sa.altaEmpleado(new TGerente(0, 1, 160, d.getId(), true, 1, 10));

		assertEquals(newId, -1);
		em.close();
	}

	@Test
	public void altaActivoFalse() {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();

		int id = sa.altaEmpleado(new TGerente(0, 1, 160, d.getId(), false, 1, 10));
		int newId = sa.altaEmpleado(new TGerente(id, 1, 160, d.getId(), true, 1, 10));

		assertEquals(newId, id);
		em.close();
	}
}
