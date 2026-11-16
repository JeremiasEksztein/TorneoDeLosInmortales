package combatientes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import combate.CriterioAccion;
import combate.CriterioDefecto;
import combate.Pareja;
import efectos.EfectoTemporal;
import equipamiento.Equipamiento;
import excepciones.EquipamientoInvalidoException;
import eventos.Acontecimiento;
import eventos.EfectoAplicado;
import eventos.AccionRealizada;
import tecnicas.Tecnica;
import tecnicas.TipoTecnica;
import equipamiento.TipoEquipamiento;
import tecnicas.Accion; 

public abstract class Combatiente implements Comparable<Combatiente>{
	protected final int id;
	private final String nombre;
	private CriterioAccion criterio;

	private static final int USOS_POR_TECNICA = 5;
	private static final int MAX_EQUIPAMIENTO = 3;
	private static final int DANIO_BASE_ATAQUE_BASICO = 10;
	
	private final int vidaBase;
	private final int energiaBase;
	
	private int vidaActual;
	private int vidaMaxima;
	private int energiaActual;
	private int energiaMaxima;
	
	private int reduccionDefensiva;
		
	private List<Equipamiento> equipamiento;
	private Map<Tecnica, Integer> tecnicasDisponibles;
	private Set<Tecnica> usadasEnCombate;
	private final List<EfectoTemporal> efectosActivos = new ArrayList<>();

	/*DOC: La vidaMaxima y energía maxima, llegan por parámetro, en lugar
	 * de estar estar definidas.
	 */
	protected Combatiente(int id, String nombre, int vidaMaxima, int energiaMaxima) {
		if (id <= 0) {
			throw new IllegalArgumentException("El identificador del combatiente debe ser positivo.");
		}
		if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre del combatiente no puede ser nulo ni vacío.");
		}
		if (vidaMaxima <= 0 || energiaMaxima < 0) {
			throw new IllegalArgumentException("La vida y energía iniciales deben ser válidas.");
		}
		this.id = id;
		this.nombre = nombre;
		this.vidaActual = vidaMaxima;
		this.vidaMaxima = vidaMaxima;
		this.energiaActual = energiaMaxima;
		this.energiaMaxima = energiaMaxima;
		this.vidaBase = vidaMaxima;
		this.energiaBase = energiaMaxima;
		this.equipamiento = new ArrayList<>();
		this.tecnicasDisponibles = new LinkedHashMap<>();
		this.usadasEnCombate = new HashSet<>();
		this.criterio = new CriterioDefecto();

