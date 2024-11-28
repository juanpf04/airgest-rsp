
package negocio.departamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import integracion.factoria.EMFSingleton;


public class SADepartamentoImp implements SADepartamento {
	
	
	public TDepartamento consultarDepartamentoPorId(int id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}


	public synchronized int altaDepartamento(TDepartamento departamento) {
		
		//CREAMOS LA TRANSACCION FUERA DEL TRY CATCH POR SI EN U NCATCH EXCEPTION HAY QUE HACER COMMIT
		EMFSingleton emf = EMFSingleton.getInstance();
		EntityManager em = emf.getEMF().createEntityManager();
		
		em.getTransaction().begin();
		
		try {
			
			if (ValidadorDepartamento.comprobarDatos(departamento)){
				
				Departamento result = em.createNamedQuery("Departamento.findByNombre", Departamento.class).getSingleResult(); //si no existe ninguno devuelve null??
				
				if (result == null){
					
					Departamento dpt = new Departamento(departamento);
					em.persist(dpt);
					em.getTransaction().commit();
					return dpt.getId();
					
				} else {
					if (result.getActivo()){
						em.getTransaction().rollback();
						return -1;
					} else {
						result.setActivo(true);
						return result.getId();
					}
					
				}
				
			} else{
				em.getTransaction().rollback();
				return -1;
			}
			
		}catch(Exception e){ //HACEN FALTA HACER CATCH DE MAS EXCEPCIONES 
			return -1;
		}
		
	}


	public boolean bajaDepartamento(int id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}


	public List<TDepartamento> consultarDepartamentos() {
		
		try {
			
			EMFSingleton emf = EMFSingleton.getInstance();
			EntityManager em = emf.getEMF().createEntityManager();
			
			em.getTransaction().begin();
			
			List<Departamento> result = em.createNamedQuery("Departamento.findById", Departamento.class).getResultList();
			//hay que pasar la lista de Departamento a TDepartamento
			
			

			return new ArrayList<TDepartamento>();
			
		} catch(Exception e){
			return new ArrayList<TDepartamento>();
		}

	}


	public boolean modificarDepartamento(TDepartamento departamento) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}

	
	public double calcularNomina(int id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}
}