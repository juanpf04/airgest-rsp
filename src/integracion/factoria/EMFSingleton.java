
package integracion.factoria;

import javax.persistence.EntityManagerFactory;

public abstract class EMFSingleton {
	private static EMFSingleton instancia;

	public static EMFSingleton getInstance() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public abstract EntityManagerFactory getEMF();
}