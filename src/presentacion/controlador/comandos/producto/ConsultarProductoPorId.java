package presentacion.controlador.comandos.producto;

import negocio.factoria.FactoriaNegocioMall;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ConsultarProductoPorId implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProducto ph = fn.crearSAProducto();
		TProducto pProducto = ph.consultarProductoPorId((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_PRODUCTO_POR_ID;
		return new Contexto(evento, pProducto);
	}

}