package negocio.avion;

import negocio.UtilidadesN;

public class ValidadorAvion {

	public static boolean comprobarDatos(TAvion tAvion) {
		if (tAvion instanceof TAComercial)
			return comprobarComercial((TAComercial) tAvion);
		else
			return comprobarPrivado((TAPrivado) tAvion);
	}

	public static boolean comprobarComercial(TAComercial tComercial) {
		return comprobarInfo(tComercial) && comprobarEmpresa(tComercial);
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

	public static boolean comprobarEmpresa(TAComercial tComercial) {
		return tComercial.getEmpresa().matches("[a-zA-Z]+");
	}

	public static boolean comprobarAsientos(int asientos) {
		return asientos > 0;
	}

	public static boolean comprobarMatricula(String matricula) {
		// matriculas españolas: EC-(caracteres alfanumericos)
		return matricula.matches("^EC-[A-Za-z0-9]+$"); 
	}
}