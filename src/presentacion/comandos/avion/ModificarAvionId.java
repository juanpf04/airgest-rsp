package presentacion.comandos.avion;

import java.util.ArrayList;

import negocio.avion.SAAvion;
import negocio.avion.TAvion;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarAvionId implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAvion sa = fn.crearSAAvion();
		TAvion avion = sa.consultarAvionPorId((int) datos);
		Evento evento;
		ArrayList<Object> lista = new ArrayList<Object>();
		lista.add(avion);
		lista.add(null);
		if (avion != null && avion.getActivo()){
			evento = Evento.VISTA_MODIFICAR_AVION;
		} else{
			evento = Evento.VISTA_FALLO_MODIFICAR_AVION;
		}
		
		return new Contexto(evento, lista);
	}

}
