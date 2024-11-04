package presentacion.factoria;

import presentacion.comandos.Comando;
import presentacion.comandos.aerolinea.AltaAerolinea;
import presentacion.comandos.aerolinea.BajaAerolinea;
import presentacion.comandos.aerolinea.ConsultarAerolineaPorId;
import presentacion.comandos.aerolinea.ConsultarAerolineasPorModelo;
import presentacion.comandos.aerolinea.ConsultarTodasAerolineas;
import presentacion.comandos.aerolinea.ModificarAerolinea;
import presentacion.comandos.aerolinea.ModificarAerolineaId;
import presentacion.comandos.avion.AltaAvion;
import presentacion.comandos.avion.BajaAvion;
import presentacion.comandos.avion.ConsultarAvionPorId;
import presentacion.comandos.avion.ConsultarAvionesDeAerolineaPorHangar;
import presentacion.comandos.avion.ConsultarAvionesPorAerolinea;
import presentacion.comandos.avion.ConsultarAvionesPorHangar;
import presentacion.comandos.avion.ConsultarAvionesPorModelo;
import presentacion.comandos.avion.ConsultarTodosAviones;
import presentacion.comandos.avion.ModificarAvion;
import presentacion.comandos.avion.ModificarAvionId;
import presentacion.comandos.contrato.AbrirContrato;
import presentacion.comandos.contrato.CerrarContrato;
import presentacion.comandos.contrato.ConsultarContratoPorId;
import presentacion.comandos.contrato.ConsultarContratosPorAerolinea;
import presentacion.comandos.contrato.ConsultarContratosPorAerolineaPrecioYDuracion;
import presentacion.comandos.contrato.ConsultarTodosContratos;
import presentacion.comandos.contrato.ModificarContrato;
import presentacion.comandos.contrato.ModificarContratoId;
import presentacion.comandos.contrato.ModificarLineaContrato;
import presentacion.comandos.hangar.AltaHangar;
import presentacion.comandos.hangar.BajaHangar;
import presentacion.comandos.hangar.ConsultarHangarPorId;
import presentacion.comandos.hangar.ConsultarHangaresPorPersonal;
import presentacion.comandos.hangar.ConsultarTodosHangares;
import presentacion.comandos.hangar.ModificarHangar;
import presentacion.comandos.hangar.ModificarHangarId;
import presentacion.comandos.modelo.AltaModelo;
import presentacion.comandos.modelo.BajaModelo;
import presentacion.comandos.modelo.ConsultarModeloPorId;
import presentacion.comandos.modelo.ConsultarModelosPorAerolinea;
import presentacion.comandos.modelo.ConsultarTodosModelos;
import presentacion.comandos.modelo.DesvincularModelo;
import presentacion.comandos.modelo.ModificarModelo;
import presentacion.comandos.modelo.ModificarModeloId;
import presentacion.comandos.modelo.VincularModelo;
import presentacion.comandos.personal.AltaPersonal;
import presentacion.comandos.personal.BajaPersonal;
import presentacion.comandos.personal.ConsultarPersonalExistente;
import presentacion.comandos.personal.ConsultarPersonalPorHangar;
import presentacion.comandos.personal.ConsultarPersonalPorId;
import presentacion.comandos.personal.DesvincularPersonal;
import presentacion.comandos.personal.ModificarPersonal;
import presentacion.comandos.personal.ModificarPersonalId;
import presentacion.comandos.personal.VincularPersonal;
import presentacion.controlador.Evento;

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