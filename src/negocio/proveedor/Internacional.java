
package negocio.proveedor;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.proveedor.Internacional.findBypais", query = "select obj from Internacional obj where :pais = obj.pais "),
		@NamedQuery(name = "negocio.proveedor.Internacional.findByimpuesto", query = "select obj from Internacional obj where :impuesto = obj.impuesto ") })
public class Internacional extends Proveedor implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	private String pais;
	
	private Double impuesto;
	
	public Internacional() {
		
	}

	public Internacional(TInternacional transfer) {
		super(transfer);
		this.pais = transfer.getPais();
		this.impuesto = transfer.getImpuesto();
	}

	public String getPais() {
		return this.pais;
	}

	public double getImpuesto() {
		return this.impuesto;
	}

	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public TInternacional toTransfer(){
		return new TInternacional(getId(), getNombre(), getActivo(), this.pais, this.impuesto);
	}
}