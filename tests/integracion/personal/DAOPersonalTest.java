package integracion.personal;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import integracion.factoria.FactoriaIntegracion;
import integracion.factoria.FactoriaIntegracionImp;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.personal.TPSeguridad;
import negocio.personal.TPersonal;
import negocio.personal.TPLimpieza;

public class DAOPersonalTest {

	@Test
    public void altaPersonalTest() {//peerfe
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOPersonal daoPersonal = new DAOPersonalImp();

        TPersonal personal;

//        personal = new TPSeguridad(1, "12345678P", "Seguridad", true, 56789);
        personal = new TPLimpieza(1, "12345678P", "Limpieza", true, "56789");
        assertTrue("No se ha dado de alta", daoPersonal.altaPersonal(personal) >= 1);
        t.commit();
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
	public void modificarPersonalTest() {//perfee
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();

		DAOPersonal dp = FactoriaIntegracionImp.getInstance().crearDAOPersonal();

		TPSeguridad personal = new TPSeguridad(14, "12345678P", "Limpieza", true, 1);
		assertTrue("no se ha modificado bien", dp.modificarPersonal(personal));

//		personal = new TPLimpieza(id, "12345699P", "Limpieza", true, "1");

		t.commit();
	}

	@Test
	public void consultarPersonalPorIdTest() {//perfee
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
	    t.start();
		
        DAOPersonal dp = FactoriaIntegracionImp.getInstance().crearDAOPersonal();
        TPersonal p = dp.consultarPersonalPorId(1);    

		assertEquals("El personal con id 1", 1, p.getId());
		t.commit();
	}
	
	@Test
    public void consultarPersonalPorDni() {//perfee
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();

        DAOPersonal dp = FactoriaIntegracionImp.getInstance().crearDAOPersonal();
        
        TPersonal p = dp.consultarPersonalPorDni("12345678A");    
    
        assertEquals("No ha devuelto la fila correcta", 3, p.getId());
        t.commit();
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

	@Test
	public void consultarPersonalPorHangarTest(){//perfee
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOPersonal dp = FactoriaIntegracion.getInstance().crearDAOPersonal();
		List<TPersonal> lista = dp.consultarPersonalPorHangar(1);
		t.commit();
		assertEquals("No coincide el tamaño", 1, lista.size());
		System.out.println(lista);
	}
	
}
