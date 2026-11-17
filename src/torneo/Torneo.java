package torneo;

import java.util.ArrayList;
import java.util.List;

import combate.Pareja;
import eventos.Interesado;
import eventos.Proclamacion;
import eventos.PublicadorAcontecimiento;
import excepciones.CantidadDeParejasInvalidaException;
import excepciones.InscripcionDuplicadaException;

public class Torneo {

    private final List<Pareja> parejasInscriptas = new ArrayList<>();
    private final PublicadorAcontecimiento publicador = new PublicadorAcontecimiento();
    private Pareja campeon;
    private final List<Ronda> rondas = new ArrayList<>();

    public void suscribir(Interesado interesado) {
        publicador.suscribir(interesado);
    }

    public void desuscribir(Interesado interesado) {
        publicador.desuscribir(interesado);
    }

    public void inscribir(Pareja p) {
    	if (p == null) {
			throw new IllegalArgumentException("La pareja a inscribir no puede ser nula.");
		}
		if (campeon != null) {
			throw new IllegalStateException("No se pueden inscribir parejas en un torneo ya finalizado.");
		}
    	for (Pareja existente : parejasInscriptas) {
            if (existente.equals(p)) {
                throw new InscripcionDuplicadaException(
                    "La pareja " + p.getId() + " ya está inscripta");
            }
            if (compartenCombatiente(existente, p)) {
                throw new InscripcionDuplicadaException(
                    "Un combatiente de la pareja " + p.getId() + " ya está inscripto");
            }
        }
        parejasInscriptas.add(p);
    }

    public void iniciar() {
    	if (campeon != null) {
			throw new IllegalStateException("El torneo ya fue ejecutado.");
		}
    	
    	int cantidad = parejasInscriptas.size();
        if (cantidad != 4 && cantidad != 8) {
            throw new CantidadDeParejasInvalidaException(
                "El torneo requiere 4 u 8 parejas y hay " + cantidad);
        }

        List<Pareja> vigentes = new ArrayList<>(parejasInscriptas);
        int numero = 1;

        while (vigentes.size() > 1) {
            Ronda ronda = new Ronda(numero, vigentes);
            ronda.jugar(publicador);
            rondas.add(ronda);
            vigentes = ronda.getGanadoras();
            numero++;
        }

        campeon = vigentes.get(0);
        publicador.notificar(new Proclamacion(campeon.getId(),
            campeon.getCombatienteA().getNombre(),
            campeon.getCombatienteB().getNombre()));
    }

    public List<Ronda> getRondas() {
        return List.copyOf(rondas);
    }

    public Pareja getCampeon() {
        if (campeon == null) {
            throw new IllegalStateException("El torneo todavía no terminó");
        }
        return campeon;
    }

    private boolean compartenCombatiente(Pareja x, Pareja y) {
        return x.getCombatienteA().equals(y.getCombatienteA())
            || x.getCombatienteA().equals(y.getCombatienteB())
            || x.getCombatienteB().equals(y.getCombatienteA())
            || x.getCombatienteB().equals(y.getCombatienteB());
    }
}