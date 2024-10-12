package presentacion.comandos.contrato;

import negocio.contrato.SAContrato;
import negocio.contrato.TCarrito;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class CerrarContrato implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAContrato sc = fn.crearSAContrato();
		int id = sc.cerrarContrato((TCarrito) datos);
		Evento evento = null;
		if (id != -1) 
			evento = Evento.VISTA_EXITO_CERRAR_CONTRATO;
		else 
			evento = Evento.VISTA_FALLO_CERRAR_CONTRATO;
		
		return new Contexto(evento, id);
	}

}
