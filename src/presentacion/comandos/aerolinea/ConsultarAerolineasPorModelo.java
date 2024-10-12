package presentacion.comandos.aerolinea;

import java.util.List;

import negocio.aerolinea.SAAerolinea;
import negocio.aerolinea.TAerolinea;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarAerolineasPorModelo implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAerolinea sa = fn.crearSAAerolinea();
		List<TAerolinea> aerolineas = sa.consultarAerolineasPorModelo((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_AEROLINEAS_POR_MODELO;
		return new Contexto(evento, aerolineas);
	}
}
