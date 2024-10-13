package presentacion.comandos.contrato;

import java.time.LocalDate;
import java.util.List;

import negocio.contrato.SAContrato;
import negocio.contrato.TContrato;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarContratosPorAerolineaPrecioYDuracion implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAContrato sc = fn.crearSAContrato();
		List<Object> info = (List<Object>) datos;
		List<TContrato> contratos = sc.consultarContratoPorAerolinea((int)info[0], (double)info[1], (LocalDate)info[2]);
		return new Contexto(Evento.VISTA_RESULTADO_CONSULTAR_CONTRATOS_POR_AEROLINEA_PRECIO_Y_DURACION, contratos);
	}

}
