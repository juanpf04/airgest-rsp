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
			//Proveedor
			case VISTA_PROVEEDOR:
				vista = new VistaProveedor();
				break;
				
			//Producto
				
				
			//Empleado
				
				
			//Departamento
				
			
			//Marca
				
				
			//Venta

			default:
				break;
			}
		}

		return vista;
	}
}