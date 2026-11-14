package eventos;

public abstract class Acontecimiento {

    private final String descripcion;

    protected Acontecimiento(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Por defecto un acontecimiento no modifica la tabla.
    // Solo ResultadoCombate lo sobrescribe.
    public void aplicarA(TablaDePosiciones tabla) {
    }
}