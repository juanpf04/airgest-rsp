package presentacion.factoria;

import presentacion.controlador.Evento;
import presentacion.comandos.Comando;
import presentacion.comandos.modelo.*;
import presentacion.comandos.avion.*;
import presentacion.comandos.aerolinea.*;
import presentacion.comandos.hangar.*;
import presentacion.comandos.personal.*;
import presentacion.comandos.contrato.*;

public class FactoriaComandosImp extends FactoriaComandos {

	@Override
	public Comando crearComando(Evento evento) {
		Comando comando = null;

		switch (evento) {
		case ALTA_MODELO:
			comando = new AltaModelo();
			break;
		case BAJA_MODELO:
			comando = new BajaModelo();
			break;
		case CONSULTAR_MODELO_POR_ID:
			comando = new ConsultarModeloPorId();
			break;
		case CONSULTAR_TODOS_MODELOS:
			comando = new ConsultarTodosModelos();
			break;
		case MODIFICAR_MODELO:
			comando = new ModificarModelo();
			break;
		case VINCULAR_MODELO:
			comando = new VincularModelo();
			break;
		case DESVINCULAR_MODELO:
			comando = new DesvincularModelo();
			break;
		case CONSULTAR_MODELOS_POR_AEROLINEA:
			comando = new ConsultarModelosPorAerolinea();
			break;

		case ALTA_AVION:
			comando = new AltaAvion();
			break;
		case BAJA_AVION:
			comando = new BajaAvion();
			break;
		case CONSULTAR_AVION_POR_ID:
			comando = new ConsultarAvionPorId();
			break;
		case CONSULTAR_TODOS_AVIONES:
			comando = new ConsultarTodosAviones();
			break;
		case MODIFICAR_AVION:
			comando = new ModificarAvion();
			break;
		case CONSULTAR_AVIONES_POR_MODELO:
			comando = new ConsultarAvionesPorModelo();
			break;
		case CONSULTAR_AVIONES_POR_AEROLINEA:
			comando = new ConsultarAvionesPorAerolinea();
			break;
		case CONSULTAR_AVIONES_POR_HANGAR:
			comando = new ConsultarAvionesPorHangar();
			break;
		case CONSULTAR_AVIONES_DE_AEROLINEA_POR_HANGAR:
			comando = new ConsultarAvionesDeAerolineaPorHangar();
			break;

		case ALTA_AEROLINEA:
			comando = new AltaAerolinea();
			break;
		case BAJA_AEROLINEA:
			comando = new BajaAerolinea();
			break;
		case CONSULTAR_AEROLINEA_POR_ID:
			comando = new ConsultarAerolineaPorId();
			break;
		case CONSULTAR_TODAS_AEROLINEAS:
			comando = new ConsultarTodasAerolineas();
			break;
		case MODIFICAR_AEROLINEA:
			comando = new ModificarAerolinea();
			break;
		case MODIFICAR_AEROLINEA_ID:
			comando = new ModificarAerolineaId();
			break;
		case CONSULTAR_AEROLINEAS_POR_MODELO:
			comando = new ConsultarAerolineasPorModelo();
			break;

		case ABRIR_CONTRATO:
			comando = new AbrirContrato();
			break;
		case CERRAR_CONTRATO:
			comando = new CerrarContrato();
			break;
		case CONSULTAR_CONTRATO_POR_ID:
			comando = new ConsultarContratoPorId();
			break;
		case CONSULTAR_TODOS_CONTRATOS:
			comando = new ConsultarTodosContratos();
			break;
		case MODIFICAR_CONTRATO:
			comando = new ModificarContrato();
			break;
		case MODIFICAR_LINEA_CONTRATO:
			comando = new ModificarLineaContrato();
			break;
		case CONSULTAR_CONTRATOS_POR_AEROLINEA:
			comando = new ConsultarContratosPorAerolinea();
			break;
		case CONSULTAR_CONTRATOS_POR_AEROLINEA_PRECIO_Y_DURACION:
			comando = new ConsultarContratosPorAerolineaPrecioYDuracion();
			break;

		case ALTA_HANGAR:
			comando = new AltaHangar();
			break;
		case BAJA_HANGAR:
			comando = new BajaHangar();
			break;
		case CONSULTAR_HANGAR_POR_ID:
			comando = new ConsultarHangarPorId();
			break;
		case CONSULTAR_TODOS_HANGARES:
			comando = new ConsultarTodosHangares();
			break;
		case MODIFICAR_HANGAR:
			comando = new ModificarHangar();
			break;
		case MODIFICAR_HANGAR_ID:
			comando = new ModificarHangarId();
			break;
		case CONSULTAR_HANGARES_POR_PERSONAL:
			comando = new ConsultarHangaresPorPersonal();
			break;

		case ALTA_PERSONAL:
			comando = new AltaPersonal();
			break;
		case BAJA_PERSONAL:
			comando = new BajaPersonal();
			break;
		case VINCULAR_PERSONAL:
			comando = new VincularPersonal();
			break;
		case DESVINCULAR_PERSONAL:
			comando = new DesvincularPersonal();
			break;
		case CONSULTAR_PERSONAL_POR_ID:
			comando = new ConsultarPersonalPorId();
			break;
		case CONSULTAR_PERSONAL_EXISTENTE:
			comando = new ConsultarPersonalExistente();
			break;
		case MODIFICAR_PERSONAL:
			comando = new ModificarPersonal();
			break;
		case MODIFICAR_PERSONAL_ID:
			comando = new ModificarPersonalId();
			break;
		case CONSULTAR_PERSONAL_POR_HANGAR:
			comando = new ConsultarPersonalPorHangar();
			break;

		default:
			break;
		}

		return comando;
	}

}