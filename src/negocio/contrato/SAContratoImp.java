package negocio.contrato;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import integracion.aerolinea.DAOAerolinea;
import integracion.contrato.DAOContrato;
import integracion.factoria.FactoriaIntegracion;
import integracion.hangar.DAOHangar;
import integracion.lineaContrato.DAOLineaContrato;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.UtilidadesN;
import negocio.aerolinea.TAerolinea;
import negocio.hangar.THangar;
import negocio.lineaContrato.TLineaContrato;

public class SAContratoImp implements SAContrato {

	public TCarrito abrirContrato(int id_aerolinea) {
		return new TCarrito(id_aerolinea);
	}

	public int cerrarContrato(TCarrito tCarrito) {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		
		DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
		TAerolinea aerolinea = da.leerAerolineaPorId(tCarrito.getIdAerolinea());

		// Comprobamos que la aerolinea exista y esté activa
		if (aerolinea != null && aerolinea.getActivo()) {
			if (tCarrito.getLineasContrato().isEmpty()) {
				t.rollback();
				return -1;
			}

			// Comprobamos que no haya hangares repetidos
			for (TLineaContrato linea : tCarrito.getLineasContrato()) {
				for (TLineaContrato l : tCarrito.getLineasContrato()) {
					if (l != linea && l.getIdHangar() == linea.getIdHangar()) {
						t.rollback();
						return -1;
					}
				}
			}
			
			// Dar de alta el contrato con precio 0
			DAOContrato dc = FactoriaIntegracion.getInstance().crearDAOContrato();
			int id_contrato = dc.altaContrato(tCarrito.getContrato());
			double precio_total = 0;
			
			// Recorrer las lineas de contrato del carrito e ir comprobando existencia, disponibilidad, etc (en el mismo bucle).
			DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
			DAOLineaContrato dl = FactoriaIntegracion.getInstance().crearDAOLineaContrato();
			
			for (TLineaContrato linea : tCarrito.getLineasContrato()){
				THangar hangar = dh.leerHangarPorId(linea.getIdHangar());
				
				// Comprobamos que el hangar exista, esté activo y que la fecha de inicio sea anterior a la de fin
				if (hangar == null || !hangar.getActivo() || toLocalDate(linea.getFechaIni()).isAfter(toLocalDate(linea.getFechaFin()))) {
					t.rollback();
					return -1;
				}
				
				// Bucle para comprobar disponibilidad
				List<TLineaContrato> lineasPorHangar = dl.consultarLineasPorHangar(linea.getIdHangar());

				for (TLineaContrato l : lineasPorHangar) {
					if (isBetween(toLocalDate(linea.getFechaIni()), toLocalDate(l.getFechaIni()), toLocalDate(l.getFechaFin()))
							|| isBetween(toLocalDate(linea.getFechaFin()), toLocalDate(l.getFechaIni()), toLocalDate(l.getFechaFin()))
							|| isBetween(toLocalDate(l.getFechaIni()), toLocalDate(linea.getFechaIni()), toLocalDate(linea.getFechaFin()))
							|| isBetween(toLocalDate(l.getFechaFin()), toLocalDate(linea.getFechaIni()), toLocalDate(linea.getFechaFin()))) {
						t.rollback();
						return -1;
					}
				}
				
				long dias_diferencia = diferencia_fechas(linea.getFechaIni(), linea.getFechaFin());
				linea.setPrecio(dias_diferencia * hangar.getCosteDia());
				linea.setIdContrato(id_contrato);
				precio_total += dias_diferencia * hangar.getCosteDia();
				dl.altaLineaContrato(linea);
				// Revisar calculo precio total
			}
			
			TContrato contrato = tCarrito.getContrato();
			contrato.setPrecio(precio_total);
			contrato.setId(id_contrato);
			boolean ok = dc.modificarContrato(contrato);
			
			if (ok){
				t.commit();
				return id_contrato;
			} else{
				t.rollback();
				return -1;
			}
		}

		return -1;
	}

	public static boolean isBetween(LocalDate fecha, LocalDate inicio, LocalDate fin) {
		return (fecha.isAfter(inicio) && fecha.isBefore(fin)) || (fecha.isEqual(inicio) || fecha.isEqual(fin));
	}

	public TInfoContrato consultarContratoPorId(int id) {
		TInfoContrato info = null;
		if (UtilidadesN.comprobarId(id)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOContrato dc = FactoriaIntegracion.getInstance().crearDAOContrato();

			TContrato contrato = dc.consultarContratoPorId(id);

			if (contrato != null) {
				DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
				TAerolinea aerolinea = da.leerAerolineaPorId(contrato.getIdAerolinea());

				DAOLineaContrato dl = FactoriaIntegracion.getInstance().crearDAOLineaContrato();
				List<TLineaContrato> lineas = dl.consultarLineasPorContrato(id);

				DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
				HashMap<Integer, THangar> hangares = new HashMap<>();

				for (TLineaContrato linea : lineas) {
					THangar hangar = dh.leerHangarPorId(linea.getIdHangar());
					hangares.put(hangar.getId(), hangar);
				}

				info = new TInfoContrato(contrato, aerolinea, lineas, hangares);
				
				t.commit();
				
			} else{
				t.rollback();
			}
		}

		return info;
	}

