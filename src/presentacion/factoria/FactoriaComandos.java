package presentacion.factoria;

import presentacion.comandos.Comando;
import presentacion.controlador.Evento;

public abstract class FactoriaComandos {

	private static FactoriaComandos instancia;

	public static FactoriaComandos getInstance() {
		if (instancia == null)
			instancia = new FactoriaComandosImp();
		return instancia;
	}

	public abstract Comando crearComando(Evento evento);
}