		//this.tecnicas = new ArrayList<>();
	}

	public void equipar(Equipamiento nuevo) {
		if (nuevo == null) {
			throw new IllegalArgumentException("El equipamiento no puede ser nulo.");
		}
		if (this.equipamiento.size() >= MAX_EQUIPAMIENTO) {
			throw new EquipamientoInvalidoException("No se puede tener más de 3 equipamientos.");
		}
		for (Equipamiento e : this.equipamiento) {
			if (e.getTipo() == nuevo.getTipo()) {
				throw new EquipamientoInvalidoException("No se puede tener dos equipamientos del mismo tipo: " + nuevo.getTipo());
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
		if (t == null) {
			throw new IllegalArgumentException("La técnica no puede ser nula.");
		}
		this.tecnicasDisponibles.put(t, USOS_POR_TECNICA);
	}
	/*
	Seccion 2:
	"Muro del Cuerpo Dorado: reduce el daño que recibe el ejecutor durante el resto del combate."
	Seccion 6:
	"Criterio por defecto, en este orden: una técnica de recuperación si le falta vida; una defensiva si no tiene una activa..."
	*/
	public void recibirDanio(int danioBase) {
		if (danioBase < 0) {
			throw new IllegalArgumentException("El daño base no puede ser negativo.");
		}
		int danioFinal = calcularDanioRecibido(danioBase);
		if (this.tieneDefensaActiva()) {
			danioFinal = Math.max(0, danioFinal - this.reduccionDefensiva);
		}
		this.vidaActual = Math.max(0, this.vidaActual - danioFinal);
	}
	
	/*
	public void recibirDanio(int danioBase) {
		if (danioBase < 0) {
			throw new IllegalArgumentException("El daño base no puede ser negativo.");
		}
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
	*/
	
	public void recuperarVida(int cantidad) {
		if(cantidad < 0) {
			throw new IllegalArgumentException("La cantidad de vida no puede ser menor a 0");
		}
		this.vidaActual = Math.min(this.vidaMaxima, this.vidaActual + cantidad);
	}

	public void gastarEnergia(int cantidad) {
		if(cantidad < 0) {
			throw new IllegalArgumentException("La cantidad de energia no puede ser menor a 0");
		}
		int cantFinal = calcularCostoEnergia(cantidad);
		
		if(cantFinal > this.energiaActual) {//esto no puede pasar, pero no sé si lanzar una exception es un poco mucho
			throw new IllegalStateException("No se puede gastar más energia de la disponible");
		}
		this.energiaActual = Math.max(0, this.energiaActual - cantFinal);
	}
	
	public  void quitarEnergia(int cantidad) {
		if(cantidad < 0) {
			throw new IllegalArgumentException("La cantidad de energia no puede ser menor a 0");
		}
		this.energiaActual = Math.max(0, this.energiaActual - cantidad);
	}
	
	public void  recuperarEnergia(int cantidad) {
		if(cantidad < 0) {
			throw new IllegalArgumentException("La cantidad de energia no puede ser menor a 0");
		}
		this.energiaActual = Math.min(this.energiaMaxima, this.energiaActual + cantidad);
	}
	
	public void registrarUso(Tecnica t) {
		if(!this.tecnicasDisponibles.containsKey(t)) {
			throw new IllegalArgumentException("El combatiente no tiene esa tecnica aprendida o no le quedan usos");
		}
		this.usadasEnCombate.add(t);
		int restantes = this.tecnicasDisponibles.get(t) - 1;
		this.tecnicasDisponibles.put(t, Math.max(0, restantes));
	}
	
	public int usosRestantes(Tecnica t) {
		return this.tecnicasDisponibles.getOrDefault(t, 0);
	}
	
	public boolean usoEnEsteCombate(Tecnica t) {
		return this.usadasEnCombate.contains(t);
	}
	
	public boolean puedeEjecutar(Tecnica t) {
		if(!this.tecnicasDisponibles.containsKey(t)) {
			return false;//si no tiene la tecnica no la puede ejecutar, o poner una excepcion diciendo que no tiene la tecnica
		}
		int costoFinal = calcularCostoEnergia(t.getCosto());
		return costoFinal <= this.energiaActual && this.tecnicasDisponibles.get(t) > 0;
	}

	public boolean puedeUsarTecnica(Pareja pareja, Tecnica t) {
		return puedeEjecutar(t) && (pareja == null || pareja.puedeUsar(this, t));
	}

	public void reiniciarParaCombate() {
		this.vidaActual = this.vidaMaxima;
		this.energiaActual = this.energiaMaxima;
		this.usadasEnCombate.clear();
		this.reduccionDefensiva = 0;
		this.efectosActivos.clear();
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

	public List<Equipamiento> getEquipamiento() {
		return new ArrayList<>(this.equipamiento);
	}

	public int getVidaMaxima() {
		return this.vidaMaxima;
	}

	public int getEnergiaActual() {
		return this.energiaActual;
	}

	public int getEnergiaMaxima() {
		return this.energiaMaxima;
	}

	public Accion elegirAccion(Pareja p) {
		if(this.criterio == null) {
			throw new IllegalStateException("No se ha definido un criterio de acción para el combatiente.");
		}
		return this.criterio.elegirAccion(this, p);
	}
	
	public void aplicarEfectoTemporal(EfectoTemporal efecto) {
		if (efecto == null) {
			throw new IllegalArgumentException("El efecto temporal no puede ser nulo.");
		}
		this.efectosActivos.add(efecto);
	}

	public void recibirDanioDirecto(int danio) {
		if (danio < 0) {
			throw new IllegalArgumentException("El daño directo no puede ser negativo.");
		}
		this.vidaActual = Math.max(0, this.vidaActual - danio);
	}
	
	public List<EfectoTemporal> getEfectosActivos() {
		return new ArrayList<>(this.efectosActivos);
	}
	
	public List<EfectoAplicado> procesarEfectosTemporales() {
		List<EfectoAplicado> eventos = new ArrayList<>();
		for (EfectoTemporal efecto : this.efectosActivos) {
			String mensaje = efecto.procesarTurno(this);
			if (mensaje != null) {
				eventos.add(new EfectoAplicado(mensaje));
			}
		}
		this.efectosActivos.removeIf(efecto -> !efecto.estaActivo());
		return eventos;
	}

	/**
	 * Ejecuta una técnica disponible y compatible con el estado del turno.
	 * Si no hay una, realiza el ataque básico contra el primer rival vivo.
	 * Criterio de objetivo por defecto según enunciado:
	 * Rival en pie con menos vida actual; en empate, menor ID.
	 */
	public Combatiente elegirObjetivoRival(List<Combatiente> rivales) {
		if (rivales == null || rivales.isEmpty()) {
			throw new IllegalArgumentException("La lista de rivales no puede ser nula ni vacía.");
		}
		Combatiente seleccionado = null;
		for (Combatiente r : rivales) {
			if (r != null && r.estaEnPie()) {
				if (seleccionado == null
						|| r.getVida() < seleccionado.getVida()
						|| (r.getVida() == seleccionado.getVida() && r.getId() < seleccionado.getId())) {
					seleccionado = r;
				}
			}
		}
		if (seleccionado == null) {
			throw new IllegalStateException("No hay rivales en pie como objetivo.");
		}
		return seleccionado;
	}

	/**
	 * Criterio de acción por defecto según enunciado:
	 * 1. Técnica de recuperación si le falta vida.
	 * 2. Si no, técnica ofensiva con más usos restantes.
	 * 3. Si ninguna es posible, null (para realizar ataque básico).
	 */
	public Tecnica seleccionarTecnicaPorDefecto(Pareja pareja) {
		// 1. Recuperación si le falta vida
		if (faltaVida()) {
			for (Map.Entry<Tecnica, Integer> entry : tecnicasDisponibles.entrySet()) {
				Tecnica t = entry.getKey();
				if (t.getTipo() == TipoTecnica.RECUPERACION && puedeUsarTecnica(pareja, t)) {
					return t;
				}
			}
		}

		// 2. Ofensiva con más usos restantes
		Tecnica masUsosOfensiva = null;
		int maxUsos = -1;
		for (Map.Entry<Tecnica, Integer> entry : tecnicasDisponibles.entrySet()) {
			Tecnica t = entry.getKey();
			if (t.getTipo() == TipoTecnica.OFENSIVA && puedeUsarTecnica(pareja, t)) {
				int usos = entry.getValue();
				if (usos > maxUsos) {
					maxUsos = usos;
					masUsosOfensiva = t;
				}
			}
		}

		return masUsosOfensiva;
	}

	/**
	 * Ejecuta el turno del combatiente siguiendo las reglas de la sección 6.
	 */
	public Acontecimiento ejecutarTurno(Pareja pareja, List<Combatiente> rivales) {
		if (!estaEnPie()) {
			throw new IllegalStateException("Un combatiente caído no puede ejecutar un turno.");
		}
		Combatiente objetivoRival = elegirObjetivoRival(rivales);
		Accion accionElegida = elegirAccion(pareja);
		return accionElegida.ejecutar(this, objetivoRival);
	}

	public void setCriterio(CriterioAccion criterio) {
		if (criterio == null) {
			throw new IllegalArgumentException("El criterio de acción no puede ser nulo.");
		}
		this.criterio = criterio;
	}

	public void activarDefensa(int reduccion) {
		if (reduccion < 0) {
			throw new IllegalArgumentException("La reducción defensiva no puede ser negativa.");
		}
		this.reduccionDefensiva = Math.max(this.reduccionDefensiva, reduccion);
	}

	public boolean tieneDefensaActiva() {
		return this.reduccionDefensiva > 0;
	}
	
	private void recalcularStatsMaximos() {
		this.vidaMaxima = this.vidaBase;
		this.energiaMaxima = this.energiaBase;

		for(Equipamiento e : this.equipamiento) {
			this.vidaMaxima = e.calcularVidaMaximaBonificada(this.vidaMaxima);
			this.energiaMaxima = e.calcularEnergiaBonificada(this.energiaMaxima);
		}
		// Regla de la sección 4: quitar un equipo que aumenta máximo recorta la vida/energía si supera el nuevo tope
		if (this.vidaActual > this.vidaMaxima) {
			this.vidaActual = this.vidaMaxima;
		}
		if (this.energiaActual > this.energiaMaxima) {
			this.energiaActual = this.energiaMaxima;
		}
	}
	
	public int calcularDanioProvocado(int danioBase) {
		if (danioBase < 0) {
			throw new IllegalArgumentException("El daño base no puede ser negativo.");
		}
		int danio = danioBase;
		for (EfectoTemporal efecto : this.efectosActivos) {
			danio = efecto.modificarDanioProvocado(danio);
		}
		return Math.max(0, danio);
	}
	
	
	public int calcularDanioRecibido(int n){

		for(Equipamiento equipo : getEquipamiento()) {
			if(equipo.getTipo() == TipoEquipamiento.ARMADURA) {
				n=equipo.calcularDanioRecibidoBonificado(n);

			}
		}
		return Math.max(0, n);
	}

	public int calcularCostoEnergia(int n) {
		return n;
	}

	public String getNombre() {
		return nombre;
	}

	public int getId() {
		return id;
	}

	public Map<Tecnica, Integer> getTecnicas() {
		return new LinkedHashMap<>(this.tecnicasDisponibles);
	}

	@Override
	public int compareTo(Combatiente otro) {
		return Integer.compare(this.id, otro.id);
	}

	@Override 
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || !(o instanceof Combatiente)) {
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
