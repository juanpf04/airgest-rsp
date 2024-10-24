package integracion.contrato;

import negocio.contrato.TContrato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import integracion.Querys;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;

public class DAOContratoImp implements DAOContrato {

	public int altaContrato(TContrato tContrato) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.alta_contrato, PreparedStatement.RETURN_GENERATED_KEYS);
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

	public TContrato leerContratoPorId(int id) {
		
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.leerContratoPorId);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			TContrato tc = null;
			if (rs.next())
				tc = new TContrato(rs.getInt(1), rs.getInt(3), rs.getDouble(2)); 
			
			return tc;
			
		} catch(Exception e){
			return null;
		}
	}

	public List<TContrato> leerTodosContratos() {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.leerTodosContratos);
			
			ResultSet rs = ps.executeQuery();
			List<TContrato> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TContrato(rs.getInt(1), rs.getInt(3), rs.getDouble(2)));
			}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TContrato>();
		}
	}

	public List<TContrato> leerContratosPorAerolinea(int id_aerolinea) {
		try{
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.leerContratosPorAerolinea);
			ps.setInt(1, id_aerolinea);			
			
			ResultSet rs = ps.executeQuery();
			List<TContrato> lista = new ArrayList<>();
			
			while (rs.next()){
				lista.add(new TContrato(rs.getInt(1), rs.getInt(3), rs.getDouble(2)));
			}
			
			rs.close();
			ps.close();
			
			return lista;
			
		} catch(Exception e){
			return new ArrayList<TContrato>();
		}
	}

	public boolean modificarContrato(TContrato tContrato) {
		
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.modificarContrato);
			ps.setDouble(1, tContrato.getPrecio());
			ps.setInt(2, tContrato.getIdAerolinea());
			ps.setInt(3, tContrato.getId());
			
			int filas = ps.executeUpdate();
			boolean modificado = filas == 1;
			
			ps.close();
			
			return modificado;
		} catch(Exception e){
			return false;
		}
	}


}