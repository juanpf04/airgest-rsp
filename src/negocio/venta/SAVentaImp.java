
package negocio.venta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import integracion.factoria.EMFSingleton;


public class SAVentaImp implements SAVenta {
	
	public TCarritoVenta abrirCarrito(int idEmpleado) {
		return new TCarritoVenta(idEmpleado);
	}

	/** 
	* (non-Javadoc)
	* @see SAVenta#cerrarVenta(TCarritoVenta carrito)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int cerrarVenta(TCarritoVenta carrito) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAVenta#consultarVentaPorId(int id)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TInfoVenta consultarVentaPorId(int id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public List<TVenta> consultarVentas() {
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		em.getTransaction().begin();
		
		try{
			
			List<Venta> ventas = em.createNamedQuery("negocio.venta.Venta.findAll", Venta.class).getResultList();
			
			List<TVenta> listaTranfers = new ArrayList<TVenta>();
			
			for (Venta venta : ventas){
				listaTranfers.add(venta.toTransfer());
			}
			
			em.getTransaction().commit();
			
			em.close();
			
			//NO sabemos si hay que cerrar la factoria
			
			return listaTranfers;
			
			
		} catch(Exception e){
			em.getTransaction().rollback();
			em.close();
		}
		
		return null;
	}

	/** 
	* (non-Javadoc)
	* @see SAVenta#devolucion(TLineaVenta lineaVenta)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public boolean devolucion(TLineaVenta lineaVenta) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAVenta#consultarVentasPorEmpleado(int id)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public List<TVenta> consultarVentasPorEmpleado(int id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAVenta#modificarVenta(TVenta venta)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public boolean modificarVenta(TVenta venta) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}
}