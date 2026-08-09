package presentacion.controlador.comandos.venta;

import negocio.factoria.FactoriaNegocioMall;
import negocio.venta.SAVenta;
import negocio.venta.TVenta;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ModificarVenta implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAVenta sv = fn.crearSAVenta();
		Evento evento = null;
		if (sv.modificarVenta((TVenta) datos)) 
			evento = Evento.VISTA_EXITO_MODIFICAR_VENTA;
		else 
			evento = Evento.VISTA_FALLO_MODIFICAR_VENTA;

		return new Contexto(evento, null);
	}

}
