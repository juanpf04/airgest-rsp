package negocio.contrato;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;

import negocio.lineaContrato.TLineaContrato;

public class SAContratoImpTest {

	@Test
	public void modificar_contrato_test() {
		

		SAContrato sc = new SAContratoImp();

		// Prueba exitosa
		TContrato contrato = new TContrato(1, 5, 346.7);
		assertTrue("debería modificarse contrato", sc.modificarContrato(contrato));
		
		// Prueba contrato no existente
		contrato = new TContrato(10, 5, 346.7);
		assertFalse("El contrato si que existe", sc.modificarContrato(contrato));

		// Prueba aerolinea no existente
		contrato = new TContrato(1, 9, 346.7);
		assertFalse("no existe la aerolinea 9", sc.modificarContrato(contrato));

		// Prueba aerolinea no activa
		contrato = new TContrato(1, 4, 456.3);
		assertFalse("la aerolinea 4 no está activa", sc.modificarContrato(contrato));
	}

	@Test
	public void consultar_contrato_por_id_test() {

		SAContrato sc = new SAContratoImp();

		// Prueba exitosa
		TInfoContrato info = sc.consultarContratoPorId(1);
		assertEquals("el toa deberia tener 2 hangares", 2, info.getHangares().size());
		System.out.println(info);

		// Prueba contrato no existente
		info = sc.consultarContratoPorId(5);
		assertNull("no existe el contrato 5", info);
	}
	
	@Test
	public void consultar_todos_contratos_test(){
		SAContrato sc = new SAContratoImp();
		assertEquals("debería haber 4 contratos", 4, sc.consultarTodosContratos().size());
	}

	@Test
	public void consultar_contratos_por_aerolinea_test() {
		

		SAContrato sc = new SAContratoImp();

		// Prueba exitosa
		List<TContrato> contratos = sc.consultarContratosPorAerolinea(1);
		assertEquals("debería tener solo 3 contratos", 3, contratos.size());

		// Prueba no existe aerolinea
		contratos = sc.consultarContratosPorAerolinea(9);
		assertEquals("no existe la aerolinea 9", 0, contratos.size());
	}

	@Test
	public void cerrar_contrato_test() {
		

		SAContrato sc = new SAContratoImp();
		TLineaContrato linea1;
		TLineaContrato linea2;
		TLineaContrato linea3;
		TCarrito carrito;

		// Prueba exitosa
		carrito = new TCarrito(1);

		linea1 = new TLineaContrato();
		linea1.setIdHangar(1);
		linea1.setFechaIni("26-10-2024");
		linea1.setFechaFin("27-10-2024");
		//carrito.anyadirLinea(linea1);

		linea2 = new TLineaContrato();
		linea2.setIdHangar(2);
		linea2.setFechaIni("02-12-2024");
		linea2.setFechaFin("03-12-2024");
		carrito.anyadirLinea(linea2);

		linea3 = new TLineaContrato();
		linea3.setIdHangar(3);
		linea3.setFechaIni("20-01-2024");
		linea3.setFechaFin("25-01-2024");
		carrito.anyadirLinea(linea3);

		assertEquals("el id del contrato debería ser 7", 7, sc.cerrarContrato(carrito));

		// Prueba aerolinea no existente
		/*carrito = new TCarrito(99);
		assertEquals("la aerolinea 99 no existe", -1, sc.cerrarContrato(carrito));

		// Prueba aerolinea no activa
		carrito = new TCarrito(4);
		assertEquals("la aerolinea 55 no está activa", -1, sc.cerrarContrato(carrito));

		// Prueba hangar no existente
		carrito = new TCarrito(1);
		linea1 = new TLineaContrato();
		linea1.setIdHangar(8);
		linea1.setFechaIni("12-04-2024");
		linea1.setFechaFin("12-05-2024");
		carrito.anyadirLinea(linea1);
		assertEquals("el hangar 8 no existe", -1, sc.cerrarContrato(carrito));

		// Prueba hangar no activo
		carrito = new TCarrito(1);
		linea1 = new TLineaContrato();
		linea1.setIdHangar(1);
		linea1.setFechaIni("12-04-2024");
		linea1.setFechaFin("12-05-2024");
		carrito.anyadirLinea(linea1);
		assertEquals("el hangar 1 no esta activo", -1, sc.cerrarContrato(carrito));

		// Prueba hangar repetido en el carrito
		carrito = new TCarrito(1);
		linea1 = new TLineaContrato();
		linea1.setIdHangar(2);
		linea1.setFechaIni("12-06-2024");
		linea1.setFechaFin("12-07-2024");
		carrito.anyadirLinea(linea1);

		linea2 = new TLineaContrato();
		linea2.setIdHangar(2);
		linea2.setFechaIni("12-04-2024");
		linea2.setFechaFin("12-05-2024");
		carrito.anyadirLinea(linea2);
		assertEquals("no hay hangares repetidos", -1, sc.cerrarContrato(carrito));

		// Prueba comprobar fechas
		carrito = new TCarrito(1);

		linea1 = new TLineaContrato();
		linea1.setIdHangar(1);
		linea1.setFechaIni("17-01-2029");
		linea1.setFechaFin("19-01-2029");
		carrito.anyadirLinea(linea1);

		linea2 = new TLineaContrato();
		linea2.setIdHangar(2);
		linea2.setFechaIni("17-01-2029");
		linea2.setFechaFin("27-01-2029");
		carrito.anyadirLinea(linea2);

		linea3 = new TLineaContrato();
		linea3.setIdHangar(3);
		linea3.setFechaIni("02-01-2024");
		linea3.setFechaFin("08-01-2024");
		carrito.anyadirLinea(linea3);

		assertEquals("el hangar 3 no se puede contratar", -1, sc.cerrarContrato(carrito));

		// Prueba fecha inicio posterior a fecha fin
		carrito = new TCarrito(1);

		linea1 = new TLineaContrato();
		linea1.setIdHangar(1);
		linea1.setFechaIni("17-01-2029");
		linea1.setFechaFin("15-01-2029");
		carrito.anyadirLinea(linea1);

		assertEquals("fecha inicio posterior a la de fin", -1, sc.cerrarContrato(carrito));*/
	}

