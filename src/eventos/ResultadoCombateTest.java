package eventos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ResultadoCombateTest {

    @Test
    void guardaLosDatosDelMomentoEnQueTerminoElCombate() {
        ResultadoCombate r = new ResultadoCombate(1, 1, 2, 70, 0, 1);

        assertEquals(1, r.getNumeroRonda());
        assertEquals(1, r.getIdParejaA());
        assertEquals(2, r.getIdParejaB());
        assertEquals(70, r.getVidaTotalA());
        assertEquals(0, r.getVidaTotalB());
        assertEquals(1, r.getIdGanadora());
    }

    @Test
    void laDescripcionIncluyeRondaYGanadora() {
        ResultadoCombate r = new ResultadoCombate(2, 3, 5, 40, 10, 3);

        assertTrue(r.getDescripcion().contains("Ronda 2"));
        assertTrue(r.getDescripcion().contains("pareja 3"));
    }

    @Test
    void aceptaComoGanadoraACualquieraDeLasDosParejas() {
        assertDoesNotThrow(() -> new ResultadoCombate(1, 1, 2, 0, 50, 2));
    }

    @Test
    void rechazaUnaGanadoraQueNoJugoElCombate() {
        assertThrows(IllegalArgumentException.class,
            () -> new ResultadoCombate(1, 1, 2, 70, 0, 9));
    }
}