package eventos;

public class Proclamacion extends Acontecimiento {

    private final int idPareja;
    private final String nombreA;
    private final String nombreB;

    public Proclamacion(int idPareja, String nombreA, String nombreB) {
        super("¡" + nombreA + " y " + nombreB
              + " (pareja " + idPareja + ") son los Dos Santos Supremos!");
        this.idPareja = idPareja;
        this.nombreA = nombreA;
        this.nombreB = nombreB;
    }

    public int getIdPareja() { return idPareja; }
    public String getNombreA() { return nombreA; }
    public String getNombreB() { return nombreB; }
}