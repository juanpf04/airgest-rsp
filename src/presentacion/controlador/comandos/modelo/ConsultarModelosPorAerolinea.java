package presentacion.controlador.comandos.modelo;

import java.util.List;

import negocio.factoria.FactoriaNegocio;
import negocio.modelo.SAModelo;
import negocio.modelo.TModelo;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ConsultarModelosPorAerolinea implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAModelo sm = fn.crearSAModelo();
		List<TModelo> modelos = sm.consultarModelosPorAerolinea((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_MODELOS_POR_AEROLINEA;
		return new Contexto(evento, modelos);
	}

}