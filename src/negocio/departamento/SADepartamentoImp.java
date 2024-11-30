
package negocio.departamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import integracion.factoria.EMFSingleton;
import negocio.UtilidadesN;
import negocio.empleado.Empleado;


public class SADepartamentoImp implements SADepartamento {
	
	
	public TDepartamento consultarDepartamentoPorId(int id) {
		EntityManager em = null;
		TDepartamento tDep = null;
		
		if(UtilidadesN.comprobarId(id)){
			
			try{
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin(); 
				
				Departamento dep = em.find(Departamento.class, id);
				
				if(dep != null)
					tDep = dep.toTransfer();
				
				em.getTransaction().commit();
				
				
				
			} catch(Exception e){
				
				if (em != null && em.getTransaction().isActive())
					em.getTransaction().rollback();
				
			} finally{
				
				if(em != null) em.close();
			}
		}
		
		return tDep;
	}


	public synchronized int altaDepartamento(TDepartamento departamento) {
		
		EntityManager em = null;
		int id = -1;
		
		if (ValidadorDepartamento.comprobarDatos(departamento)){
			
			try{
				
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();
				Departamento dep;
				
				List<Departamento> listaResultado = em.createNamedQuery("negocio.departamento.Departamento.findBynombre", Departamento.class)
						.setParameter("nombre", departamento.getNombre()).getResultList(); // el getSingleResult devolveria una excepcion si no encuentra y no null como pensabamos
				
				if(listaResultado.isEmpty()){
					
					dep = new Departamento(departamento);
					em.persist(dep);
					em.getTransaction().commit();
					id = dep.getId(); // el id se consigue tras cerrar la transacción 
					
				} else{
					
					dep = listaResultado.get(0); // la lista como mucho tendra un elemento (porque solamente habra 1 departamento con el mismo nombre)
					
					if(!dep.getActivo()){
						
						dep.setActivo(true);
						dep.setSala(departamento.getSala());
						dep.setSueldoHora(departamento.getSueldoHora());
						em.getTransaction().commit();
						id = dep.getId();
						
					} else{
						
						em.getTransaction().rollback();
					}
				}
				
			} catch(Exception e){
				
				if (em != null && em.getTransaction().isActive())
					em.getTransaction().rollback();
				
			} finally{
				
				if(em != null) em.close();
			}
			
		}
		
		return id;
	}


	public boolean bajaDepartamento(int id) {
		
		EntityManager em = null;
		boolean ok = false;
		
		if(UtilidadesN.comprobarId(id)){
			try{
				
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();
				Departamento dep;
				
				dep = em.find(Departamento.class, id);
				
				if(dep != null && dep.getActivo()){
					
					List<Empleado> listaEmpleados = em.createNamedQuery("negocio.empleado.Empleado.findBydepartamento", Empleado.class)
							.setParameter("departamento", dep)
							.setLockMode(LockModeType.OPTIMISTIC)
							.getResultList();
					
					for(Empleado emp : listaEmpleados){
						if(emp.getActivo()){
							em.getTransaction().rollback();
							return ok;
						}
					}
					
					dep.setActivo(false);
					ok = true;
					em.getTransaction().commit();
				}else{
					em.getTransaction().rollback();
				}
				
			}catch(Exception e){
				if (em != null && em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			}finally{
				
				if(em != null) em.close();
			}
		}
		
		return ok;	
	}


	public List<TDepartamento> consultarDepartamentos() {
		EntityManager em = null;
		List<TDepartamento> listadepartamentos = new ArrayList<TDepartamento>();
		
		try {
			
			em = EMFSingleton.getInstance().getEMF().createEntityManager();
			em.getTransaction().begin();
			
			List<Departamento> listaResultado = em.createNamedQuery("negocio.departamento.Departamento.findAll", Departamento.class).getResultList();
			
			for(Departamento dpt : listaResultado){
				
				listadepartamentos.add(dpt.toTransfer());
			}
			
			em.getTransaction().commit();
			
		} catch(Exception e){
			
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
			
		} finally{
			
			if (em != null) em.close();
		}
		
		return listadepartamentos;

	}


	public boolean modificarDepartamento(TDepartamento departamento) {
		EntityManager em = null;
		boolean ok = false;
		
		if (ValidadorDepartamento.comprobarDatos(departamento)){
			
			
			try{
				
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin();
				Departamento dep;
				
				dep = em.find(Departamento.class, departamento.getId());
				
				if(dep != null){
					List<Departamento> listaResultado = em.createNamedQuery("negocio.departamento.Departamento.findBynombre", Departamento.class)
							.setParameter("nombre", departamento.getNombre()).setLockMode(LockModeType.OPTIMISTIC).getResultList(); 
					
					if(dep.getActivo() && (dep.getNombre().equals(departamento.getNombre()) || listaResultado.isEmpty())){
						
						ok = true;
						dep.setNombre(departamento.getNombre());
						dep.setSala(departamento.getSala());
						dep.setSueldoHora(departamento.getSueldoHora());
						
						em.getTransaction().commit();
					} else{
						em.getTransaction().rollback();
					}
				}else
					em.getTransaction().rollback();
				
				} catch(Exception e){
					
					if (em != null && em.getTransaction().isActive())
						em.getTransaction().rollback();
					
				} finally{
					
					if(em != null) em.close();
				}
			}

		
		return ok;
	}

	
	public double calcularNomina(int id) {
		EntityManager em = null;
		double nomina = 0.0;
		
		if(UtilidadesN.comprobarId(id)){
			
			try{
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				em.getTransaction().begin(); 
				
				Departamento dep = em.find(Departamento.class, id, LockModeType.OPTIMISTIC);
				
				if(dep != null){
					for (Empleado emp : dep.getEmpleados()) {
						em.lock(emp, LockModeType.OPTIMISTIC);
						//nomina += emp.calcularSueldo();
					}
						em.getTransaction().commit();
				}else{
					em.getTransaction().rollback();
				}
				
			}catch(Exception e){
				
				if (em != null && em.getTransaction().isActive())
					em.getTransaction().rollback();
				
			} finally{
				
				if(em != null) em.close();
			}
		}
		
		return nomina;
	}
}