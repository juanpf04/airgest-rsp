package integracion;

public class Querys {
	//AEROLINEA
	
	//AVION
	
	//CONTRATO
	public static String alta_contrato = "INSERT INTO contrato (Precio , Id_Aerolinea) VALUES(?,?)";
	public static String leerContratoPorId = "SELECT * FROM Contrato WHERE Id=? FOR UPDATE";
	public static String modificarContrato = "UPDATE Contrato SET Precio = ?, Id_Aerolinea = ? WHERE Id = ?";
	public static String leerTodosContratos = "SELECT * FROM Contrato FOR UPDATE";
	public static String leerContratosPorAerolinea = "SELECT * FROM Contrato WHERE Id_Aerolinea = ?";	
	
	//LINEA DE CONTRATO
	public static String alta_linea_contrato = "INSERT INTO linea_contrato (Id_Hangar, Id_Contrato, Fecha_Ini, Fecha_Fin, Coste_Por_Dia) VALUES(?,?,?,?,?)";
	
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
	
	//PERSONAL
		
		
}
