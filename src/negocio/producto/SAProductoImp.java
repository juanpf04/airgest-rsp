
package negocio.producto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import integracion.factoria.EMFSingleton;
import negocio.UtilidadesN;
import negocio.marca.Marca;
import negocio.proveedor.Proveedor;

public class SAProductoImp implements SAProducto {

	public synchronized int altaProducto(TProducto producto) {
		EntityManager em = null;
		int id = -1;
		if(ValidadorProducto.comprobarDatos(producto)){
			try{
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();
				List<Producto> resultados = em.createNamedQuery("negocio.producto.Producto.findByref", Producto.class).
						setParameter("ref", producto.getRef()).getResultList();
				if(resultados.isEmpty()){
					
					Marca marca = em.find(Marca.class, producto.getIdMarca(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
					if(marca == null || !marca.getActivo()){
						em.getTransaction().rollback();
					}else{
						Producto p = new Producto(producto);
						p.setMarca(marca);
						em.persist(p);
						marca.getProductos().add(p);
						em.getTransaction().commit();
						id = p.getId();
					}
				}else{
					Producto p = resultados.get(0);
					if(p.getActivo()){
						em.getTransaction().rollback();
					}else{
						Marca marca = em.find(Marca.class, producto.getIdMarca(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
						if(marca == null || !marca.getActivo()){
							em.getTransaction().rollback();
						}else{
							p.setActivo(true);
							p.setMarca(marca);
							p.setNombre(producto.getNombre());
							p.setPrecio(producto.getPrecio());
							p.setRef(producto.getRef());
							p.setStock(producto.getStock());
							em.getTransaction().commit();
							id = p.getId();
						}
					}
				}
			}
			catch(Exception e) {
				if(em != null && em.getTransaction().isActive()){
					em.getTransaction().rollback();
				}
			}finally{
				if(em != null){
					em.close();
				}
			}
		}
		return id;
	}

	public boolean bajaProducto(int id) { //TODO no se puede dar de baja si tiene lineas de venta, ni proveedores vinculados
		EntityManager em = null;
		boolean ok = false;
		if(UtilidadesN.comprobarId(id)){
			try{
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();
				Producto p = em.find(Producto.class, id);
				if(p != null && p.getActivo() == true){
					p.setActivo(false);
					em.getTransaction().commit();
					ok = true;
				}
			}
			catch(Exception e) {
				if(em != null && em.getTransaction().isActive()){
					em.getTransaction().rollback();
				}
			}finally{
				if(em != null){
					em.close();
				}
			}
		}
		return ok;
	}

	public TProducto consultarProductoPorId(int id) {
		EntityManager em = null;
		TProducto p = null;
		if(UtilidadesN.comprobarId(id)){
			try{
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();
				p = em.find(Producto.class, id).toTransfer();
				em.getTransaction().commit();
			}catch(Exception e) {
				if(em != null && em.getTransaction().isActive()){
					em.getTransaction().rollback();
				}
			}finally{
				if(em != null){
					em.close();
				}
			}
		}
		return p;
	}

	public List<TProducto> consultarProductos() {
		EntityManager em = null;
		List<TProducto> list = new ArrayList<>();
		try{
			em = EMFSingleton.getInstance().getEMF().createEntityManager();
			em.getTransaction().begin();
			List<Producto> aux = em.createNamedQuery("negocio.producto.Producto.findAll", Producto.class).getResultList();
			for(Producto p : aux){
				list.add(p.toTransfer());
			}
			em.getTransaction().commit();
		}
		catch(Exception e) {
			if(em != null && em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
		}finally{
			if(em != null){
				em.close();
			}
		}
		return list;
	}

	public boolean modificarProducto(TProducto tProducto) {
		EntityManager em = null;
		boolean ok = false;
		if(ValidadorProducto.comprobarDatos(tProducto)){
			try{
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();
				
				Producto p = em.find(Producto.class, tProducto.getId());
				
				if(p != null){
					
					List<Producto> resultados = em.createNamedQuery("negocio.producto.Producto.findByref", Producto.class).
							setParameter("ref", tProducto.getRef()).setLockMode(LockModeType.OPTIMISTIC).getResultList();
					
					if(p.getActivo() && (p.getRef() == tProducto.getRef() || resultados.isEmpty())){
						
						Marca marca = em.find(Marca.class, tProducto.getIdMarca(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
						
						if(marca == null || !marca.getActivo()){
							em.getTransaction().rollback();
						}else{
							p.setMarca(marca);
							p.setNombre(tProducto.getNombre());
							p.setPrecio(tProducto.getPrecio());
							p.setRef(tProducto.getRef());
							p.setStock(tProducto.getStock());
							em.getTransaction().commit();
							ok = true;
						}
					}else{
						em.getTransaction().rollback();
					}
				}else{
					em.getTransaction().rollback();
				}
			}
			catch(Exception e) {
				if(em != null && em.getTransaction().isActive()){
					em.getTransaction().rollback();
				}
			}finally{
				if(em != null){
					em.close();
				}
			}
		}
		return ok;
	}

	public List<TProducto> consultarProductosPorMarca(int idMarca) {
		EntityManager em = null;
		List<TProducto> list = new ArrayList<TProducto>();
		if(UtilidadesN.comprobarId(idMarca)){
			try{
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();
				Marca marca = em.find(Marca.class, idMarca);
				if(marca != null && marca.getActivo()){
					List<Producto> resultados = em.createNamedQuery("negocio.producto.Producto.findBymarca", Producto.class).
							setParameter("marca", marca).getResultList();
					for(Producto p : resultados){
						list.add(p.toTransfer());
					}
					em.getTransaction().commit();
				}else{
					em.getTransaction().rollback();
				}
			}
			catch(Exception e) {
				if(em != null && em.getTransaction().isActive()){
					em.getTransaction().rollback();
				}
			}finally{
				if(em != null){
					em.close();
				}
			}
		}
		return list;
	}

	
	public List<TProducto> consultarProductosPorProveedor(int idProveedor) {
		EntityManager em = null;
		List<TProducto> list = new ArrayList<TProducto>();
		if(UtilidadesN.comprobarId(idProveedor)){
			try{
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();
				Proveedor prov = em.find(Proveedor.class, idProveedor);
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
			catch(Exception e) {
				if(em != null && em.getTransaction().isActive()){
					em.getTransaction().rollback();
				}
			}finally{
				if(em != null){
					em.close();
				}
			}
		}
		return list;
	}
}