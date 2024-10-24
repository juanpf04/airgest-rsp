package integracion;

public class Querys {
	//AEROLINEA
	
	//AVION
	
	//CONTRATO
	
	
	//HANGAR
	public static String alta_hangar = "INSERT INTO HANGAR (Stock, direccion , espacio_almacenaje, coste_dia, activo) VALUES (?, ?, ?, ?, true)";
	public static String baja_hangar = "UPDATE HANGAR SET activo = false WHERE id = ?";
	public static String leerHangarPorId = "SELECT * FROM HANGAR WHERE id = ? FOR UPDATE";
	public static String consultarTodosHangares = "SELECT * FROM HANGAR FOR UPDATE";
	public static String modificarHangar = "UPDATE HANGAR SET Stock = ?, direccion = ?, espacio_almacenaje = ?, coste_dia = ?, activo = ? WHERE id = ?";
	public static String leerHangarPorDireccion = "SELECT * FROM HANGAR WHERE direccion = ? FOR UPDATE";
	public static String actualizaStock = "UPDATE HANGAR SET Stock = ? WHERE id = ?";
	public static String consultarHangarPorPersonal = "SELECT * FROM hangar h JOIN personal_hangar ph ON h.Id = ph.Id_Hangar WHERE ph.Id_Personal = ? FOR UPDATE;";
	
	public static String vincularPersonalHangar = "INSERT INTO PERSONAL_HANGAR (Id_personal, Id_hangar) VALUES (?, ?)";
	public static String desvincularPersonalHangar = "DELETE FROM PERSONAL_HANGAR WHERE Id_personal = ? AND Id_hangar = ?";
	public static String comprobarvinculacionPersonalHangar = "SELECT COUNT(*) AS NUM FROM PERSONAL_HANGAR WHERE Id_personal = ? AND Id_hangar = ? FOR UPDATE";


	
	//MODELO
	
	public static String alta_modelo = "INSERT INTO Modelo (Nombre, motor, activo) VALUES (?, ?, true)";
	public static String baja_modelo = "UPDATE Modelo SET activo = false WHERE id = ?";
	public static String modificar_modelo = "UPDATE Modelo SET nombre = ?, motor = ?, activo = ? WHERE id = ?";
	public static String leer_modelo_por_nombre = "SELECT * FROM Modelo WHERE nombre = ? FOR UPDATE";
	public static String leer_modelo_por_id = "SELECT * FROM Modelo WHERE id = ? FOR UPDATE";
	public static String consultar_todos_modelos = "SELECT * FROM Modelo FOR UPDATE";
	public static String leer_modelo_por_aerolinea = "SELECT * FROM Modelo m JOIN Aerolinea_modelo a ON m.id = a.Id_Modelo WHERE a.id = ? FOR UPDATE";;
	
	
	//PERSONAL
		
		
}
