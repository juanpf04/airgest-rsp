package presentacion.controlador.comandos.marca;

import negocio.marca.SAMarca;
import negocio.marca.TMarca;
import negocio.factoria.FactoriaNegocioMall;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ModificarMarcaId implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAMarca sa = fn.crearSAMarca();
		TMarca marca = sa.consultarMarcaPorId((int) datos);
		Evento evento;
		if (marca != null && marca.getActivo()) {
			evento = Evento.VISTA_MODIFICAR_MARCA;
		} else {
			evento = Evento.VISTA_FALLO_MODIFICAR_MARCA;
		}

		return new Contexto(evento, marca);
	}

}
