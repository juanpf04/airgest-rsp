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
		
		int id = sa.altaEmpleado(new TGerente(0, 1, 160, 1, true, 1, 10));
		
		assertTrue(id > 0);
	}
}
