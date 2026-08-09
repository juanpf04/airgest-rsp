package negocio.proveedor;

import java.util.List;

public interface SAProveedor {

	public int altaProveedor(TProveedor proveedor);

	public boolean bajaProveedor(int id);

	public TProveedor consultarProveedorPorId(int id);

	public List<TProveedor> consultarProveedores();

	public List<TProveedor> consultarProveedoresPorProducto(int idProducto);

	public boolean modificarProveedor(TProveedor proveedor);

	public boolean vincularProveedorProducto(int idProducto, int idProveedor);

	public boolean desvincularProveedorProducto(int idProducto, int idProveedor);
}