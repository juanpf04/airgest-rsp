package integracion;


public class Queries {
	//AEROLINEA
	
	public static String altaAerolinea = "INSERT INTO AEROLINEA (Nombre, Activo) VALUES(?,?)" ;
	public static String bajaAerolinea = "UPDATE Aerolinea SET Activo = ? WHERE Id = ?";
	public static String modificarAerolinea = "UPDATE Aerolinea SET Nombre = ?, Activo = ? WHERE Id = ?";
	public static String consultarAerolineaPorId = "SELECT * FROM Aerolinea WHERE Id=? FOR UPDATE";
	public static String consultarAerolineaPorNombre = "SELECT * FROM Aerolinea WHERE Nombre=? FOR UPDATE";
	public static String consultarTodasAerolineas = "SELECT * FROM Aerolinea FOR UPDATE";
	public static String consultarAerolineasPorModelo = "SELECT * FROM aerolinea a JOIN aerolinea_modelo am ON a.Id = am.Id_Aerolinea WHERE am.Id_Modelo = ? FOR UPDATE";
	
	
	//AVION
	
	public static String altaAvion = "INSERT INTO AVION (Nombre, Num_Asientos, Matricula, Fecha_Fabricacion, Id_Hangar, Id_Modelo, Id_Aerolinea, Activo) VALUES(?,?,?,?,?,?,?,true)";
	public static String altaAvionComercial = "INSERT INTO COMERCIAL (Id_Avion, Empresa) VALUES(?,?)";
	public static String altaAvionPrivado = "INSERT INTO PRIVADO (Id_Avion, Nombre_Duenyo, Carnet) VALUES(?,?,?)";
	public static String bajaAvion = "UPDATE AVION SET Activo = false WHERE Id = ?";
	public static String consultarAvionPorId = "SELECT * FROM Avion WHERE Id=? FOR UPDATE ";
	public static String consultarComercialPorId = "SELECT * FROM Comercial WHERE Id_Avion = ? FOR UPDATE ";
	public static String consultarPrivadoPorId = "SELECT * FROM Privado WHERE Id_Avion = ? FOR UPDATE ";
	public static String consultarAvionPorMatricula = "SELECT * FROM avion av WHERE av.Matricula = ? FOR UPDATE";
	public static String consultarTodosAviones = "SELECT * FROM Avion FOR UPDATE";
	public static String modificarAvion = "UPDATE Avion SET Nombre = ?, Num_Asientos = ?, Matricula = ?, Fecha_Fabricacion = ?, Id_Hangar = ?, Id_Modelo = ?, Id_Aerolinea = ?, Activo = ? WHERE Id = ?";
	public static String modificarComercial = "UPDATE Comercial SET Empresa = ? WHERE Id_avion = ?";
	public static String modificarPrivado = "UPDATE Privado SET Nombre_Duenyo = ?, Carnet = ? WHERE Id_avion = ?";
	public static String eliminarComercial = "DELETE FROM Comercial WHERE Id_avion = ?";
	public static String eliminarPrivado = "DELETE FROM Privado WHERE Id_avion = ?";
	public static String consultarAvionesPorModelo = "SELECT * FROM avion av WHERE av.Id_Modelo = ? FOR UPDATE";
	public static String consultarAvionesActivosPorModelo = "SELECT * FROM avion av WHERE av.Id_Modelo = ? AND av.Activo = true FOR UPDATE";
	public static String consultarAvionesPorAerolinea = "SELECT * FROM avion av WHERE av.Id_Aerolinea = ? FOR UPDATE";
	public static String consultarAvionesActivosPorAerolinea = "SELECT * FROM avion av WHERE av.Id_Aerolinea = ? AND av.Activo = true FOR UPDATE";
	public static String consultarAvionesPorHangar = "SELECT * FROM avion av WHERE av.Id_Hangar = ? FOR UPDATE";
	public static String consultarAvionesActivosPorHangar = "SELECT * FROM avion av WHERE av.Id_Hangar = ? AND av.Activo = true FOR UPDATE";
	public static String consultarAvionesDeAerolineaPorHangar = "SELECT * FROM Avion WHERE id_aerolinea = ? AND id_hangar = ? FOR UPDATE";
	
	
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
	
	
	//PERSONAL HANGAR
	public static String vincularPersonalHangar = "INSERT INTO PERSONAL_HANGAR (Id_personal, Id_hangar) VALUES (?, ?)";
	public static String desvincularPersonalHangar = "DELETE FROM PERSONAL_HANGAR WHERE Id_personal = ? AND Id_hangar = ?";
	public static String comprobarvinculacionPersonalHangar = "SELECT COUNT(*) AS NUM FROM PERSONAL_HANGAR WHERE Id_personal = ? AND Id_hangar = ? FOR UPDATE";
	

