package presentacion.comandos.contrato;

import java.util.List;

import negocio.contrato.SAContrato;
import negocio.contrato.TContrato;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarTodosContratos implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAContrato sc = fn.crearSAContrato();
		List<TContrato> contratos = sc.consultarTodosContratos();
		
		return new Contexto(Evento.VISTA_CONSULTAR_TODOS_CONTRATOS, contratos);
	}

}
