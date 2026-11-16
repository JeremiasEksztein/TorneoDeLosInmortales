package eventos;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PublicadorAcontecimientoTest {

    private PublicadorAcontecimiento publicador;
    private List<String> llamados;

    @BeforeEach
    void preparar() {
        publicador = new PublicadorAcontecimiento();
        llamados = new ArrayList<>();
    }

    // Interesado de prueba: anota su nombre cada vez que lo llaman.
    //suprimo el warning rapido, para no tener que recompilar el proyecto en otra version de JAVA
    //que si permita la variable lambda vacía.
    @SuppressWarnings("unused")
    private Interesado interesado(String nombre) {
        return a -> llamados.add(nombre);
    }

    @Test
    void elInteresadoSuscriptoRecibeElAcontecimiento() {
        List<Acontecimiento> recibidos = new ArrayList<>();
        Acontecimiento a = new InicioDeCombate(1, 1, 2);
        publicador.suscribir(recibidos::add);

        publicador.notificar(a);

        assertEquals(List.of(a), recibidos);
    }

    @Test
    void losInteresadosRecibenEnElOrdenDeSuscripcion() {
        publicador.suscribir(interesado("A"));
        publicador.suscribir(interesado("B"));
        publicador.suscribir(interesado("C"));

        publicador.notificar(new InicioDeCombate(1, 1, 2));

        assertEquals(List.of("A", "B", "C"), llamados);
    }

    @Test
    void suscribirDosVecesAlMismoInteresadoNoDuplicaLaRecepcion() {
        Interesado a = interesado("A");
        publicador.suscribir(a);
        publicador.suscribir(a);

        publicador.notificar(new InicioDeCombate(1, 1, 2));

        assertEquals(List.of("A"), llamados);
    }

    @Test
    void elInteresadoDesuscriptoDejaDeRecibir() {
        Interesado a = interesado("A");
        Interesado b = interesado("B");
        publicador.suscribir(a);
        publicador.suscribir(b);

        publicador.desuscribir(a);
        publicador.notificar(new InicioDeCombate(1, 1, 2));

        assertEquals(List.of("B"), llamados);
    }

    @Test
    void desuscribirUnInteresadoNuloNoFalla() {
        assertDoesNotThrow(() -> publicador.desuscribir(null));
    }

    @Test
    void noSePuedeSuscribirUnInteresadoNulo() {
        assertThrows(NullPointerException.class,
            () -> publicador.suscribir(null));
    }

    @Test
    void noSePuedeNotificarUnAcontecimientoNulo() {
        assertThrows(IllegalArgumentException.class,
            () -> publicador.notificar(null));
    }
}