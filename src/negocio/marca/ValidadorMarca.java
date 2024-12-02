package negocio.marca;

public class ValidadorMarca {

	public static boolean comprobarDatos(TMarca marca) {
		// marca pueden ser letras y numeros con espacios y guiones, origen solo letras sin espacios
		return marca.getNombre().matches("[a-zA-Z0-9\\s\\-]+") && marca.getOrigen().matches("[a-zA-Z]+");
	}
}