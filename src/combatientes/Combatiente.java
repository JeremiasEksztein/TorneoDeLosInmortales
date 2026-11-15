package combatientes;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import combate.Pareja;
import equipamiento.Equipamiento;
import excepciones.EquipamientoInvalidoException;
import eventos.Acontecimiento;
import eventos.AccionRealizada;
import tecnicas.Accion;
import tecnicas.Tecnica;

public abstract class Combatiente implements Comparable<Combatiente> {
	protected final int id;
	 // private CriterioAccion criterio; La comento porque no se usa en ningun lado
	private final String nombre;
	
	//private static final int VIDA_BASE;
	//ctes para condiciones 
	private static final int USOS_POR_TECNICA = 5;
	private static final int MAX_EQUIPAMIENTO = 3;
	// 
	
	private final int vidaBase;//los agrego para el metodo de recalcular los valores
	private final int energiaBase;
	
	private int vidaActual;
	private int vidaMaxima;
	private int energiaActual;
	private int energiaMaxima;
	
	private int reduccionDefensiva;
	
	private List<Equipamiento> equipamiento;
	private Map<Tecnica,Integer> tecnicasDisponibles;
	private Set<Tecnica> usadasEnCombate;
	//private List<Tecnica> tecnicas; 

	/*DOC: La vidaMaxima y energía maxima, llegan por parámetro, en lugar
	 * de estar estar definidas.
	 */
	protected Combatiente(int id, String nombre, int vidaMaxima, int energiaMaxima) {
		this.id = id;
		this.nombre = nombre;
		this.vidaActual = vidaMaxima;
		this.vidaMaxima = vidaMaxima;
		this.energiaActual = energiaMaxima;
		this.energiaMaxima = energiaMaxima;
		this.vidaBase = vidaMaxima;
		this.energiaBase = energiaMaxima;
		equipamiento = new ArrayList<>();
		tecnicasDisponibles = new LinkedHashMap<>();
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
		this.recalcularStatsMaximos();
	}
	public void quitar(Equipamiento actual) {
		if(this.equipamiento.remove(actual)) {//usa equals para encontrar el equipamiento en el arreglo y lo borra
			this.recalcularStatsMaximos();
		}
	}
	
	public void aprenderTecnica(Tecnica t) {
		this.tecnicasDisponibles.put(t, USOS_POR_TECNICA);
	}
	
	public void recibirDanio(int danioBase) {
		int danioFinal = calcularDanioRecibido(danioBase);
		if(this.tieneDefensaActiva()) {//es una manera, acá es como si fuera un consumible que,
			if(this.reduccionDefensiva < danioFinal) {//si el daño lo rompe ya no lo tiene más
				danioFinal -= this.reduccionDefensiva;
				this.reduccionDefensiva = 0;
			}
			else {
				this.reduccionDefensiva-=danioFinal;
				return;
			}
		}
		this.vidaActual -= danioFinal;
		if(this.vidaActual < 0) {
			this.vidaActual = 0;
		}
	}
	
	public void recuperarVida(int cantidad) {
		if(cantidad < 0) {
			throw new IllegalArgumentException("La cantidad de vida no puede ser menor a 0");
		}
		this.vidaActual += cantidad;
		if(this.vidaActual > this.vidaMaxima) {
			this.vidaActual = this.vidaMaxima;
		}
	}
	public void gastarEnergia(int cantidad) {
		if(cantidad < 0) {
			throw new IllegalArgumentException("La cantidad de energia no puede ser menor a 0");
		}
		int cantFinal = calcularCostoEnergia(cantidad);
		
		if(cantFinal > this.energiaActual) {//esto no puede pasar, pero no sé si lanzar una exception es un poco mucho
			throw new IllegalStateException("No se puede gastar más energia");
		}
		this.energiaActual -= cantFinal;
		if(this.energiaActual < 0) {
			this.energiaActual = 0;
		}
	}
	
	public  void quitarEnergia(int cantidad) {
		if(cantidad < 0) {
			throw new IllegalArgumentException("La cantidad de energia no puede ser menor a 0");
		}
		this.energiaActual -= cantidad;
		if(this.energiaActual < 0) {
			this.energiaActual = 0;
		}
		
	}
	public void  recuperarEnergia(int cantidad) {
		if(cantidad < 0) {
			throw new IllegalArgumentException("La cantidad de energia no puede ser menor a 0");
		}
		this.energiaActual += cantidad;
		if(this.energiaActual > this.energiaMaxima) {
			this.energiaActual = this.energiaMaxima;
		}
	}
	public void registrarUso(Tecnica t) {
		if(!this.tecnicasDisponibles.containsKey(t)) {
			throw new IllegalArgumentException("El combatiente no tiene esa tecnica aprendida");
		}
		this.usadasEnCombate.add(t);
		this.tecnicasDisponibles.put(t, (this.tecnicasDisponibles.get(t)-1));
	}
	
