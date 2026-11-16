package eventos;

public class EfectoAplicado extends Acontecimiento {

    private final int idAfectado;
    private final String nombreEfecto;
    private final int puntos;

    public EfectoAplicado(String descripcion) {
        super(descripcion);
        this.idAfectado = -1;
        this.nombreEfecto = "";
        this.puntos = 0;
    }
    
    public EfectoAplicado(int idAfectado, String nombreEfecto, int puntos) {
        super("El combatiente " + idAfectado + " sufre daño por " + nombreEfecto
              + " (" + puntos + " puntos)");
        this.idAfectado = idAfectado;
        this.nombreEfecto = nombreEfecto;
        this.puntos = puntos;
    }

    public int getIdAfectado() { return idAfectado; }
    public String getNombreEfecto() { return nombreEfecto; }
    public int getPuntos() { return puntos; }
}