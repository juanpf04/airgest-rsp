package negocio.factoria;

import negocio.departamento.SADepartamento;
import negocio.empleado.SAEmpleado;
import negocio.marca.SAMarca;
import negocio.producto.SAProducto;
import negocio.proveedor.SAProveedor;
import negocio.venta.SAVenta;

public interface FactoriaNegocio2 {

	public SAVenta crearSAVenta();

	public SAProveedor crearSAProveedor();

	public SAProducto crearSAProducto();

	public SAMarca crearSAMarca();

	public SADepartamento crearSADepartamento();

	public SAEmpleado crearSAEmpleado();
}