	public int usosRestantes(Tecnica t) {
		if(!this.tecnicasDisponibles.containsKey(t)) {
			throw new IllegalStateException("El combatiente no tiene esa tecnica!");//cambiarlo por una nueva excepcion?
		}
		return this.tecnicasDisponibles.get(t);
	}
	
	public boolean usoEnEsteCombate(Tecnica t) {
		return this.usadasEnCombate.contains(t);
	}
	
	public boolean puedeEjecutar(Tecnica t) {
		if(!this.tecnicasDisponibles.containsKey(t)) {
			return false;//si no tiene la tecnica no la puede ejecutar, o poner una excepcion diciendo que no tiene la tecnica
		}
		int costoFinal = calcularCostoEnergia(t.getCosto());
		if(costoFinal > this.energiaActual) {
			return false;
		}
		
		return this.tecnicasDisponibles.get(t) > 0;
	}
	public void reiniciarParaCombate() {
		this.vidaActual = this.vidaMaxima;
		this.energiaActual = this.energiaMaxima;
		this.usadasEnCombate.clear();
		this.reduccionDefensiva = 0;
	}
	public boolean estaEnPie() {
		return this.vidaActual > 0;
	}
	public boolean faltaVida() {
		return this.vidaActual < this.vidaMaxima;
	}
	public int getVida() {
		return this.vidaActual;
	}
	public List<Equipamiento> getEquipamiento(){//
		return this.equipamiento;
	}
	public int getVidaMaxima() {
		return this.vidaMaxima;
	}
	public int energiaMaxima() {
		return this.energiaMaxima;
	}
	public Accion elegirAccion(Pareja p) {
		return null;
	}

	public void procesarEfectosTemporales() {
		// Reservado para efectos temporales; actualmente no hay ninguno persistente.
	}

	/**
	 * Ejecuta una técnica disponible y compatible con el estado del turno.
	 * Si no hay una, realiza el ataque básico contra el primer rival vivo.
	 */

	public Acontecimiento ejecutarTurno(Pareja pareja, List<Combatiente> rivales) {
		if (!estaEnPie() || rivales == null || rivales.isEmpty()) {
			throw new IllegalStateException("No se puede ejecutar un turno sin un rival vivo.");
		}

		Combatiente objetivo = rivales.getFirst(); // ataca al primer rival
		for (Map.Entry<Tecnica, Integer> entrada : tecnicasDisponibles.entrySet()) {
			Tecnica tecnica = entrada.getKey();
			if (entrada.getValue() <= 0 || !puedeEjecutar(tecnica)
					|| (pareja != null && !pareja.puedeUsar(this, tecnica))) {
				continue;
			}
			tecnica.ejecutar(this, tecnica.getTipo() == tecnicas.TipoTecnica.OFENSIVA ? objetivo : this);
			int idObjetivo = tecnica.getTipo() == tecnicas.TipoTecnica.OFENSIVA
					? objetivo.getId() : id;
			return new AccionRealizada(id, tecnica.getNombre(), idObjetivo, 0);
		}

		int danio = calcularDanioProvocado(10);
		objetivo.recibirDanio(danio);
		return new AccionRealizada(id, "Ataque básico", objetivo.getId(), danio);
	}

	@Override
	public int compareTo(Combatiente otro) {
		return Integer.compare(id, otro.id);
	}
	public void  activarDefensa(int reduccion) {
		this.reduccionDefensiva = reduccion;
	}
	public boolean tieneDefensaActiva() {
		return this.reduccionDefensiva > 0;
	}
	
	private void recalcularStatsMaximos() {
		this.vidaMaxima = this.vidaBase;
		this.energiaMaxima = this.energiaBase;
		for(Equipamiento e : this.equipamiento) {
			this.vidaMaxima += e.getBonusVida();//definir abstracto en equipamiento y sobreescribir dependiendo del item
			this.energiaMaxima += e.getBonusEnergia();//son BONUS +10, +20, etc
		}
		if(this.vidaActual > this.vidaMaxima) {
			this.vidaActual = this.vidaMaxima;
		}
		if(this.energiaActual > this.energiaMaxima) {
			this.energiaActual = this.energiaMaxima;
		}
	}
	
	public abstract int calcularDanioProvocado(int cantidad);
	public abstract int calcularDanioRecibido(int n);
	public abstract int calcularCostoEnergia(int n) ;
	
	
	
	
	public String getNombre() {
		return nombre;
	}
	public int getId() {
		return id;
	}
	@Override 
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || !(o instanceof Combatiente)) {
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
