package excepciones;
//Excepcion que ocurre al intentar reinscribir a un combatiente ya inscripto.
public class InscripcionDuplicadaException extends TorneoException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InscripcionDuplicadaException(String mensaje) {
        super(mensaje);
    }
}