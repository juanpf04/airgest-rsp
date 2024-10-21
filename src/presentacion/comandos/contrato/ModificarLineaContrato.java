package presentacion.comandos.contrato;

import negocio.contrato.SAContrato;
import negocio.factoria.FactoriaNegocio;
import negocio.lineaContrato.TLineaContrato;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarLineaContrato implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAContrato sc = fn.crearSAContrato();
		Evento evento = null;
		if (sc.modificarLineaContrato((TLineaContrato) datos)) 
			evento = Evento.VISTA_EXITO_MODIFICAR_LINEA_CONTRATO;
		else
			evento = Evento.VISTA_FALLO_MODIFICAR_LINEA_CONTRATO;
		return new Contexto(evento, null);
	}

}