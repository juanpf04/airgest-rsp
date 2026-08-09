package presentacion.controlador.comandos.marca;

import presentacion.controlador.comandos.Comando;
import negocio.marca.SAMarca;
import negocio.marca.TMarca;
import negocio.factoria.FactoriaNegocioMall;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarMarcaPorId implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAMarca sa = fn.crearSAMarca();
		TMarca marca = sa.consultarMarcaPorId((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_MARCA_POR_ID;

		return new Contexto(evento, marca);
	}
}