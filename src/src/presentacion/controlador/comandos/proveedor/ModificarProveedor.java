package presentacion.controlador.comandos.proveedor;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarProveedor implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProveedor sp = fn.crearSAProveedor();
		boolean exito = sp.modificarProveedor((TProveedor) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_MODIFICAR_PROVEEDOR;
		} else {
			evento = Evento.VISTA_FALLO_MODIFICAR_PROVEEDOR;
		}
		return new Contexto(evento, exito);
	}
}