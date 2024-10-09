package presentacion.factoria;

import presentacion.Observador;
import presentacion.controlador.Evento;

public abstract class FactoriaVistas {

	private static FactoriaVistas instancia;

	public static FactoriaVistas getInstance() {
		if (instancia == null)
			instancia = new FactoriaVistasImp();
		return instancia;
	}

	public abstract Observador crearVista(Evento evento);
}
