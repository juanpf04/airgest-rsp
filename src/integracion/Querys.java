package integracion;

public class Querys {
	//AEROLINEA
	
	public static String altaAerolinea = "INSERT INTO AEROLINEA (Nombre, Activo) VALUES(?,?)" ;
	public static String bajaAerolinea = "UPDATE Aerolinea SET Activo = ? WHERE Id = ?";
	public static String modificarAerolinea = "UPDATE Aerolinea SET Nombre = ?, Activo = ? WHERE Id = ?";
	public static String consultarAerolineaPorId = "SELECT * FROM Aerolinea WHERE Id=? FOR UPDATE";
	public static String consultarAerolineaPorNombre = "SELECT * FROM Aerolinea WHERE Nombre=? FOR UPDATE";
	public static String consultarTodasAerolineas = "SELECT * FROM Aerolinea FOR UPDATE";
	public static String consultarAerolineasPorModelo = "SELECT * FROM aerolinea a JOIN aerolinea_modelo am ON a.Id = am.Id_Aerolinea WHERE am.Id_Modelo = ? FOR UPDATE";
	
	
	//AVION
	
	public static String altaAvion = "INSERT INTO AVION (Nombre, Activo) VALUES(?,?)";
	public static String bajaAvion = "UPDATE AVION SET Activo = false WHERE Id = ?";
	public static String consultarAvionPorId = "SELECT * FROM Avion WHERE Id=? FOR UPDATE ";
	public static String consultarAvionPorMatricula = "SELECT * FROM avion av WHERE av.Matricula = ? FOR UPDATE";
	public static String consultarTodosAviones = "SELECT * FROM Avion FOR UPDATE";
	public static String modificarAvion = "UPDATE Avion SET Nombre = ?, Num_Asientos = ?, Matricula = ?, Fecha_Fabricacion = ?, Id_Hangar = ?, Id_Modelo = ?, Id_Aerolinea = ?, Activo = ? WHERE Id = ?";
	public static String consultarAvionesPorModelo = "SELECT * FROM avion av JOIN modelo ON av.Id_Modelo = ? FOR UPDATE";
	public static String consultarAvionesActivosPorModelo = "SELECT * FROM avion av JOIN modelo ON av.Id_Modelo = ? AND av.Activo = true FOR UPDATE";
	public static String consultarAvionesPorAerolinea = "SELECT * FROM avion av JOIN aerolinea ON av.Id_Aerolinea = ? FOR UPDATE";
	public static String consultarAvionesActivosPorAerolinea = "SELECT * FROM avion av JOIN aerolinea ON av.Id_Aerolinea = ? AND av.Activo = true FOR UPDATE";
	public static String consultarAvionesPorHangar = "SELECT * FROM avion av JOIN hangar ON av.Id_Hangar = ? FOR UPDATE";
	public static String consultarAvionesActivosPorHangar = "SELECT * FROM avion av JOIN hangar ON av.Id_Hangar = ? AND av.Activo = true FOR UPDATE";

	
	
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
	
	//PERSONAL
	public static String bajaPersonal = "UPDATE PERSONAL SET activo = false WHERE id = ?";
	public static String consultarPersonalExistente = "SELECT * FROM PERSONAL FOR UPDATE";
	public static String consultarPersonalPorHangar = "SELECT * FROM PERSONAL p JOIN personal_hangar ph ON p.Id = ph.Id_personal WHERE am.Id_Hangar = ? FOR UPDATE";
}
