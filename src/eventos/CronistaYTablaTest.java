package eventos;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class CronistaYTablaTest {

    @Test
    void armaLaTablaConLosResultadosQueRecibe() {
        CronistaDeLaSecta cronista = new CronistaDeLaSecta();

        cronista.recibir(new InicioDeCombate(1, 1, 2));
        cronista.recibir(new ResultadoCombate(1, 1, 2, 70, 0, 1));
        cronista.recibir(new ResultadoCombate(2, 1, 3, 20, 0, 1));

        List<Posicion> tabla = cronista.tablaDePosiciones();
        assertEquals(3, tabla.size());
        assertEquals(1, tabla.get(0).getIdPareja());   // llegó a la ronda 2
        assertEquals(2, tabla.get(0).getUltimaRonda());
        assertEquals(20, tabla.get(0).getVidaTotalFinal());
    }

    @Test
    void losAcontecimientosSimplesNoAfectanLaTabla() {
        CronistaDeLaSecta cronista = new CronistaDeLaSecta();

        cronista.recibir(new AccionRealizada("Perseo", "Ataque básico", "Percefone", 10));
        cronista.recibir(new Proclamacion(1, "A", "B"));

        assertTrue(cronista.tablaDePosiciones().isEmpty());
    }
}