package presentacion.controlador.comandos.departamento;

import presentacion.controlador.comandos.Comando;

import java.util.List;

import negocio.factoria.FactoriaNegocioMall;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarDepartamentos implements Comando {
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SADepartamento sd = fn.crearSADepartamento();
		List<TDepartamento> departamentos = sd.consultarDepartamentos();
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_DEPARTAMENTOS;
		return new Contexto(evento, departamentos);
	}
}