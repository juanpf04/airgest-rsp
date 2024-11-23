package presentacion.controlador.comandos.departamento;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarDepartamento implements Comando {
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SADepartamento sd = fn.crearSADepartamento();
		boolean exito = sd.modificarDepartamento((TDepartamento) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_MODIFICAR_DEPARTAMENTO;
		} else {
			evento = Evento.VISTA_FALLO_MODIFICAR_DEPARTAMENTO;
		}
		return new Contexto(evento, exito);
	}
}