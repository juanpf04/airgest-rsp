package presentacion.comandos;

import presentacion.controlador.Contexto;

public interface Comando {

	public Contexto ejecutar(Object datos);
}