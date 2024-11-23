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

		if (evento.ordinal() <= 170) {
			comando = super.crearComando(evento);
		} else {
			switch (evento) {
			case ALTA_MARCA:
				comando = new AltaMarca();
				break;
			case BAJA_MARCA:
				comando = new BajaMarca();
				break;

			case ALTA_PROVEEDOR:
				comando = new AltaProveedor();
				break;
			case BAJA_PROVEEDOR:
				comando = new BajaProveedor();
				break;

			case ALTA_PRODUCTO:
				comando = new AltaProducto();
				break;
			case BAJA_PRODUCTO:
				comando = new BajaProducto();
				break;

			case ALTA_EMPLEADO:
				comando = new AltaEmpleado();
				break;
			case BAJA_EMPLEADO:
				comando = new BajaEmpleado();
				break;

			case ALTA_DEPARTAMENTO:
				comando = new AltaDepartamento();
				break;
			case BAJA_DEPARTAMENTO:
				comando = new BajaDepartamento();
				break;

			case ABRIR_VENTA:
				comando = new AbrirCarrito();
				break;
			case CERRAR_VENTA:
				comando = new CerrarVenta();
				break;

			default:
				break;
			}
		}

		return comando;
	}
}