package presentacion.factoria;

import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public abstract class FactoriaComandos {

	private static FactoriaComandos instancia;

	public synchronized static FactoriaComandos getInstance() {
		if (instancia == null)
			instancia = new FactoriaComandosImp();
		return instancia;
	}

	public abstract Comando crearComando(Evento evento);
}