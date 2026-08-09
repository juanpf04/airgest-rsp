package presentacion.controlador.comandos.contrato;

import negocio.contrato.SAContrato;
import negocio.contrato.TContrato;
import negocio.contrato.TInfoContrato;
import negocio.factoria.FactoriaNegocio;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ModificarContratoId implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {
		SAContrato sa = FactoriaNegocio.getInstance().crearSAContrato();
		TInfoContrato contrato = sa.consultarContratoPorId((int) datos);
		TContrato c = null;
		Evento evento;
		
		if (contrato != null){
			evento = Evento.VISTA_MODIFICAR_CONTRATO;
			c = contrato.getContrato();
		} else{
			evento = Evento.VISTA_FALLO_MODIFICAR_CONTRATO;
		}
		
		return new Contexto(evento, c);
	}

}
