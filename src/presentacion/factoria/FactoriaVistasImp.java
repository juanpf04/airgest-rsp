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

		// MODELO
		case VISTA_MODELO:
			vista = new VistaModelo();
			break;
		case VISTA_ALTA_MODELO:
			vista = new VistaAltaModelo();
			break;
		case VISTA_EXITO_ALTA_MODELO:
			vista = new VistaExitoAltaModelo();
			break;
		case VISTA_FALLO_ALTA_MODELO:
			vista = new VistaFalloAltaModelo();
			break;
		case VISTA_BAJA_MODELO:
			vista = new VistaBajaModelo();
			break;
		case VISTA_EXITO_BAJA_MODELO:
			vista = new VistaExitoBajaModelo();
			break;
		case VISTA_FALLO_BAJA_MODELO:
			vista = new VistaFalloBajaModelo();
			break;
		case VISTA_CONSULTAR_MODELO_POR_ID:
			vista = new VistaConsultarModeloPorId();
			break;
		case VISTA_RESULTADO_CONSULTAR_MODELO_POR_ID:
			vista = new VistaResultadoConsultarModeloPorId();
			break;
		case VISTA_RESULTADO_CONSULTAR_TODOS_MODELOS:
			vista = new VistaResultadoConsultarTodosModelos();
			break;
		case VISTA_MODIFICAR_MODELO:
			vista = new VistaModificarModelo();
			break;
		case VISTA_EXITO_MODIFICAR_MODELO:
			vista = new VistaExitoModificarModelo();
			break;
		case VISTA_FALLO_MODIFICAR_MODELO:
			vista = new VistaFalloModificarModelo();
			break;
		case VISTA_VINCULAR_MODELO:
			vista = new VistaVincularModelo();
			break;
		case VISTA_EXITO_VINCULAR_MODELO:
			vista = new VistaExitoVincularModelo();
			break;
		case VISTA_FALLO_VINCULAR_MODELO:
			vista = new VistaFalloVincularModelo();
			break;
		case VISTA_DESVINCULAR_MODELO:
			vista = new VistaDesvincularModelo();
			break;
		case VISTA_EXITO_DESVINCULAR_MODELO:
			vista = new VistaExitoDesvincularModelo();
			break;
		case VISTA_FALLO_DESVINCULAR_MODELO:
			vista = new VistaFalloDesvincularModelo();
			break;
		case VISTA_CONSULTAR_MODELOS_POR_AEROLINEA:
			vista = new VistaConsultarModelosPorAerolinea();
			break;
		case VISTA_RESULTADO_CONSULTAR_MODELOS_POR_AEROLINEA:
			vista = new VistaResultadoConsultarModelosPorAerolinea();
			break;
			
		// AVI�N
		case VISTA_AVION:
			vista = new VistaAvion();
			break;
		case VISTA_ALTA_AVION:
			vista = new VistaAltaAvion();
			break;
		case VISTA_EXITO_ALTA_AVION:
			vista = new VistaExitoAltaAvion();
			break;
		case VISTA_FALLO_ALTA_AVION:
			vista = new VistaFalloAltaAvion();
			break;
		case VISTA_BAJA_AVION:
			vista = new VistaBajaAvion();
			break;
		case VISTA_EXITO_BAJA_AVION:
			vista = new VistaExitoBajaAvion();
			break;
		case VISTA_FALLO_BAJA_AVION:
			vista = new VistaFalloBajaAvion();
			break;
		case VISTA_CONSULTAR_AVION_POR_ID:
			vista = new VistaConsultarAvionPorId();
			break;
		case VISTA_RESULTADO_CONSULTAR_AVION_POR_ID:
			vista = new VistaResultadoConsultarAvionPorId();
			break;
		case VISTA_RESULTADO_CONSULTAR_TODOS_AVIONES:
			vista = new VistaResultadoConsultarTodosAviones();
			break;
		case VISTA_MODIFICAR_AVION:
			vista = new VistaModificarAvion();
			break;
		case VISTA_EXITO_MODIFICAR_AVION:
			vista = new VistaExitoModificarAvion();
			break;
		case VISTA_FALLO_MODIFICAR_AVION:
			vista = new VistaFalloModificarAvion();
			break;
		case VISTA_CONSULTAR_AVIONES_POR_MODELO:
			vista = new VistaConsultarAvionesPorModelo();
			break;
		case VISTA_RESULTADO_CONSULTAR_AVIONES_POR_MODELO:
			vista = new VistaResultadoConsultarAvionesPorModelo();
			break;
		case VISTA_CONSULTAR_AVIONES_POR_AEROLINEA:
			vista = new VistaConsultarAvionesPorAerolinea();
			break;
		case VISTA_RESULTADO_CONSULTAR_AVIONES_POR_AEROLINEA:
			vista = new VistaResultadoConsultarAvionesPorAerolinea();
			break;
		case VISTA_CONSULTAR_AVIONES_POR_HANGAR:
			vista = new VistaConsultarAvionesPorHangar();
			break;
		case VISTA_RESULTADO_CONSULTAR_AVIONES_POR_HANGAR:
			vista = new VistaResultadoConsultarAvionesPorHangar();
			break;
		case VISTA_CONSULTAR_AVIONES_DE_AEROLINEA_POR_HANGAR:
			vista = new VistaConsultarAvionesDeAerolineaPorHangar();
			break;
		case VISTA_RESULTADO_CONSULTAR_AVIONES_DE_AEROLINEA_POR_HANGAR:
			vista = new VistaResultadoConsultarAvionesDeAerolineaPorHangar();
			break;
		
		// AEROL�NEA
		case VISTA_AEROLINEA:
			vista = new VistaAerolinea();
			break;
		case VISTA_ALTA_AEROLINEA:
			vista = new VistaAltaAerolinea();
			break;
		case VISTA_EXITO_ALTA_AEROLINEA:
			vista = new VistaExitoAltaAerolinea();
			break;
		case VISTA_FALLO_ALTA_AEROLINEA:
			vista = new VistaFalloAltaAerolinea();
			break;
		case VISTA_BAJA_AEROLINEA:
			vista = new VistaBajaAerolinea();
			break;
		case VISTA_EXITO_BAJA_AEROLINEA:
			vista = new VistaExitoBajaAerolinea();
			break;
		case VISTA_FALLO_BAJA_AEROLINEA:
			vista = new VistaFalloBajaAerolinea();
			break;
		case VISTA_CONSULTAR_AEROLINEA_POR_ID:
			vista = new VistaConsultarAerolineaPorId();
			break;
		case VISTA_RESULTADO_CONSULTAR_AEROLINEA_POR_ID:
			vista = new VistaResultadoConsultarAerolineaPorId();
			break;
		case VISTA_RESULTADO_CONSULTAR_TODAS_AEROLINEAS:
			vista = new VistaResultadoConsultarTodasAerolineas();
			break;
		case VISTA_MODIFICAR_AEROLINEA:
			vista = new VistaModificarAerolinea();
			break;
		case VISTA_MODIFICAR_AEROLINEA_ID:
			vista = new VistaModificarIdAerolinea();
			break;
		case VISTA_EXITO_MODIFICAR_AEROLINEA:
			vista = new VistaExitoModificarAerolinea();
			break;
		case VISTA_FALLO_MODIFICAR_AEROLINEA:
			vista = new VistaFalloModificarAerolinea();
			break;
		case VISTA_CONSULTAR_AEROLINEAS_POR_MODELO:
			vista = new VistaConsultarAerolineasPorModelo();
			break;
		case VISTA_RESULTADO_CONSULTAR_AEROLINEAS_POR_MODELO:
			vista = new VistaResultadoConsultarAerolineasPorModelo();
			break;

		// CONTRATO
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
			vista = new VistaAnyadirHangar();
			break;
		case VISTA_ELIMINAR_HANGAR_AL_CONTRATO:
			vista = new VistaEliminarHangar();
			break;
		case VISTA_EXITO_CERRAR_CONTRATO:
			vista = new VistaExitoCerrarContrato();
			break;
		case VISTA_FALLO_CERRAR_CONTRATO:
			vista = new VistaFalloCerrarContrato();
			break;
		case VISTA_CONSULTAR_CONTRATO_POR_ID:
			vista = new VistaConsultarContratoPorId();
			break;
		case VISTA_RESULTADO_CONSULTAR_CONTRATO_POR_ID:
			vista = new VistaResultadoConsultarContratoPorId();
			break;
		case VISTA_RESULTADO_CONSULTAR_TODOS_CONTRATOS:
			vista = new VistaResultadoConsultarTodosContratos();
			break;
		case VISTA_MODIFICAR_CONTRATO:
			vista = new VistaModificarContrato();
			break;
		case VISTA_EXITO_MODIFICAR_CONTRATO:
			vista = new VistaExitoModificarContrato();
			break;
		case VISTA_FALLO_MODIFICAR_CONTRATO:
			vista = new VistaFalloModificarContrato();
			break;
		case VISTA_MODIFICAR_LINEA_CONTRATO:
			vista = new VistaModificarLineaContrato();
			break;
		case VISTA_EXITO_MODIFICAR_LINEA_CONTRATO:
			vista = new VistaExitoModificarLineaContrato();
			break;
		case VISTA_FALLO_MODIFICAR_LINEA_CONTRATO:
			vista = new VistaFalloModificarLineaContrato();
			break;
		case VISTA_CONSULTAR_CONTRATOS_POR_AEROLINEA:
			vista = new VistaConsultarContratosPorAerolinea();
			break;
		case VISTA_RESULTADO_CONSULTAR_CONTRATOS_POR_AEROLINEA:
			vista = new VistaResultadoConsultarContratosPorAerolinea();
			break;
		case VISTA_CONSULTAR_CONTRATOS_POR_AEROLINEA_PRECIO_Y_DURACION:
			vista = new VistaConsultarContratoPorAerolineaPyD();
			break;
		case VISTA_RESULTADO_CONSULTAR_CONTRATOS_POR_AEROLINEA_PRECIO_Y_DURACION:
			vista = new VistaResultadoConsultarConstratosPorAerolineaPyD(); 
			break;

		// HANGAR
		case VISTA_HANGAR:
			vista = new VistaHangar();
			break;
		case VISTA_ALTA_HANGAR:
			vista = new VistaAltaHangar();
			break;
		case VISTA_EXITO_ALTA_HANGAR:
			vista = new VistaExitoAltaHangar();
			break;
		case VISTA_FALLO_ALTA_HANGAR:
			vista = new VistaFalloAltaHangar();
			break;
		case VISTA_BAJA_HANGAR:
			vista = new VistaBajaHangar();
			break;
		case VISTA_EXITO_BAJA_HANGAR:
			vista = new VistaExitoBajaHangar();
			break;
		case VISTA_FALLO_BAJA_HANGAR:
			vista = new VistaFalloBajaHangar();
			break;
		case VISTA_CONSULTAR_HANGAR_POR_ID:
			vista = new VistaConsultarHangarPorId();
			break;
		case VISTA_RESULTADO_CONSULTAR_HANGAR_POR_ID:
			vista = new VistaResultadoConsultarHangarPorId();
			break;
		case VISTA_RESULTADO_CONSULTAR_TODOS_LOS_HANGARES:
			vista = new VistaResultadoConsultarTodosHangares();
			break;
		case VISTA_MODIFICAR_HANGAR:
			vista = new VistaModificarHangar();
			break;
		case VISTA_MODIFICAR_ID_HANGAR:
			vista = new VistaModificarIdHangar();
			break;
		case VISTA_EXITO_MODIFICAR_HANGAR:
			vista = new VistaExitoModificarHangar();
			break;
		case VISTA_FALLO_MODIFICAR_HANGAR:
			vista = new VistaFalloModificarHangar();
			break;
		case VISTA_CONSULTAR_HANGARES_POR_PERSONAL:
			vista = new VistaConsultarHangarPorPersonal();
			break;
		case VISTA_RESULTADO_CONSULTAR_HANGARES_POR_PERSONAL:
			vista = new VistaResultadoConsultarHangarPorPersonal();
			break;

		// PERSONAL
		case VISTA_PERSONAL:
			vista = new VistaPersonal();
			break;
		case VISTA_ALTA_PERSONAL:
			vista = new VistaAltaPersonal();
			break;
		case VISTA_EXITO_ALTA_PERSONAL:
			vista = new VistaExitoAltaPersonal();
			break;
		case VISTA_FALLO_ALTA_PERSONAL:
			vista = new VistaFalloAltaPersonal();
			break;
		case VISTA_BAJA_PERSONAL:
			vista = new VistaBajaPersonal();
			break;
		case VISTA_EXITO_BAJA_PERSONAL:
			vista = new VistaExitoBajaPersonal();
			break;
		case VISTA_FALLO_BAJA_PERSONAL:
			vista = new VistaFalloBajaPersonal();
			break;
		case VISTA_CONSULTAR_PERSONAL_POR_ID:
			vista = new VistaConsultarPersonalPorId();
			break;
		case VISTA_RESULTADO_CONSULTAR_PERSONAL_POR_ID:
			vista = new VistaResultadoConsultarPersonalPorId();
			break;
		case VISTA_RESULTADO_CONSULTAR_PERSONAL_EXISTENTE:
			vista = new VistaResultadoConsultarPersonalExistente();
			break;
		case VISTA_MODIFICAR_PERSONAL:
			vista = new VistaModificarPersonal();
			break;
		case VISTA_EXITO_MODIFICAR_PERSONAL:
			vista = new VistaExitoModificarPersonal();
			break;
		case VISTA_FALLO_MODIFICAR_PERSONAL:
			vista = new VistaFalloModificarPersonal();
			break;
		case VISTA_CONSULTAR_PERSONAL_POR_HANGAR:
			vista = new VistaConsultarPersonalPorHangar();
			break;
		case VISTA_RESULTADO_CONSULTAR_PERSONAL_POR_HANGAR:
			vista = new VistaResultadoConsultarPersonalPorHangar();
			break;
		case VISTA_VINCULAR_PERSONAL:
			vista = new VistaVincularPersonal();
			break;
		case VISTA_EXITO_VINCULAR_PERSONAL:
			vista = new VistaExitoVincularPersonal();
			break;
		case VISTA_FALLO_VINCULAR_PERSONAL:
			vista = new VistaFalloVincularPersonal();
			break;
		case VISTA_DESVINCULAR_PERSONAL:
			vista = new VistaDesvincularPersonal();
			break;
		case VISTA_EXITO_DESVINCULAR_PERSONAL:
			vista = new VistaExitoDesvincularPersonal();
			break;
		case VISTA_FALLO_DESVINCULAR_PERSONAL:
			vista = new VistaFalloDesvincularPersonal();
			break;
		}
		return vista;
	}
}
