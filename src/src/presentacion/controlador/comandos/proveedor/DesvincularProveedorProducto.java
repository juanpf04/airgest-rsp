package presentacion.controlador.comandos.proveedor;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.proveedor.SAProveedor;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class DesvincularProveedorProducto implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProveedor sp = fn.crearSAProveedor();
		boolean exito = sp.desvincularProveedorProducto(((int[]) datos)[0], ((int[]) datos)[1]);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_DESVINCULAR_PROVEEDOR;
		} else {
			evento = Evento.VISTA_FALLO_DESVINCULAR_PROVEEDOR;
		}
		return new Contexto(evento, exito);
	}
}