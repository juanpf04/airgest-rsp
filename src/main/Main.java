package main;

import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class Main {

	public static void main(String[] args) {

		Controlador ctrl = Controlador.getInstance();
		ctrl.accion(new Contexto(Evento.VISTA_PRINCIPAL)); // TODO: crear evento  iniciar y un comando que cree la vista principal
	}
}
