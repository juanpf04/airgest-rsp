package integracion.avion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import integracion.Querys;

import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.avion.TAComercial;
import negocio.avion.TAPrivado;
import negocio.avion.TAvion;

public class DAOAvionImp implements DAOAvion {

	@Override
	public int altaAvion(TAvion tAvion) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.altaAvion, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tAvion.getNombre());
			ps.setInt(2, tAvion.getNumAsientos());
			ps.setString(3, tAvion.getMatricula());
			ps.setString(4, tAvion.getFechaFabricacion());
			ps.setInt(5, tAvion.getIdHangar());
			ps.setInt(6, tAvion.getIdModelo());
			ps.setInt(7, tAvion.getIdAerolinea());

			int filas = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			int id = filas == 1 && rs.next() ? rs.getInt(1) : -1;
			tAvion.setId(id);
			boolean ok = false;
			if (id != -1 && tAvion instanceof TAComercial)
				ok = altaComercial((TAComercial) tAvion);
			else
				ok = altaPrivado((TAPrivado) tAvion);

			if (!ok)
				id = -1;

			rs.close();
			ps.close();

			return id;

		} catch (Exception e) {
			return -1;
		}
	}

	private Boolean altaComercial(TAComercial avion) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.altaAvionComercial);
			ps.setInt(1, avion.getId());
			ps.setString(2, avion.getEmpresa());
			int filas = ps.executeUpdate();
			ps.close();
			return filas == 1;
		} catch (Exception e) {
			return false;
		}
	}

	private Boolean altaPrivado(TAPrivado avion) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.altaAvionPrivado);
			ps.setInt(1, avion.getId());
			ps.setString(2, avion.getNombreDuenyo());
			ps.setInt(3, avion.getIdCarnet());
			int filas = ps.executeUpdate();
			ps.close();
			return filas == 1;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean bajaAvion(int id) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.bajaAvion);
			ps.setInt(1, id);

			int filas = ps.executeUpdate();
			boolean eliminado = filas == 1;

			ps.close();

			return eliminado;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean modificarAvion(TAvion tAvion) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.modificarAvion);
			ps.setString(1, tAvion.getNombre());
			ps.setInt(2, tAvion.getNumAsientos());
			ps.setString(3, tAvion.getMatricula());
			ps.setString(4, tAvion.getFechaFabricacion());
			ps.setInt(5, tAvion.getIdHangar());
			ps.setInt(6, tAvion.getIdModelo());
			ps.setInt(7, tAvion.getIdAerolinea());
			ps.setBoolean(8, tAvion.getActivo());
			ps.setInt(9, tAvion.getId());

			int filas = ps.executeUpdate();
			boolean modificado = filas == 1;

			if (modificado && tAvion instanceof TAComercial) {
				boolean comercialModificado = modificarComercial((TAComercial) tAvion);
				if (!comercialModificado) {
					modificado = eliminarPrivado(tAvion.getId());
					modificado = altaComercial((TAComercial) tAvion);
				}
			} else {
				boolean privadoModificado = modificarPrivado((TAPrivado) tAvion);
				if (!privadoModificado) {
					modificado = eliminarComercial(tAvion.getId());
					modificado = altaPrivado((TAPrivado) tAvion);
				}
			}

			ps.close();

			return modificado;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean modificarComercial(TAComercial avion) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.modificarComercial);
			ps.setString(1, avion.getEmpresa());
			ps.setInt(2, avion.getId());
			int filas = ps.executeUpdate();
			ps.close();
			return filas == 1;

		} catch (Exception e) {
			return false;
		}
	}

	private boolean modificarPrivado(TAPrivado avion) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.modificarPrivado);
			ps.setString(1, avion.getNombreDuenyo());
			ps.setInt(2, avion.getIdCarnet());
			ps.setInt(3, avion.getId());
			int filas = ps.executeUpdate();
			ps.close();
			return filas == 1;

		} catch (Exception e) {
			return false;
		}
	}

	private boolean eliminarComercial(int id) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.eliminarComercial);
			ps.setInt(1, id);
			int filas = ps.executeUpdate();
			ps.close();
			return filas == 1;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean eliminarPrivado(int id) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.eliminarPrivado);
			ps.setInt(1, id);
			int filas = ps.executeUpdate();
			ps.close();
			return filas == 1;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public TAvion consultarAvionPorId(int id) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionPorId);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			TAvion tav = null;
			if (rs.next())
				tav = new TAvion(rs.getInt("Id"), rs.getInt("Num_Asientos"), rs.getString("Fecha_Fabricacion"),
						rs.getString("Nombre"), rs.getString("Matricula"), rs.getBoolean("Activo"),
						rs.getInt("Id_Aerolinea"), rs.getInt("Id_Modelo"), rs.getInt("Id_Hangar"));
			TAComercial comercial = consultarComercialPorId(id, tav);
			TAPrivado priv = null;
			if (comercial != null) {
				tav = comercial;
			} else {
				priv = consultarPrivadoPorId(id, tav);
				if (priv != null)
					tav = priv;
			}

			rs.close();
			ps.close();

			return tav;

		} catch (Exception e) {
			return null;
		}
	}

	private TAComercial consultarComercialPorId(int id, TAvion tav) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarComercialPorId);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			String empresa = null;
			TAComercial avion = null;
			if (rs.next()) {
				empresa = rs.getString("Empresa");
				avion = new TAComercial(id, tav.getNumAsientos(), tav.getFechaFabricacion(), tav.getNombre(),
						tav.getMatricula(), tav.getActivo(), tav.getIdAerolinea(), tav.getIdModelo(), tav.getIdHangar(),
						empresa);
			}
			rs.close();
			ps.close();
			return avion;

		} catch (Exception e) {
			return null;
		}

	}

	private TAPrivado consultarPrivadoPorId(int id, TAvion tav) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarPrivadoPorId);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			String duenyo = null;
			int carnet;
			TAPrivado avion = null;
			if (rs.next()) {
				duenyo = rs.getString("Nombre_Duenyo");
				carnet = rs.getInt("Carnet");
				avion = new TAPrivado(id, tav.getNumAsientos(), tav.getFechaFabricacion(), tav.getNombre(),
						tav.getMatricula(), tav.getActivo(), tav.getIdAerolinea(), tav.getIdModelo(), tav.getIdHangar(),
						duenyo, carnet);
			}
			rs.close();
			ps.close();
			return avion;

		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public TAvion consultarAvionPorMatricula(String matricula) {
		try {
			Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionPorMatricula);
			ps.setString(1, matricula);

			ResultSet rs = ps.executeQuery();

			TAvion tav = null;

			if (rs.next())
				tav = new TAvion(rs.getInt("Id"), rs.getInt("Num_Asientos"), rs.getString("Fecha_Fabricacion"),
						rs.getString("Nombre"), rs.getString("Matricula"), rs.getBoolean("Activo"),
						rs.getInt("Id_Aerolinea"), rs.getInt("Id_Modelo"), rs.getInt("Id_Hangar"));

			rs.close();
			ps.close();

			return tav;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<TAvion> consultarTodosAviones() {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarTodosAviones);

			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();

			while (rs.next()) {
				TAvion tav = new TAvion(rs.getInt("Id"), rs.getInt("Num_Asientos"), rs.getString("Fecha_Fabricacion"),
						rs.getString("Nombre"), rs.getString("Matricula"), rs.getBoolean("Activo"),
						rs.getInt("Id_Aerolinea"), rs.getInt("Id_Modelo"), rs.getInt("Id_Hangar"));

				TAComercial comercial = consultarComercialPorId(tav.getId(), tav);
				TAPrivado priv = null;
				if (comercial != null) {
					lista.add(comercial);
				} else {
					priv = consultarPrivadoPorId(tav.getId(), tav);
					if (priv != null)
						lista.add(priv);
				}
			}

			rs.close();
			ps.close();

			return lista;

		} catch (Exception e) {
			return new ArrayList<TAvion>();
		}
	}

	public List<TAvion> consultarAvionesPorModelo(int idModelo) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesPorModelo);
			ps.setInt(1, idModelo);

			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();

			while (rs.next()) {
				TAvion tav = new TAvion(rs.getInt("Id"), rs.getInt("Num_Asientos"), rs.getString("Fecha_Fabricacion"),
						rs.getString("Nombre"), rs.getString("Matricula"), rs.getBoolean("Activo"),
						rs.getInt("Id_Aerolinea"), rs.getInt("Id_Modelo"), rs.getInt("Id_Hangar"));

				TAComercial comercial = consultarComercialPorId(tav.getId(), tav);
				TAPrivado priv = null;
				if (comercial != null) {
					lista.add(comercial);
				} else {
					priv = consultarPrivadoPorId(tav.getId(), tav);
					if (priv != null)
						lista.add(priv);
				}
			}

			rs.close();
			ps.close();

			return lista;

		} catch (Exception e) {
			return new ArrayList<TAvion>();
		}
	}

	public List<TAvion> consultarAvionesActivosPorModelo(int idModelo) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesActivosPorModelo);
			ps.setInt(1, idModelo);

			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();

			while (rs.next()) {
				TAvion tav = new TAvion(rs.getInt("Id"), rs.getInt("Num_Asientos"), rs.getString("Fecha_Fabricacion"),
						rs.getString("Nombre"), rs.getString("Matricula"), rs.getBoolean("Activo"),
						rs.getInt("Id_Aerolinea"), rs.getInt("Id_Modelo"), rs.getInt("Id_Hangar"));

				TAComercial comercial = consultarComercialPorId(tav.getId(), tav);
				TAPrivado priv = null;
				if (comercial != null) {
					lista.add(comercial);
				} else {
					priv = consultarPrivadoPorId(tav.getId(), tav);
					if (priv != null)
						lista.add(priv);
				}
			}

			rs.close();
			ps.close();

			return lista;

		} catch (Exception e) {
			return new ArrayList<TAvion>();
		}
	}

	@Override
	public List<TAvion> consultarAvionesPorAerolinea(int idAerolinea) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesPorAerolinea);
			ps.setInt(1, idAerolinea);

			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();

			while (rs.next()) {
				TAvion tav = new TAvion(rs.getInt("Id"), rs.getInt("Num_Asientos"), rs.getString("Fecha_Fabricacion"),
						rs.getString("Nombre"), rs.getString("Matricula"), rs.getBoolean("Activo"),
						rs.getInt("Id_Aerolinea"), rs.getInt("Id_Modelo"), rs.getInt("Id_Hangar"));

				TAComercial comercial = consultarComercialPorId(tav.getId(), tav);
				TAPrivado priv = null;
				if (comercial != null) {
					lista.add(comercial);
				} else {
					priv = consultarPrivadoPorId(tav.getId(), tav);
					if (priv != null)
						lista.add(priv);
				}
			}

			rs.close();
			ps.close();

			return lista;

		} catch (Exception e) {
			return new ArrayList<TAvion>();
		}
	}

	@Override
	public List<TAvion> consultarAvionesActivosPorAerolinea(int idAerolinea) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesActivosPorAerolinea);
			ps.setInt(1, idAerolinea);

			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();

			while (rs.next()) {
				TAvion tav = new TAvion(rs.getInt("Id"), rs.getInt("Num_Asientos"), rs.getString("Fecha_Fabricacion"),
						rs.getString("Nombre"), rs.getString("Matricula"), rs.getBoolean("Activo"),
						rs.getInt("Id_Aerolinea"), rs.getInt("Id_Modelo"), rs.getInt("Id_Hangar"));

				TAComercial comercial = consultarComercialPorId(tav.getId(), tav);
				TAPrivado priv = null;
				if (comercial != null) {
					lista.add(comercial);
				} else {
					priv = consultarPrivadoPorId(tav.getId(), tav);
					if (priv != null)
						lista.add(priv);
				}
			}

			rs.close();
			ps.close();

			return lista;

		} catch (Exception e) {
			return new ArrayList<TAvion>();
		}
	}

	@Override
	public List<TAvion> consultarAvionesPorHangar(int idHangar) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesPorHangar);
			ps.setInt(1, idHangar);

			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();

			while (rs.next()) {
				TAvion tav = new TAvion(rs.getInt("Id"), rs.getInt("Num_Asientos"), rs.getString("Fecha_Fabricacion"),
						rs.getString("Nombre"), rs.getString("Matricula"), rs.getBoolean("Activo"),
						rs.getInt("Id_Aerolinea"), rs.getInt("Id_Modelo"), rs.getInt("Id_Hangar"));

				TAComercial comercial = consultarComercialPorId(tav.getId(), tav);
				TAPrivado priv = null;
				if (comercial != null) {
					lista.add(comercial);
				} else {
					priv = consultarPrivadoPorId(tav.getId(), tav);
					if (priv != null)
						lista.add(priv);
				}
			}

			rs.close();
			ps.close();

			return lista;

		} catch (Exception e) {
			return new ArrayList<TAvion>();
		}
	}

	@Override
	public List<TAvion> consultarAvionesActivosPorHangar(int idHangar) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesActivosPorHangar);
			ps.setInt(1, idHangar);

			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();

			while (rs.next()) {
				TAvion tav = new TAvion(rs.getInt("Id"), rs.getInt("Num_Asientos"), rs.getString("Fecha_Fabricacion"),
						rs.getString("Nombre"), rs.getString("Matricula"), rs.getBoolean("Activo"),
						rs.getInt("Id_Aerolinea"), rs.getInt("Id_Modelo"), rs.getInt("Id_Hangar"));

				TAComercial comercial = consultarComercialPorId(tav.getId(), tav);
				TAPrivado priv = null;
				if (comercial != null) {
					lista.add(comercial);
				} else {
					priv = consultarPrivadoPorId(tav.getId(), tav);
					if (priv != null)
						lista.add(priv);
				}
			}

			rs.close();
			ps.close();

			return lista;

		} catch (Exception e) {
			return new ArrayList<TAvion>();
		}
	}

	public List<TAvion> consultarAvionesDeAerolineaPorHangar(int id_aerolinea, int id_hangar) {
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection con = (Connection) t.getResource();

			PreparedStatement ps = con.prepareStatement(Querys.consultarAvionesDeAerolineaPorHangar);
			ps.setInt(1, id_aerolinea);
			ps.setInt(2, id_hangar);

			ResultSet rs = ps.executeQuery();
			List<TAvion> lista = new ArrayList<>();

			while (rs.next()) {
				TAvion tav = new TAvion(rs.getInt("Id"), rs.getInt("Num_Asientos"), rs.getString("Fecha_Fabricacion"),
						rs.getString("Nombre"), rs.getString("Matricula"), rs.getBoolean("Activo"),
						rs.getInt("Id_Aerolinea"), rs.getInt("Id_Modelo"), rs.getInt("Id_Hangar"));

				TAComercial comercial = consultarComercialPorId(tav.getId(), tav);
				TAPrivado priv = null;
				if (comercial != null) {
					lista.add(comercial);
				} else {
					priv = consultarPrivadoPorId(tav.getId(), tav);
					if (priv != null)
						lista.add(priv);
				}
			}

			rs.close();
			ps.close();

			return lista;

		} catch (Exception e) {
			return new ArrayList<TAvion>(); // Si ocurre un error, devolvemos
											// una lista vacía
		}
	}

}