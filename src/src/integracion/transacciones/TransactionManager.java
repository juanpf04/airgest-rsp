
package integracion.transacciones;

public abstract class TransactionManager {

	private static TransactionManager transactionManager;

	public abstract Transaction nuevaTransaccion();

	public abstract void eliminarTransaccion();

	public abstract Transaction getTransaccion();

	public synchronized static TransactionManager getInstance() {

		if (transactionManager == null) {
			transactionManager = new TransactionManagerImp();
		}
		return transactionManager;

	}
}