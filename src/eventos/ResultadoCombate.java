package eventos;

public class ResultadoCombate extends Acontecimiento {

    private final int numeroRonda;
    private final int idParejaA;
    private final int idParejaB;
    private final int vidaTotalA;
    private final int vidaTotalB;
    private final int idGanadora;

    public ResultadoCombate(int numeroRonda, int idParejaA, int idParejaB,
                            int vidaTotalA, int vidaTotalB, int idGanadora) {
        super("Ronda " + numeroRonda + ": gana la pareja " + idGanadora
              + " (pareja " + idParejaA + ": " + vidaTotalA + " de vida, pareja "
              + idParejaB + ": " + vidaTotalB + " de vida)");
        if (idGanadora != idParejaA && idGanadora != idParejaB) {
            throw new IllegalArgumentException(
                "La ganadora debe ser una de las dos parejas del combate");
        }
        this.numeroRonda = numeroRonda;
        this.idParejaA = idParejaA;
        this.idParejaB = idParejaB;
        this.vidaTotalA = vidaTotalA;
        this.vidaTotalB = vidaTotalB;
        this.idGanadora = idGanadora;
    }

    @Override
    public void aplicarA(TablaDePosiciones tabla) {
        tabla.registrar(idParejaA, numeroRonda, vidaTotalA);
        tabla.registrar(idParejaB, numeroRonda, vidaTotalB);
    }

    public int getNumeroRonda() { return numeroRonda; }
    public int getIdParejaA() { return idParejaA; }
    public int getIdParejaB() { return idParejaB; }
    public int getVidaTotalA() { return vidaTotalA; }
    public int getVidaTotalB() { return vidaTotalB; }
    public int getIdGanadora() { return idGanadora; }
}