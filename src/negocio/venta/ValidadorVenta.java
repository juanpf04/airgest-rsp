package negocio.venta;

public class ValidadorVenta {
	public static boolean comprobarDatos(TVenta tVenta){
		return tVenta.getIdEmpleado() > 0 && tVenta.getPrecio() > 0;
	}
}
