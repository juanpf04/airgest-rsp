package integracion.lineaContrato;

import negocio.lineaContrato.TLineaContrato;
import java.util.List;

public interface DAOLineaContrato {

	public boolean altaLineaContrato(TLineaContrato tLineaContrato);

	public boolean modificarLineaContrato(TLineaContrato tLineaContrato);

	public List<TLineaContrato> consultarLineasPorContrato(int id_contrato);

	public List<TLineaContrato> consultarLineasPorHangar(int id_hangar);

	public TLineaContrato consultarLineaContrato(int id_contrato, int id_hangar);
	
	public List<TLineaContrato> consultarContratoPorAerolinea(int id_aerolinea, double precio, int dias);

}