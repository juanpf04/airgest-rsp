package integracion.transacciones;

import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImp extends TransactionManager {
	
	private ConcurrentHashMap<Thread, Transaction> transactionMap = new ConcurrentHashMap<>();

	public Transaction nuevaTransaccion() {
		
		if (transactionMap.contains(Thread.currentThread())){
			throw new RuntimeException("Este hilo ya tiene una transacción en curso");
		}
		
		Transaction t = TransactionFactory.getInstance().nuevaTransaccion();
		transactionMap.put(Thread.currentThread(), t);
		
		return t;
	}

	
	public void eliminarTransaccion() {
		transactionMap.remove(Thread.currentThread());
	}

	public Transaction getTransaccion() {
		if (!transactionMap.contains(Thread.currentThread())){
			throw new RuntimeException("No existe transacción para este hilo");
		}
		
		return transactionMap.get(Thread.currentThread());
		
	}
}