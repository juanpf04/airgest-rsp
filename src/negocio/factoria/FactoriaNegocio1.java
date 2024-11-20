package negocio.factoria;

import negocio.aerolinea.SAAerolinea;
import negocio.avion.SAAvion;
import negocio.contrato.SAContrato;
import negocio.hangar.SAHangar;
import negocio.modelo.SAModelo;
import negocio.personal.SAPersonal;

public interface FactoriaNegocio1 {

	public SAModelo crearSAModelo();

	public SAHangar crearSAHangar();

	public SAAvion crearSAAvion();

	public SAAerolinea crearSAAerolinea();

	public SAPersonal crearSAPersonal();

	public SAContrato crearSAContrato();
}
