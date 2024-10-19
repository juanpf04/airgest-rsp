package presentacion.comandos.modelo;

import java.util.List;

import negocio.factoria.FactoriaNegocio;
import negocio.modelo.SAModelo;
import negocio.modelo.TModelo;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarTodosModelos implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAModelo sm = fn.crearSAModelo();
		List<TModelo> modelos = sm.consultarTodosModelos();
		
		Evento evento = null;
		//Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_TODOS_MODELOS;
		return new Contexto (evento,modelos);
	}

}