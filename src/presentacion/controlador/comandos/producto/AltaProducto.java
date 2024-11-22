package presentacion.controlador.comandos.producto;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class AltaProducto implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProducto sp = fn.crearSAProducto();
		int id = sp.altaProducto((TProducto) datos);
		Evento evento = null;
		if (id != -1) {
			evento = Evento.VISTA_EXITO_ALTA_PRODUCTO;
		} else {
			evento = Evento.VISTA_FALLO_ALTA_PRODUCTO;
		}
		return new Contexto(evento, id);
	}
}