package torneo;

import java.util.ArrayList;
import java.util.List;

import combate.Pareja;
import eventos.PublicadorAcontecimiento;

public class Ronda {

    private final int numero;
    private final List<Cruce> cruces = new ArrayList<>();

    // Arma los cruces en orden: 1ª contra 2ª, 3ª contra 4ª, etc.
    public Ronda(int numero, List<Pareja> parejas) {
        if (parejas.size() % 2 != 0) {
            throw new IllegalArgumentException("Una ronda necesita una cantidad par de parejas");
        }
        this.numero = numero;
        for (int i = 0; i < parejas.size(); i += 2) {
            cruces.add(new Cruce(parejas.get(i), parejas.get(i + 1)));
        }
    }

    public void jugar(PublicadorAcontecimiento publicador) {
        for (Cruce c : cruces) {
            c.resolver(numero, publicador);
        }
    }

    // Las ganadoras salen en el orden de los cruces, que es lo que
    // determina los cruces de la ronda siguiente.
    public List<Pareja> getGanadoras() {
        List<Pareja> ganadoras = new ArrayList<>();
        for (Cruce c : cruces) {
            ganadoras.add(c.getGanador());
        }
        return ganadoras;
    }

    public int getNumero() {
        return numero;
    }

    public List<Cruce> getCruces() {
        return List.copyOf(cruces);
    }
}