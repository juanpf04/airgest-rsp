package presentacion.controlador.comandos.producto;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarProducto implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProducto sp = fn.crearSAProducto();
		boolean exito = sp.modificarProducto((TProducto) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_MODIFICAR_PRODUCTO;
		} else {
			evento = Evento.VISTA_FALLO_MODIFICAR_PRODUCTO;
		}
		return new Contexto(evento, exito);
	}

}