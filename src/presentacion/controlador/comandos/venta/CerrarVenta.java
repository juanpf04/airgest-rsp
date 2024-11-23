package presentacion.controlador.comandos.venta;

import negocio.factoria.FactoriaNegocioMall;
import negocio.venta.SAVenta;
import negocio.venta.TCarritoVenta;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class CerrarVenta implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAVenta sv = fn.crearSAVenta();
		int id = sv.cerrarVenta((TCarritoVenta) datos);
		Evento evento = null;
		if (id != -1)
			evento = Evento.VISTA_EXITO_CERRAR_VENTA;
		else
			evento = Evento.VISTA_FALLO_CERRAR_VENTA;

		return new Contexto(evento, id);
	}
}