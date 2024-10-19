package presentacion.comandos.modelo;

import java.util.List;

import negocio.aerolinea.TAerolinea;
import negocio.factoria.FactoriaNegocio;
import negocio.modelo.SAModelo;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarModelosPorAerolinea implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAModelo sm = fn.crearSAModelo();
		//TO DO
		//meter en SA el metodo consultarModeloPorAerolinea
		//List<TModelo> modelos = sa.consultarModeloPorAerolinea((int) datos);
		List<TAerolinea> aerolineas = null;

		Evento evento = null;
		//Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_MODELOS_POR_AEROLINEA;
		return new Contexto(evento, aerolineas);
	}

}