package negocio.proveedor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import integracion.factoria.EMFSingleton;
import negocio.UtilidadesN;
import negocio.producto.Producto;


public class SAProveedorImp implements SAProveedor {
	
	public int altaProveedor(TProveedor tProveedor) {
		EntityManager em = null;
		int id = -1;

		if (ValidadorProveedor.comprobarDatos(tProveedor)) 
		{
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();

				Proveedor proveedor;

				List<Proveedor> resultados = em.createNamedQuery("negocio.proveedor.Proveedor.findBynombre", Proveedor.class)
						.setParameter("nombre", tProveedor.getNombre())
						.getResultList();

				if (resultados.isEmpty()) 
				{
					proveedor = new Proveedor(tProveedor);
					em.persist(proveedor);
					em.getTransaction().commit();
					id = proveedor.getId();
				} 
				else 
				{
					proveedor = resultados.get(0);
					if (!proveedor.getActivo()) 
					{ 
						if(tProveedor instanceof TNacional && proveedor instanceof Nacional)
						{			
							((Nacional) proveedor).setCodigoPostal(((TNacional)tProveedor).getCodigoPostal());
							proveedor.setActivo(true);
							em.getTransaction().commit();
							id = tProveedor.getId();
						}
						else if(tProveedor instanceof TInternacional && proveedor instanceof Internacional)
						{
							((Internacional) proveedor).setPais(((TInternacional)tProveedor).getPais());
							((Internacional) proveedor).setImpuesto(((TInternacional)tProveedor).getImpuesto());
							proveedor.setActivo(true);
							em.getTransaction().commit();
							id = tProveedor.getId();
						}
						else
						{
							em.getTransaction().rollback();							
						}

					} 
					else 
					{
						em.getTransaction().rollback();
					}
				}
			} 
			catch (Exception e) 
			{ 
				if (em != null && em.getTransaction().isActive()) 
				{
					em.getTransaction().rollback();
				}

			} finally {
				if (em != null) 
				{
					em.close();
				}

			}
		}

