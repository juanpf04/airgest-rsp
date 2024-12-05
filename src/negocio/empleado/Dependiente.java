
package negocio.empleado;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.empleado.Dependiente.findByseccion", query = "select obj from Dependiente obj where :seccion = obj.seccion "),
		@NamedQuery(name = "negocio.empleado.Dependiente.findBynoches", query = "select obj from Dependiente obj where :noches = obj.noches ") })
public class Dependiente extends Empleado implements Serializable {

	private static final long serialVersionUID = 0;

	private int seccion;

	private boolean noches;

	public Dependiente() {

	}

	public Dependiente(TDependiente transfer) {
		super(transfer);
		this.seccion = transfer.getSeccion();
		this.noches = transfer.getNoches();
	}

	public int getSeccion() {
		return this.seccion;
	}

	public boolean getNoches() {
		return this.noches;
	}

	public void setSeccion(int seccion) {
		this.seccion = seccion;
	}

	public void setNoches(boolean noches) {
		this.noches = noches;
	}

	public TDependiente toTransfer() {
		return new TDependiente(getId(), getTag(), getHorasMensuales(), getDepartamento().getId(), getActivo(),
				this.seccion, this.noches);
	}
	
	@Override
	public double calcularSueldo(){
		double sueldoDepartamento = super.getDepartamento().getSueldoHora();
		double sueldo = sueldoDepartamento * super.getHorasMensuales();
		if(this.noches) sueldo += (sueldo * 1.4); 
		return sueldo;
	}
}