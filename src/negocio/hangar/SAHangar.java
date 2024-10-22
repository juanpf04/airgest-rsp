package negocio.hangar;

import java.util.List;

public interface SAHangar {

	public int altaHangar(THangar tHangar);

	public boolean bajaHangar(int id);//LLAMAR A FUNCION DE COMPROBAR Q NO TENGA AVIONES ACTIVOS PARA PODER DAR DE BAJA
	//NO SE PUEDE HACER HASTA Q NO ESTÉ HECHO AVION :)

	public THangar consultarHangarPorId(int id);

	public List<THangar> consultarTodosHangares();

	public boolean modificarHangar(THangar tHangar);
	
	public List<THangar> consultarHangarPorPersonal(int id_personal);//HASTA Q NO TENGAMOS PERSONAL HECHO, NO SE PUEDE HACER

}