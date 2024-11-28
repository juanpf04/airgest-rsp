
package negocio.departamento;

public class ValidadorDepartamento {

	public static boolean comprobarDatos(TDepartamento departamento) {
		
		String nombreDpt = departamento.getNombre();
		return nombreDpt.matches("[a-zA-Z]+[0-9]+") ; //el nombre del departamento es una cadena con letras y numeros, sin orden definido 
		
	}
}