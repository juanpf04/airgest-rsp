package presentacion.controlador.comandos.empleado;

import java.util.ArrayList;

import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.factoria.FactoriaNegocioMall;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ModificarEmpleadoId implements Comando{
	
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAEmpleado sp = fn.crearSAEmpleado();
		TEmpleado empleado = sp.consultarEmpleadoPorId((int) datos);
		Evento evento;
		ArrayList<Object> lista = new ArrayList<Object>();
		lista.add(empleado);
		lista.add(null);
		if(empleado != null && empleado.getActivo()) {
			evento = Evento.VISTA_MODIFICAR_EMPLEADO;
		}else {
			evento = Evento.VISTA_FALLO_MODIFICAR_EMPLEADO;
		}
		return new Contexto(evento, lista);
	}
}

	