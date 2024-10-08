package negocio.avion;

import negocio.UtilidadesN;
import negocio.avion.TAComercial;
import negocio.avion.TAPrivado;

public class ValidadorAvion {

	public static boolean comprobarDatos(TAvion tAvion) {
		if (tAvion instanceof TAComercial)
			return comprobarComercial((TAComercial) tAvion);
		else
			return comprobarPrivado((TAPrivado) tAvion);
	}

	public static boolean comprobarComercial(TAComercial tComercial) {

		return comprobarInfo(tComercial) && comprobarTrabajadores(tComercial);

	}

	public static boolean comprobarPrivado(TAPrivado tPriv) {

		return comprobarInfo(tPriv) && comprobarCarnet(tPriv) && comprobarDuenyo(tPriv);
	}

	public static boolean comprobarInfo(TAvion tAvion) {
		return tAvion != null && comprobarAsientos(tAvion.getNumAsientos())
				&& UtilidadesN.comprobarId(tAvion.getIdAerolinea()) && UtilidadesN.comprobarId(tAvion.getIdHangar())
				&& UtilidadesN.comprobarId(tAvion.getIdModelo()) && comprobarMatricula(tAvion.getMatricula());
	}

	public static boolean comprobarCarnet(TAPrivado tPriv) {
		return tPriv.getIdCarnet() > 0;
	}

	public static boolean comprobarDuenyo(TAPrivado tPriv) {
		return tPriv.getNombreDuenyo() != null && !tPriv.getNombreDuenyo().isEmpty();
	}

	public static boolean comprobarTrabajadores(TAComercial tComercial) {
		return tComercial.getTrabajadores() > 0;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static boolean comprobarId(int id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}

	public static boolean comprobarAsientos(int asientos) {
		return asientos > 0;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idHangar
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static boolean comprobarHangar(int idHangar) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idAerolinea
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static boolean comprobarAerolinea(int idAerolinea) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idModelo
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static boolean comprobarModelo(int idModelo) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}

	// public static boolean comprobarHangar(int idHangar) {
	// DAOHangar dh = FactoriaIntegracion.getInstance().crearDAOHangar();
	// THangar hangar = dh.leerHangarPorId(idHangar);
	// return hangar != null;
	// }
	//
	// public static boolean comprobarAerolinea(int idAerolinea) {
	// DAOAerolinea da = FactoriaIntegracion.getInstance().crearDAOAerolinea();
	// TAerolinea aerolinea = da.leerAerolineaPorId(idAerolinea);
	// return aerolinea != null;
	// }
	//
	// public static boolean comprobarModelo(int idModelo) {
	// DAOModelo dm = FactoriaIntegracion.getInstance().crearDAOModelo();
	// TModelo modelo = dm.leerModeloPorId(idModelo);
	// return modelo != null;
	// }

	public static boolean comprobarMatricula(String matricula) {
		return matricula.matches("^EC-[A-Za-z0-9]+$"); // matriculas españolas:
														// EC-(caracteres
														// alfanumericos)
	}
}