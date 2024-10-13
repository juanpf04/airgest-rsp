package presentacion.comandos.personal;

import java.util.List;

import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personal.TPersonal;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarPersonalPorHangar implements Comando {

    @Override
    public Contexto ejecutar(Object datos) {
        FactoriaNegocio fn = FactoriaNegocio.getInstance();
        SAPersonal sp = fn.crearSAPersonal();
        List<TPersonal> personal = sp.consultarPersonalPorHangar((int) datos);
        Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_PERSONAL_POR_HANGAR;
        return new Contexto(evento, personal);
    }
}
