package presentacion.controlador.comandos.hangar;

import negocio.factoria.FactoriaNegocio;
import negocio.hangar.SAHangar;
import negocio.hangar.THangar;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ModificarHangarId implements Comando{
	
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAHangar sa = fn.crearSAHangar();
		THangar hangar = sa.consultarHangarPorId((int) datos);
		Evento evento;
		if(hangar != null && hangar.getActivo()) {
			evento = Evento.VISTA_MODIFICAR_HANGAR;
		}else {
			evento = Evento.VISTA_FALLO_MODIFICAR_HANGAR;
		}
		return new Contexto(evento, hangar);
	}
}

	
