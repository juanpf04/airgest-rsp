package presentacion.controlador.comandos.producto;

import presentacion.controlador.comandos.Comando;

import java.util.List;

import negocio.factoria.FactoriaNegocioMall;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarProductos implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProducto sh = fn.crearSAProducto();
		List<TProducto> productos = sh.consultarProductos();
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_PRODUCTOS;
		return new Contexto(evento, productos);
	}
}