package presentacion.controlador.comandos.contrato;

import java.util.List;

import negocio.contrato.SAContrato;
import negocio.contrato.TContrato;
import negocio.factoria.FactoriaNegocio;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ConsultarContratosPorAerolinea implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAContrato sc = fn.crearSAContrato();
		List<TContrato> cs = sc.consultarContratosPorAerolinea((int) datos);
		return new Contexto(Evento.VISTA_RESULTADO_CONSULTAR_CONTRATOS_POR_AEROLINEA, cs);
	}

}