package integracion;

public class Querys {
	//AEROLINEA
	
	//AVION
	
	//CONTRATO
	public static String altaContrato = "INSERT INTO contrato (Precio , Id_Aerolinea) VALUES(?,?)";
	public static String consultarContratoPorId = "SELECT * FROM Contrato WHERE Id=? FOR UPDATE";
	public static String modificarContrato = "UPDATE Contrato SET Precio = ?, Id_Aerolinea = ? WHERE Id = ?";
	public static String consultarTodosContratos = "SELECT * FROM Contrato FOR UPDATE";
	public static String consultarContratosPorAerolinea = "SELECT * FROM Contrato WHERE Id_Aerolinea = ?";	
	
	//LINEA DE CONTRATO
	public static String altaLineaContrato = "INSERT INTO linea_contrato (Id_Hangar, Id_Contrato, Fecha_Ini, Fecha_Fin, precio) VALUES(?,?,?,?,?)";
	public static String modificarLineaContrato = "UPDATE linea_contrato SET Fecha_Ini = ?, Fecha_fin = ?, precio = ? WHERE Id_Hangar = ? AND Id_Contrato = ?";
	public static String consultarLineasPorContrato = "SELECT * FROM linea_contrato WHERE Id_Contrato = ? FOR UPDATE";
	public static String consultarLineasPorHangar = "SELECT * FROM linea_contrato WHERE Id_Hangar = ? FOR UPDATE";
	public static String consultarLineaContrato = "SELECT * FROM linea_contrato WHERE Id_Contrato = ? AND Id_Hangar = ?";
	public static String consultarContratoPorAerolineaPrecioDuracion = "SELECT lc.Id_Hangar,  lc.Id_Contrato,  lc.Fecha_Ini,  lc.Fecha_Fin,  lc.precio FROM Linea_Contrato lc "
			+ "JOIN Contrato c ON lc.Id_Contrato = c.Id JOIN Aerolinea a ON c.Id_Aerolinea = a.Id WHERE a.Id = ?  AND lc.precio > ? AND "
			+ "DATEDIFF( STR_TO_DATE(lc.Fecha_Fin, '%d-%m-%Y'),  STR_TO_DATE(lc.Fecha_Ini, '%d-%m-%Y') ) > ?;";
	
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
