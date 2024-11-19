package negocio.contrato;

import negocio.lineaContrato.TLineaContrato;

public class ValidadorLineaContrato {

	public static boolean comprobarDatos(TLineaContrato tLineaContrato) {
		return true;
	}

	public static boolean comprobarQuery(int id_aerolinea, double precio, int duracion) {
		return id_aerolinea > 0 && precio >= 0 && duracion >= 0;
	}
}