package presentacion.controlador.comandos.departamento;

import presentacion.controlador.comandos.Comando;

import java.util.ArrayList;

import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocioMall;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarDepartamentoId implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SADepartamento sd = fn.crearSADepartamento();
		TDepartamento tdep = sd.consultarDepartamentoPorId((int) datos);
		Evento evento;
		if(tdep != null && tdep.getActivo()) {
			evento = Evento.VISTA_MODIFICAR_DEPARTAMENTO;
		}else {
			evento = Evento.VISTA_FALLO_MODIFICAR_DEPARTAMENTO;
		}
		return new Contexto(evento, tdep);
	}
}