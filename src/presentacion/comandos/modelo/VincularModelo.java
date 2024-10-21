package presentacion.comandos.modelo;

import negocio.factoria.FactoriaNegocio;
import negocio.modelo.SAModelo;
import negocio.modeloAerolinea.TModeloAerolinea;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class VincularModelo implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAModelo sm = fn.crearSAModelo();
		boolean exito = sm.vincularModelo((TModeloAerolinea) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_VINCULAR_MODELO;
		} else {
			evento = Evento.VISTA_FALLO_VINCULAR_MODELO;
		}
		return new Contexto(evento, exito);
	}

}