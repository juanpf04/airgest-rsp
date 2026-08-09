package negocio.personal;

public class ValidadorPersonal {

	public static boolean comprobarDatos(TPersonal tPersonal) {
		boolean valido = tPersonal.getAreaAsignada() != null && !tPersonal.getAreaAsignada().isEmpty()
				&& comprobarDNI(tPersonal.getDni());

		if (tPersonal instanceof TPLimpieza)
			valido = valido && ((TPLimpieza) tPersonal).getRol() != null
					&& !((TPLimpieza) tPersonal).getRol().isEmpty();
		else
			valido = valido && ((TPSeguridad) tPersonal).getNumPlaca() > 0;

		return valido;
	}

	public static boolean comprobarDNI(String dni) {
		return dni.matches("^[0-9]{8}+[A-Z]{1}+$");
	}

}