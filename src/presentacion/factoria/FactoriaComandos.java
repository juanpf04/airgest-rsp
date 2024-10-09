package presentacion.factoria;

import presentacion.controlador.Evento;
import presentacion.comandos.Comando;

public abstract class FactoriaComandos {

	private static FactoriaComandos instancia;

	public static FactoriaComandos getInstance() {
		if (instancia == null)
			instancia = new FactoriaComandosImp();
		return instancia;
	}

	public abstract Comando crearComando(Evento evento);
}