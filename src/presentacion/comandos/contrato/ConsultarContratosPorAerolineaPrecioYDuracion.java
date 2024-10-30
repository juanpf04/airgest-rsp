package presentacion.comandos.contrato;

import java.util.ArrayList;
import java.util.List;

import negocio.contrato.SAContrato;
import negocio.factoria.FactoriaNegocio;
import negocio.lineaContrato.TLineaContrato;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarContratosPorAerolineaPrecioYDuracion implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAContrato sc = fn.crearSAContrato();
		ArrayList<Integer> info = (ArrayList<Integer>) datos;
		List<TLineaContrato> contratos = sc.consultarContratoPorAerolinea((int)info.get(0), (double)info.get(1), (int)info.get(2));
		return new Contexto(Evento.VISTA_RESULTADO_CONSULTAR_CONTRATOS_POR_AEROLINEA_PRECIO_Y_DURACION, contratos);
	}

}
