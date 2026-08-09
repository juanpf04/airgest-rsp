package negocio.aerolinea;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SAAerolineaImpTest {

	private boolean inmodificable = true;
	private int id_inmodificable = 0;

	@Test
	public void alta_aerolinea_test() {
		SAAerolinea sa = new SAAerolineaImp();

		// Prueba exitosa
		TAerolinea aerolinea = new TAerolinea(id_inmodificable, "CUATRO", inmodificable);
		assertEquals("debería darse de alta la aerolinea", 4, sa.altaAerolinea(aerolinea));

		
		// Fallo por nombre repetido
		aerolinea = new TAerolinea(id_inmodificable, "CUATRO", inmodificable);
		assertEquals("una aerolinea no se puede dar de alta con nombre repetido", -1, sa.altaAerolinea(aerolinea));
		
		// Reactivar aerolinea exito
		aerolinea = new TAerolinea(id_inmodificable, "pepito", inmodificable);
		assertEquals("reactiva una existente ", 1, sa.altaAerolinea(aerolinea));

		// despues de esto estan TODAS activas
	}

	@Test
	public void modificar_aerolinea_test() {
		SAAerolinea sa = new SAAerolineaImp();
		TAerolinea aerolinea;
		
		// Prueba exitosa
		aerolinea = new TAerolinea(3, "tres", inmodificable);
		assertTrue("debería modificarse aerolinea", sa.modificarAerolinea(aerolinea));

		
		// Fallo por aerolinea no activa
		aerolinea = new TAerolinea(2, "mondongo", inmodificable);
		assertTrue("una aerolinea no activa no se puede modificar", sa.modificarAerolinea(aerolinea));

		// Fallo id no existente
		aerolinea = new TAerolinea(200, "2cientos", inmodificable);
		assertFalse("no se puede modificar una aerolinea que no existe", sa.modificarAerolinea(aerolinea));

		// Fallo nombre existente
		aerolinea = new TAerolinea(1, "javi", inmodificable);
		assertFalse("Se ha modificado la aerolinea, al estar el nombre repetido, no debería dejar",
				sa.modificarAerolinea(aerolinea));
	}

	@Test
	public void baja_aerolinea_test() {
		SAAerolinea sa = new SAAerolineaImp();

		// Prueba exitosa
		assertTrue("se deberia poder dar de baja", sa.bajaAerolinea(7));
		
		// Prueba id inexistente
		assertFalse("no existe una aerolinea con id20", sa.bajaAerolinea(20));

		// Prueba aerolinea ya inactiva
		assertFalse("la aerolinea ya estaba inactivo", sa.bajaAerolinea(4));
		
		// Prueba aerolinea tiene modelos vinculados
		assertFalse("la aerolinea no tiene modelos vinculados", sa.bajaAerolinea(1));
		
		// Prueba avion activo
		assertFalse("No se puede dar de baja aerolinea con aviones activos", sa.bajaAerolinea(1));

		// Prueba contrato activo
		assertFalse("No se puede dar de baja aerolinea con contratos activos", sa.bajaAerolinea(5));
	}
	
	@Test
	public void consultar_aerolinea_por_id_test(){
		SAAerolinea sa = new SAAerolineaImp();
		TAerolinea ta;
		
		//Prueba exitosa
		ta = sa.consultarAerolineaPorId(1);
		assertEquals("No se ha encontrado aerolinea", 1, ta.getId());
		
		//Prueba id no existente
		ta = sa.consultarAerolineaPorId(10);
		assertNull("La aerolinea si existe", ta);
	}
	
	@Test
	public void consultar_todas_aerolineas_test(){
		SAAerolinea sa = new SAAerolineaImp();
		List<TAerolinea> list = sa.consultarTodasAerolineas();
		
		assertEquals("Fallo terrible", 4, list.size());
	}
	
	@Test
	public void consultar_aerolineas_por_modelo_test(){
		SAAerolinea sa = new SAAerolineaImp();
		List<TAerolinea> list = sa.consultarAerolineasPorModelo(1);
		assertEquals("Fallo terrible", 2, list.size());
		
	}
}
