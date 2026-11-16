package eventos;

public class AccionRealizada extends Acontecimiento {

    private final int idEjecutor;
    private final String nombreAccion;
    private final int idObjetivo;
    private final int puntos;

    public AccionRealizada(int idEjecutor, String nombreAccion,
                           int idObjetivo, int puntos) {
    	super(idEjecutor == idObjetivo
    	        ? "El combatiente " + idEjecutor + " usa " + nombreAccion + " sobre sí mismo (" + puntos + " puntos)"
    	        : "El combatiente " + idEjecutor + " usa " + nombreAccion + " sobre el combatiente " + idObjetivo + " (" + puntos + " puntos)");
        this.idEjecutor = idEjecutor;
        this.nombreAccion = nombreAccion;
        this.idObjetivo = idObjetivo;
        this.puntos = puntos;
    }

    public int getIdEjecutor() { return idEjecutor; }
    public String getNombreAccion() { return nombreAccion; }
    public int getIdObjetivo() { return idObjetivo; }
    public int getPuntos() { return puntos; }
}