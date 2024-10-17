package integracion.transacciones;

import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImp extends TransactionManager {
	
	private ConcurrentHashMap<Thread, Transaction> transactionMap = new ConcurrentHashMap<>();

	public Transaction nuevaTransaccion() {
				
		if(transactionMap.get(Thread.currentThread()) != null){
			throw new RuntimeException("Este hilo ya tiene una transaccion en curso");
		}
		
		Transaction t = TransactionFactory.getInstance().nuevaTransaccion();
		transactionMap.put(Thread.currentThread(), t);
		
		return t;
	}

	
	public void eliminarTransaccion() {
		transactionMap.remove(Thread.currentThread());
	}

	public Transaction getTransaccion() {		
		Transaction t = transactionMap.get(Thread.currentThread());
		
		if(t == null){
			throw new RuntimeException("No existe transaccion para este hilo");
		}
		
		return t;
		
		
	}
}