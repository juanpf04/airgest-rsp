package integracion.contrato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import integracion.Queries;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.contrato.TContrato;

public class DAOContratoImp implements DAOContrato {

	public int altaContrato(TContrato tContrato) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.altaContrato, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, tContrato.getPrecio());
			ps.setInt(2, tContrato.getIdAerolinea());

			int filas = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			int id = filas == 1 && rs.next() ? rs.getInt(1) : -1;

			rs.close();
			ps.close();

			return id;

		} catch (Exception e) {
			return -1;
		}
	}

	public TContrato consultarContratoPorId(int id) {

		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Queries.consultarContratoPorId);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			TContrato tc = null;
			if (rs.next())
				tc = new TContrato(rs.getInt("id"), rs.getInt("id_aerolinea"), rs.getDouble("precio"));

			rs.close();
			ps.close();

			return tc;

		} catch (Exception e) {
			return null;
		}
	}

	public List<TContrato> consultarTodosContratos() {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.consultarTodosContratos);

			ResultSet rs = ps.executeQuery();
			List<TContrato> lista = new ArrayList<>();

			while (rs.next()) {
				lista.add(new TContrato(rs.getInt("id"), rs.getInt("id_aerolinea"), rs.getDouble("precio")));
			}

			rs.close();
			ps.close();

			return lista;

		} catch (Exception e) {
			return new ArrayList<TContrato>();
		}
	}

	public List<TContrato> consultarContratosPorAerolinea(int id_aerolinea) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.consultarContratosPorAerolinea);
			ps.setInt(1, id_aerolinea);

			ResultSet rs = ps.executeQuery();
			List<TContrato> lista = new ArrayList<>();

			while (rs.next()) {
				lista.add(new TContrato(rs.getInt("id"), rs.getInt("id_aerolinea"), rs.getDouble("precio")));
			}

			rs.close();
			ps.close();

			return lista;

		} catch (Exception e) {
			return new ArrayList<TContrato>();
		}
	}

	public boolean modificarContrato(TContrato tContrato) {

		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.modificarContrato);
			ps.setDouble(1, tContrato.getPrecio());
			ps.setInt(2, tContrato.getIdAerolinea());
			ps.setInt(3, tContrato.getId());

			int filas = ps.executeUpdate();
			boolean modificado = filas == 1;

			ps.close();

			return modificado;
		} catch (Exception e) {
			return false;
		}
	}

}