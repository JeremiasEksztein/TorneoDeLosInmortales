package tecnicas;
import java.util.Objects;

import combatientes.Combatiente;
import eventos.AccionRealizada;

public abstract class Tecnica implements Accion {

    private final String nombre;
    private final int costo;
    private final TipoTecnica tipo;

    protected Tecnica(String nombre, int costo, TipoTecnica tipo) {
    	if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre de la técnica no puede ser nulo ni vacío.");
		}
		if (costo < 0) {
			throw new IllegalArgumentException("El costo de energía no puede ser negativo.");
		}
		if (tipo == null) {
			throw new IllegalArgumentException("El tipo de técnica no puede ser nulo.");
		}
    	this.nombre = nombre;
        this.costo = costo;
        this.tipo = tipo;
    }

    // valida antes de mutar estado, gasta energía, registra el uso y aplica el efecto.
 	@Override
 	public final AccionRealizada ejecutar(Combatiente ejecutor, Combatiente rival) {
 		if (ejecutor == null || rival == null) {
 			throw new IllegalArgumentException("El ejecutor y el rival no pueden ser nulos.");
 		}
 		if (!ejecutor.puedeEjecutar(this)) {
 			throw new IllegalStateException("El combatiente no tiene energía o usos suficientes para ejecutar " + nombre);
 		}
 		ejecutor.gastarEnergia(costo);
 		ejecutor.registrarUso(this);
 		return aplicarEfecto(ejecutor, rival);
 	}

    // Cada técnica concreta actualiza el estado del personaje afectado.
 	protected abstract AccionRealizada aplicarEfecto(Combatiente ejecutor, Combatiente rival);
 	
    public int getCosto() {
        return costo;
    }

    // Valor neutro: solo las técnicas ofensivas lo sobrescriben.
    public int getDaño() {
        return 0;
    }

    public TipoTecnica getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
    	if (this == o) return true;
    	if (o == null || getClass() != o.getClass()) return false;
    	
    	return getNombre().equals(((Tecnica) o).getNombre());
    }

    @Override
    public int hashCode() {
    	return getNombre().hashCode();
    }
}