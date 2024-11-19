package negocio.venta;

import java.io.Serializable;

import javax.persistence.Embeddable;

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

	//private UUID uuid;

	public int hashCode() {
//		final int prime = 31;
//		int hash = 17;
//		hash = hash * prime + venta;
//		hash = hash * prime + producto;
//		if (hash == 17) {
//			if (uuid == null) {
//				uuid = UUID.randomUUID();
//			}
//			hash = uuid.hashCode();
//		}
//		return hash;
		return 4;
	}
}