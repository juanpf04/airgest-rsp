package presentacion.controlador.comandos.modelo;

import negocio.factoria.FactoriaNegocio;
import negocio.modelo.SAModelo;
import negocio.modelo.TModelo;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ConsultarModeloPorId implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAModelo sm = fn.crearSAModelo();
		TModelo modelo = sm.consultarModelo((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_MODELO_POR_ID;
		return new Contexto (evento,modelo);
	}

}