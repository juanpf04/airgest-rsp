package presentacion.controlador.comandos.proveedor;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class AltaProveedor implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProveedor sp = fn.crearSAProveedor();
		int id = sp.altaProveedor((TProveedor) datos);
		Evento evento = null;
		if (id != -1) {
			evento = Evento.VISTA_EXITO_ALTA_PROVEEDOR;
		} else {
			evento = Evento.VISTA_FALLO_ALTA_PROVEEDOR;
		}
		return new Contexto(evento, id);
	}
}