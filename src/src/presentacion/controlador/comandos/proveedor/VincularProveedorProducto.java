package presentacion.controlador.comandos.proveedor;

import negocio.factoria.FactoriaNegocioMall;
import negocio.proveedor.SAProveedor;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class VincularProveedorProducto implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProveedor sp = fn.crearSAProveedor();
		boolean exito = sp.vincularProveedorProducto(((int[]) datos)[0], ((int[]) datos)[1]);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_VINCULAR_PROVEEDOR;
		} else {
			evento = Evento.VISTA_FALLO_VINCULAR_PROVEEDOR;
		}
		return new Contexto(evento, exito);
	}
}