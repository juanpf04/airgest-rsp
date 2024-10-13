package presentacion.comandos.personal;

import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class BajaPersonal implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAPersonal sp = fn.crearSAPersonal();
		sp = fn.crearSAPersonal();
		boolean exito = sp.bajaPersonal((int) datos);
		Evento evento = null;
		if (exito){
			evento=Evento.VISTA_EXITO_BAJA_PERSONAL;
		}
		 else{
			evento=Evento.VISTA_FALLO_BAJA_PERSONAL;
		 }
		return new Contexto(evento, exito);
	}

}

