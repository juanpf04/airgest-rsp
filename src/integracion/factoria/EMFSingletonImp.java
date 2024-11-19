package integracion.factoria;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFSingletonImp extends EMFSingleton {

	private final static String PERSISTENCE_UNIT_NAME = "ms2425aeropuertocod";

	private EntityManagerFactory factory;

	public EMFSingletonImp() {
		this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	@Override
	public EntityManagerFactory getEMF() {
		return this.factory;
	}
}