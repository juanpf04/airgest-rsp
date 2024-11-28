
package negocio.producto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import integracion.factoria.EMFSingleton;
import negocio.UtilidadesN;
import negocio.marca.Marca;
import negocio.proveedor.Proveedor;

public class SAProductoImp implements SAProducto {

	/////																								/////
	/////							FALTA METER EMF.CLOSE Y EM.CLOSE EN TODOS LADOS						/////
	/////																								/////
	
	public synchronized int altaProducto(TProducto producto) {
		if(ValidadorProducto.comprobarDatos(producto)){
			EMFSingleton emf = EMFSingleton.getInstance();
			EntityManager em = emf.getEMF().createEntityManager();
			try{
				em.getTransaction().begin();
				TypedQuery<Producto> query = em.createNamedQuery("negocio.producto.Producto.findByref", Producto.class);
				query.setParameter("ref", String.valueOf(producto.getRef())); //TODO NO SABEMOS SI ESTA BIEN ASI
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
					if(marca == null || !marca.getActivo()){
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
		boolean ok = false;
		if(UtilidadesN.comprobarId(id)){
			EMFSingleton emf = EMFSingleton.getInstance();
			EntityManager em = emf.getEMF().createEntityManager();
			try{
				em.getTransaction().begin();
				Producto p = em.find(Producto.class, id); //TODO mirar si lo cambiamos a query
				if(p != null && p.getActivo() == true){
					p.setActivo(false);
					em.persist(p);
					em.getTransaction().commit();
					ok = true;
				}
			}
			catch(Exception e) {
				em.getTransaction().rollback();
				return false;
			}
		}
		return ok;
	}

	public TProducto consultarProductoPorId(int id) {
		TProducto p = null;
		if(UtilidadesN.comprobarId(id)){
			EMFSingleton emf = EMFSingleton.getInstance();
			EntityManager em = emf.getEMF().createEntityManager();
			try{
				em.getTransaction().begin();
				p = em.find(Producto.class, id).toTransfer(); //TODO mirar si lo cambiamos a query
				em.getTransaction().commit();
			}catch(Exception e){
				em.getTransaction().rollback();
				return null;
			}
		}
		return p;
	}

	public List<TProducto> consultarProductos() {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		List<TProducto> list = new ArrayList<>();
		try{
			em.getTransaction().begin();
			List<Producto> aux = em.createNamedQuery("negocio.producto.Producto.findAll", Producto.class).getResultList();
			for(Producto p : aux){
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
		boolean ok = false;
		if(ValidadorProducto.comprobarDatos(tProducto)){
			EMFSingleton emf = EMFSingleton.getInstance();
			EntityManager em = emf.getEMF().createEntityManager();
			try{
				em.getTransaction().begin();
				Marca marca = em.find(Marca.class, tProducto.getIdMarca(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				TypedQuery<Producto> query = em.createNamedQuery("negocio.producto.Producto.findByref", Producto.class);
				query.setParameter("ref", String.valueOf(tProducto.getRef())); //TODO no sabemos si esta bien asi la llamada a la query
				Producto p = query.getSingleResult();
				if(marca == null || !marca.getActivo() || p == null || !p.getActivo()){
					em.getTransaction().rollback();
					ok = false;
				}else{
					em.lock(p, LockModeType.OPTIMISTIC);
					p = new Producto(tProducto);
					p.setMarca(marca);
					em.persist(p);
					em.getTransaction().commit();
					ok = true;
				}
			}
			catch (Exception e){
				em.getTransaction().rollback();
				return false;
			}
		}
		return ok;
	}

	public List<TProducto> consultarProductosPorMarca(int idMarca) {
		List<TProducto> list = new ArrayList<TProducto>();
		if(UtilidadesN.comprobarId(idMarca)){
			EMFSingleton emf = EMFSingleton.getInstance();
			EntityManager em = emf.getEMF().createEntityManager();
			try{
				em.getTransaction().begin();
				Marca marca = em.find(Marca.class, idMarca);
				if(marca != null && marca.getActivo()){
					TypedQuery<Producto> query = em.createNamedQuery("negocio.producto.Producto.findBymarca", Producto.class);
					query.setParameter("marca", String.valueOf(idMarca)); //TODO no sabemos si esta bien asi la llamada a la query
					for(Producto p : query.getResultList()){
						list.add(p.toTransfer());
					}
					em.getTransaction().commit();
				}else{
					em.getTransaction().rollback();
				}
			}
			catch(Exception e){
				em.getTransaction().rollback();
				return new ArrayList<TProducto>();
			}
		}
		return list;
	}

	public List<TProducto> consultarProductosPorProveedor(int idProveedor) {
		List<TProducto> list = new ArrayList<TProducto>();
		if(UtilidadesN.comprobarId(idProveedor)){
			EMFSingleton emf = EMFSingleton.getInstance();
			EntityManager em = emf.getEMF().createEntityManager();
			try{
				em.getTransaction().begin();
				Proveedor prov = em.find(Proveedor.class, idProveedor); //TODO mirar si lo cambiamos a query
				if(prov != null && prov.getActivo()){
					List<Producto> aux = prov.getProductos();
					for(Producto p : aux){
						list.add(p.toTransfer());
					}
					em.getTransaction().commit();
				}else{
					em.getTransaction().rollback();
				}
			}
			catch(Exception e){
				em.getTransaction().rollback();
				return new ArrayList<TProducto>();
			}
		}
		return list;
	}
}