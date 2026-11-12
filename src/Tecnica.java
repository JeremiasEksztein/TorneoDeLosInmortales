import java.util.Objects;

public abstract class Tecnica {

    private final String nombre;
    private final int costo;
    private final TipoTecnica tipo;

    protected Tecnica(String nombre, int costo, TipoTecnica tipo) {
        this.nombre = nombre;
        this.costo = costo;
        this.tipo = tipo;
    }

    // Gasta energía (con el costo calculado por el ejecutor), registra el uso y aplica el efecto.
    public void ejecutar(Combatiente ejecutor, Combatiente rival) {
        ejecutor.gastarEnergia(ejecutor.calcularCosto(this));
        ejecutor.registrarUso(this);
        aplicarEfecto(ejecutor, rival);
    }

    // Cada técnica concreta actualiza el estado del personaje afectado.
    public abstract void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
    	
    }

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
        if (!(o instanceof Tecnica)) return false;
        Tecnica otra = (Tecnica) o;
        return nombre.equals(otra.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}