/**
 * 
 */
package presentacion.controlador.comandos.proveedor;

import presentacion.controlador.comandos.Comando;
import presentacion.controlador.Contexto;

import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import negocio.factoria.FactoriaNegocioMall;
import presentacion.controlador.Evento;
public class ModificarProveedorId implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProveedor sa = fn.crearSAProveedor();
		TProveedor proveedor = sa.consultarProveedorPorId((int) datos);
		Evento evento;
		if (proveedor != null && proveedor.getActivo()) {
			evento = Evento.VISTA_MODIFICAR_PROVEEDOR;
		} else {
			evento = Evento.VISTA_FALLO_MODIFICAR_PROVEEDOR;
		}

		return new Contexto(evento, proveedor);
	}
}