package negocio.venta;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.MapsId;

@Embeddable
public class Clave implements Serializable {

	private static final long serialVersionUID = 0;

	public Clave() {
	}

	private int venta;
	
	private int producto;

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