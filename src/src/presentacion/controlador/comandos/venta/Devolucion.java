package presentacion.controlador.comandos.venta;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.venta.SAVenta;
import negocio.venta.TLineaVenta;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class Devolucion implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAVenta sv = fn.crearSAVenta();
		boolean exito = sv.devolucion((TLineaVenta) datos);
		Evento evento = null;
		if (exito)
			evento = Evento.VISTA_EXITO_DEVOLUCION_VENTA;
		else
			evento = Evento.VISTA_FALLO_DEVOLUCION_VENTA;

		return new Contexto(evento);
	}
}