package presentacion.controlador;

public class Contexto {

	private Evento evento;
	private Object info;

	public Contexto(Evento evento) {
		this(evento, null);
	}

	public Contexto(Evento evento, Object info) {
		this.evento = evento;
		this.info = info;
	}

	public Object getInfo() {
		return this.info;
	}

	public Evento getEvento() {
		return this.evento;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}