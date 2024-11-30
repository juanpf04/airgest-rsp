package presentacion.controlador.comandos.empleado;

import java.util.ArrayList;

import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personal.TPersonal;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ModificarEmpleadoId implements Comando{
	
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAPersonal sp = fn.crearSAPersonal();
		TPersonal personal = sp.consultarPersonalPorId((int) datos);
		Evento evento;
		ArrayList<Object> lista = new ArrayList<Object>();
		lista.add(personal);
		lista.add(null);
		if(personal != null && personal.getActivo()) {
			evento = Evento.VISTA_MODIFICAR_EMPLEADO;
		}else {
			evento = Evento.VISTA_FALLO_MODIFICAR_EMPLEADO;
		}
		return new Contexto(evento, lista);
	}
}

	