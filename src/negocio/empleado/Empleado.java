package negocio.empleado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import negocio.departamento.Departamento;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import java.util.List;
import negocio.venta.Venta;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.empleado.Empleado.findByid", query = "select obj from Empleado obj where :id = obj.id "),
		@NamedQuery(name = "negocio.empleado.Empleado.findBydepartamento", query = "select obj from Empleado obj where :departamento = obj.departamento "),
		@NamedQuery(name = "negocio.empleado.Empleado.findByventas", query = "select obj from Empleado obj where :ventas MEMBER OF obj.ventas "),
		@NamedQuery(name = "negocio.empleado.Empleado.findBytag", query = "select obj from Empleado obj where :tag = obj.tag "),
		@NamedQuery(name = "negocio.empleado.Empleado.findByhorasMensuales", query = "select obj from Empleado obj where :horasMensuales = obj.horasMensuales "),
		@NamedQuery(name = "negocio.empleado.Empleado.findByactivo", query = "select obj from Empleado obj where :activo = obj.activo "),
		@NamedQuery(name = "negocio.empleado.Empleado.findByversion", query = "select obj from Empleado obj where :version = obj.version "),
		@NamedQuery(name = "negocio.empleado.Empleado.findAll", query = "select obj from Empleado obj") })
public abstract class Empleado implements Serializable {

	private static final long serialVersionUID = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Departamento departamento;

	@OneToMany(mappedBy = "empleado")
	private List<Venta> ventas;

	@Column(unique = true, nullable = false)
	private int tag;

	private int horasMensuales;

	private boolean activo;

	@Version
	private int version;

	public Empleado() {
	}

	public Empleado(TEmpleado transfer) {
		this.id = transfer.getId();
		this.tag = transfer.getTag();
		this.horasMensuales = transfer.getHorasMensuales();
		this.activo = transfer.getActivo();
	}

	public Integer getId() {
		return this.id;
	}

	public int getTag() {
		return this.tag;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public int getHorasMensuales() {
		return this.horasMensuales;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public void setHorasMensuales(int horasMensuales) {
		this.horasMensuales = horasMensuales;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public TEmpleado toTransfer() {
		return new TEmpleado(this.id, this.tag, this.horasMensuales, this.departamento.getId(), this.activo);
	}

	public abstract double calcularSueldo();
}