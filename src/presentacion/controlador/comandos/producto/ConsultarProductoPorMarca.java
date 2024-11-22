package presentacion.controlador.comandos.producto;

import presentacion.controlador.comandos.Comando;

import java.util.List;

import negocio.factoria.FactoriaNegocioMall;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarProductoPorMarca implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProducto sp = fn.crearSAProducto();
		List<TProducto> producto = sp.consultarProductosPorMarca((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_PRODUCTOS_POR_MARCA;
		return new Contexto(evento, producto);
	}
}