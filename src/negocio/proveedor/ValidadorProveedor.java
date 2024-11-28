package negocio.proveedor;

import negocio.UtilidadesN;

public class ValidadorProveedor {
	
	public static boolean comprobarDatos(TProveedor datos) {
		return false;
	}

	public static boolean comprobarCodigoPostal(String cp)
	{
		return cp.matches("[0-9]{5}");
	}
	
	public static boolean comprobarNacional(TNacional nacional) {
		return UtilidadesN.comprobarId(nacional.getId()) && comprobarCodigoPostal(nacional.getCodigoPostal());
	}

	public static boolean comprobarPais(String pais)
	{
		return pais.matches("[a-zA-Z]+");
	}
	
	public static boolean comprobarImpuestos(double imp)
	{
		return imp >= 0;
	}
	
	public static boolean comprobarInternacional(TInternacional internacional) {
		return  UtilidadesN.comprobarId(internacional.getId()) && comprobarPais(internacional.getPais()) && comprobarImpuestos(internacional.getImpuesto());
	}

}