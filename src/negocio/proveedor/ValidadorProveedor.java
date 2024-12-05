package negocio.proveedor;


public class ValidadorProveedor {
	
	public static boolean comprobarDatos(TProveedor datos) {
		if(datos instanceof TNacional)
			return comprobarNombre(datos.getNombre()) && comprobarNacional((TNacional)datos);
		else
			return comprobarNombre(datos.getNombre()) && comprobarInternacional((TInternacional)datos);
	}
	
	public static boolean comprobarNombre(String nombre)
	{
		return nombre.matches("[a-zA-Z]+");
	}

	public static boolean comprobarCodigoPostal(String cp)
	{
		return cp.matches("[0-9]{5}");
	}
	
	public static boolean comprobarNacional(TNacional nacional) {
		return comprobarCodigoPostal(nacional.getCodigoPostal());
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
		return comprobarPais(internacional.getPais()) && comprobarImpuestos(internacional.getImpuesto());
	}

}