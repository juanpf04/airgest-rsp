package presentacion.comandos.contrato;

import negocio.contrato.SAContrato;
import negocio.contrato.TContrato;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarContrato implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAContrato sc = fn.crearSAContrato();
		Evento evento = null;
		if (sc.modificarContrato((TContrato) datos)) 
			evento = Evento.VISTA_EXITO_MODIFICAR_CONTRATO;
		else 
			evento = Evento.VISTA_FALLO_MODIFICAR_CONTRATO;

		return new Contexto(evento, null);
	}

}
