package presentacion.factoria;

import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;
import presentacion.controlador.comandos.marca.*;
import presentacion.controlador.comandos.proveedor.*;
import presentacion.controlador.comandos.producto.*;
import presentacion.controlador.comandos.empleado.*;
import presentacion.controlador.comandos.departamento.*;
import presentacion.controlador.comandos.venta.*;

public class FactoriaComandosImp2 extends FactoriaComandosImp {

	@Override
	public Comando crearComando(Evento evento) {
		Comando comando = null;

		if (evento.ordinal() <= 170) { // si es un comando de la parte DAO
			comando = super.crearComando(evento);
		} else {
			switch (evento) {
			// Marca
			case ALTA_MARCA:
				comando = new AltaMarca();
				break;
			case BAJA_MARCA:
				comando = new BajaMarca();
				break;
			case CONSULTAR_MARCA_POR_ID:
				comando = new ConsultarMarcaPorId();
				break;
			case CONSULTAR_MARCAS:
				comando = new ConsultarMarcas();
				break;
			case MODIFICAR_MARCA:
				comando = new ModificarMarca();
				break;
			case MODIFICAR_MARCA_ID:
				comando = new ModificarMarcaId();
				break;

			// Proveedor
			case ALTA_PROVEEDOR:
				comando = new AltaProveedor();
				break;
			case BAJA_PROVEEDOR:
				comando = new BajaProveedor();
				break;
			case CONSULTAR_PROVEEDOR_POR_ID:
				comando = new ConsultarProveedorPorId();
				break;
			case CONSULTAR_PROVEEDORES:
				comando = new ConsultarProveedores();
				break;
			case MODIFICAR_PROVEEDOR:
				comando = new ModificarProveedor();
				break;
			case VINCULAR_PROVEEDOR:
				comando = new VincularProveedorProducto();
				break;
			case DESVINCULAR_PROVEEDOR:
				comando = new DesvincularProveedorProducto();
				break;
			case CONSULTAR_PROVEEDORES_POR_PRODUCTO:
				comando = new ConsultarProveedoresPorProducto();
				break;
			case MODIFICAR_PROVEEDOR_ID:
				comando = new ModificarProveedorId();
				break;

			// Producto
			case ALTA_PRODUCTO:
				comando = new AltaProducto();
				break;
			case BAJA_PRODUCTO:
				comando = new BajaProducto();
				break;
			case CONSULTAR_PRODUCTO_POR_ID:
				comando = new ConsultarProductoPorId();
				break;
			case CONSULTAR_PRODUCTOS:
				comando = new ConsultarProductos();
				break;
			case MODIFICAR_PRODUCTO:
				comando = new ModificarProducto();
				break;
			case CONSULTAR_PRODUCTOS_POR_MARCA:
				comando = new ConsultarProductoPorMarca();
				break;
			case CONSULTAR_PRODUCTOS_POR_PROVEEDOR:
				comando = new ConsultarProductoPorProveedor();
				break;
			case MODIFICAR_PRODUCTO_ID:
				comando = new ModificarProductoId();
				break;

			// Empleado
			case ALTA_EMPLEADO:
				comando = new AltaEmpleado();
				break;
			case BAJA_EMPLEADO:
				comando = new BajaEmpleado();
				break;
			case CONSULTAR_EMPLEADO_POR_ID:
				comando = new ConsultarEmpleadoPorId();
				break;
			case CONSULTAR_EMPLEADOS:
				comando = new ConsultarEmpleados();
				break;
			case CONSULTAR_EMPLEADOS_POR_DEPARTAMENTO:
				comando = new ConsultarEmpleadosPorDepartamento();
				break;
			case MODIFICAR_EMPLEADO:
				comando = new ModificarEmpleado();
				break;
			case MODIFICAR_EMPLEADO_ID:
				comando = new ModificarEmpleadoId();
				break;

			// Departamento
			case ALTA_DEPARTAMENTO:
				comando = new AltaDepartamento();
				break;
			case BAJA_DEPARTAMENTO:
				comando = new BajaDepartamento();
				break;
			case CONSULTAR_DEPARTAMENTO_POR_ID:
				comando = new ConsultarDepartamentoPorId();
				break;
			case CONSULTAR_DEPARTAMENTOS:
				comando = new ConsultarDepartamentos();
				break;
			case MODIFICAR_DEPARTAMENTO:
				comando = new ModificarDepartamento();
				break;
			case MODIFICAR_DEPARTAMENTO_ID:
				comando = new ModificarDepartamentoId();
				break;
			case CALCULAR_NOMINA:
				comando = new CalcularNomina();
				break;

			// Venta
			case ABRIR_VENTA:
				comando = new AbrirCarrito();
				break;
			case CERRAR_VENTA:
				comando = new CerrarVenta();
				break;
			case CONSULTAR_VENTA_POR_ID:
				comando = new ConsultarVentaPorId();
				break;
			case CONSULTAR_VENTAS:
				comando = new ConsultarVentas();
				break;
			case CONSULTAR_VENTAS_POR_EMPLEADO:
				comando = new ConsultarVentasPorEmpleado();
				break;
			case DEVOLUCION_VENTA:
				comando = new Devolucion();
				break;

			default:
				break;
			}
		}

		return comando;
	}
}