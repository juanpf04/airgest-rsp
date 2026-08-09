package negocio.venta;

public class TLineaVenta {

	private int idVenta;

	private int idProducto;

	private int cantidad;

	private double precio;

	public TLineaVenta(int idVenta, int idProducto, int cantidad, double precio) {
		this.idVenta = idVenta;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public TLineaVenta() {
	}

	public int getIdVenta() {
		return this.idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String toString() {
		return " Id venta: " + this.idVenta + "\n Id producto: " + this.idProducto + "\n Cantidad: " + this.cantidad
				+ "\n Precio: " + this.precio;
	}
}