package presentacion.controlador.comandos.proveedor;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.proveedor.SAProveedor;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class BajaProveedor implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProveedor sp = fn.crearSAProveedor();
		boolean exito = sp.bajaProveedor((int) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_BAJA_PROVEEDOR;
		} else {
			evento = Evento.VISTA_FALLO_BAJA_PROVEEDOR;
		}
		return new Contexto(evento, exito);
	}

}