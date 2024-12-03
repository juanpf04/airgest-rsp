package negocio.empleado;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

import java.util.List;

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
	
	@Test
	public void bajaEmpleadoTest() {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();

		int id = sa.altaEmpleado(new TGerente(0, 1, 160, d.getId(), false, 1, 10));
		
		boolean done = sa.bajaEmpleado(id);
		assertTrue(done);
		em.close();
	}
	
	@Test 
	public void bajaEmpleadoNoExistenteTest() {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();
		
		boolean done = sa.bajaEmpleado(1);
		
		assertFalse(done);
		em.close();
	}
	
	
	@Test 
	public void bajaEmpleadoNoActivoTest() {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();

		int id = sa.altaEmpleado(new TGerente(0, 1, 160, d.getId(), false, 1, 10));
		
		sa.bajaEmpleado(id);
		boolean done = sa.bajaEmpleado(id);
		
		assertFalse(done);
		em.close();
	}
	
	
	@Test
	public void consultarPorIdTest() {
		EMFSingleton emf = EMFSingleton.getInstance();

		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();

		int id = sa.altaEmpleado(new TGerente(0, 1, 160, d.getId(), true, 1, 10));
		
		TEmpleado emp = sa.consultarEmpleadoPorId(id);
		assertEquals(1, emp.getTag()); // Si los tags son iguales, es el mismo
		em.close();
	}
	
	@Test
	public void consultarPorIdInexistenteTest() {
		EMFSingleton emf = EMFSingleton.getInstance();

		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();
		
		TEmpleado emp = sa.consultarEmpleadoPorId(1);
		assertNull(emp);
		em.close();
	}
	
	
	@Test
	public void listarTodosTest() {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();

		sa.altaEmpleado(new TGerente(0, 1, 160, d.getId(), true, 1, 10));
		sa.altaEmpleado(new TGerente(0, 2, 160, d.getId(), true, 1, 10));
		sa.altaEmpleado(new TGerente(0, 3, 160, d.getId(), true, 1, 10));
		sa.altaEmpleado(new TGerente(0, 4, 160, d.getId(), true, 1, 10));
		sa.altaEmpleado(new TDependiente(0, 5, 160, d.getId(), true, 10, true));

		List<TEmpleado> l = sa.consultarEmpleados();
		
		assertEquals(l.size(), 5);
		em.close();
	}
	
	@Test
	public void modificarEmpleadoTest() {
		EMFSingleton emf = EMFSingleton.getInstance();

		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();

		int id = sa.altaEmpleado(new TGerente(0, 1, 160, d.getId(), true, 1, 10));

		int horasMensuales = 1600;
		boolean done = sa.modificarEmpleado(new TGerente(id, 1, horasMensuales, d.getId(), true, 1, 10));
		TEmpleado emp = sa.consultarEmpleadoPorId(id);
		assertTrue(done && emp.getHorasMensuales() == horasMensuales);
		em.close();
	}
	
	@Test
	public void modificarEmpleadoTagDuplicadoTest() {
		EMFSingleton emf = EMFSingleton.getInstance();

		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();

		int id = sa.altaEmpleado(new TGerente(0, 1, 160, d.getId(), true, 1, 10));
		sa.altaEmpleado(new TGerente(0, 2, 160, d.getId(), true, 1, 10));
		
		boolean done = sa.modificarEmpleado(new TGerente(id, 2, 160, d.getId(), true, 1, 10));
		assertFalse(done);
		em.close();
	}
	
	@Test
	public void modificarEmpleadoInexistenteTest() {
		EMFSingleton emf = EMFSingleton.getInstance();

		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();

		
		boolean done = sa.modificarEmpleado(new TGerente(1, 2, 160, d.getId(), true, 1, 10));
		assertFalse(done);
		em.close();
	}
	
	@Test
	public void listarPorDepartamentoTest() {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

		em.persist(d);
		em.getTransaction().commit();
		FactoriaNegocioMall fm = new FactoriaNegocioMallImp();
		SAEmpleado sa = fm.crearSAEmpleado();

		sa.altaEmpleado(new TGerente(0, 1, 160, d.getId(), true, 1, 10));
		sa.altaEmpleado(new TGerente(0, 2, 160, d.getId(), true, 1, 10));
		sa.altaEmpleado(new TGerente(0, 3, 160, d.getId(), true, 1, 10));
		sa.altaEmpleado(new TGerente(0, 4, 160, d.getId(), true, 1, 10));
		sa.altaEmpleado(new TDependiente(0, 5, 160, d.getId(), true, 10, true));

		List<TEmpleado> l = sa.consultarEmpleadosPorDepartamento(d.getId());
		
		assertEquals(l.size(), 5);
		em.close();
	}
	
}
