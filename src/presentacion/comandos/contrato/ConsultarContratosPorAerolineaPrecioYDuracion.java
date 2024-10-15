package presentacion.comandos.contrato;

import java.util.List;

import negocio.contrato.SAContrato;
import negocio.contrato.TInfoContrato;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarContratosPorAerolineaPrecioYDuracion implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAContrato sc = fn.crearSAContrato();
		Object[] info = (Object[]) datos;
		List<TInfoContrato> contratos = sc.consultarContratoPorAerolinea((int)info[0], (double)info[1], (int)info[2]);
		return new Contexto(Evento.VISTA_RESULTADO_CONSULTAR_CONTRATOS_POR_AEROLINEA_PRECIO_Y_DURACION, contratos);
	}

}
