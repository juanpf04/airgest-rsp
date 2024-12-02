
package negocio.empleado;


public class ValidadorEmpleado {
	
	public static boolean comprobarDatos(TEmpleado empleado) {
		boolean comprobacionGeneral = comprobarTag(empleado.getTag()) 
				&& comprobarHorasMensuales(empleado.getHorasMensuales())
				&& comprobarIdDepartamemto(empleado.getIdDepartamento());
		if(empleado instanceof TGerente) comprobacionGeneral = comprobacionGeneral && comprobarGerente((TGerente)empleado);
		else comprobacionGeneral = comprobacionGeneral && comprobarDependiente((TDependiente)empleado);
		return comprobacionGeneral;
	}
	
	public static boolean comprobarTag(int tag) {
		return tag > 0;
	}
	
	public static boolean comprobarHorasMensuales(int horasMensuales) {
		return horasMensuales > 0;
	}
	
	public static boolean comprobarIdDepartamemto(int idDepartamento) {
		return idDepartamento > 0;
	}

	public static boolean comprobarDespacho(int despacho) {
		return despacho > 0;
	}
	
	public static boolean comprobarHorasExtra(int horasExtra) {
		return horasExtra >= 0;
	}
	
	public static boolean comprobarGerente(TGerente gerente) {
		
		return comprobarDespacho(gerente.getDespacho()) && comprobarHorasExtra(gerente.getHorasExtra());
	}
	
	public static boolean comprobarSeccion(int seccion) {
		return seccion > 0;
	}
	
	public static boolean comprobarDependiente(TDependiente dependiente) {
		
		return comprobarSeccion(dependiente.getSeccion());
	}
}