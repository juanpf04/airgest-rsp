
package negocio.venta;


public class ValidadorLineaVenta {
	public static boolean comprobarDatos(TLineaVenta lineaVenta){
		return lineaVenta.getCantidad() > 0 && lineaVenta.getIdVenta() > 0 && lineaVenta.getIdProducto() > 0 && lineaVenta.getPrecio() >= 0;
	}
}