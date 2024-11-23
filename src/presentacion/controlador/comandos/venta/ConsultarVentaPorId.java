package presentacion.controlador.comandos.venta;

import negocio.factoria.FactoriaNegocioMall;
import negocio.venta.SAVenta;
import negocio.venta.TInfoVenta;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ConsultarVentaPorId implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAVenta sv = fn.crearSAVenta();
		TInfoVenta venta = sv.consultarVentaPorId((int) datos);
		return new Contexto(Evento.VISTA_RESULTADO_CONSULTAR_VENTA_POR_ID, venta);
	}
}