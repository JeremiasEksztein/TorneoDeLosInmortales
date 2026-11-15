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

public abstract class Combatiente implements Comparable<Combatiente> {
	protected final int id;
	private CriterioAccion criterio;
	private String nombre;
	
	//private static final int VIDA_BASE;
	//ctes para condiciones 
	private static final int USOS_POR_TECNICA = 5;
	private static final int MAX_EQUIPAMIENTO = 3;
	// 
	
	private int vidaBase;//los agrego para el metodo de recalcular los valores
	private int energiaBase;
	
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
	
	public void recibirDaño(int dañoBase) {
		int dañoFinal = calcularDañoRecibido(dañoBase);
		if(this.tieneDefensaActiva()) {//es es una manera, acá es como si fuera un consumible que,
			if(this.reduccionDefensiva < dañoFinal) {//si el daño lo rompe ya no lo tiene mas
				dañoFinal -= this.reduccionDefensiva;
				this.reduccionDefensiva = 0;
			}
			else {
				this.reduccionDefensiva-=dañoFinal;
				return;
			}
		}
		this.vidaActual -= dañoFinal;
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
		
		if(cantFinal > this.energiaActual) {//esto no puede pasar pero no sé si lanzar una exception es un poco mucho
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
		int uso = this.tecnicasDisponibles.get(t);
		this.tecnicasDisponibles.put(t, uso - 1);
	}
	
	public int usosRestantes(Tecnica t) {
		if(!this.tecnicasDisponibles.containsKey(t)) {
			throw new IllegalStateException("El combatiente no tiene esa tecnica!");//cambiarlo por una nueva excepcion ?
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
	public void setCriterio(CriterioAccion c) {
		this.criterio = c;
	}
	public int getVidaMaxima() {
		return this.vidaMaxima;
	}
	public int energiaMaxima() {
		return this.energiaMaxima;
	}
	public Accion elegirAccion(Pareja p) {
		//return this.criterio.haceralgo(); //falta definirlo
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
	
	public abstract int calcularDañoProvocado(int cantidad);
	public abstract int calcularDañoRecibido(int n);
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
