package presentacion.controlador.comandos.contrato;

import negocio.contrato.SAContrato;
import negocio.contrato.TCarrito;
import negocio.factoria.FactoriaNegocio;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class AbrirContrato implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAContrato sc = fn.crearSAContrato();
		TCarrito carrito = sc.abrirContrato((int) datos);
		return new Contexto(Evento.VISTA_CARRITO, carrito);
	}

}
