package negocio.producto;

public class TProducto {

	private int id;

	private String nombre;

	private int stock;

	private double precio;

	private int ref;

	private int idMarca;

	private boolean activo;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getRef() {
		return this.ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public TProducto() {
	}

	public TProducto(int id, String nombre, int stock, double precio, int ref, int idMarca, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
		this.ref = ref;
		this.idMarca = idMarca;
		this.activo = activo;
	}

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
}