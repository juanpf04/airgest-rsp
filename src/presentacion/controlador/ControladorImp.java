package presentacion.controlador;

import presentacion.factoria.FactoriaComandos;
import presentacion.factoria.FactoriaVistas;
import presentacion.Observador;
import presentacion.comandos.Comando;

public class ControladorImp extends Controlador {

	@Override
	public void accion(Contexto contexto) {
		Comando comando = FactoriaComandos.getInstance().crearComando(contexto.getEvento());

		// Si no existe un comando con ese evento, es una vista
		if (comando != null)
			contexto = comando.ejecutar(contexto.getInfo());

		Observador vista = FactoriaVistas.getInstance().crearVista(contexto.getEvento());

		vista.actualizar(contexto);
	}

}
