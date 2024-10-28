package presentacion.comandos.personal;

import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personal.TPersonal;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarPersonalId implements Comando{
	
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAPersonal sp = fn.crearSAPersonal();
		TPersonal personal = sp.consultarPersonalPorId((int) datos);
		Evento evento;
		if(personal != null && personal.getActivo()) {
			evento = Evento.VISTA_MODIFICAR_PERSONAL;
		}else {
			evento = Evento.VISTA_FALLO_MODIFICAR_PERSONAL;
		}
		return new Contexto(evento, personal);
	}
}

	