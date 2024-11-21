
package negocio.producto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import negocio.marca.Marca;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import java.util.List;
import negocio.proveedor.Proveedor;
import javax.persistence.ManyToMany;


@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.producto.Producto.findByid", query = "select obj from Producto obj where :id = obj.id "),
		@NamedQuery(name = "negocio.producto.Producto.findBymarca", query = "select obj from Producto obj where :marca = obj.marca "),
		@NamedQuery(name = "negocio.producto.Producto.findByproveedores", query = "select obj from Producto obj where :proveedores MEMBER OF obj.proveedores "),
		@NamedQuery(name = "negocio.producto.Producto.findBynombre", query = "select obj from Producto obj where :nombre = obj.nombre "),
		@NamedQuery(name = "negocio.producto.Producto.findBystock", query = "select obj from Producto obj where :stock = obj.stock "),
		@NamedQuery(name = "negocio.producto.Producto.findByprecio", query = "select obj from Producto obj where :precio = obj.precio "),
		@NamedQuery(name = "negocio.producto.Producto.findByref", query = "select obj from Producto obj where :ref = obj.ref "),
		@NamedQuery(name = "negocio.producto.Producto.findByactivo", query = "select obj from Producto obj where :activo = obj.activo "),
		@NamedQuery(name = "negocio.producto.Producto.findByversion", query = "select obj from Producto obj where :version = obj.version ") })
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Marca marca;
	
	@ManyToMany
	private List<Proveedor> proveedores;
	
	private String nombre;
	
	private int stock;
	
	private double precio;
	
	@Column(unique = true, nullable = false)
	private int ref;
	
	private boolean activo;
	
	@Version
	private int version;

	
	public Producto() {
	}

	
	public Integer getId() {
		return this.id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public int getStock() {
		return stock;
	}

	
	public void setStock(int stock) {
		this.stock = stock;
	}

	
	public double getPrecio() {
		return precio;
	}

	
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	
	public int getRef() {
		return ref;
	}

	
	public void setRef(int ref) {
		this.ref = ref;
	}

	
	public boolean getActivo() {
		return activo;
	}

	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
	public Marca getMarca() {
		return marca;
	}

	
	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	
	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	
	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	
	public TProducto toTransfer() {
		return new TProducto(id, nombre, stock, precio, ref, activo);
	}

	
	public Producto(TProducto transfer) {
		this.id = transfer.getId();
		this.nombre = transfer.getNombre();
		this.precio = transfer.getPrecio();
		this.ref = transfer.getRef();
		this.stock = transfer.getStock();
		this.activo = transfer.getActivo();
	}
}