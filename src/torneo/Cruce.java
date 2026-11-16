package torneo;

import combate.Combate;
import combate.Pareja;
import eventos.PublicadorAcontecimiento;
import eventos.ResultadoCombate;

public class Cruce {

    private final Pareja parejaA;
    private final Pareja parejaB;
    private ResultadoCombate resultado;

    public Cruce(Pareja parejaA, Pareja parejaB) {
        this.parejaA = parejaA;
        this.parejaB = parejaB;
    }

    public void resolver(int numeroRonda, PublicadorAcontecimiento publicador) {
        resultado = new Combate(parejaA, parejaB, numeroRonda, publicador).ejecutar();
    }

    public Pareja getGanador() {
        if (resultado == null) {
            throw new IllegalStateException("El cruce todavía no se resolvió");
        }
        return resultado.getIdGanadora() == parejaA.getId() ? parejaA : parejaB;
    }

    public Pareja getParejaA() { return parejaA; }
    public Pareja getParejaB() { return parejaB; }
}