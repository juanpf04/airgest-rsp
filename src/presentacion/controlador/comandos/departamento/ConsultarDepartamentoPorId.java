/**
 * 
 */
package presentacion.controlador.comandos.departamento;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarDepartamentoPorId implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SADepartamento sd = fn.crearSADepartamento();
		TDepartamento departamento = sd.consultarDepartamentoPorId((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_DEPARTAMENTO_POR_ID;

		return new Contexto(evento, departamento);
	}
}