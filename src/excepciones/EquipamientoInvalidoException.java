package excepciones;

public class EquipamientoInvalidoException extends TorneoException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EquipamientoInvalidoException(String mensaje) {
        super(mensaje);
    }
}