package presentacion.controlador.comandos.proveedor;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarProveedorPorId implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProveedor sp = fn.crearSAProveedor();
		TProveedor proveedor = sp.consultarProveedorPorId((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_PROVEEDOR_POR_ID;
		return new Contexto (evento,proveedor);
	}
}