	@Test
	public void modificar_linea_contrato_test() {

		SAContrato sc = new SAContratoImp();

		// Prueba exitosa
		String fecha_ini = "27-10-2024";
		String fecha_fin = "30-10-2024";

		TLineaContrato linea = new TLineaContrato(1, 1, fecha_ini, fecha_fin, 0);
		assertTrue("deberia modificarse linea", sc.modificarLineaContrato(linea));

		// Hangar no existente
		linea = new TLineaContrato(1, 12, fecha_ini, fecha_fin, 0);
		assertFalse("no existe el hangar 12", sc.modificarLineaContrato(linea));

		// Hangar no activo
		linea = new TLineaContrato(1, 1, fecha_ini, fecha_fin, 0);
		assertTrue("el hangar 1 esta inactivo", sc.modificarLineaContrato(linea));

		// Fechas ocupadas
		fecha_ini = "20-01-2003";
		fecha_fin = "25-01-2003";
		linea = new TLineaContrato(1, 1, fecha_ini, fecha_fin, 0);
		assertFalse("rango de fechas no valido", sc.modificarLineaContrato(linea));
		
		// Linea contrato no existente
		linea = new TLineaContrato(10, 10, fecha_ini, fecha_fin, 0);
		assertFalse("no existe la linea de contrato", sc.modificarLineaContrato(linea));

		
		// fecha de fin antes que la de inicio
		fecha_ini = "20-01-2003";
		fecha_fin = "18-01-2003";
		linea = new TLineaContrato(1, 1, fecha_ini, fecha_fin, 0);
		assertFalse("la fecha de fin va antes que la de inicio", sc.modificarLineaContrato(linea));
	}
	
	@Test
	public void diferencia_fecha_test(){
		String f1 = "12-12-2024";
		String f2 = "16-12-2024";
		
		assertEquals("Diferencia mal calculada", 4, SAContratoImp.diferencia_fechas(f1, f2));
	}
	
	@Test
	public void is_between_test(){
		String fecha = "11-03-2024";
		String inicio = "11-03-2024";
		String fin = "15-03-2024";
		
		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate lFecha = LocalDate.parse(fecha, d);
		LocalDate lInicio = LocalDate.parse(inicio, d);
		LocalDate lFin = LocalDate.parse(fin, d);
		
		assertTrue("La fecha no está entre medias de las dos", SAContratoImp.isBetween(lFecha, lInicio, lFin));
	}
}
