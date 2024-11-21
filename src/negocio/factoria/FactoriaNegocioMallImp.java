package negocio.factoria;

import negocio.departamento.SADepartamento;
import negocio.departamento.SADepartamentoImp;
import negocio.empleado.SAEmpleado;
import negocio.empleado.SAEmpleadoImp;
import negocio.marca.SAMarca;
import negocio.marca.SAMarcaImp;
import negocio.producto.SAProducto;
import negocio.producto.SAProductoImp;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.SAProveedorImp;
import negocio.venta.SAVenta;
import negocio.venta.SAVentaImp;

public class FactoriaNegocioMallImp extends FactoriaNegocioMall {

	@Override
	public SAVenta crearSAVenta() {
		return new SAVentaImp();
	}

	@Override
	public SAProveedor crearSAProveedor() {
		return new SAProveedorImp();
	}

	@Override
	public SAProducto crearSAProducto() {
		return new SAProductoImp();
	}

	@Override
	public SAMarca crearSAMarca() {
		return new SAMarcaImp();
	}

	@Override
	public SADepartamento crearSADepartamento() {
		return new SADepartamentoImp();
	}

	@Override
	public SAEmpleado crearSAEmpleado() {
		return new SAEmpleadoImp();
	}
}