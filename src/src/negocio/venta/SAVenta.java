package negocio.venta;

import java.util.List;

public interface SAVenta {

	public TCarritoVenta abrirCarrito(int idEmpleado);

	public int cerrarVenta(TCarritoVenta tCarrito);

	public TInfoVenta consultarVentaPorId(int id);

	public List<TVenta> consultarVentas();

	public boolean devolucion(TLineaVenta tLineaVenta);

	public List<TVenta> consultarVentasPorEmpleado(int id);

	public boolean modificarVenta(TVenta tVenta);
}