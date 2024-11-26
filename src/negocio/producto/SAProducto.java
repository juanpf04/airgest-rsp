
package negocio.producto;

import java.util.List;

public interface SAProducto {

	public int altaProducto(TProducto producto);

	public boolean bajaProducto(int id);

	public TProducto consultarProductoPorId(int id);

	public List<TProducto> consultarProductos();

	public boolean modificarProducto(TProducto tProducto);

	public List<TProducto> consultarProductosPorMarca(int idMarca);

	public List<TProducto> consultarProductosPorProveedor(int idProveedor);
}