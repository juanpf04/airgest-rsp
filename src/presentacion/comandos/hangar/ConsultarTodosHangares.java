package presentacion.comandos.hangar;

import java.util.List;

import negocio.factoria.FactoriaNegocio;
import negocio.hangar.SAHangar;
import negocio.hangar.THangar;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarTodosHangares implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAHangar sh = fn.crearSAHangar();
		List<THangar> hangares = sh.consultarTodosHangares();
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_TODOS_LOS_HANGARES;
		return new Contexto(evento, hangares);
	}

}
