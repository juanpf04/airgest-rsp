
package negocio.venta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import negocio.empleado.Empleado;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.venta.Venta.findByid", query = "select obj from Venta obj where :id = obj.id "),
		@NamedQuery(name = "negocio.venta.Venta.findByempleado", query = "select obj from Venta obj where :empleado = obj.empleado "),
		@NamedQuery(name = "negocio.venta.Venta.findByprecio", query = "select obj from Venta obj where :precio = obj.precio "),
		@NamedQuery(name = "negocio.venta.Venta.findByfecha", query = "select obj from Venta obj where :fecha = obj.fecha "),
		@NamedQuery(name = "negocio.venta.Venta.findByversion", query = "select obj from Venta obj where :version = obj.version ") })
public class Venta implements Serializable {

	private static final long serialVersionUID = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Empleado empleado;

	private double precio;

	private String fecha;

	@Version
	private int version;

	public Venta() {

	}

	public Venta(TVenta transfer) {
		this.id = transfer.getId();
		this.precio = transfer.getPrecio();
		this.fecha = transfer.getFecha();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public TVenta toTransfer() {
		return new TVenta(this.id, this.precio, this.fecha, this.empleado.getId());
	}
}