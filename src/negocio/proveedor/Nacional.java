
package negocio.proveedor;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "negocio.proveedor.Nacional.findBycodigoPostal", query = "select obj from Nacional obj where :codigoPostal = obj.codigoPostal ")
public class Nacional extends Proveedor implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	private int codigoPostal;
	
	public Nacional() {
	
	}

	public Nacional(TNacional transfer) {
		super(transfer);
		this.codigoPostal = transfer.getCodigoPostal();
	}

	public int getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(int cp) {
		this.codigoPostal = cp;
	}

	public TNacional toTransfer(){
		return new TNacional(getId(), getNombre(), getActivo(), this.codigoPostal);
	}
	
	
}