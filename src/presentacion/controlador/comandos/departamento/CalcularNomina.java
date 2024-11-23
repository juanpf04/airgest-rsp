package presentacion.controlador.comandos.departamento;

import negocio.departamento.SADepartamento;
import negocio.factoria.FactoriaNegocioMall;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class CalcularNomina implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SADepartamento sd = fn.crearSADepartamento();
		double nomina = sd.calcularNomina((int) datos);
		Evento evento = null;
		if (nomina != -1)
			evento = Evento.VISTA_EXITO_CALCULAR_NOMINA;
		else
			evento = Evento.VISTA_FALLO_CALCULAR_NOMINA;

		return new Contexto(evento, nomina);
	}
}