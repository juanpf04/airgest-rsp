package presentacion.controlador.comandos.hangar;

import java.util.List;

import negocio.factoria.FactoriaNegocio;
import negocio.hangar.SAHangar;
import negocio.hangar.THangar;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ConsultarHangaresPorPersonal implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAHangar sh = fn.crearSAHangar();
		List<THangar> hangares = sh.consultarHangaresPorPersonal((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_HANGARES_POR_PERSONAL;
		return new Contexto(evento, hangares);
	}

}
