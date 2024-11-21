
package presentacion.controlador.comandos.marca;

import presentacion.controlador.comandos.Comando;
import negocio.marca.SAMarca;
import negocio.marca.TMarca;
import negocio.factoria.FactoriaNegocioMall;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class AltaMarca implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAMarca sa = fn.crearSAMarca();
		int id = sa.altaMarca((TMarca) datos);
		Evento evento = null;
		if (id != -1)
			evento = Evento.VISTA_EXITO_ALTA_MARCA;
		else
			evento = Evento.VISTA_FALLO_ALTA_MARCA;

		return new Contexto(evento, id);
	}
}