package eventos;

import combatientes.Combatiente;

public class Eliminacion extends Acontecimiento {

    private final int idCombatiente;
    private final String nombre;

    public Eliminacion(Combatiente rival) {
        super(rival.getNombre() + " (combatiente " + rival.getId() + ") queda fuera de combate");
        this.idCombatiente = rival.getId();
        this.nombre = rival.getNombre();
    }

    
	public Eliminacion(int i, String string) {
		super(string + " (combatiente " + i + ") queda fuera de combate");
		this.idCombatiente = i;
		this.nombre = string;
	}


	public int getIdCombatiente() { return idCombatiente; }
    public String getNombre() { return nombre; }
}