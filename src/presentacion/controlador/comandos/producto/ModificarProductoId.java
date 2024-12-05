package presentacion.controlador.comandos.producto;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;


public class ModificarProductoId implements Comando {
	
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProducto sa = fn.crearSAProducto();
		TProducto producto = sa.consultarProductoPorId((int) datos);
		Evento evento;
		if(producto != null && producto.getActivo()) {
			evento = Evento.VISTA_MODIFICAR_PRODUCTO;
		}else {
			evento = Evento.VISTA_FALLO_MODIFICAR_PRODUCTO;
		}
		return new Contexto(evento, producto);
	}
}