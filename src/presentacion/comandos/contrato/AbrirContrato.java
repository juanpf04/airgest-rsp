package presentacion.comandos.contrato;

import negocio.contrato.SAContrato;
import negocio.contrato.TCarrito;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class AbrirContrato implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAContrato sc = fn.crearSAContrato();
		TCarrito carrito = sc.abrirContrato((int) datos);
		return new Contexto(Evento.VISTA_CARRITO, carrito);
	}

}
