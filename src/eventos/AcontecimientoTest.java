package eventos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AcontecimientoTest {

    @Test
    void inicioDeCombateGuardaLosDatosDelMomento() {
        InicioDeCombate a = new InicioDeCombate(2, 3, 5);

        assertEquals(2, a.getNumeroRonda());
        assertEquals(3, a.getIdParejaA());
        assertEquals(5, a.getIdParejaB());
        assertTrue(a.getDescripcion().contains("Ronda 2"));
    }

    @Test
    void accionRealizadaGuardaEjecutorAccionYObjetivo() {
        AccionRealizada a = new AccionRealizada("Maradona", "Ataque básico", "Messi", 12);

        assertEquals("Maradona", a.getNomEjecutor());
        assertEquals("Ataque básico", a.getNombreAccion());
        assertEquals("Messi", a.getNomObjetivo());
        assertEquals(12, a.getPuntos());
    }

    @Test
    void proclamacionGuardaALosCampeones() {
        Proclamacion p = new Proclamacion(7, "Aurelio", "Nébula");

        assertEquals(7, p.getIdPareja());
        assertTrue(p.getDescripcion().contains("Aurelio"));
        assertTrue(p.getDescripcion().contains("Nébula"));
    }

    @Test
    void losAcontecimientosSimplesNoModificanLaTabla() {
        TablaDePosiciones tabla = new TablaDePosiciones();

        new InicioDeCombate(1, 1, 2).aplicarA(tabla);
        new AccionRealizada("A", "Ataque básico", "B", 10).aplicarA(tabla);
        new Proclamacion(1, "A", "B").aplicarA(tabla);

        assertTrue(tabla.ordenada().isEmpty());
    }
}