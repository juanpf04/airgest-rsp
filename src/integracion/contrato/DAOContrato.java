package integracion.contrato;

import java.util.List;

import negocio.contrato.TContrato;

public interface DAOContrato {

	public int altaContrato(TContrato tContrato);

	public TContrato consultarContratoPorId(int id);

	public List<TContrato> consultarTodosContratos();

	public List<TContrato> consultarContratosPorAerolinea(int id_aerolinea);

	public boolean modificarContrato(TContrato tContrato);

}