package eventos;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class CronistaDeLaSectaTest {

    @Test
    void empiezaConElHistorialVacio() {
        assertTrue(new CronistaDeLaSecta().getHistorial().isEmpty());
    }

    @Test
    void guardaLosAcontecimientosEnElOrdenEnQueLosRecibe() {
        CronistaDeLaSecta cronista = new CronistaDeLaSecta();
        Acontecimiento inicio = new InicioDeCombate(1, 1, 2);
        Acontecimiento accion = new AccionRealizada(1, "Ataque básico", 3, 10);
        Acontecimiento caida = new Eliminacion(3, "Kael");

        cronista.recibir(inicio);
        cronista.recibir(accion);
        cronista.recibir(caida);

        assertEquals(List.of(inicio, accion, caida), cronista.getHistorial());
    }

    @Test
    void elHistorialDevueltoNoSePuedeModificar() {
        CronistaDeLaSecta cronista = new CronistaDeLaSecta();
        cronista.recibir(new InicioDeCombate(1, 1, 2));

        assertThrows(UnsupportedOperationException.class,
            () -> cronista.getHistorial().clear());
    }
}