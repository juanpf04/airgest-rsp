package presentacion.factoria;

import presentacion.modelo.*;
import presentacion.avion.*;
import presentacion.aerolinea.*;
import presentacion.hangar.*;
import presentacion.personal.*;
import presentacion.contrato.*;
import presentacion.controlador.Evento;
import presentacion.Observador;
import presentacion.VistaPrincipal;

public class FactoriaVistasImp extends FactoriaVistas {

	@Override
	public Observador crearVista(Evento evento) {
		Observador vista = null;

		switch (evento) {
		case VISTA_PRINCIPAL:
			vista = new VistaPrincipal();
			break;

		case VISTA_ALTA_MODELO:
			vista = new VistaAltaModelo();
			break;
		case VISTA_BAJA_MODELO:
			vista = new VistaBajaModelo();
			break;
		case VISTA_CONSULTAR_MODELO_POR_ID:
			vista = new VistaConsultarModeloPorId();
			break;
		case VISTA_MODIFICAR_MODELO:
			vista = new VistaModificarModelo();
			break;
		case VISTA_VINCULAR_MODELO:
			vista = new VistaVincularModelo();
			break;
		case VISTA_DESVINCULAR_MODELO:
			vista = new VistaDesvincularModelo();
			break;

		case VISTA_AVION:
			vista = new VistaAvion();
			break;
		case VISTA_ALTA_AVION:
			vista = new VistaAltaAvion();
			break;
		case VISTA_BAJA_AVION:
			vista = new VistaBajaAvion();
			break;
		case VISTA_CONSULTAR_AVION_POR_ID:
			vista = new VistaConsultarAvionPorId();
			break;
		case VISTA_CONSULTAR_TODOS_AVIONES:
			vista = new VistaConsultarTodosAviones();
			break;
		case VISTA_MODIFICAR_AVION:
			vista = new VistaModificarAvion();
			break;
		case VISTA_MOSTRAR_AVIONES_POR_MODELO:
			vista = new VistaMostrarAvionesPorModelo();
			break;
		case VISTA_MOSTRAR_AVIONES_POR_AEROLINEA:
			vista = new VistaMostrarAvionesPorAerolínea();
			break;
		case VISTA_MOSTRAR_AVIONES_POR_HANGAR:
			vista = new VistaMostrarAvionesPorHangar();
			break;

		case VISTA_AEROLINEA:
			vista = new VistaAerolinea();
			break;
		case VISTA_ALTA_AEROLINEA:
			vista = new VistaAltaAerolinea();
			break;
		case VISTA_BAJA_AEROLINEA:
			vista = new VistaBajaAerolinea();
			break;
		case VISTA_CONSULTAR_AEROLINEA_POR_ID:
			vista = new VistaConsultarAerolineaPorId();
			break;
		case VISTA_MODIFICAR_AEROLINEA:
			vista = new VistaModificarAerolinea();
			break;

		case VISTA_CONTRATO:
			vista = new VistaContrato();
			break;
		case VISTA_CARRITO:
			vista = new VistaCarrito();
			break;
		case VISTA_ABRIR_CONTRATO:
			vista = new VistaAbrirContrato();
			break;
		case VISTA_ANYADIR_HANGAR_AL_CONTRATO:
			vista = new VistaAñadirHangarAlContrato();
			break;
		case VISTA_ELIMINAR_HANGAR_DEL_CONTRATO:
			vista = new VistaEliminarHangarDelContrato();
			break;
		case VISTA_CONSULTAR_CONTRATO_POR_ID:
			vista = new VistaConsultarContratoPorId();
			break;
		case VISTA_CONSULTAR_TODOS_CONTRATOS:
			vista = new VistaConsultarTodosContratos();
			break;
		case VISTA_MODIFICAR_CONTRATO:
			vista = new VistaModificarContrato();
			break;
		case VISTA_MODIFICAR_LINEA_CONTRATO:
			vista = new VistaModificarLíneaContrato();
			break;
		case VISTA_MOSTRAR_CONTRATOS_POR_AEROLINEA:
			vista = new VistaMostrarContratosPorAerolínea();
			break;

		case VISTA_HANGAR:
			vista = new VistaHangar();
			break;
		case VISTA_ALTA_HANGAR:
			vista = new VistaAltaHangar();
			break;
		case VISTA_BAJA_HANGAR:
			vista = new VistaBajaHangar();
			break;
		case VISTA_CONSULTAR_HANGAR_POR_ID:
			vista = new VistaConsultarHangarPorId();
			break;
		case VISTA_MODIFICAR_HANGAR:
			vista = new VistaModificarHangar();
			break;

		case VISTA_PERSONAL:
			vista = new VistaPersonal();
			break;
		case VISTA_ALTA_PERSONAL:
			vista = new VistaAltaPersonal();
			break;
		case VISTA_BAJA_PERSONAL:
			vista = new VistaBajaPersonal();
			break;
		case VISTA_VINCULAR_PERSONAL:
			vista = new VistaVincularPersonal();
			break;
		case VISTA_DESVINCULAR_PERSONAL:
			vista = new VistaDesvincularPersonal();
			break;
		case VISTA_CONSULTAR_PERSONAL_POR_ID:
			vista = new VistaConsultarPersonalPorId();
			break;
		case VISTA_MODIFICAR_PERSONAL:
			vista = new VistaModificarPersonal();
			break;

		default:
			break;
		}

		return vista;
	}

}