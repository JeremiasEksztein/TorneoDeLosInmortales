package excepciones;

public class TorneoException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TorneoException(String mensaje) {
        super(mensaje);
    }
}