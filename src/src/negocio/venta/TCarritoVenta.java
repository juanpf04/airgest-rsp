package negocio.venta;

import java.util.ArrayList;
import java.util.List;

public class TCarritoVenta {

	private int idEmpleado;

	private List<TLineaVenta> lineasVenta;

	private TVenta tVenta;

	public TCarritoVenta(int idEmpleado) {
		this.idEmpleado = idEmpleado;
		this.lineasVenta = new ArrayList<TLineaVenta>();
		this.tVenta = new TVenta(idEmpleado);
	}

	public TCarritoVenta() {
	}

	public int getIdEmpleado() {
		return this.idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public TVenta getVenta() {
		return this.tVenta;
	}

	public void setVenta(TVenta venta) {
		this.tVenta = venta;
	}

	public void anyadirLinea(TLineaVenta linea) {
		this.lineasVenta.add(linea);
	}

	public void eliminarLinea(int idProducto) {
		this.lineasVenta.removeIf(tLineaVenta -> tLineaVenta.getIdProducto() == idProducto);
	}

	public List<TLineaVenta> getLineasVenta() {
		return this.lineasVenta;
	}
}