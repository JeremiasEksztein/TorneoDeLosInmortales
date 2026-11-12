
public abstract class Combatiente {
	private int id;
	private String nombre;
	private int VIDA_BASE;
	private int USOS_POR_TECNICA;
	private int MAX_EQUIPAMIENTO;
	private int vida;
	private int energia;
	private List<Equipamiento> equipamiento;
	private CriterioAccion criterio;
	private Map<Tecnica,Integer> usosRestantes;
	private Set<Tecnica> usadasEnCombate;
	private int reduccionDefensiva;
	private List<Tecnica> tecnicas;
	
	
	public abstract void equipar(Equipamiento e) {
		
	}
	public abstract void quitar(Equipamiento e) throws RuntimeException{
		throw new RuntimeException();
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
	public abstract int calcularDañoProvocado() {
		
	}
	public abstract int calcularDañoRecibido(int n) {
		
	}
	public abstract int calcularCosto(int n) {
		
	}
	//falta equals y hash
}
