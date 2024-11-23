package presentacion.factoria;

import java.io.BufferedReader;
import java.io.FileReader;

import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public abstract class FactoriaComandos {

	private static FactoriaComandos instancia;

	public synchronized static FactoriaComandos getInstance() {
		if (instancia == null)
			instancia = getFactoriaComandosImp();
		return instancia;
	}

	private synchronized static FactoriaComandos getFactoriaComandosImp() {
		String claseFactoria = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader("recursos/configuraciones/FactoriaComandos.txt"));
			claseFactoria = in.readLine();
			in.close();
		} catch (java.io.IOException e) {
			System.out.println("Problema de E/S");
		}
		try {
			return (FactoriaComandos) Class.forName(claseFactoria).newInstance();
		} catch (Exception e) {
			System.out.println("Implementación de FactoriaComandos no encontrada");
		}
		return null;
	}

	public abstract Comando crearComando(Evento evento);
}