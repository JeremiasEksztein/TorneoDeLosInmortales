package eventos;

public class Eliminacion extends Acontecimiento {

    private final int idCombatiente;
    private final String nombre;

    public Eliminacion(int idCombatiente, String nombre) {
        super(nombre + " (combatiente " + idCombatiente + ") queda fuera de combate");
        this.idCombatiente = idCombatiente;
        this.nombre = nombre;
    }

    public int getIdCombatiente() { return idCombatiente; }
    public String getNombre() { return nombre; }
}