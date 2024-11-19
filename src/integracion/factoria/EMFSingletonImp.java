
package integracion.factoria;

import javax.persistence.EntityManagerFactory;


public class EMFSingletonImp extends EMFSingleton {
	
	private static String PERSISTENCE_UNIT_NAME;
	
	private EntityManagerFactory factory;
	
	@Override
	public EntityManagerFactory getEMF() {
		// TODO Auto-generated method stub
		return null;
	}
}