	//MODELO AEROLINEA
	public static String vincularModeloAerolinea = "INSERT INTO aerolinea_modelo (Id_Aerolinea, Id_Modelo) VALUES (?, ?)";
	public static String desvincularModeloAerolinea = "DELETE FROM aerolinea_modelo WHERE Id_Aerolinea = ? AND Id_Modelo = ?";
	public static String comprobarvinculacion = "SELECT * FROM aerolinea_modelo WHERE Id_Aerolinea = ? AND Id_Modelo = ? FOR UPDATE";
	public static String comprobarvinculacionAerolinea = "SELECT * FROM aerolinea_modelo WHERE Id_Aerolinea = ? FOR UPDATE;";
	public static String comprobarvinculacionModelo = "SELECT * FROM aerolinea_modelo WHERE Id_Modelo = ? FOR UPDATE";
	
	
	
	//MODELO
	
	public static String alta_modelo = "INSERT INTO Modelo (Nombre, motor, activo) VALUES (?, ?, true)";
	public static String baja_modelo = "UPDATE Modelo SET activo = false WHERE id = ?";
	public static String modificar_modelo = "UPDATE Modelo SET nombre = ?, motor = ?, activo = ? WHERE id = ?";
	public static String consultar_modelo_por_nombre = "SELECT * FROM Modelo WHERE nombre = ? FOR UPDATE";
	public static String consultar_modelo_por_id = "SELECT * FROM Modelo WHERE id = ? FOR UPDATE";
	public static String consultar_todos_modelos = "SELECT * FROM Modelo FOR UPDATE";
	public static String consultar_modelo_por_aerolinea = "SELECT * FROM Modelo m JOIN Aerolinea_modelo a ON m.id = a.Id_Modelo WHERE a.id_aerolinea = ? FOR UPDATE";
	
	
	//PERSONAL
	public static String altaPersonal = "INSERT INTO PERSONAL(dni, area_asignada, activo) VALUES (?, ?, true)";
	public static String altaLimpieza = "INSERT INTO PERSONAL_LIMPIEZA(id_personal, rol) VALUES (?, ?)";
	public static String altaSeguridad = "INSERT INTO PERSONAL_SEGURIDAD(id_personal, placa) VALUES (?, ?)";
	public static String bajaPersonal = "UPDATE PERSONAL SET activo = false WHERE id = ?";
	public static String modificarPersonal= "UPDATE PERSONAL SET Area_Asignada = ?, DNI = ?, Activo = ? WHERE Id = ?";
	public static String modificarLimpieza= "UPDATE PERSONAL_LIMPIEZA SET rol = ? WHERE Id_personal = ?";
	public static String modificarSeguridad= "UPDATE PERSONAL_SEGURIDAD SET Placa = ? WHERE Id_personal = ?";
	public static String eliminarLimpieza = "DELETE FROM PERSONAL_LIMPIEZA WHERE Id_personal = ?";
    public static String eliminarSeguridad = "DELETE FROM PERSONAL_SEGURIDAD WHERE Id_personal = ?";
    public static String consultarPersonalPorId = "SELECT * FROM Personal WHERE Id=? FOR UPDATE ";
    public static String consultarLimpiezaPorId = "SELECT * FROM PERSONAL_LIMPIEZA WHERE Id_personal=? FOR UPDATE ";
    public static String consultarSeguridadPorId = "SELECT * FROM PERSONAL_SEGURIDAD WHERE Id_personal=? FOR UPDATE ";
	public static String consultarPersonalPorDni = "SELECT * FROM PERSONAL WHERE dni = ?";
	public static String consultarPersonalExistente = "SELECT * FROM PERSONAL FOR UPDATE";
	public static String consultarPersonalPorHangar = "SELECT * FROM PERSONAL p JOIN personal_hangar ph ON p.Id = ph.Id_personal WHERE ph.Id_Hangar = ? FOR UPDATE";
}
