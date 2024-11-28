/**
 * 
 */
package negocio.producto;


public class ValidadorProducto {

	public static boolean comprobarDatos(TProducto tProducto) {
		return tProducto.getId() > 0 && tProducto.getPrecio() > 0 && tProducto.getRef() > 0 && tProducto.getStock() >= 0;
	}
	
}