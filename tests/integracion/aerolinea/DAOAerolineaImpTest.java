package integracion.aerolinea;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import integracion.UtilidadesI;
import integracion.factoria.FactoriaIntegracion;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.aerolinea.TAerolinea;

public class DAOAerolineaImpTest {
	
	@Test
	public void alta_aerolinea_test() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		TAerolinea a = new TAerolinea(-1, "castro", true);
		DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
		int id = da.altaAerolinea(a);
		t.commit();
		assertEquals("No ha devuelto el id correcto", 3, id);
	}
	
	@Test
	public void leerAerolineaPorIdTest(){
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
		TAerolinea ta = da.leerAerolineaPorId(3);
		t.commit();
		assertNotNull("No existe aerolinea", ta);
		System.out.println(ta);
	}
	

	@Test
	public void leer_aerolinea_por_nombre_test() {
		UtilidadesI.esTest();

		DAOAerolinea da = new DAOAerolineaImp();

		assertEquals("la aerolinea con nombre uno es la id 1", 1, da.leerAerolineaPorNombre("uno").getId());
	}
	
	
	@Test
	public void modificar_aerolinea_test() {
		UtilidadesI.esTest();
		DAOAerolinea da = new DAOAerolineaImp();

		TAerolinea aerolinea = new TAerolinea(1, "unoMODIFICADO",true);

		assertTrue("Ha leido mal el fichero", da.modificarAerolinea(aerolinea));
	}

	@Test
	public void baja_aerolinea_test() {
		UtilidadesI.esTest();

		DAOAerolinea da = new DAOAerolineaImp();

		assertTrue("No se ha dado de baja", da.bajaAerolinea(2));
	}

	@Test
	public void consultar_todas_aerolineas_test() {
		UtilidadesI.esTest();

		DAOAerolinea da = new DAOAerolineaImp();

		List<TAerolinea> aerolineas = da.consultarTodasAerolineas();

		File carpeta = new File(UtilidadesI.ruta("aerolinea"));
		File[] lista = carpeta.listFiles();

		assertEquals("tiene que haber tantas aerolineas como ficheros", lista.length, aerolineas.size());
	}
}