		return id;
	}

	
	public boolean bajaProveedor(int id) {
		EntityManager em = null;
		boolean exito = false;

		if (UtilidadesN.comprobarId(id)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();

				Proveedor proveedor;
				
				proveedor = em.find(Proveedor.class, id);
				if (proveedor != null && proveedor.getActivo())
				{
				
					if(proveedor.getProductos().size() == 0)// si no tiene productos vinculados
					{
						proveedor.setActivo(false);
						exito = true;
						em.getTransaction().commit();
					}
					else
					{
						em.getTransaction().rollback();
					}
					
				} 
				else
				{
					em.getTransaction().rollback();
				}
			} catch (Exception e) { 
				if (em != null && em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}

			} finally {
				if (em != null) {
					em.close();
				}

			}
		}

		return exito;
	}

	
	public TProveedor consultarProveedorPorId(int id) {
		EntityManager em = null;
		TProveedor tProveedor = null;

		if (UtilidadesN.comprobarId(id)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();

				Proveedor proveedor = em.find(Proveedor.class, id);

				if (proveedor != null) {
					tProveedor = proveedor.toTransfer();
				}

				em.getTransaction().commit();

			} catch (Exception e) { 
				if (em != null && em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}

			} finally {
				if (em != null) {
					em.close();
				}

			}
		}

		return tProveedor;
	}

	
	public List<TProveedor> consultarProveedores() {
		EntityManager em = null;
		List<TProveedor> listaProveedores = new ArrayList<TProveedor>();

		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();
			em.getTransaction().begin();

			List<Proveedor> resultados = em.createNamedQuery("negocio.proveedor.Proveedor.findAll", Proveedor.class).getResultList();

			for (Proveedor marca : resultados) {
				listaProveedores.add(marca.toTransfer());
			}

			em.getTransaction().commit();

		} catch (Exception e) { // excepcion por si falla algo de
								// transaccion
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if (em != null) {
				em.close();
			}
		}

		return listaProveedores;
	}

	
	public List<TProveedor> consultarProveedoresPorProducto(int idProducto) {
		EntityManager em = null;
		List<TProveedor> listaProveedores = new ArrayList<TProveedor>();
		
		try {
			em = EMFSingleton.getInstance().getEMF().createEntityManager();
			em.getTransaction().begin();
		
		List<Proveedor> resultados = em.createNamedQuery("negocio.proveedor.Proveedor.findByproductos", Proveedor.class)
				.setParameter("productos", em.find(Producto.class, idProducto))
				.getResultList();
		
		for (Proveedor marca : resultados) {
			listaProveedores.add(marca.toTransfer());
		}
		
		em.getTransaction().commit();

		}
		catch (Exception e) 
		{ // excepcion por si falla algo de
			// transaccion
			if (em != null && em.getTransaction().isActive()) 
			{
				em.getTransaction().rollback();
			}

		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return listaProveedores;
	}

	
	public boolean modificarProveedor(TProveedor tProveedor) {
		EntityManager em = null;
		boolean exito = false;

		if (ValidadorProveedor.comprobarDatos(tProveedor)) {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();

				Proveedor proveedor;
				
				proveedor = em.find(Proveedor.class, tProveedor.getId());
				
				if (proveedor != null) {
					List<Proveedor> resultados = em.createNamedQuery("negocio.proveedor.Proveedor.findBynombre", Proveedor.class)
							.setParameter("nombre", tProveedor.getNombre())
							.setLockMode(LockModeType.OPTIMISTIC)
							.getResultList();
					
					if (proveedor.getActivo()																	
							&& (proveedor.getNombre().equals(tProveedor.getNombre()) || resultados.isEmpty())) 
					{	
						if(proveedor instanceof Nacional && tProveedor instanceof TNacional)
						{
							((Nacional)proveedor).setCodigoPostal(((TNacional)tProveedor).getCodigoPostal());
							proveedor.setNombre(tProveedor.getNombre());
							em.getTransaction().commit();
							exito = true;
						}
						else if(proveedor instanceof Internacional && tProveedor instanceof TInternacional)
						{
							((Internacional)proveedor).setPais(((TInternacional)tProveedor).getPais());
							((Internacional)proveedor).setImpuesto(((TInternacional)tProveedor).getImpuesto());
							proveedor.setNombre(tProveedor.getNombre());
							em.getTransaction().commit();
							exito = true;
						}
						else
						{
							em.getTransaction().rollback();

						}
					} 
					else
					{
						em.getTransaction().rollback();
					}
				} 
				else
				{
					em.getTransaction().rollback();
				}
			} catch (Exception e) { // excepcion por si falla algo de
									// transaccion
				if (em != null && em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}

			} finally {
				if (em != null) {
					em.close();
				}

			}
		}

		return exito;
	}

	
	public boolean vincularProveedorProducto(int idProducto, int idProveedor) {
		EntityManager em = null;
		boolean exito = false;
		if (UtilidadesN.comprobarId(idProducto) && UtilidadesN.comprobarId(idProveedor)) 
		{
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();
				
				Proveedor proveedor;
				proveedor = em.find(Proveedor.class, idProveedor, LockModeType.OPTIMISTIC_FORCE_INCREMENT); 
				
				Producto producto;
				producto = em.find(Producto.class, idProducto, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				
				if(proveedor != null && producto != null && proveedor.getActivo() && producto.getActivo())
				{
					proveedor.getProductos().add(producto);
					em.getTransaction().commit();
					exito = true;
				}
				else
				{
					em.getTransaction().rollback();
				}
								
			}catch (Exception e) {
				if (em != null && em.getTransaction().isActive()) 
				{
					em.getTransaction().rollback();
				}
			} finally {
				if (em != null) 
					em.close();
			}
		}

		return exito;
	}

	
	public boolean desvincularProveedorProducto(int idProducto, int idProveedor) {
		EntityManager em = null;
		boolean exito = false;
		if (UtilidadesN.comprobarId(idProducto) && UtilidadesN.comprobarId(idProveedor)) 
		{
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();
				
				Proveedor proveedor;
				proveedor = em.find(Proveedor.class, idProveedor, LockModeType.OPTIMISTIC_FORCE_INCREMENT); 
				
				Producto producto;
				producto = em.find(Producto.class, idProducto, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				
				if(proveedor != null && producto != null && proveedor.getActivo() && producto.getActivo())
				{
					proveedor.getProductos().remove(producto);
					em.getTransaction().commit();
					exito = true;
				}
				else
				{
					em.getTransaction().rollback();
				}
								
			}catch (Exception e) {
				if (em != null && em.getTransaction().isActive()) 
				{
					em.getTransaction().rollback();
				}
			} finally {
				if (em != null) 
					em.close();
			}
		}

		return exito;
	}
}