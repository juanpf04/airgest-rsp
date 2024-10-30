package integracion.contrato;

import negocio.contrato.TContrato;
import java.util.List;

public interface DAOContrato {

	public int altaContrato(TContrato tContrato);

	public TContrato consultarContratoPorId(int id);

	public List<TContrato> consultarTodosContratos();

	public List<TContrato> consultarContratosPorAerolinea(int id_aerolinea);

	public boolean modificarContrato(TContrato tContrato);

}