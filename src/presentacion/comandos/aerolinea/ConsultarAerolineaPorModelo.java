package presentacion.comandos.aerolinea;

import java.util.List;

import negocio.aerolinea.SAAerolinea;
import negocio.aerolinea.TAerolinea;
import negocio.factoria.FactoriaNegocio;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarAerolineaPorModelo {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAerolinea sa = fn.crearSAAerolinea();
		//TO DO
		//meter en SA el metodo consultarAerolineaPorModelo
		//List<TAerolinea> aerolineas = sa.consultarAerolineaPorModelo((int) datos);
		List<TAerolinea> aerolineas = null;

		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_AEROLINEAS_POR_MODELO;
		return new Contexto(evento, aerolineas);
	}
}
