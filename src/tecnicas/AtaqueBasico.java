package tecnicas;
import combatientes.Combatiente;

public class AtaqueBasico implements Accion {
    private static final int DANIO_BASE = 10; // Daño del ataque básico

    @Override
    public void ejecutar(Combatiente ejecutor, Combatiente objetivo) {
        // Implementación del ataque básico
        int danio = ejecutor.calcularDanioProvocado(DANIO_BASE);

        objetivo.recibirDanio(danio);
    }
    @Override 
    public String getNombre() {
        return "Ataque Básico";
    }
}
