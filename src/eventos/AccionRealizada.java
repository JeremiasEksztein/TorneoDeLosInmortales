package eventos;

public class AccionRealizada extends Acontecimiento {

    private final String nomEjecutor;
    private final String nombreAccion;
    private final String nomObjetivo;
    private final int puntos;

    public AccionRealizada(String nomEjecutor, String nombreAccion,
                           String nomObjetivo, int puntos) {
    	super(nomEjecutor == nomObjetivo
    	        ? "El combatiente " + nomEjecutor + " usa " + nombreAccion + " sobre sí mismo (" + puntos + " puntos)"
    	        : "El combatiente " + nomEjecutor + " usa " + nombreAccion + " sobre el combatiente " + nomObjetivo + " (" + puntos + " puntos)");
        this.nomEjecutor = nomEjecutor;
        this.nombreAccion = nombreAccion;
        this.nomObjetivo = nomObjetivo;
        this.puntos = puntos;
    }

    public String getNomEjecutor() { return nomEjecutor; }
    public String getNombreAccion() { return nombreAccion; }
    public String getNomObjetivo() { return nomObjetivo; }
    public int getPuntos() { return puntos; }
}