package integracion.avion;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import integracion.factoria.FactoriaIntegracion;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.avion.TAComercial;
import negocio.avion.TAPrivado;
import negocio.avion.TAvion;

public class DAOAvionImpTest {

    @Test
    public void consultarAvionesPorModelo_test() {
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
        List<TAvion> aviones = da.consultarAvionesPorModelo(1);
        assertEquals("tiene que haber 1 aviones con modelo 1", 2, aviones.size());
        
        aviones = da.consultarAvionesPorModelo(2);
        assertEquals("tiene que haber 1 aviones con modelo 2", 1, aviones.size());
        
        t.commit();
    }

    @Test
    public void consultarAvionesActivosPorModelo_test() {
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
        List<TAvion> aviones = da.consultarAvionesActivosPorModelo(1);
        assertEquals("tiene que haber 2 aviones activos con modelo 1", 2, aviones.size());
        
        aviones = da.consultarAvionesActivosPorModelo(2);
        assertEquals("tiene que haber 0 aviones activos con modelo 2", 0, aviones.size());
        
        t.commit();
    }

    @Test
    public void consultarAvionPorId_test() {
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
        TAvion a = da.consultarAvionPorId(2);
        assertEquals("el avion con id 2 tiene 5 asientos", 5, a.getNumAsientos());
        t.commit();
        
        System.out.println(a);
    }

    @Test
    public void consultarAvionesPorMatricula_test() {
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
        TAvion a = da.consultarAvionPorMatricula("EC-123ASD");
        assertEquals("el avion con matricula EC-123ASD tiene id 1", 1,a.getId());
        
        t.commit();
        
        System.out.println(a);
    }

    @Test
    public void consultarTodosAviones_test() {
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
        List<TAvion> aviones = da.consultarTodosAviones();
        
        assertEquals("tiene que haber 2 aviones", 2, aviones.size());
        
        t.commit();
    }

    @Test
    public void consultarAvionesPorAerolinea_test() {
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
        List<TAvion> aviones = da.consultarAvionesPorAerolinea(1);
        assertEquals("tiene que haber 2 aviones con aerolinea 1", 2, aviones.size());
        
        aviones = da.consultarAvionesPorAerolinea(5);
        assertEquals("tiene que haber 0 aviones con aerolinea 5", 0, aviones.size());
        
        t.commit();
    }

    @Test
    public void consultar_aviones_por_hangar_test() {
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
        List<TAvion> aviones = da.consultarAvionesPorHangar(1);
        assertEquals("tiene que haber 2 aviones con hangar 1", 2, aviones.size());
        
        t.commit();
    }

    @Test
    public void consultar_aviones_activos_por_aerolinea_test() {
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
        List<TAvion> aviones = da.consultarAvionesActivosPorAerolinea(1);
        assertEquals("tiene que haber 2 aviones activos con aerolinea 1", 2, aviones.size());
        
        aviones = da.consultarAvionesActivosPorAerolinea(5);
        assertEquals("tiene que haber 0 aviones activos con aerolinea 5", 0, aviones.size());
        
        t.commit();
    }

    @Test
    public void consultar_aviones_activos_por_hangar_test() {
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
        List<TAvion> aviones = da.consultarAvionesActivosPorHangar(3);
        assertEquals("tiene que haber 0 aviones activos con hangar 3", 0, aviones.size());
        
        t.commit();
    }
    
    @Test
    public void alta_avion_test() {
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();

        TAvion avion = new TAComercial(0, 5, "06-12-2004", "nombrePrueba", "EC-1234", 
                true, 1, 1, 1, "Empresa");
        //assertEquals("Deberia darse de alta el avion comercial", 1, da.altaAvion(avion));
        
        avion = new TAPrivado(0, 5, "06-12-2004", "nombrePrueba2", "EC-69", 
                true, 1, 1, 1, "Patricio", 7);
        assertEquals("Deberia darse de alta el avion privado", 2, da.altaAvion(avion));
        
        t.commit();
    }
    
    @Test
    public void modificar_avion_test() {
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
        TAvion avion = new TAComercial(1, 7, "06-12-2004", "nombrePruebaModif", "EC-123ASD", 
                true, 1, 1, 1, "UCM");
        assertTrue("Deberia modificarse el avion comercial", da.modificarAvion(avion));
        
        avion = new TAPrivado(2, 5, "06-12-2004", "nombrePruebaModif2", "EC-2Cambio", 
                true, 1, 1, 1, "Pablo", 7);
        assertTrue("Deberia modificarse el avion privado", da.modificarAvion(avion));
        
        t.commit();
    }
    
    @Test
    public void baja_avion_test() {
        Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
        assertTrue("Deberia darse de baja el avion", da.bajaAvion(2));
        
        t.commit();
    }
    
    @Test 
    public void consultar_aviones_aerolinea_por_hangar(){
    	Transaction t = TransactionManager.getInstance().nuevaTransaccion();
        t.start();
        
        DAOAvion da = FactoriaIntegracion.getInstance().crearDAOAvion();
        List<TAvion> aviones = da.consultarAvionesDeAerolineaPorHangar(1, 1);
        assertEquals("Deberia haber dos aviones", 2, aviones.size());
        t.commit();
        System.out.println(aviones);
    }
}
