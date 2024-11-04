package integracion.lineaContrato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import integracion.Queries;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.lineaContrato.TLineaContrato;

public class DAOLineaContratoImp implements DAOLineaContrato {

	@Override
	public boolean altaLineaContrato(TLineaContrato tLineaContrato) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.altaLineaContrato, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tLineaContrato.getIdHangar());
			ps.setInt(2, tLineaContrato.getIdContrato());
			ps.setString(3, tLineaContrato.getFechaIni());
			ps.setString(4,  tLineaContrato.getFechaFin());
			ps.setDouble(5,  tLineaContrato.getPrecio());
			
			int filas = ps.executeUpdate();
			
			boolean ok = filas == 1;
			
			ps.close();
			
			return ok;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean modificarLineaContrato(TLineaContrato tLineaContrato) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.modificarLineaContrato);
			ps.setString(1, tLineaContrato.getFechaIni());
			ps.setString(2, tLineaContrato.getFechaFin());
			ps.setDouble(3, tLineaContrato.getPrecio());
			ps.setInt(4, tLineaContrato.getIdHangar());
			ps.setInt(5, tLineaContrato.getIdContrato());
			
			int filas = ps.executeUpdate();
			boolean modificado = filas == 1;
			
			ps.close();
			
			return modificado;
		} catch(Exception e){
			return false;
		}
	}

	@Override
	public List<TLineaContrato> consultarLineasPorContrato(int id_contrato) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.consultarLineasPorContrato);
			ps.setInt(1, id_contrato);			
			
			ResultSet rs = ps.executeQuery();
			List<TLineaContrato> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TLineaContrato(rs.getInt(2), rs.getInt(1), rs.getString(3), rs.getString(4), rs.getDouble(5)));
			}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TLineaContrato>();
		}
	}

	@Override
	public List<TLineaContrato> consultarLineasPorHangar(int id_hangar) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.consultarLineasPorHangar);
			ps.setInt(1, id_hangar);			
			
			ResultSet rs = ps.executeQuery();
			List<TLineaContrato> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TLineaContrato(rs.getInt(2), rs.getInt(1), rs.getString(3), rs.getString(4), rs.getDouble(5)));
			}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TLineaContrato>();
		}
	}

	@Override
	public TLineaContrato consultarLineaContrato(int id_contrato, int id_hangar) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Queries.consultarLineaContrato);
			ps.setInt(1, id_contrato);
			ps.setInt(2, id_hangar);
			
			ResultSet rs = ps.executeQuery();
			
			TLineaContrato tcl = null;
			if (rs.next())
				tcl = new TLineaContrato(rs.getInt(2), rs.getInt(1), rs.getString(3), rs.getString(4), rs.getDouble(5)); 
			
			return tcl;
			
		} catch(Exception e){
			return null;
		}
	}

	@Override
	public List<TLineaContrato> consultarContratoPorAerolinea(int id_aerolinea, double precio, int dias) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Queries.consultarContratoPorAerolineaPrecioDuracion);
			ps.setInt(1, id_aerolinea);			
			ps.setDouble(2, precio);			
			ps.setInt(3, dias);			
			
			ResultSet rs = ps.executeQuery();
			List<TLineaContrato> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TLineaContrato(rs.getInt(2), rs.getInt(1), rs.getString(3), rs.getString(4), rs.getDouble(5)));
			}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TLineaContrato>();
		}
	}
}