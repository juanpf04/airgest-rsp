package integracion.personal;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import integracion.UtilidadesI;
import integracion.factoria.FactoriaIntegracionImp;
import integracion.hangar.DAOHangar;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.personal.TPSeguridad;
import negocio.personal.TPersonal;
import negocio.hangar.THangar;
import negocio.personal.TPLimpieza;

public class DAOPersonalTest {

	@Test
	public void altaPersonalTest() {
		UtilidadesI.esTest();

		DAOPersonal daoPersonal = new DAOPersonalImp();

		TPersonal personal;

		File carpeta = new File(UtilidadesI.ruta("personal"));
		File[] lista = carpeta.listFiles();

		personal = new TPSeguridad(0, 12345, "Seguridad", true, 56789);
		assertEquals("No ha devuelto el id correcto", lista.length + 1, daoPersonal.altaPersonal(personal));
		
		personal = new TPLimpieza(0, 67890, "Limpieza", true, "Supervisor");
		assertEquals("No ha devuelto el id correcto", lista.length + 2, daoPersonal.altaPersonal(personal));
	}

	@Test
	public void bajaPersonalTest() {//peerfe
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOPersonal dp = FactoriaIntegracionImp.getInstance().crearDAOPersonal();

		boolean id = dp.bajaPersonal(1);
//		boolean id2 = dp.bajaPersonal(2);
		t.commit();
		assertTrue("No se ha dado de baja", id);
//		assertFalse("Se ha dado de baja un empleado que no existe", id2);
	}

	@Test
	public void modificarPersonalTest() {
		UtilidadesI.esTest();

		DAOPersonal daoPersonal = new DAOPersonalImp();

		TPSeguridad personal = new TPSeguridad(1, 12345, "Seguridad", true, 56789);

		assertTrue("Ha leído mal el fichero", daoPersonal.modificarPersonal(personal));
	}

	@Test
	public void consultarPersonalPorIdTest() {
		UtilidadesI.esTest();

		DAOPersonal daoPersonal = new DAOPersonalImp();

		assertEquals("El personal con id 3 debe tener el idEmpleado 67890", 67890,
				daoPersonal.consultarPersonalPorId(3).getIdEmpleado());
	}

	@Test
	public void consultarTodosPersonalTest() {//perfee
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOPersonal dp = FactoriaIntegracionImp.getInstance().crearDAOPersonal();

		List<TPersonal> id = dp.consultarPersonalExistente();
		t.commit();

		assertEquals("No hay el mismo numero de entidades que en la base de datos", 2, id.size());
		System.out.println(id);
	}

}
