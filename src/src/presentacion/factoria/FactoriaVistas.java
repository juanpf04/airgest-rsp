package presentacion.factoria;

import java.io.BufferedReader;
import java.io.FileReader;

import presentacion.Observador;
import presentacion.controlador.Evento;

public abstract class FactoriaVistas {

	private static FactoriaVistas instancia;

	public synchronized static FactoriaVistas getInstance() {
		if (instancia == null)
			instancia = getFactoriaVistasImp();
		return instancia;
	}

	private synchronized static FactoriaVistas getFactoriaVistasImp() {
		String claseFactoria = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader("recursos/configuraciones/FactoriaVistas.txt"));
			claseFactoria = in.readLine();
			in.close();
		} catch (java.io.IOException e) {
			System.out.println("Problema de E/S");
		}
		try {
			return (FactoriaVistas) Class.forName(claseFactoria).newInstance();
		} catch (Exception e) {
			System.out.println("Implementación de FactoriaVistas no encontrada");
		}
		return null;
	}

	public abstract Observador crearVista(Evento evento);
}
