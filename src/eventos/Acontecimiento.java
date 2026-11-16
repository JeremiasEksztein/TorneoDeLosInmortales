package eventos;

import java.util.Objects;

public abstract class Acontecimiento {

    private final String descripcion;

    protected Acontecimiento(String descripcion) {
    	if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción del acontecimiento no puede ser nula ni vacía.");
        }
    	this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Por defecto un acontecimiento no modifica la tabla.
    // Solo ResultadoCombate lo sobrescribe.
    public void aplicarA(TablaDePosiciones tabla) {
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Acontecimiento that = (Acontecimiento) o;
        return Objects.equals(this.descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getClass(), this.descripcion);
    }
}