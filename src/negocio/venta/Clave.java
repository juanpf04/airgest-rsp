package negocio.venta;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.MapsId;

@Embeddable
public class Clave implements Serializable {

	private static final long serialVersionUID = 0;

	private int venta;
	
	private int producto;
	
	public Clave() {
	}
	
	public Clave(int venta, int producto){
		this.venta = venta;
		this.producto = producto;
	}
	
	public int getVenta() {
		return venta;
	}

	public void setVenta(int venta) {
		this.venta = venta;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Clave))
			return false;
		Clave pk = (Clave) obj;
		if (!(venta == pk.venta))
			return false;
		if (!(producto == pk.producto))
			return false;
		return true;
	}

	
	public int hashCode() {
	    final int prime = 31; 
	    int result = 1;       
	    result = prime * result + venta;     
	    result = prime * result + producto; 
	    return result;        
	}
}