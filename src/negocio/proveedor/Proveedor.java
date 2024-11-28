package negocio.proveedor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import java.util.List;
import negocio.producto.Producto;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.proveedor.Proveedor.findByid", query = "select obj from Proveedor obj where :id = obj.id "),
		@NamedQuery(name = "negocio.proveedor.Proveedor.findByproductos", query = "select obj from Proveedor obj where :productos MEMBER OF obj.productos "),
		@NamedQuery(name = "negocio.proveedor.Proveedor.findBynombre", query = "select obj from Proveedor obj where :nombre = obj.nombre "),
		@NamedQuery(name = "negocio.proveedor.Proveedor.findByactivo", query = "select obj from Proveedor obj where :activo = obj.activo "),
		@NamedQuery(name = "negocio.proveedor.Proveedor.findByversion", query = "select obj from Proveedor obj where :version = obj.version ") })
public class Proveedor implements Serializable {

	private static final long serialVersionUID = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToMany
	private List<Producto> productos;

	@Column(unique = true, nullable = false)
	private String nombre;

	private boolean activo;

	@Version
	private int version;

	public Proveedor(TProveedor transfer) {
		this.id = transfer.getId();
		this.nombre = transfer.getNombre();
		this.activo = transfer.getActivo();
	}

	public Proveedor() {

	}

	public Integer getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public TProveedor toTransfer() {
		return new TProveedor(this.id, this.nombre, this.activo);
	}
}