package presentacion.controlador.comandos.contrato;

import negocio.contrato.SAContrato;
import negocio.contrato.TInfoContrato;
import negocio.factoria.FactoriaNegocio;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ConsultarContratoPorId implements Comando {

	@Override
	public Contexto ejecutar(Object datos) 
	{
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAContrato sc = fn.crearSAContrato();
		TInfoContrato contrato = sc.consultarContratoPorId((int) datos);
		return new Contexto(Evento.VISTA_RESULTADO_CONSULTAR_CONTRATO_POR_ID, contrato);
	}

}
