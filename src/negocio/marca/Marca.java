package negocio.marca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.List;
import negocio.producto.Producto;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.marca.Marca.findByid", query = "select obj from Marca obj where :id = obj.id "),
		@NamedQuery(name = "negocio.marca.Marca.findByproductos", query = "select obj from Marca obj where :productos MEMBER OF obj.productos "),
		@NamedQuery(name = "negocio.marca.Marca.findBynombre", query = "select obj from Marca obj where :nombre = obj.nombre "),
		@NamedQuery(name = "negocio.marca.Marca.findByorigen", query = "select obj from Marca obj where :origen = obj.origen "),
		@NamedQuery(name = "negocio.marca.Marca.findByactivo", query = "select obj from Marca obj where :activo = obj.activo "),
		@NamedQuery(name = "negocio.marca.Marca.findByversion", query = "select obj from Marca obj where :version = obj.version ") })
public class Marca implements Serializable {

	private static final long serialVersionUID = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "marca")
	private List<Producto> productos;

	@Column(unique = true, nullable = false)
	private String nombre;

	private String origen;

	private boolean activo;

	@Version
	private int version;

	public Marca() {
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

	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param productos
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setProductos(Producto... productos) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public TMarca toTransfer() {
		return new TMarca(this.id, this.nombre, this.origen, this.activo);
	}

	public Marca(TMarca transfer) {
		this.id = transfer.getId();
		this.nombre = transfer.getNombre();
		this.origen = transfer.getOrigen();
		this.activo = transfer.getActivo();
	}
}