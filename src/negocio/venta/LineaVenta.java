
package negocio.venta;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import negocio.producto.Producto;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.venta.LineaVenta.findByid", query = "select obj from LineaVenta obj where :id = obj.id "),
		@NamedQuery(name = "negocio.venta.LineaVenta.findByproducto", query = "select obj from LineaVenta obj where :producto = obj.producto "),
		@NamedQuery(name = "negocio.venta.LineaVenta.findByventa", query = "select obj from LineaVenta obj where :venta = obj.venta "),
		@NamedQuery(name = "negocio.venta.LineaVenta.findBycantidad", query = "select obj from LineaVenta obj where :cantidad = obj.cantidad "),
		@NamedQuery(name = "negocio.venta.LineaVenta.findByprecio", query = "select obj from LineaVenta obj where :precio = obj.precio "),
		@NamedQuery(name = "negocio.venta.LineaVenta.findByversion", query = "select obj from LineaVenta obj where :version = obj.version ") })
public class LineaVenta implements Serializable {

	private static final long serialVersionUID = 0;

	@EmbeddedId
	private Clave id;

	@ManyToOne
	@MapsId
	private Producto producto;

	@ManyToOne
	@MapsId
	private Venta venta;

	private int cantidad;

	private double precio;

	@Version
	private int version;

	public LineaVenta() {

	}

	public LineaVenta(TLineaVenta transfer) {
		// TODO Clave???
		this.cantidad = transfer.getCantidad();
		this.precio = transfer.getPrecio();
		//this.id = new Clave(transfer.getIdVenta(), transfer.getIdProducto());
	}

	public Clave getId() {
		return this.id;
	}

	public void setId(Clave id) {
		this.id = id;
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

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public TLineaVenta toTransfer() {
		return new TLineaVenta(this.id.getVenta(), this.id.getProducto(), this.cantidad, this.precio);
	}
}