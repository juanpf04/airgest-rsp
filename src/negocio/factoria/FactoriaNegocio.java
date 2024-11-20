package negocio.factoria;

import java.io.BufferedReader;
import java.io.FileReader;

import negocio.aerolinea.SAAerolinea;
import negocio.avion.SAAvion;
import negocio.contrato.SAContrato;
import negocio.departamento.SADepartamento;
import negocio.empleado.SAEmpleado;
import negocio.hangar.SAHangar;
import negocio.marca.SAMarca;
import negocio.modelo.SAModelo;
import negocio.personal.SAPersonal;
import negocio.producto.SAProducto;
import negocio.proveedor.SAProveedor;
import negocio.venta.SAVenta;

public abstract class FactoriaNegocio {

	private static FactoriaNegocio instancia;

	public synchronized static FactoriaNegocio getInstance() {
		if (instancia == null)
			instancia = getFactoriaNegocioImp();
		return instancia;
	}

	private synchronized static FactoriaNegocio getFactoriaNegocioImp() {
		String claseFactoria = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader("recursos/configuraciones/FactoriaNegocio.txt"));
			claseFactoria = in.readLine();
			in.close();
		} catch (java.io.IOException e) {
			System.out.println("Problema de E/S");
		}
		try {
			return (FactoriaNegocio) Class.forName(claseFactoria).newInstance();
		} catch (Exception e) {
			System.out.println("Implementación de FabricaDeLaberintos no encontrada");
		}
		return null;
	}
	
	public abstract SAVenta crearSAVenta();

	public abstract SAProveedor crearSAProveedor();

	public abstract SAProducto crearSAProducto();

	public abstract SAMarca crearSAMarca();

	public abstract SADepartamento crearSADepartamento();

	public abstract SAEmpleado crearSAEmpleado();
	
	public abstract SAModelo crearSAModelo();

	public abstract SAHangar crearSAHangar();

	public abstract SAAvion crearSAAvion();

	public abstract SAAerolinea crearSAAerolinea();

	public abstract SAPersonal crearSAPersonal();

	public abstract SAContrato crearSAContrato();
}