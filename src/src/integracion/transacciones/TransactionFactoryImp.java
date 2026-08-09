
package integracion.transacciones;

public class TransactionFactoryImp extends TransactionFactory {
	public Transaction nuevaTransaccion() {
		return new TransactionMySQL();
	}
}