package combate;
import combatientes.Combatiente;

public class Pareja {
	private int id;
	private Combatiente combatienteA;
	private Combatiente combatienteB;
	
	
	public int vidaTotal() {
		
	}
	public boolean estaEnPie() {
		
	}
	public void integrantesEnOrden() {
		
	}
	public boolean puedeUsar() {
		
	}
	public void reiniciarParaCombate() {
		
	}
	public int getId() {
		return this.id;
	}
	public Combatiente getCombatienteA() {
		return this.combatienteA;
	}
	public Combatiente getCombatienteB() {
		return this.combatienteB;
	}
	
	@Override 
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || this.getClass() != o.getClass()) {
			return false;
		}
		Pareja p = (Pareja) o;
		return this.id == p.id;
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(this.id);
	}
}
