package negocio.empleado;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.empleado.Gerente.findBydespacho", query = "select obj from Gerente obj where :despacho = obj.despacho "),
		@NamedQuery(name = "negocio.empleado.Gerente.findByhorasExtra", query = "select obj from Gerente obj where :horasExtra = obj.horasExtra ") })
public class Gerente extends Empleado implements Serializable {

	private static final long serialVersionUID = 0;

	private int despacho;

	private int horasExtra;

	public Gerente() {
	}

	public Gerente(TGerente transfer) {
		super(transfer);
		this.despacho = transfer.getDespacho();
		this.horasExtra = transfer.getHorasExtra();
	}

	public int getDespacho() {
		return this.getDespacho();
	}

	public int getHorasExtra() {
		return this.horasExtra;
	}

	public void setDespacho(int despacho) {
		this.despacho = despacho;
	}

	public void setHorasExtra(int horasExtra) {
		this.horasExtra = horasExtra;
	}

	public TGerente toTransfer() {
		return new TGerente(getId(), getTag(), getHorasMensuales(), getDepartamento().getId(), getActivo(),
				this.despacho, this.horasExtra);
	}

	@Override
	public double calcularSueldo() {
		double sueldoDepartamento = super.getDepartamento().getSueldoHora();
		return sueldoDepartamento * super.getHorasMensuales() + (sueldoDepartamento * 1.2) * this.horasExtra;
	}
}