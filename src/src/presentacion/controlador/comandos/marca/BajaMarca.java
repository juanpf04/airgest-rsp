package presentacion.controlador.comandos.marca;

import presentacion.controlador.comandos.Comando;
import negocio.marca.SAMarca;
import negocio.factoria.FactoriaNegocioMall;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class BajaMarca implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAMarca sa = fn.crearSAMarca();
		boolean exito = sa.bajaMarca((int) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_BAJA_MARCA;
		} else {
			evento = Evento.VISTA_FALLO_BAJA_MARCA;
		}
		return new Contexto(evento, exito);
	}
}