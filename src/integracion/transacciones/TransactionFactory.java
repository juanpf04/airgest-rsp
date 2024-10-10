
package integracion.transacciones;

public abstract class TransactionFactory {
	
	private static TransactionFactory transactionFactory;

	public synchronized static TransactionFactory getInstance() {
		
		if (transactionFactory == null){
			transactionFactory = new TransactionFactoryImp();
		}
		
		return transactionFactory;
		
	}

	public abstract Transaction nuevaTransaccion();
}