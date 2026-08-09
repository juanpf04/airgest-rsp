package integracion.factoria;

import javax.persistence.EntityManagerFactory;

public abstract class EMFSingleton {

	private static EMFSingleton instancia;

	public synchronized static EMFSingleton getInstance() {
		if (instancia == null)
			instancia = new EMFSingletonImp();

		return instancia;
	}

	public abstract EntityManagerFactory getEMF();
}