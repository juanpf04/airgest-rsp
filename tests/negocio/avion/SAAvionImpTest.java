package negocio.avion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import integracion.UtilidadesI;
import negocio.avion.SAAvion;
import negocio.avion.SAAvionImp;
import negocio.avion.TAvion;
import negocio.avion.TAPrivado;
import negocio.avion.TAComercial;


public class SAAvionImpTest {

	private boolean inmodificable = true;
	private int id_inmodificable = 0;
	
	@Test
	public void alta_avion_test() {

		SAAvion sa = new SAAvionImp();

		// Prueba exitosa comercial
		TAvion avion = new TAComercial(id_inmodificable, 5, "06-12-2004", "nombrePrueba", "EC-1", 
				inmodificable, 8, 1, 1, "Valeria");
		int id = sa.altaAvion(avion);

		assertEquals("debería darse de alta el avion comercial", 1, id);
				
		//Prueba exitosa privada
		avion = new TAPrivado(id_inmodificable, 5, "06-12-2004", "nombrePrueba2", "EC-2", 
				inmodificable, 8, 1, 1, "Patricio", 7);
		assertEquals("debería darse de alta el avion privado", 2, sa.altaAvion(avion));
				
		// Fallo por matricula repetida y avion activo
		avion = new TAPrivado(id_inmodificable, 5, "06-12-2004", "nombrePrueba2", "EC-2", 
				inmodificable, 8, 1, 1, "Patricio", 7);
		assertEquals("un avion activo no se puede modificar", -1, sa.altaAvion(avion));
		
		// Reactivar avion exito
		avion = new TAComercial(id_inmodificable, 5, "06-12-2004", "pruebaReactivar", "EC-3", 
				inmodificable, 1, 1, 1, "Amazon");
		//assertEquals("deberia reactivarse el avion ", 2, sa.altaAvion(avion));
		
	}

	@Test
	public void modificar_avion_test() {
		UtilidadesI.esTest();
		
		SAAvion sa = new SAAvionImp();
		
		// Prueba comercial exitosa
		TAvion avion = new TAComercial(3, 10, "28-01-2001", "nombreModificar1", "EC-1", 
				inmodificable, 1, 1, 1, "NuevaEmpresa");
		assertTrue("Debería modificarse el avion comercial", sa.modificarAvion(avion));
		
		// Prueba comercial exitosa
		avion = new TAPrivado(4, 5, "02-02-2002", "nombreModificar2", "EC-2", 
				inmodificable, 2, 2, 2, "Pepe", 12);
		assertTrue("Debería modificarse el avion privado", sa.modificarAvion(avion));
		
		// Fallo por avion inactivo
		avion = new TAComercial(1, 5, "03-03-2003", "pruebaModificar3", "EC-3", 
				inmodificable, 3, 3, 3, "NuevaEmpresa");
		assertFalse("Un avion inactivo no se puede modificar", sa.modificarAvion(avion));
		
		// Fallo id no existente
		avion = new TAPrivado(100, 24, "04-04-2004", "nombreModificar4", "EC-4", 
				inmodificable, 4, 4, 4, "Manolo", 14);
		assertFalse("No se puede modificar un avion inexiste", sa.modificarAvion(avion));
		
		// Fallo matricula existente
		avion = new TAComercial(4, 4, "05-05-2005", "nombreModificar2", "EC-1", 
				inmodificable, 5, 5, 5, "NuevaEmpresa");
		assertFalse("No se puede modificar un avion con una matricula ya existente",
				sa.modificarAvion(avion));
	}
	
	@Test
	public void baja_avion_test() {
		UtilidadesI.esTest();

		SAAvion sa = new SAAvionImp();
		
		//Prueba comercial exitosa
		assertTrue("Deberia darse de baja el avion comercial", sa.bajaAvion(3));
		
		//Prueba privado exitosa
		assertTrue("Deberia darse de baja el avion privado", sa.bajaAvion(4));
		
		//Prueba baja avion inactivo fallo
		assertFalse("El avion ya estaba inactivo", sa.bajaAvion(3));

	}

}
