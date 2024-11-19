package presentacion.controlador.comandos.aerolinea;

import negocio.aerolinea.SAAerolinea;
import negocio.aerolinea.TAerolinea;
import negocio.factoria.FactoriaNegocio;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ModificarAerolineaId implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAerolinea sa = fn.crearSAAerolinea();
		TAerolinea aerolinea = sa.consultarAerolineaPorId((int) datos);
		Evento evento;
		if (aerolinea != null && aerolinea.getActivo()){
			evento = Evento.VISTA_MODIFICAR_AEROLINEA;
		} else{
			evento = Evento.VISTA_FALLO_MODIFICAR_AEROLINEA;
		}
		
		return new Contexto(evento, aerolinea);
	}

}
