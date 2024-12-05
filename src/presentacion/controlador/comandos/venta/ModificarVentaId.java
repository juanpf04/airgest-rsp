package presentacion.controlador.comandos.venta;

import negocio.factoria.FactoriaNegocioMall;
import negocio.venta.SAVenta;
import negocio.venta.TInfoVenta;
import negocio.venta.TVenta;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ModificarVentaId implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {
		SAVenta sv = FactoriaNegocioMall.getInstance().crearSAVenta();
		TInfoVenta venta = sv.consultarVentaPorId((int) datos);
		TVenta v = null;
		Evento evento;
		
		if (venta != null){
			evento = Evento.VISTA_MODIFICAR_VENTA;
			v = venta.getVenta();
		} else{
			evento = Evento.VISTA_FALLO_MODIFICAR_VENTA;
		}
		
		return new Contexto(evento, v);
	}

}
