package presentacion.controlador.comandos.personal;

import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personal.TPersonal;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ModificarPersonal implements Comando {

    @Override
    public Contexto ejecutar(Object datos) {
        FactoriaNegocio fn = FactoriaNegocio.getInstance();
        SAPersonal sp = fn.crearSAPersonal();
        boolean exito = sp.modificarPersonal((TPersonal) datos);
        Evento evento = null;
        if (exito) {
            evento = Evento.VISTA_EXITO_MODIFICAR_PERSONAL;
        } else {
            evento = Evento.VISTA_FALLO_MODIFICAR_PERSONAL;
        }
        return new Contexto(evento, exito);
    }
}
