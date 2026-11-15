package combate;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

import combatientes.Combatiente;
import tecnicas.Tecnica;

public class Pareja {
	private int id;
	private static int contadorId = 1;
	private Combatiente combatienteA;
	private Combatiente combatienteB;



	public Pareja(Combatiente combatienteA, Combatiente combatienteB) {
		this.id = this.contadorId;
		this.combatienteA = combatienteA;
		this.combatienteB = combatienteB;
		this.contadorId++;
	}
	
	public int vidaTotal() {
		return (combatienteA.getVida() + combatienteB.getVida());
		
	}
	public boolean estaEnPie() {
		
		if(!combatienteA.estaEnPie()) {
			if(!combatienteB.estaEnPie())
				return false;
		}
		return true;
		
	}
	
	// le cambie el tipo porque se usa en "Combate" pero antes devolvia un void
	public List<Combatiente> integrantesEnOrden() {
		List<Combatiente> integrantes = new ArrayList<>(
				Arrays.asList(combatienteA, combatienteB));
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

	public boolean estaDerrotada() {
		return !estaEnPie();
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
	public void recuperarEnergiaAsalto(int eNERGIA_POR_ASALTO) {
		combatienteA.recuperarEnergia(eNERGIA_POR_ASALTO);
		combatienteB.recuperarEnergia(eNERGIA_POR_ASALTO);		
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
