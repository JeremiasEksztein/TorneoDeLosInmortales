package eventos;

public class InicioDeCombate extends Acontecimiento {

    private final int numeroRonda;
    private final int idParejaA;
    private final int idParejaB;

    public InicioDeCombate(int numeroRonda, int idParejaA, int idParejaB) {
        super("Ronda " + numeroRonda + ": comienza el combate entre la pareja "
              + idParejaA + " y la pareja " + idParejaB);
        this.numeroRonda = numeroRonda;
        this.idParejaA = idParejaA;
        this.idParejaB = idParejaB;
    }

    public int getNumeroRonda() { return numeroRonda; }
    public int getIdParejaA() { return idParejaA; }
    public int getIdParejaB() { return idParejaB; }
}