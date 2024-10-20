package integracion;

public class Querys {
	//AEROLINEA
	
	//AVION
	
	//CONTRATO
	
	
	//HANGAR
	public static String alta_hangar = "INSERT INTO HANGAR (Stock, direccion , espacio_almacenaje, coste_dia, activo) VALUES (?, ?, ?, ?, 1)";
	public static String baja_hangar = "UPDATE HANGAR SET activo = false WHERE id = ?";
	public static String leerHangarPorId = "SELECT * FROM HANGAR WHERE id = ?";
	public static String consultarTodosHangares = "SELECT * FROM HANGAR";
	public static String modificarHangar = "UPDATE HANGAR SET Stock = ?, direccion = ?, espacio_almacenaje = ?, coste_dia = ?, activo = ? WHERE id = ?";
	public static String leerHangarPorDireccion = "SELECT * FROM HANGAR WHERE direccion = ?";
	public static String actualizaStock = "UPDATE HANGAR SET Stock = ? WHERE id = ?";
	
	//MODELO
	
	//PERSONAL
		
		
}
