package presentacion.controlador.comandos.marca;

import presentacion.controlador.comandos.Comando;

import java.util.List;

import negocio.marca.SAMarca;
import negocio.marca.TMarca;
import negocio.factoria.FactoriaNegocioMall;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarMarcas implements Comando {
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAMarca sa = fn.crearSAMarca();
		List<TMarca> marcas = sa.consultarMarcas();
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_MARCAS;
		return new Contexto(evento, marcas);
	}
}