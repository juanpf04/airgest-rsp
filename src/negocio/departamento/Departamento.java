
package negocio.departamento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.util.List;
import negocio.empleado.Empleado;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.departamento.Departamento.findByid", query = "select obj from Departamento obj where :id = obj.id "),
		@NamedQuery(name = "negocio.departamento.Departamento.findBynombre", query = "select obj from Departamento obj where :nombre = obj.nombre "),
		@NamedQuery(name = "negocio.departamento.Departamento.findBysala", query = "select obj from Departamento obj where :sala = obj.sala "),
		@NamedQuery(name = "negocio.departamento.Departamento.findBysueldoHora", query = "select obj from Departamento obj where :sueldoHora = obj.sueldoHora "),
		@NamedQuery(name = "negocio.departamento.Departamento.findByempleados", query = "select obj from Departamento obj where :empleados MEMBER OF obj.empleados "),
		@NamedQuery(name = "negocio.departamento.Departamento.findByactivo", query = "select obj from Departamento obj where :activo = obj.activo "),
		@NamedQuery(name = "negocio.departamento.Departamento.findByversion", query = "select obj from Departamento obj where :version = obj.version "),
		@NamedQuery(name = "negocio.departamento.Departamento.findAll", query = "select obj from Departamento obj ")})

public class Departamento implements Serializable {

	private static final long serialVersionUID = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false)
	private String nombre;

	private int sala;

	private double sueldoHora;

	@OneToMany(mappedBy = "departamento")
	private List<Empleado> empleados;

	private boolean activo;

	@Version
	private int version;

	public Departamento() {

	}

	public Departamento(TDepartamento transfer) {
		this.id = transfer.getId();
		this.nombre = transfer.getNombre();
		this.sala = transfer.getSala();
		this.sueldoHora = transfer.getSueldoHora();
		this.activo = transfer.getActivo();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSala() {
		return this.sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public double getSueldoHora() {
		return this.sueldoHora;
	}

	public void setSueldoHora(double sueldoHora) {
		this.sueldoHora = sueldoHora;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public double calcularNomina() {
		// TODO Departamento::calcularNomina()
		return 0;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public TDepartamento toTransfer() {
		return new TDepartamento(this.id, this.nombre, this.sala, this.sueldoHora, this.activo);
	}
}