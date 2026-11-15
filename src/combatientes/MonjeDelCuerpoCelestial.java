package combatientes;

import java.util.List;

import combate.CriterioAccion;
import combate.Pareja;
import equipamiento.Equipamiento;
import tecnicas.Accion;
import tecnicas.Tecnica;

public class MonjeDelCuerpoCelestial extends Combatiente{
	private int REDUCCION_DAÑO;
	private static final int VIDA_INICIAL = 150; //dsp los cambiamos
	private static final int ENERGIA_INICIAL = 300;
	public MonjeDelCuerpoCelestial(int id, String nombre) {
		super(id, nombre, VIDA_INICIAL, ENERGIA_INICIAL);
	}
	
	public int calcularDañoRecibido(int daño) {
		return daño;
	}

	@Override
	public void aprender(Tecnica t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Accion elegirAccion(Pareja p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recibirDaño(int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recuperarVida(int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gastarEnergia(int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quitarEnergia(int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recuperarEnergia(int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarUso(Tecnica t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int usosRestantes(Tecnica t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean usoEnEsteCombate(Tecnica t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean puedeEjecutar(Tecnica t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void activarDefensa(int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tieneDefensaActiva() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reiniciarParaCombate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean estaEnPie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean faltaVida() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int vidaMaxima() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int energiaMaxima() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Equipamiento> getEquipamiento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCriterio(CriterioAccion c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int calcularDañoProvocado() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calcularCostoEnergia(int n) {
		// TODO Auto-generated method stub
		return 0;
	}
}
