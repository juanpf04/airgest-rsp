package presentacion.comandos.personal;

import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personalHangar.TPersonalHangar;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class VincularPersonal implements Comando {

    @Override
    public Contexto ejecutar(Object datos) {
        FactoriaNegocio fn = FactoriaNegocio.getInstance();
        SAPersonal sp = fn.crearSAPersonal();
        boolean exito = sp.vincularPersonal((TPersonalHangar) datos);
        Evento evento = null;
        if (exito) {
            evento = Evento.VISTA_EXITO_VINCULAR_PERSONAL;
        } else {
            evento = Evento.VISTA_FALLO_VINCULAR_PERSONAL;
        }
        return new Contexto(evento, exito);
    }
}
