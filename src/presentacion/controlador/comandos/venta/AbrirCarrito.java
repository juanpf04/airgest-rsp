package presentacion.controlador.comandos.venta;

import negocio.factoria.FactoriaNegocioMall;
import negocio.venta.SAVenta;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class AbrirCarrito implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAVenta sv = fn.crearSAVenta();
		/*boolean exito = sv.abrirCarrito((int) datos);
		Evento evento = null;
		if (exito)
			evento = Evento.VISTA_EXITO_ABRIR_VENTA;
		else
			evento = Evento.VISTA_FALLO_ABRIR_VENTA;*/

		return null;
	}
}