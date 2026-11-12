package excepciones;

public class CantidadDeParejasInvalidaException extends TorneoException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CantidadDeParejasInvalidaException(String mensaje) {
        super(mensaje);
    }
}