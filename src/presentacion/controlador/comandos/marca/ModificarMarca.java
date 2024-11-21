
package presentacion.controlador.comandos.marca;

import presentacion.controlador.comandos.Comando;
import negocio.marca.SAMarca;
import negocio.marca.TMarca;
import negocio.factoria.FactoriaNegocioMall;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarMarca implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAMarca sa = fn.crearSAMarca();
		boolean exito = sa.modificarMarca((TMarca) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_MODIFICAR_MARCA;
		} else {
			evento = Evento.VISTA_FALLO_MODIFICAR_MARCA;
		}
		return new Contexto(evento, exito);
	}
}