package combate;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

import combatientes.Combatiente;
import tecnicas.Tecnica;

public class Pareja {
	private final int id;
	private static int contadorId = 1;
	private final Combatiente combatienteA;
	private final Combatiente combatienteB;


	public Pareja(int id, Combatiente combatienteA, Combatiente combatienteB) {
		if (id <= 0) {
			throw new IllegalArgumentException("El identificador de la pareja debe ser positivo.");
		}
		if (combatienteA == null || combatienteB == null) {
			throw new IllegalArgumentException("Los combatientes de la pareja no pueden ser nulos.");
		}
		if (combatienteA.equals(combatienteB)) {
			throw new IllegalArgumentException("Un mismo combatiente no puede formar pareja consigo mismo.");
		}
		this.id = id;
		this.combatienteA = combatienteA;
		this.combatienteB = combatienteB;
	}
	
	public Pareja(Combatiente combatienteA, Combatiente combatienteB) {
		this(contadorId++, combatienteA, combatienteB);
	}
	
	public static void reiniciarContadorId() {
		contadorId = 1;
	}
	
	public int vidaTotal() {
		return (combatienteA.getVida() + combatienteB.getVida());
	}
	
	public boolean estaEnPie() {
		return combatienteA.estaEnPie() || combatienteB.estaEnPie();	
	}

	public boolean estaDerrotada() {
		return !estaEnPie();
	}

	// le cambie el tipo porque se usa en "Combate" pero antes devolvia un void
	public List<Combatiente> integrantesEnOrden() {
		List<Combatiente> integrantes = new ArrayList<>(Arrays.asList(combatienteA, combatienteB));
		integrantes.sort(Comparator.comparingInt(Combatiente::getId));
		return integrantes;
	}

	public List<Combatiente> getCombatientesEnPieOrdenados() {
		List<Combatiente> resultado = new ArrayList<>();
		for (Combatiente combatiente : integrantesEnOrden()) {
			if (combatiente.estaEnPie()) {
				resultado.add(combatiente);
			}
		}
		return resultado;
	}

	public Combatiente getCompanieroDe(Combatiente combatiente) {
		if (combatienteA.equals(combatiente)) {
			return combatienteB;
		}
		if (combatienteB.equals(combatiente)) {
			return combatienteA;
		}
		throw new IllegalArgumentException("El combatiente no pertenece a esta pareja.");
	}
	
	public boolean puedeUsar(Combatiente ejecutor, Tecnica tecnica) {

		if(ejecutor==null) {
			throw new IllegalArgumentException("El ejecutor no puede ser nulo.");
		}
		
		if(tecnica==null) {
			throw new IllegalArgumentException("La técnica no puede ser nula.");
		}
        Combatiente companiero = getCompanieroDe(ejecutor);

        // se bloquea si tu compañero uso la misma tecnica
        boolean bloqueadaPorCompaniero = companiero.usoEnEsteCombate(tecnica)
                && companiero.estaEnPie()
                && companiero.usosRestantes(tecnica) > 0;

        return !bloqueadaPorCompaniero;
    }
	
	public void reiniciarParaCombate() {
		combatienteA.reiniciarParaCombate();
		combatienteB.reiniciarParaCombate();
		
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
	public void recuperarEnergiaAsalto(int energia) {
		for (Combatiente c : getCombatientesEnPieOrdenados()) {
			c.recuperarEnergia(energia);
		}		
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
