
package negocio.producto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import integracion.factoria.EMFSingleton;
import negocio.marca.Marca;

public class SAProductoImp implements SAProducto {

	public synchronized int altaProducto(TProducto producto) {
		if(ValidadorProducto.comprobarDatos(producto)){
			EMFSingleton emf = EMFSingleton.getInstance();
			EntityManager em = emf.getEMF().createEntityManager();
			try{
				em.getTransaction().begin();
				TypedQuery<Producto> query = em.createNamedQuery("negocio.producto.Producto.findByref", Producto.class);
				query.setParameter("ref", String.valueOf(producto.getRef())); //TODO
				Producto aux = query.getSingleResult();
				if(aux != null){
					if(aux.getActivo()){
						em.getTransaction().rollback();
						return -1;
					}else{
						aux.setActivo(true);
						em.persist(aux);
						em.getTransaction().commit();
						return aux.getId();
					}
				}else{
					Marca marca = em.find(Marca.class, producto.getIdMarca(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
					if(marca == null){
						em.getTransaction().rollback();
						return -1;
					}
					Producto p = new Producto(producto);
					p.setMarca(marca);
					em.persist(p);
					em.getTransaction().commit();
					return p.getId();
				}
			}
			catch(Exception e) {
				em.getTransaction().rollback();
				return -1;
			}
		}else return -1;
	}

	public boolean bajaProducto(int id) {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		try{
			em.getTransaction().begin();
			Producto p = em.find(Producto.class, id);
			p.setActivo(false);
			em.persist(p);
			em.getTransaction().commit();
			return true;
		}
		catch(Exception e) {
			em.getTransaction().rollback();
			return false;
		}
	}

	public TProducto consultarProductoPorId(int id) {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		try{
			em.getTransaction().begin();
			TProducto p = em.find(Producto.class, id).toTransfer();
			em.getTransaction().commit();
			return p;
		}catch(Exception e){
			em.getTransaction().rollback();
			return null;
		}
	}

	public List<TProducto> consultarProductos() {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		try{
			em.getTransaction().begin();
			TypedQuery<Producto> query = em.createNamedQuery("negocio.producto.Producto.findAll", Producto.class);
			List<TProducto> list = new ArrayList<TProducto>();
			for(Producto p : query.getResultList()){
				list.add(p.toTransfer());
			}
			em.getTransaction().commit();
			return list;
		}
		catch(Exception e){
			em.getTransaction().rollback();
			return new ArrayList<TProducto>();
		}
	}

	public boolean modificarProducto(TProducto tProducto) {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		if(ValidadorProducto.comprobarDatos(tProducto)){
			try{
				em.getTransaction().begin();
				Marca marca = em.find(Marca.class, tProducto.getIdMarca(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				if(marca == null){
					em.getTransaction().rollback();
					return false;
				}
				Producto p = em.find(Producto.class, tProducto.getId(), LockModeType.OPTIMISTIC);
				p = new Producto(tProducto);
				p.setMarca(marca);
				em.persist(p);
				em.getTransaction().commit();
				return true;
			}
			catch (Exception e){
				em.getTransaction().rollback();
				return false;
			}
		}else return false;
	}

	public List<TProducto> consultarProductosPorMarca(int idMarca) {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		try{
			em.getTransaction().begin();
			TypedQuery<Producto> query = em.createNamedQuery("negocio.producto.Producto.findBymarca", Producto.class);
			query.setParameter("marca", String.valueOf(idMarca)); //TODO
			List<TProducto> list = new ArrayList<TProducto>();
			for(Producto p : query.getResultList()){
				list.add(p.toTransfer());
			}
			em.getTransaction().commit();
			return list;
		}
		catch(Exception e){
			em.getTransaction().rollback();
			return new ArrayList<TProducto>();
		}
	}

	public List<TProducto> consultarProductosPorProveedor(int idProveedor) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}