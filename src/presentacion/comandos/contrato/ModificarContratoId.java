package presentacion.comandos.contrato;

import negocio.contrato.SAContrato;
import negocio.contrato.TInfoContrato;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarContratoId implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {
		SAContrato sa = FactoriaNegocio.getInstance().crearSAContrato();
		TInfoContrato contrato = sa.consultarContratoPorId((int) datos);
		
		Evento evento;
		
		if (contrato != null){
			evento = Evento.VISTA_MODIFICAR_CONTRATO;
		} else{
			evento = Evento.VISTA_FALLO_MODIFICAR_CONTRATO;
		}
		
		return new Contexto(evento, contrato.getContrato());
	}

}
