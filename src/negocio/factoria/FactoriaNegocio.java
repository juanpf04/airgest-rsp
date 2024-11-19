
package negocio.factoria;

import negocio.aerolinea.SAAerolinea;
import negocio.avion.SAAvion;
import negocio.contrato.SAContrato;
import negocio.departamento.SADepartamento;
import negocio.empleado.SAEmpleado;
import negocio.hangar.SAHangar;
import negocio.marca.SAMarca;
import negocio.modelo.SAModelo;
import negocio.personal.SAPersonal;
import negocio.producto.SAProducto;
import negocio.proveedor.SAProveedor;
import negocio.venta.SAVenta;

public abstract class FactoriaNegocio {

	private static FactoriaNegocio instancia;

	public synchronized static FactoriaNegocio getInstance() {
		if (instancia == null)
			instancia = new FactoriaNegocioImp2();
		return instancia;
	}

	public abstract SAModelo crearSAModelo();

	public abstract SAHangar crearSAHangar();

	public abstract SAAvion crearSAAvion();

	public abstract SAAerolinea crearSAAerolinea();

	public abstract SAPersonal crearSAPersonal();

	public abstract SAContrato crearSAContrato();

	public abstract SAVenta crearSAVenta();

	public abstract SAProveedor crearSAProveedor();

	public abstract SAProducto crearSAProducto();

	public abstract SAMarca crearSAMarca();

	public abstract SADepartamento crearSADepartamento();

	public abstract SAEmpleado crearSAEmpleado();
}