	public List<TContrato> consultarTodosContratos() {
		Transaction t = TransactionManager.getInstance().nuevaTransaccion();
		t.start();
		DAOContrato dc = FactoriaIntegracion.getInstance().crearDAOContrato();
		List<TContrato> lista = dc.consultarTodosContratos();
		t.commit();
		return lista;
	}

	public boolean modificarContrato(TContrato tContrato) {
		boolean ok = false;
		if (ValidadorContrato.comprobarDatos(tContrato)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOContrato dc = FactoriaIntegracion.getInstance().crearDAOContrato();

			int id = tContrato.getId();

			TContrato leido = dc.consultarContratoPorId(id);

			if (leido != null) {
				DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
				TAerolinea aerolinea = da.leerAerolineaPorId(tContrato.getIdAerolinea());
				if (aerolinea != null && aerolinea.getActivo()) {
					ok = dc.modificarContrato(tContrato);
					t.commit();
				} else{
					t.rollback();
				}
			} else{
				t.rollback();
			}
		}

		return ok;
	}

	public boolean modificarLineaContrato(TLineaContrato linea) {
		boolean ok = false;
		if (ValidadorLineaContrato.comprobarDatos(linea)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOLineaContrato dl = FactoriaIntegracion.getInstance().crearDAOLineaContrato();

			// Comprobamos la existencia de la linea de contrato
			if (dl.consultarLineaContrato(linea.getIdContrato(), linea.getIdHangar()) != null) {

				// Comprobamos que la fecha de inicio vaya antes que la de fin
				if (!toLocalDate(linea.getFechaIni()).isAfter(toLocalDate(linea.getFechaFin()))) {
					List<TLineaContrato> lineasPorHangar = dl.consultarLineasPorHangar(linea.getIdHangar());

					// Bucle para ver si el hangar esta ocupado en esas fechas
					for (TLineaContrato l : lineasPorHangar) {
						if (l.getIdHangar() != linea.getIdHangar() || l.getIdContrato() != linea.getIdContrato()) {
							if (isBetween(toLocalDate(linea.getFechaIni()), toLocalDate(l.getFechaIni()), toLocalDate(l.getFechaFin()))
									|| isBetween(toLocalDate(linea.getFechaFin()), toLocalDate(l.getFechaIni()), toLocalDate(l.getFechaFin()))
									|| isBetween(toLocalDate(l.getFechaIni()), toLocalDate(linea.getFechaIni()), toLocalDate(linea.getFechaFin()))
									|| isBetween(toLocalDate(l.getFechaFin()), toLocalDate(linea.getFechaIni()), toLocalDate(linea.getFechaFin()))) {
								t.rollback();
								return false;
							}
						}
					}
					
					DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
					THangar hangar = dh.leerHangarPorId(linea.getIdHangar());
					
					if (hangar != null){
						if (!hangar.getActivo()){
							//Reactivamos hangar 
							hangar.setActivo(true);
							dh.modificarHangar(hangar);
						}
						
						long diasDiferencia = diferencia_fechas(linea.getFechaIni(), linea.getFechaFin());
						linea.setPrecio(diasDiferencia * hangar.getCosteDia());
						dl.modificarLineaContrato(linea);
						
						List<TLineaContrato> lineas = dl.consultarLineasPorContrato(linea.getIdContrato());

						double precioContrato = 0;

						for (TLineaContrato l : lineas) {
							precioContrato += l.getPrecio();
						}

						DAOContrato dc = FactoriaIntegracion.getInstance().crearDAOContrato();
						TContrato contrato = dc.consultarContratoPorId(linea.getIdContrato());
						contrato.setPrecio(precioContrato);
						ok = dc.modificarContrato(contrato);
						t.commit();

					} else {
						t.rollback();
					}
				} else{
					t.rollback();
				}
			} else {
				t.rollback();
			}
		}
		return ok;
	}

	public List<TContrato> consultarContratosPorAerolinea(int id_aerolinea) {
		List<TContrato> contratos = new ArrayList<>();
		if (UtilidadesN.comprobarId(id_aerolinea)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();

			TAerolinea aerolinea = da.leerAerolineaPorId(id_aerolinea);

			if (aerolinea != null) {
				DAOContrato dc = FactoriaIntegracion.getInstance().crearDAOContrato();
				contratos = dc.consultarContratosPorAerolinea(id_aerolinea);
				t.commit();
			} else{
				t.rollback();
			}
		}

		return contratos;
	}

	public static long diferencia_fechas(String f1, String f2) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date d1 = df.parse(f1);
            Date d2 = df.parse(f2);

            return Math.abs(d1.getTime()- d2.getTime()) / (1000*3600*24);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Usa el formato dd-MM-yyyy");
        }
    }
	
	private LocalDate toLocalDate(String fecha){
		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		return LocalDate.parse(fecha, d);
	}

	@Override
	public List<TLineaContrato> consultarContratoPorAerolinea(int id_aerolinea, double precio, int dias) {
		List<TLineaContrato> contratos = new ArrayList<>();
		if (ValidadorLineaContrato.comprobarQuery(id_aerolinea, precio, dias)) {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			
			DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();

			TAerolinea aerolinea = da.leerAerolineaPorId(id_aerolinea);

			if (aerolinea != null) {
				DAOLineaContrato dlc = FactoriaIntegracion.getInstance().crearDAOLineaContrato();
				contratos = dlc.consultarContratoPorAerolinea(id_aerolinea, precio, dias);
				t.commit();
			} else{
				t.rollback();
			}
		}

		return contratos;
	}
}