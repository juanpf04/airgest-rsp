
package negocio.departamento;

public class ValidadorDepartamento {

	public static boolean comprobarDatos(TDepartamento departamento) {
		
		String nombreDpt = departamento.getNombre();
		boolean condicion = (departamento.getSala() > 0) && (departamento.getSueldoHora() > 0);
		return nombreDpt.matches("[a-zA-Z]+") && condicion ; //el nombre del departamento es una cadena con letras y numeros, sin orden definido 
		
	}
}