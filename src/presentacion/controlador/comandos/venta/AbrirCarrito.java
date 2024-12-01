package presentacion.controlador.comandos.venta;

import negocio.contrato.TCarrito;
import negocio.factoria.FactoriaNegocioMall;
import negocio.venta.SAVenta;
import negocio.venta.TCarritoVenta;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class AbrirCarrito implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAVenta sv = fn.crearSAVenta();
		TCarritoVenta carrito = sv.abrirCarrito((int) datos);
		return new Contexto(Evento.VISTA_CARRITO_VENTA, carrito);
	}
}