package excepciones;

public class TipoDeCombatienteDesconocidoException extends TorneoException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TipoDeCombatienteDesconocidoException(String mensaje) {
        super(mensaje);
    }
}