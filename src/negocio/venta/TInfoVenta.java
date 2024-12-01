
package negocio.venta;

import java.util.List;
import negocio.empleado.TEmpleado;
import negocio.lineaContrato.TLineaContrato;

import java.util.HashMap;
import negocio.producto.TProducto;

public class TInfoVenta {
	
	private TVenta venta;
	
	private List<TLineaVenta> lineasVenta;
	
	private TEmpleado empleado;
	
	private HashMap<Integer, TProducto> productos;

	public TInfoVenta(TVenta venta, TEmpleado empleado, List<TLineaVenta> lineasVenta, HashMap<Integer, TProducto> productos) {
		this.venta = venta;
		this.empleado = empleado;
		this.lineasVenta = lineasVenta;
		this.productos = productos;
	}

	public TInfoVenta() {
		
	}

	public TVenta getVenta() {
		return this.venta;
	}

	public void setVenta(TVenta venta) {
		this.venta = venta;
	}

	public TEmpleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(TEmpleado empleado) {
		this.empleado = empleado;
	}

	public List<TLineaVenta> getLineasVenta() {
		return this.lineasVenta;
	}

	public void setLineasVenta(List<TLineaVenta> lineasVenta) {
		this.lineasVenta = lineasVenta;
	}

	public HashMap<Integer, TProducto> getProductos() {
		return this.productos;
	}

	public void setProductos(HashMap<Integer, TProducto> productos) {
		this.productos = productos;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Información de la venta:\n");
		sb.append(this.venta + "\n\n");
		sb.append("Información del empleado:\n");
		sb.append(this.empleado + "\n\n");
		sb.append("Información de las líneas y sus productos:\n");

		for (TLineaVenta linea : this.lineasVenta) {
			sb.append("Información de la línea:\n");
			sb.append(linea + "\n");
			sb.append("Información del producto:\n");
			sb.append(this.productos.get(linea.getIdProducto()) + "\n");
		}

		return sb.toString();
	}
}