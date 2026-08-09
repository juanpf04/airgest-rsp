package presentacion.controlador.comandos.venta;

import presentacion.controlador.comandos.Comando;

import java.util.List;

import negocio.factoria.FactoriaNegocioMall;
import negocio.venta.SAVenta;
import negocio.venta.TVenta;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarVentas implements Comando {
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAVenta sv = fn.crearSAVenta();
		List<TVenta> ventas = sv.consultarVentas();
		
		return new Contexto(Evento.VISTA_RESULTADO_CONSULTAR_VENTAS, ventas);
	}
}