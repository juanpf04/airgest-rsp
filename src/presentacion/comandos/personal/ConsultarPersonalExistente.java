package presentacion.comandos.personal;

import java.util.List;

import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personal.TPersonal;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarPersonalExistente implements Comando {

    @Override
    public Contexto ejecutar(Object datos) {
        FactoriaNegocio fn = FactoriaNegocio.getInstance();
        SAPersonal sp = fn.crearSAPersonal();
        List<TPersonal> personal = sp.consultarPersonalExistente();
        Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_PERSONAL_EXISTENTE;
        return new Contexto(evento, personal);
    }
}
