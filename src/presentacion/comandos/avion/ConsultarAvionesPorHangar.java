package presentacion.comandos.avion;

import java.util.List;

import negocio.avion.SAAvion;
import negocio.avion.TAvion;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarAvionesPorHangar implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAvion sav = fn.crearSAAvion();
		List<TAvion> aviones = sav.consultarAvionesPorHangar((int) datos);
		return new Contexto(Evento.VISTA_RESULTADO_CONSULTAR_AVIONES_POR_HANGAR, aviones);
	}

}
