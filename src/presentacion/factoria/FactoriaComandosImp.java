package presentacion.factoria;

import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;
import presentacion.controlador.comandos.aerolinea.AltaAerolinea;
import presentacion.controlador.comandos.aerolinea.BajaAerolinea;
import presentacion.controlador.comandos.aerolinea.ConsultarAerolineaPorId;
import presentacion.controlador.comandos.aerolinea.ConsultarAerolineasPorModelo;
import presentacion.controlador.comandos.aerolinea.ConsultarTodasAerolineas;
import presentacion.controlador.comandos.aerolinea.ModificarAerolinea;
import presentacion.controlador.comandos.aerolinea.ModificarAerolineaId;
import presentacion.controlador.comandos.avion.AltaAvion;
import presentacion.controlador.comandos.avion.BajaAvion;
import presentacion.controlador.comandos.avion.ConsultarAvionPorId;
import presentacion.controlador.comandos.avion.ConsultarAvionesDeAerolineaPorHangar;
import presentacion.controlador.comandos.avion.ConsultarAvionesPorAerolinea;
import presentacion.controlador.comandos.avion.ConsultarAvionesPorHangar;
import presentacion.controlador.comandos.avion.ConsultarAvionesPorModelo;
import presentacion.controlador.comandos.avion.ConsultarTodosAviones;
import presentacion.controlador.comandos.avion.ModificarAvion;
import presentacion.controlador.comandos.avion.ModificarAvionId;
import presentacion.controlador.comandos.contrato.AbrirContrato;
import presentacion.controlador.comandos.contrato.CerrarContrato;
import presentacion.controlador.comandos.contrato.ConsultarContratoPorId;
import presentacion.controlador.comandos.contrato.ConsultarContratosPorAerolinea;
import presentacion.controlador.comandos.contrato.ConsultarContratosPorAerolineaPrecioYDuracion;
import presentacion.controlador.comandos.contrato.ConsultarTodosContratos;
import presentacion.controlador.comandos.contrato.ModificarContrato;
import presentacion.controlador.comandos.contrato.ModificarContratoId;
import presentacion.controlador.comandos.contrato.ModificarLineaContrato;
import presentacion.controlador.comandos.hangar.AltaHangar;
import presentacion.controlador.comandos.hangar.BajaHangar;
import presentacion.controlador.comandos.hangar.ConsultarHangarPorId;
import presentacion.controlador.comandos.hangar.ConsultarHangaresPorPersonal;
import presentacion.controlador.comandos.hangar.ConsultarTodosHangares;
import presentacion.controlador.comandos.hangar.ModificarHangar;
import presentacion.controlador.comandos.hangar.ModificarHangarId;
import presentacion.controlador.comandos.modelo.AltaModelo;
import presentacion.controlador.comandos.modelo.BajaModelo;
import presentacion.controlador.comandos.modelo.ConsultarModeloPorId;
import presentacion.controlador.comandos.modelo.ConsultarModelosPorAerolinea;
import presentacion.controlador.comandos.modelo.ConsultarTodosModelos;
import presentacion.controlador.comandos.modelo.DesvincularModelo;
import presentacion.controlador.comandos.modelo.ModificarModelo;
import presentacion.controlador.comandos.modelo.ModificarModeloId;
import presentacion.controlador.comandos.modelo.VincularModelo;
import presentacion.controlador.comandos.personal.AltaPersonal;
import presentacion.controlador.comandos.personal.BajaPersonal;
import presentacion.controlador.comandos.personal.ConsultarPersonalExistente;
import presentacion.controlador.comandos.personal.ConsultarPersonalPorHangar;
import presentacion.controlador.comandos.personal.ConsultarPersonalPorId;
import presentacion.controlador.comandos.personal.DesvincularPersonal;
import presentacion.controlador.comandos.personal.ModificarPersonal;
import presentacion.controlador.comandos.personal.ModificarPersonalId;
import presentacion.controlador.comandos.personal.VincularPersonal;

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
		case MODIFICAR_MODELO_ID:
			comando = new ModificarModeloId();
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
		case MODIFICAR_AVION_ID:
			comando = new ModificarAvionId();
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
		case MODIFICAR_CONTRATO_ID:
			comando = new ModificarContratoId();
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