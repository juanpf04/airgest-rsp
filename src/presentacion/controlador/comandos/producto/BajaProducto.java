package presentacion.controlador.comandos.producto;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.producto.SAProducto;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class BajaProducto implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProducto sh = fn.crearSAProducto();
		boolean exito = sh.bajaProducto((int) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_BAJA_PRODUCTO;
		} else {
			evento = Evento.VISTA_FALLO_BAJA_PRODUCTO;
		}
		return new Contexto(evento, exito);
	}

}