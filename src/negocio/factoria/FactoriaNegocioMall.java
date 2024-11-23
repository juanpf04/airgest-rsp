package negocio.factoria;

import negocio.departamento.SADepartamento;
import negocio.empleado.SAEmpleado;
import negocio.marca.SAMarca;
import negocio.producto.SAProducto;
import negocio.proveedor.SAProveedor;
import negocio.venta.SAVenta;

public abstract class FactoriaNegocioMall {
	private static FactoriaNegocioMall instancia;

	public static FactoriaNegocioMall getInstance() {
		if (instancia == null)
			instancia = new FactoriaNegocioMallImp();
		return instancia;
	}

	public abstract SAVenta crearSAVenta();

	public abstract SAProveedor crearSAProveedor();

	public abstract SAProducto crearSAProducto();

	public abstract SAMarca crearSAMarca();

	public abstract SADepartamento crearSADepartamento();

	public abstract SAEmpleado crearSAEmpleado();
}
