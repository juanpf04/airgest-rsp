package presentacion.comandos.personal;

import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personal.TPersonal;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarPersonalPorId implements Comando {

    @Override
    public Contexto ejecutar(Object datos) {
        FactoriaNegocio fn = FactoriaNegocio.getInstance();
        SAPersonal sp = fn.crearSAPersonal();
        TPersonal personal = sp.consultarPersonalPorId((int) datos);
        Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_PERSONAL_POR_ID;
        return new Contexto(evento, personal);
    }
}
