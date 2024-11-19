package presentacion.comandos.hangar;

import negocio.factoria.FactoriaNegocio;
import negocio.hangar.SAHangar;
import negocio.hangar.THangar;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarHangarPorId implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAHangar sh = fn.crearSAHangar();
		THangar hangarh = sh.consultarHangarPorId((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_HANGAR_POR_ID;
		return new Contexto(evento, hangarh);
	}

}
