package combatientes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import combate.CriterioAccion;
import combate.Pareja;
import equipamiento.Equipamiento;
import excepciones.EquipamientoInvalidoException;
import tecnicas.Accion;
import tecnicas.Tecnica;

public abstract class Combatiente {
	protected final int id;
	private CriterioAccion criterio;
	private String nombre;
	
	//private static final int VIDA_BASE;
	//ctes para condiciones 
	private static final int USOS_POR_TECNICA = 5;
	private static final int MAX_EQUIPAMIENTO = 3;
	// 
	private int vidaActual;
	private int vidaMaxima;
	private int energiaActual;
	private int energiaMaxima;
	
	private int reduccionDefensiva;
	
	private List<Equipamiento> equipamiento;
	private Map<Tecnica,Integer> tecnicasDisponibles;
	private Set<Tecnica> usadasEnCombate;
	//private List<Tecnica> tecnicas; 

	
	protected Combatiente(int id, String nombre, int vidaMaxima, int energiaMaxima) {
		this.id = id;
		this.nombre = nombre;
		this.vidaActual = vidaMaxima;
		this.vidaMaxima = vidaMaxima;
		this.energiaActual = energiaMaxima;
		this.energiaMaxima = energiaMaxima;

		equipamiento = new ArrayList<>();
		tecnicasDisponibles = new HashMap<>();
		usadasEnCombate = new HashSet<>();
		//tecnicas = new ArrayList<>();
		
	}
	public void equipar(Equipamiento nuevo) {
		if(this.equipamiento.size() >= MAX_EQUIPAMIENTO) {
			throw new EquipamientoInvalidoException("No se puede tener más de 3 equipamientos");
		}
		for(Equipamiento e : this.equipamiento) {
			if(e.getTipo()==nuevo.getTipo()) {
				throw new EquipamientoInvalidoException("No se puede tener dos equipamientos del mismo tipo");
			}
		}
		this.equipamiento.add(nuevo);
	}
	public void quitar(Equipamiento actual) {
		if(this.equipamiento.remove(actual)) {//usa equals para encontrar el equipamiento en el arreglo y lo borra
			//la logica para los modificadores de vida max, etc
		}
	}
	
	public abstract void aprender(Tecnica t) {
		
	}
	public abstract Accion elegirAccion(Pareja p) {
		return Accion;
	}
	public abstract void recibirDaño(int n) {
		
	}
	public abstract  void recuperarVida(int n) {
		
	}
	public abstract  void gastarEnergia(int n) {
		
	}
	public abstract  void quitarEnergia(int n) {
		
	}
	public abstract void  recuperarEnergia(int n) {
		
	}
	public abstract void registrarUso(Tecnica t) {
		
	}
	public abstract int usosRestantes(Tecnica t) {
		
	}
	public abstract boolean usoEnEsteCombate(Tecnica t) {
		
	}
	public abstract boolean puedeEjecutar(Tecnica t) {
		
	}
	public abstract void  activarDefensa(int n) {
		
	}
	public abstract boolean tieneDefensaActiva() {
		
	}
	public abstract void reiniciarParaCombate() {
		
	}
	public abstract boolean estaEnPie() {
		
	}
	public abstract boolean faltaVida() {
		
	}
	public abstract int vidaMaxima() {
		
	}
	public abstract int energiaMaxima() {
		
	}
	public abstract List<Equipamiento> getEquipamiento(){
		
	}
	public abstract void setCriterio(Criterio c) {
		
	}
	public abstract int calcularDañoProvocado() ;
	public abstract int calcularDañoRecibido(int n);
	public abstract int calcularCosto(int n) {
		
	}
	@Override 
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || this.getClass() != o.getClass()) {
			return false;
		}
		Combatiente c = (Combatiente) o;
		return this.id == c.id;
	}
	@Override
	public int hashCode() {
		return Integer.hashCode(this.id);
	}

}
