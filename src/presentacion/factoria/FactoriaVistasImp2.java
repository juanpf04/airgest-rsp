package presentacion.factoria;

import presentacion.controlador.Evento;
import presentacion.Observador;
import presentacion.marca.*;
import presentacion.proveedor.*;
import presentacion.producto.*;
import presentacion.empleado.*;
import presentacion.departamento.*;
import presentacion.venta.*;

public class FactoriaVistasImp2 extends FactoriaVistasImp {

	@Override
	public Observador crearVista(Evento evento) {
		Observador vista = null;

		if (evento.ordinal() <= 170) {
			vista = super.crearVista(evento);
		} else {
			switch (evento) {
			// Proveedor
			case VISTA_PROVEEDOR:
				vista = new VistaProveedor();
				break;
			case VISTA_ALTA_PROVEEDOR:
				vista = new VistaAltaProveedor();
				break;
			case VISTA_EXITO_ALTA_PROVEEDOR:
				vista = new VistaExitoAltaProveedor();
				break;
			case VISTA_FALLO_ALTA_PROVEEDOR:
				vista = new VistaFalloAltaProveedor();
				break;
			case VISTA_BAJA_PROVEEDOR:
				vista = new VistaBajaProveedor();
				break;
			case VISTA_EXITO_BAJA_PROVEEDOR:
				vista = new VistaExitoBajaProveedor();
				break;
			case VISTA_FALLO_BAJA_PROVEEDOR:
				vista = new VistaFalloBajaProveedor();
				break;
			case VISTA_CONSULTAR_PROVEEDOR_POR_ID:
				vista = new VistaConsultarProveedorPorId();
				break;
			case VISTA_RESULTADO_CONSULTAR_PROVEEDOR_POR_ID:
				vista = new VistaResultadoConsultarProveedorPorId();
				break;
			case VISTA_RESULTADO_CONSULTAR_PROVEEDORES:
				vista = new VistaResultadoConsultarTodosProveedores();
				break;
			case VISTA_MODIFICAR_PROVEEDOR:
				vista = new VistaModificarProveedor();
				break;
			case VISTA_EXITO_MODIFICAR_PROVEEDOR:
				vista = new VistaExitoModificarProveedor();
				break;
			case VISTA_FALLO_MODIFICAR_PROVEEDOR:
				vista = new VistaFalloModificarProveedor();
				break;
			case VISTA_DESVINCULAR_PROVEEDOR:
				vista = new VistaDesvincularProveedorProducto();
				break;

			// Producto
			case VISTA_PRODUCTO:
				vista = new VistaProducto();
				break;
			case VISTA_ALTA_PRODUCTO:
				vista = new VistaAltaProducto();
				break;
			case VISTA_EXITO_ALTA_PRODUCTO:
				vista = new VistaExitoAltaProducto();
				break;
			case VISTA_FALLO_ALTA_PRODUCTO:
				vista = new VistaFalloAltaProducto();
				break;
			case VISTA_BAJA_PRODUCTO:
				vista = new VistaBajaProducto();
				break;
			case VISTA_EXITO_BAJA_PRODUCTO:
				vista = new VistaExitoBajaProducto();
				break;
			case VISTA_FALLO_BAJA_PRODUCTO:
				vista = new VistaFalloBajaProducto();
				break;
			case VISTA_CONSULTAR_PRODUCTO_POR_ID:
				vista = new VistaConsultarProductoPorId();
				break;
			case VISTA_RESULTADO_CONSULTAR_PRODUCTOS:
				vista = new VistaResultadoConsultarProductos();
				break;
			case VISTA_MODIFICAR_PRODUCTO:
				vista = new VistaModificarProducto();
				break;
			case VISTA_EXITO_MODIFICAR_PRODUCTO:
				vista = new VistaExitoModificarProducto();
				break;
			case VISTA_FALLO_MODIFICAR_PRODUCTO:
				vista = new VistaFalloModificarProducto();
				break;

			// Empleado
			case VISTA_EMPLEADO:
				vista = new VistaEmpleado();
				break;
			case VISTA_ALTA_EMPLEADO:
				vista = new VistaAltaEmpleado();
				break;
			case VISTA_EXITO_ALTA_EMPLEADO:
				vista = new VistaExitoAltaEmpleado();
				break;
			case VISTA_FALLO_ALTA_EMPLEADO:
				vista = new VistaFalloAltaEmpleado();
				break;
			case VISTA_BAJA_EMPLEADO:
				vista = new VistaBajaEmpleado();
				break;
			case VISTA_EXITO_BAJA_EMPLEADO:
				vista = new VistaExitoBajaEmpleado();
				break;
			case VISTA_FALLO_BAJA_EMPLEADO:
				vista = new VistaFalloBajaEmpleado();
				break;
			case VISTA_CONSULTAR_EMPLEADO_POR_ID:
				vista = new VistaConsultarEmpleadoPorId();
				break;
			case VISTA_CONSULTAR_EMPLEADOS_POR_DEPARTAMENTO:
				vista = new VistaConsultarEmpleadosDeUnDepartamento();
				break;
			case VISTA_RESULTADO_CONSULTAR_EMPLEADO_POR_ID:
				vista = new VistaResultadoConsultarEmpleadoPorId();
				break;
			case VISTA_RESULTADO_CONSULTAR_EMPLEADOS:
				vista = new VistaResultadoConsultarEmpleados();
				break;
			case VISTA_RESULTADO_CONSULTAR_EMPLEADOS_POR_DEPARTAMENTO:
				vista = new VistaResultadoConsultarEmpleadosDeUnDepartamento();
				break;
			case VISTA_MODIFICAR_EMPLEADO:
				vista = new VistaModificarEmpleado();
				break;
			case VISTA_MODIFICAR_EMPLEADO_ID:
				vista = new VistaModificarEmpleadoId();
				break;
			case VISTA_EXITO_MODIFICAR_EMPLEADO:
				vista = new VistaExitoModificarEmpleado();
				break;
			case VISTA_FALLO_MODIFICAR_EMPLEADO:
				vista = new VistaFalloModificarEmpleado();
				break;

			// Departamento
			case VISTA_DEPARTAMENTO:
				vista = new VistaDepartamento();
				break;
			case VISTA_ALTA_DEPARTAMENTO:
				vista = new VistaAltaDepartamento();
				break;
			case VISTA_EXITO_ALTA_DEPARTAMENTO:
				vista = new VistaExitoAltaDepartamento();
				break;
			case VISTA_FALLO_ALTA_DEPARTAMENTO:
				vista = new VistaFalloAltaDepartamento();
				break;
			case VISTA_BAJA_DEPARTAMENTO:
				vista = new VistaBajaDepartamento();
				break;
			case VISTA_EXITO_BAJA_DEPARTAMENTO:
				vista = new VistaExitoBajaDepartamento();
				break;
			case VISTA_FALLO_BAJA_DEPARTAMENTO:
				vista = new VistaFalloBajaDepartamento();
				break;
			case VISTA_CONSULTAR_DEPARTAMENTO_POR_ID:
				vista = new VistaConsultarDepartamentoPorId();
				break;
			case VISTA_RESULTADO_CONSULTAR_DEPARTAMENTOS:
				vista = new VistaResultadoConsultarDepartamentos();
				break;
			case VISTA_MODIFICAR_DEPARTAMENTO:
				vista = new VistaModificarDepartamento();
				break;
			case VISTA_EXITO_MODIFICAR_DEPARTAMENTO:
				vista = new VistaExitoModificarDepartamento();
				break;
			case VISTA_FALLO_MODIFICAR_DEPARTAMENTO:
				vista = new VistaFalloModificarDepartamento();
				break;
			case VISTA_CALCULAR_NOMINA:
				vista = new VistaCalcularNomina();
				break;
			case VISTA_EXITO_CALCULAR_NOMINA:
				vista = new VistaExitoCalcularNomina();
				break;
			case VISTA_FALLO_CALCULAR_NOMINA:
				vista = new VistaFalloCalcularNomina();
				break;

			// Marca
			case VISTA_MARCA:
				vista = new VistaMarca();
				break;
			case VISTA_ALTA_MARCA:
				vista = new VistaAltaMarca();
				break;
			case VISTA_EXITO_ALTA_MARCA:
				vista = new VistaExitoAltaMarca();
				break;
			case VISTA_FALLO_ALTA_MARCA:
				vista = new VistaFalloAltaMarca();
				break;
			case VISTA_BAJA_MARCA:
				vista = new VistaBajaMarca();
				break;
			case VISTA_EXITO_BAJA_MARCA:
				vista = new VistaExitoBajaMarca();
				break;
			case VISTA_FALLO_BAJA_MARCA:
				vista = new VistaFalloBajaMarca();
				break;
			case VISTA_CONSULTAR_MARCA_POR_ID:
				vista = new VistaConsultarMarcaPorId();
				break;
			case VISTA_RESULTADO_CONSULTAR_MARCAS:
				vista = new VistaResultadoConsultarMarcas();
				break;
			case VISTA_MODIFICAR_MARCA:
				vista = new VistaModificarMarca();
				break;
			case VISTA_EXITO_MODIFICAR_MARCA:
				vista = new VistaExitoModificarMarca();
				break;
			case VISTA_FALLO_MODIFICAR_MARCA:
				vista = new VistaFalloModificarMarca();
				break;

			// Venta
			case VISTA_VENTA:
				vista = new VistaVenta();
				break;
			case VISTA_CARRITO_VENTA:
				vista = new VistaCarrito();
				break;
			case VISTA_ANYADIR_PRODUCTO:
				vista = new VistaAñadirProducto();
				break;
			case VISTA_ELIMINAR_PRODUCTO:
				vista = new VistaEliminarProducto();
				break;
			case VISTA_EXITO_CERRAR_VENTA:
				vista = new VistaExitoCerrarVenta();
				break;
			case VISTA_FALLO_CERRAR_VENTA:
				vista = new VistaFalloCerrarVenta();
				break;
			case VISTA_CONSULTAR_VENTA_POR_ID:
				vista = new VistaConsultarVentaPorId();
				break;
			case VISTA_RESULTADO_CONSULTAR_VENTAS:
				vista = new VistaResultadoConsultarVentas();
				break;
			case VISTA_DEVOLUCION_VENTA:
				vista = new VistaDevolucion();
				break;
			case VISTA_EXITO_DEVOLUCION_VENTA:
				vista = new VistaExitoDevolucion();
				break;
			case VISTA_FALLO_DEVOLUCION_VENTA:
				vista = new VistaFalloDevolucion();
				break;

			default:
				break;
			}
		}

		return vista;
	}
}