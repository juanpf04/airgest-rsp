package presentacion.comandos.avion;

import negocio.avion.SAAvion;
import negocio.avion.TAvion;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarAvionPorId implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAvion sav = fn.crearSAAvion();
		TAvion avion = sav.consultarAvionPorId((int) datos);
		return new Contexto(Evento.VISTA_RESULTADO_CONSULTAR_AVION_POR_ID, avion);
	}

}
