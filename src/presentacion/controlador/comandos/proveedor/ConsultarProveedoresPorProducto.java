package presentacion.controlador.comandos.proveedor;

import presentacion.controlador.comandos.Comando;

import java.util.List;

import negocio.factoria.FactoriaNegocioMall;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarProveedoresPorProducto implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAProveedor sp = fn.crearSAProveedor();
		List<TProveedor> proveedores = sp.consultarProveedoresPorProducto((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_PROVEEDORES_POR_PRODUCTO;
		return new Contexto(evento, proveedores);
	}}