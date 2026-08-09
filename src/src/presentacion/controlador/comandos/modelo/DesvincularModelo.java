package presentacion.controlador.comandos.modelo;

import negocio.factoria.FactoriaNegocio;
import negocio.modelo.SAModelo;
import negocio.modeloAerolinea.TModeloAerolinea;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class DesvincularModelo implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAModelo sm = fn.crearSAModelo();
		boolean exito = sm.desvincularModelo((TModeloAerolinea) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_DESVINCULAR_MODELO;
		} else {
			evento = Evento.VISTA_FALLO_DESVINCULAR_MODELO;
		}
		return new Contexto(evento, exito);
	}

}