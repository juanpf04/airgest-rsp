package presentacion.controlador.comandos.modelo;

import negocio.factoria.FactoriaNegocio;
import negocio.modelo.SAModelo;
import negocio.modelo.TModelo;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ModificarModelo implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAModelo sm = fn.crearSAModelo();
		boolean exito = sm.modificarModelo((TModelo) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_MODIFICAR_MODELO;
		} else {
			evento = Evento.VISTA_FALLO_MODIFICAR_MODELO;
		}
		return new Contexto(evento, exito);
	}

}