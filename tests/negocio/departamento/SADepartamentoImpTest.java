package negocio.departamento;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import integracion.factoria.EMFSingleton;
import negocio.empleado.Empleado;
import negocio.empleado.TEmpleado;
import negocio.empleado.TGerente;
import negocio.factoria.FactoriaNegocioMall;


public class SADepartamentoImpTest {
	@Test
	public void altaDepartamentoTest(){
		SADepartamento sad = FactoriaNegocioMall.getInstance().crearSADepartamento();
		
		TDepartamento tDep = new TDepartamento(-1, "departamento1", 1, 1.0, true);
		
		int id = sad.altaDepartamento(tDep);
		
		//primer caso exito
		assertEquals("El id deberia ser 1", 1, id);
		
		// nombre repetido
		id = sad.altaDepartamento(tDep);
		assertEquals("El id deberia ser -1", -1, id);
		 
		//Dar de alta departamento inactivo
		tDep.setNombre("departamento2");
		tDep.setActivo(false);
		id = sad.altaDepartamento(tDep);
		assertEquals("El id deberia ser 2", 2, id);
		 
		//reactivacion
		tDep.setActivo(true);
		id = sad.altaDepartamento(tDep);
		assertEquals("El id deberia ser 2", 2 , id);
	}
	
	@Test
	 public void bajaDepartamentoTest(){
		
		SADepartamento sad = FactoriaNegocioMall.getInstance().crearSADepartamento();
		
		TDepartamento tDep = new TDepartamento(-1, "departamento1", 1, 1.0, true);
		 int id = sad.altaDepartamento(tDep);
		 
		//ejemplo de depto sin empleado (tiene que darlo de baja)
		 
		boolean ok = sad.bajaDepartamento(id);
		assertTrue("Deberia darse de baja", ok);
		
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		
		Departamento dep = em.find(Departamento.class, 1);
		Empleado emp = new Empleado(new TGerente(0, 1, 160, dep.getId(), false, 1, 10));
		emp.setDepartamento(dep);
		dep.getEmpleados().add(emp);
		em.persist(emp);

		em.getTransaction().commit();
		
		//como tiene un empleado deberia prohibirse dar de baja
		boolean exito = sad.bajaDepartamento(id);
		assertFalse("No deberia darse de baja", exito);
		
		//ahora tiene un empleado no activo(deberia poder darlo de baja)
		boolean exito2 = sad.bajaDepartamento(id);
		assertTrue("Deberia darse de baja", exito2);
		
	}
	
	@Test
	public void consultarDepartamentosTest(){
		
		SADepartamento sad = FactoriaNegocioMall.getInstance().crearSADepartamento();
		
		TDepartamento tDep = new TDepartamento(-1, "Departamento1", 1, 1.0, true);
		sad.altaDepartamento(tDep);
		
		TDepartamento tDep2 = new TDepartamento(-1, "Departamento2", 2, 2.0, false);
		sad.altaDepartamento(tDep2);
		
		TDepartamento tDep3 = new TDepartamento(-1, "Departamento3", 3, 3.0, true);
		sad.altaDepartamento(tDep3);
		
		TDepartamento tDep4 = new TDepartamento(-1, "Departamento4", 4, 4.0, false);
		sad.altaDepartamento(tDep4);
		
		List<TDepartamento> departamentos = sad.consultarDepartamentos();
		
		assertEquals("Tendrían que existir 4 (cuatro) deps", 4, departamentos.size());
		
	}
	
	@Test
	public void consultarDepartamentoPorIdTest(){
		SADepartamento sad = FactoriaNegocioMall.getInstance().crearSADepartamento();
		
		TDepartamento tDep = new TDepartamento(-1, "Departamento1", 1, 1.0, true);
		sad.altaDepartamento(tDep);
		
		TDepartamento depResult = sad.consultarDepartamentoPorId(1);
		
		assertEquals("El nombre tendria que ser Departamento1", "Departamento1", depResult.getNombre());
		
		//si no existe debe comprobar que sea null
		TDepartamento depResult2 = sad.consultarDepartamentoPorId(2);

		assertNull("No existe, tiene que ser null", depResult2);
		
	}
	
	@Test
	public void modificarDepartamentoTest(){
		
		SADepartamento sad = FactoriaNegocioMall.getInstance().crearSADepartamento();
		TDepartamento tDep = new TDepartamento(-1, "Departamento1", 1, 1.0, true);
		sad.altaDepartamento(tDep);
		
		// modifico el departamento
		tDep.setId(1);
		tDep.setNombre("Dep1");
		tDep.setSala(2);
		tDep.setSueldoHora(60.2);
		boolean ok = sad.modificarDepartamento(tDep);
		assertTrue("Deberia poder modificarse", ok);
		
		// no existe el departamento
		
		tDep.setId(20);
		ok = sad.modificarDepartamento(tDep);
		assertFalse("No tendria que modificarse", ok);
		
		// departamento inactivo
		
		tDep = new TDepartamento(-1, "departamentoN1", 1, 1.0, false);
		sad.altaDepartamento(tDep);
		tDep.setId(2);
		tDep.setSala(2);
		ok = sad.modificarDepartamento(tDep);
		assertFalse("no se debería modificar", ok);
		
	}

}
