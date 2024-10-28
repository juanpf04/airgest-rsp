package negocio.modelo;

import static org.junit.Assert.*;

import org.junit.Test;

import integracion.UtilidadesI;
import negocio.modeloAerolinea.TModeloAerolinea;

public class SAModeloImpTest {

	private boolean inmodificable = true;
	private int id_inmodificable = 0;

	@Test
	public void alta_modelo_test() {

		SAModelo sm = new SAModeloImp();
		TModelo modelo;

		// Prueba exitosa
		modelo = new TModelo(id_inmodificable, "boeing-900", "EFV-32", inmodificable);
		
		assertEquals("debería darse de alta el modelo", 5, sm.altaModelo(modelo));

		// Fallo por nombre repetido
		modelo = new TModelo(id_inmodificable, "boeing-900", "EFV-33", inmodificable);
		assertEquals("un modelo no activo no se puede modificar", -1, sm.altaModelo(modelo));

		// Reactivar modelo exito
		modelo = new TModelo(id_inmodificable, "boeing-900", "EFV-40", inmodificable);
		assertEquals("no se puede modificar un modelo que no existe",5, sm.altaModelo(modelo));
	}

	@Test
	public void modificar_modelo_test() {

		SAModelo sm = new SAModeloImp();
		TModelo modelo;

		// Prueba exitosa
		modelo = new TModelo(1, "boeing-888", "EFV-30", inmodificable);
		assertTrue("debería modificarse modelo", sm.modificarModelo(modelo));

		// Fallo por modelo no activo
		modelo = new TModelo(4, "boeing-744", "EFV-32", inmodificable);
		assertFalse("un modelo no activo no se puede modificar", sm.modificarModelo(modelo));

		// Fallo id no existente
		modelo = new TModelo(200, "boeing-744", "EFV-32", inmodificable);
		assertFalse("no se puede modificar un modelo que no existe", sm.modificarModelo(modelo));

		// Fallo nombre existente
		modelo = new TModelo(2, "boeing-888", "EFV-32", inmodificable);
		assertFalse("Se ha modificado el modelo, al estar el nombre repetido, no debería dejar",
				sm.modificarModelo(modelo));
	}

	@Test
	public void baja_modelo_test() {

		SAModelo sm = new SAModeloImp();

		//Prueba exitosa
		assertTrue("se deberia poder dar de baja", sm.bajaModelo(2));
		
		//Prueba id inexsistente
		assertFalse("no existe un modelo con id20", sm.bajaModelo(20));
		
		//Prueba modelo ya inactivo
		assertFalse("el modelo ya estaba inactivo", sm.bajaModelo(4));
		
		//Prueba aerolineas activas
		assertFalse("No se puede dar de baja modelo con aerolineas activas", sm.bajaModelo(3));
		
		// Prueba avion activo			//TODO Falta este
		assertFalse("No se puede dar de baja modelo con aviones activos", sm.bajaModelo(1));
	}

	@Test
	public void vincular_modelo_test() {

		SAModelo sm = new SAModeloImp();
		TModeloAerolinea tma = new TModeloAerolinea();
		tma.setIdModelo(1);
		tma.setIdAerolinea(1);

		// prueba exitosa
		assertTrue("No se han podido vincular", sm.vincularModelo(tma));
		
		// prueba ya vinculados
		assertFalse("ya estaban vinculados", sm.vincularModelo(tma));
		
		//uno de los dos esta inactivo
		assertFalse("no se debe poder vincular", sm.vincularModelo(tma));
	}

	@Test
	public void desvincular_modelo_test() {
		UtilidadesI.esTest();

		SAModelo sm = new SAModeloImp();
		TModeloAerolinea tma = new TModeloAerolinea();
		tma.setIdModelo(1);
		tma.setIdAerolinea(1);
		
		sm.vincularModelo(tma);

		// Prueba exitosa
		assertTrue("No se han podido desvincular", sm.desvincularModelo(tma));

		// prueba no vinculados
		assertFalse("ya estan desvinculados", sm.desvincularModelo(tma));
	}
}
